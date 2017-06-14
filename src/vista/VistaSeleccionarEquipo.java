package vista;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import modelo.juego.DragonBall;
import modelo.personajes.Personaje;
import modelo.utilidades.Constantes;
import vista.controlador.BotonAtacarEventHandler;
import vista.controlador.BotonAtaqueEspecialEventHandler;
import vista.controlador.BotonCasilleroVacioEventHandler;
import vista.controlador.BotonMoverEventHandler;
import vista.controlador.BotonTerminarTurnoEventHandler;
import vista.controlador.BotonTransformarEventHandler;

public class VistaSeleccionarEquipo extends HBox{
	private DragonBall juego;
	private Stage stage;
	public VistaSeleccionarEquipo(DragonBall juego, Stage stage){
		
		this.juego = juego;
		this.stage = stage;
		this.crearSeleccionables();
		this.setAlignment(Pos.CENTER);
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
		
		/*Button elegirGuerreros = new Button();
		EventHandler<MouseEvent> eventHandler = new BotonElegirGuerrerosEventHandler(juego, this);
		elegirGuerreros.setOnAction(eventHandler);
		elegirGuerreros.setText("Elijo Guerreros");
		
		this.getChildren().add(elegirGuerreros);*/
		
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
