package vista;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


public class MenuJuego extends Application {

	private MenuPrincipal menuJuego;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Pane root = new Pane();
		root.setPrefSize(800, 600);
		
		InputStream entradaImagen = Files.newInputStream(Paths.get("Recursos/sheng long 2.jpg"));
		
		Image imagen = new Image(entradaImagen);
		
		entradaImagen.close();
		
		ImageView vistaImagen = new ImageView(imagen);
		vistaImagen.setFitWidth(800);
		vistaImagen.setFitHeight(600);
		
		this.menuJuego = new MenuPrincipal();
		
		root.getChildren().addAll(vistaImagen, menuJuego);
		
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
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
			
			BotonMenu btnEmpezar = new BotonMenu ("NUEVA PARTIDA");
			btnEmpezar.setOnMouseClicked( evento1 -> {
				FadeTransition transicion= new FadeTransition(Duration.seconds(0.5),this);
				transicion.setFromValue(1);
				transicion.setToValue(0);
				transicion.setOnFinished(evtento2 -> this.setVisible(false));
				transicion.play();
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
				System.exit(0);
			});
			
			
			menu1.getChildren().addAll(btnEmpezar , btnOpciones, btnSalir);
			menu2.getChildren().addAll(btnAtras, btnSonido);
			
			
			this.getChildren().addAll(menu1);
			
			
		}
		
		
	}

	
	
	
	private static class BotonMenu extends StackPane {
		private Text texto;
		
		public BotonMenu(String nombre){
			texto = new Text(nombre);
			texto.setFont(this.texto.getFont().font(20));
			texto.setFill(Color.WHITE);
			
			Rectangle fondo = new Rectangle(250,30);
			fondo.setOpacity(0.6);
			fondo.setFill(Color.BLUE);
			
			GaussianBlur desenfoque = new GaussianBlur(3.5);
			fondo.setEffect(desenfoque);
			
			this.setAlignment(Pos.CENTER_LEFT);
			this.setRotate(-0.5);
			this.getChildren().addAll(fondo,texto);
			
			this.setOnMouseEntered(evento -> {
				fondo.setTranslateX(10);
				texto.setTranslateX(10);
				fondo.setFill(Color.WHITE);
				texto.setFill(Color.BLUE);
			});
			
			this.setOnMouseExited(event ->{
				fondo.setTranslateX(0);
				texto.setTranslateX(0);
				fondo.setFill(Color.BLUE);
				texto.setFill(Color.WHITE);
			});
			
			
			DropShadow sombra = new DropShadow(50,Color.WHITE);
			sombra.setInput(new Glow());
			
			this.setOnMousePressed(evento -> setEffect(sombra));
			this.setOnMouseReleased(evento -> setEffect(null)); //remuevo el efecto
			
		}
		
		
		
	}
	
	public static void main(String [] args){
		launch(args);
	}

}