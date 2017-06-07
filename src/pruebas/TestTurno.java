package pruebas;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import modelo.excepciones.AtaqueNoPosible;
import modelo.excepciones.AtaqueYaRealizado;
import modelo.excepciones.EquipoNoDisponible;
import modelo.excepciones.MovimientoNoPosible;
import modelo.excepciones.MovimientoYaRealizado;
import modelo.excepciones.PersonajeNoSeleccionable;
import modelo.juego.DragonBall;
import modelo.juego.Jugador;
import modelo.juego.Turno;
import modelo.personajes.Personaje;
import modelo.tablero.Posicion;
import modelo.utilidades.Constantes;

public class TestTurno {
	
	private DragonBall juego;
	private Jugador jugador1;
	private Jugador jugador2;
	private Turno turno;
	
	@Before
	public void setUp() throws EquipoNoDisponible{
		juego = new DragonBall();
//		juego.elegirEquipos(Constantes.GUERREROS, Constantes.ENEMIGOS); //Crea un turno interno que no uso
		juego.establecerEquipoJugador1(Constantes.GUERREROS);
		juego.establecerEquipoJugador2(Constantes.ENEMIGOS);
		this.jugador1 = juego.getJugador1();
		this.jugador2 = juego.getJugador2();
		this.turno = new Turno (jugador1, jugador2);
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
		Personaje personaje = jugador1.getEquipo().getMiembros().get(0);
		int kiActual = personaje.getKi();
		new Turno (jugador1, jugador2);
		Assert.assertEquals(kiActual + 5, personaje.getKi());
	}
	
	@Test
	public void testNoGenerarKiAlComienzoDelTurnoDelOtroJugador(){
		Personaje personaje = jugador2.getEquipo().getMiembros().get(0);
		int kiActual = personaje.getKi();
		new Turno (jugador1, jugador2);
		Assert.assertEquals(kiActual, personaje.getKi());
	}
	
	@Test
	public void testJugadorActualPuedeAtacarEnemigo() throws AtaqueYaRealizado{
		Personaje enemigo = jugador2.getEquipo().getMiembros().get(0);
		juego.getTablero().reposicionarPersonaje(enemigo,new Posicion (1, Constantes.SIZE_TABLERO / 2));
		
		try {
			turno.atacarEnemigo(enemigo);
			Assert.assertTrue(enemigo.getPorcentajeVida() < 100); 
		} catch (AtaqueNoPosible e) {
			Assert.fail("Deberia haber atacado");
		}
	}
	
	@Test
	public void testDespuesDeAtacarNoCambiaElTurno() throws AtaqueNoPosible, AtaqueYaRealizado{
		Personaje enemigo = jugador2.getEquipo().getMiembros().get(0);
		juego.getTablero().reposicionarPersonaje(enemigo,new Posicion (1, Constantes.SIZE_TABLERO / 2));
		turno.atacarEnemigo(enemigo);
		Assert.assertEquals(jugador1, turno.jugadorActual());
	}
	
	@Test
	public void testJugadorActualPuedeMover() throws MovimientoYaRealizado{
		Posicion destino = new Posicion (1, Constantes.SIZE_TABLERO / 2);
		
		try {
			turno.moverPersonaje(destino);
			Assert.assertTrue(juego.getTablero().getPosicionPersonaje(jugador1.getEquipo().getMiembros().get(0)).equals(destino)); 
		} catch (MovimientoNoPosible e) {
			Assert.fail("Deberia haberse movido");
		}
	}
	
	@Test
	public void testDespuesDeMoverNoCambiaElTurno() throws MovimientoNoPosible, MovimientoYaRealizado{
		turno.moverPersonaje(new Posicion (1, Constantes.SIZE_TABLERO / 2) );
		Assert.assertEquals(jugador1, turno.jugadorActual());
	}
	
