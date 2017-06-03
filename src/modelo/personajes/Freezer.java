package modelo.personajes;

import modelo.personajes.modos.modosFreezer.FreezerNormal;
import modelo.tablero.Tablero;

public class Freezer extends Personaje{

	public Freezer(Tablero tablero) {
		super("Freezer", 400, new FreezerNormal(), tablero);
	}

}
