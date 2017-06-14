package modelo.personajes;

import modelo.juego.Tablero;
import modelo.personajes.modos.Modo;
import modelo.personajes.modos.ModoNormal;
import modelo.personajes.modos.PrimeraTransformacion;
import modelo.personajes.modos.SegundaTransformacion;

public class MajinBoo extends Personaje {
	
	public static final int vidaInicial = 300;
	public static final int poderPeleaNormal = 30;
	public static final int poderPeleaBooMalo = 50;
	public static final int poderPeleaBooOriginal = 60;
	public static final int distanciaAtaqueNormal=2;
	public static final int distanciaAtaqueBooMalo=2;
	public static final int distanciaAtaqueBooOriginal=3;
	public static final int velocidadNormal = 2;
	public static final int velocidadPrimerTranformacion = 3;
	public static final int velocidadSegundaTranformacion = 4;

	public MajinBoo(Tablero tablero) {
		super(vidaInicial, new ModoNormal(poderPeleaNormal,distanciaAtaqueNormal,velocidadNormal, "Majin Boo"), new AtaqueInmovilizador(30), tablero, 20, 50);
	}

	@Override
	public Modo realizarPrimeraTransformacion(){
		super.restarKiPrimeraTransformacion();
		return new PrimeraTransformacion(poderPeleaBooMalo,distanciaAtaqueBooMalo,velocidadPrimerTranformacion, "Boo Malo");
	}
	
	@Override
	public Modo realizarSegundaTransformacion() {
		super.restarKiSegundaTransformacion();
		return new SegundaTransformacion(poderPeleaBooOriginal,distanciaAtaqueBooOriginal,velocidadSegundaTranformacion, "Boo Original");
	}

	
}
