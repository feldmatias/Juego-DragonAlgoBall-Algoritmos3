package vista;

import javafx.stage.Screen;
import modelo.utilidades.Constantes;

public class ConstantesPantalla {
	
	private static double heightPantalla = Screen.getPrimary().getVisualBounds().getHeight();
	private static double widthPantalla = Screen.getPrimary().getVisualBounds().getWidth();

	//Iamgenes de fondo y titulo menu principal
	public static final double anchoImagenFondo = widthPantalla;
	public static final double altoImagenFondo = heightPantalla;
	public static final double tamFuenteTituloMenuPrincipal = heightPantalla / 4.7;
	
	//Parametros de personaje y turno actual en el juego
	public static final double tamFuenteNombreEquipo = heightPantalla / 30;
	public static final double tamFuenteNombrePersonaje = heightPantalla / 35 ;
	public static final double tamFuenteTurno = heightPantalla / 17.5;
	public static final double tamEsfera = heightPantalla / 7;
	
	//Imagenes de equipos en vista de seleccionar equipos
	public static final double alturaImagenesEquipos = heightPantalla / 2;
	
	//Botones de acciones
	public static final double anchoBotonAccion = widthPantalla / 8 ; 
	public static final double altoBotonAccion = heightPantalla / 8.5; 
	public static final double tamFuenteBotonAccion = heightPantalla / 42 ;
	
	//Botones de casilleros
	public static final double anchoBotonCasillero = heightPantalla / 13;
	public static final double altoBotonCasillero = heightPantalla / 13;
	
	
	//Imagenes Consumibles
	public static final double anchoConsumible = anchoBotonCasillero * 0.3;
	public static final double altoConsumible = altoBotonCasillero * 0.3;
	
	//Imagenes personajes
	public static final double anchoPersonaje = anchoBotonCasillero * 0.7;
	public static final double altoPersonaje = altoBotonCasillero;
	
	//Imagen fondo del tablero
	public static final double anchoImagenTablero = Constantes.SIZE_TABLERO * anchoBotonCasillero;
	public static final double altoImagenTablero = Constantes.SIZE_TABLERO * altoBotonCasillero;
}
