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
import modelo.utilidades.Constantes;

public class TestConsumibles {

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
		
		Assert.assertEquals(Goku.vidaInicial, personaje1.getVidaActual(), Constantes.diferenciaMaximaFloats);
		tablero.reposicionarPersonaje(personaje1, posConsumible);
		
		//Personaje1 Agarro el consumible
		int vidaEsperada = Goku.vidaInicial + SemillaDelErmitanio.regeneracionDeVida;
		Assert.assertEquals(vidaEsperada , personaje1.getVidaActual(), Constantes.diferenciaMaximaFloats);
		
		tablero.reposicionarPersonaje(personaje1, new Posicion (0,0));
		
		tablero.reposicionarPersonaje(personaje2, posConsumible);
		//Personaje2 no agarro el consumible
		Assert.assertEquals(Freezer.vidaInicial, personaje2.getVidaActual(), Constantes.diferenciaMaximaFloats);
	}
	
	@Test
	public void testSemillaDelErmitanioRegeneraVida(){
		int vidaEsperada = Goku.vidaInicial + SemillaDelErmitanio.regeneracionDeVida;
		tablero.getCasillero(posConsumible).agregarConsumible(new SemillaDelErmitanio());
		tablero.reposicionarPersonaje(personaje1, posConsumible);
		Assert.assertEquals(vidaEsperada, personaje1.getVidaActual(), Constantes.diferenciaMaximaFloats);
	}
	
	@Test
	public void testEsferaDelDragonAumentaElPoderDePelea(){
		tablero.getCasillero(posConsumible).agregarConsumible(new EsferaDeDragon());
		tablero.reposicionarPersonaje(personaje1, posConsumible);
		double poderPeleaEsperado = Goku.poderPeleaNormal * EsferaDeDragon.aumentoDanio;
		Assert.assertEquals(poderPeleaEsperado, personaje1.getPoderPelea(), Constantes.diferenciaMaximaFloats);
	}
	
	@Test
	public void testNubeVoladoraAumentaVelocidadAlDoble(){
		tablero.getCasillero(posConsumible).agregarConsumible(new NubeVoladora());
		tablero.reposicionarPersonaje(personaje1, posConsumible);
		int velocidadEsperada = Goku.velocidadNormal * NubeVoladora.aumentoVelocidad;
		Assert.assertEquals(velocidadEsperada , personaje1.getVelocidad() );
	}
	
	@Test
	public void testNubeVoladoraAumentaVelocidadAlDobleDuranteSegundoTurno(){
		tablero.getCasillero(posConsumible).agregarConsumible(new NubeVoladora());
		tablero.reposicionarPersonaje(personaje1, posConsumible);
		personaje1.empezarTurno();
		personaje1.empezarTurno(); //segundo turno
		int velocidadEsperada = Goku.velocidadNormal * NubeVoladora.aumentoVelocidad;
		Assert.assertEquals(velocidadEsperada , personaje1.getVelocidad() );
		
	}
	
	@Test
	public void testNubeVoladoraPierdeEfectoAlPasarTurnosNecesarios(){
		tablero.getCasillero(posConsumible).agregarConsumible(new NubeVoladora());
		tablero.reposicionarPersonaje(personaje1, posConsumible);
		for (int i = 0; i < NubeVoladora.duracionTurnos; i++){
			personaje1.empezarTurno();
		}
		Assert.assertEquals(Goku.velocidadNormal, personaje1.getVelocidad());
	}
	
	
	@Test
	public void testSemillaDelErmitanioNoGeneraVidaDosVeces(){
		tablero.getCasillero(posConsumible).agregarConsumible(new SemillaDelErmitanio());
		tablero.reposicionarPersonaje(personaje1, posConsumible);
		int vidaEsperada = Goku.vidaInicial + SemillaDelErmitanio.regeneracionDeVida;
		Assert.assertEquals(vidaEsperada, personaje1.getVidaActual(), Constantes.diferenciaMaximaFloats);
		personaje1.empezarTurno();
		Assert.assertEquals(vidaEsperada, personaje1.getVidaActual(), Constantes.diferenciaMaximaFloats);
	}
	
	
	@Test
	public void testEsferaDelDragonAumentaElDanioARecibirPorElEnemigo() {
		tablero.getCasillero(posConsumible).agregarConsumible(new EsferaDeDragon());
		tablero.reposicionarPersonaje(personaje1, posConsumible);
		try {
			personaje1.atacarAPersonaje(personaje2);
		} catch (AtaqueNoPosible e) {}
		double vidaEsperada = Freezer.vidaInicial - Goku.poderPeleaNormal * EsferaDeDragon.aumentoDanio;
		Assert.assertEquals(vidaEsperada, personaje2.getVidaActual(), Constantes.diferenciaMaximaFloats);
	}
	
	@Test
	public void testEsferaDelDragonAumentaElDanioARecibirPorEnemigoPorDosAtaques(){
		tablero.getCasillero(posConsumible).agregarConsumible(new EsferaDeDragon());
		tablero.reposicionarPersonaje(personaje1, posConsumible);
		try {
			personaje1.atacarAPersonaje(personaje2); 
		} catch (AtaqueNoPosible e) {}
		double vidaEsperada = Freezer.vidaInicial - Goku.poderPeleaNormal * EsferaDeDragon.aumentoDanio;
		Assert.assertEquals(vidaEsperada, personaje2.getVidaActual(), Constantes.diferenciaMaximaFloats);
		try {
			personaje1.atacarAPersonaje(personaje2);
		}catch (AtaqueNoPosible e){}
		vidaEsperada = Freezer.vidaInicial - Goku.poderPeleaNormal * EsferaDeDragon.aumentoDanio * 2;
		Assert.assertEquals(vidaEsperada, personaje2.getVidaActual(), Constantes.diferenciaMaximaFloats);
	}
	
	@Test
	public void testEsferaDelDragonEnElTercerAtaqueAplicaDanioNormal(){
		tablero.getCasillero(posConsumible).agregarConsumible(new EsferaDeDragon());
		tablero.reposicionarPersonaje(personaje1, posConsumible);
		try {
			personaje1.atacarAPersonaje(personaje2);
			personaje1.atacarAPersonaje(personaje2);
		} catch (AtaqueNoPosible e) {}
		double vidaTrasDosAtaques = Freezer.vidaInicial - Goku.poderPeleaNormal * EsferaDeDragon.aumentoDanio * 2;
		try{
			personaje1.atacarAPersonaje(personaje2);
		}catch(AtaqueNoPosible e){}
		double vidaEsperada = vidaTrasDosAtaques - Goku.poderPeleaNormal;
		Assert.assertEquals(vidaEsperada, personaje2.getVidaActual(), Constantes.diferenciaMaximaFloats);
	}
	

	@Test
	public void testEsferaDelDragonSumaEsferaAEquipo(){
		int cantidadEsferas = 3;
		for (int i = 0; i< cantidadEsferas; i++){
			personaje1.sumarEfecto(new EsferaDeDragon());
		}
		Assert.assertEquals(cantidadEsferas, personaje1.getEquipo().getCantidadEsferas());
	}
	
}
