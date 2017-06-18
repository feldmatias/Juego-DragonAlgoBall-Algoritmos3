package controlador.eventosBotonesJuego;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import modelo.consumibles.Efecto;
import modelo.excepciones.MovimientoNoPosible;
import modelo.juego.Casillero;
import modelo.juego.DragonBall;
import vista.VistaJuego;
import vista.botones.BotonInvisible;

public class BotonCasilleroVacioEventHandler implements EventHandler<MouseEvent>{

	private DragonBall juego;
	private Casillero casillero;
	private Text acciones;
	private VistaJuego vista;
	private BotonInvisible boton;
	
	public BotonCasilleroVacioEventHandler(DragonBall juego, Casillero casillero, Text informacionAcciones, VistaJuego vista, BotonInvisible boton) {
		this.juego = juego;
		this.casillero = casillero;
		this.acciones = informacionAcciones;
		this.vista = vista;
		this.boton = boton;
	}



	@Override
	public void handle(MouseEvent event) {
		try {
			Efecto consumible = casillero.getConsumible();
			juego.jugadorActualMoverAPosicion(casillero.getPosicion());
			if (consumible != null){
				boton.reproducirSonidoAgarrarConsumible();
			}
			vista.actualizarVista();
			this.boton.reproducirSonidoMovimiento();
		} catch (MovimientoNoPosible error){
			this.boton.reproducirSonidoError();
			acciones.setText("No puede moverse: " + error.getMensaje());
		}
	}

}
