package vista;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modelo.juego.Casillero;
import modelo.juego.DragonBall;
import modelo.juego.Posicion;
import modelo.juego.Tablero;
import modelo.personajes.Personaje;
import modelo.utilidades.Constantes;
import vista.controlador.BotonAtacarEventHandler;
import vista.controlador.BotonAtaqueEspecialEventHandler;
import vista.controlador.BotonCasilleroOcupadoEventHandler;
import vista.controlador.BotonCasilleroVacioEventHandler;
import vista.controlador.BotonMoverEventHandler;
import vista.controlador.BotonTerminarTurnoEventHandler;
import vista.controlador.BotonTransformarEventHandler;

public class VistaJuego extends BorderPane{

	private DragonBall juego;
	private List <BotonInvisible> botonesCasilleros;
	private Map <Personaje,BotonInvisible> botonesPersonajes;
	private Label labelAcciones;
	
	public VistaJuego(DragonBall juego){
		this.juego = juego;
		this.labelAcciones = new Label();
		this.actualizarVista();
		this.crearBotonesAcciones();
		this.crearFondo();
	}


	private void crearFondo() {
		InputStream entradaImagen = null;
		try {
			entradaImagen = Files.newInputStream(Paths.get("src/vista/imagenes/fondo.png"));
			Image imagen = new Image(entradaImagen);
			entradaImagen.close();
			BackgroundImage vistaImagen = new BackgroundImage(imagen,
					BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
			this.setBackground(new Background(vistaImagen));
		} catch (IOException e) {
		}
		
	}


	private void crearBotonesAcciones() {
		Button terminarTurno = new Button();
		BotonTerminarTurnoEventHandler eventHandler = new BotonTerminarTurnoEventHandler(juego, this);
		terminarTurno.setOnAction(eventHandler);
		terminarTurno.setText("Terminar Turno");
		
		Button transformar = new Button();
		BotonTransformarEventHandler eventHandler2 = new BotonTransformarEventHandler(juego, this, labelAcciones);
		transformar.setOnAction(eventHandler2);
		transformar.setText("Transformar");
		
		Button mover = new Button();
		BotonMoverEventHandler eventHandler3 = new BotonMoverEventHandler( labelAcciones, botonesCasilleros, botonesPersonajes);
		mover.setOnAction(eventHandler3);
		mover.setText("Mover");
		
		Button atacar = new Button();
		BotonAtacarEventHandler eventHandler4 = new BotonAtacarEventHandler( labelAcciones, botonesCasilleros, botonesPersonajes, juego, this);
		atacar.setOnAction(eventHandler4);
		atacar.setText("Atacar");
		
		Button ataqueEspecial = new Button();
		BotonAtaqueEspecialEventHandler eventHandler5 = new BotonAtaqueEspecialEventHandler( labelAcciones, botonesCasilleros, botonesPersonajes, juego, this);
		ataqueEspecial.setOnAction(eventHandler5);
		ataqueEspecial.setText("Ataque Especial");
		
		
		HBox botones = new HBox(terminarTurno, transformar, mover,atacar,ataqueEspecial);
		botones.setPadding(new Insets(20));
		botones.setSpacing(10);
		
		this.setBottom(botones);
		
	}


	public void actualizarVista() {
		
		this.actualizarCasilleros();
		this.actualizarTurnos();
	}
		
	private void actualizarTurnos() {
		Label labelTurnos = new Label();
		labelTurnos.setText("Turno de: " + juego.getJugadorActual().getEquipo().getNombre());
		VBox contenedorLabels = new VBox(labelTurnos, labelAcciones);
		this.setTop(contenedorLabels);
	}


	private void actualizarCasilleros() {
		botonesCasilleros = new ArrayList<BotonInvisible>();
		botonesPersonajes = new HashMap<Personaje,BotonInvisible>();
		
		Tablero tablero = juego.getTablero();
		VBox columnas = new VBox();
		HBox fila;
		for (int i = 0; i < Constantes.SIZE_TABLERO; i++){
			fila = new HBox();
			for (int j = 0; j < Constantes.SIZE_TABLERO; j++){
				Posicion pos = new Posicion(i,j);
				Casillero casillero = tablero.getCasillero(pos);
					if (casillero.estaVacio()){
						this.nuevoBotonCasilleroVacio(fila, pos);
					}else{
						Personaje ocupante = casillero.getPersonaje();
						this.nuevoBotonPersonaje(fila, ocupante);
					}
			}
			columnas.getChildren().add(fila);
		}
		this.setCenter(columnas);
	}

	private void nuevoBotonPersonaje(HBox fila, Personaje personaje) {
		BotonCasilleroOcupadoEventHandler eventHandler = new BotonCasilleroOcupadoEventHandler(juego, personaje, new Label(), new Label(), this);
		//Ver labels
		BotonInvisible boton = new BotonPersonaje(personaje, juego);
		boton.setOnAction(eventHandler);
		boton.habilitar();
		fila.getChildren().add(boton);
		
	}


	private void nuevoBotonCasilleroVacio(HBox fila, Posicion pos) {
		BotonCasilleroVacioEventHandler eventHandler = new BotonCasilleroVacioEventHandler(juego,pos,new Label(), this);
		//ver LABELS
		BotonInvisible boton = new BotonCasilleroVacio();
		boton.setOnAction(eventHandler);
		boton.deshabilitar();
		fila.getChildren().add(boton);
	}
	
}
