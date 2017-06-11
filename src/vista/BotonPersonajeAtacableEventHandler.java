package vista;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import modelo.excepciones.AtaqueNoPosible;
import modelo.excepciones.AtaqueYaRealizado;
import modelo.juego.DragonBall;
import modelo.personajes.Personaje;

public class BotonPersonajeAtacableEventHandler implements EventHandler<ActionEvent> {

	private DragonBall juego;
	private Personaje personaje;
	private Label acciones;
	private Vista vista;

	public BotonPersonajeAtacableEventHandler(DragonBall juego, Personaje personaje, Label labelAcciones, Vista vista) {
		this.juego = juego;
		this.personaje = personaje;
		this.acciones = labelAcciones;
		this.vista = vista;
	}

	@Override
	public void handle(ActionEvent event) {
		try {
			juego.jugadorActualAtacarAEnemigo(personaje);
			vista.actualizarVista();
		} catch (AtaqueYaRealizado | AtaqueNoPosible e) {
			acciones.setText("No puede atacar a ese personaje");
		}

	}

}
