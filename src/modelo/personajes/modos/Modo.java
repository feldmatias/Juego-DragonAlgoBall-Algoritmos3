package modelo.personajes.modos;

import modelo.personajes.Personaje;

public abstract class Modo {

	public abstract int getPoderPelea(Personaje personaje);

	public abstract int getDistanciaAtaque(Personaje personaje);
	
	public abstract int getVelocidad(Personaje personaje);

	public abstract Modo transformar(Personaje personaje);

	public abstract boolean puedeTransformarse(Personaje personaje);
	
	public void empezarTurno(Personaje personaje){
		personaje.generarKi();
	}
}
