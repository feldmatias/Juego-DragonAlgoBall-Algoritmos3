package modelo.personajes;

import modelo.excepciones.AtaqueNoPosible;
import modelo.utilidades.Constantes;

public abstract class AtaqueEspecial {
	
	private int kiNecesario;
	
	public AtaqueEspecial(int kiNecesario){
		this.kiNecesario = kiNecesario;
	}
	
	public void ataqueEspecial(Personaje atacante, Personaje enemigo) throws AtaqueNoPosible{
		this.ataquePosible(atacante, enemigo);
		enemigo.recibirAtaque(this.getPoderAtaqueEspecial(atacante));
		this.restarKi(atacante);
	}

	public abstract double getPoderAtaqueEspecial(Personaje atacante);

	protected void ataquePosible(Personaje atacante, Personaje enemigo) throws AtaqueNoPosible {
		atacante.puedeAtacarA(enemigo);
		if (!atacante.alcanzaKi(this.kiNecesario)){
			throw new AtaqueNoPosible(Constantes.ErrorAtaqueEspecialKiInsuficiente);
		}
	}
	
	protected void restarKi(Personaje atacante){
		atacante.restarKi(kiNecesario);
	}

}
