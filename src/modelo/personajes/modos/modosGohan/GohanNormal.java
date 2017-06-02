package modelo.personajes.modos.modosGohan;

import modelo.personajes.modos.ModoConKi;

public class GohanNormal extends ModoConKi {

	public GohanNormal() {
		super(15, 2, 2, 10, new GohanSuperSayajinFase1());
		//poderPelea,distanciaAtaque,velocidad,kiNecesario,modoSiguiente
	}

}
