package modelo.personajes.modos.modosFreezer;

import modelo.personajes.Personaje;
import modelo.personajes.modos.Modo;

public class FreezerNormal extends Modo {

	public FreezerNormal() {
		super(20, 2, 4, 20, new FreezerSegundaForma());
		//poderPelea,distanciaAtaque,velocidad,kiNecesario,modoSiguiente
	}

	@Override
	public boolean puedeTransformarse(Personaje personaje) {
		// TODO Auto-generated method stub
		return false;
	}

}
