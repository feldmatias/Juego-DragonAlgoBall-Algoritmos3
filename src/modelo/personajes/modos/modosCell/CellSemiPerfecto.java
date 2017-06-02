package modelo.personajes.modos.modosCell;

import modelo.personajes.Personaje;
import modelo.personajes.modos.Modo;

public class CellSemiPerfecto extends Modo {

	public CellSemiPerfecto() {
		super(40, 4, 3, new CellPerfecto());
		//poderPelea,distanciaAtaque,velocidad,modoSiguiente
	}

	@Override
	public boolean puedeTransformarse(Personaje personaje) {
		// TODO Auto-generated method stub
		//HACER
		return false;
	}

}
