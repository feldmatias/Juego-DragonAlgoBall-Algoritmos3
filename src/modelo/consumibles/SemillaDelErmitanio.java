package modelo.consumibles;

import modelo.personajes.Personaje;

public class SemillaDelErmitanio extends Efecto{

	public static final int regeneracionDeVida=100;
	
	private int aumentoDeVida;
	
	public SemillaDelErmitanio() {
		super(0, "Semilla Ermitanio");
		this.aumentoDeVida = 100;
	}
	
	@Override
	public void aplicarEfectoInstantaneo(Personaje personaje){
		personaje.regenerarVida( this.aumentoDeVida );
	}

}
