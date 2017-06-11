package vista;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import modelo.excepciones.TransformacionNoPosible;
import modelo.juego.DragonBall;

public class BotonTransformarEventHandler implements EventHandler<ActionEvent> {

	private DragonBall juego;
	private Vista vista;
	private Label labelAcciones;
	
	public BotonTransformarEventHandler(DragonBall juego, Vista vista, Label labelAcciones) {
		this.juego = juego;
		this.vista = vista;
		this.labelAcciones = labelAcciones;
	}

	@Override
	public void handle(ActionEvent event) {
		try {
			juego.jugadorActualTransformar();
			vista.actualizarVista();
		} catch (TransformacionNoPosible e) {
			labelAcciones.setText("El personaje no puede Transformarse");
		}
		
	}

}
