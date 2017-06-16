package vista;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class LibreriaSonidos {
	
	private Media archivoSonidoError;
	private Media archivoMusica;
	
	public LibreriaSonidos(){
		this.archivoSonidoError = new Media(new File("src/vista/sonidos/sonido error.wav").toURI().toString());
		this.archivoMusica = new Media(new File("src/vista/sonidos/Dragon Ball opening.mp3").toURI().toString());


	}
	

	public MediaPlayer getSonidoError(){
		MediaPlayer sonido = new MediaPlayer(archivoSonidoError);
		return sonido;
	}
	
	public MediaPlayer getMusica(){
		MediaPlayer musica = new MediaPlayer(archivoMusica);
		return musica;
	}

}
