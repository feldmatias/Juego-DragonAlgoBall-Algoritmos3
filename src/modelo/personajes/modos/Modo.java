package modelo.personajes.modos;

import modelo.personajes.Personaje;

public abstract class Modo {

	private int poderPelea;
	private int distanciaAtaque;
	private int velocidad;
	
	public Modo(int poderPelea, int distanciaAtaque, int velocidad){
		this.poderPelea = poderPelea;
		this.distanciaAtaque = distanciaAtaque;
		this.velocidad = velocidad;
	}
	
	public int getPoderPelea(){
		return this.poderPelea;
	}

	public  int getDistanciaAtaque(){
		return this.distanciaAtaque;
	}
	
	public int getVelocidad(){
		return this.velocidad;
	}

	public abstract Modo transformar(Personaje personaje);

	public abstract boolean puedeTransformarse(Personaje personaje);
	
	public void empezarTurno(Personaje personaje){
		personaje.generarKi();
	}
}
