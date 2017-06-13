package modelo.personajes;

import modelo.juego.Tablero;
import modelo.personajes.modos.Modo;
import modelo.personajes.modos.ModoNormal;
import modelo.personajes.modos.PrimeraTransformacion;
import modelo.personajes.modos.SegundaTransformacion;

public class Cell extends Personaje {

	public Cell( Tablero tablero) {
		super(500, new ModoNormal(20,3,2, "Cell"), new AtaqueAbsorver(5), tablero, 0, 0);
	}


	@Override
	public Modo realizarPrimeraTransformacion() {
		return new PrimeraTransformacion(40,4,3, "Cell Semi Perfecto");
	}

	@Override
	public boolean puedeRealizarPrimeraTransformacion() {
		return this.getCantidadAbsorciones() >= 4;
	}


	@Override
	public Modo realizarSegundaTransformacion() {
		return new SegundaTransformacion(80,4,4, "Cell Perfecto");
	}

	@Override
	public boolean puedeRealizarSegundaTransformacion() {
		return this.getCantidadAbsorciones() >= 8;
	}
	
	private int getCantidadAbsorciones(){
		AtaqueAbsorver ataque = (AtaqueAbsorver) this.ataqueEspecial;
		return ataque.getCantidadUsos();
	}
}
