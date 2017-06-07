package modelo.personajes;

import modelo.tablero.Tablero;

public class Piccolo extends PersonajeTransformableConKi {

	public Piccolo(Tablero tablero) {
		super("Piccolo", 500, tablero, 20 ,0);
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
		return 2;
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
	public int getPoderPeleaModoFinal() {
		return 60;
	}

	@Override
	public int getDistanciaAtaqueModoFinal() {
		return 6;
	}

	@Override
	public int getVelocidadModoFinal() {
		return 4;
	}
	
	@Override
	public boolean puedeTransformarseAModoFinal() {
		return false;
		
		//HACER   HACER  HACER
	}

}
