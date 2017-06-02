package modelo.personajes.modos.modosPiccolo;

import modelo.personajes.modos.ModoConKi;

public class PiccoloNormal extends ModoConKi {

	public PiccoloNormal() {
		super(20, 2, 2, 20, new PiccoloFortalecido());
		//poderPelea,distanciaAtaque,velocidad,kiNecesario,modoSiguiente
	}
}
