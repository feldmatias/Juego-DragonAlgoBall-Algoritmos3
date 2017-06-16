package vista.controlador;



import java.util.Map;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import modelo.excepciones.TransformacionNoPosible;
import modelo.juego.DragonBall;
import modelo.personajes.Personaje;
import vista.BotonInvisible;
import vista.VistaJuego;

public class BotonTransformarEventHandler implements EventHandler<ActionEvent> {

	private DragonBall juego;
	private VistaJuego vista;
	private Label labelAcciones;
	private Map<Personaje, BotonInvisible> botonesPersonajes;
	
	public BotonTransformarEventHandler(DragonBall juego, VistaJuego vista, Label labelAcciones, Map<Personaje, BotonInvisible> botonesPersonajes) {
		this.juego = juego;
		this.vista = vista;
		this.labelAcciones = labelAcciones;
		this.botonesPersonajes = botonesPersonajes;
	}

	@Override
	public void handle(ActionEvent event) {
		BotonInvisible boton = botonesPersonajes.get(juego.getJugadorActual().getPersonajeSeleccionado());
		try {
			juego.jugadorActualTransformar();
			boton.titilar(Color.BLUE);
			PauseTransition pausa = new PauseTransition(Duration.seconds(0.7));
			pausa.setOnFinished(finPausa -> {
				vista.actualizarVista();
			});
			pausa.play();
		} catch (TransformacionNoPosible error) {
			labelAcciones.setText("El personaje no puede Transformarse: " + error.getMensaje());
			boton.lanzarSonidoError();
		}
	}

}
