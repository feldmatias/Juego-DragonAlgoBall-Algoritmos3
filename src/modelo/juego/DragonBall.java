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
	
	private Equipo crearEquipoYPosicionarPersonajes(String nombreEquipo, Personaje pers1, Personaje pers2, Personaje pers3, Posicion posInicial){
		List<Personaje> lista = new ArrayList<Personaje>();
		lista.add(pers1);
		lista.add(pers2);
		lista.add(pers3);
		tablero.posicionarPersonaje(pers1, posInicial);
		tablero.posicionarPersonaje(pers2, posInicial.sumarPosicion(new Posicion(0,1)));
		tablero.posicionarPersonaje(pers3, posInicial.sumarPosicion(new Posicion(0,2)));
		return new Equipo (nombreEquipo, lista);
	}
	
	private Equipo crearEquipoGuerreros() {
		Personaje goku = new Goku(this.tablero);
		Personaje gohan = new Gohan(this.tablero);
		Personaje piccolo = new Piccolo(this.tablero);		
	
		return this.crearEquipoYPosicionarPersonajes("Guerreros Z", goku, gohan, piccolo, new Posicion (0,0));
	}
	
	private Equipo crearEquipoEnemigos() {
		Personaje cell = new Cell(this.tablero);
		Personaje freezer = new Freezer(this.tablero);
		Personaje majinBoo = new MajinBoo(this.tablero);		
		
		return this.crearEquipoYPosicionarPersonajes("Enemigos de la Tierra", cell, freezer, majinBoo, new Posicion (SIZE_TABLERO - 1, 0));
	}

	public void elegirEquipos(String primerEquipo, String segundoEquipo) {
		//Excepciones
		this.asignarEquipoAJugador(this.equipos.get(primerEquipo), this.jugador1);
		this.asignarEquipoAJugador(this.equipos.get(segundoEquipo), this.jugador2);
	}

	public boolean existeEquipo(String unNombre) {
		
		return this.equipos.containsKey(unNombre);
		
	}

	public boolean personajePerteneceAEquipo(String unNombrePersonaje, String unNombreEquipo) {
		
		Equipo unEquipo = this.getEquipo(unNombreEquipo);
		return(unEquipo.contienePersonajeConNombre(unNombrePersonaje));
		
	}

	private Equipo getEquipo(String unNombre) {
		return equipos.get(unNombre);
		//return null;//aca iria un return excepcion
	}

	public String getNombreEquipoDelJugador(String unJugador) {
		Jugador elJugador = this.jugador1; //probando para que no quede null
		if(unJugador == "Jugador 1"){
			elJugador = this.jugador1;
		}
		if(unJugador == "Jugador 2"){
			elJugador = this.jugador2;
		}
		Equipo elEquipo = elJugador.getEquipo();
		return (elEquipo.getNombre());
	
	}
}
