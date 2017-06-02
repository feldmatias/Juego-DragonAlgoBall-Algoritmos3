package modelo.personajes.modos;

public abstract class Modo {
	
	private int poderPelea;
	private int distanciaAtaque;
	private int velocidad;
	private Modo modoSiguiente;
	
	public Modo (int poderPelea, int distanciaAtaque, int velocidad, Modo modoSiguiente){
		this. poderPelea = poderPelea;
		this.distanciaAtaque = distanciaAtaque;
		this.velocidad = velocidad;
		this.modoSiguiente = modoSiguiente;
	}

	public int obtenerPoderPelea() {
		return this.poderPelea;
	}

	public int obtenerDistanciaAtaque() {
		return this.distanciaAtaque;
	}
	
	public int obtenerVelocidad() {
		return this.velocidad;
	}

	public Modo obtenerTransformacion() {
		return this.modoSiguiente;
	}

	public abstract boolean puedeTransformar();
	
}
