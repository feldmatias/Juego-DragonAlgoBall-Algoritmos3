package vista;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import modelo.juego.DragonBall;
import modelo.utilidades.Constantes;


public class MenuJuego extends Application {

	private MenuPrincipal menuJuego;
	private Stage stage;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.stage = primaryStage;
		
		Pane root = new Pane();
		root.setPrefSize(800, 600);
		
		InputStream entradaImagen = Files.newInputStream(Paths.get("src/vista/imagenes/fondo menu esferas.jpg"));
		
		Image imagen = new Image(entradaImagen);
		entradaImagen.close();

		
		Media mediaCancionFondo = new Media(new File("src/vista/sonidos/Dragon Ball opening.mp3").toURI().toString());
		MediaPlayer cancionFondo = new MediaPlayer(mediaCancionFondo);
		cancionFondo.setAutoPlay(true);
		cancionFondo.setVolume(0.1);
//		cancionFondo.setMute(true);
		
		
		ImageView vistaImagen = new ImageView(imagen);
		
		this.menuJuego = new MenuPrincipal();
		
		root.getChildren().addAll(vistaImagen, menuJuego);
		
		Scene scene = new Scene(root);
		
		stage.setScene(scene);
		stage.setFullScreen(true);
		stage.setResizable(false);
		stage.setMinWidth(1200);
		stage.setMinHeight(700);
		stage.setTitle("DRAGON ALGO BALL");
		stage.show();
		
		
	}
	
	private class MenuPrincipal extends Parent {
		
		public MenuPrincipal(){
			
			VBox menuPrincipal = new VBox(10);
			VBox menuOpciones = new VBox(10);
			HBox opcionesMusica = new HBox(10);
			HBox opcionesEfectosSonido = new HBox(10);
			
			menuPrincipal.setTranslateX(100);
			menuPrincipal.setTranslateY(200);

			menuOpciones.setTranslateX(100);
			menuOpciones.setTranslateY(200);
			
			opcionesMusica.setTranslateX(200);
			opcionesEfectosSonido.setTranslateX(200);
			
			final int compensacion = 400;
			
			menuOpciones.setTranslateX(compensacion);
			opcionesMusica.setTranslateY(menuOpciones.getTranslateY() + compensacion);
			opcionesEfectosSonido.setTranslateY(menuOpciones.getTranslateY() +compensacion  + 50);
			
			DragonBall juego = new DragonBall();
			//HAcer logica de seleccion de equipos
			/*juego.establecerEquipoJugador1(Constantes.GUERREROS);
			juego.establecerEquipoJugador2(Constantes.ENEMIGOS);
			juego.iniciar();*/
			
			BotonMenu btnEmpezar = new BotonMenu ("NUEVA PARTIDA");
			btnEmpezar.setOnMouseClicked( evento1 -> {
				
	
				
				stage.hide();
				stage.setFullScreen(true);
				//Scene scene = new Scene(new VistaJuego(juego));
				Scene scene = new Scene(new VistaSeleccionarEquipo(juego, stage));
				stage.setScene(scene);
				stage.show();
				
			});
	
			
			BotonMenu btnOpciones = new BotonMenu("OPCIONES");
			btnOpciones.setOnMouseClicked(evento1 -> {
				this.getChildren().addAll(menuOpciones);
				TranslateTransition transicionTraslado1 = new TranslateTransition(Duration.seconds(0.25),menuPrincipal);
				transicionTraslado1.setToX( menuPrincipal.getTranslateX() - compensacion );
				
				TranslateTransition transicionTraslado2 = new TranslateTransition(Duration.seconds(0.5),menuOpciones);
				transicionTraslado2.setToX( menuPrincipal.getTranslateX() );
				
				transicionTraslado1.play();
				transicionTraslado2.play();
				
				transicionTraslado1.setOnFinished( evento2 -> {
					this.getChildren().remove(menuPrincipal);
				});
				
			});
			
			BotonMenu btnAtras =new BotonMenu("ATRAS");
			btnAtras.setOnMouseClicked( evento -> {
				this.getChildren().add(menuPrincipal);
				
				TranslateTransition transicionOpciones = new TranslateTransition(Duration.seconds(0.25), menuOpciones);
				transicionOpciones.setToX( menuOpciones.getTranslateX() + compensacion );
				
				TranslateTransition transicionMusica = new TranslateTransition(Duration.seconds(0.1),opcionesMusica);
				transicionMusica.setToY(menuOpciones.getTranslateY() + compensacion);
				
				TranslateTransition transicionEfectos = new TranslateTransition(Duration.seconds(0.1),opcionesEfectosSonido);
				transicionEfectos.setToY(menuOpciones.getTranslateY() + compensacion + 50);
				
				TranslateTransition transicionMenuPrincipal = new TranslateTransition(Duration.seconds(0.5), menuPrincipal);
				transicionMenuPrincipal.setToX( menuOpciones.getTranslateX() );
				
				transicionOpciones.play();
				transicionMusica.play();
				transicionEfectos.play();
				transicionMenuPrincipal.play();
				
				
				transicionOpciones.setOnFinished( evento2 -> {
					this.getChildren().removeAll( menuOpciones, opcionesMusica, opcionesEfectosSonido );
				
				});
				
			});
			
			
			BotonMenu btnSonido = new BotonMenu("SONIDO");
			btnSonido.setOnMouseClicked( evento-> {
				this.getChildren().addAll(opcionesMusica,opcionesEfectosSonido);
				TranslateTransition transicionTraslado = new TranslateTransition(Duration.seconds(0.5),opcionesMusica);
				transicionTraslado.setToY(	menuOpciones.getTranslateY() + 200);		
				
				TranslateTransition transicionTraslado2 = new TranslateTransition(Duration.seconds(0.5),opcionesEfectosSonido);
				transicionTraslado2.setToY( menuOpciones.getTranslateY() + 250 );
				
				transicionTraslado.play();
				transicionTraslado2.play();
				
			});
			
			
			BotonMenu btnMusicaOff = new BotonMenu("MUSICA OFF");
			
			BotonMenu btnMusicaOn = new BotonMenu("MUSICA ON");
			
			BotonMenu btnEfectosOn = new BotonMenu("EFECTOS DE SONIDO ON");
			
			BotonMenu btnEfectosOff = new BotonMenu("EFECTOS DE SONIDO OFF");
			
			
			
			BotonMenu btnSalir = new BotonMenu( "SALIR" );
			btnSalir.setOnMouseClicked( evento -> {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Confirmación de salida");
				alert.setHeaderText("Se ha seleccionado la opción de SALIR de la aplicación");
				alert.setContentText("¿Está seguro que desea salir?");
				alert.showAndWait();
				if (alert.getResult() == ButtonType.OK){
					System.exit(0);
				}				
				
			});
			
			
			menuPrincipal.getChildren().addAll(btnEmpezar , btnOpciones, btnSalir);
			menuOpciones.getChildren().addAll(btnAtras, btnSonido);
			opcionesMusica.getChildren().addAll(btnMusicaOn,btnMusicaOff);
			opcionesEfectosSonido.getChildren().addAll(btnEfectosOn,btnEfectosOff);
			
			
			this.getChildren().addAll(menuPrincipal);
			
			
		}
		
		
	}

	
	
	
