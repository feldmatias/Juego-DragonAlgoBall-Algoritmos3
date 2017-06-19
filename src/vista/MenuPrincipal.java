package vista;


import controlador.TransicionMenuEventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import modelo.juego.DragonBall;
import vista.botones.BotonMenu;

public class MenuPrincipal extends StackPane{
	
		private Scene vistaJuego;
		private BotonMenu btnContinuar;
		private Stage stage;
		private LibreriaSonidos sonidos;
		private Submenu menuPrincipal;
		private Submenu menuOpciones;
		private Submenu menuSonido;
		private Submenu menuPantalla;
		private Submenu menuAcerca;
		private Submenu menuInstrucciones;
		
		private final double posSubmenu1 = 400;
		private final double posSubmenu2 = 800;
		private final double posSubmenu3 = 1000;
		
		public MenuPrincipal(Stage stage,LibreriaSonidos sonidos) {
			this.stage = stage;
			this.sonidos = sonidos;

			Image imagen = new Image("file:src/vista/imagenes/fondo menu.jpg");
			BackgroundSize size = new BackgroundSize(ConstantesPantalla.altoImagenFondo,ConstantesPantalla.anchoImagenFondo, false,false,true,true);
			BackgroundImage imagenDeFondo = new BackgroundImage(imagen, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,size);
			this.setBackground(new Background(imagenDeFondo));

			Text title = new Text("Dragon AlgoBall");
	        title.setFont(Font.loadFont("file:src/vista/imagenes/Saiyan-Sans.ttf", ConstantesPantalla.tamFuenteTituloMenuPrincipal));
	        title.setFill(Color.RED);
	        title.setStroke(Color.YELLOW);
	        title.setStrokeWidth(3);
	        title.setTextAlignment(TextAlignment.CENTER);
	        this.setAlignment(Pos.TOP_CENTER);
			
			menuPrincipal = new Submenu();
			menuOpciones = new Submenu();
			menuSonido = new Submenu();
			menuPantalla = new Submenu();
			menuAcerca = new Submenu();
			menuInstrucciones = new Submenu();
			
			this.crearMenuPrincipal();
			this.crearMenuOpciones();
			this.crearMenuSonido();
			this.crearMenuPantalla();
			this.crearMenuAcerca();
			this.crearMenuInstrucciones();
			
			menuOpciones.setTranslateX(posSubmenu1);
			menuSonido.setTranslateX( posSubmenu2 );
			menuPantalla.setTranslateX( posSubmenu2 );

			this.getChildren().addAll(title, menuPrincipal);
			
			
		}

		private void crearMenuPrincipal() {
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
			
			BotonMenu btnSalir = new BotonMenu( "SALIR" , sonidos);
			btnSalir.setOnMouseClicked( evento -> {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Confirmacion de salida");
				alert.setHeaderText("Se ha seleccionado la opcion de SALIR de la aplicacion");
				alert.setContentText("Esta seguro que desea salir?");
				alert.initOwner(stage);
				alert.showAndWait();
				if (alert.getResult() == ButtonType.OK){
					System.exit(0);
				}				
				
			});
			
			menuPrincipal.getChildren().addAll(btnContinuar, btnEmpezar , btnOpciones, btnSalir);
			
		}
		
		private void crearMenuOpciones() {
			BotonMenu btnAtrasPrincipal =new BotonMenu("ATRAS", sonidos);
			TransicionMenuEventHandler eventoTransicion = new TransicionMenuEventHandler(this,menuOpciones, menuPrincipal,-posSubmenu1);
			btnAtrasPrincipal.setOnMouseClicked(eventoTransicion);
			
			
			BotonMenu btnSonido = new BotonMenu("SONIDO", sonidos);
			TransicionMenuEventHandler eventoTransicion2 = new TransicionMenuEventHandler(this, menuOpciones, menuSonido, posSubmenu2);
			btnSonido.setOnMouseClicked(eventoTransicion2);
			
			BotonMenu btnPantalla = new BotonMenu("PANTALLA", sonidos);
			TransicionMenuEventHandler eventoTransicion3 = new TransicionMenuEventHandler(this,menuOpciones, menuPantalla, posSubmenu1);
			btnPantalla.setOnMouseClicked(eventoTransicion3);
			
			BotonMenu btnInstrucciones = new BotonMenu("INSTRUCCIONES", sonidos);
			TransicionMenuEventHandler eventoTransicion4 = new TransicionMenuEventHandler(this,menuOpciones, menuInstrucciones, posSubmenu3);
			btnInstrucciones.setOnMouseClicked(eventoTransicion4);
			
			BotonMenu btnAcercaDe = new BotonMenu("ACERCA DE", sonidos);
			TransicionMenuEventHandler eventoTransicion5 = new TransicionMenuEventHandler(this,menuOpciones, menuAcerca, posSubmenu3);
			btnAcercaDe.setOnMouseClicked(eventoTransicion5);
			
			menuOpciones.getChildren().addAll(btnSonido, btnPantalla, btnInstrucciones, btnAcercaDe, btnAtrasPrincipal);
			
		}
		
