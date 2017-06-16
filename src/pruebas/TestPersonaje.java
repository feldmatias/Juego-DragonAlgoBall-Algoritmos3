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
import modelo.juego.Posicion;
import modelo.juego.Tablero;
import modelo.personajes.Freezer;
import modelo.personajes.Goku;
import modelo.personajes.Personaje;
import modelo.utilidades.Constantes;

public class TestPersonaje {
	
	private Personaje personaje;
	private Tablero tablero;
	
	@Before
	public void setUp(){
		int sizeTablero = 8;
		this.tablero = new Tablero(sizeTablero);
		this.personaje = new Goku(tablero);
	}
	
	@Test
	public void testKiInicialEs0(){
		Assert.assertEquals(0, personaje.getKi());
	}
	
	@Test
	public void testGenerarKiUnaVez(){
		personaje.generarKi();
		Assert.assertEquals(Personaje.kiGeneradoAlComienzoTurno, personaje.getKi());
	}
	
	@Test
	public void testGenerarKiVariasVeces(){
		int cantidadVeces = 4;
		for (int i = 0; i< cantidadVeces; i++){
			personaje.generarKi();
		}
		int kiEsperado = Personaje.kiGeneradoAlComienzoTurno * cantidadVeces;
		Assert.assertEquals(kiEsperado, personaje.getKi());
	}
	
	@Test
	public void testPorcentajeVidaInicialEs100(){
		Assert.assertEquals(100, personaje.getPorcentajeVida(), Constantes.porcentajeEsperado);
	}
	
	@Test
	public void testObtenerPoderPeleaModoNormal(){
		Assert.assertEquals(Goku.poderPeleaNormal, personaje.getPoderPelea(), Constantes.porcentajeEsperado);
	}
	
	@Test
	public void testDistanciaAtaqueModoNormal(){
		Assert.assertEquals(Goku.distanciaAtaqueNormal, personaje.getDistanciaAtaque());
	}
	
	@Test
	public void testObtenerVelocidadModoNormal(){
		Assert.assertEquals(Goku.velocidadNormal, personaje.getVelocidad());
	}
	
	@Test
	public void testRecibirAtaqueDeMayorPoderDePelea(){
		double danioAtaque = Goku.poderPeleaNormal * 2;
		personaje.recibirAtaque(danioAtaque); 
		double vidaEsperada = Goku.vidaInicial - danioAtaque;
		Assert.assertEquals(vidaEsperada, personaje.getVidaActual(), Constantes.porcentajeEsperado);
	}
	
	@Test
	public void testRecibirAtaqueDeMenorPoderDePelea(){
		double danioAtaque = Goku.poderPeleaNormal / 2;
		personaje.recibirAtaque(danioAtaque); 
		double vidaEsperada = Goku.vidaInicial - danioAtaque * Personaje.disminucionPoderPeleaAlAtacarConMenorPoderPelea;
		Assert.assertEquals(vidaEsperada, personaje.getVidaActual(), Constantes.porcentajeEsperado);
	}
		
	//Test transformaciones con ki, igual para todos los personajes sin transformaciones especiales
	
	@Test 
	public void testTransformacionDeKiRealizarSinKiLanzaExcepcion(){

		try {
			personaje.transformar();
			Assert.fail("La transformacion no deberia realizarse");
		} catch (TransformacionNoPosible e) {
			Assert.assertEquals(Goku.poderPeleaNormal, personaje.getPoderPelea(), Constantes.porcentajeEsperado);
		}
	}
	
	@Test 
	public void testTransformacionDeKiRealizarConKiComprobarNuevoPoderPelea(){
		
		for (int i = 0; i < Constantes.cantidadParaGenerarKiSuficiente; i++){
			personaje.generarKi();
		}
		try {
			personaje.transformar();
			Assert.assertEquals(Goku.poderPeleaPrimerTransformacion,personaje.getPoderPelea(), Constantes.porcentajeEsperado);
		} catch (TransformacionNoPosible e) {
			Assert.fail("La transformacion deberia realizarse");
		}
	}
	
