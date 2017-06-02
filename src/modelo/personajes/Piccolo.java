package modelo.personajes;

import modelo.personajes.modos.modosPiccolo.PiccoloNormal;

public class Piccolo extends Personaje {

	public Piccolo() {
		super("Piccolo", 500, new PiccoloNormal());
	}

}
