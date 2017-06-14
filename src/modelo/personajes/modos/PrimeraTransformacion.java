package modelo.personajes.modos;

import modelo.personajes.Personaje;

public class PrimeraTransformacion extends Modo {

	
	public PrimeraTransformacion(double poderPelea, int distanciaAtaque, int velocidad, String nombre) {
		super(poderPelea, distanciaAtaque, velocidad, nombre);
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
