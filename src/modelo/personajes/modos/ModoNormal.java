package modelo.personajes.modos;

import modelo.personajes.Personaje;

public class ModoNormal extends Modo{

	@Override
	public int getPoderPelea(Personaje personaje) {
		return personaje.getPoderPeleaModoNormal();
	}

	@Override
	public int getDistanciaAtaque(Personaje personaje) {
		return personaje.getDistanciaAtaqueModoNormal();
	}

	@Override
	public int getVelocidad(Personaje personaje) {
		return personaje.getVelocidadModoNormal();
	}

	@Override
	public Modo transformar(Personaje personaje) {
		personaje.transformarAModoTransformado();
		return new ModoTransformado();
	}

	@Override
	public boolean puedeTransformarse(Personaje personaje) {
		return personaje.puedeTransformarseAModoTransformado();
	}



}
