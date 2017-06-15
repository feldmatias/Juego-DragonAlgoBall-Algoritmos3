package modelo.personajes;

import modelo.juego.Tablero;
import modelo.personajes.modos.Modo;
import modelo.personajes.modos.ModoNormal;
import modelo.personajes.modos.PrimeraTransformacion;
import modelo.personajes.modos.SegundaTransformacion;

public class Cell extends Personaje {
	
	public static final int vidaInicial = 500;
	public static final int poderPeleaNormal = 20;
	public static final int poderPeleaPrimerTransformacion = 40;
	public static final int poderPeleaSegundaTransformacion = 80;
	public static final int distanciaAtaqueNormal=3;
	public static final int distanciaAtaquePrimerTransformacion=4;
	public static final int distanciaAtaqueSegundaTransformacion=4;
	public static final int velocidadNormal = 2;
	public static final int velocidadPrimerTranformacion = 3;
	public static final int velocidadSegundaTranformacion = 4;
	public static final int cantidadAbsorcionesPrimerTranformacion=4;
	public static final int cantidadAbsorcionesSegundaTranformacion=8;
	
	public Cell( Tablero tablero) {
		super(vidaInicial, new ModoNormal(poderPeleaNormal,distanciaAtaqueNormal,velocidadNormal, "Cell"), new AtaqueAbsorver(5), tablero, 0, 0);
	}


	@Override
	public Modo realizarPrimeraTransformacion() {
		return new PrimeraTransformacion(poderPeleaPrimerTransformacion,distanciaAtaquePrimerTransformacion,velocidadPrimerTranformacion, "Cell Semi Perfecto");
	}

	@Override
	public boolean puedeRealizarPrimeraTransformacion() {
		return this.getCantidadAbsorciones() >= cantidadAbsorcionesPrimerTranformacion;
	}


	@Override
	public Modo realizarSegundaTransformacion() {
		return new SegundaTransformacion(poderPeleaSegundaTransformacion,distanciaAtaqueSegundaTransformacion,velocidadSegundaTranformacion, "Cell Perfecto");
	}

	@Override
	public boolean puedeRealizarSegundaTransformacion() {
		return this.getCantidadAbsorciones() >= cantidadAbsorcionesSegundaTranformacion;
	}
	
	private int getCantidadAbsorciones(){
		AtaqueAbsorver ataque = (AtaqueAbsorver) this.ataqueEspecial;
		return ataque.getCantidadUsos();
	}
}
