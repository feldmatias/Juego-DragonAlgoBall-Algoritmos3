package modelo.personajes;

import modelo.personajes.modos.Modo;
import modelo.personajes.modos.ModoFinal;
import modelo.personajes.modos.ModoNormal;
import modelo.personajes.modos.ModoTransformado;
import modelo.tablero.Tablero;

public class Piccolo extends PersonajeTransformableConKi {

	public Piccolo(Tablero tablero) {
		super("Piccolo", 500, new ModoNormal(20,2,2), new AtaquePotenciador(10,1.25), tablero, 20 ,0);
	}
	
	public Modo transformarAModoTransformado(){
		super.transformarAModoTransformadoConKi();
		return new ModoTransformado(40,4,3);
	}

	@Override
	public Modo transformarAModoFinal() {
		super.transformarAModoFinalConKi();
		return new ModoFinal(60,6,4);
	}
	
	@Override
	public boolean puedeTransformarseAModoFinal() {

		for (Personaje personaje: this.getEquipo().getMiembros()){
			if (personaje.getNombre() == "Gohan"){
				return personaje.getPorcentajeVida() < 20;
			}
		}
		return true; //Gohan esta muerto
	}

}
