package modelo.personajes;

import modelo.personajes.modos.modosCell.CellNormal;

public class Cell extends Personaje {

	public Cell() {
		super("Cell", 500, new CellNormal());
	}

}
