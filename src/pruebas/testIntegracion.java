package pruebas;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
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

public class testIntegracion {
	
	@Test
	public void integracionNuevoJuegoConDosJugadoresYPersonajesDistribuidos() throws NombresDeEquipoIguales, EquipoInexistente {
		
		try{
			DragonBall dragonBall = new DragonBall();
			dragonBall.elegirEquipos("Guerreros Z", "Enemigos de la Tierra");
			
			String unNombre = dragonBall.getJugador1().getEquipo().getNombre();
			String otroNombre = dragonBall.getJugador2().getEquipo().getNombre();
			Assert.assertEquals("La prueba pasó: el jugador 1 es del equipo Guerreros", "Guerreros Z", unNombre);
			Assert.assertEquals("La prueba pasó: el jugador 2 es del equipo Enemigos", "Enemigos de la Tierra", otroNombre);
			
			List<Personaje> miembros = dragonBall.getJugador1().getEquipo().getMiembros();
			for (Personaje personaje : miembros){
				boolean flag = personaje.getClass() == Goku.class;
				flag = flag || personaje.getClass() == Gohan.class;
				flag = flag || personaje.getClass() == Piccolo.class;
				Assert.assertTrue(flag);
			}
			
			miembros = dragonBall.getJugador2().getEquipo().getMiembros();
			for (Personaje personaje : miembros){
				boolean flag = personaje.getClass() == Cell.class;
				flag = flag || personaje.getClass() == Freezer.class;
				flag = flag || personaje.getClass() == MajinBoo.class;
				Assert.assertTrue(flag);
			}
			
			
		}catch (NombresDeEquipoIguales e){
			Assert.fail("No se han elegido dos equipos iguales.");
		} catch (EquipoInexistente e) {
			Assert.fail("No se ha elegido ningun equipo inexistente.");
		}
		
		
		
		
		
	}
}
