package pruebas;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import modelo.personajes.Personaje;
import modelo.tablero.Casillero;

public class TestCasillero {
	
	private Casillero casillero1;
	
	@Before
	public void setUp() {
		casillero1 = new Casillero(0,0);
		
	}
	
	@Test
	public void testAlCrearCasilleroEstaVacio(){
		Assert.assertTrue( casillero1.estaVacio() );
	}
	
}