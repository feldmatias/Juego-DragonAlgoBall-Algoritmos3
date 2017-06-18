package modelo.juego;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.consumibles.Efecto;
import modelo.consumibles.EsferaDeDragon;
import modelo.consumibles.NubeVoladora;
import modelo.consumibles.SemillaDelErmitanio;
import modelo.excepciones.MovimientoNoPosible;
import modelo.excepciones.PosicionFueraDeRango;
import modelo.personajes.Personaje;

public class Tablero {
	private Casillero [][] tablero;
	private int size;
	private Map<Personaje,Casillero> casillerosOcupados;
	
	public Tablero(int size){
		this.size = size;
		this.tablero = new Casillero[size][size];
		this.crearCasilleros();
		this.casillerosOcupados = new HashMap<Personaje,Casillero>();
	}


	private void crearCasilleros(){
		Casillero casillero;
		for(int x=0 ; x < this.size ; x++){
			for(int y=0 ; y < this.size ; y++){
				casillero= new Casillero(x,y);
				this.tablero[x][y] = casillero;
			}
		}
	}
	
	public Casillero getCasillero(Posicion pos){
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
	
	public int distanciaEntre(Personaje personaje, Personaje enemigo) {
		Posicion pos1 = this.getPosicionPersonaje(personaje);
		Posicion pos2 = this.getPosicionPersonaje(enemigo);
		return pos1.distanciaA(pos2);
	}
	
	public Posicion getPosicionPersonaje(Personaje personaje){
		return this.casillerosOcupados.get(personaje).getPosicion();
	}

	private boolean existeCamino(Casillero actual,Casillero origen, Casillero destino, int velocidad){
		
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
	
	private List<Casillero> adyacentesA(Casillero origen){
		List<Casillero> listaCasilleros = new ArrayList<Casillero>();
		Posicion posOrigen = origen.getPosicion();
		Posicion posActual;
		
		for(int x = -1; x < 2 ; x++ ){
			for(int y = -1 ; y < 2 ; y++){
				posActual = posOrigen.sumarPosicion(new Posicion (x, y));
				
				if ( !this.posicionEnRango(posActual) ) continue;
				
				Casillero casillero =  this.getCasillero(posActual);
					
				if (casillero == origen) continue;
				
				listaCasilleros.add(casillero);
			}
		}
		return listaCasilleros;
	}


	public void posicionarPersonajes(Equipo equipo, Posicion posCentral) {
		
		Collection<Personaje> personajes = equipo.getMiembros().values();
		List<Posicion> listadoPosiciones = new ArrayList<Posicion>();
		listadoPosiciones.add(posCentral);
		int cantPorLado = (personajes.size()) /2;
		
		for(int i=1; i <= cantPorLado; i++){
			Posicion posDerecha = posCentral.sumarPosicion( new Posicion (0, i) );
			Posicion posIzquierda = posCentral.sumarPosicion( new Posicion (0,-i) );
			listadoPosiciones.add(posDerecha);
			listadoPosiciones.add(posIzquierda);
		}
		
		for(Personaje personaje: personajes){
			Posicion posicion = listadoPosiciones.get(0);
			listadoPosiciones.remove(0);
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
		//Genera un solo consumible en una posicion al azar con una probabilidad 
		int probabilidad = 30;
		List<Efecto> consumibles = this.getListaConsumibles();
		
		double random = Math.random() * 100;
		if (random <= probabilidad){	
			int x = (int) (Math.random() * (this.size - 1));
			int y = (int) (Math.random() * (this.size - 1));
			Posicion pos = new Posicion (x,y); //Posicion al azar
			try {
				this.getCasillero(pos).agregarConsumible( consumibles.get(0).getClass().newInstance());
			} catch (InstantiationException | IllegalAccessException e) {
			}
		}
	}
	
	private List<Efecto> getListaConsumibles() {
		List <Efecto> consumibles = new ArrayList<Efecto>();
		consumibles.add(new EsferaDeDragon());
		consumibles.add(new SemillaDelErmitanio());
		consumibles.add(new NubeVoladora());
		Collections.shuffle(consumibles);
		return consumibles;
	}

	public boolean tieneMovimientoPosible(Personaje personaje) {
		Casillero actual = this.getCasillero(this.getPosicionPersonaje(personaje));
		for (Casillero casillero: this.adyacentesA(actual)){
			try {
				personaje.puedeMoverse(casillero.getPosicion());
				return true;
			} catch (MovimientoNoPosible e) {
			}
		}
		return false;
	}
	
}