		private void crearMenuSonido() {
			HBox opcionesMusica = new HBox(10);
			HBox opcionesEfectosSonido = new HBox(10);
			
			BotonMenu btnAtrasSonido = new BotonMenu("ATRAS", sonidos);
			TransicionMenuEventHandler eventoTransicion = new TransicionMenuEventHandler(this, menuSonido, menuOpciones, -posSubmenu2);
			btnAtrasSonido.setOnMouseClicked(eventoTransicion);
			
			BotonMenu btnMusicaOff = new BotonMenu("MUSICA OFF", sonidos);
			BotonMenu btnMusicaOn = new BotonMenu("MUSICA ON", sonidos);

			if (sonidos.estaReproduciendoMusicaFondo()){
				btnMusicaOn.deshabilitar();
			}else{
				btnMusicaOff.deshabilitar();
			}
			
			btnMusicaOff.setOnMouseClicked(evento -> {
				sonidos.mutearMusica();
				btnMusicaOff.deshabilitar();
				btnMusicaOn.habilitar();
			});
			
			btnMusicaOn.setOnMouseClicked(evento -> {
				sonidos.desmutearMusica();
				btnMusicaOn.deshabilitar();
				btnMusicaOff.habilitar();
			});
			
			
			BotonMenu btnEfectosOn = new BotonMenu("EFECTOS DE SONIDO ON", sonidos);
			BotonMenu btnEfectosOff = new BotonMenu("EFECTOS DE SONIDO OFF", sonidos);

			if (sonidos.estaReproduciendoSonidos()){
				btnEfectosOn.deshabilitar();
			}else{
				btnEfectosOff.deshabilitar();
			}
			
			btnEfectosOn.setOnMouseClicked(evento -> {
				sonidos.desmutearEfectosSonido();
				btnEfectosOn.deshabilitar();
				btnEfectosOff.habilitar();
			});
			
			btnEfectosOff.setOnMouseClicked(evento-> {
				sonidos.mutearEfectosSonido();
				btnEfectosOff.deshabilitar();
				btnEfectosOn.habilitar();
			});
			
			opcionesMusica.getChildren().addAll(btnMusicaOn,btnMusicaOff);
			opcionesEfectosSonido.getChildren().addAll(btnEfectosOn,btnEfectosOff);
			menuSonido.getChildren().addAll(opcionesMusica,opcionesEfectosSonido, btnAtrasSonido);
			
		}
		
		private void crearMenuPantalla() {
			BotonMenu btnAtrasPantalla = new BotonMenu("ATRAS", sonidos);
			TransicionMenuEventHandler eventoTransicion = new TransicionMenuEventHandler(this,menuPantalla,menuOpciones,-posSubmenu2);
			btnAtrasPantalla.setOnMouseClicked(eventoTransicion);
			
			
			BotonMenu btnPantallaCompletaOn = new BotonMenu("PANTALLA COMPLETA", sonidos);
			BotonMenu btnPantallaCompletaOff = new BotonMenu("MODO VENTANA", sonidos);
			
			if (this.comprobarPantallaCompleta()){
				btnPantallaCompletaOn.deshabilitar();
			}else{
				btnPantallaCompletaOff.deshabilitar();
			}
			
			btnPantallaCompletaOn.setOnMouseClicked( evento-> {
				stage.setFullScreen(true);
				stage.setFullScreenExitHint("");
				btnPantallaCompletaOff.habilitar();
				btnPantallaCompletaOn.deshabilitar();
			});
			

			
			btnPantallaCompletaOff.setOnMouseClicked( evento-> {
				stage.setFullScreen(false);
				btnPantallaCompletaOff.deshabilitar();
				btnPantallaCompletaOn.habilitar();
			});
			
			menuPantalla.getChildren().addAll(btnPantallaCompletaOn,btnPantallaCompletaOff, btnAtrasPantalla);
		}
		
