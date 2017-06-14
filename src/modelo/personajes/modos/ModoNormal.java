package modelo.personajes.modos;

import modelo.personajes.Personaje;

public class ModoNormal extends Modo{

	public ModoNormal(double poderPelea, int distanciaAtaque, int velocidad, String nombre) {
		super(poderPelea, distanciaAtaque, velocidad, nombre);
	}

	@Override
	public Modo transformar(Personaje personaje) {
		return personaje.realizarPrimeraTransformacion();
	}

	@Override
	public boolean puedeTransformarse(Personaje personaje) {
		return personaje.puedeRealizarPrimeraTransformacion();
	}



}
