package modelo.personajes.modos;

import modelo.personajes.Personaje;

public class ModoTransformado implements Modo {

	@Override
	public int getPoderPelea(Personaje personaje) {
		return personaje.getPoderPeleaModoTransformado();
	}

	@Override
	public int getDistanciaAtaque(Personaje personaje) {
		return personaje.getDistanciaAtaqueModoTransformado();
	}

	@Override
	public int getVelocidad(Personaje personaje) {
		return personaje.getVelocidadModoTransformado();
	}

	@Override
	public Modo transformar(Personaje personaje) {
		personaje.transformarAModoFinal();
		return new ModoFinal();
	}

	@Override
	public boolean puedeTransformarse(Personaje personaje) {
		return personaje.puedeTransformarseAModoFinal();
	}

}
