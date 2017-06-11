package vista;

import java.util.List;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import modelo.juego.DragonBall;
import modelo.personajes.Personaje;

public class BotonAtaqueEspecialEventHandler implements EventHandler<ActionEvent> {

	private Label labelAcciones;
	private List<Button> casilleros;
	private Map<Personaje, Button> personajes;
	private DragonBall juego;
	private Vista vista;

	public BotonAtaqueEspecialEventHandler(Label labelAcciones, List<Button> botonesCasilleros,
			Map<Personaje, Button> botonesPersonajes, DragonBall juego, Vista vista) {

		this.labelAcciones = labelAcciones;
		this.casilleros = botonesCasilleros;
		this.personajes = botonesPersonajes;
		this.juego = juego;
		this.vista = vista;
	}

	@Override
	public void handle(ActionEvent event) {
		for (Button boton: casilleros){
			boton.setDisable(true);
		}
		for (Personaje personaje: personajes.keySet()){
			Button boton = personajes.get(personaje);
			boton.setDisable(false);
			boton.setOnAction(new BotonPersonajeAtacableEspecialEventHandler(juego,personaje, labelAcciones, vista));
		}
		
	}

}
