package pruebas;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import modelo.excepciones.EquipoInexistente;
import modelo.excepciones.NombresDeEquipoIguales;
import modelo.excepciones.PosicionFueraDeRango;
import modelo.juego.DragonBall;
import modelo.personajes.Cell;
import modelo.personajes.Freezer;
import modelo.personajes.Gohan;
import modelo.personajes.Goku;
import modelo.personajes.MajinBoo;
import modelo.personajes.Personaje;
import modelo.personajes.Piccolo;


public class TestDragonBall {
	private DragonBall dragonBall;
	
	@Before
	public void setUp() {
		dragonBall = new DragonBall();
	}

	@Test
	public void alIniciarExisteEquipoConNombreGuerrerosZ() throws NombresDeEquipoIguales, EquipoInexistente {
		dragonBall.elegirEquipos("Guerreros Z", "Enemigos de la Tierra");
		Assert.assertEquals("La prueba pasó: existe un equipo con nombre Guerreros Z", 
				"Guerreros Z",  dragonBall.getJugador1().getEquipo().getNombre());
	}
	@Test
	public void alIniciarExisteEquipoConNombreEnemigos() throws NombresDeEquipoIguales, EquipoInexistente {
		dragonBall.elegirEquipos("Guerreros Z", "Enemigos de la Tierra");
		Assert.assertEquals("La prueba pasó: existe un equipo con nombre Enemigos de la Tierra", 
				"Enemigos de la Tierra",  dragonBall.getJugador2().getEquipo().getNombre());
	}
	
	@Test
	public void equipoGuerrerosTieneLosTresPersonajesAlIniciar() throws NombresDeEquipoIguales, EquipoInexistente {
		dragonBall.elegirEquipos("Guerreros Z", "Enemigos de la Tierra");
		List<Personaje> miembros = dragonBall.getJugador1().getEquipo().getMiembros();
		for (Personaje personaje : miembros){
			boolean flag = personaje.getClass() == Goku.class;
			flag = flag || personaje.getClass() == Gohan.class;
			flag = flag || personaje.getClass() == Piccolo.class;
			Assert.assertTrue(flag);
		}
	}
	
	@Test
	public void equipoEnemigosTieneLosTresPersonajesAlIniciar() throws NombresDeEquipoIguales, EquipoInexistente {
		
		dragonBall.elegirEquipos("Guerreros Z", "Enemigos de la Tierra");
		List<Personaje> miembros = dragonBall.getJugador2().getEquipo().getMiembros();
		for (Personaje personaje : miembros){
			boolean flag = personaje.getClass() == Cell.class;
			flag = flag || personaje.getClass() == Freezer.class;
			flag = flag || personaje.getClass() == MajinBoo.class;
			Assert.assertTrue(flag);
		}
	}
	
	@Test
	public void elegirEquiposJugador1GuerrerosJugador2Enemigos() {
		try{
			dragonBall.elegirEquipos("Guerreros Z", "Enemigos de la Tierra");
			String unNombre = dragonBall.getJugador1().getEquipo().getNombre();
			String otroNombre = dragonBall.getJugador2().getEquipo().getNombre();
			
			Assert.assertEquals("La prueba pasó: el jugador 1 es del equipo Guerreros Z", "Guerreros Z", unNombre);
			Assert.assertEquals("La prueba pasó: el jugador 2 es del equipo Enemigos de la Tierra", "Enemigos de la Tierra", otroNombre);
			
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
			
			String unNombre = dragonBall.getJugador1().getEquipo().getNombre();
			String otroNombre = dragonBall.getJugador2().getEquipo().getNombre();
			
			Assert.assertEquals("La prueba pasó: el jugador 1 es del equipo Enemigos de la Tierra", "Enemigos de la Tierra", unNombre);
			Assert.assertEquals("La prueba pasó: el jugador 2 es del equipo Guerreros Z", "Guerreros Z", otroNombre);
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