package modelo.juego;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.personajes.Personaje;
import modelo.utilidades.Constantes;

public class Equipo {
	
	private Map<String, Personaje> personajes;
	private String nombre;
	private int cantidadEsferas;
	
	public Equipo(String nombre, List<Personaje> listaPersonajes){
		this.nombre = nombre;
		this.personajes = new HashMap<String, Personaje>();
		this.inicializarMiembros(listaPersonajes);
		this.cantidadEsferas = 0;
	}

	private void inicializarMiembros(List<Personaje> listaPersonajes){
		for(Personaje personaje: listaPersonajes ){
			personaje.setEquipo(this);
			personajes.put(personaje.getNombre(), personaje);
		}
	} 
	
	public boolean pertenece(Personaje personaje) {
		return this.personajes.containsValue(personaje);
	}
	
	public String getNombre() {
		return this.nombre;
	}

	public Map<String, Personaje> getMiembros() {
		return this.personajes;
	}

	public void empezarTurno() {
		for (Personaje personaje: this.personajes.values()){
			personaje.empezarTurno();
		}
	}

	public void personajeMuerto(Personaje personaje) {
		this.personajes.values().remove(personaje);
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
