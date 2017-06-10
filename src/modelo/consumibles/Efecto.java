package modelo.consumibles;

import modelo.personajes.Personaje;

public abstract class Efecto {
	
	private int duracion;
	
	public Efecto (int duracion){
		this.duracion = duracion;
	}

	public int modificarVelocidad(int velocidad){
		return velocidad;
	}
	
	public double modificarPoderPelea(double poderPelea){
		return poderPelea;
	}
	
	public void aplicarEfectoInstantaneo(Personaje personaje){
		return;
	}
	
	public void restarDuracion(){
		this.duracion -= 1;
	}
	
	public void empezarTurno(){
		return;
	}
	
	public boolean terminoTiempo(){
		return this.duracion == 0 ;
	}

}
