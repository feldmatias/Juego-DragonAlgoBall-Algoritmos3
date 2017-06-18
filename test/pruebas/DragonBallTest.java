package pruebas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import modelo.excepciones.EquipoNoDisponible;
import modelo.juego.DragonBall;
import modelo.juego.Equipo;
import modelo.personajes.Personaje;
import modelo.utilidades.Constantes;


public class DragonBallTest {
	
	public static final String equipoInexistente1 = "Equipo Verde";
	public static final String equipoInexistente2 = "Equipo Rojo";
	
	private DragonBall dragonBall;
	
	@Before
	public void setUp() {
		dragonBall = new DragonBall();
	}

	@Test
	public void alIniciarEstaDisponibleEquipoConNombreGuerrerosZ(){
		Assert.assertTrue( dragonBall.equipoEstaDisponible(Constantes.GUERREROS) );
	}
	
	@Test
	public void alIniciarEstaDisponibleEquipoConNombreEnemigos() {
		Assert.assertTrue( dragonBall.equipoEstaDisponible( Constantes.GUERREROS ) );
	}
	
	@Test
	public void seleccionarEquipoJugador1ComoGuerrerosEstableceEquipoCorrecto() {
		dragonBall.establecerEquipoJugador1( Constantes.GUERREROS );
		String nombreEquipoEsperado = Constantes.GUERREROS;
		Equipo equipo = dragonBall.getJugador1().getEquipo();
		String nombreEquipoObtenido = equipo.getNombre();
		
		Assert.assertEquals(nombreEquipoEsperado, nombreEquipoObtenido);
	}
	
	@Test
	public void seleccionarEquipoJugador1ComoEnemigosEstableceEquipoCorrecto(){
		dragonBall.establecerEquipoJugador1(Constantes.ENEMIGOS);
		String nombreEquipoEsperado = Constantes.ENEMIGOS;
		Equipo equipo = dragonBall.getJugador1().getEquipo();
		String nombreEquipoObtenido = equipo.getNombre();
		Assert.assertEquals(nombreEquipoEsperado, nombreEquipoObtenido);

	}
	
	@Test
	public void seleccionarEquipoJugador2ComoGuerrerosEstableceEquipoCorrecto() {
		dragonBall.establecerEquipoJugador2( Constantes.GUERREROS );
		String nombreEquipoEsperado = Constantes.GUERREROS;
		Equipo equipo = dragonBall.getJugador2().getEquipo();
		String nombreEquipoObtenido = equipo.getNombre();
		
		Assert.assertEquals(nombreEquipoEsperado, nombreEquipoObtenido);
	}
	
	@Test
	public void seleccionarEquipoJugador2ComoEnemigosEstableceEquipoCorrecto(){
		dragonBall.establecerEquipoJugador2(Constantes.ENEMIGOS);
		String nombreEquipoEsperado = Constantes.ENEMIGOS;
		Equipo equipo = dragonBall.getJugador2().getEquipo();
		String nombreEquipoObtenido = equipo.getNombre();
		Assert.assertEquals(nombreEquipoEsperado, nombreEquipoObtenido);

	}
	
	@Test
	public void equipoGuerrerosTieneLosTresPersonajesAlIniciar(){

		dragonBall.establecerEquipoJugador1( Constantes.GUERREROS );
		Collection<Personaje> miembros = dragonBall.getJugador1().getEquipo().getMiembros().values();
		List<String> nombresEsperados = new ArrayList<String>();
		nombresEsperados.add("Goku");
		nombresEsperados.add("Gohan");
		nombresEsperados.add("Piccolo");
		
		for (Personaje personaje : miembros){
			nombresEsperados.remove(personaje.getNombre());
		}
		Assert.assertTrue(nombresEsperados.size() == 0);
	}
	
	@Test
	public void equipoEnemigosTieneLosTresPersonajesAlIniciar(){
		
		dragonBall.establecerEquipoJugador1( Constantes.ENEMIGOS );
		Collection<Personaje> miembros = dragonBall.getJugador1().getEquipo().getMiembros().values();
		List<String> nombresEsperados = new ArrayList<String>();
		nombresEsperados.add("Cell");
		nombresEsperados.add("Majin Boo");
		nombresEsperados.add("Freezer");
		
		for (Personaje personaje : miembros){
			nombresEsperados.remove(personaje.getNombre());
		}
		Assert.assertTrue(nombresEsperados.size() == 0);
	}
	
	@Test
	public void elegirEquiposJugador1GuerrerosJugador2Enemigos() {

		dragonBall.establecerEquipoJugador1( Constantes.GUERREROS );
		dragonBall.establecerEquipoJugador2( Constantes.ENEMIGOS );
			
		String nombreEquipo1 = dragonBall.getJugador1().getEquipo().getNombre();
		String nombreEquipo2 = dragonBall.getJugador2().getEquipo().getNombre();
			
		Assert.assertEquals("La prueba paso: el jugador 1 es del equipo Guerreros Z", Constantes.GUERREROS, nombreEquipo1);
		Assert.assertEquals("La prueba paso: el jugador 2 es del equipo Enemigos de la Tierra", Constantes.ENEMIGOS, nombreEquipo2);
			
	}
	
	@Test
	public void elegirEquiposJugador1EnemigosJugador2Guerreros() {

		dragonBall.establecerEquipoJugador1(Constantes.ENEMIGOS);
		dragonBall.establecerEquipoJugador2(Constantes.GUERREROS);
			
		String nombreEquipo1 = dragonBall.getJugador1().getEquipo().getNombre();
		String nombreEquipo2 = dragonBall.getJugador2().getEquipo().getNombre();
			
		Assert.assertEquals("La prueba paso: el jugador 1 es del equipo Enemigos de la Tierra", Constantes.ENEMIGOS, nombreEquipo1);
		Assert.assertEquals("La prueba paso: el jugador 2 es del equipo Guerreros Z", Constantes.GUERREROS, nombreEquipo2);
		
	}
	
	@Test
	public void elegirUnEquipoInexistenteLanzaExcepcion() {
		try {
			dragonBall.establecerEquipoJugador1( equipoInexistente1 );
			Assert.fail("Deberia lanzar excepcion");
		} catch( EquipoNoDisponible e ){
			Assert.assertTrue(true);
		}
		
	}
	
	@Test
	public void elegirDosEquiposInexistentesLanzaExcepcion() {
		try {
			dragonBall.establecerEquipoJugador1( equipoInexistente1 );
			dragonBall.establecerEquipoJugador1( equipoInexistente2 );
			Assert.fail("Deberia lanzar excepcion");
		} catch( EquipoNoDisponible e ){
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void elegirDosEquiposIgualesLanzaExcepcion() {
		try {
			dragonBall.establecerEquipoJugador1(Constantes.GUERREROS);
			dragonBall.establecerEquipoJugador2(Constantes.GUERREROS);
			Assert.fail("El jugador 2 no deberia haber elegido el mismo equipo");
		}catch (EquipoNoDisponible e){
			Assert.assertTrue(true);
		}
	}
	

	
}
