package modelo.personajes;

import modelo.juego.Tablero;
import modelo.personajes.modos.Modo;
import modelo.personajes.modos.ModoNormal;
import modelo.personajes.modos.PrimeraTransformacion;
import modelo.personajes.modos.SegundaTransformacion;

public class Freezer extends Personaje{
	
	public static final int vidaInicial = 400;
	public static final int poderPeleaNormal = 20;
	public static final int poderPeleaPrimerTransformacion = 40;
	public static final int poderPeleaSegundaTransformacion = 50;
	public static final int distanciaAtaqueNormal=2;
	public static final int distanciaAtaquePrimerTransformacion=3;
	public static final int distanciaAtaqueSegundaTransformacion=3;
	public static final int velocidadNormal = 4;
	public static final int velocidadPrimerTranformacion = 4;
	public static final int velocidadSegundaTranformacion = 6;

	public Freezer(Tablero tablero) {
		super(vidaInicial, new ModoNormal(poderPeleaNormal,distanciaAtaqueNormal,velocidadNormal, "Freezer"), new AtaquePotenciador(20,1.5), tablero, 20, 50);
	}
	
	@Override
	public Modo realizarPrimeraTransformacion(){
		super.restarKiPrimeraTransformacion();
		return new PrimeraTransformacion(poderPeleaPrimerTransformacion,distanciaAtaquePrimerTransformacion,velocidadPrimerTranformacion, "Freezer Segunda Forma");
	}
	
	@Override
	public Modo realizarSegundaTransformacion() {
		super.restarKiSegundaTransformacion();
		return new SegundaTransformacion(poderPeleaSegundaTransformacion,distanciaAtaqueSegundaTransformacion,velocidadSegundaTranformacion, "Freezer Definitivo");
	}

}
