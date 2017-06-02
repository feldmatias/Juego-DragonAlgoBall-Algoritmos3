package modelo.personajes;

import modelo.personajes.modos.modosGoku.GokuNormal;

public class Goku extends Personaje {
	
	public Goku(){
		super("Goku", 500,  new GokuNormal());
	}

}
