package modelo.personajes;

import modelo.juego.Tablero;
import modelo.personajes.modos.Modo;
import modelo.personajes.modos.ModoNormal;
import modelo.personajes.modos.PrimeraTransformacion;
import modelo.personajes.modos.SegundaTransformacion;

public class MajinBoo extends Personaje {

	public MajinBoo(Tablero tablero) {
		super(300, new ModoNormal(30,2,2, "Majin Boo"), new AtaqueInmovilizador(30), tablero, 20, 50);
	}

	@Override
	public Modo realizarPrimeraTransformacion(){
		super.restarKiPrimeraTransformacion();
		return new PrimeraTransformacion(50,2,3, "Boo Malo");
	}
	
	@Override
	public Modo realizarSegundaTransformacion() {
		super.restarKiSegundaTransformacion();
		return new SegundaTransformacion(60,3,4, "Boo Original");
	}

	
}
