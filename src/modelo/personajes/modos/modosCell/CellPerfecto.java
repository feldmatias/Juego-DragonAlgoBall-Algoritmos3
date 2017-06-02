package modelo.personajes.modos.modosCell;

import modelo.personajes.Personaje;
import modelo.personajes.modos.Modo;

public class CellPerfecto extends Modo {

	public CellPerfecto() {
		super(80, 4, 4, 0, null);
		//poderPelea,distanciaAtaque,velocidad,kiNecesario,modoSiguiente
	}

	@Override
	public boolean puedeTransformarse(Personaje personaje) {
		return false;
	}

}
