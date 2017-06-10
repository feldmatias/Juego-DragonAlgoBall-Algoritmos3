package modelo.tablero;

import modelo.consumibles.Efecto;
import modelo.personajes.Personaje;

public class Casillero {
	private Posicion posicion;
	private Personaje personaje;
	private Efecto consumible;
	

	public Casillero(int x, int y) {
		this.posicion = new Posicion(x,y);
		this.personaje = null;
		this.consumible = null;
	}

	public int distanciaA(Casillero otroCasillero) {
		return this.posicion.distanciaA(otroCasillero.getPosicion());
	}
	
	public Posicion getPosicion(){
		return this.posicion;
	}

	public void desocupar() {
		this.personaje = null;
	}

	public void ocupar(Personaje personaje) {
		if (this.consumible != null){
			personaje.sumarEfecto(this.consumible);
			this.consumible = null;
		}
		this.personaje = personaje;
	}

	public boolean estaVacio() {
		return (this.personaje == null );
	}
	
	public void agregarConsumible(Efecto consumible){
		if (this.estaVacio()){
			this.consumible = consumible;
		}
	}

}
