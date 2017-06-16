package pruebas;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import modelo.juego.Posicion;

public class TestPosicion {

	private Posicion posicion;
	private int sizeTableroPrueba = 10;
	
	@Before
	public void setUp() {
		this.posicion = new Posicion (5,5);
	}
	
	@Test
	public void testPosicionEsValidaDevuelveTrue(){
		Assert.assertTrue(posicion.esValida(10));
	}
	
	@Test
	public void testCalcularDistanciaHorizontalAdelante(){
		int distanciaEsperada = 3;
		Posicion destino = new Posicion (8,5);
		Assert.assertEquals(distanciaEsperada, posicion.distanciaA(destino));
	}
	
	@Test
	public void testCalcularDistanciaHorizontalAtras(){
		int distanciaEsperada = 4;
		Posicion destino = new Posicion (1,5);
		Assert.assertEquals(distanciaEsperada, posicion.distanciaA(destino));
	}
	
	@Test
	public void testCalcularDistanciaVerticalAdelante(){
		int distanciaEsperada = 1;
		Posicion destino = new Posicion (5,6);
		Assert.assertEquals(distanciaEsperada, posicion.distanciaA(destino));
	}
	
	@Test
	public void testCalcularDistanciaVerticalAtras(){
		int distanciaEsperada = 2;
		Posicion destino = new Posicion (5,3);
		Assert.assertEquals(distanciaEsperada, posicion.distanciaA(destino));
	}
	
	@Test
	public void testCalcularDistanciaDiagonal(){
		int distanciaEsperada = 2;
		Posicion destino = new Posicion (7,7);
		Assert.assertEquals(distanciaEsperada, posicion.distanciaA(destino));
	}
	
	@Test
	public void testCalcularDistanciaAOtraPosicion(){
		int distanciaEsperada = 3;
		Posicion destino = new Posicion (4,8);
		Assert.assertEquals(distanciaEsperada, posicion.distanciaA(destino));
	}
	
	@Test
	public void testSumarMismaPosicion(){
		Posicion posicionEsperada = new Posicion (10,10);
		Posicion resultado = posicion.sumarPosicion(posicion);
		Assert.assertTrue(resultado.equals(posicionEsperada));
	}
	
	@Test
	public void testSumarPosicionNula(){
		Posicion resultado = posicion.sumarPosicion(new Posicion(0,0));
		Assert.assertTrue(resultado.equals(posicion));
	}
	
	@Test
	public void testSumarCualquierPosicion(){
		Posicion posicionEsperada = new Posicion (12,7);
		Posicion resultado = posicion.sumarPosicion(new Posicion(7,2));
		Assert.assertTrue(resultado.equals(posicionEsperada));
	}
	
	@Test
	public void testPosicionXNegativoNoEsValida(){
		this.posicion = new Posicion (-1,4);
		Assert.assertFalse(posicion.esValida(sizeTableroPrueba));
	}
	
	@Test
	public void testPosicionYNegativoNoEsValida(){
		this.posicion = new Posicion (3,-5);
		Assert.assertFalse(posicion.esValida(sizeTableroPrueba));
	}
	
	@Test
	public void testPosicionXMuyAltoNoEsValida(){
		this.posicion = new Posicion (20,3);
		Assert.assertFalse(posicion.esValida(sizeTableroPrueba));
	}
	
	@Test
	public void testPosicionYMuyAltoNoEsValida(){
		this.posicion = new Posicion (5,18);
		Assert.assertFalse(posicion.esValida(sizeTableroPrueba));
	}
}
