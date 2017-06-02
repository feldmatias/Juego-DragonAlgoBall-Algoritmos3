package modelo.personajes.modos.modosFreezer;

import modelo.personajes.Personaje;
import modelo.personajes.modos.Modo;

public class FreezerDefinitivo extends Modo {

	public FreezerDefinitivo() {
		super(50, 3, 6, 0, null);
		//poderPelea,distanciaAtaque,velocidad,kiNecesario,modoSiguiente
	}

	@Override
	public boolean puedeTransformarse(Personaje personaje) {
		return false;
	}

}
