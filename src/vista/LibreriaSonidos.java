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
	private MediaPlayer sonidoAtaque;
	private MediaPlayer sonidoAtaqueEspecial;
	private MediaPlayer sonidoBotonMenu;
	private double volumenMusica;
	private double volumenEfectosSonido;
	
	private final String carpetaSonidos = "src/vista/sonidos/";


	public LibreriaSonidos(){
		this.musica =  new MediaPlayer(new Media(new File(carpetaSonidos + "Dragon Ball opening.mp3").toURI().toString()));
		
		this.sonidoBotonMenu = new MediaPlayer(new Media(new File(carpetaSonidos + "sonido deslizarse por boton.wav").toURI().toString()));
		
		this.sonidoError = new MediaPlayer(new Media(new File(carpetaSonidos + "sonido error.wav").toURI().toString()));
		
		this.sonidoTransformacion = new MediaPlayer(new Media(new File(carpetaSonidos + "transformacion.wav").toURI().toString()));
		this.sonidoMovimiento = new MediaPlayer(new Media(new File(carpetaSonidos + "teleport.wav").toURI().toString()));
		this.sonidoAtaque = new MediaPlayer(new Media(new File(carpetaSonidos + "ataque.wav").toURI().toString()));
		this.sonidoAtaqueEspecial = new MediaPlayer(new Media(new File(carpetaSonidos + "ataque especial.wav").toURI().toString()));
		
		
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
	
	public void reproducirSonidoAtaque(){
		this.reproducirSonidoEfecto(sonidoAtaque);
	}
	
	public void reproducirSonidoAtaqueEspecial(){
		this.reproducirSonidoEfecto(sonidoAtaqueEspecial);
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
