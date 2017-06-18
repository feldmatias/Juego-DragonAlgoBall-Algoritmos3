package controlador.eventosAccionesJuego;

import java.util.Map;

import javafx.animation.PauseTransition;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import modelo.excepciones.TransformacionNoPosible;
import modelo.juego.DragonBall;
import modelo.personajes.Personaje;
import vista.VistaJuego;
import vista.botones.BotonInvisible;

public class BotonTransformarEventHandler implements EventHandler<MouseEvent> {

	private DragonBall juego;
	private VistaJuego vista;
	private Text acciones;
	private Map<Personaje, BotonInvisible> botonesPersonajes;
	
	public BotonTransformarEventHandler(DragonBall juego, VistaJuego vista, Text acciones, Map<Personaje, BotonInvisible> botonesPersonajes) {
		this.juego = juego;
		this.vista = vista;
		this.acciones = acciones;
		this.botonesPersonajes = botonesPersonajes;
	}

	@Override
	public void handle(MouseEvent event) {
		BotonInvisible boton = botonesPersonajes.get(juego.getJugadorActual().getPersonajeSeleccionado());
		try {
			juego.jugadorActualTransformar();
			boton.parpadear(Color.YELLOW);
			PauseTransition pausa = new PauseTransition(Duration.seconds(2));
			pausa.setOnFinished(finPausa -> {
				vista.actualizarVista();
			});
			pausa.play();
			boton.reproducirSonidoTransformacion();

		} catch (TransformacionNoPosible error) {
			acciones.setText("El personaje no puede Transformarse: " + error.getMensaje());
			boton.reproducirSonidoError();
		}
	}

}
