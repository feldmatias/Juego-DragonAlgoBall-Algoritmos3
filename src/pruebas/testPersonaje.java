package pruebas;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import modelo.excepciones.AtaqueNoPosible;
import modelo.excepciones.MovimientoNoPosible;
import modelo.excepciones.TransformacionNoPosible;
import modelo.juego.Equipo;
import modelo.personajes.Freezer;
import modelo.personajes.Goku;
import modelo.personajes.Personaje;
import modelo.tablero.Posicion;
import modelo.tablero.Tablero;

public class testPersonaje {
	
	private Personaje personaje;
	private Tablero tablero;
	
	@Before
	public void setUp(){
		this.tablero = new Tablero(8);
		this.personaje = new Goku(tablero);
	}
	
	@Test
	public void testKiInicialEs0(){
		Assert.assertEquals(0, personaje.getKi());
	}
	
	@Test
	public void testGenerarKiUnaVez(){
		personaje.generarKi();
		Assert.assertEquals(5, personaje.getKi());
	}
	
	@Test
	public void testGenerarKiTresVeces(){
		personaje.generarKi();
		personaje.generarKi();
		personaje.generarKi();
		Assert.assertEquals(15, personaje.getKi());
	}
	
	@Test
	public void testPorcentajeVidaInicialEs100(){
		Assert.assertEquals(100, personaje.getPorcentajeVida(), 0.01);
	}
	
	@Test
	public void testObtenerPoderPeleaModoNormal(){
		Assert.assertEquals(20, personaje.getPoderPelea());
	}
	
	@Test
	public void testDistanciaAtaqueModoNormal(){
		Assert.assertEquals(2, personaje.getDistanciaAtaque());
	}
	
	@Test
	public void testObtenerVelocidadModoNormal(){
		Assert.assertEquals(2, personaje.getVelocidad());
	}
	
	@Test
	public void testRecibirAtaqueDeMayorPoderDePelea(){
		personaje.recibirAtaque(50); //Poder pelea 20 < 50
		Assert.assertEquals(90, personaje.getPorcentajeVida(), 0.01);
	}
	
	@Test
	public void testRecibirAtaqueDeMenorPoderDePelea(){
		personaje.recibirAtaque(10);  //Poder pelea 20 > 10
		Assert.assertEquals(98.4, personaje.getPorcentajeVida(), 0.01);
	}
		
	//Test transformaciones con ki
	
	@Test 
	public void testTransformacionDeKiRealizarSinKiLanzaExcepcion(){

		try {
			personaje.transformar();
			Assert.fail("La transformacion no deberia realizarse");
		} catch (TransformacionNoPosible e) {
			Assert.assertEquals(20, personaje.getPoderPelea());
		}
	}
	
	@Test 
	public void testTransformacionDeKiRealizarConKiComprobarNuevoPoderPelea(){
		
		for (int i = 0; i < 6; i++){
			personaje.generarKi();
		}
		try {
			personaje.transformar();
			Assert.assertEquals(40,personaje.getPoderPelea());
		} catch (TransformacionNoPosible e) {
			Assert.fail("La transformacion deberia realizarse");
		}
	}
	
	@Test 
	public void testTransformacionDeKiRealizarConKiComprobarNuevaDistanciaAtaque(){
		
		for (int i = 0; i < 6; i++){
			personaje.generarKi();
		}
		try {
			personaje.transformar();
			Assert.assertEquals(4,personaje.getDistanciaAtaque());
		} catch (TransformacionNoPosible e) {
			Assert.fail("La transformacion deberia realizarse");
		}
	}
	
	@Test 
	public void testTransformacionDeKiRealizarConKiComprobarNuevaVelocidad(){
		
		for (int i = 0; i < 6; i++){
			personaje.generarKi();
		}
		try {
			personaje.transformar();
			Assert.assertEquals(3,personaje.getVelocidad());
		} catch (TransformacionNoPosible e) {
			Assert.fail("La transformacion deberia realizarse");
		}
	}
	
	@Test 
	public void testTransformacionDeKiRealizarConKiComprobarKiRestante(){
		
		for (int i = 0; i < 6; i++){
			personaje.generarKi(); //ki=30
		}
		try {
			personaje.transformar();
			Assert.assertEquals(10,personaje.getKi());
		} catch (TransformacionNoPosible e) {
			Assert.fail("La transformacion deberia realizarse");
		}
	}
	
