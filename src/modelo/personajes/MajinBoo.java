package modelo.personajes;

import modelo.personajes.modos.modosMajinBoo.MajinBooNormal;
import modelo.tablero.Tablero;

public class MajinBoo extends Personaje {

	public MajinBoo(Tablero tablero) {
		super("Majin Boo", 300, new MajinBooNormal(), tablero);
	}
	
}
