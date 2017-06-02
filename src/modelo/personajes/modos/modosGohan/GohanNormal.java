package modelo.personajes.modos.modosGohan;

import modelo.personajes.Personaje;
import modelo.personajes.modos.Modo;

public class GohanNormal extends Modo {

	public GohanNormal() {
		super(15, 2, 2, 10, new GohanSuperSayajinFase1());
		//poderPelea,distanciaAtaque,velocidad,kiNecesario,modoSiguiente
	}

	@Override
	public boolean puedeTransformarse(Personaje personaje) {
		// TODO Auto-generated method stub
		return false;
	}

}
