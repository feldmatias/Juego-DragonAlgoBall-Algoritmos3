package modelo.juego;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.excepciones.AtaqueNoPosible;
import modelo.excepciones.AtaqueYaRealizado;
import modelo.excepciones.EquipoNoDisponible;
import modelo.excepciones.MovimientoNoPosible;
import modelo.excepciones.MovimientoYaRealizado;
import modelo.excepciones.PersonajeNoSeleccionable;
import modelo.excepciones.PosicionFueraDeRango;
import modelo.excepciones.TransformacionNoPosible;
import modelo.personajes.Cell;
import modelo.personajes.Freezer;
import modelo.personajes.Gohan;
import modelo.personajes.Goku;
import modelo.personajes.MajinBoo;
import modelo.personajes.Personaje;
import modelo.personajes.Piccolo;
import modelo.tablero.Posicion;
import modelo.tablero.Tablero;
import modelo.utilidades.Constantes;

public class DragonBall {
	
	private Tablero tablero;
	private Jugador jugador1;
	private Jugador jugador2;
	private Map<String,Equipo> equipos;
	private Turno turno;

	public DragonBall () {
		
		this.tablero = new Tablero(Constantes.SIZE_TABLERO);
		this.equipos = new HashMap<String, Equipo>();
		//equipos.put(Constantes.GUERREROS, this.crearEquipoGuerreros());
		//equipos.put(Constantes.ENEMIGOS, this.crearEquipoEnemigos());
		this.crearEquipoGuerreros();
		this.crearEquipoEnemigos();
		this.jugador1 = new Jugador();
		this.jugador2 = new Jugador();
		
	}

//	private void asignarEquipoAJugador(Equipo equipo, Jugador jugador) {
//		jugador.setEquipo(equipo);
//		
//	}
	
//	private Equipo crearEquipoYPosicionarPersonajes(String nombreEquipo, Personaje pers1, Personaje pers2, Personaje pers3, Posicion posInicial) throws PosicionFueraDeRango{
//		List<Personaje> lista = new ArrayList<Personaje>();
//		lista.add(pers1);
//		lista.add(pers2);
//		lista.add(pers3);
//		tablero.posicionarPersonaje(pers1, posInicial);
//		tablero.posicionarPersonaje(pers2, posInicial.sumarPosicion(new Posicion(0,1)));
//		tablero.posicionarPersonaje(pers3, posInicial.sumarPosicion(new Posicion(0,-1)));
//		Equipo equipo = new Equipo (nombreEquipo, lista);
//		pers1.setEquipo(equipo);
//		pers2.setEquipo(equipo);
//		pers3.setEquipo(equipo);
//		return equipo;
//	}
	
	private Equipo crearEquipo(String nombreEquipo, Personaje pers1, Personaje pers2, Personaje pers3){
		List<Personaje> listaPersonajes = new ArrayList<Personaje>();
		listaPersonajes.add(pers1);
		listaPersonajes.add(pers2);
		listaPersonajes.add(pers3);
		Equipo equipo = new Equipo( nombreEquipo, listaPersonajes);
		this.equipos.put(nombreEquipo, equipo);
		return equipo;
	}
	
	private Equipo crearEquipoGuerreros() throws PosicionFueraDeRango {
		Personaje goku = new Goku(this.tablero);
		Personaje gohan = new Gohan(this.tablero);
		Personaje piccolo = new Piccolo(this.tablero);		
		return this.crearEquipo(Constantes.GUERREROS, goku, gohan, piccolo);
		//return this.crearEquipoYPosicionarPersonajes(Constantes.GUERREROS, goku, gohan, piccolo, new Posicion (0,Constantes.POS_CENTRAL));
	}
	
