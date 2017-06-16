package modelo.consumibles;

import modelo.personajes.Personaje;

public class EsferaDeDragon extends Efecto{

	public static final double aumentoDanio = 0.25;
	public static final int duracionAtaques = 2;
	
	public EsferaDeDragon() {
		super(duracionAtaques, "Esfera Dragon");
	}

	@Override
	public double modificarPoderPelea(double poderPelea){
		if (this.terminoTiempo()){
			return poderPelea;
		}
		this.restarDuracion();
		return poderPelea + (poderPelea * aumentoDanio);
	}
	
	@Override
	public void aplicarEfectoInstantaneo(Personaje personaje){
		personaje.agregarEsferaAColeccion();
	}
	
}
