package vista.controlador;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import modelo.excepciones.PersonajeNoSeleccionable;
import modelo.juego.DragonBall;
import modelo.personajes.Personaje;
import vista.VistaJuego;

public class BotonCasilleroOcupadoEventHandler implements EventHandler<MouseEvent> {

	private DragonBall juego;
	private Personaje personaje;
	private Label label;
	private Label labelAcciones;
	private VistaJuego vista;
	
	public BotonCasilleroOcupadoEventHandler(DragonBall juego,  Personaje personaje, Label labelPersonajeSeleccionado, Label acciones, VistaJuego vista) {
		this.juego = juego;
		this.personaje = personaje;
		this.label = labelPersonajeSeleccionado;
		this.labelAcciones = acciones;
		this.vista = vista;
	}



	@Override
	public void handle(MouseEvent event) {
		try {
			juego.jugadorActualSeleccionarPersonaje(personaje);
			label.setText("Personaje Seleccionado: " + personaje.getNombre());
			vista.actualizarVista();
		} catch (PersonajeNoSeleccionable e) {
			labelAcciones.setText("No puede seleccionar a ese personaje");
		}
	}

}
