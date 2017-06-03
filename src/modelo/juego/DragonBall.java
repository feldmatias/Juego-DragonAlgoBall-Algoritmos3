package modelo.juego;

import modelo.personajes.Cell;
import modelo.personajes.Freezer;
import modelo.personajes.Gohan;
import modelo.personajes.Goku;
import modelo.personajes.MajinBoo;
import modelo.personajes.Personaje;
import modelo.personajes.Piccolo;
import modelo.tablero.Tablero;

import java.util.ArrayList;
import java.util.List;

import modelo.juego.Equipo;

public class DragonBall {
	private static final int SIZE_TABLERO = 6; //Defini la constante con cualquier numero
	private Tablero tablero;
	private Jugador jugador1;
	private Jugador jugador2;
	private Equipo equipoGuerreros;
	private Equipo equipoEnemigos;
	
	
	public void nuevoJuego () {
		
		this.tablero = new Tablero(SIZE_TABLERO);
		
		equipoGuerreros = crearEquipoGuerreros();
		equipoEnemigos = crearEquipoEnemigos();
		
		
	}

	private void asignarEquipoAJugador(Equipo equipo, Jugador jugador) {
		jugador.setEquipo(equipo);
		
	}
	
	private Equipo crearEquipo(String nombreEquipo, Personaje pers1, Personaje pers2, Personaje pers3){
		List<Personaje> lista = new ArrayList<Personaje>();
		lista.add(pers1);
		lista.add(pers2);
		lista.add(pers3);
		return new Equipo (nombreEquipo, lista);
	}
	
	private Equipo crearEquipoGuerreros() {
		Personaje goku = new Goku();
		Personaje gohan = new Gohan();
		Personaje piccolo = new Piccolo();		
	
		return this.crearEquipo("Guerreros Z", goku, gohan, piccolo);
	}
	
	private Equipo crearEquipoEnemigos() {
		Personaje cell = new Cell();
		Personaje freezer = new Freezer();
		Personaje majinBoo = new MajinBoo();		
		
		return this.crearEquipo("Enemigos de la tierra", cell, freezer, majinBoo);
	}

	public void elegirEquipos(String primerEquipo, String segundoEquipo) {
		/*falta excepciones nombres invalidos*/
		if(primerEquipo=="Guerreros"){
			asignarEquipoAJugador(equipoGuerreros, jugador1);
			asignarEquipoAJugador(equipoEnemigos, jugador2);
		}else{
			asignarEquipoAJugador(equipoGuerreros, jugador2);
			asignarEquipoAJugador(equipoEnemigos, jugador1);
		}
		
	}

	public boolean existeEquipo(String unNombre) {
		
		return ((equipoGuerreros.tuNombreEs(unNombre)) || (equipoEnemigos.tuNombreEs(unNombre)));
		
	}

	public boolean personajePerteneceAEquipo(String unNombrePersonaje, String unNombreEquipo) {
		
		Equipo unEquipo = this.getEquipo(unNombreEquipo);
		return(unEquipo.contienePersonajeConNombre(unNombrePersonaje));
		
	}

	private Equipo getEquipo(String unNombre) {
		if(unNombre=="Guerreros Z"){
			return this.equipoGuerreros;
		}
		if(unNombre=="Enemigos de la Tierra"){
			return this.equipoEnemigos;
		}
		return null;//aca iria un return excepcion
	}
}
