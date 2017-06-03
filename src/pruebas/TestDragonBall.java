package pruebas;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import modelo.excepciones.EquipoInexistente;
import modelo.excepciones.NombresDeEquipoIguales;
import modelo.juego.DragonBall;


public class TestDragonBall {
	private DragonBall dragonBall;
	
	@Before
	public void setUp(){
		dragonBall = new DragonBall();
		dragonBall.nuevoJuego();
	}

	@Test
	public void alIniciarExisteEquipoConNombreGuerrerosZ() {
		Assert.assertEquals("La prueba pasó: existe un equipo con nombre Guerreros Z", true, dragonBall.existeEquipo("Guerreros Z"));
	}
	@Test
	public void alIniciarExisteEquipoConNombreEnemigos() {
		Assert.assertEquals("La prueba pasó: existe un equipo con nombre Enemigos de la Tierra", true, dragonBall.existeEquipo("Enemigos de la Tierra"));
	}
	@Test
	public void equipoGuerrerosTieneLosTresPersonajesAlIniciar() {
		
		Boolean flag = dragonBall.personajePerteneceAEquipo("Goku", "Guerreros Z");
		flag = flag && (dragonBall.personajePerteneceAEquipo("Gohan", "Guerreros Z"));
		flag = flag && (dragonBall.personajePerteneceAEquipo("Piccolo", "Guerreros Z"));
		
		Assert.assertEquals("La prueba pasó: Goku está en el equipo Guerreros Z", true, flag);
	}
	
	@Test
	public void equipoEnemigosTieneLosTresPersonajesAlIniciar() {
		
		Boolean flag = dragonBall.personajePerteneceAEquipo("Cell", "Enemigos de la Tierra");
		flag = flag && (dragonBall.personajePerteneceAEquipo("Freezer", "Enemigos de la Tierra"));
		flag = flag && (dragonBall.personajePerteneceAEquipo("Majin Boo", "Enemigos de la Tierra"));
		
		Assert.assertEquals("La prueba pasó: Goku está en el equipo Enemigos de la Tierra", true, flag);
	}
	
	@Test
	public void elegirEquiposJugador1GuerrerosJugador2Enemigos() {
		try{
			dragonBall.elegirEquipos("Guerreros Z", "Enemigos de la Tierra");
			String unNombre = dragonBall.getNombreEquipoDelJugador("Jugador 1");
			String otroNombre = dragonBall.getNombreEquipoDelJugador("Jugador 2");
			
			Assert.assertEquals("La prueba pasó: el jugador 1 es del equipo Guerreros Z", true, (unNombre == "Guerreros Z"));
			Assert.assertEquals("La prueba pasó: el jugador 2 es del equipo Enemigos de la Tierra", true, (otroNombre == "Enemigos de la Tierra"));
			
		} catch (NombresDeEquipoIguales e){
			Assert.fail("No se han elegido dos equipos iguales.");
		} catch (EquipoInexistente e) {
			Assert.fail("No se ha elegido ningun equipo inexistente.");
		}
	}
	@Test
	public void elegirEquiposJugador1EnemigosJugador2Guerreros() {
		try{
			
			dragonBall.elegirEquipos("Enemigos de la Tierra", "Guerreros Z");
			
			String unNombre = dragonBall.getNombreEquipoDelJugador("Jugador 1");
			String otroNombre = dragonBall.getNombreEquipoDelJugador("Jugador 2");
			
			Assert.assertEquals("La prueba pasó: el jugador 1 es del equipo Enemigos de la Tierra", true, (unNombre == "Enemigos de la Tierra"));
			Assert.assertEquals("La prueba pasó: el jugador 2 es del equipo Guerreros Z", true, (otroNombre == "Guerreros Z"));
		} catch (NombresDeEquipoIguales e){
			Assert.fail("No se han elegido dos equipos iguales.");
		} catch (EquipoInexistente e) {
			Assert.fail("No se ha elegido ningun equipo inexistente.");
		}
		
	}
	
	@Test
	public void elegirUnEquipoInexistenteLanzaExcepcion() {
		try {
			dragonBall.elegirEquipos("Equipo Verde", "Enemigos de la Tierra");
		} catch (NombresDeEquipoIguales e) {
			Assert.fail("No se han elegido dos equipos iguales.");
		} catch (EquipoInexistente e) {}
	}
	
	@Test
	public void elegirDosEquiposInexistentesLanzaExcepcion() {
		try {
			dragonBall.elegirEquipos("Equipo Verde", "Equipo Rojo");
		} catch (NombresDeEquipoIguales e) {
			Assert.fail("No se han elegido dos equipos iguales.");
		} catch (EquipoInexistente e) {}
	}
	
	@Test
	public void elegirDosEquiposIgualesLanzaExcepcion() {
		try {
			dragonBall.elegirEquipos("Guerreros Z", "Guerreros Z");
		} catch (NombresDeEquipoIguales e) {
		} catch (EquipoInexistente e) {
			Assert.fail("No se ha elegido ningun equipo inexistente.");
		}
	}
	

	
}