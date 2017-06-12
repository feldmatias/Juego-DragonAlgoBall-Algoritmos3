package vista.ejemplos;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class EjemploEfectoSeleccionarPersonaje extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
	
		InputStream archivoImagen = Files.newInputStream(Paths.get("Recursos/gokuKamehameha recortado.png"));
	
		Image imagen = new Image(archivoImagen);
		ImageView vistaImagen = new ImageView(imagen);
		archivoImagen.close();

		 DropShadow ds= new DropShadow( 20, Color.AQUA );
		 
		 vistaImagen.setOnMouseClicked(evento -> vistaImagen.setEffect(ds));


		 final Scene scene = new Scene( new VBox( vistaImagen ), 800, 600 );
		    primaryStage.setScene( scene );
		    primaryStage.show();
		
		}
	
	public static void main(String[] args){
		launch(args);
	}
	
	
	

}