	@Test 
	public void testTransformacionDeKiRealizarConKiComprobarNuevaDistanciaAtaque(){
		
		for (int i = 0; i < Constantes.cantidadParaGenerarKiSuficiente; i++){
			personaje.generarKi();
		}
		try {
			personaje.transformar();
			Assert.assertEquals(Goku.distanciaAtaquePrimerTransformacion, personaje.getDistanciaAtaque());
		} catch (TransformacionNoPosible e) {
			Assert.fail("La transformacion deberia realizarse");
		}
	}
	
	@Test 
	public void testTransformacionDeKiRealizarConKiComprobarNuevaVelocidad(){
		
		for (int i = 0; i < Constantes.cantidadParaGenerarKiSuficiente; i++){
			personaje.generarKi();
		}
		try {
			personaje.transformar();
			Assert.assertEquals(Goku.velocidadPrimerTranformacion,personaje.getVelocidad());
		} catch (TransformacionNoPosible e) {
			Assert.fail("La transformacion deberia realizarse");
		}
	}
	
	@Test 
	public void testTransformacionDeKiRealizarConKiComprobarKiRestante(){
		
		for (int i = 0; i < Constantes.cantidadParaGenerarKiSuficiente; i++){
			personaje.generarKi(); 
		}
		int kiEsperado = personaje.getKi() - Goku.kiNecesarioPrimerTransformacion;
		try {
			personaje.transformar();
			Assert.assertEquals(kiEsperado,personaje.getKi());
		} catch (TransformacionNoPosible e) {
			Assert.fail("La transformacion deberia realizarse");
		}
	}
	
	@Test 
	public void testTransformacionDeKiTransformarDosVecesLanzaExcepcionEnLaSegunda(){

		int cantidadVecesGenerarKi = Goku.kiNecesarioPrimerTransformacion / Personaje.kiGeneradoAlComienzoTurno;
		
		for (int i = 0; i < cantidadVecesGenerarKi; i++){
			personaje.generarKi(); 
		}
		try {
			personaje.transformar();
			Assert.assertEquals(Goku.poderPeleaPrimerTransformacion,personaje.getPoderPelea(), Constantes.porcentajeEsperado);
		} catch (TransformacionNoPosible e) {
			Assert.fail("La transformacion deberia realizarse");
		}
		try {
			personaje.transformar();
			Assert.fail("La transformacion no deberia realizarse");
		} catch (TransformacionNoPosible f) {
			Assert.assertEquals(Goku.poderPeleaPrimerTransformacion, personaje.getPoderPelea(), Constantes.porcentajeEsperado);
		}
	}

	@Test 
	public void testTransformacionDeKiRealizarDosVecesConKiComprobarKiRestante(){
		
		for (int i = 0; i < Constantes.cantidadParaGenerarKiSuficiente; i++){
			personaje.generarKi(); 
		}
		try {
			int kiEsperado = personaje.getKi() - Goku.kiNecesarioPrimerTransformacion - Goku.kiNecesarioSegundaTransformacion;
			personaje.transformar();
			personaje.transformar();
			Assert.assertEquals(kiEsperado,personaje.getKi());
		} catch (TransformacionNoPosible e) {
			Assert.fail("Las transformaciones deberian realizarse");
		}
	}
	
	@Test 
	public void testTransformacionDeKiRealizarDosVecesConKiComprobarPoderPelea(){
		
		for (int i = 0; i < Constantes.cantidadParaGenerarKiSuficiente; i++){
			personaje.generarKi(); 
		}
		try {
			personaje.transformar();
			personaje.transformar();
			Assert.assertEquals(Goku.poderPeleaSegundaTransformacion, personaje.getPoderPelea(), Constantes.porcentajeEsperado);
		} catch (TransformacionNoPosible e) {
			Assert.fail("Las transformaciones deberian realizarse");
		}
	}
	
	@Test 
	public void testTransformacionDeKiRealizarTresVecesConKiLanzaExcepcionEnLaTercera(){
		
		for (int i = 0; i < Constantes.cantidadParaGenerarKiSuficiente * 3 ; i++){
			personaje.generarKi(); 
		}
		int kiEsperado = personaje.getKi() - Goku.kiNecesarioPrimerTransformacion - Goku.kiNecesarioSegundaTransformacion;
		try {
			personaje.transformar();
			personaje.transformar();
		} catch (TransformacionNoPosible e) {
		}
		try {
			personaje.transformar();
			Assert.fail("La tercera transformacion no deberia realizarse");
		} catch (TransformacionNoPosible e) {
			Assert.assertEquals(kiEsperado, personaje.getKi());
		}
	}
	
