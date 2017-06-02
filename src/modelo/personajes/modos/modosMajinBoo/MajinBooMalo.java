package modelo.personajes.modos.modosMajinBoo;

import modelo.personajes.Personaje;
import modelo.personajes.modos.Modo;

public class MajinBooMalo extends Modo {

	public MajinBooMalo() {
		super(50, 2, 3, 50, new MajinBooOriginal());
		//poderPelea,distanciaAtaque,velocidad,kiNecesario,modoSiguiente
	}

	@Override
	public boolean puedeTransformarse(Personaje personaje) {
		// TODO Auto-generated method stub
		return false;
	}

}
