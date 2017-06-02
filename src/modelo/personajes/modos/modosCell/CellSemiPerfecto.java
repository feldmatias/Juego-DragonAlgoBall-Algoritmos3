package modelo.personajes.modos.modosCell;

import modelo.personajes.Personaje;
import modelo.personajes.modos.Modo;

public class CellSemiPerfecto extends Modo {

	public CellSemiPerfecto() {
		super(40, 4, 3, 0, new CellPerfecto());
		//poderPelea,distanciaAtaque,velocidad,kiNecesario,modoSiguiente
	}

	@Override
	public boolean puedeTransformarse(Personaje personaje) {
		// TODO Auto-generated method stub
		return false;
	}

}
