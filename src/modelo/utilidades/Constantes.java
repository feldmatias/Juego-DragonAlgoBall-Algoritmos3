package modelo.utilidades;

import modelo.juego.Posicion;

public class Constantes {

	public static final int SIZE_TABLERO = 9;
	public static final int POS_CENTRAL = (SIZE_TABLERO-1)/2;
	public static final Posicion POS_INICIAL1= new Posicion(0, POS_CENTRAL );
	public static final Posicion POS_INICIAL2= new Posicion(SIZE_TABLERO-1, POS_CENTRAL);
	public static final String GUERREROS = "Guerreros Z";
	public static final String ENEMIGOS = "Enemigos de la Tierra";
	public static final int cantidadParaGenerarKiSuficiente = 20;
	public static final double diferenciaMaximaFloats = 0.01;
	public static final int cantidadEsferasParaGanar = 7;
	
	public static final String ErrorAtaqueEnemigoMismoEquipo = "El personaje pertenece al mismo equipo";
	public static final String ErrorAtaqueEnemigoLejano = "El personaje esta muy lejos";
	public static final String ErrorMovimientoLejano = "La posicion esta muy lejos o el camino esta bloqueado";
	public static final String ErrorTransformacionKiInsuficiente = "El ki no es suficiente para realizar la transformacion";
	public static final String ErrorAtaqueEspecialKiInsuficiente = "El ki no es suficiente para realizar el ataque especial";
	public static final String ErrorTransformacionInmovilizado = "Esta inmovilizado";
	public static final String ErrorTransformacionPiccolo = "Gohan tiene mucha vida";
	public static final String ErrorTransformacionGohan = "Los aliados tienen mucha vida";
	public static final String ErrorTransformacionCell = "Debe realizar mas absorciones de vida";
	public static final String ErrorTransformacionFinal = "No puede realizar mas transformaciones";
	public static final String ErrorAtaqueYaRealizado = "Ya realizo un ataque en este turno";
	public static final String ErrorMovimientoYaRealizado = "Ya realizo un movimiento en este turno";
	
	public static final String GanadorPorDerrotarEnemigo = ": Derroto al otro equipo";
	public static final String GanadorPorEncontrarSieteEsferas = ": Encontro siete esferas del dragon";
}
