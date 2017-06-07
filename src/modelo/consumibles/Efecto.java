package modelo.consumibles;

import modelo.personajes.Personaje;

public abstract class Efecto {
	private int duracion;
	private int costoKi;
	
	public Efecto (int duracion, int costoKi){
		this.duracion = duracion;
		this.costoKi = costoKi;
	}
	public void regenerarVida(Personaje personaje){
		return;
	}
	public void modificarVelocidad(Personaje personaje){
		return;
	}
	public void modificarPoderPelea(Personaje personaje){
		return;
	}
	public boolean permiteAcciones(){ //para inutilizar
		return true;
	}


}
