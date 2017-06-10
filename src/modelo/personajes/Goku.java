package modelo.personajes;


import modelo.juego.Tablero;
import modelo.personajes.modos.Modo;
import modelo.personajes.modos.ModoNormal;
import modelo.personajes.modos.PrimeraTransformacion;
import modelo.personajes.modos.SegundaTransformacion;

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
	public Modo realizarPrimeraTransformacion(){
		super.restarKiPrimeraTransformacion();
		return new PrimeraTransformacion(40,4,3);
	}
	
	@Override
	public Modo realizarSegundaTransformacion() {
		super.restarKiSegundaTransformacion();
		return new SegundaTransformacion(60,4,5);
	}

}