		private void crearMenuAcerca() {
			VBox infoBox = new VBox(0);
			
			BotonMenu btnAtrasAcerca = new BotonMenu("ATRAS", sonidos);
			TransicionMenuEventHandler eventoTransicion = new TransicionMenuEventHandler(this,menuAcerca,menuOpciones,posSubmenu3);
			btnAtrasAcerca.setOnMouseClicked(eventoTransicion);
			btnAtrasAcerca.setAlignment(Pos.CENTER);
						
			Text titulo = this.crearTextosAcercaDeConFormato("Algoritmos y programacion III", Color.DARKCYAN, Color.WHITE, 80);

			String nombreAlumnos = "FELD Matias \nLOBOSCO Barbara \nMATLES Karina \nPELOZO Emanuel\n" ;
			Text alumnos = this.crearTextosAcercaDeConFormato(nombreAlumnos, Color.DARKRED, Color.WHITE, 60);

			Text profesor = this.crearTextosAcercaDeConFormato("Profesor: CARLOS FONTELA",Color.CORAL, Color.DARKRED, 70);
			
			Text fecha = new Text("Primer Cuatrimestre 2017");
			fecha.setFont(Font.font("Calibri", FontWeight.BOLD, 35));
			fecha.setFill(Color.SEAGREEN);
			fecha.setStroke(Color.BLACK);
			fecha.setStrokeWidth(1);
			
			infoBox.getChildren().addAll(titulo, alumnos, profesor, fecha, btnAtrasAcerca);
			infoBox.setAlignment(Pos.CENTER);
			menuAcerca.getChildren().add(infoBox);
			
		}
		

		private Text crearTextosAcercaDeConFormato(String textoDeseado,Color colorLetra,Color colorBorde ,int tamFuente){
			

			Text texto = new Text(textoDeseado);
			texto.setFont(Font.loadFont("file:src/vista/imagenes/Saiyan-Sans.ttf", tamFuente));
			texto.setFill(colorLetra);
			texto.setStroke(colorBorde);
			texto.setStrokeWidth(3);
			return texto;
			
		}
		

		private void crearMenuInstrucciones() {
			VBox instrucciones = new VBox();
			instrucciones.setSpacing(16);
			
			Text texto = new Text();
			texto.setText("Es un juego por turnos. Hay 2 jugadores, cada uno debe elegir un equipo antes de iniciar una partida\n"
					+ "En un mismo turno, se puede realizar una ataque y un movimiento, no necesariamente del mismo personaje.\n"
					+ "Cada personaje tiene un ataque basico y un ataque especial, que puede aplicar un efecto.\n"
					+ "Tambien pueden realizar transformaciones cuando cumplen los requerimientos necesarios.\n"
					+ "Los personajes se desplazan por el tablero de casillero en casillero. \n"
					+ "Cada punto de su velocidad de desplazamiento representa 1 casillero.\n"
					+ "La distancia de ataque representa la cantidad de casilleros maxima que puede atacar.\n"
					+ "El Ki se aumenta automaticamente en cada personaje, al comenzar el turno.\n"
					+ "Cuando comienza el turno del jugador, puede seleccionar cualquier personaje de su equipo. \n"
					+ "Luego debe selecionar una accion dentro de las posibles: \n"
					+ "Mover, atacar, transformar o ataque especial.\n"
					+ "Al seleccionar mover, debe elegir la posicion a moverse. \n"
					+ "Al seleccionar atacar o ataque especial, debe elegir al enemigo a atacar.\n"
					+ "Se puede cancelar la accion seleccionada en cualquier momento, o terminar el turno.\n"
					+ "El turno termina automaticamente cuando ya se realizo un ataque y un movimiento, \n"
					+ "o cuando ninguna de estas acciones es posible para ningun personaje del equipo.\n"
					+ "Para ganar se debe matar a todos los personajes enemigos, \n"
					+ "o capturar 7 esferas del dragon entre cualquiera de los 3 personajes.");
			
			texto.setFill(Color.LIGHTBLUE);
			texto.setFont(Font.font("Calibri", FontWeight.EXTRA_BOLD, 20));
			texto.setTextAlignment(TextAlignment.JUSTIFY);
			texto.setStroke(Color.BLACK);
			texto.setStrokeWidth(0.8);
			
			BotonMenu btnAtrasInstrucciones = new BotonMenu("ATRAS", sonidos);
			TransicionMenuEventHandler eventoTransicion = new TransicionMenuEventHandler(this, menuInstrucciones, menuOpciones, -posSubmenu2);
			btnAtrasInstrucciones.setOnMouseClicked(eventoTransicion);
			
			btnAtrasInstrucciones.setAlignment(Pos.CENTER);
			instrucciones.getChildren().addAll(texto, btnAtrasInstrucciones);
			instrucciones.setAlignment(Pos.CENTER);
			menuInstrucciones.getChildren().add(instrucciones);
			
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
