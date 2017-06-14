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
		this.goku = juego.getJugador1().getEquipo().getMiembros().get(0);
		this.cell = juego.getJugador2().getEquipo().getMiembros().get(0);
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
			Assert.assertEquals(Cell.poderPeleaNormal, cell.getPoderPelea(), Constantes.porcentajeEsperado);
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
			Assert.assertEquals(Cell.poderPeleaNormal, cell.getPoderPelea(), Constantes.porcentajeEsperado);
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
			Assert.assertEquals(Cell.poderPeleaSemiPerfecto, cell.getPoderPelea(), Constantes.porcentajeEsperado);
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
			Assert.assertEquals(Cell.poderPeleaSemiPerfecto, cell.getPoderPelea(), Constantes.porcentajeEsperado);
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
			Assert.assertEquals(Cell.poderPeleaSemiPerfecto, cell.getPoderPelea(), Constantes.porcentajeEsperado);
		} catch (TransformacionNoPosible e) {
			Assert.fail("Deberia haberse transformado: primera transformacion");
		}
		try {
			cell.transformar();
			Assert.fail("No deberia haberse transformado");
		} catch (TransformacionNoPosible e) {
			Assert.assertEquals(Cell.poderPeleaSemiPerfecto, cell.getPoderPelea(), Constantes.porcentajeEsperado);
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
			Assert.assertEquals(Cell.poderPeleaPerfecto, cell.getPoderPelea(), Constantes.porcentajeEsperado);
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
			Assert.assertEquals(Cell.poderPeleaPerfecto, cell.getPoderPelea(), Constantes.porcentajeEsperado);
		} catch (TransformacionNoPosible e) {
			Assert.fail("Deberia haberse transformado");
		}

	}
	
	//Test poder especial absorber
	
	@Test
	public void testAbsorberRegeneraVida(){
		double porcentajeVidaEsperado = 104; //se espera aumento 4% aumento luego del ataque
		try {
			cell.realizarAtaqueEspecial(goku);
			Assert.assertEquals(porcentajeVidaEsperado, cell.getPorcentajeVida(), Constantes.porcentajeEsperado);
		} catch (AtaqueNoPosible e) {}	
	}
	
	@Test
	public void testAbsorberDosVecesRegeneraVida(){
		double porcentajeVidaEsperado = 108; // se espera aumento 8% luego de dos ataques
		try {
			cell.realizarAtaqueEspecial(goku);
			cell.realizarAtaqueEspecial(goku);
			Assert.assertEquals(porcentajeVidaEsperado,cell.getPorcentajeVida() , Constantes.porcentajeEsperado);
		} catch (AtaqueNoPosible e) {}
	}
	
	@Test
	public void testRealizarAtaqueEspecialYComprobarVidaEnemigo(){
		double porcentajeVidaEsperado = 96; //se espera que tenga 4% menos de vida
		try {
			cell.realizarAtaqueEspecial(goku);
			Assert.assertEquals(porcentajeVidaEsperado, goku.getPorcentajeVida(), Constantes.porcentajeEsperado);
		} catch (AtaqueNoPosible e) {}
	}
}
