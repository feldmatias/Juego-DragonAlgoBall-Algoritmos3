package modelo.cambios;

public class SegundaTransformacion extends Estado{

	public SegundaTransformacion(int poderPelea, int velocidad, int distanciaAtaque, int kiRequeridoTransformacion) {
		super(poderPelea, velocidad, distanciaAtaque, kiRequeridoTransformacion);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean tieneKiParaTransformacion(Personaje2 personaje){
		return false;
	}
	
	@Override
	public void transformar(Personaje2 personaje){
		return;
	}
	

}
