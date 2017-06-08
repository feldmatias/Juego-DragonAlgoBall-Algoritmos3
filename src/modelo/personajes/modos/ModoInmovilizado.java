package modelo.personajes.modos;

import modelo.excepciones.TransformacionNoPosible;
import modelo.personajes.Personaje;

public class ModoInmovilizado extends Modo {

	private int turnosRestantes;
	private Modo modoAnterior;
	
	public ModoInmovilizado(Modo modoAnterior){
		super(0,0,0);
		this.turnosRestantes = 4;
		this.modoAnterior = modoAnterior;
	}
	

	@Override
	public Modo transformar(Personaje personaje) {
		return this.modoAnterior;
	}

	@Override
	public boolean puedeTransformarse(Personaje personaje) {
		return this.turnosRestantes == 0;
	}
	
	@Override
	public void empezarTurno(Personaje personaje){
		this.turnosRestantes -= 1;
		if (this.turnosRestantes == 0){
			try {
				personaje.transformar();
			} catch (TransformacionNoPosible e) {
			}
			personaje.empezarTurno();
		}
	}

}
