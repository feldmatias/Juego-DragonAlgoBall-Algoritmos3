package vista.controlador;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import modelo.excepciones.MovimientoNoPosible;
import modelo.juego.DragonBall;
import modelo.juego.Posicion;
import vista.BotonInvisible;
import vista.VistaJuego;

public class BotonCasilleroVacioEventHandler implements EventHandler<MouseEvent>{

	private DragonBall juego;
	private Posicion pos;
	private Label acciones;
	private VistaJuego vista;
	private BotonInvisible boton;
	
	public BotonCasilleroVacioEventHandler(DragonBall juego, Posicion pos, Label acciones, VistaJuego vista, BotonInvisible boton) {
		this.juego = juego;
		this.pos = pos;
		this.acciones = acciones;
		this.vista = vista;
		this.boton = boton;
	}



	@Override
	public void handle(MouseEvent event) {
		try {
			juego.jugadorActualMoverAPosicion(pos);
			vista.actualizarVista();
			this.boton.lanzarSonidoMovimiento();
		} catch (MovimientoNoPosible error){
			this.boton.lanzarSonidoError();
			acciones.setText("No puede moverse: " + error.getMensaje());
		}
	}

}
