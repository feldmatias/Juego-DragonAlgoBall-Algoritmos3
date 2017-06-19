package pruebas;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import modelo.excepciones.AtaqueNoPosible;
import modelo.excepciones.MovimientoNoPosible;
import modelo.excepciones.PersonajeNoSeleccionable;
import modelo.juego.DragonBall;
import modelo.juego.Jugador;
import modelo.juego.Posicion;
import modelo.juego.Turno;
import modelo.personajes.Gohan;
import modelo.personajes.Personaje;
import modelo.personajes.Piccolo;
import modelo.utilidades.Constantes;

public class TurnoTest {
	
	private DragonBall juego;
	private Jugador jugador1;
	private Jugador jugador2;
	private Turno turno;
	private Personaje personajeJugador2;
	private Personaje personajeJugadorActual;
	private Posicion destino;
	
	@Before
	public void setUp() throws PersonajeNoSeleccionable{
		juego = new DragonBall();
		juego.establecerEquipoJugador1(Constantes.GUERREROS);
		juego.establecerEquipoJugador2(Constantes.ENEMIGOS);
		this.jugador1 = juego.getJugador1();
		this.jugador2 = juego.getJugador2();
		this.turno = new Turno (jugador1, jugador2, juego.getTablero());
		this.personajeJugadorActual = jugador1.getEquipo().getMiembros().get("Goku");
		turno.seleccionarPersonaje(personajeJugadorActual);
		this.personajeJugador2 = jugador2.getEquipo().getMiembros().get("Freezer");
		juego.getTablero().reposicionarPersonaje(personajeJugadorActual ,new Posicion (0,0));
		juego.getTablero().reposicionarPersonaje(personajeJugador2 ,new Posicion (0,1)); // Los posiciono cerca
		this.destino = new Posicion (1,1); //Posicion cercana a ambos para poder moverse
	}
	
	@Test
	public void testNoEsElTurnodelEquipo2AlIniciar (){
		Assert.assertNotEquals(jugador2, turno.jugadorActual());
	}
	
	@Test
	public void testEsElTurnoDelEquipo1AlIniciar (){
		Assert.assertEquals(jugador1, turno.jugadorActual());
	}	
		
	@Test
	public void testGenerarKiAlComienzoDelTurno(){
		for (Personaje personaje : jugador1.getEquipo().getMiembros().values()){
			int kiEsperado = personaje.getKi() + Personaje.kiGeneradoAlComienzoTurno;
			new Turno (jugador1, jugador2, juego.getTablero());
			Assert.assertEquals(kiEsperado, personaje.getKi());
		}
	}
	
	@Test
	public void testNoGenerarKiAlComienzoDelTurnoDelOtroJugador(){
		for (Personaje personaje : jugador2.getEquipo().getMiembros().values()){
			int kiEsperado = personaje.getKi() ;
			new Turno (jugador1, jugador2, juego.getTablero());
			Assert.assertEquals(kiEsperado, personaje.getKi());
		}
	}
	
	@Test
	public void testJugadorActualPuedeAtacarEnemigo(){
		
		try {
			turno.atacarEnemigo(personajeJugador2);
			Assert.assertTrue(personajeJugador2.getPorcentajeVida() < 100); 
		} catch (AtaqueNoPosible e) {
			Assert.fail("Deberia haber atacado");
		}
	}
	
	@Test
	public void testDespuesDeAtacarNoCambiaElTurno() throws AtaqueNoPosible{
		turno.atacarEnemigo(personajeJugador2);
		Assert.assertEquals(jugador1, turno.jugadorActual());
	}
	
	@Test
	public void testJugadorActualPuedeMover() {
		
		try {
			turno.moverPersonaje(destino);
			Assert.assertTrue(juego.getTablero().getPosicionPersonaje(personajeJugadorActual).equals(destino)); 
		} catch (MovimientoNoPosible e) {
			Assert.fail("Deberia haberse movido");
		}
	}
	
	@Test
	public void testDespuesDeMoverNoCambiaElTurno() throws MovimientoNoPosible{
		turno.moverPersonaje(destino );
		Assert.assertEquals(jugador1, turno.jugadorActual());
	}
	
	@Test
	public void testDespuesDeAtacarNoPuedeVolverAAtacar() throws AtaqueNoPosible{
		try {
			turno.atacarEnemigo(personajeJugador2);
		} catch (AtaqueNoPosible e) {
			Assert.fail("Deberia haber atacado");
		}
		try {
			turno.atacarEnemigo(personajeJugador2);
			Assert.fail("No deberia haber atacado");
		} catch (AtaqueNoPosible error) {
			Assert.assertEquals(Constantes.ErrorAtaqueYaRealizado, error.getMensaje());
		}
	}
	
