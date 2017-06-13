package modelo.personajes;

import modelo.juego.Tablero;
import modelo.personajes.modos.Modo;
import modelo.personajes.modos.ModoNormal;
import modelo.personajes.modos.PrimeraTransformacion;
import modelo.personajes.modos.SegundaTransformacion;

public class Piccolo extends Personaje {

	public Piccolo(Tablero tablero) {
		super(500, new ModoNormal(20,2,2, "Piccolo"), new AtaquePotenciador(10,1.25), tablero, 20 ,0);
	}
	
	public Modo realizarPrimeraTransformacion(){
		super.restarKiPrimeraTransformacion();
		return new PrimeraTransformacion(40,4,3, "Piccolo Fortalecido");
	}

	@Override
	public Modo realizarSegundaTransformacion() {
		super.restarKiSegundaTransformacion();
		return new SegundaTransformacion(60,6,4, "Piccolo Protector");
	}
	
	@Override
	public boolean puedeRealizarSegundaTransformacion() {

		for (Personaje personaje: this.getEquipo().getMiembros()){
			if (personaje.getNombre() == "Gohan"){
				return personaje.getPorcentajeVida() < 20;
			}
		}
		return true; //Gohan esta muerto
	}

}
