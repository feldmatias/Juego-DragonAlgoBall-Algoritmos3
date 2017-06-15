package vista;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modelo.juego.DragonBall;

public class VistaFinDelJuego extends VBox {

	
	public VistaFinDelJuego(DragonBall juego, Stage stage){
		
		this.mensajeFinJuego();
		this.mensajeGanador(juego);
		this.botonMenu(stage);
	}

	private void mensajeFinJuego() {
		Text finJuego = new Text("Termino El Juego");
		finJuego.setFont(Font.font(30));
		this.getChildren().add(finJuego);
	}
	
	private void mensajeGanador(DragonBall juego) {
		Text mensaje = new Text("El ganador es:");
		mensaje.setFont(Font.font(30));
		Text ganador = new Text(juego.getGanador());
		ganador.setFont(Font.font(20));
		this.getChildren().addAll(mensaje, ganador);
	}
	
	private void botonMenu(Stage stage) {
		BotonMenu volver = new BotonMenu("Volver al Menu Principal");
		volver.setOnMouseClicked( evento -> {
				MenuPrincipal menu = new MenuPrincipal(stage);
				Scene escenaMenu = new Scene (menu);
				stage.setScene(escenaMenu);
		});
	}
}