//	private static class BotonMenu extends StackPane {
//		private Text texto;
//		
//		public BotonMenu(String nombre){
//			texto = new Text(nombre);
//			texto.setFont(Font.font(20));
//			texto.setFill(Color.WHITE);
//			
//			Rectangle fondo = new Rectangle(250,30);
//			fondo.setOpacity(0.6);
//			fondo.setFill(Color.ORANGE);
//			
//			GaussianBlur desenfoque = new GaussianBlur(3.5);
//			fondo.setEffect(desenfoque);
//			
//			this.setAlignment(Pos.CENTER_LEFT);
//			this.setRotate(-0.5);
//			this.getChildren().addAll(fondo,texto);
//			
//			this.setOnMouseEntered(evento -> {
//				fondo.setTranslateX(10);
//				texto.setTranslateX(10);
//				fondo.setFill(Color.WHITE);
//				texto.setFill(Color.ORANGE);
//			});
//			
//			this.setOnMouseExited(event ->{
//				fondo.setTranslateX(0);
//				texto.setTranslateX(0);
//				fondo.setFill(Color.ORANGE);
//				texto.setFill(Color.WHITE);
//			});
//			
//			
//			DropShadow sombra = new DropShadow(50,Color.WHITE);
//			sombra.setInput(new Glow());
//			
//			this.setOnMousePressed(evento -> setEffect(sombra));
//			this.setOnMouseReleased(evento -> setEffect(null)); //remuevo el efecto
//			
//		}
		
		
		
//	}
	
	public static void main(String [] args){
		launch(args);
	}

}