	@Test 
	public void testTransformacionDeKiTransformarDosVecesLanzaExcepcionEnLaSegunda(){

		for (int i = 0; i < 6; i++){
			personaje.generarKi(); 
		}
		try {
			personaje.transformar();
			Assert.assertEquals(10,personaje.getKi());
		} catch (TransformacionNoPosible e) {
			Assert.fail("La transformacion deberia realizarse");
		}
		try {
			personaje.transformar();
			Assert.fail("La transformacion no deberia realizarse");
		} catch (TransformacionNoPosible f) {
			Assert.assertEquals(40, personaje.getPoderPelea());
		}
	}

	@Test 
	public void testTransformacionDeKiRealizarDosVecesConKiComprobarKiRestante(){
		
		for (int i = 0; i < 20; i++){
			personaje.generarKi(); //ki=100
		}
		try {
			personaje.transformar();
			personaje.transformar();
			Assert.assertEquals(30,personaje.getKi());
		} catch (TransformacionNoPosible e) {
			Assert.fail("Las transformaciones deberian realizarse");
		}
	}
	
	@Test 
	public void testTransformacionDeKiRealizarDosVecesConKiComprobarPoderPelea(){
		
		for (int i = 0; i < 20; i++){
			personaje.generarKi(); //ki=100
		}
		try {
			personaje.transformar();
			personaje.transformar();
			Assert.assertEquals(60,personaje.getPoderPelea());
		} catch (TransformacionNoPosible e) {
			Assert.fail("Las transformaciones deberian realizarse");
		}
	}
	
	@Test 
	public void testTransformacionDeKiRealizarTresVecesConKiLanzaExcepcionEnLaTercera(){
		
		for (int i = 0; i < 30; i++){
			personaje.generarKi(); //ki=150
		}
		try {
			personaje.transformar();
			personaje.transformar();
		} catch (TransformacionNoPosible e) {
			Assert.fail("Las primeras dos transformaciones deberian realizarse");
		}
		try {
			personaje.transformar();
			Assert.fail("La tercera transformacion no deberia realizarse");
		} catch (TransformacionNoPosible e) {
			Assert.assertEquals(60, personaje.getPoderPelea());
		}
	}
	
	//Test mover personaje
	
	@Test
	public void testMoverPersonajeAcordeALaVelocidadYComprobarNuevaPosicion(){
		
		Posicion destino = new Posicion(0,1);
		tablero.posicionarPersonaje(personaje, new Posicion(0,0));
		
		try {
			personaje.mover(destino);
			Assert.assertEquals(destino,tablero.getPosicionPersonaje(personaje));
		} catch (MovimientoNoPosible e) {
			Assert.fail("Deberia poder moverse");
		}
		
	}
	
	@Test
	public void testMoverPersonajeAPosicionLejanaLanzaExcepcion(){

		Posicion posActual = new Posicion(0,0);
		Posicion destino = new Posicion(6,6);
		tablero.posicionarPersonaje(personaje, posActual);
		
		try {
			personaje.mover(destino);
			Assert.fail("No deberia haberse movido");
		} catch (MovimientoNoPosible e) {
			Assert.assertEquals(posActual, tablero.getPosicionPersonaje(personaje));
		}
	}
	
	@Test
	public void testMoverPersonajeAPosicionOcupadaLanzaExcepcion(){

		Posicion actual = new Posicion(0,0);
		Posicion destino = new Posicion(6,6);
		tablero.posicionarPersonaje(personaje, actual);
		tablero.posicionarPersonaje(new Freezer(tablero), destino);
		
		try {
			personaje.mover(destino);
			Assert.fail("No deberia haberse movido");
		} catch (MovimientoNoPosible e) {
			Assert.assertEquals(actual,  tablero.getPosicionPersonaje(personaje));
		}
	}

	@Test
	public void testMoverPersonajeAPosicionDespuesDeTransformacion(){

		Posicion destino = new Posicion(0,3);
		tablero.posicionarPersonaje(personaje, new Posicion(0,0));
		
		for (int i = 0; i < 6; i++){
			personaje.generarKi(); 
		}
		
		try {
			personaje.mover(destino);
			Assert.fail("No deberia haberse movido");
		} catch (MovimientoNoPosible e) {
			try {
				personaje.transformar();
				personaje.mover(destino);
				Assert.assertEquals(destino, tablero.getPosicionPersonaje(personaje));
			} catch (TransformacionNoPosible f){
				Assert.fail("Deberia haberse transformado");
			} catch (MovimientoNoPosible g){
				Assert.fail("Deberia haberse movido");
			}
		}
	}
	
