package controlador.eventosAccionesJuego;

import java.util.List;
import java.util.Map;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import modelo.personajes.Personaje;
import vista.botones.BotonInvisible;

public class BotonMoverEventHandler implements EventHandler<MouseEvent> {

	private Text acciones;
	private Map<Personaje, BotonInvisible> personajes;
	private List<BotonInvisible> casilleros;

	public BotonMoverEventHandler(Text informacionAcciones, List<BotonInvisible> botonesCasilleros, 
			Map<Personaje, BotonInvisible> botonesPersonajes) {
		this.acciones = informacionAcciones;
		this.personajes = botonesPersonajes;
		this.casilleros = botonesCasilleros;
	}


	@Override
	public void handle(MouseEvent event) {
		for (BotonInvisible boton: personajes.values()){
			boton.deshabilitar();
		}
		for (BotonInvisible boton: casilleros){
			boton.habilitar();
		}
		acciones.setText("Seleccione una nueva Posicion");
	}

}
