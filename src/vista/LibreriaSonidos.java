package vista;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class LibreriaSonidos {
	
	private MediaPlayer	sonidoError;
	private MediaPlayer musica;
	private double volumenMusica;
	private double volumenEfectosSonido;
	
	public LibreriaSonidos(){
		
		
//		this.archivoSonidoError = new Media(new File("src/vista/sonidos/sonido error.wav").toURI().toString());
//		this.archivoMusica = new Media(new File("src/vista/sonidos/Dragon Ball opening.mp3").toURI().toString());
		
		this.sonidoError = new MediaPlayer(new Media(new File("src/vista/sonidos/sonido error.wav").toURI().toString()));
		this.musica =  new MediaPlayer(new Media(new File("src/vista/sonidos/Dragon Ball opening.mp3").toURI().toString()));
		
		this.volumenMusica = 0.1;
		this.volumenEfectosSonido = 0.2;


	}
	
	
	public void mutearMusica(){
		this.musica.setMute(true);
	}
	
	public void mutearEfectosSonido(){
		this.volumenEfectosSonido = 0;
	}
	
	public void desmutearMusica(){
		this.musica.setMute(false);
	}
	
	public void desmutearEfectosSonido(){
		this.volumenEfectosSonido = 0.2;
	}

	public void reproducirSonidoError(){
		sonidoError.setVolume(this.volumenEfectosSonido);
		sonidoError.play();
		sonidoError.stop();
	}
	
	public void	reproducirMusica(){
		musica.setVolume(this.volumenMusica);
		musica.setCycleCount(MediaPlayer.INDEFINITE);
		musica.play();
	}

}
