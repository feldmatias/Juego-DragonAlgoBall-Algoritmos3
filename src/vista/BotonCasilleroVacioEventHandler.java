package vista;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import modelo.excepciones.MovimientoNoPosible;
import modelo.excepciones.MovimientoYaRealizado;
import modelo.juego.DragonBall;
import modelo.juego.Posicion;

public class BotonCasilleroVacioEventHandler implements EventHandler<ActionEvent>{

	private DragonBall juego;
	private Posicion pos;
	private Label acciones;
	private Vista vista;
	
	public BotonCasilleroVacioEventHandler(DragonBall juego, Posicion pos, Label acciones, Vista vista) {
		this.juego = juego;
		this.pos = pos;
		this.acciones = acciones;
		this.vista = vista;
	}

	@Override
	public void handle(ActionEvent arg0) {
		try {
			juego.jugadorActualMoverAPosicion(pos);
			vista.actualizarVista();
		} catch (MovimientoYaRealizado | MovimientoNoPosible e) {
			acciones.setText("No puede moverse ahi");
		}
	}

}
