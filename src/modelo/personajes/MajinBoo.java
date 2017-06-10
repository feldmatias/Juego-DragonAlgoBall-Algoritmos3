package modelo.personajes;

import modelo.personajes.modos.Modo;
import modelo.personajes.modos.ModoNormal;
import modelo.personajes.modos.PrimeraTransformacion;
import modelo.personajes.modos.SegundaTransformacion;
import modelo.tablero.Tablero;

public class MajinBoo extends Personaje {

	public MajinBoo(Tablero tablero) {
		super("Majin Boo", 300, new ModoNormal(30,2,2), new AtaqueInmovilizador(30), tablero, 20, 50);
	}

	@Override
	public Modo realizarPrimeraTransformacion(){
		super.restarKiPrimeraTransformacion();
		return new PrimeraTransformacion(50,2,3);
	}
	
	@Override
	public Modo realizarSegundaTransformacion() {
		super.restarKiSegundaTransformacion();
		return new SegundaTransformacion(60,3,4);
	}

	
}
