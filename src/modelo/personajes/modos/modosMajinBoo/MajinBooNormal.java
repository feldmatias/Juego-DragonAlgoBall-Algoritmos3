package modelo.personajes.modos.modosMajinBoo;

import modelo.personajes.modos.ModoConKi;

public class MajinBooNormal extends ModoConKi {

	public MajinBooNormal() {
		super(30, 2, 2, 20, new MajinBooMalo());
		//poderPelea,distanciaAtaque,velocidad,kiNecesario,modoSiguiente
	}
	
}
