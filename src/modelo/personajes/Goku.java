package modelo.personajes;


import modelo.juego.Tablero;
import modelo.personajes.modos.Modo;
import modelo.personajes.modos.ModoNormal;
import modelo.personajes.modos.PrimeraTransformacion;
import modelo.personajes.modos.SegundaTransformacion;

public class Goku extends Personaje{

	
	public static final int vidaInicial = 500;
	public static final int poderPeleaNormal = 20;
	public static final int poderPeleaPrimerTransformacion = 40;
	public static final int poderPeleaSegundaTransformacion = 60;
	public static final int distanciaAtaqueNormal=2;
	public static final int distanciaAtaquePrimerTransformacion=4;
	public static final int distanciaAtaqueSegundaTransformacion=4;
	public static final int velocidadNormal = 2;
	public static final int velocidadPrimerTranformacion = 3;
	public static final int velocidadSegundaTranformacion = 5;
	public static final int kiNecesarioPrimerTransformacion = 20;
	public static final int kiNecesarioSegundaTransformacion = 50;
	public static final double multiplicadorPocaVida = 1.2;
	public static final int kiNecesarioAtaqueEspecial = 20;
	public static final double multiplicadorAtaqueEspecial = 1.5;
	
	public Goku(Tablero tablero){
		super(vidaInicial, new ModoNormal(poderPeleaNormal,distanciaAtaqueNormal,velocidadNormal,"Goku"),
				new AtaquePotenciador(kiNecesarioAtaqueEspecial,multiplicadorAtaqueEspecial), 
				tablero, kiNecesarioPrimerTransformacion, kiNecesarioSegundaTransformacion);
	}
	
	public double getPoderPelea(){
		double poderPelea = super.getPoderPelea();
		if (this.getPorcentajeVida() < 30){
			poderPelea *= multiplicadorPocaVida;
		}
		return poderPelea;
	}
	
	@Override
	public Modo realizarPrimeraTransformacion(){
		super.restarKiPrimeraTransformacion();
		return new PrimeraTransformacion(poderPeleaPrimerTransformacion,distanciaAtaquePrimerTransformacion,velocidadPrimerTranformacion, "Goku KaioKen");
	}
	
	@Override
	public Modo realizarSegundaTransformacion() {
		super.restarKiSegundaTransformacion();
		return new SegundaTransformacion(poderPeleaSegundaTransformacion,distanciaAtaqueSegundaTransformacion,velocidadSegundaTranformacion, "Goku Super Sayajin");
	}

}
