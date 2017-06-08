package modelo.personajes;

import modelo.personajes.modos.Modo;
import modelo.tablero.Tablero;

public abstract class PersonajeTransformableConKi extends Personaje{

	private int kiNecesarioModoTransformado;
	private int kiNecesarioModoFinal;
	
	public PersonajeTransformableConKi(String nombre, int vidaInicial, Modo modoInicial, Tablero tablero, int kiModoTransformado, int kiModoFinal){
		super(nombre, vidaInicial, modoInicial, tablero);
		this.kiNecesarioModoTransformado = kiModoTransformado;
		this.kiNecesarioModoFinal = kiModoFinal;
	}
	
	public void transformarAModoTransformadoConKi() {
		this.restarKi(kiNecesarioModoTransformado);
	}

	@Override
	public boolean puedeTransformarseAModoTransformado() {
		return this.comprobarKiNecesario(kiNecesarioModoTransformado);
	}
	
	public void transformarAModoFinalConKi() {
		this.restarKi(kiNecesarioModoFinal);
	}

	@Override
	public boolean puedeTransformarseAModoFinal() {
		return this.comprobarKiNecesario(kiNecesarioModoFinal);
	}
	
	
}
