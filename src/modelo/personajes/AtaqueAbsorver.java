package modelo.personajes;

import modelo.excepciones.AtaqueNoPosible;

public class AtaqueAbsorver extends AtaqueEspecial {

	private int cantidadUsos;
	
	public AtaqueAbsorver(int kiNecesario) {
		super(kiNecesario);
		this.cantidadUsos = 0;
	}

	@Override
	public void ataqueEspecial(Personaje atacante, Personaje enemigo) throws AtaqueNoPosible{
		super.ataqueEspecial(atacante, enemigo);
		atacante.regenerarVida(this.getPoderAtaqueEspecial(atacante));
		this.cantidadUsos += 1;
	}
	
	@Override
	public double getPoderAtaqueEspecial(Personaje atacante) {
		return atacante.getPoderPelea();
	}
	
	public int getCantidadUsos(){
		return this.cantidadUsos;
	}

}
