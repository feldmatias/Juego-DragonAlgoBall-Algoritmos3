package modelo.personajes;


import modelo.tablero.Tablero;

public class Goku extends PersonajeTransformableConKi {
	
	
	public Goku(Tablero tablero){
		super("Goku", 500, tablero, 20, 50);
	}
	
	public int getPoderPelea(){
		int poderPelea = super.getPoderPelea();
		if (this.getPorcentajeVida() < 30){
			poderPelea *= 1.2;  //Aumenta 20%
		}
		return poderPelea;
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
		return 4;
	}

	@Override
	public int getVelocidadModoFinal() {
		return 5;
	}

}
