package modelo.juego;

import java.util.List;

import modelo.personajes.Personaje;
import modelo.utilidades.Constantes;

public class Equipo {
	
	private List<Personaje> personajes;
	private String nombre;
	private int cantidadEsferas;
	
	public Equipo(String nombre, List<Personaje> personajes){
		this.nombre = nombre;
		this.personajes = personajes;
		this.inicializarMiembros();
		this.cantidadEsferas = 0;
	}

	private void inicializarMiembros(){
		for(Personaje personaje: this.personajes ){
			personaje.setEquipo(this);
		}
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
			personaje.empezarTurno();
		}
	}

	public void personajeMuerto(Personaje personaje) {
		this.personajes.remove(personaje);
	}
	
	public boolean estaMuerto(){
		return this.personajes.isEmpty();
	}

	public void agregarEsferaAColeccion() {
		this.cantidadEsferas += 1;

	}
	
	public int getCantidadEsferas(){
		return this.cantidadEsferas;
	}

	public boolean coleccionDeEsferasCompleta() {
		return this.getCantidadEsferas() >= Constantes.cantidadEsferasParaGanar;
	}

}
