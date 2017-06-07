package modelo.personajes.modos;

import modelo.excepciones.TransformacionNoPosible;
import modelo.personajes.Personaje;

public class ModoInmovilizado extends Modo {

	private int turnosRestantes;
	private Modo modoAnterior;
	
	public ModoInmovilizado(Modo modoAnterior){
		this.turnosRestantes = 3;
		this.modoAnterior = modoAnterior;
	}
	
	@Override
	public int getPoderPelea(Personaje personaje) {
		return 0;
	}

	@Override
	public int getDistanciaAtaque(Personaje personaje) {
		return 0;
	}

	@Override
	public int getVelocidad(Personaje personaje) {
		return 0;
	}

	@Override
	public Modo transformar(Personaje personaje) {
		return this.modoAnterior;
	}

	@Override
	public boolean puedeTransformarse(Personaje personaje) {
		return this.turnosRestantes == 0;
	}
	
	public void empezarTurno(Personaje personaje){
		this.turnosRestantes -= 1;
		if (this.turnosRestantes == 0){
			try {
				personaje.transformar();
			} catch (TransformacionNoPosible e) {
			}
		}
		personaje.empezarTurno();
	}

}
