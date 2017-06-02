package modelo.personajes.modos.modosGohan;

import modelo.personajes.Personaje;
import modelo.personajes.modos.ModoConKi;

public class GohanSuperSayajinFase1 extends ModoConKi {

	public GohanSuperSayajinFase1() {
		super(30, 2, 2, 30, new GohanSuperSayajinFase2());
		//poderPelea,distanciaAtaque,velocidad,kiNecesario,modoSiguiente
	}

	@Override
	public boolean puedeTransformarse(Personaje personaje) {
		// return super.puedeTransformase(personaje) && VER CONDICION TRANSFORMACION
		return false;
	}

}
