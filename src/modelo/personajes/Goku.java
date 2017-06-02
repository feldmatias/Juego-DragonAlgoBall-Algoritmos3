package modelo.personajes;

import modelo.personajes.modos.modosGoku.GokuNormal;

public class Goku extends Personaje {
	
	public Goku(){
		super("Goku", 500,  new GokuNormal());
	}
	
	public int getPoderPelea(){
		int poderPelea = super.getPoderPelea();
		if (this.getPorcentajeVida() < 30){
			poderPelea *= 1.2;  //Aumenta 20%
		}
		return poderPelea;
	}

}
