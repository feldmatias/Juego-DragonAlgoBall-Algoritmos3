package modelo.personajes.modos;

import modelo.excepciones.TransformacionNoPosible;
import modelo.personajes.Personaje;
import modelo.utilidades.Constantes;

public class SegundaTransformacion extends Modo {

	public SegundaTransformacion(double poderPelea, int distanciaAtaque, int velocidad, String nombre) {
		super(poderPelea, distanciaAtaque, velocidad, nombre);
	}

	@Override
	public Modo transformar(Personaje personaje) {
		return this;
	}

	@Override
	public void puedeTransformarse(Personaje personaje) throws TransformacionNoPosible {
		throw new TransformacionNoPosible(Constantes.ErrorTransformacionFinal);
	}

}
