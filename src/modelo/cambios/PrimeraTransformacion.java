package modelo.cambios;

public class PrimeraTransformacion extends Estado {

	public PrimeraTransformacion(int poderPelea, int velocidad, int distanciaAtaque, int kiRequeridoTransformacion) {
		super(poderPelea, velocidad, distanciaAtaque, kiRequeridoTransformacion);
		
	}
	@Override
	public void transformar(Personaje2 personaje){
		return personaje.realizarSegundaTransformacion();
	}
}
