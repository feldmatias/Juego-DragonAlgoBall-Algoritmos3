package modelo.personajes;


import modelo.juego.Tablero;
import modelo.personajes.modos.Modo;
import modelo.personajes.modos.ModoNormal;
import modelo.personajes.modos.PrimeraTransformacion;
import modelo.personajes.modos.SegundaTransformacion;

public class Goku extends Personaje{

	public static final int POS_X = 5;
	public static final int POS_Y = 0;
	public static final int vidaInicial = 500;
	public static final int poderPeleaNormal = 20;
	public static final int poderPeleaKaioKen = 40;
	public static final int poderPeleaSayajin = 60;
	public static final int distanciaAtaqueNormal=2;
	public static final int distanciaAtaqueTranformado=4;
	public static final int velocidadNormal = 2;
	public static final int velocidadPrimerTranformacion = 3;
	public static final int velocidadSegundaTranformacion = 5;
	
	public Goku(Tablero tablero){
		super(vidaInicial, new ModoNormal(poderPeleaNormal,distanciaAtaqueNormal,velocidadNormal,"Goku"), new AtaquePotenciador(20,1.5), tablero, 20, 50);
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
		return new PrimeraTransformacion(poderPeleaKaioKen,distanciaAtaqueTranformado,velocidadPrimerTranformacion, "Goku KaioKen");
	}
	
	@Override
	public Modo realizarSegundaTransformacion() {
		super.restarKiSegundaTransformacion();
		return new SegundaTransformacion(poderPeleaSayajin,distanciaAtaqueTranformado,velocidadSegundaTranformacion, "Goku Super Sayajin");
	}

}
