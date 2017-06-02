package modelo.personajes.modos.modosGoku;

import modelo.personajes.Personaje;
import modelo.personajes.modos.Modo;

public class GokuKaioKen extends Modo {

	public GokuKaioKen() {
		super(40, 4, 3, 50, new GokuSuperSayajin());
		//poderPelea,distanciaAtaque,velocidad,kiNecesario,modoSiguiente
	}

	@Override
	public boolean puedeTransformarse(Personaje personaje) {
		return personaje.getKi() >= this.getKiNecesario();
	}

}
