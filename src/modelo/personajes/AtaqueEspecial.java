package modelo.personajes;

import modelo.excepciones.AtaqueNoPosible;

public abstract class AtaqueEspecial {
	
	private int kiNecesario;
	
	public AtaqueEspecial(int kiNecesario){
		this.kiNecesario = kiNecesario;
	}
	
	public void ataqueEspecial(Personaje atacante, Personaje enemigo) throws AtaqueNoPosible{
		if (!this.ataquePosible(atacante, enemigo)){
			throw new AtaqueNoPosible();
		}
		enemigo.recibirAtaque(this.getPoderAtaqueEspecial(atacante));
		this.restarKi(atacante);
	}

	public abstract double getPoderAtaqueEspecial(Personaje atacante);

	protected boolean ataquePosible(Personaje atacante, Personaje enemigo) {
		return atacante.puedeAtacarA(enemigo) && atacante.comprobarKiNecesario(this.kiNecesario);
	}
	
	protected void restarKi(Personaje atacante){
		atacante.restarKi(kiNecesario);
	}

}
