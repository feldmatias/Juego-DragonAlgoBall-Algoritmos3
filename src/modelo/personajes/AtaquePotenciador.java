package modelo.personajes;

public class AtaquePotenciador extends AtaqueEspecial {

	private double multiplicador;
	
	public AtaquePotenciador(int kiNecesario, double multiplicador) {
		super(kiNecesario);
		this.multiplicador = multiplicador;
	}

	@Override
	public double getPoderAtaqueEspecial(Personaje atacante) {
		return atacante.getPoderPelea() * this.multiplicador;
	}

}
