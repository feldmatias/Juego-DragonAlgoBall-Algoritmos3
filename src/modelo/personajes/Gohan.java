package modelo.personajes;

import modelo.juego.Tablero;
import modelo.personajes.modos.Modo;
import modelo.personajes.modos.ModoNormal;
import modelo.personajes.modos.PrimeraTransformacion;
import modelo.personajes.modos.SegundaTransformacion;

public class Gohan extends Personaje{
	
	public static final int vidaInicial = 300;
	public static final int poderPeleaNormal = 15;
	public static final int poderPeleaPrimerTransformacion = 30;
	public static final int poderPeleaSegundaTransformacion = 100;
	public static final int distanciaAtaqueNormal=2;
	public static final int distanciaAtaquePrimerTransformacion=2;
	public static final int distanciaAtaqueSegundaTransformacion=4;
	public static final int velocidadNormal = 2;
	public static final int velocidadPrimerTransformacion = 2;
	public static final int velocidadSegundaTransformacion = 3;
	public static final int porcentajeVidaCompanierosParaSegundaTransformacion = 30;
	
	public Gohan(Tablero tablero) {
		super(vidaInicial, new ModoNormal(poderPeleaNormal,distanciaAtaqueNormal,velocidadNormal, "Gohan"), new AtaquePotenciador(10,1.25), tablero, 10, 30);
	}

	@Override
	public Modo realizarPrimeraTransformacion(){
		super.restarKiPrimeraTransformacion();
		return new PrimeraTransformacion(poderPeleaPrimerTransformacion,distanciaAtaquePrimerTransformacion,velocidadPrimerTransformacion, "Gohan Super Sayajin 1");
	}
	
	@Override
	public boolean puedeRealizarSegundaTransformacion() {
		boolean puedeTransformarse = super.puedeRealizarSegundaTransformacion();
		
		for (Personaje personaje: this.getEquipo().getMiembros()){
			if (personaje == this){
				continue;
			}
			puedeTransformarse = puedeTransformarse && (personaje.getPorcentajeVida() < porcentajeVidaCompanierosParaSegundaTransformacion);
		}
		return puedeTransformarse;
	}

	@Override
	public Modo realizarSegundaTransformacion() {
		super.restarKiSegundaTransformacion();
		return new SegundaTransformacion(poderPeleaSegundaTransformacion,distanciaAtaqueSegundaTransformacion,velocidadSegundaTransformacion, "Gohan Super Sayajin 2");
	}
}
