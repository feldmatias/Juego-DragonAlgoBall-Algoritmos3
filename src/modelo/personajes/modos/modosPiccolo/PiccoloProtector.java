package modelo.personajes.modos.modosPiccolo;

import modelo.personajes.Personaje;
import modelo.personajes.modos.Modo;

public class PiccoloProtector extends Modo {

	public PiccoloProtector() {
		super(60, 6, 4, 0, null);
		//poderPelea,distanciaAtaque,velocidad,kiNecesario,modoSiguiente
	}

	@Override
	public boolean puedeTransformarse(Personaje personaje) {
		return false;
	}

}
