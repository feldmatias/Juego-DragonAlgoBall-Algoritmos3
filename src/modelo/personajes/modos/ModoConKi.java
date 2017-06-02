package modelo.personajes.modos;

import modelo.personajes.Personaje;

public abstract class ModoConKi extends Modo {

	private int kiNecesario;
	
	public ModoConKi(int poderPelea, int distanciaAtaque, int velocidad, int kiNecesario, Modo modoSiguiente) {
		super(poderPelea, distanciaAtaque, velocidad, modoSiguiente);
		this.kiNecesario = kiNecesario;
	}
	
	public boolean puedeTransformarse(Personaje personaje) {
		return personaje.getKi() >= this.kiNecesario;
	}
	
	public Modo transformar(Personaje personaje){
		personaje.restarKi(this.kiNecesario);
		return super.transformar(personaje);
	}


}
