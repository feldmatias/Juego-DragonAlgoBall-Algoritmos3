package vista;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class ImagenFondo extends StackPane {

	
	public ImagenFondo (Posicionable posicionable){
		
		if (posicionable == null){
			return;
		}
		
		InputStream entradaImagen;
		try {
			entradaImagen = Files.newInputStream(Paths.get("src/vista/imagenes/posicionables/"+posicionable.getNombre()+".png"));
			Image imagen = new Image(entradaImagen);
			entradaImagen.close();
			ImageView vistaImagen = new ImageView(imagen);
			this.getChildren().add(vistaImagen);
		} catch (IOException e) {
		}
		
	}

}
