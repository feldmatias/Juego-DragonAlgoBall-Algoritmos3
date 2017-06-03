package pruebas;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import modelo.excepciones.PosicionFueraDeRango;
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
	public void getCasilleroSegunPosicionDevuelveElCasilleroConLaMismaPosicion() throws PosicionFueraDeRango {
		Posicion posBuscada = new Posicion(2,2);
		Casillero casillero = tablero.getCasillero(posBuscada);
		Posicion posCasillero = casillero.getPosicion();
		
		Assert.assertTrue( posBuscada.equals(posCasillero) );	
	}
	
}
