package modelo.personajes.modos;

import modelo.personajes.Personaje;

public class ModoFinal extends Modo {

	@Override
	public int getPoderPelea(Personaje personaje) {
		return personaje.getPoderPeleaModoFinal();
	}

	@Override
	public int getDistanciaAtaque(Personaje personaje) {
		return personaje.getDistanciaAtaqueModoFinal();
	}

	@Override
	public int getVelocidad(Personaje personaje) {
		return personaje.getVelocidadModoFinal();
	}

	@Override
	public Modo transformar(Personaje personaje) {
		return null;
	}

	@Override
	public boolean puedeTransformarse(Personaje personaje) {
		return false;
	}

}
