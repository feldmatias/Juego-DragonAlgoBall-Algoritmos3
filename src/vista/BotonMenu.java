package vista;

import java.io.File;

import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class BotonMenu extends StackPane {
	private Text texto;
	private MediaPlayer sonido;
	private Rectangle fondo;
	private Color colorActualBoton;
	private Color colorAuxiliarBoton;
	
	public BotonMenu(String nombre){
		texto = new Text(nombre);
		texto.setFont(Font.font(20));
		texto.setFill(Color.WHITE);
		
		Media archivoSonido= new Media(new File("src/vista/sonidos/sonido deslizarse por boton.wav").toURI().toString());
		MediaPlayer sonido = new MediaPlayer(archivoSonido);
		
		this.colorActualBoton =Color.ORANGE;
		this.colorAuxiliarBoton = Color.WHITE;
		this.fondo = new Rectangle(250,30);
		this.fondo.setOpacity(0.6);
		this.fondo.setFill(colorActualBoton);
		
		GaussianBlur desenfoque = new GaussianBlur(3.5);
		this.fondo.setEffect(desenfoque);
		
		this.setAlignment(Pos.CENTER_LEFT);
		this.setRotate(-0.5);
		this.getChildren().addAll(fondo,texto);
		
		this.setOnMouseEntered(evento -> {
			this.fondo.setTranslateX(10);
			this.texto.setTranslateX(10);
			this.fondo.setFill(colorAuxiliarBoton);
			this.texto.setFill(Color.ORANGE);
			sonido.play();
		});
		
		this.setOnMouseExited(event ->{
			this.fondo.setTranslateX(0);
			this.texto.setTranslateX(0);
			this.fondo.setFill(colorActualBoton);
			this.texto.setFill(Color.WHITE);
			sonido.stop();
		});
		
		
		DropShadow sombra = new DropShadow(50,Color.WHITE);
		sombra.setInput(new Glow());
		
		this.setOnMousePressed(evento -> setEffect(sombra));
		this.setOnMouseReleased(evento -> setEffect(null));
		
	}
	
	public void deshabilitar(){
		colorActualBoton = Color.GRAY;
		this.fondo.setFill(colorActualBoton);
		this.setDisable(true);
	}
	public void habilitar(){
		colorActualBoton = Color.ORANGE;
		this.fondo.setFill(colorActualBoton);
		this.setDisable(false);
	}
	
}
	
