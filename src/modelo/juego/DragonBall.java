package modelo.juego;

import modelo.personajes.Cell;
import modelo.personajes.Freezer;
import modelo.personajes.Gohan;
import modelo.personajes.Goku;
import modelo.personajes.MajinBoo;
import modelo.personajes.Personaje;
import modelo.personajes.Piccolo;
import modelo.tablero.Posicion;
import modelo.tablero.Tablero;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.excepciones.EquipoInexistente;
import modelo.excepciones.NombresDeEquipoIguales;
import modelo.excepciones.PosicionFueraDeRango;
import modelo.juego.Equipo;

public class DragonBall {
	
	private static final int SIZE_TABLERO = 8;
	private Tablero tablero;
	private Jugador jugador1;
	private Jugador jugador2;
	private Map<String,Equipo> equipos;
	
	
	public DragonBall () {
		
		this.tablero = new Tablero(SIZE_TABLERO);
		this.equipos = new HashMap<String, Equipo>();
		equipos.put("Guerreros Z", this.crearEquipoGuerreros());
		equipos.put("Enemigos de la Tierra", this.crearEquipoEnemigos());
		
		jugador1 = new Jugador();
		jugador2 = new Jugador();
		
		
	}

	private void asignarEquipoAJugador(Equipo equipo, Jugador jugador) {
		jugador.setEquipo(equipo);
		
	}
	
	private Equipo crearEquipoYPosicionarPersonajes(String nombreEquipo, Personaje pers1, Personaje pers2, Personaje pers3, Posicion posInicial) throws PosicionFueraDeRango{
		List<Personaje> lista = new ArrayList<Personaje>();
		lista.add(pers1);
		lista.add(pers2);
		lista.add(pers3);
		tablero.posicionarPersonaje(pers1, posInicial);
		tablero.posicionarPersonaje(pers2, posInicial.sumarPosicion(new Posicion(0,1)));
		tablero.posicionarPersonaje(pers3, posInicial.sumarPosicion(new Posicion(0,-1)));
		return new Equipo (nombreEquipo, lista);
	}
	
	private Equipo crearEquipoGuerreros() throws PosicionFueraDeRango {
		Personaje goku = new Goku(this.tablero);
		Personaje gohan = new Gohan(this.tablero);
		Personaje piccolo = new Piccolo(this.tablero);		
	
		return this.crearEquipoYPosicionarPersonajes("Guerreros Z", goku, gohan, piccolo, new Posicion (0,SIZE_TABLERO/2));
	}
	
	private Equipo crearEquipoEnemigos() throws PosicionFueraDeRango {
		Personaje cell = new Cell(this.tablero);
		Personaje freezer = new Freezer(this.tablero);
		Personaje majinBoo = new MajinBoo(this.tablero);		
		
		return this.crearEquipoYPosicionarPersonajes("Enemigos de la Tierra", cell, freezer, majinBoo, new Posicion (SIZE_TABLERO - 1, SIZE_TABLERO/2));
	}

	public void elegirEquipos(String primerEquipo, String segundoEquipo) throws NombresDeEquipoIguales, EquipoInexistente {
		//Excepciones
		if(primerEquipo == segundoEquipo){
			throw new NombresDeEquipoIguales();
		}
		
		if (! this.existeEquipo(primerEquipo) || ! this.existeEquipo(segundoEquipo)){
			throw new EquipoInexistente();
		}
		
		this.asignarEquipoAJugador(this.equipos.get(primerEquipo), this.jugador1);
		this.asignarEquipoAJugador(this.equipos.get(segundoEquipo), this.jugador2);
	}

	private boolean existeEquipo(String unNombre) {
		
		return this.equipos.containsKey(unNombre);
		
	}

	public Jugador getJugador1(){
		return this.jugador1;
	}
	
	public Jugador getJugador2(){
		return this.jugador2;
	}


}
