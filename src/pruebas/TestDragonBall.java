package pruebas;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import modelo.excepciones.EquipoNoDisponible;
import modelo.juego.DragonBall;
import modelo.juego.Equipo;
import modelo.personajes.Cell;
import modelo.personajes.Freezer;
import modelo.personajes.Gohan;
import modelo.personajes.Goku;
import modelo.personajes.MajinBoo;
import modelo.personajes.Personaje;
import modelo.personajes.Piccolo;
import modelo.utilidades.Constantes;


public class TestDragonBall {
	private DragonBall dragonBall;
	
	@Before
	public void setUp() {
		dragonBall = new DragonBall();
	}

	@Test
	public void alIniciarEstaDisponibleEquipoConNombreGuerrerosZ(){
		//dragonBall.elegirEquipos(Constantes.GUERREROS, Constantes.ENEMIGOS);
		//Assert.assertEquals("La prueba pas�: existe un equipo con nombre Guerreros Z", 
				//Constantes.GUERREROS, dragonBall.getJugador1().getEquipo().getNombre());
		
		Assert.assertTrue( dragonBall.equipoEstaDisponible(Constantes.GUERREROS) );
		
	}
	@Test
	public void alIniciarEstaDisponibleEquipoConNombreEnemigos() {
//		dragonBall.elegirEquipos(Constantes.GUERREROS, Constantes.ENEMIGOS);
//		Assert.assertEquals("La prueba pas�: existe un equipo con nombre Enemigos de la Tierra", 
//				"Enemigos de la Tierra",  dragonBall.getJugador2().getEquipo().getNombre());
		
		Assert.assertTrue( dragonBall.equipoEstaDisponible( Constantes.GUERREROS ) );
		}
	
	@Test
	public void seleccionarEquipoJugador1ComoGuerrerosEstableceEquipoCorrecto() throws EquipoNoDisponible{
		dragonBall.establecerEquipoJugador1( Constantes.GUERREROS );
		String nombreEquipoEsperado = Constantes.GUERREROS;
		Equipo equipo = dragonBall.getJugador1().getEquipo();
		String nombreEquipoObtenido = equipo.getNombre();
		
		Assert.assertEquals(nombreEquipoEsperado, nombreEquipoObtenido);
	}
	
	@Test
	public void seleccionarEquipoJugador1ComoEnemigosEstableceEquipoCorrecto(){
		try{
			dragonBall.establecerEquipoJugador1(Constantes.ENEMIGOS);
			String nombreEquipoEsperado = Constantes.ENEMIGOS;
			Equipo equipo = dragonBall.getJugador1().getEquipo();
			String nombreEquipoObtenido = equipo.getNombre();
			Assert.assertEquals(nombreEquipoEsperado, nombreEquipoObtenido);
		}catch(EquipoNoDisponible e){
			Assert.fail("La prueba fallo, deberia estar disponible el equipo");
		}
	}
	
//AGREGAR PRUEBAS PARA JUGADOR2 ??	
	
	@Test
	public void equipoGuerrerosTieneLosTresPersonajesAlIniciar() throws EquipoNoDisponible {
		//dragonBall.elegirEquipos(Constantes.GUERREROS, Constantes.ENEMIGOS);<>
		
		try{
			dragonBall.establecerEquipoJugador1( Constantes.GUERREROS );
			List<Personaje> miembros = dragonBall.getJugador1().getEquipo().getMiembros();
			for (Personaje personaje : miembros){
				boolean flag = personaje.getClass() == Goku.class;
				flag = flag || personaje.getClass() == Gohan.class;
				flag = flag || personaje.getClass() == Piccolo.class;
				Assert.assertTrue(flag);
			}
		}catch(EquipoNoDisponible e){
			Assert.fail("La prueba fallo, el equipo deberia estar disponible");
		}
	}
	
	@Test
	public void equipoEnemigosTieneLosTresPersonajesAlIniciar(){
		
		//dragonBall.elegirEquipos(Constantes.GUERREROS, Constantes.ENEMIGOS);
		try{
			dragonBall.establecerEquipoJugador2(Constantes.ENEMIGOS);
			List<Personaje> miembros = dragonBall.getJugador2().getEquipo().getMiembros();
			for (Personaje personaje : miembros){
				boolean flag = personaje.getClass() == Cell.class;
				flag = flag || personaje.getClass() == Freezer.class;
				flag = flag || personaje.getClass() == MajinBoo.class;
				Assert.assertTrue(flag);
			}
		}catch(EquipoNoDisponible e){
			Assert.fail("La prueba fallo, el equipo deberia existir");
		}
	}
	
