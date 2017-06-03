package modelo.juego;

import java.util.List;

import modelo.personajes.Personaje;

public class Equipo {
	
	private List<Personaje> personajes;
	private String nombre;
	
	public Equipo(List<Personaje> personajes){
		this.personajes = personajes;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}

	public boolean pertenece(Personaje personaje) {
		return this.personajes.contains(personaje);
	}

	boolean tuNombreEs(String unNombre) {
		return(this.nombre == unNombre);
	}

	public boolean contienePersonajeConNombre(String unNombrePersonaje) {
		for (Personaje unPersonaje : personajes) {
			if (unPersonaje.getNombre()==unNombrePersonaje){
				return true;
			}
		}
		return false;
	}

}
