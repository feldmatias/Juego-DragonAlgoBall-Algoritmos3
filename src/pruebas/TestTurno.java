package pruebas;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import modelo.excepciones.AtaqueNoPosible;
import modelo.excepciones.EquipoInexistente;
import modelo.excepciones.NombresDeEquipoIguales;
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
	public void setUp() throws NombresDeEquipoIguales, EquipoInexistente{
		juego = new DragonBall();
		juego.elegirEquipos(Constantes.GUERREROS, Constantes.ENEMIGOS); //Crea un turno interno que no uso
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
		for (Personaje personaje: jugador1.getEquipo().getMiembros()){
			Assert.assertEquals(10, personaje.getKi());
		}
	}
	
	@Test
	public void testNoGenerarKiAlComienzoDelTurnoDelOtroJugador(){
		for (Personaje personaje: jugador2.getEquipo().getMiembros()){
			Assert.assertEquals(0, personaje.getKi());
		}
	}
	
	@Test
	public void testJugadorActualPuedeAtacarEnemigo(){
		Personaje enemigo = jugador2.getEquipo().getMiembros().get(0);
		juego.getTablero().reposicionarPersonaje(enemigo,new Posicion (1, Constantes.SIZE_TABLERO / 2));
		System.out.println(enemigo.toString());
		try {
			turno.atacarEnemigo(enemigo);
			Assert.assertTrue(enemigo.getPorcentajeVida() < 100); 
		} catch (AtaqueNoPosible e) {
			Assert.fail("Deberia haber atacado");
		}
	}
}