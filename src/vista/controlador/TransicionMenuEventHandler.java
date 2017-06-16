package vista.controlador;

import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import vista.MenuPrincipal;

public class TransicionMenuEventHandler  implements EventHandler<MouseEvent>{
	private MenuPrincipal menuPrincipal;
	private VBox submenuActual;
	private VBox submenuProximo;
	private double posNueva;

	public TransicionMenuEventHandler(MenuPrincipal menuPrincipal,VBox submenuActual,VBox submenuProximo, double posNueva){
		
		this.menuPrincipal = menuPrincipal;
		this.submenuActual = submenuActual;
		this.submenuProximo = submenuProximo;
		this.posNueva = posNueva;
	}

	@Override
	public void handle(MouseEvent event) {
		this.menuPrincipal.getChildren().addAll(this.submenuProximo);
		TranslateTransition transicionTraslado1 = new TranslateTransition(Duration.seconds(0.25),this.submenuActual);
		transicionTraslado1.setToX( this.submenuActual.getTranslateX() - this.posNueva );
		
		TranslateTransition transicionTraslado2 = new TranslateTransition(Duration.seconds(0.5),this.submenuProximo);
		transicionTraslado2.setToX( this.submenuActual.getTranslateX() );
		
		transicionTraslado1.play();
		transicionTraslado2.play();
		
		transicionTraslado1.setOnFinished( evento -> {
			this.menuPrincipal.getChildren().remove(this.submenuActual);
		});	
	}
	
}
