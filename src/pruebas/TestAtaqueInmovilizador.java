package pruebas;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import modelo.excepciones.AtaqueNoPosible;
import modelo.excepciones.EquipoNoDisponible;
import modelo.excepciones.MovimientoNoPosible;
import modelo.excepciones.TransformacionNoPosible;
import modelo.juego.DragonBall;
import modelo.personajes.Personaje;
import modelo.tablero.Posicion;
import modelo.utilidades.Constantes;

public class TestAtaqueInmovilizador {

	private Personaje majinBoo;
	private Personaje goku;
	private DragonBall juego;
	
	@Before
	public void setUp() throws EquipoNoDisponible{
		juego = new DragonBall();
		juego.establecerEquipoJugador1(Constantes.GUERREROS);
		juego.establecerEquipoJugador2(Constantes.ENEMIGOS);
		this.goku = juego.getJugador1().getEquipo().getMiembros().get(0);
		this.majinBoo = juego.getJugador2().getEquipo().getMiembros().get(2);
		juego.getTablero().reposicionarPersonaje(goku, new Posicion(5,5));
		juego.getTablero().reposicionarPersonaje(majinBoo, new Posicion(5,6));
		
		for (int i = 0; i < 15; i++){
			majinBoo.generarKi();
			goku.generarKi();
		}
	}
	
	@Test
	public void testPersonajeInmovilizadoNoPuedeAtacar(){
		try {
			majinBoo.realizarAtaqueEspecial(goku);
		} catch (AtaqueNoPosible e) {
			Assert.fail("El ataque deberia realizarse");
		}
		try {
			goku.atacarAPersonaje(majinBoo);
			Assert.fail("El ataque no deberia realizarse");
		} catch (AtaqueNoPosible e) {
			Assert.assertTrue(true);
		}
		
	}
	
	@Test
	public void testPersonajeInmovilizadoNoPuedeRealizarAtaqueEspecial(){
		try {
			majinBoo.realizarAtaqueEspecial(goku);
		} catch (AtaqueNoPosible e) {
			Assert.fail("El ataque deberia realizarse");
		}
		try {
			goku.realizarAtaqueEspecial(majinBoo);
			Assert.fail("El ataque no deberia realizarse");
		} catch (AtaqueNoPosible e) {
			Assert.assertTrue(true);
		}
		
	}
	
	@Test
	public void testPersonajeInmovilizadoNoPuedeMover(){
		try {
			majinBoo.realizarAtaqueEspecial(goku);
		} catch (AtaqueNoPosible e) {
			Assert.fail("El ataque deberia realizarse");
		}
		try {
			goku.mover(new Posicion (5,4));
			Assert.fail("El movimiento no deberia realizarse");
		} catch (MovimientoNoPosible e) {
			Assert.assertTrue(true);
		}
		
	}
	
	@Test
	public void testPersonajeInmovilizadoNoPuedeTransformarse(){
		try {
			majinBoo.realizarAtaqueEspecial(goku);
		} catch (AtaqueNoPosible e) {
			Assert.fail("El ataque deberia realizarse");
		}
		try {
			goku.transformar();
			Assert.fail("La transformacion no deberia realizarse");
		} catch (TransformacionNoPosible e) {
			Assert.assertTrue(true);
		}
		
	}
	
	@Test
	public void testPersonajeInmovilizadoNoPuedeGenerarKi(){
		try {
			majinBoo.realizarAtaqueEspecial(goku);
		} catch (AtaqueNoPosible e) {
			Assert.fail("El ataque deberia realizarse");
		}
		int kiActual = goku.getKi();
		goku.empezarTurno();
		goku.empezarTurno();
		Assert.assertEquals(kiActual, goku.getKi());
		
	}
	
	@Test
	public void testPersonajeInmovilizadoNoPuedeHacerNadaPorTresTurnos(){
		try {
			majinBoo.realizarAtaqueEspecial(goku);
		} catch (AtaqueNoPosible e) {
			Assert.fail("El ataque deberia realizarse");
		}
		int kiActual = goku.getKi();
		for (int i = 0; i < 3; i++){
			goku.empezarTurno();
			try{
				goku.transformar();
				Assert.fail("No deberia deberia realizarse: Transformacion" + i);
			} catch (TransformacionNoPosible e1){
			}
			try{
				goku.atacarAPersonaje(majinBoo);
				goku.realizarAtaqueEspecial(majinBoo);
				Assert.fail("No deberia deberia realizarse: Ataque" + i);
			} catch (AtaqueNoPosible e2){
			}
			try{
				goku.mover(new Posicion (5,4));
				Assert.fail("No deberia deberia realizarse: Movimiento" + i);
			} catch (MovimientoNoPosible e3){
			}
		}
		Assert.assertEquals(kiActual, goku.getKi());
		
	}
	
	@Test
	public void testPersonajeInmovilizadoVuelveALaNormalidadDespuesDeTresTurnos(){
		try {
			majinBoo.realizarAtaqueEspecial(goku);
		} catch (AtaqueNoPosible e) {
			Assert.fail("El ataque deberia realizarse");
		}
		int kiActual = goku.getKi();
		for (int i = 0; i < 4; i++){
			goku.empezarTurno();
		}
		Assert.assertEquals(kiActual + 5, goku.getKi());
		
		try {
			goku.atacarAPersonaje(majinBoo);
			goku.realizarAtaqueEspecial(majinBoo);
		} catch (AtaqueNoPosible e) {
			Assert.fail("Deberia haberse realizado");
		}
		try {
			goku.transformar();
		} catch (TransformacionNoPosible e) {
			Assert.fail("Deberia haberse realizado");
		}
		try {
			goku.mover(new Posicion (5,4));
		} catch (MovimientoNoPosible e) {
			Assert.fail("Deberia haberse realizado");
		}
		
		
	}
	
}
