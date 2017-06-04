package pruebas;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import modelo.excepciones.PosicionFueraDeRango;
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
			Assert.fail("Deberia haber levantado la excepcion");
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
	public void alReposicionarPersonajeElNuevoCasilleroNoEstaVacio(){
		Personaje personaje = new Goku(this.tablero);
		Posicion posInicial = new Posicion(0,0);
		Posicion posFinal = new Posicion (5,5);
		this.tablero.posicionarPersonaje(personaje, posInicial);
		
		this.tablero.reposicionarPersonaje(personaje, posFinal);
		Assert.assertTrue( (this.tablero.getCasillero(posInicial)).estaVacio() );
		Assert.assertFalse( this.tablero.getCasillero(posFinal).estaVacio() );
		
	
	}
	
	
	
}
