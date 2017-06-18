package pruebas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import modelo.juego.DragonBall;
import modelo.juego.Posicion;
import modelo.personajes.Personaje;
import modelo.utilidades.Constantes;

public class IntegracionTest {
	
	@Test
	public void integracionNuevoJuegoConDosJugadoresYPersonajesDistribuidos() {

		DragonBall dragonBall = new DragonBall();
		dragonBall.establecerEquipoJugador1(Constantes.GUERREROS);
		dragonBall.establecerEquipoJugador2(Constantes.ENEMIGOS);
		dragonBall.iniciar();
			
		//Se comprueba seleccion de equipos correcta
		String unNombre = dragonBall.getJugador1().getEquipo().getNombre();
		String otroNombre = dragonBall.getJugador2().getEquipo().getNombre();
		Assert.assertEquals("La prueba paso: el jugador 1 es del equipo Guerreros", Constantes.GUERREROS, unNombre);
		Assert.assertEquals("La prueba paso: el jugador 2 es del equipo Enemigos", Constantes.ENEMIGOS, otroNombre);
			
		//Se comprueban personajes equipo guerreros
		Collection<Personaje> miembrosGuerreros = dragonBall.getJugador1().getEquipo().getMiembros().values();
		List<String> nombresEsperados = new ArrayList<String>();
		nombresEsperados.add("Goku");
		nombresEsperados.add("Gohan");
		nombresEsperados.add("Piccolo");
			
		for (Personaje personaje : miembrosGuerreros){
			nombresEsperados.remove(personaje.getNombre());
		}
		Assert.assertTrue(nombresEsperados.size() == 0);
			
			
		//Se comprueban personajes equipo enemigos
		Collection<Personaje> miembrosEnemigos = dragonBall.getJugador2().getEquipo().getMiembros().values();
		nombresEsperados.clear();
		nombresEsperados.add("Cell");
		nombresEsperados.add("Freezer");
		nombresEsperados.add("Majin Boo");
			
		for (Personaje personaje : miembrosEnemigos){
			nombresEsperados.remove(personaje.getNombre());
		}
		Assert.assertTrue(nombresEsperados.size() == 0);
			
		//Se comprueban posiciones iniciales en el tablero
		Posicion posicionEsperadaGuerreros1 = Constantes.POS_INICIAL1;
		Posicion posicionEsperadaGuerreros2 = posicionEsperadaGuerreros1.sumarPosicion( new Posicion (0, 1) );
		Posicion posicionEsperadaGuerreros3 = posicionEsperadaGuerreros1.sumarPosicion( new Posicion (0, -1) );
		for (Personaje personaje : miembrosGuerreros){
			Posicion posicionReal = dragonBall.getTablero().getPosicionPersonaje(personaje);
			boolean flag = posicionReal.equals(posicionEsperadaGuerreros1);
			flag = flag || posicionReal.equals(posicionEsperadaGuerreros2);
			flag = flag || posicionReal.equals(posicionEsperadaGuerreros3);
			Assert.assertTrue(flag);
		}
			
		Posicion posicionEsperadaEnemigos1 = Constantes.POS_INICIAL2;
		Posicion posicionEsperadaEnemigos2 = posicionEsperadaEnemigos1.sumarPosicion( new Posicion (0, 1) );
		Posicion posicionEsperadaEnemigos3 = posicionEsperadaEnemigos1.sumarPosicion( new Posicion (0, -1) );
		for (Personaje personaje : miembrosEnemigos){
			Posicion posicionReal = dragonBall.getTablero().getPosicionPersonaje(personaje);
			boolean flag = posicionReal.equals(posicionEsperadaEnemigos1);
			flag = flag || posicionReal.equals(posicionEsperadaEnemigos2);
			flag = flag || posicionReal.equals(posicionEsperadaEnemigos3);
			Assert.assertTrue(flag);
		}
	}

	
}
