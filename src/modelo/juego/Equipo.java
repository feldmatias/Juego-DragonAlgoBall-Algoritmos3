package modelo.juego;

import java.util.List;

import modelo.personajes.Personaje;

public class Equipo {
	
	private List<Personaje> personajes;
	private String nombre;
	
	public Equipo(String nombre, List<Personaje> personajes){
		this.nombre = nombre;
		this.personajes = personajes;
	}

	public boolean pertenece(Personaje personaje) {
		return this.personajes.contains(personaje);
	}

	public String getNombre() {
		return this.nombre;
	}

	public List<Personaje> getMiembros() {
		return this.personajes;
	}

	public void empezarTurno() {
		for (Personaje personaje: this.personajes){
			personaje.generarKi();
		}
	}

	public void personajeMuerto(Personaje personaje) {
		this.personajes.remove(personaje);
	}
	
	public boolean equipoMuerto(){
		return this.personajes.isEmpty();
	}

}
