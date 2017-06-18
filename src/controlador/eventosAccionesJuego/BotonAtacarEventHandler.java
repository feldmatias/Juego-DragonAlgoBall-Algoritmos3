package controlador.eventosAccionesJuego;

import java.util.List;
import java.util.Map;

import controlador.eventosBotonesJuego.BotonPersonajeAtacableEventHandler;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import modelo.juego.DragonBall;
import modelo.personajes.Personaje;
import vista.VistaJuego;
import vista.botones.BotonInvisible;

public class BotonAtacarEventHandler implements EventHandler<MouseEvent> {

	private Text acciones;
	private Map<Personaje, BotonInvisible> personajes;
	private List<BotonInvisible> casilleros;
	private DragonBall juego;
	private VistaJuego vista;

	public BotonAtacarEventHandler(Text informacionAcciones, List<BotonInvisible> botonesCasilleros,
			Map<Personaje, BotonInvisible> botonesPersonajes, DragonBall juego, VistaJuego vista) {
		
		this.acciones = informacionAcciones;
		this.casilleros = botonesCasilleros;
		this.personajes = botonesPersonajes;
		this.juego = juego;
		this.vista = vista;
	}




	@Override
	public void handle(MouseEvent event) {
		for (BotonInvisible boton: casilleros){
			boton.deshabilitar();
		}
		for (Personaje personaje: personajes.keySet()){
			BotonInvisible boton = personajes.get(personaje);
			boton.habilitar();
			boton.setOnAction(new BotonPersonajeAtacableEventHandler(juego,personaje, acciones, vista, boton));
		}
		acciones.setText("Seleccione al enemigo a atacar");
	}

}