	private Equipo crearEquipoEnemigos() throws PosicionFueraDeRango {
		Personaje cell = new Cell(this.tablero);
		Personaje freezer = new Freezer(this.tablero);
		Personaje majinBoo = new MajinBoo(this.tablero);		
		return this.crearEquipo(Constantes.ENEMIGOS, cell, freezer, majinBoo);
		//return this.crearEquipoYPosicionarPersonajes(Constantes.ENEMIGOS, cell, freezer, majinBoo, new Posicion (Constantes.SIZE_TABLERO - 1, Constantes.POS_CENTRAL));
	}

//	public void elegirEquipos(String primerEquipo, String segundoEquipo) throws NombresDeEquipoIguales, EquipoInexistente {
//		//Excepciones
//		if(primerEquipo == segundoEquipo){
//			throw new NombresDeEquipoIguales();
//		}
//		
//		if (! this.existeEquipo(primerEquipo) || ! this.existeEquipo(segundoEquipo)){
//			throw new EquipoInexistente();
//		}
//		
//		this.asignarEquipoAJugador(this.equipos.get(primerEquipo), this.jugador1);
//		this.asignarEquipoAJugador(this.equipos.get(segundoEquipo), this.jugador2);
//		
//		this.iniciarTurno();
//	}

	
	//aca puse el iniciar turno porque no sabia donde ponerlo
	public void iniciar(){
		this.iniciarTurno();
	}

	private void establecerEquipo(Jugador jugador,String nombreEquipo,Posicion posInicial) throws EquipoNoDisponible{
		if(! this.equipoEstaDisponible(nombreEquipo) ){
			throw new EquipoNoDisponible();
		}
		Equipo equipoElegido = this.equipos.get(nombreEquipo);
		jugador.setEquipo(equipoElegido);
		List<Personaje> personajesEquipo = equipoElegido.getMiembros();
		this.equipos.remove(nombreEquipo);
		this.tablero.posicionarPersonajes(personajesEquipo, posInicial);
	}
	
	public void establecerEquipoJugador1(String nombreEquipo) throws EquipoNoDisponible{
		Jugador jugador = this.jugador1;
		this.establecerEquipo(jugador, nombreEquipo, Constantes.POS_INICIAL1);
	}
	
	public void establecerEquipoJugador2(String nombreEquipo) throws EquipoNoDisponible{
		Jugador jugador = this.jugador2;
		this.establecerEquipo(jugador, nombreEquipo,Constantes.POS_INICIAL2);
	}
	
	
	public boolean equipoEstaDisponible(String unNombre) {
		return this.equipos.containsKey(unNombre);
		
	}

	public Jugador getJugador1(){
		return this.jugador1;
	}
	
	public Jugador getJugador2(){
		return this.jugador2;
	}
	
	public Tablero getTablero(){
		return this.tablero;
	}
	
	private void iniciarTurno(){
		List<Jugador> lista = new ArrayList<Jugador>();
		lista.add(this.jugador1);
		lista.add(this.jugador2);
		Collections.shuffle(lista);
		this.turno = new Turno (lista.get(0), lista.get(1), this.tablero);
	}
	
	public Jugador getJugadorActual(){
		return this.turno.jugadorActual();
	}
	
	public void jugadorActualAtacarAEnemigo(Personaje enemigo) throws AtaqueYaRealizado, AtaqueNoPosible{
		this.turno.atacarEnemigo(enemigo);
	}
	
	public void jugadorActualRealizarAtaqueEspecial(Personaje enemigo) throws AtaqueYaRealizado, AtaqueNoPosible{
		this.turno.realizarAtaqueEspecial(enemigo);
	}
	
	public void jugadorActualMoverAPosicion(Posicion destino) throws MovimientoYaRealizado, MovimientoNoPosible{
		this.turno.moverPersonaje(destino);
	}
	
	public void jugadorActualTransformar() throws TransformacionNoPosible{
		this.turno.transformar();
	}
	
	public void jugadorActualSeleccionarPersonaje(Personaje personaje) throws PersonajeNoSeleccionable{
		this.turno.seleccionarPersonaje(personaje);
	}
	
	public void jugadorActualTerminarTurno(){
		this.turno.terminarTurno();
	}

}
