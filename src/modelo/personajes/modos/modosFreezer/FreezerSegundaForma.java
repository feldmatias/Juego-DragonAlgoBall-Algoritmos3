package modelo.personajes.modos.modosFreezer;

import modelo.personajes.modos.ModoConKi;

public class FreezerSegundaForma extends ModoConKi {

	public FreezerSegundaForma() {
		super(40, 3, 4, 50, new FreezerDefinitivo());
		//poderPelea,distanciaAtaque,velocidad,kiNecesario,modoSiguiente
	}

}
