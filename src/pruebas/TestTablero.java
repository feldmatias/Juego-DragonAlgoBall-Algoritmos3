package pruebas;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import modelo.excepciones.PosicionFueraDeRango;
import modelo.personajes.Cell;
import modelo.personajes.Goku;
import modelo.personajes.Personaje;
import modelo.tablero.Casillero;
import modelo.tablero.Posicion;
import modelo.tablero.Tablero;

public class TestTablero {
	private int sizeTablero;
	private Tablero tablero;

	@Before
	public void setUp() throws Exception {
		sizeTablero = 6;
		tablero = new Tablero(6);
		
		
	}
	
	@Test
	public void alCrearTableroTodosLosCasillerosEstanVacios(){
		Posicion pos;
		Casillero casillero;
		boolean estaVacio = true;
		
		for(int x=0 ; x < this.sizeTablero ; x++){
			for(int y=0 ; y < this.sizeTablero ; y++){
				pos= new Posicion(x,y);
				casillero = this.tablero.getCasillero(pos);
				 if(!casillero.estaVacio()){
					 estaVacio = false;
				 }
			}
		}
		Assert.assertTrue(estaVacio);
	}
	
	@Test
	public void getCasilleroSegunPosicionDevuelveElCasilleroConLaMismaPosicion() throws PosicionFueraDeRango {
		Posicion posBuscada = new Posicion(2,2);
		Casillero casillero = tablero.getCasillero(posBuscada);
		Posicion posCasillero = casillero.getPosicion();
		
		Assert.assertTrue( posBuscada.equals(posCasillero) );	
	}
	
	@Test
	public void getCasilleroDeUnaPosicionFueraDeRangoLevantaExcepcion(){
		Posicion pos = new Posicion(-2,-2);
		try{
			this.tablero.getCasillero(pos);
			Assert.fail("La prueba fallo, deberia haber levantado la excepcion");
		}
		catch(PosicionFueraDeRango e){
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void alPosicionarPersonajeEnTableroElCasilleroNoEstaVacio(){
		Personaje personaje = new Goku(this.tablero);
		Posicion pos = new Posicion(1,1);
		Casillero casillero = this.tablero.getCasillero(pos);
		this.tablero.posicionarPersonaje(personaje, pos);
		
		Assert.assertFalse( casillero.estaVacio() );	
	}
	
	@Test
	public void posicionarPersonajeEnPosicionFueraDelTableroLevantaExcepcion(){
		Posicion posFueraDeRango = new Posicion (-1,0);
		Personaje personaje = new Goku(this.tablero);
		try{
			this.tablero.posicionarPersonaje(personaje, posFueraDeRango);
			Assert.fail("La prueba fallo. deberia haber levantado excepcion");
		}catch(PosicionFueraDeRango e){
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void alReposicionarPersonajeCasilleroDeOrigenCambiaDeOcupadoAVacio(){
		Personaje personaje = new Goku(this.tablero);
		Posicion posInicial = new Posicion(0,0);
		Posicion posFinal = new Posicion (5,5);
		this.tablero.posicionarPersonaje(personaje, posInicial);
		Assert.assertFalse( (this.tablero.getCasillero(posInicial)).estaVacio() );
		
		this.tablero.reposicionarPersonaje(personaje, posFinal);
		Assert.assertTrue( this.tablero.getCasillero(posInicial).estaVacio() );	
	}
	
	@Test
	public void alReposicionarPersonajeCasilleroDestinoCambiaDeVacioAOcupado(){
		Personaje personaje = new Goku(this.tablero);
		Posicion posInicial = new Posicion(0,0);
		Posicion posFinal = new Posicion (5,5);
		this.tablero.posicionarPersonaje(personaje, posInicial);
		Assert.assertTrue( (this.tablero.getCasillero(posFinal)).estaVacio() );
		
		this.tablero.reposicionarPersonaje(personaje, posFinal);
		Assert.assertFalse( this.tablero.getCasillero(posFinal).estaVacio() );	
	}
	
	@Test
	public void getPosicionDePersonajeDevuelveLaPosicionCorrecta(){
		Personaje personaje = new Goku(this.tablero);
		Posicion posEsperada = new Posicion(0,1);
		this.tablero.posicionarPersonaje(personaje, posEsperada);
		Posicion posPersonaje = this.tablero.getPosicionPersonaje(personaje);
		Assert.assertTrue( posPersonaje.equals(posEsperada));
	}
	
	@Test
	public void getPosicionPersonajeDevuelveLaPosicionCorrectaConMasDeUnPersonaje(){
		Personaje personaje1 = new Goku (this.tablero); 
		Personaje personaje2 =  new Cell (this.tablero);
		Posicion pos1 = new Posicion(1,2);
		Posicion pos2 =	new Posicion(3,0);
		this.tablero.posicionarPersonaje(personaje1, pos1);
		this.tablero.posicionarPersonaje(personaje2, pos2);
		
		Assert.assertTrue( pos1.equals( (this.tablero.getPosicionPersonaje(personaje1) )) );
		Assert.assertTrue( pos2.equals( (this.tablero.getPosicionPersonaje(personaje2) )) );	
	}
	
	@Test
	public void distanciaEntreDosPersonajesDevuelveLaDistanciaCorrecta(){
		Personaje personaje1 = new Goku (this.tablero); 
		Personaje personaje2 =  new Cell (this.tablero);
		Posicion pos1 = new Posicion(1,2);
		Posicion pos2 =	new Posicion(3,0);
		int distanciaEsperada = 2;
		this.tablero.posicionarPersonaje(personaje1, pos1);
		this.tablero.posicionarPersonaje(personaje2, pos2);
		
		Assert.assertEquals( this.tablero.distanciaEntre(personaje1, personaje2), distanciaEsperada );
	}
	
	@Test
	public void personajePuedeMoverseAUnaPosicionFueraDeRangoDevuelveFalse(){
		Posicion posInicio = new Posicion(0,0);
		Posicion posFueraDeRango = new Posicion(-1,1);
		Personaje personaje = new Goku(this.tablero);
		this.tablero.posicionarPersonaje(personaje, posInicio);
		
		Assert.assertFalse( this.tablero.personajePuedeMoverse(personaje, posFueraDeRango) );
	}
	
	@Test
	public void personajePuedeMoverseEnDireccionRectaDevuelveTrueSiLeAlcanzaLaVelocidad(){
		Personaje personaje = new Goku(this.tablero); //velocidad 2
		Posicion posInicial = new Posicion(0,0);
		Posicion posDestino =  new Posicion(0,2); //distancia 2
		this.tablero.posicionarPersonaje(personaje, posInicial);
		
		Assert.assertTrue( this.tablero.personajePuedeMoverse(personaje, posDestino) );
	}
	
	@Test
	public void personajePuedeMoverseEnDireccionRectaDevuelveFalseSiNoLeAlcanzaLaVelocidad(){
		Personaje personaje = new Goku(this.tablero); //velocidad 2
		Posicion posInicial = new Posicion(0,0); 
		Posicion posDestino = new Posicion(0,3); //distancia 3
		this.tablero.posicionarPersonaje(personaje, posInicial);
		
		Assert.assertTrue( this.tablero.personajePuedeMoverse(personaje, posDestino) );
	}
	
}
