package pruebas;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import modelo.consumibles.EsferaDeDragon;
import modelo.consumibles.NubeVoladora;
import modelo.consumibles.SemillaDelErmitanio;
import modelo.excepciones.AtaqueNoPosible;
import modelo.juego.Equipo;
import modelo.personajes.Freezer;
import modelo.personajes.Goku;
import modelo.personajes.Personaje;
import modelo.tablero.Posicion;
import modelo.tablero.Tablero;

public class TestConsumibles {

	private Personaje personaje1;
	private Personaje personaje2;
	private Tablero tablero;
	private Posicion posConsumible;
	
	@Before
	public void setUp(){
		this.tablero = new Tablero(8);
		this.personaje1 = new Goku(tablero);
		this.personaje2 = new Freezer(tablero);
		
		tablero.posicionarPersonaje(personaje1, new Posicion (0,0));
		tablero.posicionarPersonaje(personaje2, new Posicion (0,1));
		posConsumible = new Posicion (1,1);

		List<Personaje> equipo = new ArrayList<Personaje>();
		equipo.add(personaje1);
		new Equipo("equipo", equipo);
	}
	
	@Test
	public void testConsumibleDesapareceAlAgarrarse(){
		tablero.getCasillero(posConsumible).agregarConsumible(new SemillaDelErmitanio());
		
		Assert.assertEquals(100, personaje1.getPorcentajeVida(), 0.01);
		tablero.reposicionarPersonaje(personaje1, posConsumible);
		//Personaje1 Agarro el consumible
		Assert.assertTrue(personaje1.getPorcentajeVida() > 100);
		tablero.reposicionarPersonaje(personaje1, new Posicion (0,0));
		
		tablero.reposicionarPersonaje(personaje2, posConsumible);
		//Personaje2 no agarro el consumible
		Assert.assertFalse(personaje2.getPorcentajeVida() > 100);
	}
	
	@Test
	public void testComprobarEfectoSemillaDelErmitanio(){
		tablero.getCasillero(posConsumible).agregarConsumible(new SemillaDelErmitanio());
		tablero.reposicionarPersonaje(personaje1, posConsumible);
		Assert.assertEquals(120, personaje1.getPorcentajeVida(), 0.01);
	}
	
	@Test
	public void testComprobarEfectoEsferaDelDragon(){
		tablero.getCasillero(posConsumible).agregarConsumible(new EsferaDeDragon());
		tablero.reposicionarPersonaje(personaje1, posConsumible);
		Assert.assertEquals(25, personaje1.getPoderPelea(), 0.01);
	}
	
	@Test
	public void testComprobarEfectoNubeVoladora(){
		tablero.getCasillero(posConsumible).agregarConsumible(new NubeVoladora());
		tablero.reposicionarPersonaje(personaje1, posConsumible);
		Assert.assertEquals(4, personaje1.getVelocidad());
	}
	
	@Test
	public void testSemillaDelErmitanioNoGeneraVidaDosVeces(){
		tablero.getCasillero(posConsumible).agregarConsumible(new SemillaDelErmitanio());
		tablero.reposicionarPersonaje(personaje1, posConsumible);
		Assert.assertEquals(120, personaje1.getPorcentajeVida(), 0.01);
		personaje1.empezarTurno();
		Assert.assertEquals(120, personaje1.getPorcentajeVida(), 0.01);
	}
	
	@Test
	public void testEsferaDelDragonDuraSoloDosAtaques(){
		tablero.getCasillero(posConsumible).agregarConsumible(new EsferaDeDragon());
		tablero.reposicionarPersonaje(personaje1, posConsumible);
		try {
			personaje1.atacarAPersonaje(personaje2);
			personaje1.atacarAPersonaje(personaje2);
		} catch (AtaqueNoPosible e) {
			Assert.fail("Deberia haber atacado");
		}
		Assert.assertEquals(20, personaje1.getPoderPelea(), 0.01);
	}
	
	@Test
	public void testNubeVoladoraDuraSoloDosTurnos(){
		tablero.getCasillero(posConsumible).agregarConsumible(new NubeVoladora());
		tablero.reposicionarPersonaje(personaje1, posConsumible);
		personaje1.empezarTurno();
		personaje1.empezarTurno();
		personaje1.empezarTurno(); //En el tercer turno ya no tiene efecto
		Assert.assertEquals(2, personaje1.getVelocidad());
	}
	
	@Test
	public void testEsferaDelDragonSumaEsferaAEquipo(){
		personaje1.sumarEfecto(new EsferaDeDragon());
		personaje1.sumarEfecto(new EsferaDeDragon());
		personaje1.sumarEfecto(new EsferaDeDragon());
		Assert.assertEquals(3, personaje1.getEquipo().getCantidadEsferas());
	}
	
}
