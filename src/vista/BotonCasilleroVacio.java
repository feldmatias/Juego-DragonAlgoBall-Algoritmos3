package vista;



import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import modelo.juego.Casillero;

public class BotonCasilleroVacio extends BotonInvisible {
	
	private static double width = BotonInvisible.anchoBoton * 0.3;
	private static double height = BotonInvisible.altoBoton * 0.3;

	public BotonCasilleroVacio(Casillero casillero,MediaPlayer sonidoError) {
		super(new ImagenFondo(casillero.getConsumible(), width, height),sonidoError);
	}

	@Override
	protected Color getColorMouseEntered() {
		return Color.WHITE;
	}


}
