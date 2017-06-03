package modelo.personajes;

import modelo.personajes.modos.modosPiccolo.PiccoloNormal;
import modelo.tablero.Tablero;

public class Piccolo extends Personaje {

	public Piccolo(Tablero tablero) {
		super("Piccolo", 500, new PiccoloNormal(), tablero);
	}

}
