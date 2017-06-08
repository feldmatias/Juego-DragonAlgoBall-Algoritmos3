package modelo.personajes;

import modelo.excepciones.AtaqueNoPosible;

public class AtaqueInmovilizador extends AtaqueEspecial {

	public AtaqueInmovilizador(int kiNecesario) {
		super(kiNecesario);
	}

	@Override
	public double getPoderAtaqueEspecial(Personaje atacante) {
		return 0;
	}
	
	public void ataqueEspecial(Personaje atacante, Personaje enemigo) throws AtaqueNoPosible{
		if (!this.ataquePosible(atacante, enemigo)){
			throw new AtaqueNoPosible();
		}
		enemigo.inmovilizar();
		this.restarKi(atacante);
	}

}
