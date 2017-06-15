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
	
	public VistaSeleccionarEquipo(DragonBall juego, Stage stage, MenuPrincipal menu){
		
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
	
	private void crearNombre(String nombre, VBox vboxGuerreros) {

		Text nombreGuerreros = new Text(nombre);
		nombreGuerreros.setFont(Font.loadFont("file:src/vista/imagenes/Saiyan-Sans.ttf", 120));
		nombreGuerreros.setFill(Color.YELLOW);
		nombreGuerreros.setTextAlignment(TextAlignment.CENTER);
		vboxGuerreros.getChildren().add(nombreGuerreros);
		
	}

	private void crearHbox() {
		HBox hbox = new HBox();
		hbox.setFillHeight(true);
		hbox.setAlignment(Pos.CENTER);
		
		this.crearSeleccionables(hbox);
		this.getChildren().add(hbox);

		
	}

	private void crearSeleccionables(HBox hbox) {
		VBox vboxGuerreros = new VBox();
		vboxGuerreros.setAlignment(Pos.CENTER);
		VBox vboxEnemigos = new VBox();
		vboxEnemigos.setAlignment(Pos.CENTER);
		
		InputStream archivoImagenGuerreros = null;
		try {
			archivoImagenGuerreros = Files.newInputStream(Paths.get("src/vista/imagenes/guerreros.png"));
		} catch (IOException e) {
		}
		
		Image imagenGuerreros = new Image(archivoImagenGuerreros);
		ImageView vistaImagenGuerreros = new ImageView(imagenGuerreros);
		try {
			archivoImagenGuerreros.close();
		} catch (IOException e1) {
		}
		vistaImagenGuerreros.setFitWidth(700);
		vistaImagenGuerreros.setPreserveRatio(true);
		vistaImagenGuerreros.setTranslateY(ALTURA_EQUIPOS);
		
		DropShadow dsGuerreros= new DropShadow( 20, Color.GREEN );
		DropShadow dsGuerreros2= new DropShadow();
		vistaImagenGuerreros.setOnMouseMoved(evento ->{
			vistaImagenGuerreros.setEffect(dsGuerreros);
		});
		vistaImagenGuerreros.setOnMouseExited(evento ->{
			vistaImagenGuerreros.setEffect(dsGuerreros2);
		});
		
		vistaImagenGuerreros.setOnMouseClicked(evento -> {
			
			 //el jugador 1 elige equipo GUERREROS
			
			juego.establecerEquipoJugador1(Constantes.GUERREROS);
			juego.establecerEquipoJugador2(Constantes.ENEMIGOS);
			juego.iniciar();
			Scene scene = new Scene(new VistaJuego(juego, stage));
			scene.setOnKeyPressed(new VolverAlMenuEventHandler(menu, stage, scene));
			stage.setScene(scene);
			stage.setFullScreen(true);
			stage.setFullScreenExitHint("");
			stage.setResizable(false);
			stage.show();
			
		});
		
		vboxGuerreros.getChildren().add(vistaImagenGuerreros);
		this.crearNombre(Constantes.GUERREROS, vboxGuerreros);
		
		InputStream archivoImagenEnemigos = null;
		try {
			archivoImagenEnemigos = Files.newInputStream(Paths.get("src/vista/imagenes/enemigos.png"));
		} catch (IOException e) {
		}
		Image imagenEnemigos = new Image(archivoImagenEnemigos);
		ImageView vistaImagenEnemigos = new ImageView(imagenEnemigos);
		try {
			archivoImagenEnemigos.close();
		} catch (IOException e) {
		}
		vistaImagenEnemigos.setFitWidth(700);
		vistaImagenEnemigos.setPreserveRatio(true);
		vistaImagenEnemigos.setTranslateY(ALTURA_EQUIPOS);
		
		DropShadow dsEnemigos= new DropShadow( 20, Color.RED );
		DropShadow dsEnemigos2= new DropShadow();
		vistaImagenEnemigos.setOnMouseMoved(evento ->{
			vistaImagenEnemigos.setEffect(dsEnemigos);
		});
		vistaImagenEnemigos.setOnMouseExited(evento ->{
			vistaImagenEnemigos.setEffect(dsEnemigos2);
		});
		
		vistaImagenEnemigos.setOnMouseClicked(evento -> {
			//el jugador 1 elige equipo ENEMIGOS
			juego.establecerEquipoJugador1(Constantes.ENEMIGOS);
			juego.establecerEquipoJugador2(Constantes.GUERREROS);
			juego.iniciar();
			Scene scene = new Scene(new VistaJuego(juego, stage));
			scene.setOnKeyPressed(new VolverAlMenuEventHandler(menu, stage, scene));
			stage.setScene(scene);
			stage.setFullScreen(true);
			stage.setFullScreenExitHint("");
			stage.setResizable(false);
			stage.show();
		});
		vboxEnemigos.getChildren().add(vistaImagenEnemigos);
		this.crearNombre(Constantes.ENEMIGOS, vboxEnemigos);
		
		hbox.getChildren().add(vboxGuerreros);
		hbox.getChildren().add(vboxEnemigos);
		
	}

	
}
