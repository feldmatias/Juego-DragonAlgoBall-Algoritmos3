package modelo.personajes.modos;

import modelo.personajes.Personaje;

public class SegundaTransformacion extends Modo {

	public SegundaTransformacion(double poderPelea, int distanciaAtaque, int velocidad, String nombre) {
		super(poderPelea, distanciaAtaque, velocidad, nombre);
	}

	@Override
	public Modo transformar(Personaje personaje) {
		return this;
	}

	@Override
	public boolean puedeTransformarse(Personaje personaje) {
		return false;
	}

}
