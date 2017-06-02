package modelo.personajes;

import modelo.personajes.modos.modosGohan.GohanNormal;

public class Gohan extends Personaje {

	public Gohan() {
		super("Gohan", 300, new GohanNormal());
	}

}
