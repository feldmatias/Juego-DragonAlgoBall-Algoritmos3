package modelo.personajes.modos;

import modelo.personajes.Personaje;

public class ModoNormal extends Modo{

	public ModoNormal(int poderPelea, int distanciaAtaque, int velocidad) {
		super(poderPelea, distanciaAtaque, velocidad);
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
