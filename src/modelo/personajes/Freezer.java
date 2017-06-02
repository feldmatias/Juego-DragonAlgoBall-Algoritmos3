package modelo.personajes;

import modelo.personajes.modos.modosFreezer.FreezerNormal;

public class Freezer extends Personaje{

	public Freezer() {
		super("Freezer", 400, new FreezerNormal());
	}

}
