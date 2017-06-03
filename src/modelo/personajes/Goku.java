package modelo.personajes;

import modelo.personajes.modos.modosGoku.GokuNormal;
import modelo.tablero.Tablero;

public class Goku extends Personaje {
	
	public Goku(Tablero tablero){
		super("Goku", 500,  new GokuNormal(), tablero);
	}
	
	public int getPoderPelea(){
		int poderPelea = super.getPoderPelea();
		if (this.getPorcentajeVida() < 30){
			poderPelea *= 1.2;  //Aumenta 20%
		}
		return poderPelea;
	}

}
