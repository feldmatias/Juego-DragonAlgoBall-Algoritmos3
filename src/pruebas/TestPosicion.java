package pruebas;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import modelo.tablero.Posicion;

public class TestPosicion {

	private Posicion posicion;
	
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
		Posicion destino = new Posicion (8,5);
		Assert.assertEquals(3, posicion.distanciaA(destino));
	}
	
	@Test
	public void testCalcularDistanciaHorizontalAtras(){
		Posicion destino = new Posicion (1,5);
		Assert.assertEquals(4, posicion.distanciaA(destino));
	}
	
	@Test
	public void testCalcularDistanciaVerticalAdelante(){
		Posicion destino = new Posicion (5,6);
		Assert.assertEquals(1, posicion.distanciaA(destino));
	}
	
	@Test
	public void testCalcularDistanciaVerticalAtras(){
		Posicion destino = new Posicion (5,3);
		Assert.assertEquals(2, posicion.distanciaA(destino));
	}
	
	@Test
	public void testCalcularDistanciaDiagonal(){
		Posicion destino = new Posicion (7,7);
		Assert.assertEquals(2, posicion.distanciaA(destino));
	}
	
	@Test
	public void testCalcularDistanciaAOtraPosicion(){
		Posicion destino = new Posicion (4,8);
		Assert.assertEquals(3, posicion.distanciaA(destino));
	}
	
	@Test
	public void testSumarMismaPosicion(){
		Posicion resultado = posicion.sumarPosicion(posicion);
		Assert.assertTrue(resultado.equals(new Posicion (10,10)));
	}
	
	@Test
	public void testSumarPosicionNula(){
		Posicion resultado = posicion.sumarPosicion(new Posicion(0,0));
		Assert.assertTrue(resultado.equals(posicion));
	}
	
	@Test
	public void testSumarCualquierPosicion(){
		Posicion resultado = posicion.sumarPosicion(new Posicion(7,2));
		Assert.assertTrue(resultado.equals(new Posicion (12,7)));
	}
	
	@Test
	public void testPosicionXNegativoNoEsValida(){
		this.posicion = new Posicion (-1,4);
		Assert.assertFalse(posicion.esValida(10));
	}
	
	@Test
	public void testPosicionYNegativoNoEsValida(){
		this.posicion = new Posicion (3,-5);
		Assert.assertFalse(posicion.esValida(10));
	}
	
	@Test
	public void testPosicionXMuyAltoNoEsValida(){
		this.posicion = new Posicion (20,3);
		Assert.assertFalse(posicion.esValida(10));
	}
	
	@Test
	public void testPosicionYMuyAltoNoEsValida(){
		this.posicion = new Posicion (5,18);
		Assert.assertFalse(posicion.esValida(10));
	}
}
