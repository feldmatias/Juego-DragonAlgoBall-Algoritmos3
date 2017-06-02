package modelo.personajes.modos.modosFreezer;

import modelo.personajes.Personaje;
import modelo.personajes.modos.Modo;

public class FreezerSegundaForma extends Modo {

	public FreezerSegundaForma() {
		super(40, 3, 4, 50, new FreezerDefinitivo());
		//poderPelea,distanciaAtaque,velocidad,kiNecesario,modoSiguiente
	}

	@Override
	public boolean puedeTransformarse(Personaje personaje) {
		// TODO Auto-generated method stub
		return false;
	}

}
