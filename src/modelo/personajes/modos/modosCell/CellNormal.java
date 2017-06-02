package modelo.personajes.modos.modosCell;

import modelo.personajes.Personaje;
import modelo.personajes.modos.Modo;

public class CellNormal extends Modo {

	public CellNormal() {
		super(20, 3, 2, 0, new CellSemiPerfecto());
		//poderPelea,distanciaAtaque,velocidad,kiNecesario,modoSiguiente
	}

	@Override
	public boolean puedeTransformarse(Personaje personaje) {
		// TODO Auto-generated method stub
		return false;
	}

}
