package modelo.juego;

import java.util.function.Function;

import modelo.excepciones.AtaqueNoPosible;
import modelo.excepciones.MovimientoNoPosible;
import modelo.excepciones.PersonajeNoPerteneceAEquipo;
import modelo.excepciones.TransformacionNoPosible;
import modelo.personajes.Personaje;
import modelo.tablero.Posicion;

public class Jugador {
	private Equipo equipo;
	private Personaje personajeSeleccionado;
	
	public void setEquipo (Equipo equipo) {
		this.equipo = equipo;
		this.seleccionarCualquierPersonaje();
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
	
	private void seleccionarCualquierPersonaje(){
		this.seleccionarPersonaje(this.equipo.getMiembros().get(0));
	}
	
	public void comprobarPersonajeSeleccionado(){
		if (! this.equipo.pertenece(this.personajeSeleccionado)){
			this.seleccionarCualquierPersonaje();
		}
	}

	public void empezarTurno() {
		this.equipo.empezarTurno();
	}

	public void transformarPersonaje() throws TransformacionNoPosible {
		this.personajeSeleccionado.transformar();
	}

	public void realizarAtaqueEspecial(Personaje enemigo) throws AtaqueNoPosible {
		this.personajeSeleccionado.realizarAtaqueEspecial(enemigo);
	}
}
