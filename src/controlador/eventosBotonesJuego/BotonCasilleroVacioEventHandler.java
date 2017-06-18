package controlador.eventosBotonesJuego;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import modelo.excepciones.MovimientoNoPosible;
import modelo.juego.DragonBall;
import modelo.juego.Posicion;
import vista.VistaJuego;
import vista.botones.BotonInvisible;

public class BotonCasilleroVacioEventHandler implements EventHandler<MouseEvent>{

	private DragonBall juego;
	private Posicion pos;
	private Text acciones;
	private VistaJuego vista;
	private BotonInvisible boton;
	
	public BotonCasilleroVacioEventHandler(DragonBall juego, Posicion pos, Text informacionAcciones, VistaJuego vista, BotonInvisible boton) {
		this.juego = juego;
		this.pos = pos;
		this.acciones = informacionAcciones;
		this.vista = vista;
		this.boton = boton;
	}



	@Override
	public void handle(MouseEvent event) {
		try {
			juego.jugadorActualMoverAPosicion(pos);
			vista.actualizarVista();
			this.boton.reproducirSonidoMovimiento();
		} catch (MovimientoNoPosible error){
			this.boton.reproducirSonidoError();
			acciones.setText("No puede moverse: " + error.getMensaje());
		}
	}

}
