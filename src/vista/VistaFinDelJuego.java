package vista;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
		
		Image imagen;
		try {
			imagen = new Image(Files.newInputStream(Paths.get("src/vista/imagenes/shenlong 2.jpg")));
			BackgroundImage fondoImagen = new BackgroundImage(imagen, null, null, null, null);
			Background fondo = new Background(fondoImagen);
			this.setBackground(fondo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void mensajeFinJuego() {
		Text finJuego = new Text("Fin de la partida");
		finJuego.setFill(Color.WHITE);
		finJuego.setStroke(Color.BLACK);
		finJuego.setFont(Font.font("Castellar", 60));
		this.getChildren().add(finJuego);
	}
	
	private void mensajeGanador(DragonBall juego) {
		Text mensaje = new Text("El ganador es:");
		mensaje.setFill(Color.WHITE);
		mensaje.setStroke(Color.BLACK);
		mensaje.setFont(Font.font("Bahaus 93", 50));
		Text ganador = new Text(juego.getGanador());
		ganador.setFill(Color.WHITE);
		ganador.setStroke(Color.BLACK);
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
