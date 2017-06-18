package vista.botones;

import javafx.scene.paint.Color;
import modelo.juego.Casillero;
import vista.ConstantesPantalla;
import vista.LibreriaSonidos;

public class BotonCasilleroVacio extends BotonInvisible {
	

	public BotonCasilleroVacio(Casillero casillero,LibreriaSonidos sonidos) {
		super(new ImagenFondo(casillero.getConsumible(), ConstantesPantalla.anchoConsumible, ConstantesPantalla.altoConsumible),sonidos);
	}

	@Override
	protected Color getColorMouseEntered() {
		return Color.WHITE;
	}


}
