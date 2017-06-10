package modelo.personajes.modos;

import modelo.personajes.Personaje;

public class PrimeraTransformacion extends Modo {

	
	public PrimeraTransformacion(int poderPelea, int distanciaAtaque, int velocidad) {
		super(poderPelea, distanciaAtaque, velocidad);
	}

	@Override
	public Modo transformar(Personaje personaje) {
		return personaje.realizarSegundaTransformacion();
	}

	@Override
	public boolean puedeTransformarse(Personaje personaje) {
		return personaje.puedeRealizarSegundaTransformacion();
	}

}
