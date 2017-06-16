package vista;

import javafx.scene.paint.Color;
import modelo.juego.Casillero;

public class BotonCasilleroVacio extends BotonInvisible {
	
	private static double width = BotonInvisible.anchoBoton * 0.3;
	private static double height = BotonInvisible.altoBoton * 0.3;

	public BotonCasilleroVacio(Casillero casillero,LibreriaSonidos sonidos) {
		super(new ImagenFondo(casillero.getConsumible(), width, height),sonidos);
	}

	@Override
	protected Color getColorMouseEntered() {
		return Color.WHITE;
	}


}
