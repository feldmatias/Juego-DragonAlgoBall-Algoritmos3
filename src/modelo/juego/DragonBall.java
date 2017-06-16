package modelo.juego;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.consumibles.Efecto;
import modelo.consumibles.EsferaDeDragon;
import modelo.consumibles.NubeVoladora;
import modelo.consumibles.SemillaDelErmitanio;
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
		
		this.crearTablero(Constantes.SIZE_TABLERO);
		this.equipos = new HashMap<String, Equipo>();
		this.crearEquipoGuerreros();
		this.crearEquipoEnemigos();
		this.jugador1 = new Jugador();
		this.jugador2 = new Jugador();
		
	}
	
	private void crearTablero(int sizeTablero){
		List <Efecto> consumiblesPosibles = new ArrayList<Efecto>();
		consumiblesPosibles.add(new EsferaDeDragon());
		consumiblesPosibles.add(new NubeVoladora());
		consumiblesPosibles.add(new SemillaDelErmitanio());
		this.tablero = new Tablero(sizeTablero);
		this.tablero.setListadoConsumibles(consumiblesPosibles);
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
	
	private Equipo crearEquipoGuerreros() /*throws PosicionFueraDeRango*/ {
		Personaje goku = new Goku(this.tablero);
		Personaje gohan = new Gohan(this.tablero);
		Personaje piccolo = new Piccolo(this.tablero);		
		return this.crearEquipo(Constantes.GUERREROS, goku, gohan, piccolo);
	}
	
	private Equipo crearEquipoEnemigos() /*throws PosicionFueraDeRango*/ {
		Personaje cell = new Cell(this.tablero);
		Personaje freezer = new Freezer(this.tablero);
		Personaje majinBoo = new MajinBoo(this.tablero);		
		return this.crearEquipo(Constantes.ENEMIGOS, cell, freezer, majinBoo);
	}

	
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
	
	public void jugadorActualTerminarTurno(){
		this.turno.terminarTurno();
	}
	
	public boolean estaTerminado(){
		if (jugador1.equipoMuerto()){
			ganador = jugador2.getEquipo().getNombre() + ": Derroto al otro equipo";
			return true;
		}
		if (jugador2.equipoMuerto()){
			ganador = jugador1.getEquipo().getNombre() + ": Derroto al otro equipo";
			return true;
		}
		if (jugador1.coleccionDeEsferasCompleta()){
			ganador = jugador1.getEquipo().getNombre() + ": Encontro siete esferas del dragon";
			return true;
		}
		if (jugador2.coleccionDeEsferasCompleta()){
			ganador = jugador2.getEquipo().getNombre() + ": Encontro siete esferas del dragon";
			return true;
		}
		return false;
	}
	
	public String getGanador(){
		return ganador;
	}

}
