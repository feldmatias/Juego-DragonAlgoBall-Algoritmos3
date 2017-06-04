package modelo.juego;

import java.util.LinkedList;
import java.util.Queue;

import modelo.excepciones.AtaqueNoPosible;
import modelo.excepciones.AtaqueYaRealizado;
import modelo.excepciones.MovimientoNoPosible;
import modelo.excepciones.MovimientoYaRealizado;
import modelo.excepciones.PersonajeNoPerteneceAEquipo;
import modelo.excepciones.PersonajeNoSeleccionable;
import modelo.personajes.Personaje;
import modelo.tablero.Posicion;


public class Turno {
	
	private Queue<Jugador> jugadores;
	private boolean movimientoRealizado = false;
	private boolean ataqueRealizado = false;
	
	public Turno(Jugador jugadorInicial, Jugador otroJugador){
		this.jugadores = new LinkedList<Jugador>();
		jugadores.add(jugadorInicial);
		jugadores.add(otroJugador);
		this.empezarTurno();
	}
	

	private void empezarTurno(){
		this.jugadorActual().getEquipo().empezarTurno();	
		this.movimientoRealizado = false;
		this.ataqueRealizado = false;	
	}
	
	public void atacarEnemigo(Personaje enemigo) throws AtaqueYaRealizado, AtaqueNoPosible{
		if (ataqueRealizado){
			throw new AtaqueYaRealizado();
		}
		this.jugadorActual().atacar(enemigo);
		this.ataqueRealizado = true;
		this.terminarTurno();
	}
	
	public void moverPersonaje(Posicion destino) throws MovimientoYaRealizado, MovimientoNoPosible{
		if (movimientoRealizado){
			throw new MovimientoYaRealizado();
		}
		this.jugadorActual().mover(destino);
		this.movimientoRealizado = true;
		this.terminarTurno();
	}
	
	private void terminarTurno() {
		if (! (this.movimientoRealizado && this.ataqueRealizado)){
			return;
		}
		jugadores.add(jugadores.remove()); // Cambia el jugador actual
		this.empezarTurno();
	}
	
	public Jugador jugadorActual(){
		return jugadores.element();
	}
	
	public void seleccionarPersonaje (Personaje personaje) throws PersonajeNoSeleccionable{
		try{
			this.jugadorActual().seleccionarPersonaje(personaje);
		} catch (PersonajeNoPerteneceAEquipo e){
			throw new PersonajeNoSeleccionable();
		}
	}
}