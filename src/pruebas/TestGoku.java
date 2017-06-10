package pruebas;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import modelo.excepciones.TransformacionNoPosible;
import modelo.personajes.Goku;



public class TestGoku {

	private Goku goku;
	
	@Before
	public void setUp(){	
		this.goku = new Goku(null);
	}
		
	@Test
	public void testGokuVidaCompletaTienePoderPeleaNormal(){
		Assert.assertEquals(20, goku.getPoderPelea(), 0.01);
	}
	
	@Test
	public void testGokuPocaVidaTienePoderPeleaMayor(){
		goku.recibirAtaque(480);
		Assert.assertEquals(24, goku.getPoderPelea(), 0.01);
	}
	
	@Test
	public void testGokuTransformadoPocaVidaTienePoderPeleaMayor(){
		for(int i = 0; i< 10; i++){
			goku.generarKi();
		}
		goku.recibirAtaque(480);
		try {
			goku.transformar();
		} catch (TransformacionNoPosible e) {
			Assert.fail("Deberia haberse transformado");
		}
		Assert.assertEquals(48, goku.getPoderPelea(), 0.01);
	}
	
	@Test
	public void testGokuTransformadoModoFinalPocaVidaTienePoderPeleaMayor(){
		for(int i = 0; i< 15; i++){
			goku.generarKi();
		}
		goku.recibirAtaque(480);
		try {
			goku.transformar();
			goku.transformar();
		} catch (TransformacionNoPosible e) {
			Assert.fail("Deberia haberse transformado");
		}
		Assert.assertEquals(72, goku.getPoderPelea(), 0.01);
	}

}
