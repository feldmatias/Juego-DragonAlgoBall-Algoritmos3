package pruebas;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import modelo.excepciones.EquipoInexistente;
import modelo.excepciones.EquipoYaElegido;
import modelo.excepciones.NombresDeEquipoIguales;
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
	public void alIniciarExisteEquipoConNombreGuerrerosZ() throws NombresDeEquipoIguales, EquipoInexistente {
		//dragonBall.elegirEquipos(Constantes.GUERREROS, Constantes.ENEMIGOS);
		//Assert.assertEquals("La prueba pasó: existe un equipo con nombre Guerreros Z", 
				//Constantes.GUERREROS, dragonBall.getJugador1().getEquipo().getNombre());
		
		Assert.assertTrue( dragonBall.existeEquipo(Constantes.GUERREROS) );
		
	}
	@Test
	public void alIniciarExisteEquipoConNombreEnemigos() throws NombresDeEquipoIguales, EquipoInexistente {
//		dragonBall.elegirEquipos(Constantes.GUERREROS, Constantes.ENEMIGOS);
//		Assert.assertEquals("La prueba pasó: existe un equipo con nombre Enemigos de la Tierra", 
//				"Enemigos de la Tierra",  dragonBall.getJugador2().getEquipo().getNombre());
		
		Assert.assertTrue( dragonBall.existeEquipo( Constantes.GUERREROS ) );
		}
	
	@Test
	public void seleccionarEquipoJugador1ComoGuerrerosEstableceEquipoCorrecto() throws EquipoInexistente, EquipoYaElegido{
		dragonBall.establecerEquipoJugador1( Constantes.GUERREROS );
		String nombreEquipoEsperado = Constantes.GUERREROS;
		Equipo equipo = dragonBall.getJugador1().getEquipo();
		String nombreEquipoObtenido = equipo.getNombre();
		
		Assert.assertEquals(nombreEquipoEsperado, nombreEquipoObtenido);
	}
	
	@Test
	public void seleccionarEquipoJugador1ComoEnemigosEstableceEquipoCorrecto() throws EquipoInexistente, EquipoYaElegido{
		try{
			dragonBall.establecerEquipoJugador1(Constantes.ENEMIGOS);
			String nombreEquipoEsperado = Constantes.ENEMIGOS;
			Equipo equipo = dragonBall.getJugador1().getEquipo();
			String nombreEquipoObtenido = equipo.getNombre();
			Assert.assertEquals(nombreEquipoEsperado, nombreEquipoObtenido);
		}catch(EquipoInexistente e){
			Assert.fail("La prueba fallo, deberia existir el equipo");
		}catch(EquipoYaElegido e){
			Assert.fail("La prueba fallo no se eligieron equipos iguales");
		}
	}
	
//AGREGAR PRUEBAS PARA JUGADOR2 ??	
	
	@Test
	public void equipoGuerrerosTieneLosTresPersonajesAlIniciar() throws EquipoInexistente, EquipoYaElegido {
		//dragonBall.elegirEquipos(Constantes.GUERREROS, Constantes.ENEMIGOS);<>
		
		dragonBall.establecerEquipoJugador1( Constantes.GUERREROS );
		List<Personaje> miembros = dragonBall.getJugador1().getEquipo().getMiembros();
		for (Personaje personaje : miembros){
			boolean flag = personaje.getClass() == Goku.class;
			flag = flag || personaje.getClass() == Gohan.class;
			flag = flag || personaje.getClass() == Piccolo.class;
			Assert.assertTrue(flag);
		}
	}
	
	@Test
	public void equipoEnemigosTieneLosTresPersonajesAlIniciar() throws EquipoInexistente, EquipoYaElegido {
		
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
		}catch(EquipoInexistente e){
			Assert.fail("La prueba fallo, el equipo deberia existir");
		}catch(EquipoYaElegido e){
			Assert.fail("La prueba fallo, no se eligio un equipo ya elegido");
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
			
			Assert.assertEquals("La prueba pasó: el jugador 1 es del equipo Guerreros Z", Constantes.GUERREROS, nombreEquipo1);
			Assert.assertEquals("La prueba pasó: el jugador 2 es del equipo Enemigos de la Tierra", Constantes.ENEMIGOS, nombreEquipo2);
			
		//} catch (NombresDeEquipoIguales e){
			//Assert.fail("No se han elegido dos equipos iguales.");
		}catch( EquipoYaElegido e ){
				Assert.fail("No han elegido los mismos equipos");
		} catch (EquipoInexistente e ) {
			Assert.fail("No se ha elegido ningun equipo inexistente.");
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
			
			Assert.assertEquals("La prueba pasó: el jugador 1 es del equipo Enemigos de la Tierra", Constantes.ENEMIGOS, nombreEquipo1);
			Assert.assertEquals("La prueba pasó: el jugador 2 es del equipo Guerreros Z", Constantes.GUERREROS, nombreEquipo2);
		//} catch (NombresDeEquipoIguales e){
			//Assert.fail("No se han elegido dos equipos iguales.");
		}catch (EquipoYaElegido e){
			Assert.fail("No se han elegido los mismos equipos");
		} catch (EquipoInexistente e) {
			Assert.fail("No se ha elegido ningun equipo inexistente.");
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
		} catch( EquipoYaElegido e ){
			Assert.fail( "No se ha ingresado un equipo ya elegido" );
		} catch (EquipoInexistente e) {}
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
			
		}catch (EquipoYaElegido e){
			Assert.fail("No se han ingresado equipos ya elegidos");
		} catch (EquipoInexistente e) {}
	}
	
	@Test
	public void elegirDosEquiposIgualesLanzaExcepcion() {
		try {
//			dragonBall.elegirEquipos(Constantes.GUERREROS, Constantes.GUERREROS);
			dragonBall.establecerEquipoJugador1(Constantes.GUERREROS);
			dragonBall.establecerEquipoJugador2(Constantes.GUERREROS);
			Assert.fail("El jugador 2 no deberia haber elegido el mismo equipo");
//		} catch (NombresDeEquipoIguales e) {
		}catch (EquipoYaElegido e){
		} catch (EquipoInexistente e) {
			Assert.fail("No se ha elegido ningun equipo inexistente.");
		}
	}
	

	
}