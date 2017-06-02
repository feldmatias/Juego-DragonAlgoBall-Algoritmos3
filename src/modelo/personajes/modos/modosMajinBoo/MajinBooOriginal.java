package modelo.personajes.modos.modosMajinBoo;

import modelo.personajes.Personaje;
import modelo.personajes.modos.Modo;

public class MajinBooOriginal extends Modo {

	public MajinBooOriginal() {
		super(60, 3, 4, 0, null);
		//poderPelea,distanciaAtaque,velocidad,kiNecesario,modoSiguiente
	}

	@Override
	public boolean puedeTransformarse(Personaje personaje) {
		return false;
	}

}
