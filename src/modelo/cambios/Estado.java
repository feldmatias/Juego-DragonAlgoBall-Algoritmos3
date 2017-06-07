package modelo.cambios;

import modelo.personajes.Personaje;

public class Estado {
	
	private int poderPelea;
	private int velocidad;
	private int distanciaAtaque;
	private int kiRequeridoTransformacion;
	
	
	public Estado(int poderPelea, int velocidad, int distanciaAtaque ,int kiRequeridoTransformacion){
		this.poderPelea = poderPelea;
		this.velocidad = velocidad;
		this.kiRequeridoTransformacion = kiRequeridoTransformacion;
		this.distanciaAtaque = distanciaAtaque;
		
	}
	
	public void setPoderPelea(int poderPelea){
		this.poderPelea = poderPelea;
	}
	public int getPoderPelea(){
		return this.poderPelea;
	}
	
	public void setVelocidad(int velocidad){
		this.velocidad = velocidad;
	}
	public int getVelocidad(){
		return this.velocidad;
	}
	
	public void setKiParaTransformacion(int kiParaTransformacion){
		this.kiRequeridoTransformacion = kiParaTransformacion;
	}
	public boolean tieneKiParaTransformacion(Personaje2 personaje){
		int kiPersonaje = personaje.getKi();
		return (kiPersonaje >= kiRequeridoTransformacion);
	}
	
	public void transformar(Personaje2 personaje){
		return personaje.realizarPrimerTransformacion();
		
	}
	public int getDistanciaAtaque(){
		return this.distanciaAtaque;
	}
	
}
