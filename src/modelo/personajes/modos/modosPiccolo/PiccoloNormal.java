package modelo.personajes.modos.modosPiccolo;

import modelo.personajes.Personaje;
import modelo.personajes.modos.Modo;

public class PiccoloNormal extends Modo {

	public PiccoloNormal() {
		super(20, 2, 2, 20, new PiccoloFortalecido());
		//poderPelea,distanciaAtaque,velocidad,kiNecesario,modoSiguiente
	}

	@Override
	public boolean puedeTransformarse(Personaje personaje) {
		// TODO Auto-generated method stub
		return false;
	}

}
