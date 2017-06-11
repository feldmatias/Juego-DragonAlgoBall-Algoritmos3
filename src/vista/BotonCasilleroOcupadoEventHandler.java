package vista;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import modelo.excepciones.PersonajeNoSeleccionable;
import modelo.juego.DragonBall;
import modelo.personajes.Personaje;

public class BotonCasilleroOcupadoEventHandler implements EventHandler<ActionEvent> {

	private DragonBall juego;
	private Personaje personaje;
	private Label label;
	private Label labelAcciones;
	
	public BotonCasilleroOcupadoEventHandler(DragonBall juego,  Personaje personaje, Label labelPersonajeSeleccionado, Label acciones) {
		this.juego = juego;
		this.personaje = personaje;
		this.label = labelPersonajeSeleccionado;
		this.labelAcciones = acciones;
	}

	@Override
	public void handle(ActionEvent event) {
		try {
			juego.jugadorActualSeleccionarPersonaje(personaje);
			label.setText("Personaje Seleccionado: " + personaje.getNombre());
		} catch (PersonajeNoSeleccionable e) {
			labelAcciones.setText("No puede seleccionar a ese personaje");
		}
		
	}

}
