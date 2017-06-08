package modelo.personajes;

import modelo.personajes.modos.Modo;
import modelo.personajes.modos.ModoFinal;
import modelo.personajes.modos.ModoNormal;
import modelo.personajes.modos.ModoTransformado;
import modelo.tablero.Tablero;

public class Cell extends Personaje {

	public Cell( Tablero tablero) {
		super("Cell", 500, new ModoNormal(20,3,2), tablero);
	}


	@Override
	public Modo transformarAModoTransformado() {
		return new ModoTransformado(40,4,3);
	}

	@Override
	public boolean puedeTransformarseAModoTransformado() {
		// TODO Auto-generated method stub
		return false;
		
		//HACER HACER    HACER
	}


	@Override
	public Modo transformarAModoFinal() {
		return new ModoFinal(80,4,4);
	}

	@Override
	public boolean puedeTransformarseAModoFinal() {
		// TODO Auto-generated method stub
		return false;
		
		//HACER   AHCER    HACER
	}
	
}
