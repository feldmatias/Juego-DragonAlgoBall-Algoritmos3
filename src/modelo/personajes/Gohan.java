package modelo.personajes;

import modelo.juego.Tablero;
import modelo.personajes.modos.Modo;
import modelo.personajes.modos.ModoNormal;
import modelo.personajes.modos.PrimeraTransformacion;
import modelo.personajes.modos.SegundaTransformacion;

public class Gohan extends Personaje{
	
	public static final int poderPeleaNormal = 15;
	public static final int poderPeleaFase1 = 30;
	public static final int poderPeleaFase2 = 100;
	public static final int distanciaAtaqueNormal=2;
	public static final int distanciaAtaqueFase1=2;
	public static final int distanciaAtaqueFase2=4;
	public static final int velocidadNormal = 2;
	public static final int velocidadFase1 = 2;
	public static final int velocidadFase2 = 3;
	
	public Gohan(Tablero tablero) {
		super(300, new ModoNormal(poderPeleaNormal,distanciaAtaqueNormal,velocidadNormal, "Gohan"), new AtaquePotenciador(10,1.25), tablero, 10, 30);
	}

	@Override
	public Modo realizarPrimeraTransformacion(){
		super.restarKiPrimeraTransformacion();
		return new PrimeraTransformacion(poderPeleaFase1,distanciaAtaqueFase1,velocidadFase1, "Gohan Super Sayajin 1");
	}
	
	@Override
	public boolean puedeRealizarSegundaTransformacion() {
		boolean puedeTransformarse = super.puedeRealizarSegundaTransformacion();
		
		for (Personaje personaje: this.getEquipo().getMiembros()){
			if (personaje == this){
				continue;
			}
			puedeTransformarse = puedeTransformarse && (personaje.getPorcentajeVida() < 30);
		}
		return puedeTransformarse;
	}

	@Override
	public Modo realizarSegundaTransformacion() {
		super.restarKiSegundaTransformacion();
		return new SegundaTransformacion(poderPeleaFase2,distanciaAtaqueFase2,velocidadFase2, "Gohan Super Sayajin 2");
	}
}
