package pruebas;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import modelo.excepciones.EquipoNoDisponible;
import modelo.excepciones.TransformacionNoPosible;
import modelo.juego.DragonBall;
import modelo.juego.Equipo;
import modelo.personajes.Personaje;
import modelo.personajes.Piccolo;
import modelo.utilidades.Constantes;

public class TestPiccolo {
	
	public static final int danioParaPocaVidaGohan = 270;
	public static final int danioParaPocaVidaGoku = 480;
	public static final int danioParaMuerteDeGohan = 1000;

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
			Assert.assertEquals(Piccolo.poderPeleaFortalecido, piccolo.getPoderPelea(), Constantes.porcentajeEsperado);
		}
	}
	
	@Test
	public void testPiccoloPuedeTransformarseGohanPocaVida(){
		
		gohan.recibirAtaque(danioParaPocaVidaGohan);
		try {
			piccolo.transformar();
			Assert.assertEquals(Piccolo.poderPeleaProtector, piccolo.getPoderPelea(), Constantes.porcentajeEsperado);
		} catch (TransformacionNoPosible e) {
			Assert.fail("deberia haberse Transformado");
		}
	}
	
	@Test
	public void testPiccoloPuedeTransformarseGohanMuerto(){
		
		gohan.recibirAtaque(danioParaMuerteDeGohan);
		try {
			piccolo.transformar();
			Assert.assertEquals(Piccolo.poderPeleaProtector, piccolo.getPoderPelea(), Constantes.porcentajeEsperado);
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
			Assert.assertEquals(Piccolo.poderPeleaFortalecido, piccolo.getPoderPelea(), Constantes.porcentajeEsperado);
		}
	}
}
