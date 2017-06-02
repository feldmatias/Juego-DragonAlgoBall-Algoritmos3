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
	private int sizeTablero;
	private Tablero tablero;
	private Jugador jugador1;
	private Jugador jugador2;
	
	
	public void nuevoJuego () {
		
		this.tablero = Tablero.crearNuevo(sizeTablero);
		
		Equipo equipoGuerreros = crearEquipoGuerreros();
		Equipo equipoEnemigos = crearEquipoEnemigos();
		
				
		
	}

	private Equipo crearEquipoGuerreros() {
		Equipo equipo;
		Personaje goku = new Goku();
		Personaje gohan = new Gohan();
		Personaje piccolo = new Piccolo();		
		
		List<Personaje> lista = new ArrayList<Personaje>();
		lista.add(goku);
		lista.add(gohan);
		lista.add(piccolo);
		equipo = new Equipo (lista);
		equipo.setNombre("Guerreros Z");
		
		return equipo;
	}
	
	private Equipo crearEquipoEnemigos() {
		Equipo equipo;
		Personaje cell = new Cell();
		Personaje freezer = new Freezer();
		Personaje majinBoo = new MajinBoo();		
		
		List<Personaje> lista = new ArrayList<Personaje>();
		lista.add(cell);
		lista.add(freezer);
		lista.add(majinBoo);
		equipo = new Equipo (lista);
		equipo.setNombre("Enemigos de la Tierra");
		
		return equipo;
	}
}
