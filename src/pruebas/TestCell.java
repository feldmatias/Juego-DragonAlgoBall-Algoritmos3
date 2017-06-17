package pruebas;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import modelo.excepciones.AtaqueNoPosible;
import modelo.excepciones.EquipoNoDisponible;
import modelo.excepciones.TransformacionNoPosible;
import modelo.juego.DragonBall;
import modelo.juego.Posicion;
import modelo.personajes.Cell;
import modelo.personajes.Goku;
import modelo.personajes.Personaje;
import modelo.utilidades.Constantes;

public class TestCell {

	private Personaje cell;
	private Personaje goku;
	private DragonBall juego;
	
	@Before
	public void setUp() throws EquipoNoDisponible{
		juego = new DragonBall();
		juego.establecerEquipoJugador1(Constantes.GUERREROS);
		juego.establecerEquipoJugador2(Constantes.ENEMIGOS);
		this.goku = juego.getJugador1().getEquipo().getMiembros().get("Goku");
		this.cell = juego.getJugador2().getEquipo().getMiembros().get("Cell");
		juego.getTablero().reposicionarPersonaje(goku, new Posicion(5,5));
		juego.getTablero().reposicionarPersonaje(cell, new Posicion(5,6));
		
		for (int i = 0; i < Constantes.cantidadParaGenerarKiSuficiente; i++){
			cell.generarKi();
		}
	}
	
	//Test primera transformacion
	
	@Test
	public void testCellNoPuedeTransformarseSinAbsorber(){
		try {
			cell.transformar();
			Assert.fail("No deberia haberse transformado");
		} catch (TransformacionNoPosible e) {
			Assert.assertEquals(Cell.poderPeleaNormal, cell.getPoderPelea(), Constantes.diferenciaMaximaFloats);
		}
	}
	
	@Test
	public void testCellNoPuedeTransformarseAbsorberTresVeces(){
		try {
			for(int i = 0; i < 3; i++){
				cell.realizarAtaqueEspecial(goku);
			}
		} catch (AtaqueNoPosible e1) {
		}
		try {
			cell.transformar();
			Assert.fail("No deberia haberse transformado");
		} catch (TransformacionNoPosible e) {
			Assert.assertEquals(Cell.poderPeleaNormal, cell.getPoderPelea(), Constantes.diferenciaMaximaFloats);
		}
	}
	
	@Test
	public void testCellPuedeTransformarseAbsorberCuatroVeces(){
		try {
			for(int i = 0; i < Cell.cantidadAbsorcionesPrimerTranformacion; i++){
				cell.realizarAtaqueEspecial(goku);
			}
		} catch (AtaqueNoPosible e1) {}
		try {
			cell.transformar();
			Assert.assertEquals(Cell.poderPeleaPrimerTransformacion, cell.getPoderPelea(), Constantes.diferenciaMaximaFloats);
		} catch (TransformacionNoPosible e) {
			Assert.fail("Deberia haberse transformado");
		}
	}
	
	@Test
	public void testCellPuedeTransformarseAbsorberCincoVeces(){
		try {
			for(int i = 0; i < 5; i++){
				cell.realizarAtaqueEspecial(goku);
			}
		} catch (AtaqueNoPosible e1) {
		}
		try {
			cell.transformar();
			Assert.assertEquals(Cell.poderPeleaPrimerTransformacion, cell.getPoderPelea(), Constantes.diferenciaMaximaFloats);
		} catch (TransformacionNoPosible e) {
			Assert.fail("Deberia haberse transformado");
		}
	}

	//Test Segunda transformacion
	
	@Test
	public void testCellNoPuedeTransformarseDosVecesAbsorberSieteVeces(){
		try {
			for(int i = 0; i < 7; i++){
				cell.realizarAtaqueEspecial(goku);
			}
		} catch (AtaqueNoPosible e1) {}
		try {
			cell.transformar();
			Assert.assertEquals(Cell.poderPeleaPrimerTransformacion, cell.getPoderPelea(), Constantes.diferenciaMaximaFloats);
		} catch (TransformacionNoPosible e) {
			Assert.fail("Deberia haberse transformado: primera transformacion");
		}
		try {
			cell.transformar();
			Assert.fail("No deberia haberse transformado");
		} catch (TransformacionNoPosible e) {
			Assert.assertEquals(Cell.poderPeleaPrimerTransformacion, cell.getPoderPelea(), Constantes.diferenciaMaximaFloats);
		}
	}
	
	@Test
	public void testCellPuedeTransformarseDosVecesAbsorberOchoVeces(){
		try {
			for(int i = 0; i < Cell.cantidadAbsorcionesSegundaTranformacion; i++){
				cell.realizarAtaqueEspecial(goku);
			}
		} catch (AtaqueNoPosible e1) {}
		try {
			cell.transformar();
			cell.transformar();
			Assert.assertEquals(Cell.poderPeleaSegundaTransformacion, cell.getPoderPelea(), Constantes.diferenciaMaximaFloats);
		} catch (TransformacionNoPosible e) {
			Assert.fail("Deberia haberse transformado");
		}

	}
	
	@Test
	public void testCellPuedeTransformarseDosVecesAbsorberMuchasVeces(){
		try {
			for(int i = 0; i < Cell.cantidadAbsorcionesSegundaTranformacion * 2; i++){
				cell.realizarAtaqueEspecial(goku);
			}
		} catch (AtaqueNoPosible e1) {}
		try {
			cell.transformar();
			cell.transformar();
			Assert.assertEquals(Cell.poderPeleaSegundaTransformacion, cell.getPoderPelea(), Constantes.diferenciaMaximaFloats);
		} catch (TransformacionNoPosible e) {
			Assert.fail("Deberia haberse transformado");
		}

	}
	
	//Test poder especial absorber
	
	@Test
	public void testAbsorberRegeneraVida(){
		double vidaEsperada = Cell.vidaInicial + Cell.poderPeleaNormal;;
		try {
			cell.realizarAtaqueEspecial(goku);
			Assert.assertEquals(vidaEsperada, cell.getVidaActual(), Constantes.diferenciaMaximaFloats);
		} catch (AtaqueNoPosible e) {}	
	}
	
	@Test
	public void testAbsorberDosVecesRegeneraVida(){
		double vidaEsperada = Cell.vidaInicial + Cell.poderPeleaNormal * 2;
		try {
			cell.realizarAtaqueEspecial(goku);
			cell.realizarAtaqueEspecial(goku);
			Assert.assertEquals(vidaEsperada,cell.getVidaActual() , Constantes.diferenciaMaximaFloats);
		} catch (AtaqueNoPosible e) {}
	}
	
	@Test
	public void testRealizarAtaqueEspecialYComprobarVidaEnemigo(){
		double vidaEsperada = Goku.vidaInicial - Cell.poderPeleaNormal;
		try {
			cell.realizarAtaqueEspecial(goku);
			Assert.assertEquals(vidaEsperada, goku.getVidaActual(), Constantes.diferenciaMaximaFloats);
		} catch (AtaqueNoPosible e) {}
	}
}
