package modelo.personajes;

import modelo.personajes.modos.Modo;
import modelo.personajes.modos.ModoFinal;
import modelo.personajes.modos.ModoNormal;
import modelo.personajes.modos.ModoTransformado;
import modelo.tablero.Tablero;

public class MajinBoo extends PersonajeTransformableConKi {

	public MajinBoo(Tablero tablero) {
		super("Majin Boo", 300, new ModoNormal(30,2,2), tablero, 20, 50);
	}

	@Override
	public Modo transformarAModoTransformado(){
		super.transformarAModoTransformadoConKi();
		return new ModoTransformado(50,2,3);
	}
	
	@Override
	public Modo transformarAModoFinal() {
		super.transformarAModoFinalConKi();
		return new ModoFinal(60,3,4);
	}

	
}
