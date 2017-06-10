package pruebas;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import modelo.excepciones.EquipoNoDisponible;
import modelo.excepciones.TransformacionNoPosible;
import modelo.juego.DragonBall;
import modelo.juego.Equipo;
import modelo.personajes.Personaje;
import modelo.utilidades.Constantes;

public class TestGohan {

	private Personaje gohan;
	private Personaje goku;
	private Personaje piccolo;
	
	@Before
	public void setUp() throws EquipoNoDisponible{
		DragonBall juego = new DragonBall();
		juego.establecerEquipoJugador1(Constantes.GUERREROS);
		Equipo equipo = juego.getJugador1().getEquipo();
		this.goku = equipo.getMiembros().get(0);
		this.gohan = equipo.getMiembros().get(1);
		this.piccolo = equipo.getMiembros().get(2);
		
		for (int i = 0; i < 15; i ++){
			gohan.empezarTurno();
		}
		try {
			gohan.transformar();
		} catch (TransformacionNoPosible e) {
			Assert.fail("Deberia haberse transformado: Primera transformacion");
		}
	}
	
	@Test
	public void testGohanNoPuedeTransformarseVidaCompanierosCompleta(){
		try {
			gohan.transformar();
			Assert.fail("No deberia haberse transformado");
		} catch (TransformacionNoPosible e) {
			Assert.assertEquals(30, gohan.getPoderPelea());
		}
	}
	
	@Test
	public void testGohanPuedeTransformarseCompanierosConPocaVida(){
		
		goku.recibirAtaque(420);
		piccolo.recibirAtaque(420);
		try {
			gohan.transformar();
			Assert.assertEquals(100, gohan.getPoderPelea());
		} catch (TransformacionNoPosible e) {
			Assert.fail("deberia haberse Transformado");
		}
	}
	
	@Test
	public void testGohanPuedeTransformarseCompanierosMuertos(){
		
		goku.recibirAtaque(1000);
		piccolo.recibirAtaque(1000);
		try {
			gohan.transformar();
			Assert.assertEquals(100, gohan.getPoderPelea());
		} catch (TransformacionNoPosible e) {
			Assert.fail("deberia haberse Transformado");
		}
	}
	
	@Test
	public void testGohanNoPuedeTransformarseVidaPiccoloCompanieroCompleta(){
		
		goku.recibirAtaque(420);
		try {
			gohan.transformar();
			Assert.fail("No deberia haberse transformado");
		} catch (TransformacionNoPosible e) {
			Assert.assertEquals(30, gohan.getPoderPelea());
		}
	}
	
	@Test
	public void testGohanNoPuedeTransformarseVidaGokuCompanieroCompleta(){
		
		piccolo.recibirAtaque(420);
		try {
			gohan.transformar();
			Assert.fail("No deberia haberse transformado");
		} catch (TransformacionNoPosible e) {
			Assert.assertEquals(30, gohan.getPoderPelea());
		}
	}
	
	@Test
	public void testGohanTransformarYComprobarKi(){
		
		goku.recibirAtaque(420);
		piccolo.recibirAtaque(420);
		int costoKiTransformacion = 30;
		int kiEsperado = gohan.getKi() - costoKiTransformacion;
		try {
			gohan.transformar();
			Assert.assertEquals( kiEsperado, gohan.getKi());
		} catch (TransformacionNoPosible e) {
			Assert.fail("deberia haberse Transformado");
		}
	}
	
}
