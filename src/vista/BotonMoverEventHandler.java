package vista;

import java.util.List;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import modelo.personajes.Personaje;

public class BotonMoverEventHandler implements EventHandler<ActionEvent> {

	private Label labelAcciones;
	private Map<Personaje, Button> personajes;
	private List<Button> casilleros;

	public BotonMoverEventHandler(Label labelAcciones, List<Button> botonesCasilleros, Map<Personaje, Button> botonesPersonajes) {
		this.labelAcciones = labelAcciones;
		this.personajes = botonesPersonajes;
		this.casilleros = botonesCasilleros;
	}

	@Override
	public void handle(ActionEvent event) {
		for (Button boton: personajes.values()){
			boton.setDisable(true);
		}
		for (Button boton: casilleros){
			boton.setDisable(false);
		}
		labelAcciones.setText("Seleccione una nueva Posicion");
	}

}
