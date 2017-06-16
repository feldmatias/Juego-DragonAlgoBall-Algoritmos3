package modelo.personajes.modos;

import modelo.excepciones.TransformacionNoPosible;
import modelo.personajes.Personaje;
import modelo.utilidades.Constantes;

public class ModoInmovilizado extends Modo {

	private int turnosRestantes;
	private Modo modoAnterior;
	
	public ModoInmovilizado(Modo modoAnterior){
		super(0,0,0,modoAnterior.getNombre());
		this.turnosRestantes = 4;
		this.modoAnterior = modoAnterior;
	}
	

	@Override
	public Modo transformar(Personaje personaje) {
		return this.modoAnterior;
	}

	@Override
	public void puedeTransformarse(Personaje personaje) throws TransformacionNoPosible {
		if (! (this.turnosRestantes <= 0)){
			throw new TransformacionNoPosible(Constantes.ErrorTransformacionInmovilizado);
		}
	}
	
	@Override
	public void empezarTurno(Personaje personaje){
		this.turnosRestantes -= 1;
		if (this.turnosRestantes <= 0){
			this.volverAModoNormal(personaje);
		}
	}


	private void volverAModoNormal(Personaje personaje) {
		try {
			personaje.transformar();
		} catch (TransformacionNoPosible e) {
		}
		personaje.empezarTurno();
	}

}
