package modelo.consumibles;

import modelo.personajes.Personaje;
import vista.Posicionable;

public abstract class Efecto implements Posicionable{
	
	private int duracion;
	private String nombre;
	
	public Efecto (int duracion, String nombre){
		this.duracion = duracion;
		this.nombre = nombre;
	}

	public int modificarVelocidad(int velocidad){
		return velocidad;
	}
	
	public double modificarPoderPelea(double poderPelea){
		return poderPelea;
	}
	
	public void aplicarEfectoInstantaneo(Personaje personaje){
		return;
	}
	
	public void restarDuracion(){
		this.duracion -= 1;
	}
	
	public void empezarTurno(){
		return;
	}
	
	public boolean terminoTiempo(){
		return this.duracion <= 0 ;
	}
	
	public String getNombre(){
		return this.nombre;
	}

}
