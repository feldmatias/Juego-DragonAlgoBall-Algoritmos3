package modelo.personajes.modos.modosGoku;

import modelo.personajes.Personaje;
import modelo.personajes.modos.Modo;

public class GokuNormal extends Modo {

	public GokuNormal() {
		super(20, 2, 2, 20, new GokuKaioKen());
		//poderPelea,distanciaAtaque,velocidad,kiNecesario,modoSiguiente
	}

	@Override
	public boolean puedeTransformarse(Personaje personaje) {
		return personaje.getKi() >= this.getKiNecesario();
	}

}
