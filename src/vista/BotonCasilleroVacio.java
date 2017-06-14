package vista;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import modelo.juego.Casillero;

public class BotonCasilleroVacio extends BotonInvisible {
	
	private static boolean redimensionable = false;

	public BotonCasilleroVacio(Casillero casillero,MediaPlayer sonidoError) {
		super(new ImagenFondo(casillero.getConsumible(), redimensionable),sonidoError);
	}

	@Override
	protected Color getColorMouseEntered() {
		return Color.WHITE;
	}


}
