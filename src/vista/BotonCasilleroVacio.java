package vista;


import javafx.scene.paint.Color;
import modelo.juego.Casillero;

public class BotonCasilleroVacio extends BotonInvisible {

	public BotonCasilleroVacio(Casillero casillero) {
		super(new ImagenFondo(casillero.getConsumible()));
	}

	@Override
	protected Color getColorMouseEntered() {
		return Color.WHITE;
	}


}
