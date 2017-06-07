package modelo.personajes;

import modelo.tablero.Tablero;

public class Gohan extends PersonajeTransformableConKi {
	
	public Gohan(Tablero tablero) {
		super("Gohan", 300, tablero, 10, 30);
	}

	@Override
	public int getPoderPeleaModoNormal() {
		return 15;
	}

	@Override
	public int getDistanciaAtaqueModoNormal() {
		return 2;
	}

	@Override
	public int getVelocidadModoNormal() {
		return 2;
	}

	@Override
	public int getPoderPeleaModoTransformado() {
		return 30;
	}

	@Override
	public int getDistanciaAtaqueModoTransformado() {
		return 2;
	}

	@Override
	public int getVelocidadModoTransformado() {
		return 2;
	}


	@Override
	public boolean puedeTransformarseAModoFinal() {
		// TODO Auto-generated method stub
		return false;
		
		//HACER HACER HACER
	}

	@Override
	public int getPoderPeleaModoFinal() {
		return 100;
	}

	@Override
	public int getDistanciaAtaqueModoFinal() {
		return 4;
	}

	@Override
	public int getVelocidadModoFinal() {
		return 3;
	}

}
