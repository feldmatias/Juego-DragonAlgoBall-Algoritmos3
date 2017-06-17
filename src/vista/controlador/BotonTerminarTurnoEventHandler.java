package vista.controlador;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import modelo.juego.DragonBall;
import vista.VistaJuego;

public class BotonTerminarTurnoEventHandler implements EventHandler<MouseEvent> {

	private DragonBall juego;
	private VistaJuego vista;
	
	public BotonTerminarTurnoEventHandler(DragonBall juego, VistaJuego vista) {
		this.juego = juego;
		this.vista = vista;
	}


	@Override
	public void handle(MouseEvent event) {
		juego.jugadorActualTerminarTurno();
		vista.actualizarVista();
	}

}
