package pruebas;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import modelo.excepciones.AtaqueNoPosible;
import modelo.excepciones.AtaqueYaRealizado;
import modelo.excepciones.EquipoInexistente;
import modelo.excepciones.MovimientoNoPosible;
import modelo.excepciones.MovimientoYaRealizado;
import modelo.excepciones.NombresDeEquipoIguales;
import modelo.excepciones.PersonajeNoSeleccionable;
import modelo.excepciones.TransformacionNoPosible;
import modelo.juego.DragonBall;
import modelo.personajes.Cell;
import modelo.personajes.Freezer;
import modelo.personajes.Gohan;
import modelo.personajes.Goku;
import modelo.personajes.MajinBoo;
import modelo.personajes.Personaje;
import modelo.personajes.Piccolo;
import modelo.tablero.Posicion;
import modelo.utilidades.Constantes;

public class TestIntegracion {
	
	@Test
	public void integracionNuevoJuegoConDosJugadoresYPersonajesDistribuidos() throws NombresDeEquipoIguales, EquipoInexistente {
		
		try{
			DragonBall dragonBall = new DragonBall();
			dragonBall.elegirEquipos(Constantes.GUERREROS, Constantes.ENEMIGOS);
			
			String unNombre = dragonBall.getJugador1().getEquipo().getNombre();
			String otroNombre = dragonBall.getJugador2().getEquipo().getNombre();
			Assert.assertEquals("La prueba pasó: el jugador 1 es del equipo Guerreros", Constantes.GUERREROS, unNombre);
			Assert.assertEquals("La prueba pasó: el jugador 2 es del equipo Enemigos", Constantes.ENEMIGOS, otroNombre);
			
			List<Personaje> miembrosGuerreros = dragonBall.getJugador1().getEquipo().getMiembros();
			for (Personaje personaje : miembrosGuerreros){
				boolean flag = personaje.getClass() == Goku.class;
				flag = flag || personaje.getClass() == Gohan.class;
				flag = flag || personaje.getClass() == Piccolo.class;
				Assert.assertTrue(flag);
			}
			
			List<Personaje> miembrosEnemigos = dragonBall.getJugador2().getEquipo().getMiembros();
			for (Personaje personaje : miembrosEnemigos){
				boolean flag = personaje.getClass() == Cell.class;
				flag = flag || personaje.getClass() == Freezer.class;
				flag = flag || personaje.getClass() == MajinBoo.class;
				Assert.assertTrue(flag);
			}
			int size = Constantes.SIZE_TABLERO;
			
			Posicion posicionEsperadaGuerreros1 = new Posicion(0,Constantes.POS_CENTRAL);
			Posicion posicionEsperadaGuerreros2 = new Posicion(0,(Constantes.POS_CENTRAL)+1);
			Posicion posicionEsperadaGuerreros3 = new Posicion(0,(Constantes.POS_CENTRAL)-1);
			for (Personaje personaje : miembrosGuerreros){
				Posicion posicionReal = dragonBall.getTablero().getPosicionPersonaje(personaje);
				boolean flag = posicionReal.equals(posicionEsperadaGuerreros1);
				flag = flag || posicionReal.equals(posicionEsperadaGuerreros2);
				flag = flag || posicionReal.equals(posicionEsperadaGuerreros3);
				Assert.assertTrue(flag);
			}
			Posicion posicionEsperadaEnemigos1 = new Posicion(size-1,Constantes.POS_CENTRAL);
			Posicion posicionEsperadaEnemigos2 = new Posicion(size-1,(Constantes.POS_CENTRAL)+1);
			Posicion posicionEsperadaEnemigos3 = new Posicion(size-1,(Constantes.POS_CENTRAL)-1);
			for (Personaje personaje : miembrosEnemigos){
				Posicion posicionReal = dragonBall.getTablero().getPosicionPersonaje(personaje);
				boolean flag = posicionReal.equals(posicionEsperadaEnemigos1);
				flag = flag || posicionReal.equals(posicionEsperadaEnemigos2);
				flag = flag || posicionReal.equals(posicionEsperadaEnemigos3);
				Assert.assertTrue(flag);
			}
			
		}catch (NombresDeEquipoIguales e){
			Assert.fail("No se han elegido dos equipos iguales.");
		} catch (EquipoInexistente e) {
			Assert.fail("No se ha elegido ningun equipo inexistente.");
		}
		
		
	}
	
	@Test
	public void testIntegracionJugarJuego() throws NombresDeEquipoIguales, EquipoInexistente, MovimientoYaRealizado, MovimientoNoPosible, PersonajeNoSeleccionable, AtaqueYaRealizado, AtaqueNoPosible, TransformacionNoPosible{
		DragonBall juego = new DragonBall();
		juego.elegirEquipos(Constantes.GUERREROS, Constantes.ENEMIGOS);
		
		if (juego.getJugadorActual() == juego.getJugador2()){
			juego.jugadorActualTerminarTurno();
		}
		
		Personaje goku = juego.getJugador1().getEquipo().getMiembros().get(0);
		Personaje freezer = juego.getJugador2().getEquipo().getMiembros().get(1);
		
		//Turno guerreros
		juego.jugadorActualSeleccionarPersonaje(goku);
		juego.jugadorActualMoverAPosicion(new Posicion (2,3));
		juego.jugadorActualTerminarTurno();
		
		//Turno enemigos
		juego.jugadorActualSeleccionarPersonaje(freezer);
		juego.jugadorActualMoverAPosicion(new Posicion (4,6));
		juego.jugadorActualTerminarTurno();
		
		//Turno guerreros
		juego.jugadorActualMoverAPosicion(new Posicion(3,1));
		juego.jugadorActualTerminarTurno();
		
		//Turno enemigos
		juego.jugadorActualMoverAPosicion(new Posicion (5,3));
		juego.jugadorActualTerminarTurno();
		
		//Turno guerreros
		juego.jugadorActualAtacarAEnemigo(freezer);
		Assert.assertEquals(95, freezer.getPorcentajeVida(), 0.01);
		juego.jugadorActualTerminarTurno();
		
		//Turno enemigos
		juego.jugadorActualAtacarAEnemigo(goku);
		Assert.assertEquals(96, goku.getPorcentajeVida(), 0.01);
		juego.jugadorActualTerminarTurno();
		
		//Turno guerreros
		juego.jugadorActualMoverAPosicion(new Posicion (5,1));
		juego.jugadorActualAtacarAEnemigo(freezer);
		Assert.assertEquals(90, freezer.getPorcentajeVida(), 0.01);
		
		//Turno enemigos
		Assert.assertEquals(juego.getJugador2(), juego.getJugadorActual());
		juego.jugadorActualAtacarAEnemigo(goku);
		Assert.assertEquals(92, goku.getPorcentajeVida(), 0.01);
		juego.jugadorActualMoverAPosicion(new Posicion (5,2));
		
		//Turno guerreros
		juego.jugadorActualTransformar();
		juego.jugadorActualAtacarAEnemigo(freezer);
		Assert.assertEquals(80, freezer.getPorcentajeVida(), 0.01);
		juego.jugadorActualMoverAPosicion(new Posicion (2,4));
		
		//Turno enemigos
		juego.jugadorActualTerminarTurno();
		
		//Turno guerreros
		Assert.assertEquals(juego.getJugador1(), juego.getJugadorActual());
	}
	
}
