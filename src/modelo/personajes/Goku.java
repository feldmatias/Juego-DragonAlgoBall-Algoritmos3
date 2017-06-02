package modelo.personajes;

import modelo.juego.Equipo;

public class Goku extends Personaje {
	
	public Goku(Equipo equipo){
		int vidaInicial = 500;
		super("Goku", vidaInicial, equipo, new ModoNormalGoku());
	}

}
