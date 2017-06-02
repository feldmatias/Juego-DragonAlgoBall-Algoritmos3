package modelo.personajes.modos.modosMajinBoo;

import modelo.personajes.modos.ModoConKi;

public class MajinBooMalo extends ModoConKi {

	public MajinBooMalo() {
		super(50, 2, 3, 50, new MajinBooOriginal());
		//poderPelea,distanciaAtaque,velocidad,kiNecesario,modoSiguiente
	}

}
