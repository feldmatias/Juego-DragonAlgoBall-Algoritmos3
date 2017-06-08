package modelo.personajes;

public class AtaquePotenciador extends AtaqueEspecial {

	private double multiplicador;
	
	public AtaquePotenciador(int kiNecesario, double d) {
		super(kiNecesario);
		this.multiplicador = d;
	}

	@Override
	public double getPoderAtaqueEspecial(Personaje atacante) {
		return atacante.getPoderPelea() * this.multiplicador;
	}

}
