package vista;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import modelo.personajes.Personaje;

public class ImagenFondo extends StackPane {

	
	public ImagenFondo (Personaje personaje){
		Label nombre = new Label();
		nombre.setText(personaje.getNombre());
		
		Label vida = new Label();
		vida.setText(String.valueOf(personaje.getPorcentajeVida()));

		InputStream entradaImagen;
		try {
			entradaImagen = Files.newInputStream(Paths.get("src/vista/imagenes/personajes/"+personaje.getNombre()+".png"));
			Image imagen = new Image(entradaImagen);
			entradaImagen.close();
			ImageView vistaImagen = new ImageView(imagen);
			vistaImagen.setFitWidth(BotonInvisible.anchoBoton);
			vistaImagen.setFitHeight(BotonInvisible.altoBoton);
			this.getChildren().add(vistaImagen);
		} catch (IOException e) {
		}
		
		VBox labels = new VBox(nombre,vida);
		this.getChildren().add(labels);
	}

}
