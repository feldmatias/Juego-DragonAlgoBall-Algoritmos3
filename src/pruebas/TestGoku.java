package pruebas;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import modelo.excepciones.TransformacionNoPosible;
import modelo.personajes.Goku;
import modelo.utilidades.Constantes;



public class TestGoku {

	public static final int danioParaPocaVida = 480;
	public static final double poderPeleaconPocavida = 24;
	public static final double poderPeleaconPocavidaKaioKen = 48;
	public static final double poderPeleaconPocavidaSayajin = 72;
	
	
	private Goku goku;
	
	@Before
	public void setUp(){	
		this.goku = new Goku(null);
	}
		
	@Test
	public void testGokuVidaCompletaTienePoderPeleaNormal(){
		Assert.assertEquals(Goku.poderPeleaNormal, goku.getPoderPelea(), Constantes.porcentajeEsperado);
	}
	
	@Test
	public void testGokuPocaVidaTienePoderPeleaMayor(){
		goku.recibirAtaque(danioParaPocaVida);
		Assert.assertEquals(poderPeleaconPocavida, goku.getPoderPelea(), Constantes.porcentajeEsperado);
	}
	
	@Test
	public void testGokuTransformadoPocaVidaTienePoderPeleaMayor(){
		for(int i = 0; i< Constantes.cantidadParaGenerarKiSuficiente; i++){
			goku.generarKi();
		}
		goku.recibirAtaque(danioParaPocaVida);
		try {
			goku.transformar();
		} catch (TransformacionNoPosible e) {}
		Assert.assertEquals(poderPeleaconPocavidaKaioKen, goku.getPoderPelea(), Constantes.porcentajeEsperado);
	}
	
	@Test
	public void testGokuTransformadoModoFinalPocaVidaTienePoderPeleaMayor(){
		for(int i = 0; i< Constantes.cantidadParaGenerarKiSuficiente; i++){
			goku.generarKi();
		}
		goku.recibirAtaque(danioParaPocaVida);
		try {
			goku.transformar();
			goku.transformar();
		} catch (TransformacionNoPosible e) {}
		Assert.assertEquals(poderPeleaconPocavidaSayajin, goku.getPoderPelea(), Constantes.porcentajeEsperado);
	}

}
