package modelo.personajes.modos.modosPiccolo;

import modelo.personajes.Personaje;
import modelo.personajes.modos.Modo;

public class PiccoloFortalecido extends Modo {

	public PiccoloFortalecido() {
		super(40, 4, 3, new PiccoloProtector());
		//poderPelea,distanciaAtaque,velocidad,modoSiguiente
	}

	@Override
	public boolean puedeTransformarse(Personaje personaje) {
		// TODO Auto-generated method stub
		//HACER
		return false;
	}

}
