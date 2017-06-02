package modelo.personajes.modos.modosGohan;

import modelo.personajes.Personaje;
import modelo.personajes.modos.Modo;

public class GohanSuperSayajinFase1 extends Modo {

	public GohanSuperSayajinFase1() {
		super(30, 2, 2, 30, new GohanSuperSayajinFase2());
		//poderPelea,distanciaAtaque,velocidad,kiNecesario,modoSiguiente
	}

	@Override
	public boolean puedeTransformarse(Personaje personaje) {
		// TODO Auto-generated method stub
		return false;
	}

}
