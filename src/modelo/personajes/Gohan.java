package modelo.personajes;

import modelo.personajes.modos.modosGohan.GohanNormal;
import modelo.tablero.Tablero;

public class Gohan extends Personaje {

	public Gohan(Tablero tablero) {
		super("Gohan", 300, new GohanNormal(), tablero);
	}

}
