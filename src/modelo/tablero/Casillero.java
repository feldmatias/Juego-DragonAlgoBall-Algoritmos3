package modelo.tablero;

import modelo.personajes.Personaje;

public class Casillero {
	private Posicion posicion;
	private Personaje ficha;
	

	public Casillero(int x, int y) {
		this.posicion = new Posicion(x,y);
		this.ficha = null;
	}

	public double distanciaA(Casillero otroCasillero) {
		return this.posicion.distanciaA(otroCasillero.getPosicion());
	}
	
	public Posicion getPosicion(){
		return this.posicion;
	}

	public void desocupar() {
		this.ficha = null;
	}

	public void ocupar(Personaje personaje) {
		this.ficha = personaje;
	}

	public boolean estaVacio() {
		return (this.ficha == null );
	}

}
