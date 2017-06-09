package modelo.consumibles;

import modelo.personajes.Personaje;

public class EsferaDeDragon extends Efecto{

	private double aumentoDeDanio;
	
	public EsferaDeDragon() {
		super(2);
		this.aumentoDeDanio =  0.25;
	}

	@Override
	public double modificarPoderPelea(int poderPelea){
		this.restarDuracion();
		return poderPelea + (poderPelea*aumentoDeDanio);
	}
	
	@Override
	public void aplicarEfectoInstantaneo(Personaje personaje){
		personaje.agregarEsferaAColeccion(this);
	}
	
}
