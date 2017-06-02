package modelo.personajes.modos.modosFreezer;

import modelo.personajes.modos.ModoConKi;

public class FreezerNormal extends ModoConKi {

	public FreezerNormal() {
		super(20, 2, 4, 20, new FreezerSegundaForma());
		//poderPelea,distanciaAtaque,velocidad,kiNecesario,modoSiguiente
	}

}
