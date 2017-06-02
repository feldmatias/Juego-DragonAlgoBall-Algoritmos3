package modelo.personajes.modos;

import modelo.personajes.Personaje;

public abstract class Modo {
	
	private int poderPelea;
	private int distanciaAtaque;
	private int velocidad;
	private Modo modoSiguiente;
	
	public Modo (int poderPelea, int distanciaAtaque, int velocidad, Modo modoSiguiente){
		this. poderPelea = poderPelea;
		this.distanciaAtaque = distanciaAtaque;
		this.velocidad = velocidad;
		this.modoSiguiente = modoSiguiente;
	}

	public int getPoderPelea() {
		return this.poderPelea;
	}

	public int getDistanciaAtaque() {
		return this.distanciaAtaque;
	}
	
	public int getVelocidad() {
		return this.velocidad;
	}

	public Modo transformar(Personaje personaje) {
		return this.modoSiguiente;
	}

	public abstract boolean puedeTransformarse(Personaje personaje);
	
}
