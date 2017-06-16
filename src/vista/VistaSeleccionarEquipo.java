package vista;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import modelo.juego.DragonBall;
import modelo.utilidades.Constantes;
import vista.controlador.VolverAlMenuEventHandler;

public class VistaSeleccionarEquipo extends VBox{
	
	private DragonBall juego;
	private Stage stage;
	private MenuPrincipal menu;
	public static final int ALTURA_EQUIPOS = 0;
	private LibreriaSonidos sonidos;
	
	public VistaSeleccionarEquipo(DragonBall juego, Stage stage, MenuPrincipal menu,LibreriaSonidos sonidos){
		this.sonidos = sonidos;
		this.juego = juego;
		this.stage = stage;
		this.menu = menu;
		
		Image imagen = new Image("file:src/vista/imagenes/FondoElegir.jpg");
		BackgroundImage imagenDeFondo = new BackgroundImage(imagen, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		this.setBackground(new Background(imagenDeFondo));
		
		Text title = new Text("Jugador I: Elegir equipo");
        
        title.setFont(Font.loadFont("file:src/vista/imagenes/Saiyan-Sans.ttf", 120));
        title.setFill(Color.YELLOW);
        title.setTextAlignment(TextAlignment.CENTER);
        
        this.getChildren().add(title);
		
		this.crearHbox();
		
		
	    
	}


	private void crearHbox() {
		HBox hbox = new HBox();
		hbox.setFillHeight(true);
		hbox.setAlignment(Pos.CENTER);
		
		this.crearSeleccionables(hbox);
		this.getChildren().add(hbox);

		
	}

	private void crearSeleccionables(HBox hbox) {
		VBox vboxGuerreros = this.crearSeleccionableEquipo(Constantes.GUERREROS, Constantes.ENEMIGOS, Color.GREEN);
		VBox vboxEnemigos = this.crearSeleccionableEquipo(Constantes.ENEMIGOS, Constantes.GUERREROS, Color.RED);
		
		hbox.getChildren().add(vboxGuerreros);
		hbox.getChildren().add(vboxEnemigos);
		
	}
	
	private VBox crearSeleccionableEquipo(String equipo, String enemigo,Color color){
		
		VBox vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);
		
		
		InputStream archivoImagen = null;
		try {
			archivoImagen = Files.newInputStream(Paths.get("src/vista/imagenes/" + equipo + ".png"));
		} catch (IOException e) {
		}
		
		Image imagen = new Image(archivoImagen);
		ImageView vistaImagen = new ImageView(imagen);
		try {
			archivoImagen.close();
		} catch (IOException e1) {
		}
		vistaImagen.setFitWidth(700);
		vistaImagen.setPreserveRatio(true);
		vistaImagen.setTranslateY(ALTURA_EQUIPOS);
		
		DropShadow sombra1= new DropShadow( 20, color);
		DropShadow sombra2= new DropShadow();
		vistaImagen.setOnMouseMoved(evento ->{
			vistaImagen.setEffect(sombra1);
		});
		vistaImagen.setOnMouseExited(evento ->{
			vistaImagen.setEffect(sombra2);
		});
		
		vistaImagen.setOnMouseClicked(evento -> {
			
			 
			
			juego.establecerEquipoJugador1(equipo);
			juego.establecerEquipoJugador2(enemigo);
			juego.iniciar();
			Boolean pantallaCompleta = this.comprobarPantallaCompleta();
			Scene scene = new Scene(new VistaJuego(juego, stage,this.sonidos));
			scene.setOnKeyPressed(new VolverAlMenuEventHandler(menu, stage, scene));
			stage.setScene(scene);
			stage.setFullScreen(pantallaCompleta);
			stage.show();
			
		});
		
		vbox.getChildren().add(vistaImagen);
		this.crearNombre(equipo, vbox);
		
		return vbox;
	}
	
	private Boolean comprobarPantallaCompleta() {
		return stage.isFullScreen();
	}


	private void crearNombre(String nombre, VBox vboxGuerreros) {

		Text nombreGuerreros = new Text(nombre);
		nombreGuerreros.setFont(Font.loadFont("file:src/vista/imagenes/Saiyan-Sans.ttf", 120));
		nombreGuerreros.setFill(Color.YELLOW);
		nombreGuerreros.setTextAlignment(TextAlignment.CENTER);
		vboxGuerreros.getChildren().add(nombreGuerreros);
		
	}

	
}
