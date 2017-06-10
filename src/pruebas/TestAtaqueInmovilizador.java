package pruebas;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import modelo.excepciones.AtaqueNoPosible;
import modelo.excepciones.EquipoNoDisponible;
import modelo.excepciones.MovimientoNoPosible;
import modelo.excepciones.TransformacionNoPosible;
import modelo.juego.DragonBall;
import modelo.personajes.Personaje;
import modelo.tablero.Posicion;
import modelo.utilidades.Constantes;

public class TestAtaqueInmovilizador {

	private Personaje majinBoo;
	private Personaje goku;
	private DragonBall juego;
	
	@Before
	public void setUp() throws EquipoNoDisponible{
		juego = new DragonBall();
		juego.establecerEquipoJugador1(Constantes.GUERREROS);
		juego.establecerEquipoJugador2(Constantes.ENEMIGOS);
		this.goku = juego.getJugador1().getEquipo().getMiembros().get(0);
		this.majinBoo = juego.getJugador2().getEquipo().getMiembros().get(2);
		juego.getTablero().reposicionarPersonaje(goku, new Posicion(5,5));
		juego.getTablero().reposicionarPersonaje(majinBoo, new Posicion(5,6));
		
		for (int i = 0; i < 15; i++){
			majinBoo.generarKi();
		}
	}
	
	@Test
	public void testPersonajeInmovilizadoNoPuedeAtacar(){
		try {
			majinBoo.realizarAtaqueEspecial(goku);
		} catch (AtaqueNoPosible e) {
			Assert.fail("El ataque deberia realizarse");
		}
		try {
			goku.atacarAPersonaje(majinBoo);
			Assert.fail("El ataque no deberia realizarse");
		} catch (AtaqueNoPosible e) {
			Assert.assertTrue(true);
		}
		
	}
	@Test 
	public void testPersonajeInmovilizadoPuedeAtacarLuegoDeTresTurnos(){
		try{
			majinBoo.realizarAtaqueEspecial(goku);
		}catch(AtaqueNoPosible e){
			Assert.fail("El ataque de Majin Boo deberia haberse realizado");
		}
		for(int i = 0; i < 3; i++){
			goku.empezarTurno(); 
		}
		goku.empezarTurno(); //empieza el 4 turno
		try{
			goku.atacarAPersonaje(majinBoo);
			Assert.assertTrue(true);
		}catch(AtaqueNoPosible e){
			Assert.fail("El ataque de Goku deberia haberse realizado");
		}
	}
	
	@Test
	public void testPersonajeInmovilizadoNoPuedeRealizarAtaqueEspecialSinKiNecesario(){
		try {
			majinBoo.realizarAtaqueEspecial(goku);
		} catch (AtaqueNoPosible e) {
			Assert.fail("El ataque deberia realizarse");
		}
		try {
			goku.realizarAtaqueEspecial(majinBoo);
			Assert.fail("El ataque no deberia realizarse");
		} catch (AtaqueNoPosible e) {
			Assert.assertTrue(true);
		}
		
	}
	@Test
	public void testPersonajeInmovilzadoNoPuedeRealizarAtaqueEspecialSinKiNecesarioLuegoDeTresTurnos(){
		try {
			majinBoo.realizarAtaqueEspecial(goku);
		} catch (AtaqueNoPosible e) {
			Assert.fail("El ataque deberia realizarse");
		}
		for(int j=0; j < 4; j++){
			goku.empezarTurno();
		}
		try {
			goku.realizarAtaqueEspecial(majinBoo);
			Assert.fail("El ataque no deberia realizarse");
		} catch (AtaqueNoPosible e) {
			Assert.assertTrue(true);
		}
		
	}
	
