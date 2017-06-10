package modelo.juego;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.consumibles.Efecto;
import modelo.excepciones.PosicionFueraDeRango;
import modelo.personajes.Personaje;

public class Tablero {
	private Casillero [][] tablero;
	private int size;
	private Map<Personaje,Casillero> casillerosOcupados;
	private List<Efecto> consumibles;
	
	public Tablero(int size){
		this.size = size;
		this.tablero = new Casillero[size][size];
		this.cargarCasilleros();
		this.casillerosOcupados = new HashMap<Personaje,Casillero>();
	}
	
	public void setListadoConsumibles(List<Efecto> listadoConsumibles){
		this.consumibles = listadoConsumibles;
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
	
	public Casillero getCasillero(Posicion pos) throws PosicionFueraDeRango{
		if ( !this.posicionEnRango(pos)){
			throw new PosicionFueraDeRango();
		}
		return this.tablero[pos.getX()][pos.getY()];
	}
	
	public void posicionarPersonaje(Personaje personaje, Posicion posicion) throws PosicionFueraDeRango{
			Casillero casillero = this.getCasillero(posicion);
			casillero.ocupar(personaje);
			this.casillerosOcupados.put(personaje, casillero);
		
	}
	
	public void reposicionarPersonaje(Personaje personaje, Posicion nuevaPosicion) throws PosicionFueraDeRango{
	
		Casillero casilleroActual = this.getCasillero(this.getPosicionPersonaje(personaje));
		casilleroActual.desocupar();
		this.posicionarPersonaje(personaje, nuevaPosicion);
		
	}
	

	private boolean existeCamino(Casillero actual,Casillero origen, Casillero destino, int velocidad) throws PosicionFueraDeRango {
		
		if (actual == destino){
			return true;
		}
		if ( velocidad == 0 || (actual != origen && !actual.estaVacio()) ){
			return false;
		}
		List<Casillero> adyacentes = this.adyacentesA(actual);
		for	(Casillero casillero: adyacentes){
			if (this.existeCamino(casillero , origen, destino , velocidad-1)){
				return true;
			}
		}
		return false;
	}
		
	
	private boolean posicionEnRango(Posicion posicion){
		return posicion.esValida(this.size);
	}
	
	private List<Casillero> adyacentesA(Casillero origen) throws PosicionFueraDeRango{
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
		Posicion pos1 = this.getPosicionPersonaje(personaje);
		Posicion pos2 = this.getPosicionPersonaje(enemigo);
		return pos1.distanciaA(pos2);
	}

	public boolean personajePuedeMoverse(Personaje personaje, Posicion nuevaPosicion){
		Casillero destino;
		Casillero origen = this.casillerosOcupados.get(personaje);
		try{
			destino = this.getCasillero(nuevaPosicion);
			return destino.estaVacio() && this.existeCamino(origen, origen, destino, personaje.getVelocidad());			
		}catch(PosicionFueraDeRango e){
			return false;
		}
	}

	public Posicion getPosicionPersonaje(Personaje personaje){
		return this.casillerosOcupados.get(personaje).getPosicion();
	}

	public void posicionarPersonajes(List<Personaje> personajes, Posicion posCentral) {
		
		List<Posicion> listadoPosiciones = new ArrayList<Posicion>();
		listadoPosiciones.add(posCentral);
		int cantPorLado = (personajes.size()) /2;
		
		for(int i=1; i <= cantPorLado; i++){
			Posicion posDerecha = posCentral.sumarPosicion( new Posicion (0, i) );
			Posicion posIzquierda = posCentral.sumarPosicion( new Posicion (0,-i) );
			listadoPosiciones.add(posDerecha);
			listadoPosiciones.add(posIzquierda);
		}
		
		for(int j=0; j < personajes.size() ; j++ ){
			Personaje personaje = personajes.get(j);
			Posicion posicion = listadoPosiciones.get(j);
			this.posicionarPersonaje(personaje, posicion);
		}
		
	}

	public void personajeMuerto(Personaje personaje) {
		Posicion pos = this.getPosicionPersonaje(personaje);
		Casillero casillero = this.getCasillero(pos);
		casillero.desocupar();
		this.casillerosOcupados.remove(personaje);
	}

	public void generarConsumibles() {
		//Genera un solo consumible en una posicion al azar con una probabilidad de 50%
		Collections.shuffle(consumibles);
		
		double probabilidad = Math.random() * 10;
		if (probabilidad >= 5){	
			int x = (int) (Math.random() * (this.size - 1));
			int y = (int) (Math.random() * (this.size - 1));
			Posicion pos = new Posicion (x,y); //Posicion al azar
			this.getCasillero(pos).agregarConsumible(consumibles.get(0));
		}
	}
	
}
