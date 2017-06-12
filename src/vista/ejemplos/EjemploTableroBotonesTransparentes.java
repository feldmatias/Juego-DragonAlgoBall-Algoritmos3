package vista.ejemplos;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import modelo.utilidades.Constantes;

public class EjemploTableroBotonesTransparentes extends Application{
		
	
public void start(Stage primaryStage) throws Exception {
		
		Pane root = new Pane();
		root.setPrefSize(1000, 700);
		
		InputStream entradaImagen = Files.newInputStream(Paths.get("Recursos/mapa feo.jpg"));
		
		Image imagen = new Image(entradaImagen);
		
		entradaImagen.close();
		
		ImageView vistaImagen = new ImageView(imagen);
		vistaImagen.setFitWidth(800);
		vistaImagen.setFitHeight(600);
		
		
		
		
		
		
		VBox contenedorPrincipal = new VBox();
		HBox fila;
		
		
		for (int i = 0; i < 8; i++){
			fila = new HBox();
			for(int j= 0 ; j < 8; j++){
//				Button boton = new Button();
				BotonTransparente boton = new BotonTransparente();
				fila.getChildren().add(boton);
			}
			contenedorPrincipal.getChildren().add(fila);
		}
		
		root.getChildren().addAll(vistaImagen,contenedorPrincipal);
		
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		primaryStage.show();


		
		
	}	


	


	private static class BotonTransparente extends StackPane{
		
		public BotonTransparente(){
			Rectangle fondo = new Rectangle(100,60);
			fondo.setFill(Color.TRANSPARENT);
			fondo.setStroke(Color.BLACK);
		
			this.getChildren().add(fondo);
			

			DropShadow sombra = new DropShadow(50,Color.WHITE);
			sombra.setInput(new Glow());
			
			this.setOnMouseEntered(evento -> {
				fondo.setFill(Color.WHITE);
				fondo.setOpacity(0.5);
				fondo.setEffect(sombra);
			});
			
			this.setOnMouseExited(evento2 -> {
				fondo.setFill(Color.TRANSPARENT);
				fondo.setEffect(null);
				
			});
			
			
		}

		
		
	}

	public static void main(String[] args){
		launch(args);
	}
	
}
