package modelo.consumibles;

import modelo.personajes.Personaje;

public abstract class Efecto {
	private int duracion;
	
	public Efecto (int duracion){
		this.duracion = duracion;
	}
	public int regenerarVida(){
		return 0;
	}
	public int modificarVelocidad(int velocidad){
		return velocidad;
	}
	public int modificarPoderPelea(int poderPelea){
		return poderPelea;
	}
	public boolean permiteAcciones(){ //para inutilizar
		return true;
	}


}
