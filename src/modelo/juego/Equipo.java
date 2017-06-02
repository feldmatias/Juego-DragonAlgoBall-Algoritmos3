package modelo.juego;

import java.util.List;

import modelo.personajes.Personaje;

public class Equipo {
	
	private List<Personaje> personajes;
	
	public Equipo(List<Personaje> personajes){
		this.personajes = personajes;
	}

	public boolean pertenece(Personaje personaje) {
		return this.personajes.contains(personaje);
	}

}
