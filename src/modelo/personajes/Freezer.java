package modelo.personajes;

import modelo.personajes.modos.Modo;
import modelo.personajes.modos.ModoFinal;
import modelo.personajes.modos.ModoNormal;
import modelo.personajes.modos.ModoTransformado;
import modelo.tablero.Tablero;

public class Freezer extends PersonajeTransformableConKi{

	public Freezer(Tablero tablero) {
		super("Freezer", 400, new ModoNormal(20,2,4),  tablero, 20, 50);
	}
	
	@Override
	public Modo transformarAModoTransformado(){
		super.transformarAModoTransformadoConKi();
		return new ModoTransformado(40,3,4);
	}
	
	@Override
	public Modo transformarAModoFinal() {
		super.transformarAModoFinalConKi();
		return new ModoFinal(50,3,6);
	}

}
