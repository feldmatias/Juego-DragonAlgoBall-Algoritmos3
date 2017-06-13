package vista.controlador;

import java.util.List;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import modelo.personajes.Personaje;
import vista.BotonInvisible;

public class BotonMoverEventHandler implements EventHandler<ActionEvent> {

	private Label labelAcciones;
	private Map<Personaje, BotonInvisible> personajes;
	private List<BotonInvisible> casilleros;

	public BotonMoverEventHandler(Label labelAcciones, List<BotonInvisible> botonesCasilleros, 
			Map<Personaje, BotonInvisible> botonesPersonajes) {
		this.labelAcciones = labelAcciones;
		this.personajes = botonesPersonajes;
		this.casilleros = botonesCasilleros;
	}


	@Override
	public void handle(ActionEvent event) {
		for (BotonInvisible boton: personajes.values()){
			boton.deshabilitar();
		}
		for (BotonInvisible boton: casilleros){
			boton.habilitar();
		}
		labelAcciones.setText("Seleccione una nueva Posicion");
	}

}
