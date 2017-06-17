package vista;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class LibreriaSonidos {
	
	private static double volumenEfectosPorDefecto = 0.2;
	
	private MediaPlayer	sonidoError;
	private MediaPlayer musica;
	private MediaPlayer sonidoTransformacion;
	private MediaPlayer sonidoMovimiento;
	private MediaPlayer sonidoBotonMenu;
	private double volumenMusica;
	private double volumenEfectosSonido;


	public LibreriaSonidos(){
		this.musica =  new MediaPlayer(new Media(new File("src/vista/sonidos/Dragon Ball opening.mp3").toURI().toString()));
		
		this.sonidoBotonMenu = new MediaPlayer(new Media(new File("src/vista/sonidos/sonido deslizarse por boton.wav").toURI().toString()));
		
		this.sonidoError = new MediaPlayer(new Media(new File("src/vista/sonidos/sonido error.wav").toURI().toString()));
		
		this.sonidoTransformacion = new MediaPlayer(new Media(new File("src/vista/sonidos/transformacion.wav").toURI().toString()));
		this.sonidoMovimiento = new MediaPlayer(new Media(new File("src/vista/sonidos/teleport.wav").toURI().toString()));
		
		
		this.volumenMusica = 0.1;
		this.volumenEfectosSonido = volumenEfectosPorDefecto;
		


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
		this.volumenEfectosSonido = volumenEfectosPorDefecto;
	}
	
	public void	reproducirMusica(){
		musica.setVolume(this.volumenMusica);
		musica.setCycleCount(MediaPlayer.INDEFINITE);
		musica.play();
	}
	
	public void reproducirSonidoError(){
		this.reproducirSonidoEfecto(sonidoError);
	}
	
	public void reproducirSonidoTransformacion(){
		this.reproducirSonidoEfecto(sonidoTransformacion);
	}
	
	public void reproducirSonidoMovimiento(){
		this.reproducirSonidoEfecto(sonidoMovimiento);
	}
	
	public void reproducirSonidoBotonMenu() {
		this.reproducirSonidoEfecto(sonidoBotonMenu);
	}
	
	private void reproducirSonidoEfecto(MediaPlayer sonido){
		sonido.setVolume(volumenEfectosSonido);
		sonido.stop();
		sonido.play();
	}



}
