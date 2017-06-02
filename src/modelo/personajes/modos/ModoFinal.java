package modelo.personajes.modos;

import modelo.personajes.Personaje;

public abstract class ModoFinal extends Modo {

	public ModoFinal(int poderPelea, int distanciaAtaque, int velocidad) {
		super(poderPelea, distanciaAtaque, velocidad, null);
		//poderPelea,distanciaAtaque,velocidad
	}

	@Override
	public boolean puedeTransformarse(Personaje personaje) {
		return false;
	}

}
