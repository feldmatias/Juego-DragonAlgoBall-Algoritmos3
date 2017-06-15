package vista;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import modelo.juego.DragonBall;

public class MenuPrincipal extends StackPane{
	
		
		public MenuPrincipal(Stage stage) {
			
			InputStream entradaImagen;
			try {
				entradaImagen = Files.newInputStream(Paths.get("src/vista/imagenes/fondo menu esferas.jpg"));
				Image imagen = new Image(entradaImagen);
				entradaImagen.close();
				ImageView vistaImagen = new ImageView(imagen);
				this.getChildren().add(vistaImagen);
			} catch (IOException e) {
			}
			
			
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
			
			
			BotonMenu btnEmpezar = new BotonMenu ("NUEVA PARTIDA");
			btnEmpezar.setOnMouseClicked( evento1 -> {
				DragonBall juego = new DragonBall();
	
				
				Scene scene = new Scene(new VistaSeleccionarEquipo(juego, stage));
				stage.setScene(scene);
				stage.setFullScreen(true);
				stage.setFullScreenExitHint("");
				stage.setResizable(false);
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
