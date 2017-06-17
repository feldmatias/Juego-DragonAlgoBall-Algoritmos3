package vista.controlador.eventosBotonesJuego;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import modelo.excepciones.PersonajeNoSeleccionable;
import modelo.juego.DragonBall;
import modelo.personajes.Personaje;
import vista.VistaJuego;
import vista.botones.BotonInvisible;

public class BotonCasilleroOcupadoEventHandler implements EventHandler<MouseEvent> {

	private DragonBall juego;
	private Personaje personaje;
	private Label labelAcciones;
	private VistaJuego vista;
	private BotonInvisible boton;
	
	public BotonCasilleroOcupadoEventHandler(DragonBall juego,  Personaje personaje, Label acciones, VistaJuego vista, BotonInvisible boton) {
		this.juego = juego;
		this.personaje = personaje;
		this.labelAcciones = acciones;
		this.vista = vista;
		this.boton = boton;
	}



	@Override
	public void handle(MouseEvent event) {
		try {
			juego.jugadorActualSeleccionarPersonaje(personaje);
			vista.actualizarVista();
		} catch (PersonajeNoSeleccionable e) {
			labelAcciones.setText("No puede seleccionar a ese personaje");
			boton.reproducirSonidoError();
		}
	}

}
