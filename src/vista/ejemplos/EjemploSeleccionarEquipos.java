package vista.ejemplos;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import modelo.juego.DragonBall;
import modelo.utilidades.Constantes;
import vista.VistaJuego;

public class EjemploSeleccionarEquipos extends Application{
	private DragonBall juego;
	private Stage stage;

		


	@Override
	public void start(Stage primaryStage) throws Exception {
		this.stage = primaryStage;
		
		Pane raiz = new Pane();
			
		InputStream archivoEnemigos = Files.newInputStream(Paths.get("src/vista/imagenes/enemigos2.jpg"));
		InputStream archivoGuerreros = Files.newInputStream(Paths.get("src/vista/imagenes/guerreros.png"));
		
		Image imagenGuerreros = new Image(archivoGuerreros);		

		Image imagenEnemigos = new Image(archivoEnemigos);
		
		VistaEquipo vistaImagenGuerreros = new VistaEquipo("guerrero",imagenGuerreros);
		vistaImagenGuerreros.setFitWidth(500);
		vistaImagenGuerreros.setFitHeight(500);
		VistaEquipo vistaImagenEnemigos = new VistaEquipo("enemigos",imagenEnemigos);
		vistaImagenEnemigos.setFitWidth(500);
		vistaImagenEnemigos.setFitHeight(500);
		
		Contenedor imagenes = new Contenedor(vistaImagenEnemigos,vistaImagenGuerreros);
		
		raiz.getChildren().addAll(imagenes);
		
		Scene escena = new Scene(raiz);
		stage.setMinHeight(600);
		stage.setMinWidth(1000);
		
		stage.setScene(escena);
		stage.show();
		
		
	}
	
	private class VistaEquipo extends ImageView{
		
		private String nombre;
		
		public VistaEquipo(String nombre, Image imagen){
			super(imagen);
			this.nombre = nombre;
			
		}

		public String getEquipo(){
			return "Equipo Seleccionado " + this.nombre;
		
		}
	}
	
	private class Contenedor extends VBox{
		
		Rectangle fondo;
		StackPane imagenes;
		VistaEquipo imagenActual;
		VistaEquipo proximaImagen;
		HBox botones;
		
		public Contenedor(VistaEquipo enemigos, VistaEquipo guerreros){
			
			this.fondo = new Rectangle(1000,600);
			this.fondo.setFill(Color.RED);
			this.fondo.setOpacity(1);
			this.imagenActual = enemigos;
			this.proximaImagen = guerreros;
			this.imagenes = new StackPane();
			
			
			this.imagenActual.setTranslateX(200);
			this.proximaImagen.setTranslateX(200);
			
			this.imagenes.getChildren().addAll(this.imagenActual,this.proximaImagen);

			this.proximaImagen.setVisible(false);
			
			this.botones = new HBox();
			Button btnSiguiente = new Button("siguiente");
			Button btnSeleccionar = new Button("seleccionar");
			
			
			this.botones.getChildren().addAll(btnSiguiente,btnSeleccionar);
			
			btnSiguiente.setOnAction(evento-> {
				VistaEquipo imagenRemovida = this.imagenActual;
//				TranslateTransition transicionTraslado = new TranslateTransition(Duration.seconds(2), this.imagenActual);
//				transicionTraslado.setToX( this.imagenActual.getTranslateX() + 1000 );
//				transicionTraslado.play();
				
				this.imagenActual.setVisible(false);
				proximaImagen.setVisible(true);
				
				
				this.imagenActual = this.proximaImagen;
				this.proximaImagen = imagenRemovida;			
				
			});
			
			btnSeleccionar.setOnAction(evento2 -> System.out.println(this.imagenActual.getEquipo()));
			
			
			this.getChildren().addAll(this.imagenes,this.botones);
			
			
		}
		
			
		
		
	}
	
	public static void main(String [] args){
		launch(args);
	}
	
}

