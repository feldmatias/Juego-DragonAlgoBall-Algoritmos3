package vista.controlador;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import modelo.excepciones.TransformacionNoPosible;
import modelo.juego.DragonBall;
import vista.LibreriaSonidos;
import vista.VistaJuego;

public class BotonTransformarEventHandler implements EventHandler<ActionEvent> {

	private DragonBall juego;
	private VistaJuego vista;
	private Label labelAcciones;
	private LibreriaSonidos sonidos;
	
	public BotonTransformarEventHandler(DragonBall juego, VistaJuego vista, Label labelAcciones, LibreriaSonidos sonidos) {
		this.juego = juego;
		this.vista = vista;
		this.labelAcciones = labelAcciones;
		this.sonidos = sonidos;
	}

	@Override
	public void handle(ActionEvent event) {
		try {
			juego.jugadorActualTransformar();
			vista.actualizarVista();
		} catch (TransformacionNoPosible error) {
			labelAcciones.setText("El personaje no puede Transformarse: " + error.getMensaje());
			sonidos.getSonidoError().play();
		}
	}

}
