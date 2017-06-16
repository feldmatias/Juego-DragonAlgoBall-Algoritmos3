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
	
	@Override
	public void ataqueEspecial(Personaje atacante, Personaje enemigo) throws AtaqueNoPosible{
		this.ataquePosible(atacante, enemigo);
			
		enemigo.inmovilizar();
		this.restarKi(atacante);
	}

}
