package pruebas;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import modelo.excepciones.TransformacionNoPosible;
import modelo.personajes.Goku;
import modelo.utilidades.Constantes;



public class GokuTest {

	public static final double danioParaPocaVida = Goku.vidaInicial * 0.98;
	
	
	private Goku goku;
	
	@Before
	public void setUp(){	
		this.goku = new Goku(null);
	}
		
	@Test
	public void testGokuVidaCompletaTienePoderPeleaNormal(){
		Assert.assertEquals(Goku.poderPeleaNormal, goku.getPoderPelea(), Constantes.diferenciaMaximaFloats);
	}
	
	@Test
	public void testGokuPocaVidaTienePoderPeleaMayor(){
		goku.recibirAtaque(danioParaPocaVida);
		double poderPeleaEsperado = Goku.poderPeleaNormal * Goku.multiplicadorPocaVida;
		Assert.assertEquals(poderPeleaEsperado, goku.getPoderPelea(), Constantes.diferenciaMaximaFloats);
	}
	
	@Test
	public void testGokuTransformadoPocaVidaTienePoderPeleaMayor(){
		for(int i = 0; i< Constantes.cantidadParaGenerarKiSuficiente; i++){
			goku.generarKi();
		}
		goku.recibirAtaque(danioParaPocaVida);
		double poderPeleaEsperado = Goku.poderPeleaPrimerTransformacion * Goku.multiplicadorPocaVida;
		try {
			goku.transformar();
		} catch (TransformacionNoPosible e) {}
		Assert.assertEquals(poderPeleaEsperado, goku.getPoderPelea(), Constantes.diferenciaMaximaFloats);
	}
	
	@Test
	public void testGokuTransformadoModoFinalPocaVidaTienePoderPeleaMayor(){
		for(int i = 0; i< Constantes.cantidadParaGenerarKiSuficiente; i++){
			goku.generarKi();
		}
		goku.recibirAtaque(danioParaPocaVida);
		double poderPeleaEsperado = Goku.poderPeleaSegundaTransformacion * Goku.multiplicadorPocaVida;
		try {
			goku.transformar();
			goku.transformar();
		} catch (TransformacionNoPosible e) {}
		Assert.assertEquals(poderPeleaEsperado, goku.getPoderPelea(), Constantes.diferenciaMaximaFloats);
	}

}
