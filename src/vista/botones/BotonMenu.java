package vista.botones;


import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import vista.LibreriaSonidos;

public class BotonMenu extends StackPane {
	private Text texto;
	private Rectangle fondo;
	private Color colorActualBoton;
	private Color colorAuxiliarBoton;
	private LibreriaSonidos sonidos;
	
	public BotonMenu(String nombre, LibreriaSonidos sonidos){
		this.sonidos = sonidos;
		
		texto = new Text(nombre);
		texto.setFont(Font.font(20));
		texto.setFill(Color.WHITE);

		
		this.colorActualBoton =Color.ORANGE;
		this.colorAuxiliarBoton = Color.WHITE;
		this.fondo = new Rectangle(250,30);
		this.fondo.setOpacity(0.6);
		this.fondo.setFill(colorActualBoton);
		
		GaussianBlur desenfoque = new GaussianBlur(3.5);
		this.fondo.setEffect(desenfoque);
		
		this.setAlignment(Pos.CENTER_LEFT);
		this.setRotate(-0.5);
		this.getChildren().addAll(fondo,texto);
		
		this.setOnMouseEntered(evento -> {
			this.fondo.setTranslateX(10);
			this.texto.setTranslateX(10);
			this.fondo.setFill(colorAuxiliarBoton);
			this.texto.setFill(Color.ORANGE);
			this.reproducirSonido();
		});
		
		this.setOnMouseExited(event ->{
			this.fondo.setTranslateX(0);
			this.texto.setTranslateX(0);
			this.fondo.setFill(colorActualBoton);
			this.texto.setFill(Color.WHITE);
		});
		
		
		DropShadow sombra = new DropShadow(50,Color.WHITE);
		sombra.setInput(new Glow());
		
		this.setOnMousePressed(evento -> setEffect(sombra));
		this.setOnMouseReleased(evento -> setEffect(null));
		
	}

	public void deshabilitar(){
		colorActualBoton = Color.GRAY;
		this.fondo.setFill(colorActualBoton);
		this.setDisable(true);
	}
	public void habilitar(){
		colorActualBoton = Color.ORANGE;
		this.fondo.setFill(colorActualBoton);
		this.setDisable(false);
	}
	
	private void reproducirSonido() {
		sonidos.reproducirSonidoBotonMenu();
		
	}
	
}
	
