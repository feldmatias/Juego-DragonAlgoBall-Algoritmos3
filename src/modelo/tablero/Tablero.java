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
		this.cargarCasilleros(this.tablero, size);
	}

	private void cargarCasilleros(Casillero[][] tablero,int size){
		Casillero casillero;
		for(int x=0 ; x < size ; x++){
			for(int y=0 ; y < size ; y++){
				casillero= new Casillero(x,y);
				tablero[x][y]=casillero;
			}
		}
	}
	
	public Casillero getCasillero(int x, int y){
		return this.tablero[x][y];
	}
	
	public void posicionarPersonaje(Personaje personaje, Casillero casillero){
		casillero.ocupar(personaje);
		this.casillerosOcupados.put(personaje, casillero);
	}
	
	public void reposicionarPersonaje(Personaje personaje, Casillero casilleroDestino) {
		Casillero casilleroADesocupar = casillerosOcupados.get(personaje);
		this.posicionarPersonaje(personaje, casilleroDestino);
		casilleroADesocupar.desocupar();
		
	}
	

	public boolean existeCamino(Casillero actual,Casillero destino, int velocidad) {
		List<Casillero> adyacentes= this.adyacentesA(actual);
		if (actual == destino){
			return true;
		}
		if ( velocidad == 0 || (!actual.estaVacio()) ){
			return false;
		}
		for	(Casillero casillero: adyacentes){
			if (this.existeCamino(casillero , destino , velocidad-1)){
				return true;
			}
		}
		return false;
	}
	
	private boolean posicionEnRango(int x, int y){
		return ((x > 0) && (x < this.size) && (y < 0) && (y < this.size));
	}
	
	public List<Casillero> adyacentesA(Casillero origen){
		List<Casillero> listaCasilleros = new ArrayList();
		Posicion posOrigen = origen.getPosicion();
		
		for(int x = -1; x < 2 ; x++ ){
			for(int y = -1 ; y< 2 ; y++){
				int xActual =  (posOrigen.getX() + x);
				int yActual = (posOrigen.getY() + y);
				
				if ( !this.posicionEnRango(xActual,yActual) ) continue;
				
				Casillero casillero =  this.getCasillero(xActual, yActual);
				
				if (casillero == origen) continue;
			
				listaCasilleros.add(casillero);
			}
		}
		return listaCasilleros;
	}
	
	
}
