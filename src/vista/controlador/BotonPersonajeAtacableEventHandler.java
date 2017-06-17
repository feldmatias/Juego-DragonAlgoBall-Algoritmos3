package vista.controlador;


import javafx.animation.PauseTransition;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import modelo.excepciones.AtaqueNoPosible;
import modelo.juego.DragonBall;
import modelo.personajes.Personaje;
import vista.BotonInvisible;
import vista.VistaJuego;

public class BotonPersonajeAtacableEventHandler implements EventHandler<MouseEvent> {

	private DragonBall juego;
	private Personaje personaje;
	private Label acciones;
	private VistaJuego vista;
	private BotonInvisible boton;

	public BotonPersonajeAtacableEventHandler(DragonBall juego, Personaje personaje, Label labelAcciones, VistaJuego vista, BotonInvisible boton) {
		this.juego = juego;
		this.personaje = personaje;
		this.acciones = labelAcciones;
		this.vista = vista;
		this.boton = boton;
	}



	@Override
	public void handle(MouseEvent event) {
		try {
			juego.jugadorActualAtacarAEnemigo(personaje);
			boton.parpadear(Color.RED);
			PauseTransition pausa = new PauseTransition(Duration.seconds(1));
			pausa.setOnFinished(finPausa -> {
				vista.actualizarVista();
			});
			pausa.play();
		} catch (AtaqueNoPosible error) {
			acciones.setText("No puede atacar: " + error.getMensaje());
			boton.reproducirSonidoError();
		}
	}

}
