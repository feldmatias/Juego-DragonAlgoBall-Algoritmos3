package modelo.personajes.modos;

import modelo.personajes.Personaje;

public class SegundaTransformacion extends Modo {

	public SegundaTransformacion(int poderPelea, int distanciaAtaque, int velocidad) {
		super(poderPelea, distanciaAtaque, velocidad);
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
