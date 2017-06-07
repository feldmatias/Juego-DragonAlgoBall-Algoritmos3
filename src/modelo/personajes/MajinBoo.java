package modelo.personajes;

import modelo.tablero.Tablero;

public class MajinBoo extends PersonajeTransformableConKi {

	public MajinBoo(Tablero tablero) {
		super("Majin Boo", 300, tablero, 20, 50);
	}

	@Override
	public int getPoderPeleaModoNormal() {
		return 30;
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
		return 50;
	}

	@Override
	public int getDistanciaAtaqueModoTransformado() {
		return 2;
	}

	@Override
	public int getVelocidadModoTransformado() {
		return 3;
	}

	@Override
	public int getPoderPeleaModoFinal() {
		return 60;
	}

	@Override
	public int getDistanciaAtaqueModoFinal() {
		return 3;
	}

	@Override
	public int getVelocidadModoFinal() {
		return 4;
	}
	
}