	@Test
	public void testDespuesDeMoverNoPuedeVolverAMover() throws MovimientoNoPosible{
		Posicion destino2 = destino.sumarPosicion(destino);
		try {
			turno.moverPersonaje(destino);
		} catch (MovimientoNoPosible e) {
			Assert.fail("Deberia haberse movido");
		}
		try {
			turno.moverPersonaje(destino2);
			Assert.fail("No deberia haberse movido");
		} catch (MovimientoNoPosible error) {
			Assert.assertEquals(Constantes.ErrorMovimientoYaRealizado, error.getMensaje());
		}
	}
	
	@Test
	public void testJugadorPuedeMoverYDespuesAtacar(){
		
		try {
			turno.atacarEnemigo(personajeJugador2);
		} catch ( AtaqueNoPosible e) {
			Assert.fail("Deberia haber atacado");
		}
		try {
			turno.moverPersonaje(destino);
		} catch (MovimientoNoPosible e) {
			Assert.fail("Deberia haberse movido");
		}
		Assert.assertTrue(true);
	}
	
	@Test
	public void testJugadorPuedeAtacarYDespuesMover(){
		
		try {
			turno.moverPersonaje(destino);
		} catch (MovimientoNoPosible e) {
			Assert.fail("Deberia haberse movido");
		}
		try {
			turno.atacarEnemigo(personajeJugador2);
		} catch (AtaqueNoPosible e) {
			Assert.fail("Deberia haber atacado");
		}
		Assert.assertTrue(true);
	}
	
	@Test
	public void testDespuesDeMoverYAtacarCambiaDeTurno(){
		
		try {
			turno.atacarEnemigo(personajeJugador2);
		} catch (AtaqueNoPosible e) {
			Assert.fail("Deberia haber atacado");
		}
		try {
			turno.moverPersonaje(destino);
		} catch (MovimientoNoPosible e) {
			Assert.fail("Deberia haberse movido");
		}
		Assert.assertEquals(jugador2, turno.jugadorActual());
	}
	
	@Test
	public void testAlCambiarDeTurnoGeneraKiNuevoJugador() throws AtaqueNoPosible,  MovimientoNoPosible{
		int kiEsperado = personajeJugador2.getKi() + Personaje.kiGeneradoAlComienzoTurno;
		turno.atacarEnemigo(personajeJugador2);
		turno.moverPersonaje(destino);
		
		Assert.assertEquals(kiEsperado, personajeJugador2.getKi());

	}
	
	@Test
	public void testPuedoSeleccionarOtroPersonajeDelJugadorYMoverlo() throws MovimientoNoPosible{
		Posicion origen = juego.getTablero().getPosicionPersonaje(personajeJugadorActual);
		Personaje companiero = jugador1.getEquipo().getMiembros().get("Gohan"); 
		juego.getTablero().reposicionarPersonaje(companiero ,new Posicion (1,0)); //Lo posiciono cerca de los otros
		try {
			turno.seleccionarPersonaje(companiero);
			turno.moverPersonaje(destino);
			Assert.assertTrue(juego.getTablero().getPosicionPersonaje(companiero).equals(destino));
			Assert.assertTrue(juego.getTablero().getPosicionPersonaje(personajeJugadorActual).equals(origen));
		} catch (PersonajeNoSeleccionable e) {
			Assert.fail("Deberia haber seleccionado el personaje");
		}
	}
	
	@Test
	public void testNoPuedoSeleccionarOtroPersonajeDelOtroJugador(){
		try {
			turno.seleccionarPersonaje(personajeJugador2);
			Assert.fail("No deberia haber seleccionado al personaje");
		} catch (PersonajeNoSeleccionable e){
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void testDespuesDeMoverCambiaDeTurnoSiNoPuedeAtacar(){
		juego.getTablero().reposicionarPersonaje(personajeJugador2 ,new Posicion (7,7)); // Posiciono al enemigo lejos
		try {
			turno.moverPersonaje(destino);
		} catch (MovimientoNoPosible e) {
		}
		Assert.assertEquals(jugador2, turno.jugadorActual());
	}

	@Test
	public void testDespuesDeAtacarCambiaDeTurnoSiNoPuedeMover(){
		Posicion posDestino2 = new Posicion(1,0);
		jugador1.getEquipo().getMiembros().get("Gohan").recibirAtaque(Gohan.vidaInicial * 2);
		jugador1.getEquipo().getMiembros().get("Piccolo").recibirAtaque(Piccolo.vidaInicial * 2); //Mato a los companieros
		Personaje enemigo2 = jugador2.getEquipo().getMiembros().get("Cell");
		Personaje enemigo3 = jugador2.getEquipo().getMiembros().get("Majin Boo");
		juego.getTablero().posicionarPersonaje(enemigo2, destino);
		juego.getTablero().posicionarPersonaje(enemigo3, posDestino2); //Bloqueo al personaje
		try {
			turno.atacarEnemigo(personajeJugador2);
		} catch (AtaqueNoPosible e) {
		}
		Assert.assertEquals(jugador2, turno.jugadorActual());
	}
	
	
}