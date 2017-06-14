package modelo.personajes;

import modelo.juego.Tablero;
import modelo.personajes.modos.Modo;
import modelo.personajes.modos.ModoNormal;
import modelo.personajes.modos.PrimeraTransformacion;
import modelo.personajes.modos.SegundaTransformacion;

public class Cell extends Personaje {
	
	public static final int poderPeleaNormal = 20;
	public static final int poderPeleaSemiPerfecto = 40;
	public static final int poderPeleaPerfecto = 80;
	public static final int cantidadAbsorcionesPrimerTranformacion=4;
	public static final int cantidadAbsorcionesSegundaTranformacion=8;
	public static final int distanciaAtaqueNormal=3;
	public static final int distanciaAtaqueTranformado=4;
	public static final int velocidadNormal = 2;
	public static final int velocidadPrimerTranformacion = 3;
	public static final int velocidadSegundaTranformacion = 4;
	
	public Cell( Tablero tablero) {
		super(500, new ModoNormal(poderPeleaNormal,distanciaAtaqueNormal,velocidadNormal, "Cell"), new AtaqueAbsorver(5), tablero, 0, 0);
	}


	@Override
	public Modo realizarPrimeraTransformacion() {
		return new PrimeraTransformacion(poderPeleaSemiPerfecto,distanciaAtaqueTranformado,velocidadPrimerTranformacion, "Cell Semi Perfecto");
	}

	@Override
	public boolean puedeRealizarPrimeraTransformacion() {
		return this.getCantidadAbsorciones() >= cantidadAbsorcionesPrimerTranformacion;
	}


	@Override
	public Modo realizarSegundaTransformacion() {
		return new SegundaTransformacion(poderPeleaPerfecto,distanciaAtaqueTranformado,velocidadSegundaTranformacion, "Cell Perfecto");
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
