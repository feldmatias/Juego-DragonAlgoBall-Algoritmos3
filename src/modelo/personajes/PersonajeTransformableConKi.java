package modelo.personajes;

import modelo.tablero.Tablero;

public abstract class PersonajeTransformableConKi extends Personaje{

	private int kiNecesarioModoTransformado;
	private int kiNecesarioModoFinal;
	
	public PersonajeTransformableConKi(String nombre, int vidaInicial, Tablero tablero, int kiModoTransformado, int kiModoFinal){
		super(nombre, vidaInicial, tablero);
		this.kiNecesarioModoTransformado = kiModoTransformado;
		this.kiNecesarioModoFinal = kiModoFinal;
	}
	
	@Override
	public void transformarAModoTransformado() {
		this.restarKi(kiNecesarioModoTransformado);
	}

	@Override
	public boolean puedeTransformarseAModoTransformado() {
		return this.comprobarKiNecesario(kiNecesarioModoTransformado);
	}
	
	@Override
	public void transformarAModoFinal() {
		this.restarKi(kiNecesarioModoFinal);
	}

	@Override
	public boolean puedeTransformarseAModoFinal() {
		return this.comprobarKiNecesario(kiNecesarioModoFinal);
	}
	
	
}
