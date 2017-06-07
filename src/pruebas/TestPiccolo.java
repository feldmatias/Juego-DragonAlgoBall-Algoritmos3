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

public class TestPiccolo {

	private Personaje gohan;
	private Personaje piccolo;
	
	@Before
	public void setUp() throws EquipoNoDisponible{
		DragonBall juego = new DragonBall();
		juego.establecerEquipoJugador1(Constantes.GUERREROS);
		Equipo equipo = juego.getJugador1().getEquipo();
		this.gohan = equipo.getMiembros().get(1);
		this.piccolo = equipo.getMiembros().get(2);
		
		for (int i = 0; i < 15; i ++){
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
			Assert.assertEquals(40, piccolo.getPoderPelea());
		}
	}
	
	@Test
	public void testPiccoloPuedeTransformarseGohanPocaVida(){
		
		gohan.recibirAtaque(270);
		try {
			piccolo.transformar();
			Assert.assertEquals(60, piccolo.getPoderPelea());
		} catch (TransformacionNoPosible e) {
			Assert.fail("deberia haberse Transformado");
		}
	}
	
	@Test
	public void testPiccoloPuedeTransformarseGohanMuerto(){
		
		gohan.recibirAtaque(1000);
		try {
			piccolo.transformar();
			Assert.assertEquals(60, piccolo.getPoderPelea());
		} catch (TransformacionNoPosible e) {
			Assert.fail("deberia haberse Transformado");
		}
	}
	
	@Test
	public void testPiccoloNoPuedeTransformarseGohanVidaCompletaYGokuVidaVacia(){
		
		Personaje goku = piccolo.getEquipo().getMiembros().get(0);
		goku.recibirAtaque(480);
		try {
			piccolo.transformar();
			Assert.fail("No deberia haberse transformado");
		} catch (TransformacionNoPosible e) {
			Assert.assertEquals(40, piccolo.getPoderPelea());
		}
	}
}
