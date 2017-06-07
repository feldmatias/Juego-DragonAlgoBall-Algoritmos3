package modelo.cambios;

import modelo.tablero.Tablero;

public class Goku2 extends Personaje2 {
	private Estado estadoActual ;

	public Goku2(Tablero tablero) {
		super("Goku", 500, tablero);
		this.estadoActual = new Estado(20,2,2,20);
//		Estado estadoInicial = new Estado(20,2,2,20);
//		super.setEstadoInicial(estadoInicial);

	}
	@Override
	public int getPoderPelea(){
		int poderPelea = this.estadoActual.getPoderPelea();
		if (this.getPorcentajeVida() < 30){
			poderPelea *= 1.2;  //Aumenta 20%
		}
		return poderPelea;
	}
	
	

}
