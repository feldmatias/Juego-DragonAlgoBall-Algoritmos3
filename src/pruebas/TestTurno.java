package pruebas;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import modelo.juego.Equipo;
import modelo.juego.Turno;
import modelo.personajes.Cell;
import modelo.personajes.Freezer;
import modelo.personajes.Gohan;
import modelo.personajes.Goku;
import modelo.personajes.MajinBoo;
import modelo.personajes.Personaje;
import modelo.personajes.Piccolo;
import modelo.personajes.modos.modosGoku.GokuNormal;
import modelo.tablero.Tablero;
import modelo.utilidades.Constantes;

public class TestTurno {
	private Equipo equipo1;
	private Equipo equipo2;
	
	@Before
	public void setUp(){
		List<Personaje> personajes = new ArrayList<Personaje>();
		Tablero tablero = new Tablero(8);
		personajes.add(new Goku(tablero));
		personajes.add(new Gohan(tablero));
		personajes.add(new Piccolo(tablero));
		this.equipo1 = new Equipo(Constantes.GUERREROS, personajes);
		
		List<Personaje> personajesMalos = new ArrayList<Personaje>();
		personajesMalos.add(new Cell(tablero));
		personajesMalos.add(new Freezer(tablero));
		personajesMalos.add(new MajinBoo(tablero));
		this.equipo2 = new Equipo(Constantes.ENEMIGOS, personajesMalos);
	}
	
	
	

	@Test
	public void testTurnoNoEstaCreado() {
		Turno turno = new Turno();
		Assert.assertTrue(Turno.getInstance() != null);
	}
	
	@Test
	public void testNoEsElTurnodelEquipo2 (){
		Turno.getInstance().setEquipo(equipo1);
		Turno.getInstance().esMiTurno(equipo2);
		Assert.assertTrue(Turno.getInstance().esMiTurno(equipo2)== false);
	}
	
	@Test
	public void testEsElTurnoDelEquipo1 (){
		Turno.getInstance().setEquipo(equipo1);
		Turno.getInstance().esMiTurno(equipo1);
		Assert.assertTrue(Turno.getInstance().esMiTurno(equipo1)== true);
	}	
		
	@Test
	public void testGenerarKiAlComienzoDelTurno(){
		Turno.getInstance().setEquipo(equipo1);
		Turno.getInstance().empezarTurno();
		for (Personaje personaje: equipo1.getMiembros()){
			Assert.assertEquals(5, personaje.getKi());
		}
	}
}