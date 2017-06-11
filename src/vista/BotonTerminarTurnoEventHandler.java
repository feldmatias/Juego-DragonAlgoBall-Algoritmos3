package vista;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import modelo.juego.DragonBall;

public class BotonTerminarTurnoEventHandler implements EventHandler<ActionEvent> {

	private DragonBall juego;
	private Vista vista;
	
	public BotonTerminarTurnoEventHandler(DragonBall juego, Vista vista) {
		this.juego = juego;
		this.vista = vista;
	}

	@Override
	public void handle(ActionEvent event) {
		juego.jugadorActualTerminarTurno();
		vista.actualizarVista();
	}

}
