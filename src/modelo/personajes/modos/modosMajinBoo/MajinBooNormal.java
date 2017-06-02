package modelo.personajes.modos.modosMajinBoo;

import modelo.personajes.Personaje;
import modelo.personajes.modos.Modo;

public class MajinBooNormal extends Modo {

	public MajinBooNormal() {
		super(30, 2, 2, 20, new MajinBooMalo());
		//poderPelea,distanciaAtaque,velocidad,kiNecesario,modoSiguiente
	}

	@Override
	public boolean puedeTransformarse(Personaje personaje) {
		// TODO Auto-generated method stub
		return false;
	}

}
