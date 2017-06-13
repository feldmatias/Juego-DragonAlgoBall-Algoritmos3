package modelo.personajes;

import modelo.juego.Tablero;
import modelo.personajes.modos.Modo;
import modelo.personajes.modos.ModoNormal;
import modelo.personajes.modos.PrimeraTransformacion;
import modelo.personajes.modos.SegundaTransformacion;

public class Gohan extends Personaje{
	
	public Gohan(Tablero tablero) {
		super(300, new ModoNormal(15,2,2, "Gohan"), new AtaquePotenciador(10,1.25), tablero, 10, 30);
	}

	@Override
	public Modo realizarPrimeraTransformacion(){
		super.restarKiPrimeraTransformacion();
		return new PrimeraTransformacion(30,2,2, "Gohan Super Sayajin 1");
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
		return new SegundaTransformacion(100,4,3, "Gohan Super Sayajin 2");
	}
	

}
