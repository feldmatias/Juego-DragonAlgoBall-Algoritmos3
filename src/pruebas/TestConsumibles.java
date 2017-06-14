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
import modelo.juego.Posicion;
import modelo.juego.Tablero;
import modelo.personajes.Freezer;
import modelo.personajes.Goku;
import modelo.personajes.Personaje;

public class TestConsumibles {

	public static final double porcentajeDeVidaEsperado = 0.01;
	public static final int consumible_pos_x = 1;
	public static final int consumible_pos_y = 1;
	
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
		posConsumible = new Posicion (consumible_pos_x,consumible_pos_y);

		List<Personaje> equipo = new ArrayList<Personaje>();
		equipo.add(personaje1);
		new Equipo("equipo", equipo);
	}
	
	@Test
	public void testConsumibleDesapareceAlAgarrarse(){
		tablero.getCasillero(posConsumible).agregarConsumible(new SemillaDelErmitanio());
		
		Assert.assertEquals(SemillaDelErmitanio.regeneracionDeVida, personaje1.getPorcentajeVida(), porcentajeDeVidaEsperado);
		tablero.reposicionarPersonaje(personaje1, posConsumible);
		//Personaje1 Agarro el consumible
		Assert.assertTrue(personaje1.getPorcentajeVida() > SemillaDelErmitanio.regeneracionDeVida);
		
		tablero.reposicionarPersonaje(personaje1, new Posicion (0,0));
		
		tablero.reposicionarPersonaje(personaje2, posConsumible);
		//Personaje2 no agarro el consumible
		Assert.assertFalse(personaje2.getPorcentajeVida() > SemillaDelErmitanio.regeneracionDeVida);
	}
	
	@Test
	public void testSemillaDelErmitanioRegeneraVida(){
		float porcentajeVidaInicial = personaje1.getPorcentajeVida();
		tablero.getCasillero(posConsumible).agregarConsumible(new SemillaDelErmitanio());
		tablero.reposicionarPersonaje(personaje1, posConsumible);
		float porcentajeVidaFinal = personaje1.getPorcentajeVida(); //se espera aumento del 20%
		Assert.assertEquals(porcentajeVidaFinal, porcentajeVidaInicial + 20, porcentajeDeVidaEsperado);
	}
	
	@Test
	public void testEsferaDelDragonAumentaElPoderDePelea(){
		double poderPeleaInicial = personaje1.getPoderPelea();
		tablero.getCasillero(posConsumible).agregarConsumible(new EsferaDeDragon());
		tablero.reposicionarPersonaje(personaje1, posConsumible);
		double poderPeleaFinal = personaje1.getPoderPelea();
		Assert.assertEquals(poderPeleaInicial + 5, poderPeleaFinal, porcentajeDeVidaEsperado);
	}
	
	@Test
	public void testNubeVoladoraAumentaVelocidadAlDoble(){
		int velocidadInicial = personaje1.getVelocidad();
		tablero.getCasillero(posConsumible).agregarConsumible(new NubeVoladora());
		tablero.reposicionarPersonaje(personaje1, posConsumible);
		int velocidadFinal = personaje1.getVelocidad();
		Assert.assertEquals(velocidadFinal , velocidadInicial * 2 );
	}
	
	@Test
	public void testNubeVoladoraAumentaVelocidadAlDobleDuranteSegundoTurno(){
		int velocidadInicial = personaje1.getVelocidad();
		tablero.getCasillero(posConsumible).agregarConsumible(new NubeVoladora());
		tablero.reposicionarPersonaje(personaje1, posConsumible);
		personaje1.empezarTurno();
		personaje1.empezarTurno(); //segundo turno
		int velocidadFinal = personaje1.getVelocidad();
		Assert.assertEquals(velocidadFinal , velocidadInicial * 2 );
		
	}
	
	@Test
	public void testNubeVoladoraPierdeEfectoEnElTercerTurno(){
		int velocidadInicial = personaje1.getVelocidad();
		tablero.getCasillero(posConsumible).agregarConsumible(new NubeVoladora());
		tablero.reposicionarPersonaje(personaje1, posConsumible);
		personaje1.empezarTurno();
		personaje1.empezarTurno();
		personaje1.empezarTurno(); //En el tercer turno ya no tiene efecto
		Assert.assertEquals(velocidadInicial, personaje1.getVelocidad());
	}
	
	
	@Test
	public void testSemillaDelErmitanioNoGeneraVidaDosVeces(){
		tablero.getCasillero(posConsumible).agregarConsumible(new SemillaDelErmitanio());
		tablero.reposicionarPersonaje(personaje1, posConsumible);
		Assert.assertEquals(120, personaje1.getPorcentajeVida(), porcentajeDeVidaEsperado);
		personaje1.empezarTurno();
		Assert.assertEquals(120, personaje1.getPorcentajeVida(), porcentajeDeVidaEsperado);
	}
	
	
	@Test
	public void testEsferaDelDragonAumentaElDanioARecibirPorElEnemigo() {
		tablero.getCasillero(posConsumible).agregarConsumible(new EsferaDeDragon());
		tablero.reposicionarPersonaje(personaje1, posConsumible);
		float porcentajeVidaInicial = personaje2.getPorcentajeVida();
		try {
			personaje1.atacarAPersonaje(personaje2); //se espera danio de 25
		} catch (AtaqueNoPosible e) {}
		float porcentajeVidaFinal = personaje2.getPorcentajeVida(); // se espera 6.25% menos de vida
		Assert.assertEquals(porcentajeVidaFinal, porcentajeVidaInicial - 6.25, porcentajeDeVidaEsperado);
	}
	
	@Test
	public void testEsferaDelDragonAumentaElDanioARecibirPorEnemigoPorDosAtaques(){
		tablero.getCasillero(posConsumible).agregarConsumible(new EsferaDeDragon());
		tablero.reposicionarPersonaje(personaje1, posConsumible);
		float porcentajeVidaInicial = personaje2.getPorcentajeVida();
		try {
			personaje1.atacarAPersonaje(personaje2); //se espera danio de 25
		} catch (AtaqueNoPosible e) {}
		float porcentajeVidaFinal = personaje2.getPorcentajeVida(); // se espera 6.25% menos de vida
		Assert.assertEquals(porcentajeVidaFinal, porcentajeVidaInicial - 6.25, porcentajeDeVidaEsperado);
		try {
			personaje1.atacarAPersonaje(personaje2);
		}catch (AtaqueNoPosible e){}
		porcentajeVidaFinal = personaje2.getPorcentajeVida();
		Assert.assertEquals(porcentajeVidaFinal, porcentajeVidaInicial -12.5 , porcentajeDeVidaEsperado);
	}
	
	@Test
	public void testEsferaDelDragonEnElTercerAtaqueAplicaDanioNormal(){
		tablero.getCasillero(posConsumible).agregarConsumible(new EsferaDeDragon());
		tablero.reposicionarPersonaje(personaje1, posConsumible);
		try {
			personaje1.atacarAPersonaje(personaje2);
			personaje1.atacarAPersonaje(personaje2);
		} catch (AtaqueNoPosible e) {}
		float porcentajeVidaInicial = personaje2.getPorcentajeVida();
		try{
			personaje1.atacarAPersonaje(personaje2);
		}catch(AtaqueNoPosible e){}
		float porcentajeVidaFinal = personaje2.getPorcentajeVida(); //se espera danio del 5%
		Assert.assertEquals(porcentajeVidaFinal, porcentajeVidaInicial - 5, porcentajeDeVidaEsperado);
	}
	

	@Test
	public void testEsferaDelDragonSumaEsferaAEquipo(){
		personaje1.sumarEfecto(new EsferaDeDragon());
		personaje1.sumarEfecto(new EsferaDeDragon());
		personaje1.sumarEfecto(new EsferaDeDragon());
		Assert.assertEquals(3, personaje1.getEquipo().getCantidadEsferas());
	}
	
}
