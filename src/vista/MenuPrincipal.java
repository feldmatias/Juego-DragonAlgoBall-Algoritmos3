package vista;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import modelo.juego.DragonBall;
import vista.controlador.TransicionMenuEventHandler;

public class MenuPrincipal extends StackPane{
	
		private Scene vistaJuego = null;
		private BotonMenu btnContinuar;
		private Stage stage;
		private LibreriaSonidos sonidos;
		
		public MenuPrincipal(Stage stage,LibreriaSonidos sonidos) {
			this.stage = stage;
			this.sonidos = sonidos;
			stage.setFullScreen(true);

			
			InputStream entradaImagen;
			try {
				entradaImagen = Files.newInputStream(Paths.get("src/vista/imagenes/fondo menu esferas.jpg"));
				Image imagen = new Image(entradaImagen);
				entradaImagen.close();
				ImageView vistaImagen = new ImageView(imagen);
				this.getChildren().add(vistaImagen);
			} catch (IOException e) {
			}
			
			
			Submenu menuPrincipal = new Submenu();
			Submenu menuOpciones = new Submenu();
			Submenu menuSonido = new Submenu();
			Submenu menuPantalla = new Submenu();
			

			HBox opcionesMusica = new HBox(10);
			HBox opcionesEfectosSonido = new HBox(10);

			
			final double posSubmenu1 = 400;
			final double posSubmenu2 = 800;
			
			menuOpciones.setTranslateX(posSubmenu1);
			menuSonido.setTranslateX( posSubmenu2 );
			menuPantalla.setTranslateX( posSubmenu2 );
			
			
			btnContinuar = new BotonMenu ("CONTINUAR PARTIDA", sonidos);
			btnContinuar.deshabilitar();
				
			
			BotonMenu btnEmpezar = new BotonMenu ("NUEVA PARTIDA", sonidos);
			btnEmpezar.setOnMouseClicked( evento1 -> {
				DragonBall juego = new DragonBall();
	
				btnContinuar.habilitar();
				Boolean pantallaCompleta = this.comprobarPantallaCompleta();
				Scene scene = new Scene(new VistaSeleccionarEquipo(juego, stage, this,this.sonidos));
				stage.setScene(scene);
				stage.setFullScreen(pantallaCompleta);
				stage.show();
				
			});
	
			
			BotonMenu btnOpciones = new BotonMenu("OPCIONES", sonidos);
			TransicionMenuEventHandler eventoTransicion = new TransicionMenuEventHandler(this,menuPrincipal,menuOpciones,posSubmenu1);
			btnOpciones.setOnMouseClicked(eventoTransicion);
			
			
			BotonMenu btnAtrasPrincipal =new BotonMenu("ATRAS", sonidos);
			TransicionMenuEventHandler eventoTransicion2 = new TransicionMenuEventHandler(this,menuOpciones,menuPrincipal,-posSubmenu1);
			btnAtrasPrincipal.setOnMouseClicked(eventoTransicion2);
			
			
			BotonMenu btnSonido = new BotonMenu("SONIDO", sonidos);
			TransicionMenuEventHandler eventoTransicion3 = new TransicionMenuEventHandler(this, menuOpciones, menuSonido, posSubmenu2);
			btnSonido.setOnMouseClicked(eventoTransicion3);
			
			
			BotonMenu btnAtrasSonido = new BotonMenu("ATRAS", sonidos);
			TransicionMenuEventHandler eventoTransicion4 = new TransicionMenuEventHandler(this, menuSonido, menuOpciones, -posSubmenu2);
			btnAtrasSonido.setOnMouseClicked(eventoTransicion4);
			
						
			BotonMenu btnPantalla = new BotonMenu("PANTALLA", sonidos);
			TransicionMenuEventHandler eventoTransicion5 = new TransicionMenuEventHandler(this,menuOpciones,menuPantalla, posSubmenu1);
			btnPantalla.setOnMouseClicked(eventoTransicion5);
			
			
			
			BotonMenu btnAtrasPantalla = new BotonMenu("ATRAS", sonidos);
			TransicionMenuEventHandler eventoTransicion6 = new TransicionMenuEventHandler(this,menuPantalla,menuOpciones,-posSubmenu2);
			btnAtrasPantalla.setOnMouseClicked(eventoTransicion6);
			
			
			BotonMenu btnPantallaCompletaOn = new BotonMenu("PANTALLA COMPLETA", sonidos);
			BotonMenu btnPantallaCompletaOff = new BotonMenu("MODO VENTANA", sonidos);
			
			btnPantallaCompletaOn.setOnMouseClicked( evento-> {
				stage.setFullScreen(true);
				stage.setFullScreenExitHint("");
				btnPantallaCompletaOff.habilitar();
				btnPantallaCompletaOn.deshabilitar();
			});
			
			btnPantallaCompletaOn.deshabilitar();

			
			btnPantallaCompletaOff.setOnMouseClicked( evento-> {
				stage.setFullScreen(false);
				btnPantallaCompletaOff.deshabilitar();
				btnPantallaCompletaOn.habilitar();
			});

			
			BotonMenu btnMusicaOff = new BotonMenu("MUSICA OFF", sonidos);
			btnMusicaOff.setOnMouseClicked(evento -> {
				sonidos.mutearMusica();
			});
			
			BotonMenu btnMusicaOn = new BotonMenu("MUSICA ON", sonidos);
			btnMusicaOn.setOnMouseClicked(evento -> {
				sonidos.desmutearMusica();
			});
			
			BotonMenu btnEfectosOn = new BotonMenu("EFECTOS DE SONIDO ON", sonidos);
			btnEfectosOn.setOnMouseClicked(evento -> {
				sonidos.desmutearEfectosSonido();
			});
			
			BotonMenu btnEfectosOff = new BotonMenu("EFECTOS DE SONIDO OFF", sonidos);
			btnEfectosOff.setOnMouseClicked(evento-> {
				sonidos.mutearEfectosSonido();
			});
			
			
			
			BotonMenu btnSalir = new BotonMenu( "SALIR" , sonidos);
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
			
			
			menuPrincipal.getChildren().addAll(btnContinuar, btnEmpezar , btnOpciones, btnSalir);
			menuOpciones.getChildren().addAll(btnAtrasPrincipal, btnSonido, btnPantalla);
			opcionesMusica.getChildren().addAll(btnMusicaOn,btnMusicaOff);
			opcionesEfectosSonido.getChildren().addAll(btnEfectosOn,btnEfectosOff);
			menuSonido.getChildren().addAll(btnAtrasSonido,opcionesMusica,opcionesEfectosSonido);
			menuPantalla.getChildren().addAll(btnAtrasPantalla,btnPantallaCompletaOn,btnPantallaCompletaOff);
			
			
			this.getChildren().addAll(menuPrincipal);
			
			
		}
		
		private Boolean comprobarPantallaCompleta() {
			return stage.isFullScreen();
		}

		public void habilitarContinuar(Scene vista){
			this.vistaJuego = vista;
			btnContinuar.setDisable(false);
			btnContinuar.setOnMouseClicked( evento1 -> {
				
				Boolean pantallaCompleta = this.comprobarPantallaCompleta();
				stage.setScene(vistaJuego);
				stage.setFullScreen(pantallaCompleta);
				stage.show();
				
			});
			
		}

		
		
}
