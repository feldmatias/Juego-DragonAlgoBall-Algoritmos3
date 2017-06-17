package pruebas;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import modelo.consumibles.EsferaDeDragon;
import modelo.excepciones.EquipoNoDisponible;
import modelo.juego.DragonBall;
import modelo.juego.Equipo;
import modelo.personajes.Gohan;
import modelo.personajes.Goku;
import modelo.personajes.Personaje;
import modelo.personajes.Piccolo;
import modelo.utilidades.Constantes;

public class TestEquipo {

	private DragonBall juego;
	private Equipo equipo;
	
	@Before
	public void setUp() throws EquipoNoDisponible{
		juego = new DragonBall();
		juego.establecerEquipoJugador1(Constantes.GUERREROS);
		juego.establecerEquipoJugador2(Constantes.ENEMIGOS);
		this.equipo = juego.getJugador1().getEquipo();
	}

	@Test
	public void testEquipoMuertoInicialmenteDevuelveFalse(){
		Assert.assertFalse(equipo.estaMuerto());
	}
	
	
	@Test
	public void testPersonajeMuertoDesapareceDeEquipo(){
		Personaje goku = equipo.getMiembros().get("Goku");
		goku.recibirAtaque(Goku.vidaInicial * 2);
		Assert.assertFalse(equipo.pertenece(goku));
	}
	
	@Test
	public void testAlMatarUnPersonajeEquipoMuertoDevuelveFalse(){
		Personaje goku = equipo.getMiembros().get("Goku");
		goku.recibirAtaque(Goku.vidaInicial * 2);
		Assert.assertFalse(equipo.estaMuerto());
	}
	
	@Test
	public void testAlMatarDosPersonajeEquipoMuertoDevuelveFalse(){
		Personaje goku = equipo.getMiembros().get("Goku");
		goku.recibirAtaque(Goku.vidaInicial * 2);
		Personaje gohan = equipo.getMiembros().get("Gohan");
		gohan.recibirAtaque(Gohan.vidaInicial * 2);
		Assert.assertFalse(equipo.estaMuerto());
	}
	
	@Test
	public void testAlMatarTresPersonajeEquipoMuertoDevuelveTrue(){
		Personaje goku = equipo.getMiembros().get("Goku");
		goku.recibirAtaque(Goku.vidaInicial * 2);
		Personaje gohan = equipo.getMiembros().get("Gohan");
		gohan.recibirAtaque(Gohan.vidaInicial * 2);
		Personaje piccolo = equipo.getMiembros().get("Piccolo");
		piccolo.recibirAtaque(Piccolo.vidaInicial * 2);
		Assert.assertTrue(equipo.estaMuerto());
	}
	
	@Test
	public void testCantidadEsferasInicialmenteEs0(){
		Assert.assertEquals(0, equipo.getCantidadEsferas());
	}
	
	@Test
	public void testCantidadEsferasDevuelveValorCorrecto(){
		int cantidad = 5;
		for (int i = 0; i < cantidad; i++){
			equipo.agregarEsferaAColeccion();
		}
		Assert.assertEquals(cantidad, equipo.getCantidadEsferas());
	}
	
	@Test
	public void testColeccionEsferasCompletaDevuelveFalseInicialmente(){
		Assert.assertFalse(equipo.coleccionDeEsferasCompleta());
	}
	
	@Test
	public void testColeccionEsferasCompletaDevuelveFalseConAlgunasEsferas(){
		int cantidad = 5;
		for (int i = 0; i < cantidad; i++){
			equipo.agregarEsferaAColeccion();
		}
		Assert.assertFalse(equipo.coleccionDeEsferasCompleta());
	}
	
	@Test
	public void testColeccionEsferasCompletaDevuelveTrueConCantidadEsferasCorrecta(){
		for (int i = 0; i < Constantes.cantidadEsferasParaGanar; i++){
			equipo.agregarEsferaAColeccion();
		}
		Assert.assertTrue(equipo.coleccionDeEsferasCompleta());
	}
	
	@Test
	public void testAgregarEsferaAMiembroLaAgregaAlEquipo(){
		Personaje goku = equipo.getMiembros().get("Goku");
		goku.sumarEfecto(new EsferaDeDragon());
		Assert.assertEquals(1,equipo.getCantidadEsferas());
	}
	
	@Test
	public void testAgregarVariasEsferasAMiembroLasAgregaAlEquipo(){
		Personaje goku = equipo.getMiembros().get("Goku");
		int cantidad = 5;
		for (int i = 0; i < cantidad; i++){
			goku.sumarEfecto(new EsferaDeDragon());
		}
		Assert.assertEquals(cantidad, equipo.getCantidadEsferas());
	}
	
}
