package vista.controlador;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import vista.BotonInvisible;

public class MovimientoAtaqueEventHandler implements EventHandler<ActionEvent>{
	
	
	BotonInvisible atacante;
	BotonInvisible atacado;
	double posInicialX;
	double posInicialY;
	double posDestinoX;
	double posDestinoY;
	
	public MovimientoAtaqueEventHandler(BotonInvisible atacante, BotonInvisible atacado){
		this.atacante = atacante;
		this.atacado = atacado;
		
		this.posInicialX = atacante.getTranslateX();
		this.posInicialY = atacante.getTranslateY();
		
		this.posDestinoX = atacado.getTranslateX();
		this.posDestinoY = atacado.getTranslateY();
	}
	
	@Override
	public void handle(ActionEvent event) {
	
		TranslateTransition transicionTraslado = new TranslateTransition(Duration.seconds(2), this.atacante);
		transicionTraslado.setToX(this.posDestinoX);
		transicionTraslado.setToY(this.posDestinoY);
		transicionTraslado.setAutoReverse(true);
		
		transicionTraslado.play();
		
	}
	

	
	
}
