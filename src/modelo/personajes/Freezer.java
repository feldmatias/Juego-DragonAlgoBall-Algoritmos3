package modelo.personajes;

import modelo.tablero.Tablero;

public class Freezer extends PersonajeTransformableConKi{

	public Freezer(Tablero tablero) {
		super("Freezer", 400, tablero, 20, 50);
	}

	@Override
	public int getPoderPeleaModoNormal() {
		return 20;
	}

	@Override
	public int getDistanciaAtaqueModoNormal() {
		return 2;
	}

	@Override
	public int getVelocidadModoNormal() {
		return 4;
	}

	@Override
	public int getPoderPeleaModoTransformado() {
		return 40;
	}

	@Override
	public int getDistanciaAtaqueModoTransformado() {
		return 3;
	}

	@Override
	public int getVelocidadModoTransformado() {
		return 4;
	}

	@Override
	public int getPoderPeleaModoFinal() {
		return 50;
	}

	@Override
	public int getDistanciaAtaqueModoFinal() {
		return 3;
	}

	@Override
	public int getVelocidadModoFinal() {
		return 6;
	}

}
