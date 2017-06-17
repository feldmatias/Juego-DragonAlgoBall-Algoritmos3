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
		
		for (int i = 0; i < Constantes.cantidadParaGenerarKiSuficiente ;i ++){
			gohan.empezarTurno();
		}
		try {
			gohan.transformar();
		} catch (TransformacionNoPosible e) {
		}
	}
	
	@Test
	public void testGohanNoPuedeTransformarseVidaCompanierosCompleta(){
		try {
			gohan.transformar();
			Assert.fail("No deberia haberse transformado");
		} catch (TransformacionNoPosible e) {
			Assert.assertEquals(Gohan.poderPeleaPrimerTransformacion, gohan.getPoderPelea(), Constantes.diferenciaMaximaFloats);
		}
	}
	
	@Test
	public void testGohanPuedeTransformarseCompanierosConPocaVida(){
		
		goku.recibirAtaque(Goku.vidaInicial * 0.98);
		piccolo.recibirAtaque(Piccolo.vidaInicial * 0.98);
		try {
			gohan.transformar();
			Assert.assertEquals(Gohan.poderPeleaSegundaTransformacion, gohan.getPoderPelea(), Constantes.diferenciaMaximaFloats);
		} catch (TransformacionNoPosible e) {
			Assert.fail("Deberia haberse Transformado");
		}
	}
	
	@Test
	public void testGohanPuedeTransformarseCompanierosMuertos(){
		
		goku.recibirAtaque(Goku.vidaInicial * 2);
		piccolo.recibirAtaque(Piccolo.vidaInicial * 2);
		try {
			gohan.transformar();
			Assert.assertEquals(Gohan.poderPeleaSegundaTransformacion, gohan.getPoderPelea(), Constantes.diferenciaMaximaFloats);
		} catch (TransformacionNoPosible e) {
			Assert.fail("Deberia haberse Transformado");
		}
	}
	
	@Test
	public void testGohanNoPuedeTransformarseVidaPiccoloCompanieroCompleta(){
		
		goku.recibirAtaque(Goku.vidaInicial * 0.98);
		try {
			gohan.transformar();
			Assert.fail("No deberia haberse transformado");
		} catch (TransformacionNoPosible e) {
			Assert.assertEquals(Gohan.poderPeleaPrimerTransformacion, gohan.getPoderPelea(), Constantes.diferenciaMaximaFloats);
		}
	}
	
	@Test
	public void testGohanNoPuedeTransformarseVidaGokuCompanieroCompleta(){
		
		piccolo.recibirAtaque(Piccolo.vidaInicial * 0.98);
		try {
			gohan.transformar();
			Assert.fail("No deberia haberse transformado");
		} catch (TransformacionNoPosible e) {
			Assert.assertEquals(Gohan.poderPeleaPrimerTransformacion, gohan.getPoderPelea(), Constantes.diferenciaMaximaFloats);
		}
	}
	
	@Test
	public void testGohanTransformarYComprobarKi(){
		
		goku.recibirAtaque(Goku.vidaInicial * 0.98);
		piccolo.recibirAtaque(Piccolo.vidaInicial * 0.98);
		int kiEsperado = gohan.getKi() - Gohan.kiNecesarioSegundaTransformacion;
		try {
			gohan.transformar();
			Assert.assertEquals( kiEsperado, gohan.getKi());
		} catch (TransformacionNoPosible e) {
		}
	}
	
}
