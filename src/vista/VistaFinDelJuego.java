package vista;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modelo.juego.DragonBall;

public class VistaFinDelJuego extends VBox {
	LibreriaSonidos sonidos;
	
	public VistaFinDelJuego(DragonBall juego, Stage stage,LibreriaSonidos sonidos){
		
		this.sonidos = sonidos;
		this.mensajeFinJuego();
		this.mensajeGanador(juego);
		this.botonMenu(stage);
		this.setAlignment(Pos.CENTER);
		this.setSpacing(100);
	}

	private void mensajeFinJuego() {
		Text finJuego = new Text("Termino el Juego");
		finJuego.setFont(Font.font("Castellar", 60));
		this.getChildren().add(finJuego);
	}
	
	private void mensajeGanador(DragonBall juego) {
		Text mensaje = new Text("El ganador es:");
		mensaje.setFont(Font.font("Bahaus 93", 50));
		Text ganador = new Text(juego.getGanador());
		ganador.setFont(Font.font("Comic Sans MS", 40));
		this.getChildren().addAll(mensaje, ganador);
	}
	
	private void botonMenu(Stage stage) {
		BotonMenu volver = new BotonMenu("Volver al Menu Principal" , sonidos);
		volver.setOnMouseClicked( evento -> {
				Boolean pantallaCompleta = this.comprobarPantallaCompleta(stage);
				MenuPrincipal menu = new MenuPrincipal(stage,sonidos);
				Scene escenaMenu = new Scene (menu);
				stage.setScene(escenaMenu);
				stage.setFullScreen(pantallaCompleta);
		});
		volver.setAlignment(Pos.CENTER);
		this.getChildren().add(volver);
	}

	private Boolean comprobarPantallaCompleta(Stage stage) {
		return stage.isFullScreen();
	}
}