	@Test
	public void testDespuesDeAtacarNoPuedeVolverAAtacar() throws AtaqueNoPosible{
		Personaje enemigo = jugador2.getEquipo().getMiembros().get(0);
		juego.getTablero().reposicionarPersonaje(enemigo,new Posicion (1, Constantes.SIZE_TABLERO / 2));
		try {
			turno.atacarEnemigo(enemigo);
		} catch (AtaqueYaRealizado e) {
			Assert.fail("Deberia haber atacado");
		}
		try {
			turno.atacarEnemigo(enemigo);
			Assert.fail("No deberia haber atacado");
		} catch (AtaqueYaRealizado e) {
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void testDespuesDeMoverNoPuedeVolverAMover() throws MovimientoNoPosible{
		Posicion destino1 = new Posicion (1, Constantes.SIZE_TABLERO / 2);
		Posicion destino2 = new Posicion (2, Constantes.SIZE_TABLERO / 2);
		try {
			turno.moverPersonaje(destino1);
		} catch (MovimientoYaRealizado e) {
			Assert.fail("Deberia haberse movido");
		}
		try {
			turno.moverPersonaje(destino2);
			Assert.fail("No deberia haberse movido");
		} catch (MovimientoYaRealizado e) {
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void testJugadorPuedeMoverYDespuesAtacar(){
		Personaje enemigo = jugador2.getEquipo().getMiembros().get(0);
		juego.getTablero().reposicionarPersonaje(enemigo,new Posicion (1, Constantes.SIZE_TABLERO / 2));
		Posicion destino = new Posicion (2, Constantes.SIZE_TABLERO / 2);
		
		try {
			turno.atacarEnemigo(enemigo);
		} catch (AtaqueYaRealizado | AtaqueNoPosible e) {
			Assert.fail("Deberia haber atacado");
		}
		try {
			turno.moverPersonaje(destino);
		} catch (MovimientoYaRealizado | MovimientoNoPosible e) {
			Assert.fail("Deberia haberse movido");
		}
		Assert.assertTrue(true);
	}
	
	@Test
	public void testJugadorPuedeAtacarYDespuesMover(){
		Personaje enemigo = jugador2.getEquipo().getMiembros().get(0);
		juego.getTablero().reposicionarPersonaje(enemigo,new Posicion (1, Constantes.SIZE_TABLERO / 2));
		Posicion destino = new Posicion (2, Constantes.SIZE_TABLERO / 2);
		
		try {
			turno.moverPersonaje(destino);
		} catch (MovimientoYaRealizado | MovimientoNoPosible e) {
			Assert.fail("Deberia haberse movido");
		}
		try {
			turno.atacarEnemigo(enemigo);
		} catch (AtaqueYaRealizado | AtaqueNoPosible e) {
			Assert.fail("Deberia haber atacado");
		}
		Assert.assertTrue(true);
	}
	
	@Test
	public void testDespuesDeMoverYAtacarCambiaDeTurno(){
		Personaje enemigo = jugador2.getEquipo().getMiembros().get(0);
		juego.getTablero().reposicionarPersonaje(enemigo,new Posicion (1, Constantes.SIZE_TABLERO / 2));
		Posicion destino = new Posicion (2, Constantes.SIZE_TABLERO / 2);
		
		try {
			turno.atacarEnemigo(enemigo);
		} catch (AtaqueYaRealizado | AtaqueNoPosible e) {
			Assert.fail("Deberia haber atacado");
		}
		try {
			turno.moverPersonaje(destino);
		} catch (MovimientoYaRealizado | MovimientoNoPosible e) {
			Assert.fail("Deberia haberse movido");
		}
		Assert.assertEquals(jugador2, turno.jugadorActual());
	}
	
	@Test
	public void testAlCambiarDeTurnoGeneraKiNuevoJugador() throws AtaqueYaRealizado, AtaqueNoPosible, MovimientoYaRealizado, MovimientoNoPosible{
		Personaje enemigo = jugador2.getEquipo().getMiembros().get(0);
		int kiActual = enemigo.getKi();
		juego.getTablero().reposicionarPersonaje(enemigo,new Posicion (1, Constantes.SIZE_TABLERO / 2));
		Posicion destino = new Posicion (2, Constantes.SIZE_TABLERO / 2);

		turno.atacarEnemigo(enemigo);
		turno.moverPersonaje(destino);
		
		Assert.assertEquals(kiActual + 5, enemigo.getKi());

	}
	
	@Test
	public void testPuedoSeleccionarOtroPersonajeDelJugadorYMoverlo() throws MovimientoYaRealizado, MovimientoNoPosible{
		Posicion origen = new Posicion (0, Constantes.SIZE_TABLERO / 2);
		Posicion destino = new Posicion (1, Constantes.SIZE_TABLERO / 2);
		Personaje personaje1 = jugador1.getEquipo().getMiembros().get(1);
		Personaje personaje2 = jugador1.getEquipo().getMiembros().get(0);
		try {
			turno.seleccionarPersonaje(personaje1);
			turno.moverPersonaje(destino);
			Assert.assertTrue(juego.getTablero().getPosicionPersonaje(personaje1).equals(destino));
			Assert.assertTrue(juego.getTablero().getPosicionPersonaje(personaje2).equals(origen));
		} catch (PersonajeNoSeleccionable e) {
			Assert.fail("Deberia haber seleccionado el personaje");
		}
	}
	
	@Test
	public void testNoPuedoSeleccionarOtroPersonajeDelOtroJugador(){
		Personaje personaje1 = jugador2.getEquipo().getMiembros().get(0);
		try {
			turno.seleccionarPersonaje(personaje1);
			Assert.fail("No deberia haber seleccionado al personaje");
		} catch (PersonajeNoSeleccionable e){
			Assert.assertTrue(true);
		}
	}
}