	@Test
	public void elegirEquiposJugador1GuerrerosJugador2Enemigos() {
		try{
			//dragonBall.elegirEquipos(Constantes.GUERREROS, "Enemigos de la Tierra");
			dragonBall.establecerEquipoJugador1( Constantes.GUERREROS );
			dragonBall.establecerEquipoJugador2( Constantes.ENEMIGOS );
			
			String nombreEquipo1 = dragonBall.getJugador1().getEquipo().getNombre();
			String nombreEquipo2 = dragonBall.getJugador2().getEquipo().getNombre();
			
			Assert.assertEquals("La prueba pas�: el jugador 1 es del equipo Guerreros Z", Constantes.GUERREROS, nombreEquipo1);
			Assert.assertEquals("La prueba pas�: el jugador 2 es del equipo Enemigos de la Tierra", Constantes.ENEMIGOS, nombreEquipo2);
			
		//} catch (NombresDeEquipoIguales e){
			//Assert.fail("No se han elegido dos equipos iguales.");
		}catch( EquipoNoDisponible e ){
				Assert.fail("La prueba fallo, los equipos deberian estar disponibles");
		}
	}
	
	@Test
	public void elegirEquiposJugador1EnemigosJugador2Guerreros() {
		try{
			//dragonBall.elegirEquipos("Enemigos de la Tierra", Constantes.GUERREROS);
			
			dragonBall.establecerEquipoJugador1(Constantes.ENEMIGOS);
			dragonBall.establecerEquipoJugador2(Constantes.GUERREROS);
			
			String nombreEquipo1 = dragonBall.getJugador1().getEquipo().getNombre();
			String nombreEquipo2 = dragonBall.getJugador2().getEquipo().getNombre();
			
			Assert.assertEquals("La prueba pas�: el jugador 1 es del equipo Enemigos de la Tierra", Constantes.ENEMIGOS, nombreEquipo1);
			Assert.assertEquals("La prueba pas�: el jugador 2 es del equipo Guerreros Z", Constantes.GUERREROS, nombreEquipo2);
		//} catch (NombresDeEquipoIguales e){
			//Assert.fail("No se han elegido dos equipos iguales.");
		}catch (EquipoNoDisponible e){
			Assert.fail("La prueba fallo, los equipos deberian estar disponibles");
		}
		
	}
	
	@Test
	public void elegirUnEquipoInexistenteLanzaExcepcion() {
		String equipoInexistente = "Equipo Verde";
		try {
//			dragonBall.elegirEquipos("Equipo Verde", Constantes.ENEMIGOS);
			dragonBall.establecerEquipoJugador1( equipoInexistente );
//		} catch (NombresDeEquipoIguales e) {
//			Assert.fail("No se han elegido dos equipos iguales.");
		} catch( EquipoNoDisponible e ){}
		
	}
	
	@Test
	public void elegirDosEquiposInexistentesLanzaExcepcion() {
		String equipoInexistente1 = "Equipo Verde";
		String equipoInexistente2 = "Equipo Rojo";
		try {
//			dragonBall.elegirEquipos("Equipo Verde", "Equipo Rojo");
			dragonBall.establecerEquipoJugador1(equipoInexistente1);
			dragonBall.establecerEquipoJugador2(equipoInexistente2);
//		} catch (NombresDeEquipoIguales e) {
//			Assert.fail("No se han elegido dos equipos iguales.");
			
		} catch (EquipoNoDisponible e) {}
	}
	
	@Test
	public void elegirDosEquiposIgualesLanzaExcepcion() {
		try {
//			dragonBall.elegirEquipos(Constantes.GUERREROS, Constantes.GUERREROS);
			dragonBall.establecerEquipoJugador1(Constantes.GUERREROS);
			dragonBall.establecerEquipoJugador2(Constantes.GUERREROS);
			Assert.fail("El jugador 2 no deberia haber elegido el mismo equipo");
//		} catch (NombresDeEquipoIguales e) {
		}catch (EquipoNoDisponible e){}
	}
	

	
}