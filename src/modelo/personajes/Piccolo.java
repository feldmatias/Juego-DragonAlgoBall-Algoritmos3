package modelo.personajes;

import modelo.juego.Tablero;
import modelo.personajes.modos.Modo;
import modelo.personajes.modos.ModoNormal;
import modelo.personajes.modos.PrimeraTransformacion;
import modelo.personajes.modos.SegundaTransformacion;

public class Piccolo extends Personaje {
	
	public static final int vidaInicial = 500;
	public static final int poderPeleaNormal = 20;
	public static final int poderPeleaFortalecido = 40;
	public static final int poderPeleaProtector = 60;
	public static final int distanciaAtaqueNormal=2;
	public static final int distanciaAtaqueFortalecido=4;
	public static final int distanciaAtaqueProtector=6;
	public static final int velocidadNormal = 2;
	public static final int velocidadPrimerTranformacion = 3;
	public static final int velocidadSegundaTranformacion = 4;

	public Piccolo(Tablero tablero) {
		super(vidaInicial, new ModoNormal(poderPeleaNormal,distanciaAtaqueNormal,velocidadNormal, "Piccolo"), new AtaquePotenciador(10,1.25), tablero, 20 ,0);
	}
	
	public Modo realizarPrimeraTransformacion(){
		super.restarKiPrimeraTransformacion();
		return new PrimeraTransformacion(poderPeleaFortalecido,distanciaAtaqueFortalecido,velocidadPrimerTranformacion, "Piccolo Fortalecido");
	}

	@Override
	public Modo realizarSegundaTransformacion() {
		super.restarKiSegundaTransformacion();
		return new SegundaTransformacion(poderPeleaProtector,distanciaAtaqueProtector,velocidadSegundaTranformacion, "Piccolo Protector");
	}
	
	@Override
	public boolean puedeRealizarSegundaTransformacion() {

		for (Personaje personaje: this.getEquipo().getMiembros()){
			if (personaje.getNombre() == "Gohan"){
				return personaje.getPorcentajeVida() < 20;
			}
		}
		return true; //Gohan esta muerto
	}

}
