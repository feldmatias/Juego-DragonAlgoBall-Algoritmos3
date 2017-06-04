package pruebas;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import modelo.excepciones.EquipoInexistente;
import modelo.excepciones.NombresDeEquipoIguales;
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
			
			Posicion posicionEsperadaGuerreros1 = new Posicion(0,size/2);
			Posicion posicionEsperadaGuerreros2 = new Posicion(0,(size/2)+1);
			Posicion posicionEsperadaGuerreros3 = new Posicion(0,(size/2)-1);
			for (Personaje personaje : miembrosGuerreros){
				Posicion posicionReal = dragonBall.getTablero().getPosicionPersonaje(personaje);
				boolean flag = posicionReal.equals(posicionEsperadaGuerreros1);
				flag = flag || posicionReal.equals(posicionEsperadaGuerreros2);
				flag = flag || posicionReal.equals(posicionEsperadaGuerreros3);
				Assert.assertTrue(flag);
			}
			Posicion posicionEsperadaEnemigos1 = new Posicion(size-1,size/2);
			Posicion posicionEsperadaEnemigos2 = new Posicion(size-1,(size/2)+1);
			Posicion posicionEsperadaEnemigos3 = new Posicion(size-1,(size/2)-1);
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
}