	//Test mover personaje
	
	@Test
	public void testMoverPersonajeAcordeALaVelocidadYComprobarNuevaPosicion(){
		
		Posicion posActual = new Posicion(0,0);
		Posicion destino = new Posicion(0,1);
		tablero.posicionarPersonaje(personaje, posActual);
		
		try {
			personaje.mover(destino);
			Assert.assertTrue(destino.equals(tablero.getPosicionPersonaje(personaje)));
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
			Assert.assertTrue(posActual.equals(tablero.getPosicionPersonaje(personaje)));
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
			Assert.assertTrue(actual.equals(tablero.getPosicionPersonaje(personaje)));
		}
	}

	@Test
	public void testMoverPersonajeAPosicionDespuesDeTransformacion(){

		Posicion posActual = new Posicion(0,0);
		Posicion destino = new Posicion(0,Goku.velocidadPrimerTranformacion);
		tablero.posicionarPersonaje(personaje, posActual);
		
		for (int i = 0; i < Constantes.cantidadParaGenerarKiSuficiente; i++){
			personaje.generarKi(); 
		}
		
		try {
			personaje.mover(destino);
			Assert.fail("No deberia haberse movido");
		} catch (MovimientoNoPosible e) {
			try {
				personaje.transformar();
				personaje.mover(destino);
				Assert.assertTrue(destino.equals(tablero.getPosicionPersonaje(personaje)));
			} catch (TransformacionNoPosible f){
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
			Assert.assertTrue(posActual.equals(tablero.getPosicionPersonaje(personaje)));
		}
	}
	
	//Test atacar a personaje
	
	private Equipo crearEquipoUnPersonaje(Personaje personaje, String nombreEquipo){
		List<Personaje> lista = new ArrayList<Personaje>();
		lista.add(personaje);
		Equipo equipo = new Equipo (nombreEquipo, lista);
		return equipo;
	}
	
	@Test
	public void testAtacarPersonajeDistintoEquipoPosicionCorrecta(){
		
		Personaje enemigo = new Freezer(tablero);
		this.crearEquipoUnPersonaje(personaje, Constantes.GUERREROS);
		this.crearEquipoUnPersonaje(enemigo, Constantes.ENEMIGOS);

		tablero.posicionarPersonaje(personaje, new Posicion(0,0));
		tablero.posicionarPersonaje(enemigo, new Posicion(0,1));
		
		try {
			personaje.atacarAPersonaje(enemigo);
			double vidaEsperada = Freezer.vidaInicial - Goku.poderPeleaNormal;
			Assert.assertEquals(vidaEsperada, enemigo.getVidaActual(), Constantes.porcentajeEsperado);
		} catch (AtaqueNoPosible e) {
			Assert.fail("El ataque deberia realizarse");
		}
	}
	
	@Test 
	public void testAtacarPersonajeDistintoEquipoPosicionLejanaLanzaExcepcion(){
		
		Personaje enemigo = new Freezer(tablero);
		this.crearEquipoUnPersonaje(personaje, Constantes.GUERREROS);
		this.crearEquipoUnPersonaje(enemigo, Constantes.ENEMIGOS);
		
		tablero.posicionarPersonaje(personaje, new Posicion(0,0));
		tablero.posicionarPersonaje(enemigo, new Posicion(0,Goku.distanciaAtaqueNormal * 2));
		
		try {
			personaje.atacarAPersonaje(enemigo);
			Assert.fail("El ataque no deberia realizarse");
		} catch (AtaqueNoPosible e) {
			Assert.assertEquals(Freezer.vidaInicial,enemigo.getVidaActual(),Constantes.porcentajeEsperado);
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
			Assert.assertEquals(Freezer.vidaInicial,enemigo.getVidaActual(),Constantes.porcentajeEsperado);
		}
	}
	
	@Test
	public void testAtacarPersonajeDistintoEquipoPosicionCorrectaDespuesDeTransformar(){
		
		Personaje enemigo = new Freezer(tablero);
		this.crearEquipoUnPersonaje(personaje, Constantes.GUERREROS);
		this.crearEquipoUnPersonaje(enemigo, Constantes.ENEMIGOS);
		
		tablero.posicionarPersonaje(personaje, new Posicion(0,0));
		tablero.posicionarPersonaje(enemigo, new Posicion(0,Goku.distanciaAtaquePrimerTransformacion));
		
		for (int i = 0; i < Constantes.cantidadParaGenerarKiSuficiente; i++){
			personaje.generarKi();
		}
		
		try {
			personaje.atacarAPersonaje(enemigo);
			Assert.fail("El ataque no deberia realizarse");
		} catch (AtaqueNoPosible e) {
			try {
				personaje.transformar();
				personaje.atacarAPersonaje(enemigo);
				double vidaEsperada = Freezer.vidaInicial - Goku.poderPeleaPrimerTransformacion;
				Assert.assertEquals(vidaEsperada, enemigo.getVidaActual(), Constantes.porcentajeEsperado);
			} catch (TransformacionNoPosible e1) {
				Assert.fail("La transformacion deberia realizarse");
			} catch (AtaqueNoPosible e1) {
				Assert.fail("El ataque deberia realizarse");
			}
		}
	}
	
	//Test Ataque especial potenciador

	@Test 
	public void testAtaqueEspecialSinKiNecesarioLanzaExcepcion(){
		
		Personaje enemigo = new Freezer(tablero);
		this.crearEquipoUnPersonaje(personaje, Constantes.GUERREROS);
		this.crearEquipoUnPersonaje(enemigo, Constantes.ENEMIGOS);
		tablero.posicionarPersonaje(personaje, new Posicion(0,0));
		tablero.posicionarPersonaje(enemigo, new Posicion(0,1));
		
		try {
			personaje.realizarAtaqueEspecial(enemigo);
			Assert.fail("El ataque no deberia realizarse");
		} catch (AtaqueNoPosible e) {
			Assert.assertEquals(Freezer.vidaInicial,enemigo.getVidaActual(),Constantes.porcentajeEsperado);
		}
	}
	
	@Test 
	public void testRealizarAtaqueEspecialYComprobarVida(){
		for (int i = 0; i < Constantes.cantidadParaGenerarKiSuficiente; i++ ){
			personaje.generarKi();
		}
		
		Personaje enemigo = new Freezer(tablero);
		this.crearEquipoUnPersonaje(personaje, Constantes.GUERREROS);
		this.crearEquipoUnPersonaje(enemigo, Constantes.ENEMIGOS);
		tablero.posicionarPersonaje(personaje, new Posicion(0,0));
		tablero.posicionarPersonaje(enemigo, new Posicion(0,1));
		
		try {
			personaje.realizarAtaqueEspecial(enemigo);
			double vidaEsperada = Freezer.vidaInicial - Goku.poderPeleaNormal * Goku.multiplicadorAtaqueEspecial;
			Assert.assertEquals(vidaEsperada , enemigo.getVidaActual(), Constantes.porcentajeEsperado);
		} catch (AtaqueNoPosible e) {
			Assert.fail("El ataque deberia realizarse");
		}
	}
	
	@Test 
	public void testRealizarAtaqueEspecialYComprobarKi(){
		for (int i = 0; i < Constantes.cantidadParaGenerarKiSuficiente; i++ ){
			personaje.generarKi();
		}
		int kiEsperado = personaje.getKi() - Goku.kiNecesarioAtaqueEspecial;
		
		Personaje enemigo = new Freezer(tablero);
		this.crearEquipoUnPersonaje(personaje, Constantes.GUERREROS);
		this.crearEquipoUnPersonaje(enemigo, Constantes.ENEMIGOS);
		tablero.posicionarPersonaje(personaje, new Posicion(0,0));
		tablero.posicionarPersonaje(enemigo, new Posicion(0,1));
		
		try {
			personaje.realizarAtaqueEspecial(enemigo);
			Assert.assertEquals(kiEsperado, personaje.getKi());
		} catch (AtaqueNoPosible e) {
			Assert.fail("El ataque deberia realizarse");
		}
	}
	
}
