package modelo.personajes.modos;

import modelo.personajes.Personaje;

public class ModoTransformado extends Modo {

	
	public ModoTransformado(int poderPelea, int distanciaAtaque, int velocidad) {
		super(poderPelea, distanciaAtaque, velocidad);
	}

	@Override
	public Modo transformar(Personaje personaje) {
		return personaje.transformarAModoFinal();
	}

	@Override
	public boolean puedeTransformarse(Personaje personaje) {
		return personaje.puedeTransformarseAModoFinal();
	}

}
