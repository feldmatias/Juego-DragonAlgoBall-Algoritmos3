package pruebas;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import modelo.excepciones.AtaqueNoPosible;
import modelo.excepciones.EquipoNoDisponible;
import modelo.excepciones.TransformacionNoPosible;
import modelo.juego.DragonBall;
import modelo.personajes.Personaje;
import modelo.tablero.Posicion;
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
		
		for (int i = 0; i < 15; i++){
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
			Assert.assertEquals(20, cell.getPoderPelea(), 0.01);
		}
	}
	
	@Test
	public void testCellNoPuedeTransformarseAbsorverTresVeces(){
		try {
			for(int i = 0; i < 3; i++){
				cell.realizarAtaqueEspecial(goku);
			}
		} catch (AtaqueNoPosible e1) {
			Assert.fail("Deberia haber atacado");
		}
		try {
			cell.transformar();
			Assert.fail("No deberia haberse transformado");
		} catch (TransformacionNoPosible e) {
			Assert.assertEquals(20, cell.getPoderPelea(), 0.01);
		}
	}
	
	@Test
	public void testCellPuedeTransformarseAbsorverCuatroVeces(){
		try {
			for(int i = 0; i < 4; i++){
				cell.realizarAtaqueEspecial(goku);
			}
		} catch (AtaqueNoPosible e1) {
			Assert.fail("Deberia haber atacado");
		}
		try {
			cell.transformar();
			Assert.assertEquals(40, cell.getPoderPelea(), 0.01);
		} catch (TransformacionNoPosible e) {
			Assert.fail("Deberia haberse transformado");
		}
	}
	
	@Test
	public void testCellPuedeTransformarseAbsorverCincoVeces(){
		try {
			for(int i = 0; i < 5; i++){
				cell.realizarAtaqueEspecial(goku);
			}
		} catch (AtaqueNoPosible e1) {
			Assert.fail("Deberia haber atacado");
		}
		try {
			cell.transformar();
			Assert.assertEquals(40, cell.getPoderPelea(), 0.01);
		} catch (TransformacionNoPosible e) {
			Assert.fail("Deberia haberse transformado");
		}
	}

	//Test Segunda transformacion
	
	@Test
	public void testCellNoPuedeTransformarseDosVecesAbsorverSieteVeces(){
		try {
			for(int i = 0; i < 7; i++){
				cell.realizarAtaqueEspecial(goku);
			}
		} catch (AtaqueNoPosible e1) {
			Assert.fail("Deberia haber atacado");
		}
		try {
			cell.transformar();
			Assert.assertEquals(40, cell.getPoderPelea(), 0.01);
		} catch (TransformacionNoPosible e) {
			Assert.fail("Deberia haberse transformado: primera transformacion");
		}
		try {
			cell.transformar();
			Assert.fail("No deberia haberse transformado");
		} catch (TransformacionNoPosible e) {
			Assert.assertEquals(40, cell.getPoderPelea(), 0.01);
		}
	}
	
	@Test
	public void testCellPuedeTransformarseDosVecesAbsorverOchoVeces(){
		try {
			for(int i = 0; i < 8; i++){
				cell.realizarAtaqueEspecial(goku);
			}
		} catch (AtaqueNoPosible e1) {
			Assert.fail("Deberia haber atacado");
		}
		try {
			cell.transformar();
			cell.transformar();
			Assert.assertEquals(80, cell.getPoderPelea(), 0.01);
		} catch (TransformacionNoPosible e) {
			Assert.fail("Deberia haberse transformado");
		}

	}
	
	@Test
	public void testCellPuedeTransformarseDosVecesAbsorverMuchasVeces(){
		try {
			for(int i = 0; i < 14; i++){
				cell.realizarAtaqueEspecial(goku);
			}
		} catch (AtaqueNoPosible e1) {
			Assert.fail("Deberia haber atacado");
		}
		try {
			cell.transformar();
			cell.transformar();
			Assert.assertEquals(80, cell.getPoderPelea(), 0.01);
		} catch (TransformacionNoPosible e) {
			Assert.fail("Deberia haberse transformado");
		}

	}
	
	//Test poder especial absorver
	
	@Test
	public void testAbsorverRegeneraVida(){
		double porcentajeVidaEsperado = 104; //se espera aumento 4% aumento luego del ataque
		try {
			cell.realizarAtaqueEspecial(goku);
			Assert.assertEquals(porcentajeVidaEsperado, cell.getPorcentajeVida(), 0.01);
		} catch (AtaqueNoPosible e) {
			Assert.fail("Deberia haber atacado");
		}
		
	}
	
	@Test
	public void testAbsorverDosVecesRegeneraVida(){
		double porcentajeVidaEsperado = 108; // se espera aumento 8% luego de dos ataques
		try {
			cell.realizarAtaqueEspecial(goku);
			cell.realizarAtaqueEspecial(goku);
			Assert.assertEquals(porcentajeVidaEsperado,cell.getPorcentajeVida() , 0.01);
		} catch (AtaqueNoPosible e) {
			Assert.fail("Deberia haber atacado");
		}
		
	}
	
	@Test
	public void testRealizarAtaqueEspecialYComprobarVidaEnemigo(){
		double porcentajeVidaEsperado = 96; //se espera que tenga 4% menos de vida
		try {
			cell.realizarAtaqueEspecial(goku);
			Assert.assertEquals(porcentajeVidaEsperado, goku.getPorcentajeVida(), 0.01);
		} catch (AtaqueNoPosible e) {
			Assert.fail("Deberia haber atacado");
		}
		
	}

}
