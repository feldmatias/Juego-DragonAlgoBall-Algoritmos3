package modelo.personajes.modos.modosGoku;

import modelo.personajes.modos.ModoConKi;

public class GokuNormal extends ModoConKi {

	public GokuNormal() {
		super(20, 2, 2, 20, new GokuKaioKen());
		//poderPelea,distanciaAtaque,velocidad,kiNecesario,modoSiguiente
	}

}
