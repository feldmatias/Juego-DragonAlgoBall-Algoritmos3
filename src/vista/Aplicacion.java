package vista;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;


public class Aplicacion extends Application {

	private MenuPrincipal menuJuego;
	private Stage stage;
	private LibreriaSonidos sonidos ;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.stage = primaryStage;
		this.sonidos = new LibreriaSonidos();
		

		this.sonidos.reproducirMusica();		
		
		this.menuJuego = new MenuPrincipal(stage,sonidos);
		
		Scene scene = new Scene(menuJuego);
		
		stage.setScene(scene);
		stage.setFullScreen(true);
		stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
		stage.setMinWidth(800);
		stage.setMinHeight(600);
		stage.setTitle("DRAGON ALGO BALL");
		stage.show();
		
		
	}
	
	
	
	public static void main(String [] args){
		launch(args);
	}

}
