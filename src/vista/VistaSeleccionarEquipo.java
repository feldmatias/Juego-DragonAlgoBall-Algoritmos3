package vista;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import modelo.juego.DragonBall;
import modelo.utilidades.Constantes;

public class VistaSeleccionarEquipo extends HBox{
	private DragonBall juego;
	private Stage stage;
	public VistaSeleccionarEquipo(DragonBall juego, Stage stage){
		
		this.juego = juego;
		this.stage = stage;
		this.crearSeleccionables();
		this.setAlignment(Pos.CENTER);
		Image imagen = new Image("file:src/vista/imagenes/FondoElegir.jpg");
		BackgroundImage imagenDeFondo = new BackgroundImage(imagen, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
	    this.setBackground(new Background(imagenDeFondo));
	}
	
	private void crearSeleccionables() {
		
		InputStream archivoImagenGuerreros = null;
		try {
			archivoImagenGuerreros = Files.newInputStream(Paths.get("src/vista/imagenes/guerreros.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Image imagenGuerreros = new Image(archivoImagenGuerreros);
		ImageView vistaImagenGuerreros = new ImageView(imagenGuerreros);
		try {
			archivoImagenGuerreros.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		vistaImagenGuerreros.setFitWidth(700);
		vistaImagenGuerreros.setPreserveRatio(true);
		
		DropShadow dsGuerreros= new DropShadow( 20, Color.GREEN );
		DropShadow dsGuerreros2= new DropShadow();
		vistaImagenGuerreros.setOnMouseMoved(evento ->{
			vistaImagenGuerreros.setEffect(dsGuerreros);
		});
		vistaImagenGuerreros.setOnMouseExited(evento ->{
			vistaImagenGuerreros.setEffect(dsGuerreros2);
		});
		
		vistaImagenGuerreros.setOnMouseClicked(evento -> {
			
			System.out.println("El jugador 1 eligió " + Constantes.GUERREROS); //el jugador 1 elige equipo GUERREROS
			
			juego.establecerEquipoJugador1(Constantes.GUERREROS);
			juego.establecerEquipoJugador2(Constantes.ENEMIGOS);
			juego.iniciar();
			Scene scene = new Scene(new VistaJuego(juego));
			stage.setScene(scene);
			stage.show();
			
		});
		
		this.getChildren().add(vistaImagenGuerreros);
		
		InputStream archivoImagenEnemigos = null;
		try {
			archivoImagenEnemigos = Files.newInputStream(Paths.get("src/vista/imagenes/enemigos.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Image imagenEnemigos = new Image(archivoImagenEnemigos);
		ImageView vistaImagenEnemigos = new ImageView(imagenEnemigos);
		try {
			archivoImagenEnemigos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		vistaImagenEnemigos.setFitWidth(700);
		vistaImagenEnemigos.setPreserveRatio(true);

		DropShadow dsEnemigos= new DropShadow( 20, Color.RED );
		DropShadow dsEnemigos2= new DropShadow();
		vistaImagenEnemigos.setOnMouseMoved(evento ->{
			vistaImagenEnemigos.setEffect(dsEnemigos);
		});
		vistaImagenEnemigos.setOnMouseExited(evento ->{
			vistaImagenEnemigos.setEffect(dsEnemigos2);
		});
		
		vistaImagenEnemigos.setOnMouseClicked(evento -> {
			System.out.println("El jugador 1 eligió "+ Constantes.ENEMIGOS); //el jugador 1 elige equipo ENEMIGOS
			juego.establecerEquipoJugador1(Constantes.ENEMIGOS);
			juego.establecerEquipoJugador2(Constantes.GUERREROS);
			juego.iniciar();
			Scene scene = new Scene(new VistaJuego(juego));
			stage.setScene(scene);
			stage.show();
		});
		this.getChildren().add(vistaImagenEnemigos);
		
	}
}
