package pruebas;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import modelo.excepciones.EquipoNoDisponible;
import modelo.excepciones.TransformacionNoPosible;
import modelo.juego.DragonBall;
import modelo.juego.Equipo;
import modelo.personajes.Gohan;
import modelo.personajes.Goku;
import modelo.personajes.Personaje;
import modelo.personajes.Piccolo;
import modelo.utilidades.Constantes;

public class TestPiccolo {
	
	public static final double danioParaPocaVidaGohan = Gohan.vidaInicial * 0.98;
	public static final double danioParaPocaVidaGoku = Goku.vidaInicial * 0.98;
	public static final double danioParaMuerteDeGohan = Gohan.vidaInicial * 3;

	private Personaje gohan;
	private Personaje piccolo;
	
	@Before
	public void setUp() throws EquipoNoDisponible{
		DragonBall juego = new DragonBall();
		juego.establecerEquipoJugador1(Constantes.GUERREROS);
		Equipo equipo = juego.getJugador1().getEquipo();
		this.gohan = equipo.getMiembros().get(1);
		this.piccolo = equipo.getMiembros().get(2);
		
		for (int i = 0; i < Constantes.cantidadParaGenerarKiSuficiente; i ++){
			piccolo.empezarTurno();
		}
		try {
			piccolo.transformar();
		} catch (TransformacionNoPosible e) {
			Assert.fail("Deberia haberse transformado: Primera transformacion");
		}
	}
	
	@Test
	public void testPiccoloNoPuedeTransformarseGohanVidaCompleta(){
		try {
			piccolo.transformar();
			Assert.fail("No deberia haberse transformado");
		} catch (TransformacionNoPosible e) {
			Assert.assertEquals(Piccolo.poderPeleaPrimerTransformacion, piccolo.getPoderPelea(), Constantes.diferenciaMaximaFloats);
		}
	}
	
	@Test
	public void testPiccoloPuedeTransformarseGohanPocaVida(){
		
		gohan.recibirAtaque(danioParaPocaVidaGohan);
		try {
			piccolo.transformar();
			Assert.assertEquals(Piccolo.poderPeleaSegundaTransformacion, piccolo.getPoderPelea(), Constantes.diferenciaMaximaFloats);
		} catch (TransformacionNoPosible e) {
			Assert.fail("deberia haberse Transformado");
		}
	}
	
	@Test
	public void testPiccoloPuedeTransformarseGohanMuerto(){
		
		gohan.recibirAtaque(danioParaMuerteDeGohan);
		try {
			piccolo.transformar();
			Assert.assertEquals(Piccolo.poderPeleaSegundaTransformacion, piccolo.getPoderPelea(), Constantes.diferenciaMaximaFloats);
		} catch (TransformacionNoPosible e) {
			Assert.fail("deberia haberse Transformado");
		}
	}
	
	@Test
	public void testPiccoloNoPuedeTransformarseGohanVidaCompletaYGokuVidaVacia(){
		
		Personaje goku = piccolo.getEquipo().getMiembros().get(0);
		goku.recibirAtaque(danioParaPocaVidaGoku);
		try {
			piccolo.transformar();
			Assert.fail("No deberia haberse transformado");
		} catch (TransformacionNoPosible e) {
			Assert.assertEquals(Piccolo.poderPeleaPrimerTransformacion, piccolo.getPoderPelea(), Constantes.diferenciaMaximaFloats);
		}
	}
	
	@Test
	public void testPiccoloNoPuedeTransformarseGohanTransformadoVidaCompleta(){
		for (int i = 0; i < Constantes.cantidadParaGenerarKiSuficiente; i++){
			gohan.generarKi();
		}
		try {
			gohan.transformar();
		} catch (TransformacionNoPosible e1) {
		}
		try {
			piccolo.transformar();
			Assert.fail("No deberia haberse transformado");
		} catch (TransformacionNoPosible e) {
			Assert.assertEquals(Piccolo.poderPeleaPrimerTransformacion, piccolo.getPoderPelea(), Constantes.diferenciaMaximaFloats);
		}
	}
	
	@Test
	public void testPiccoloPuedeTransformarseGohanTransformadoPocaVida(){
		
		for (int i = 0; i < Constantes.cantidadParaGenerarKiSuficiente; i++){
			gohan.generarKi();
		}
		try {
			gohan.transformar();
		} catch (TransformacionNoPosible e1) {
		}
		gohan.recibirAtaque(danioParaPocaVidaGohan);
		try {
			piccolo.transformar();
			Assert.assertEquals(Piccolo.poderPeleaSegundaTransformacion, piccolo.getPoderPelea(), Constantes.diferenciaMaximaFloats);
		} catch (TransformacionNoPosible e) {
			Assert.fail("deberia haberse Transformado");
		}
	}
}
