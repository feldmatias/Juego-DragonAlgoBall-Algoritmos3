package modelo.juego;

import java.util.ArrayList;
import java.util.List;

import modelo.consumibles.Efecto;
import modelo.consumibles.EsferaDeDragon;
import modelo.personajes.Personaje;

public class Equipo {
	
	private List<Personaje> personajes;
	private String nombre;
	private List<EsferaDeDragon> coleccionDeEsferas;
	
	public Equipo(String nombre, List<Personaje> personajes){
		this.nombre = nombre;
		this.personajes = personajes;
		this.inicializarMiembros();
		this.coleccionDeEsferas = new ArrayList<EsferaDeDragon>();
	}

	public boolean pertenece(Personaje personaje) {
		return this.personajes.contains(personaje);
	}

	private void inicializarMiembros(){
		for(Personaje personaje: this.personajes ){
			personaje.setEquipo(this);
		}
	} 
	
	public String getNombre() {
		return this.nombre;
	}

	public List<Personaje> getMiembros() {
		return this.personajes;
	}

	public void empezarTurno() {
		for (Personaje personaje: this.personajes){
			personaje.empezarTurno();
		}
	}

	public void personajeMuerto(Personaje personaje) {
		this.personajes.remove(personaje);
	}
	
	public boolean equipoMuerto(){
		return this.personajes.isEmpty();
	}

	public void agregarEsferaAColeccion(EsferaDeDragon esferaDeDragon) {
		this.coleccionDeEsferas.add(esferaDeDragon);
		
	}

}
