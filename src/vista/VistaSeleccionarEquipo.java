package vista;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import controlador.ElegirEquipoPrimerJugadorEventHandler;
import javafx.geometry.Pos;
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

public class VistaSeleccionarEquipo extends VBox{
	
	private DragonBall juego;
	private Stage stage;
	private MenuPrincipal menu;
	private Text title;
	private LibreriaSonidos sonidos;
	
	public VistaSeleccionarEquipo(DragonBall juego, Stage stage, MenuPrincipal menu,LibreriaSonidos sonidos){
		this.sonidos = sonidos;
		this.juego = juego;
		this.stage = stage;
		this.menu = menu;
		
		Image imagen = new Image("file:src/vista/imagenes/FondoElegir.jpg");
		BackgroundImage imagenDeFondo = new BackgroundImage(imagen, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		this.setBackground(new Background(imagenDeFondo));
		
		title = new Text("Jugador I: Elegir equipo");
        
        title.setFont(Font.loadFont("file:src/vista/imagenes/Saiyan-Sans.ttf", 100));
        title.setFill(Color.YELLOW);
        title.setTextAlignment(TextAlignment.CENTER);
        
        this.getChildren().add(title);
		
		this.crearHbox();
		
		this.setAlignment(Pos.CENTER);
	    
	}


	private void crearHbox() {
		HBox hbox = new HBox();
		hbox.setFillHeight(true);
		hbox.setAlignment(Pos.CENTER);
		
		this.crearSeleccionables(hbox);
		this.getChildren().add(hbox);

		
	}

	private void crearSeleccionables(HBox hbox) {
		VBox vboxGuerreros = this.crearSeleccionableEquipo(Constantes.GUERREROS, Constantes.ENEMIGOS, Color.LIME);
		VBox vboxEnemigos = this.crearSeleccionableEquipo(Constantes.ENEMIGOS, Constantes.GUERREROS, Color.MAGENTA);
		this.agregarEventHandler(vboxGuerreros, vboxEnemigos, Constantes.GUERREROS, Constantes.ENEMIGOS);
		this.agregarEventHandler( vboxEnemigos, vboxGuerreros, Constantes.ENEMIGOS, Constantes.GUERREROS);
		hbox.getChildren().add(vboxGuerreros);
		hbox.getChildren().add(vboxEnemigos);
		
	}
	
	private void agregarEventHandler(VBox vboxActual, VBox vboxContrario, String nombrePropio, String nombreContrario) {
		ElegirEquipoPrimerJugadorEventHandler eventHandler = new ElegirEquipoPrimerJugadorEventHandler(juego, nombrePropio, nombreContrario, 
				vboxActual, vboxContrario, stage, sonidos, menu, title);
		vboxActual.setOnMouseClicked(eventHandler);
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
		vistaImagen.setFitHeight(ConstantesPantalla.alturaImagenesEquipos);
		vistaImagen.setPreserveRatio(true);
		
		DropShadow sombra1= new DropShadow( 20, color);
		DropShadow sombra2= new DropShadow();
		vistaImagen.setOnMouseEntered(evento ->{
			vistaImagen.setEffect(sombra1);
			sonidos.reproducirSonidoBotonMenu();
		});
		
		vistaImagen.setOnMouseExited(evento ->{
			vistaImagen.setEffect(sombra2);
		});
		
		vistaImagen.setOnMouseClicked(evento ->{
			sonidos.reproducirSonidoSeleccion();
		});
		
		vbox.getChildren().add(vistaImagen);
		this.crearNombre(equipo, vbox);
		
		return vbox;
	}
	

	private void crearNombre(String nombre, VBox vboxGuerreros) {

		Text textoNombre = new Text(nombre);
		textoNombre.setFont(Font.loadFont("file:src/vista/imagenes/Saiyan-Sans.ttf", 80));
		textoNombre.setFill(Color.YELLOW);
		textoNombre.setTextAlignment(TextAlignment.CENTER);
		vboxGuerreros.getChildren().add(textoNombre);
		
	}

	
}
