package vista;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;

import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import modelo.juego.DragonBall;

public class VistaFinDelJuego extends VBox {
	LibreriaSonidos sonidos;
	private double anchoImgFondo = Screen.getPrimary().getVisualBounds().getWidth();
	private double altoImgFondo = Screen.getPrimary().getVisualBounds().getHeight();
	
	
	public VistaFinDelJuego(DragonBall juego, Stage stage,LibreriaSonidos sonidos){
		
		this.sonidos = sonidos;
		this.mensajeFinJuego();
		this.mensajeGanador(juego);
		this.botonMenu(stage);
		this.setAlignment(Pos.CENTER);
		this.setSpacing(100);
		
		Image imagen = new Image("file:src/vista/imagenes/Shenlong 3.jpg");
		BackgroundSize size = new BackgroundSize(altoImgFondo,anchoImgFondo,false,false,true,true);
		BackgroundImage imagenDeFondo = new BackgroundImage(imagen, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,size);
		this.setBackground(new Background(imagenDeFondo));
		
	}

	private void mensajeFinJuego() {
		Text finJuego = new Text("Termino el Juego");
		finJuego.setFont(Font.loadFont("file:src/vista/imagenes/Saiyan-Sans.ttf", 80));
		finJuego.setFill(Color.RED);
		finJuego.setStroke(Color.WHITE);
		this.getChildren().add(finJuego);
	}
	
	private void mensajeGanador(DragonBall juego) {
		Text mensaje = new Text("El ganador es:");

		mensaje.setFont(Font.loadFont("file:src/vista/imagenes/Saiyan-Sans.ttf", 40));
		mensaje.setFill(Color.YELLOW);
		mensaje.setStroke(Color.WHITE);
		Text ganador = new Text(juego.getGanador());

		ganador.setFont(Font.loadFont("file:src/vista/imagenes/Saiyan-Sans.ttf", 50));
		ganador.setFill(Color.DARKCYAN);
		ganador.setStroke(Color.WHITE);
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
