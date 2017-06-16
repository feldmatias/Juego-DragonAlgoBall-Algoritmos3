package modelo.consumibles;

import modelo.personajes.Personaje;

public class SemillaDelErmitanio extends Efecto{

	public static final int regeneracionDeVida = 100;
	public static final int duracionTurnos = 0;
	
	public SemillaDelErmitanio() {
		super(duracionTurnos, "Semilla Ermitanio");
	}
	
	@Override
	public void aplicarEfectoInstantaneo(Personaje personaje){
		personaje.regenerarVida( regeneracionDeVida );
	}

}
