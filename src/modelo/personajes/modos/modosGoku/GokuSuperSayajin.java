package modelo.personajes.modos.modosGoku;

import modelo.personajes.Personaje;
import modelo.personajes.modos.Modo;

public class GokuSuperSayajin extends Modo {

	public GokuSuperSayajin() {
		super(60, 4, 5, 0, null);
		//poderPelea,distanciaAtaque,velocidad,kiNecesario,modoSiguiente
	}

	@Override
	public boolean puedeTransformarse(Personaje personaje) {
		return false;
	}

}
