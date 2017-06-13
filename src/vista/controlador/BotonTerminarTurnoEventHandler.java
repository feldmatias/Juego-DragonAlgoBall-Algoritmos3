package vista.controlador;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import modelo.juego.DragonBall;
import vista.VistaJuego;

public class BotonTerminarTurnoEventHandler implements EventHandler<ActionEvent> {

	private DragonBall juego;
	private VistaJuego vista;
	
	public BotonTerminarTurnoEventHandler(DragonBall juego, VistaJuego vista) {
		this.juego = juego;
		this.vista = vista;
	}


	@Override
	public void handle(ActionEvent event) {
		juego.jugadorActualTerminarTurno();
		vista.actualizarVista();
	}

}
