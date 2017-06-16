package vista;

import javafx.scene.paint.Color;
import modelo.juego.DragonBall;
import modelo.personajes.Personaje;

public class BotonPersonaje extends BotonInvisible {

	private Personaje personaje;
	private DragonBall juego;
	public static double width = BotonInvisible.anchoBoton * 0.7;
	public static double height = BotonInvisible.altoBoton;
	
	public BotonPersonaje(Personaje personaje, DragonBall juego,LibreriaSonidos sonidos) {
		super(new ImagenFondo(personaje, width, height), sonidos);
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
