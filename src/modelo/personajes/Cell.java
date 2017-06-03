package modelo.personajes;

import modelo.personajes.modos.modosCell.CellNormal;
import modelo.tablero.Tablero;

public class Cell extends Personaje {

	public Cell( Tablero tablero) {
		super("Cell", 500, new CellNormal(), tablero);
	}

}
