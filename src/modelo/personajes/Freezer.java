package modelo.personajes;

import modelo.personajes.modos.Modo;
import modelo.personajes.modos.ModoNormal;
import modelo.personajes.modos.PrimeraTransformacion;
import modelo.personajes.modos.SegundaTransformacion;
import modelo.tablero.Tablero;

public class Freezer extends Personaje{

	public Freezer(Tablero tablero) {
		super("Freezer", 400, new ModoNormal(20,2,4), new AtaquePotenciador(20,1.5), tablero, 20, 50);
	}
	
	@Override
	public Modo realizarPrimeraTransformacion(){
		super.restarKiPrimeraTransformacion();
		return new PrimeraTransformacion(40,3,4);
	}
	
	@Override
	public Modo realizarSegundaTransformacion() {
		super.restarKiSegundaTransformacion();
		return new SegundaTransformacion(50,3,6);
	}

}
