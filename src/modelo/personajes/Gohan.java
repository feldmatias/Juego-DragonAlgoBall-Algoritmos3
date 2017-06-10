package modelo.personajes;

import modelo.personajes.modos.Modo;
import modelo.personajes.modos.ModoNormal;
import modelo.personajes.modos.PrimeraTransformacion;
import modelo.personajes.modos.SegundaTransformacion;
import modelo.tablero.Tablero;

public class Gohan extends Personaje{
	
	public Gohan(Tablero tablero) {
		super("Gohan", 300, new ModoNormal(15,2,2), new AtaquePotenciador(10,1.25), tablero, 10, 30);
	}

	@Override
	public Modo realizarPrimeraTransformacion(){
		super.restarKiPrimeraTransformacion();
		return new PrimeraTransformacion(30,2,2);
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
		return new SegundaTransformacion(100,4,3);
	}
	

}
