package vista.botones;

import javafx.scene.paint.Color;
import modelo.juego.DragonBall;
import modelo.personajes.Personaje;
import vista.ConstantesPantalla;
import vista.LibreriaSonidos;

public class BotonPersonaje extends BotonInvisible {

	private Personaje personaje;
	private DragonBall juego;
	
	public BotonPersonaje(Personaje personaje, DragonBall juego,LibreriaSonidos sonidos) {
		super(new ImagenFondo(personaje, ConstantesPantalla.anchoPersonaje, ConstantesPantalla.altoPersonaje), sonidos);
		this.personaje = personaje;
		this.juego = juego;
		this.comprobarPersonajeSeleccionado();
	}

	private void comprobarPersonajeSeleccionado() {
		Personaje seleccionado = juego.getJugadorActual().getPersonajeSeleccionado();
		if (personaje == seleccionado){
			this.seleccionar();
		}
		
	}

	@Override
	protected Color getColorMouseEntered() {
		return Color.LIGHTBLUE;
	}

}
