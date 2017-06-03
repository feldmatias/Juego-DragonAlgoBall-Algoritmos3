package pruebas;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
	
	public void elegirEquiposJugador1GuerrerosJugador2Enemigos() {
		dragonBall.elegirEquipos("Guerreros", "Enemigos");
		
	}

	
}