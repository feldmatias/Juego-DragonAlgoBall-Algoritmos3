package modelo.personajes.modos;

import modelo.personajes.Personaje;

public class ModoFinal extends Modo {

	public ModoFinal(int poderPelea, int distanciaAtaque, int velocidad) {
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
