package vista;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;


import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;

public class BotonAccion extends StackPane{

	private VBox boton;
	private LibreriaSonidos sonidos;
	public static int anchoBotonAccion = (int) (Screen.getPrimary().getVisualBounds().getWidth()/8); //cambio  200
	public static int altoBotonAccion = (int) (Screen.getPrimary().getVisualBounds().getHeight()/8.5); //cambio  120
	public static double tamFuente = Screen.getPrimary().getVisualBounds().getHeight() / 42 ;
	
	public BotonAccion(String nombre, EventHandler<MouseEvent> eventHandler, LibreriaSonidos sonidos){
		this.sonidos = sonidos;
		this.crearBoton(nombre);
		this.crearBotonTransparente();
		this.setOnMouseClicked(eventHandler);
		this.setAlignment(Pos.CENTER);
		
		
		DropShadow sombra = new DropShadow(50,Color.WHITE);
		sombra.setInput(new Glow());
		
		
	}

	private void crearBotonTransparente() {
		Rectangle botonTransparente = new Rectangle(anchoBotonAccion, altoBotonAccion);
		botonTransparente.setFill(Color.TRANSPARENT);
		this.getChildren().add(botonTransparente);
		
		DropShadow sombra = new DropShadow(50,Color.BEIGE);
		sombra.setInput(new Glow());
		this.setOnMouseEntered(evento -> {
			botonTransparente.setFill(Color.WHITE);
			botonTransparente.setOpacity(0.3);
			botonTransparente.setEffect(sombra);
			sonidos.reproducirSonidoBotonMenu();
		});
		
		this.setOnMouseExited(evento2 -> {
			botonTransparente.setFill(Color.TRANSPARENT);
			botonTransparente.setEffect(null);
			
		});
	}

	private void crearBoton(String nombre) {
		this.boton = new VBox();
		
		this.agregarTexto(nombre);
		this.crearImagenBoton(nombre);
		
		this.getChildren().add(boton);
		boton.setAlignment(Pos.CENTER);
	}

	private void agregarTexto(String nombre) {
		Label texto = new Label(nombre);
		texto.setFont(Font.font("Arial", FontWeight.BOLD, tamFuente));
		boton.getChildren().add(texto);
	}
	
	private void crearImagenBoton(String nombre){
		InputStream archivoImagen = null;
		try {
			archivoImagen = Files.newInputStream(Paths.get("src/vista/imagenes/botonesAcciones/" + nombre + ".jpg"));
			Image imagen = new Image(archivoImagen);
			ImageView vistaImagen = new ImageView(imagen);
			archivoImagen.close();
			boton.getChildren().add(vistaImagen);
		} catch (IOException e) {
		}
	}
	
}
