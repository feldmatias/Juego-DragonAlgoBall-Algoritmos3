package vista;

import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import modelo.juego.DragonBall;
import modelo.personajes.Personaje;

public class BotonPersonaje extends BotonInvisible {

	private Personaje personaje;
	private DragonBall juego;
	private static boolean redimensionable = true;
	
	public BotonPersonaje(Personaje personaje, DragonBall juego,MediaPlayer sonidoError) {
		super(new ImagenFondo(personaje, redimensionable), sonidoError);
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
