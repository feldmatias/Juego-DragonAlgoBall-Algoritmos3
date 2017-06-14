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
			
			VBox menu1 = new VBox(10);
			VBox menu2 = new VBox(10);
			
			menu1.setTranslateX(100);
			menu1.setTranslateY(200);

			menu2.setTranslateX(100);
			menu2.setTranslateY(200);
			
			final int compensacion = 400;
			
			menu2.setTranslateX(compensacion);
			
			DragonBall juego = new DragonBall();
			//HAcer logica de seleccion de equipos
			juego.establecerEquipoJugador1(Constantes.GUERREROS);
			juego.establecerEquipoJugador2(Constantes.ENEMIGOS);
			juego.iniciar();
			
			BotonMenu btnEmpezar = new BotonMenu ("NUEVA PARTIDA");
			btnEmpezar.setOnMouseClicked( evento1 -> {
				
	
				
				stage.hide();
				stage.setFullScreen(true);
				Scene scene = new Scene(new VistaJuego(juego));
				stage.setScene(scene);
				stage.show();
				
			});
	
			
			BotonMenu btnOpciones = new BotonMenu("OPCIONES");
			btnOpciones.setOnMouseClicked(evento1 -> {
				this.getChildren().add(menu2);
				TranslateTransition transicionTraslado1 = new TranslateTransition(Duration.seconds(0.25),menu1);
				transicionTraslado1.setToX( menu1.getTranslateX() - compensacion );
				
				TranslateTransition transicionTraslado2 = new TranslateTransition(Duration.seconds(0.5),menu2);
				transicionTraslado2.setToX( menu1.getTranslateX() );
				
				transicionTraslado1.play();
				transicionTraslado2.play();
				
				transicionTraslado1.setOnFinished( evento2 -> {
					this.getChildren().remove(menu1);
				});
				
			});
			
			BotonMenu btnAtras =new BotonMenu("ATRAS");
			btnAtras.setOnMouseClicked( evento -> {
				this.getChildren().add(menu1);
				
				TranslateTransition transicionTraslado = new TranslateTransition(Duration.seconds(0.25), menu2);
				transicionTraslado.setToX( menu2.getTranslateX() + compensacion );
				
				TranslateTransition transicionTraslado2 = new TranslateTransition(Duration.seconds(0.5), menu1);
				transicionTraslado2.setToX( menu2.getTranslateX() );
				
				transicionTraslado.play();
				transicionTraslado2.play();
				
				transicionTraslado.setOnFinished( evento2 -> {
					this.getChildren().remove( menu2 );
				});
				
			});
			
			
			BotonMenu btnSonido = new BotonMenu("SONIDO");
			
			
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
			
			
			menu1.getChildren().addAll(btnEmpezar , btnOpciones, btnSalir);
			menu2.getChildren().addAll(btnAtras, btnSonido);
			
			
			this.getChildren().addAll(menu1);
			
			
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
