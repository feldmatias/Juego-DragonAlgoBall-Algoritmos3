package modelo.juego;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.excepciones.AtaqueNoPosible;
import modelo.excepciones.EquipoNoDisponible;
import modelo.excepciones.MovimientoNoPosible;
import modelo.excepciones.PersonajeNoSeleccionable;
import modelo.excepciones.TransformacionNoPosible;
import modelo.personajes.Cell;
import modelo.personajes.Freezer;
import modelo.personajes.Gohan;
import modelo.personajes.Goku;
import modelo.personajes.MajinBoo;
import modelo.personajes.Personaje;
import modelo.personajes.Piccolo;
import modelo.utilidades.Constantes;

public class DragonBall {
	
	private Tablero tablero;
	private Jugador jugador1;
	private Jugador jugador2;
	private Map<String,Equipo> equipos;
	private Turno turno;
	private String ganador;

	public DragonBall () {
		
		this.tablero = new Tablero(Constantes.SIZE_TABLERO);
		this.equipos = new HashMap<String, Equipo>();
		this.crearEquipoGuerreros();
		this.crearEquipoEnemigos();
		
	}
	
	public void iniciar(){
		this.iniciarTurno();
	}
	
	private void iniciarTurno(){
		List<Jugador> lista = new ArrayList<Jugador>();
		lista.add(this.jugador1);
		lista.add(this.jugador2);
		Collections.shuffle(lista);
		this.turno = new Turno (lista.get(0), lista.get(1), this.tablero);
	}
	
	private Equipo crearEquipo(String nombreEquipo, Personaje pers1, Personaje pers2, Personaje pers3){
		List<Personaje> listaPersonajes = new ArrayList<Personaje>();
		listaPersonajes.add(pers1);
		listaPersonajes.add(pers2);
		listaPersonajes.add(pers3);
		Equipo equipo = new Equipo( nombreEquipo, listaPersonajes);
		this.equipos.put(nombreEquipo, equipo);
		return equipo;
	}
	
	private Equipo crearEquipoGuerreros()  {
		Personaje goku = new Goku(this.tablero);
		Personaje gohan = new Gohan(this.tablero);
		Personaje piccolo = new Piccolo(this.tablero);		
		return this.crearEquipo(Constantes.GUERREROS, goku, gohan, piccolo);
	}
	
	private Equipo crearEquipoEnemigos()  {
		Personaje cell = new Cell(this.tablero);
		Personaje freezer = new Freezer(this.tablero);
		Personaje majinBoo = new MajinBoo(this.tablero);		
		return this.crearEquipo(Constantes.ENEMIGOS, cell, freezer, majinBoo);
	}



	private void establecerEquipo(Jugador jugador,String nombreEquipo,Posicion posInicial){
		if(! this.equipoEstaDisponible(nombreEquipo) ){
			throw new EquipoNoDisponible();
		}
		Equipo equipoElegido = this.equipos.get(nombreEquipo);
		jugador.setEquipo(equipoElegido);
		this.equipos.remove(nombreEquipo);
		this.tablero.posicionarPersonajes(equipoElegido, posInicial);
	}
	
	public void establecerEquipoJugador1(String nombreEquipo){
		this.jugador1 = new Jugador();
		this.establecerEquipo(jugador1, nombreEquipo, Constantes.POS_INICIAL1);
	}
	
	public void establecerEquipoJugador2(String nombreEquipo){
		this.jugador2 = new Jugador();
		this.establecerEquipo(jugador2, nombreEquipo,Constantes.POS_INICIAL2);
	}
	
	
	public boolean equipoEstaDisponible(String unNombre) {
		return this.equipos.containsKey(unNombre);
		
	}

	public Tablero getTablero(){
		return this.tablero;
	}
	
	public Jugador getJugador1(){
		return this.jugador1;
	}
	
	public Jugador getJugador2(){
		return this.jugador2;
	}

	public Jugador getJugadorActual(){
		return this.turno.jugadorActual();
	}
	
	public void jugadorActualAtacarAEnemigo(Personaje enemigo) throws AtaqueNoPosible{
		this.turno.atacarEnemigo(enemigo);
	}
	
	public void jugadorActualRealizarAtaqueEspecial(Personaje enemigo) throws AtaqueNoPosible{
		this.turno.realizarAtaqueEspecial(enemigo);
	}
	
	public void jugadorActualMoverAPosicion(Posicion destino) throws MovimientoNoPosible{
		this.turno.moverPersonaje(destino);
	}
	
	public void jugadorActualTransformar() throws TransformacionNoPosible{
		this.turno.transformar();
	}
	
	public void jugadorActualSeleccionarPersonaje(Personaje personaje) throws PersonajeNoSeleccionable{
		this.turno.seleccionarPersonaje(personaje);
	}
	
	public Personaje jugadorActualgetPersonajeSeleccionado(){
		return this.turno.jugadorActual().getPersonajeSeleccionado();
	}
	
	public void jugadorActualTerminarTurno(){
		this.turno.terminarTurno();
	}
	
	public boolean estaTerminado(){
		if (jugador1.equipoMuerto()){
			ganador = jugador2.getEquipo().getNombre() +  Constantes.GanadorPorDerrotarEnemigo;
			return true;
		}
		if (jugador2.equipoMuerto()){
			ganador = jugador1.getEquipo().getNombre() + Constantes.GanadorPorDerrotarEnemigo;
			return true;
		}
		if (jugador1.coleccionDeEsferasCompleta()){
			ganador = jugador1.getEquipo().getNombre() +  Constantes.GanadorPorEncontrarSieteEsferas;
			return true;
		}
		if (jugador2.coleccionDeEsferasCompleta()){
			ganador = jugador2.getEquipo().getNombre() + Constantes.GanadorPorEncontrarSieteEsferas;
			return true;
		}
		return false;
	}
	
	public String getGanador(){
		return ganador;
	}

}
