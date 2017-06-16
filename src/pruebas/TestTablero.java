package pruebas;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import modelo.excepciones.PosicionFueraDeRango;
import modelo.juego.Casillero;
import modelo.juego.Posicion;
import modelo.juego.Tablero;
import modelo.personajes.Cell;
import modelo.personajes.Freezer;
import modelo.personajes.Gohan;
import modelo.personajes.Goku;
import modelo.personajes.Personaje;

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
		
		for(int x=0 ; x < this.sizeTablero ; x++){
			for(int y=0 ; y < this.sizeTablero ; y++){
				pos= new Posicion(x,y);
				casillero = this.tablero.getCasillero(pos);
				Assert.assertTrue(casillero.estaVacio());
			}
		}
	}
	
	@Test
	public void getCasilleroSegunPosicionDevuelveElCasilleroConLaMismaPosicion() {
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
		
		Assert.assertFalse( this.tablero.personajePuedeMoverse(personaje, posDestino) );
	}
	
	@Test 
	public void personajePuedeMoverseEnDireccionDiagonalDevuelveTrueSiLeAlcanzaLaVelocidad(){
		Personaje personaje = new Goku(this.tablero); //velocidad 2
		Posicion posInicial = new Posicion(0,0);
		Posicion posDestino =  new Posicion(2,2); //distancia 2
		this.tablero.posicionarPersonaje(personaje, posInicial);
		
		Assert.assertTrue( this.tablero.personajePuedeMoverse(personaje, posDestino) );
	}
	
	@Test
	public void personajePuedeMoverseEnDireccionRectaConCaminoBloqueadoDebeDarTrueSiAlcanzaLaVelocidad(){
		Personaje personaje = new Goku(this.tablero); //velocidad 2
		Personaje personaje2 = new Cell(this.tablero);
		Posicion posInicial = new Posicion(0,0); 
		Posicion PosBloqueada = new Posicion (0,1);
		Posicion posDestino = new Posicion(0,2); //distancia 2
		this.tablero.posicionarPersonaje(personaje, posInicial);
		this.tablero.posicionarPersonaje(personaje2, PosBloqueada);
		
		Assert.assertTrue( this.tablero.personajePuedeMoverse(personaje, posDestino) );
	}
	
	@Test
	public void personajePuedeMoverseEnDireccionDiagonalConCaminoBloqueadoDebeDarTrueSiAlcanzaLaVelocidad(){
		Personaje personaje = new Freezer(this.tablero); //velocidad 4
		Personaje personaje2 = new Goku(this.tablero);
		Posicion posInicial = new Posicion(0,0); 
		Posicion PosBloqueada = new Posicion (1,1);
		Posicion posDestino = new Posicion(2,2); //se puede llegar en 3 casilleros
		this.tablero.posicionarPersonaje(personaje, posInicial);
		this.tablero.posicionarPersonaje(personaje2, PosBloqueada);
		
		Assert.assertTrue( this.tablero.personajePuedeMoverse(personaje, posDestino) );
	}
	
	@Test
	public void personajePuedeMoverseEnDireccionDiagonalConCaminoBloqueadoDebeDarFalseSiNoAlcanzaLaVelocidad(){
		Personaje personaje = new Goku(this.tablero); //velocidad 2
		Personaje personaje2 = new Freezer(this.tablero); 
		Posicion posInicial = new Posicion(0,0); 
		Posicion PosBloqueada = new Posicion (1,1);
		Posicion posDestino = new Posicion(2,2); //se puede llegar en 3 casilleros
		this.tablero.posicionarPersonaje(personaje, posInicial);
		this.tablero.posicionarPersonaje(personaje2, PosBloqueada);
		
		Assert.assertFalse( this.tablero.personajePuedeMoverse(personaje, posDestino) );
	}
	
	@Test
	public void personajePuedeMoverseConCaminoBloqueadoDevuelveTrueCombinandoDireccionesSiLeAlcanzaLaVelocidad(){
		Personaje personaje = new Freezer(this.tablero); //velocidad 4
		Personaje personaje2 = new Goku (this.tablero); 
		Personaje personaje3 = new Cell (this.tablero); 
		Posicion PosInicial = new Posicion(0,0);
		Posicion PosBloqueada1 = new Posicion(1,1);//bloquea camino recto
		Posicion Posbloqueada2 = new Posicion (1,0);//bloquea camino diagonal
		Posicion PosicionDestino = new Posicion(3,3); // se llega con 3 casilleros combinando direcciones
		
		this.tablero.posicionarPersonaje(personaje, PosInicial);
		this.tablero.posicionarPersonaje(personaje2, PosBloqueada1);
		this.tablero.posicionarPersonaje(personaje3, Posbloqueada2);
		
		Assert.assertTrue( this.tablero.personajePuedeMoverse(personaje, PosicionDestino) );
		
	}
	
	@Test
	public void personajePuedeMoverseConCaminoBloqueadoDevuelveFalseCombinandoDireccionesSiNoAlcanzaLaVelocidad(){
		Personaje personaje = new Goku(this.tablero); //velocidad 2
		Personaje personaje2 = new Freezer (this.tablero); 
		Personaje personaje3 = new Cell (this.tablero); 
		Posicion PosInicial = new Posicion(0,0);
		Posicion PosBloqueada1 = new Posicion(1,1);//bloquea camino recto
		Posicion Posbloqueada2 = new Posicion (1,0);//bloquea camino diagonal
		Posicion PosicionDestino = new Posicion(3,3); // se llega con 3 casilleros combinando direcciones
		
		this.tablero.posicionarPersonaje(personaje, PosInicial);
		this.tablero.posicionarPersonaje(personaje2, PosBloqueada1);
		this.tablero.posicionarPersonaje(personaje3, Posbloqueada2);
		
		Assert.assertFalse( this.tablero.personajePuedeMoverse(personaje, PosicionDestino) );
		
	}
	
	@Test
	public void personajePuedeMoverseConCaminoTotalmenteBloqueadoDevuelveFalse(){
		Personaje personaje1 = new Freezer(this.tablero);
		Personaje personaje2 = new Goku(this.tablero);
		Personaje personaje3 = new Cell(this.tablero);
		Personaje personaje4 = new Gohan(this.tablero);
		
		Posicion PosInicial = new Posicion(0,0);
		Posicion PosDestino = new Posicion(0,2);
		Posicion PosBloqueada1 = new Posicion(0,1);
		Posicion PosBloqueada2 = new Posicion (1,0);
		Posicion PosBloqueada3 = new Posicion(1,1);
		
		//se encierra al personaje1
		this.tablero.posicionarPersonaje(personaje1, PosInicial);
		this.tablero.posicionarPersonaje(personaje2, PosBloqueada1);
		this.tablero.posicionarPersonaje(personaje3, PosBloqueada2);
		this.tablero.posicionarPersonaje(personaje4, PosBloqueada3);
		
		Assert.assertFalse( this.tablero.personajePuedeMoverse(personaje1, PosDestino) );
	}
	
	
	
	
}
