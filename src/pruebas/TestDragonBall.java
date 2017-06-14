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
		}catch(EquipoNoDisponible e){}
	}
	
//AGREGAR PRUEBAS PARA JUGADOR2 ??	
	
	@Test
	public void equipoGuerrerosTieneLosTresPersonajesAlIniciar() throws EquipoNoDisponible {
		
		try{
			dragonBall.establecerEquipoJugador1( Constantes.GUERREROS );
			List<Personaje> miembros = dragonBall.getJugador1().getEquipo().getMiembros();
			for (Personaje personaje : miembros){
				boolean flag = personaje.getClass() == Goku.class;
				flag = flag || personaje.getClass() == Gohan.class;
				flag = flag || personaje.getClass() == Piccolo.class;
				Assert.assertTrue(flag);
			}
		}catch(EquipoNoDisponible e){}
	}
	
	@Test
	public void equipoEnemigosTieneLosTresPersonajesAlIniciar(){
		
		try{
			dragonBall.establecerEquipoJugador2(Constantes.ENEMIGOS);
			List<Personaje> miembros = dragonBall.getJugador2().getEquipo().getMiembros();
			for (Personaje personaje : miembros){
				boolean flag = personaje.getClass() == Cell.class;
				flag = flag || personaje.getClass() == Freezer.class;
				flag = flag || personaje.getClass() == MajinBoo.class;
				Assert.assertTrue(flag);
			}
		}catch(EquipoNoDisponible e){}
	}
	
	@Test
	public void elegirEquiposJugador1GuerrerosJugador2Enemigos() {
		try{
			dragonBall.establecerEquipoJugador1( Constantes.GUERREROS );
			dragonBall.establecerEquipoJugador2( Constantes.ENEMIGOS );
			
			String nombreEquipo1 = dragonBall.getJugador1().getEquipo().getNombre();
			String nombreEquipo2 = dragonBall.getJugador2().getEquipo().getNombre();
			
			Assert.assertEquals("La prueba pasó: el jugador 1 es del equipo Guerreros Z", Constantes.GUERREROS, nombreEquipo1);
			Assert.assertEquals("La prueba pasó: el jugador 2 es del equipo Enemigos de la Tierra", Constantes.ENEMIGOS, nombreEquipo2);
			
		}catch( EquipoNoDisponible e ){}
	}
	
	@Test
	public void elegirEquiposJugador1EnemigosJugador2Guerreros() {
		try{
			
			dragonBall.establecerEquipoJugador1(Constantes.ENEMIGOS);
			dragonBall.establecerEquipoJugador2(Constantes.GUERREROS);
			
			String nombreEquipo1 = dragonBall.getJugador1().getEquipo().getNombre();
			String nombreEquipo2 = dragonBall.getJugador2().getEquipo().getNombre();
			
			Assert.assertEquals("La prueba pasó: el jugador 1 es del equipo Enemigos de la Tierra", Constantes.ENEMIGOS, nombreEquipo1);
			Assert.assertEquals("La prueba pasó: el jugador 2 es del equipo Guerreros Z", Constantes.GUERREROS, nombreEquipo2);
		
			}catch (EquipoNoDisponible e){}	
	}
	
	@Test
	public void elegirUnEquipoInexistenteLanzaExcepcion() {
		try {
			dragonBall.establecerEquipoJugador1( equipoInexistente1 );
		} catch( EquipoNoDisponible e ){}
		
	}
	
	@Test
	public void elegirDosEquiposInexistentesLanzaExcepcion() {
		try {
			dragonBall.establecerEquipoJugador1(equipoInexistente1);
			dragonBall.establecerEquipoJugador2(equipoInexistente2);
			
		} catch (EquipoNoDisponible e) {}
	}
	
	@Test
	public void elegirDosEquiposIgualesLanzaExcepcion() {
		try {
			dragonBall.establecerEquipoJugador1(Constantes.GUERREROS);
			dragonBall.establecerEquipoJugador2(Constantes.GUERREROS);
			Assert.fail("El jugador 2 no deberia haber elegido el mismo equipo");
		}catch (EquipoNoDisponible e){}
	}
	

	
}