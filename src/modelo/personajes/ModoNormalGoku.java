package modelo.personajes;

import modelo.personajes.modos.Modo;

public class ModoNormalGoku extends Modo {

	public ModoNormalGoku(int poderPelea, int distanciaAtaque, int velocidad, Modo modoSiguiente) {
		super(poderPelea, distanciaAtaque, velocidad, modoSiguiente);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean puedeTransformar() {
		// TODO Auto-generated method stub
		return false;
	}

}
