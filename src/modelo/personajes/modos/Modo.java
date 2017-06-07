package modelo.personajes.modos;

import modelo.personajes.Personaje;

public interface Modo {

	public int getPoderPelea(Personaje personaje);

	public int getDistanciaAtaque(Personaje personaje);
	
	public int getVelocidad(Personaje personaje);

	public Modo transformar(Personaje personaje);

	public boolean puedeTransformarse(Personaje personaje);
	
}
