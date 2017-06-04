package modelo.juego;

import modelo.excepciones.AtaqueNoPosible;
import modelo.excepciones.MovimientoNoPosible;
import modelo.excepciones.PersonajeNoPerteneceAEquipo;
import modelo.personajes.Personaje;
import modelo.tablero.Posicion;

public class Jugador {
	private Equipo equipo;
	private Personaje personajeSeleccionado;
	
	public void setEquipo (Equipo equipo) {
		this.equipo = equipo;
		this.personajeSeleccionado = equipo.getMiembros().get(0);
	}

	public Equipo getEquipo() {
		return this.equipo;
	}

	public void atacar(Personaje enemigo) throws AtaqueNoPosible {
		personajeSeleccionado.atacarAPersonaje(enemigo);
	}

	public void mover(Posicion destino) throws MovimientoNoPosible {
		personajeSeleccionado.mover(destino);
	}
	
	public void seleccionarPersonaje(Personaje personaje){
		if (! this.equipo.pertenece(personaje)){
			throw new PersonajeNoPerteneceAEquipo();
		}
		this.personajeSeleccionado = personaje;
	}
}
