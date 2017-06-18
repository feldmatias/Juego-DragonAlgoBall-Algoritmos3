package controlador.eventosBotonesJuego;


import javafx.animation.PauseTransition;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import modelo.excepciones.AtaqueNoPosible;
import modelo.juego.DragonBall;
import modelo.personajes.Personaje;
import vista.VistaJuego;
import vista.botones.BotonInvisible;

public class BotonPersonajeAtacableEspecialEventHandler implements EventHandler<MouseEvent> {

	private DragonBall juego;
	private Label acciones;
	private Personaje personaje;
	private VistaJuego vista;
	private BotonInvisible boton;

	public BotonPersonajeAtacableEspecialEventHandler(DragonBall juego, Personaje personaje, Label labelAcciones,
			VistaJuego vista, BotonInvisible boton) {
		this.juego = juego;
		this.personaje = personaje;
		this.acciones = labelAcciones;
		this.vista = vista;
		this.boton = boton;
	}



	@Override
	public void handle(MouseEvent event) {
		try {
			juego.jugadorActualRealizarAtaqueEspecial(personaje);
			boton.reproducirSonidoAtaqueEspecial();
			boton.parpadear(Color.RED);
			PauseTransition pausa = new PauseTransition(Duration.seconds(1));
			pausa.setOnFinished(finPausa -> {
				vista.actualizarVista();
			});
			pausa.play();
		} catch ( AtaqueNoPosible error) {
			acciones.setText("No puede realizar ataque especial: " + error.getMensaje());
			boton.reproducirSonidoError();
		}
	}

}
