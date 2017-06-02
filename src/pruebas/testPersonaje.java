package pruebas;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import modelo.juego.Equipo;
import modelo.personajes.Freezer;
import modelo.personajes.Goku;
import modelo.personajes.Personaje;
import modelo.tablero.Casillero;
import modelo.tablero.Posicion;

public class testPersonaje {
	
	private Personaje personaje;
	
	@Before
	public void setUp(){
		personaje = new Goku();
	}
	
	@Test
	public void testGenerarKiUnaVez(){
		personaje.generarKi();
		Assert.assertEquals(5, personaje.getKi());
	}
	
	@Test
	public void testGenerarKiTresVeces(){
		personaje.generarKi();
		personaje.generarKi();
		personaje.generarKi();
		Assert.assertEquals(15, personaje.getKi());
	}
	
	@Test
	public void testPorcentajeVidaInicialEs100(){
		Assert.assertEquals(100, personaje.getPorcentajeVida(), 0.01);
	}
	
	@Test
	public void testObtenerPoderPeleaModoNormal(){
		Assert.assertEquals(20, personaje.getPoderPelea());
	}
	
	@Test
	public void testDistanciaAtaqueModoNormal(){
		Assert.assertEquals(2, personaje.getDistanciaAtaque());
	}
	
	@Test
	public void testObtenerVelocidadModoNormal(){
		Assert.assertEquals(2, personaje.getVelocidad());
	}
	
	@Test
	public void testRecibirAtaqueDeMayorPoderDePelea(){
		personaje.recibirAtaque(50); //Poder pelea 20 < 50
		Assert.assertEquals(90, personaje.getPorcentajeVida(), 0.01);
	}
	
	@Test
	public void testRecibirAtaqueDeMenorPoderDePelea(){
		personaje.recibirAtaque(10);  //Poder pelea 20 > 10
		Assert.assertEquals(98.4, personaje.getPorcentajeVida(), 0.01);
	}
	
	private Equipo crearEquipo(Personaje personaje){
		List<Personaje> lista = new ArrayList<Personaje>();
		lista.add(personaje);
		Equipo equipo = new Equipo (lista);
		personaje.setEquipo(equipo);
		return equipo;
	}
	
	@Test
	public void testAtacarPersonajeDistintoEquipoPosicionCorrecta(){
		
		this.crearEquipo(this.personaje);
		this.crearEquipo(new Freezer());
		
		Posicion pos1 = new Posicion (0,0);
		Posicion pos2 = new Posicion (0,1);
		
		Casillero cas1 = new Casillero (pos1);
		Casillero cas2 = new Casillero (pos2);
		
	}

}
