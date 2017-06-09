package modelo.consumibles;

import modelo.personajes.Personaje;

public class SemillaDelErmitanio extends Efecto{

	private int aumentoDeVida;
	
	public SemillaDelErmitanio() {
		super(0);
		this.aumentoDeVida = 100;
		// TODO Auto-generated constructor stub
	}
	@Override
	public void aplicarEfectoInstantaneo(Personaje personaje){
		personaje.regenerarVida( this.aumentoDeVida );
	}

}
