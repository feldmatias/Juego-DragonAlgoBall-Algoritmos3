package modelo.personajes;

import modelo.excepciones.TransformacionNoPosible;
import modelo.juego.Tablero;
import modelo.personajes.modos.Modo;
import modelo.personajes.modos.ModoNormal;
import modelo.personajes.modos.PrimeraTransformacion;
import modelo.personajes.modos.SegundaTransformacion;
import modelo.utilidades.Constantes;

public class Gohan extends Personaje{
	
	public static final int vidaInicial = 300;
	public static final int poderPeleaNormal = 15;
	public static final int poderPeleaPrimerTransformacion = 30;
	public static final int poderPeleaSegundaTransformacion = 100;
	public static final int distanciaAtaqueNormal=2;
	public static final int distanciaAtaquePrimerTransformacion=2;
	public static final int distanciaAtaqueSegundaTransformacion=4;
	public static final int velocidadNormal = 2;
	public static final int velocidadPrimerTransformacion = 2;
	public static final int velocidadSegundaTransformacion = 3;
	public static final int kiNecesarioPrimerTransformacion = 10;
	public static final int kiNecesarioSegundaTransformacion = 30;
	public static final int porcentajeVidaCompanierosParaSegundaTransformacion = 30;
	public static final int kiNecesarioAtaqueEspecial = 10;
	public static final double multiplicadorAtaqueEspecial = 1.25;
	
	public Gohan(Tablero tablero) {
		super(vidaInicial, new ModoNormal(poderPeleaNormal,distanciaAtaqueNormal,velocidadNormal, "Gohan"), 
				new AtaquePotenciador(kiNecesarioAtaqueEspecial,multiplicadorAtaqueEspecial), 
				tablero, kiNecesarioPrimerTransformacion, kiNecesarioSegundaTransformacion);
	}

	@Override
	public Modo realizarPrimeraTransformacion(){
		super.restarKiPrimeraTransformacion();
		return new PrimeraTransformacion(poderPeleaPrimerTransformacion,distanciaAtaquePrimerTransformacion,velocidadPrimerTransformacion, "Gohan Super Sayajin 1");
	}
	
	@Override
	public void puedeRealizarSegundaTransformacion() throws TransformacionNoPosible {
		super.puedeRealizarSegundaTransformacion();
		
		for (Personaje personaje: this.getEquipo().getMiembros().values()){
			if (personaje == this){
				continue;
			}
			if (personaje.getPorcentajeVida() >= porcentajeVidaCompanierosParaSegundaTransformacion){
				throw new TransformacionNoPosible(Constantes.ErrorTransformacionGohan);
			}
		}
	}

	@Override
	public Modo realizarSegundaTransformacion() {
		super.restarKiSegundaTransformacion();
		return new SegundaTransformacion(poderPeleaSegundaTransformacion,distanciaAtaqueSegundaTransformacion,velocidadSegundaTransformacion, "Gohan Super Sayajin 2");
	}
}
