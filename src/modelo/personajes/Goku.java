package modelo.personajes;


import modelo.personajes.modos.Modo;
import modelo.personajes.modos.ModoFinal;
import modelo.personajes.modos.ModoNormal;
import modelo.personajes.modos.ModoTransformado;
import modelo.tablero.Tablero;

public class Goku extends Personaje{
	
	
	public Goku(Tablero tablero){
		super("Goku", 500, new ModoNormal(20,2,2), new AtaquePotenciador(20,1.5), tablero, 20, 50);
	}
	
	public double getPoderPelea(){
		double poderPelea = super.getPoderPelea();
		if (this.getPorcentajeVida() < 30){
			poderPelea *= 1.2;  //Aumenta 20%
		}
		return poderPelea;
	}
	
	@Override
	public Modo transformarAModoTransformado(){
		super.transformarAModoTransformadoConKi();
		return new ModoTransformado(40,4,3);
	}
	
	@Override
	public Modo transformarAModoFinal() {
		super.transformarAModoFinalConKi();
		return new ModoFinal(60,4,5);
	}

}
