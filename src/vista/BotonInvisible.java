package vista;



import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class BotonInvisible extends StackPane{

	public static int anchoBoton = 60;
	public static int altoBoton = 60;
	private Rectangle boton;
	private Node imagenFondo;
	private MediaPlayer sonidoError;
	
	public BotonInvisible(Node imagenFondo,MediaPlayer sonidoError){
		this.imagenFondo = imagenFondo;
		this.getChildren().add(imagenFondo);
		this.sonidoError = sonidoError;
		this.sonidoError.setVolume(0.1);
		this.crearBoton();
	}


	private void crearBoton() {
		boton = new Rectangle(anchoBoton,altoBoton);
		boton.setFill(Color.TRANSPARENT);
		boton.setStroke(Color.BLACK);
		this.getChildren().add(boton);
		
		this.setEfectoMouseEntered();
		
	}
	
	protected void setEfectoMouseEntered() {
		DropShadow sombra = new DropShadow(50,Color.WHITE);
		sombra.setInput(new Glow());
		
		this.setOnMouseEntered(evento -> {
			boton.setFill(this.getColorMouseEntered());
			boton.setOpacity(0.5);
			boton.setEffect(sombra);
		});
		
		this.setOnMouseExited(evento2 -> {
			boton.setFill(Color.TRANSPARENT);
			boton.setEffect(null);
			
		});
		
	}


	protected abstract Color getColorMouseEntered() ;


	public void habilitar(){
		this.setDisable(false);
		
	}
	
	public void deshabilitar(){
		this.setDisable(true);
		
	}
	
	public void setOnAction(EventHandler<MouseEvent> evento){
		this.setOnMouseClicked(evento);
	}
	
	protected void seleccionar(){
		 DropShadow sombra= new DropShadow( 1, Color.AQUA );
		 sombra.setWidth(anchoBoton / 2);
		 sombra.setHeight(altoBoton);
		 sombra.setSpread(0.6);
		 imagenFondo.setEffect(sombra);
	}
	
	public void lanzarSonidoError(){
		this.sonidoError.play();
	}
	public void pararSonidoError(){
		this.sonidoError.stop();
	}
	
}
