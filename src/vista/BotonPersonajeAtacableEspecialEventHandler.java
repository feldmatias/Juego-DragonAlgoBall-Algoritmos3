package vista;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import modelo.excepciones.AtaqueNoPosible;
import modelo.excepciones.AtaqueYaRealizado;
import modelo.juego.DragonBall;
import modelo.personajes.Personaje;

public class BotonPersonajeAtacableEspecialEventHandler implements EventHandler<ActionEvent> {

	private DragonBall juego;
	private Label acciones;
	private Personaje personaje;
	private Vista vista;

	public BotonPersonajeAtacableEspecialEventHandler(DragonBall juego, Personaje personaje, Label labelAcciones,
			Vista vista) {
		this.juego = juego;
		this.personaje = personaje;
		this.acciones = labelAcciones;
		this.vista = vista;
	}

	@Override
	public void handle(ActionEvent event) {
		try {
			juego.jugadorActualRealizarAtaqueEspecial(personaje);
			vista.actualizarVista();
		} catch (AtaqueYaRealizado | AtaqueNoPosible e) {
			acciones.setText("No puede atacar a ese personaje");
		}

	}

}
