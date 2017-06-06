package modelo.utilidades;

import modelo.tablero.Posicion;

public class Constantes {

	public static final int SIZE_TABLERO = 9;
	public static final int POS_CENTRAL = (SIZE_TABLERO-1)/2;
	public static final Posicion POS_INICIAL1= new Posicion(0, POS_CENTRAL );
	public static final Posicion POS_INICIAL2= new Posicion(SIZE_TABLERO-1, POS_CENTRAL);
	public static final String GUERREROS = "Guerreros Z";
	public static final String ENEMIGOS = "Enemigos de la Tierra"; 
}
