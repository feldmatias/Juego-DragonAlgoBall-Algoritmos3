package modelo.personajes;

import modelo.tablero.Tablero;

public class Cell extends Personaje {

	public Cell( Tablero tablero) {
		super("Cell", 500, tablero);
	}

	@Override
	public int getPoderPeleaModoNormal() {
		return 20;
	}

	@Override
	public int getDistanciaAtaqueModoNormal() {
		return 3;
	}

	@Override
	public int getVelocidadModoNormal() {
		return 2;
	}

	@Override
	public void transformarAModoTransformado() {
		return;
	}

	@Override
	public boolean puedeTransformarseAModoTransformado() {
		// TODO Auto-generated method stub
		return false;
		
		//HACER HACER    HACER
	}

	@Override
	public int getPoderPeleaModoTransformado() {
		return 40;
	}

	@Override
	public int getDistanciaAtaqueModoTransformado() {
		return 4;
	}

	@Override
	public int getVelocidadModoTransformado() {
		return 3;
	}

	@Override
	public void transformarAModoFinal() {
		return;
	}

	@Override
	public boolean puedeTransformarseAModoFinal() {
		// TODO Auto-generated method stub
		return false;
		
		//HACER   AHCER    HACER
	}

	@Override
	public int getPoderPeleaModoFinal() {
		return 80;
	}

	@Override
	public int getDistanciaAtaqueModoFinal() {
		return 4;
	}

	@Override
	public int getVelocidadModoFinal() {
		return 4;
	}

}
