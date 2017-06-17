package vista.controlador.eventosAccionesJuego;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import vista.VistaJuego;

public class BotonCancelarAccionEventHandler implements EventHandler<MouseEvent> {

	private VistaJuego vista;
	
	public BotonCancelarAccionEventHandler(VistaJuego vista) {
		this.vista = vista;
	}


	@Override
	public void handle(MouseEvent event) {
		vista.actualizarVista();
	}

}
