package vista;


import javafx.scene.paint.Color;
import modelo.juego.Casillero;

public class BotonCasilleroVacio extends BotonInvisible {
	
	private static boolean redimensionable = false;

	public BotonCasilleroVacio(Casillero casillero) {
		super(new ImagenFondo(casillero.getConsumible(), redimensionable));
	}

	@Override
	protected Color getColorMouseEntered() {
		return Color.WHITE;
	}


}
