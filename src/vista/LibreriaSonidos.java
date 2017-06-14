package vista;

import java.io.File;

import javafx.scene.media.Media;

public class LibreriaSonidos {
	
	public Media archivoSonidoError = new Media(new File("src/vista/sonidos/sonido error.wav").toURI().toString());

	public Media getSonidoError(){
		return archivoSonidoError;
	}

}
