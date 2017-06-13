package vista.controlador;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import modelo.excepciones.MovimientoNoPosible;
import modelo.excepciones.MovimientoYaRealizado;
import modelo.juego.DragonBall;
import modelo.juego.Posicion;
import vista.VistaJuego;

public class BotonCasilleroVacioEventHandler implements EventHandler<MouseEvent>{

	private DragonBall juego;
	private Posicion pos;
	private Label acciones;
	private VistaJuego vista;
	
	public BotonCasilleroVacioEventHandler(DragonBall juego, Posicion pos, Label acciones, VistaJuego vista) {
		this.juego = juego;
		this.pos = pos;
		this.acciones = acciones;
		this.vista = vista;
	}



	@Override
	public void handle(MouseEvent event) {
		try {
			juego.jugadorActualMoverAPosicion(pos);
			vista.actualizarVista();
		} catch (MovimientoYaRealizado | MovimientoNoPosible e) {
			acciones.setText("No puede moverse ahi" + String.valueOf(pos.getX()) +","+String.valueOf(pos.getY()));
		}
	}

}