	@Test
	public void testPersonajeInmovilizadoNoRealizaAtaqueEspecialConKiSuficiente(){
		
		for(int i=0 ; i < 5; i++){
			goku.generarKi(); //Obteniendo ki necesario para ataque
		}
		
		try {
			majinBoo.realizarAtaqueEspecial(goku);
		} catch (AtaqueNoPosible e) {
			Assert.fail("El ataque deberia realizarse");
		}
		try {
			goku.realizarAtaqueEspecial(majinBoo);
			Assert.fail("El ataque no deberia realizarse");
		} catch (AtaqueNoPosible e) {
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void testPersonajeInmovilizadoRealizaAtaqueEspecialConKiSuficienteLuegoDeTresTurnos(){
		for(int i=0 ; i < 5; i++){
			goku.generarKi(); //Obteniendo ki necesario para ataque
		}
		
		try {
			majinBoo.realizarAtaqueEspecial(goku);
		} catch (AtaqueNoPosible e) {
			Assert.fail("El ataque deberia realizarse");
		}
		for(int j = 0; j < 4; j++){
			goku.empezarTurno();
		}
		try {
			goku.realizarAtaqueEspecial(majinBoo);
			Assert.assertTrue(true);
		} catch (AtaqueNoPosible e) {
			Assert.fail("El ataque debio realizarse luego de los 3 turnos");
		}
	}
	
	@Test
	public void testPersonajeInmovilizadoNoPuedeMover(){
		try {
			majinBoo.realizarAtaqueEspecial(goku);
		} catch (AtaqueNoPosible e) {
			Assert.fail("El ataque deberia realizarse");
		}
		try {
			goku.mover(new Posicion (5,4));
			Assert.fail("El movimiento no deberia realizarse");
		} catch (MovimientoNoPosible e) {
			Assert.assertTrue(true);
		}
		
	}
	
	@Test
	public void testPersonajeInmovilizadoSePuedeMoverLuegoDeTresTurnos(){
		
		try {
			majinBoo.realizarAtaqueEspecial(goku);
		} catch (AtaqueNoPosible e) {
			Assert.fail("El ataque deberia realizarse");
		}
		for( int i = 0; i < 4; i++){
			goku.empezarTurno();
		}
		try {
			goku.mover(new Posicion (5,4));
			Assert.assertTrue(true);
		} catch (MovimientoNoPosible e) {
			Assert.fail("El movimiento deberia realizarse");
		}
			
	}
	
	@Test
	public void testPersonajeInmovilizadoNoPuedeTransformarseSinKiNecesario(){
		try {
			majinBoo.realizarAtaqueEspecial(goku);
		} catch (AtaqueNoPosible e) {
			Assert.fail("El ataque deberia realizarse");
		}
		try {
			goku.transformar();
			Assert.fail("La transformacion no deberia realizarse");
		} catch (TransformacionNoPosible e) {
			Assert.assertTrue(true);
		}
		
	}
	@Test
	public void testPersonajeInmovilizadoNoPuedeTransformarseSinKiNecesarioLuegoDeTresTurnos(){
		
		try {
			majinBoo.realizarAtaqueEspecial(goku);
		} catch (AtaqueNoPosible e) {
			Assert.fail("El ataque deberia realizarse");
		}
		for( int i = 0; i < 4; i++){
			goku.empezarTurno();
		}
		try {
			goku.transformar();
			Assert.fail("La transformacion no deberia realizarse");
		} catch (TransformacionNoPosible e) {
			Assert.assertTrue(true);
		}
		
	}
	
	@Test
	public void testPersonajeInmovilizadoNoPuedeHacerPrimeraTransformacionAunConKiSuficiente(){
		for(int i = 0; i < 5; i++){
			goku.generarKi();
		}
		
		try {
			majinBoo.realizarAtaqueEspecial(goku);
		} catch (AtaqueNoPosible e) {
			Assert.fail("El ataque deberia realizarse");
		}
		try {
			goku.transformar();
			Assert.fail("La transformacion no deberia realizarse");
		} catch (TransformacionNoPosible e) {
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void testPersonajeInmovilizadoHacePrimeraTransformacionTeniendoKiLuegoDeTresTurnos(){
		for(int i = 0; i < 5; i++){
			goku.generarKi();
		}
		
		try {
			majinBoo.realizarAtaqueEspecial(goku);
		} catch (AtaqueNoPosible e) {
			Assert.fail("El ataque deberia realizarse");
		}
		for(int j = 0; j < 4; j++){
			goku.empezarTurno();
		}
		
		try {
			goku.transformar();
			Assert.assertTrue(true);
		} catch (TransformacionNoPosible e) {
			Assert.fail("La transformacion deberia realizarse luego de los 3 turnos");
		}		
	}
	
	
	
	@Test
	public void testPersonajeInmovilizadoNoPuedeHacerSegundaTransformacionAunConKiSuficiente(){
		for(int i = 0; i < 15; i++){
			goku.generarKi(); //obteniendo ki necesario para 2 transformaciones
		}
		try {
			goku.transformar(); //primera transformacion
		} catch (TransformacionNoPosible e) {
			Assert.fail("La prueba fallo goku deberia haberse transformado la primera vez");
		}
		try {
			majinBoo.realizarAtaqueEspecial(goku);
		} catch (AtaqueNoPosible e) {
			Assert.fail("El ataque deberia realizarse");
		}
		try {
			goku.transformar();
			Assert.fail("La transformacion no deberia realizarse");
		} catch (TransformacionNoPosible e) {
			Assert.assertTrue(true);
		}
		
		
	} 
	
	@Test
	public void testPersonajeInmovilizadoPuedeHacerSegundaTransformacionLuegoDeTresTurnos(){
		for(int i = 0; i < 15; i++){
			goku.generarKi(); //obteniendo ki necesario para 2 transformaciones
		}
		try {
			goku.transformar();
		} catch (TransformacionNoPosible e) {
			Assert.fail("La prueba fallo goku deberia haberse transformado la primera vez");
		}
		try {
			majinBoo.realizarAtaqueEspecial(goku);
		} catch (AtaqueNoPosible e) {
			Assert.fail("El ataque deberia realizarse");
		}
		for(int j = 0; j < 4; j++){
			goku.empezarTurno();
		}
		
		try {
			goku.transformar();
			Assert.assertTrue(true);
		} catch (TransformacionNoPosible e) {
			Assert.fail("Deberia haber realizado la segunda transformacion luego de 3 turnos");
		}
	}
	
	@Test
	public void testPersonajeInmovilizadoNoPuedeGenerarKiDuranteTresTurnos(){
		int kiInicial = goku.getKi();
		try {
			majinBoo.realizarAtaqueEspecial(goku);
		} catch (AtaqueNoPosible e) {
			Assert.fail("El ataque deberia realizarse");
		}
		for(int i=0; i<3 ; i++){
			goku.empezarTurno();
		}
		int kiFinal = goku.getKi();
		Assert.assertEquals(kiInicial, kiFinal);
		
	}
	
	@Test
	public void testPersonajeInmovilizadoVuelveAGenerarKiLuegoDeTresTurnos(){
		int kiInicial = goku.getKi();
		try {
			majinBoo.realizarAtaqueEspecial(goku);
		} catch (AtaqueNoPosible e) {
			Assert.fail("El ataque deberia realizarse");
		}
		for(int i=0; i < 4 ; i++){
			goku.empezarTurno();
		}
		int kiFinal = goku.getKi() ;
		Assert.assertEquals(kiFinal , kiInicial + 5);
	
	}
	
	@Test
	public void testPersonajeInmovilizadoNoPuedeHacerNadaPorTresTurnosTeniendoKiSuficiente(){
		for (int j = 0; j < 15; j++){
			goku.generarKi();
		}
		
		try {
			majinBoo.realizarAtaqueEspecial(goku);
		} catch (AtaqueNoPosible e) {
			Assert.fail("El ataque deberia realizarse");
		}
		int kiActual = goku.getKi();
		for (int i = 0; i < 3; i++){
			goku.empezarTurno();
			try{
				goku.transformar();
				Assert.fail("No deberia deberia realizarse: Transformacion" + i);
			} catch (TransformacionNoPosible e1){
			}
			try{
				goku.atacarAPersonaje(majinBoo);
				goku.realizarAtaqueEspecial(majinBoo);
				Assert.fail("No deberia deberia realizarse: Ataque" + i);
			} catch (AtaqueNoPosible e2){
			}
			try{
				goku.mover(new Posicion (5,4));
				Assert.fail("No deberia deberia realizarse: Movimiento" + i);
			} catch (MovimientoNoPosible e3){
			}
		}
		Assert.assertEquals(kiActual, goku.getKi());
		
	}
	
	@Test
	public void testPersonajeInmovilizadoVuelveALaNormalidadDespuesDeTresTurnos(){
		for( int j = 0; j < 15 ; j++){
			goku.generarKi();
		}
		try {
			majinBoo.realizarAtaqueEspecial(goku);
		} catch (AtaqueNoPosible e) {
			Assert.fail("El ataque deberia realizarse");
		}
		int kiActual = goku.getKi();
		for (int i = 0; i < 4; i++){
			goku.empezarTurno();
		}
		Assert.assertEquals(kiActual + 5, goku.getKi());
		
		try {
			goku.atacarAPersonaje(majinBoo);
			goku.realizarAtaqueEspecial(majinBoo);
		} catch (AtaqueNoPosible e) {
			Assert.fail("Deberia haberse realizado");
		}
		try {
			goku.transformar();
		} catch (TransformacionNoPosible e) {
			Assert.fail("Deberia haberse transformado");
		}
		try {
			goku.mover(new Posicion (5,4));
		} catch (MovimientoNoPosible e) {
			Assert.fail("Deberia haberse movido");
		}
		
		
	}
	
}