	@Test
	public void testMoverPersonajeConCaminoBloquadoLanzaExcepcion(){
	
		Posicion posActual = new Posicion(0,0);
		Posicion destino = new Posicion(2,0);
		tablero.posicionarPersonaje(personaje, posActual);
		tablero.posicionarPersonaje(new Freezer(tablero), new Posicion(0,1));
		tablero.posicionarPersonaje(new Freezer(tablero), new Posicion(1,0));
		tablero.posicionarPersonaje(new Freezer(tablero), new Posicion(1,1));
		
		try {
			personaje.mover(destino);
			Assert.fail("No deberia haberse movido, el camino esta bloqueado");
		} catch (MovimientoNoPosible e) {
			Assert.assertEquals(posActual, tablero.getPosicionPersonaje(personaje));
		}
	}
	
	//Test atacar a personaje
	
	private Equipo crearEquipoUnPersonaje(Personaje personaje){
		List<Personaje> lista = new ArrayList<Personaje>();
		lista.add(personaje);
		Equipo equipo = new Equipo ("nombre",lista);
		personaje.setEquipo(equipo);
		return equipo;
	}
	
	@Test
	public void testAtacarPersonajeDistintoEquipoPosicionCorrecta(){
		
		Personaje enemigo = new Freezer(tablero);
		this.crearEquipoUnPersonaje(personaje);
		this.crearEquipoUnPersonaje(enemigo);

		tablero.posicionarPersonaje(personaje, new Posicion(0,0));
		tablero.posicionarPersonaje(enemigo, new Posicion(0,1));
		
		try {
			personaje.atacarAPersonaje(enemigo);
			Assert.assertEquals(95, enemigo.getPorcentajeVida(), 0.01);
		} catch (AtaqueNoPosible e) {
			Assert.fail("El ataque deberia realizarse");
		}
	}
	
	@Test 
	public void testAtacarPersonajeDistintoEquipoPosicionLejanaLanzaExcepcion(){
		
		Personaje enemigo = new Freezer(tablero);
		this.crearEquipoUnPersonaje(personaje);
		this.crearEquipoUnPersonaje(enemigo);
		
		tablero.posicionarPersonaje(personaje, new Posicion(0,0));
		tablero.posicionarPersonaje(enemigo, new Posicion(5,5));
		
		try {
			personaje.atacarAPersonaje(enemigo);
			Assert.fail("El ataque no deberia realizarse");
		} catch (AtaqueNoPosible e) {
			Assert.assertEquals(100,enemigo.getPorcentajeVida(),0.01);
		}
	}
	
	@Test 
	public void testAtacarPersonajeMismoEquipoLanzaExcepcion(){
		
		Personaje enemigo = new Freezer(tablero);
		List<Personaje> lista = new ArrayList<Personaje>();
		lista.add(personaje);
		lista.add(enemigo);
		personaje.setEquipo(new Equipo("equipo",lista));
		
		try {
			personaje.atacarAPersonaje(enemigo);
			Assert.fail("El ataque no deberia realizarse");
		} catch (AtaqueNoPosible e) {
			Assert.assertEquals(100,enemigo.getPorcentajeVida(),0.01);
		}
	}
	
	@Test
	public void testAtacarPersonajeDistintoEquipoPosicionCorrectaDespuesDeTransformar(){
		
		Personaje enemigo = new Freezer(tablero);
		this.crearEquipoUnPersonaje(personaje);
		this.crearEquipoUnPersonaje(enemigo);

		tablero.posicionarPersonaje(personaje, new Posicion(0,0));
		tablero.posicionarPersonaje(enemigo, new Posicion(0,4));
		
		for (int i = 0; i < 6; i++){
			personaje.generarKi();
		}
		
		try {
			personaje.atacarAPersonaje(enemigo);
			Assert.fail("El ataque no deberia realizarse");
		} catch (AtaqueNoPosible e) {
			try {
				personaje.transformar();
				personaje.atacarAPersonaje(enemigo);
				Assert.assertEquals(90, enemigo.getPorcentajeVida(), 0.01);
			} catch (TransformacionNoPosible e1) {
				Assert.fail("La transformacion deberia realizarse");
			} catch (AtaqueNoPosible e1) {
				Assert.fail("El ataque deberia realizarse");
			}
		}
	}

	
}
