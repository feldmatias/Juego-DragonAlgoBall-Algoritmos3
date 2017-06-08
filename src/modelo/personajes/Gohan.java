package modelo.personajes;

import modelo.personajes.modos.Modo;
import modelo.personajes.modos.ModoFinal;
import modelo.personajes.modos.ModoNormal;
import modelo.personajes.modos.ModoTransformado;
import modelo.tablero.Tablero;

public class Gohan extends PersonajeTransformableConKi {
	
	public Gohan(Tablero tablero) {
		super("Gohan", 300, new ModoNormal(15,2,2), new AtaquePotenciador(10,1.25), tablero, 10, 30);
	}

	@Override
	public Modo transformarAModoTransformado(){
		super.transformarAModoTransformadoConKi();
		return new ModoTransformado(30,2,2);
	}
	
	@Override
	public boolean puedeTransformarseAModoFinal() {
		boolean puedeTransformarse = super.puedeTransformarseAModoFinal();
		
		for (Personaje personaje: this.getEquipo().getMiembros()){
			if (personaje == this){
				continue;
			}
			puedeTransformarse = puedeTransformarse && (personaje.getPorcentajeVida() < 30);
		}
		return puedeTransformarse;
	}

	@Override
	public Modo transformarAModoFinal() {
		super.transformarAModoFinalConKi();
		return new ModoFinal(100,4,3);
	}
	

}
