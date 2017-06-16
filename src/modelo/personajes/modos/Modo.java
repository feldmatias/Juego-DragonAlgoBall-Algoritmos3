package modelo.personajes.modos;

import modelo.excepciones.TransformacionNoPosible;
import modelo.personajes.Personaje;

public abstract class Modo {

	private double poderPelea;
	private int distanciaAtaque;
	private int velocidad;
	private String nombre;
	
	public Modo(double poderPelea, int distanciaAtaque, int velocidad, String nombre){
		this.poderPelea = poderPelea;
		this.distanciaAtaque = distanciaAtaque;
		this.velocidad = velocidad;
		this.nombre = nombre;
	}
	
	public double getPoderPelea(){
		return this.poderPelea;
	}

	public  int getDistanciaAtaque(){
		return this.distanciaAtaque;
	}
	
	public int getVelocidad(){
		return this.velocidad;
	}

	public abstract Modo transformar(Personaje personaje);

	public abstract void puedeTransformarse(Personaje personaje) throws TransformacionNoPosible;
	
	public void empezarTurno(Personaje personaje){
		personaje.generarKi();
	}

	public String getNombre() {
		return this.nombre;
	}
}
