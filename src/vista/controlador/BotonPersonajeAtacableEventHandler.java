package vista.controlador;


import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import modelo.excepciones.AtaqueNoPosible;
import modelo.excepciones.AtaqueYaRealizado;
import modelo.juego.DragonBall;
import modelo.personajes.Personaje;
import vista.VistaJuego;

public class BotonPersonajeAtacableEventHandler implements EventHandler<MouseEvent> {

	private DragonBall juego;
	private Personaje personaje;
	private Label acciones;
	private VistaJuego vista;

	public BotonPersonajeAtacableEventHandler(DragonBall juego, Personaje personaje, Label labelAcciones, VistaJuego vista) {
		this.juego = juego;
		this.personaje = personaje;
		this.acciones = labelAcciones;
		this.vista = vista;
	}



	@Override
	public void handle(MouseEvent event) {
		try {
			juego.jugadorActualAtacarAEnemigo(personaje);
			vista.actualizarVista();
		} catch (AtaqueYaRealizado | AtaqueNoPosible e) {
			acciones.setText("No puede atacar a ese personaje");
		}
	}

}
