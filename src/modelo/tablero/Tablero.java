package modelo.tablero;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import modelo.personajes.Personaje;

public class Tablero {
	private Casillero [][] tablero;
	private int size;
	private Hashtable<Personaje,Casillero> casillerosOcupados;
	
	public Tablero(int size){
		this.size = size;
		this.tablero = new Casillero[size][size];
		this.cargarCasilleros();
	}

	private void cargarCasilleros(){
		Casillero casillero;
		for(int x=0 ; x < this.size ; x++){
			for(int y=0 ; y < this.size ; y++){
				casillero= new Casillero(x,y);
				this.tablero[x][y] = casillero;
			}
		}
	}
	
	public Casillero getCasillero(Posicion pos){
		return this.tablero[pos.getX()][pos.getY()];
	}
	
	public void posicionarPersonaje(Personaje personaje, Casillero casillero){
		casillero.ocupar(personaje);
		this.casillerosOcupados.put(personaje, casillero);
	}
	
	public void reposicionarPersonaje(Personaje personaje, Casillero casilleroDestino) {
		this.getCasilleroPersonaje(personaje).desocupar();
		this.posicionarPersonaje(personaje, casilleroDestino);
		
	}
	

	public boolean existeCamino(Casillero actual,Casillero destino, int velocidad) {
		
		if (actual == destino){
			return true;
		}
		if ( velocidad == 0 || (!actual.estaVacio()) ){
			return false;
		}
		List<Casillero> adyacentes= this.adyacentesA(actual);
		for	(Casillero casillero: adyacentes){
			if (this.existeCamino(casillero , destino , velocidad-1)){
				return true;
			}
		}
		return false;
	}
	
	private boolean posicionEnRango(Posicion posicion){
		return posicion.esValida(this.size);
	}
	
	public List<Casillero> adyacentesA(Casillero origen){
		List<Casillero> listaCasilleros = new ArrayList<Casillero>();
		Posicion posOrigen = origen.getPosicion();
		Posicion posActual;
		
		for(int x = -1; x < 2 ; x++ ){
			for(int y = -1 ; y< 2 ; y++){
				posActual = posOrigen.sumarPosicion(new Posicion (x, y));
				
				if ( !this.posicionEnRango(posActual) ) continue;
				
				Casillero casillero =  this.getCasillero(posActual);
				
				if (casillero == origen) continue;
			
				listaCasilleros.add(casillero);
			}
		}
		return listaCasilleros;
	}


	public int distanciaEntre(Personaje personaje, Personaje enemigo) {
		Posicion pos1 = this.getCasilleroPersonaje(personaje).getPosicion();
		Posicion pos2 = this.getCasilleroPersonaje(enemigo).getPosicion();
		return pos1.distanciaA(pos2);
	}

	public boolean personajePuedeMoverse(Personaje personaje, Casillero nuevoCasillero) {
		return this.existeCamino(this.casillerosOcupados.get(personaje), nuevoCasillero, personaje.getVelocidad());
	}
	
	public Casillero getCasilleroPersonaje(Personaje personaje){
		return this.casillerosOcupados.get(personaje);
	}
	
	
}
