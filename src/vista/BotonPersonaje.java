package vista;

import modelo.juego.DragonBall;
import modelo.personajes.Personaje;

public class BotonPersonaje extends BotonInvisible {

	private Personaje personaje;
	private DragonBall juego;
	
	public BotonPersonaje(Personaje personaje, DragonBall juego) {
		super(new ImagenFondo(personaje));
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


}
