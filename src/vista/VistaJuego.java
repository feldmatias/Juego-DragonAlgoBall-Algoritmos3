package vista;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import modelo.juego.Casillero;
import modelo.juego.DragonBall;
import modelo.juego.Jugador;
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

public class VistaJuego extends VBox{

	private DragonBall juego;
	private List <BotonInvisible> botonesCasilleros;
	private Map <Personaje,BotonInvisible> botonesPersonajes;
	private Label labelAcciones;
	private HBox contenedorBotonesAcciones;
	public Media archivoSonidoError = new Media(new File("src/vista/sonidos/sonido error.wav").toURI().toString());
	public MediaPlayer sonidoError = new MediaPlayer(archivoSonidoError);
	private Stage stage;

	
	public VistaJuego(DragonBall juego, Stage stage){
		this.stage = stage;
		
		botonesCasilleros = new ArrayList<BotonInvisible>();
		botonesPersonajes = new HashMap<Personaje,BotonInvisible>();
		
		this.juego = juego;
		this.labelAcciones = new Label();
		labelAcciones.setFont(Font.font("Calibri", 18));
		this.crearBotonesAcciones();
		this.actualizarVista();
	}
	
	public void actualizarVista() {
		if (juego.estaTerminado()){
			this.terminarJuego();
		}
		this.getChildren().clear();
		this.getChildren().add(this.actualizarTurnos());
		HBox contenedorHorizontal = new HBox();
		contenedorHorizontal.setAlignment(Pos.CENTER);
		contenedorHorizontal.getChildren().add(this.espacioJugador1());
		contenedorHorizontal.getChildren().add(this.actualizarCasilleros());
		contenedorHorizontal.getChildren().add(this.espacioJugador2());
		contenedorHorizontal.setPadding(new Insets(20));
		contenedorHorizontal.setSpacing(100);
		this.getChildren().add(contenedorHorizontal);
		this.getChildren().add(this.contenedorBotonesAcciones);
		this.setAlignment(Pos.CENTER);
	}
		
	private void terminarJuego() {
		Boolean pantallaCompleta = this.comprobarPantallaCompleta();
		Scene fin = new Scene (new VistaFinDelJuego(juego,stage));
		stage.setScene(fin);
		stage.setFullScreen(pantallaCompleta);
	}
	
	private Boolean comprobarPantallaCompleta() {
		return stage.isFullScreen();
	}
	
	private VBox espacioJugador1() {
		return this.crearEspacioJugador(juego.getJugador1());
	}
	
	private VBox espacioJugador2() {
		return this.crearEspacioJugador(juego.getJugador2());
	}

	private VBox crearEspacioJugador(Jugador jugador){
		Label nombre = new Label();
		nombre.setText(jugador.getEquipo().getNombre());
		nombre.setFont(Font.font("Comic Sans MS", 20));
		VBox contenedor = new VBox (nombre);
		int cantidadEsferas = jugador.getEquipo().getCantidadEsferas();
		InputStream entradaImagen;
		try {
			entradaImagen = Files.newInputStream(Paths.get("src/vista/imagenes/esferas/" + cantidadEsferas + "Esferas.png"));
			Image imagen = new Image(entradaImagen);
			entradaImagen.close();
			ImageView vistaImagen = new ImageView(imagen);
			contenedor.getChildren().add(vistaImagen);
		} catch (IOException e) {
		}
		for (Personaje personaje: jugador.getEquipo().getMiembros()){
			contenedor.getChildren().add(this.crearBoxPersonaje(personaje));
		}
		contenedor.setAlignment(Pos.TOP_CENTER);
		contenedor.setSpacing(10);
		return contenedor;
	}

	private HBox crearBoxPersonaje(Personaje personaje) {
		HBox box = new HBox(new ImagenFondo(personaje, true));
		VBox parametrosPersonaje = new VBox();
		parametrosPersonaje.setPadding(new Insets(10));
		
		Label nombre = new Label();
		nombre.setText(personaje.getNombre());
		nombre.setFont(Font.font("Bell MT", 17));
		nombre.setUnderline(true);
		
		Label labelVacio = new Label();
		
		Label vida = new Label();
		vida.setText("Vida: " + String.valueOf((int)personaje.getVidaActual()));
		Label ki = new Label();
		ki.setText("Ki: " + String.valueOf(personaje.getKi()));
		Label velocidad = new Label();
		velocidad.setText("Velocidad: " + String.valueOf(personaje.getVelocidad()));
		Label distanciaAtaque = new Label();
		distanciaAtaque.setText("Distancia Ataque: " + String.valueOf(personaje.getDistanciaAtaque()));
		
		String letraParametros = "Calibri";
		double tamanioParametros = 15;
		
		vida.setFont(Font.font(letraParametros, tamanioParametros));
		ki.setFont(Font.font(letraParametros, tamanioParametros));
		velocidad.setFont(Font.font(letraParametros, tamanioParametros));
		distanciaAtaque.setFont(Font.font(letraParametros, tamanioParametros));
		
		parametrosPersonaje.getChildren().addAll(nombre,labelVacio,vida,ki,velocidad,distanciaAtaque);
		box.getChildren().add(parametrosPersonaje);
		box.setAlignment(Pos.CENTER);
		return box;
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
		
		
		contenedorBotonesAcciones = new HBox(terminarTurno, transformar, mover,atacar,ataqueEspecial);
		contenedorBotonesAcciones.setPadding(new Insets(20));
		contenedorBotonesAcciones.setSpacing(10);
		
		contenedorBotonesAcciones.setAlignment(Pos.CENTER);
		
	}



	private VBox actualizarTurnos() {
		Label labelTurnos = new Label();
		labelTurnos.setText("Turno de: " + juego.getJugadorActual().getEquipo().getNombre());
		labelTurnos.setFont(Font.font("Comic Sans MS", 40));
		labelTurnos.setUnderline(true);
		labelAcciones.setText("Selecciona un personaje");
		VBox contenedorLabels = new VBox(labelTurnos, labelAcciones);
		contenedorLabels.setAlignment(Pos.CENTER);
		contenedorLabels.setSpacing(12);
		return contenedorLabels;
	}


	private StackPane actualizarCasilleros() {
		StackPane contenedor = new StackPane();
		contenedor.getChildren().add(this.crearFondoTablero());
		botonesCasilleros.clear();
		botonesPersonajes.clear();
		
		Tablero tablero = juego.getTablero();
		VBox columnas = new VBox();
		HBox fila;
		for (int i = 0; i < Constantes.SIZE_TABLERO; i++){
			fila = new HBox();
			for (int j = 0; j < Constantes.SIZE_TABLERO; j++){
				Posicion pos = new Posicion(j,i);
				Casillero casillero = tablero.getCasillero(pos);
					if (casillero.estaVacio()){
						this.nuevoBotonCasilleroVacio(fila, pos, casillero);
					}else{
						Personaje ocupante = casillero.getPersonaje();
						this.nuevoBotonPersonaje(fila, ocupante);
					}
			}
			columnas.getChildren().add(fila);
		}
		columnas.setAlignment(Pos.CENTER);
		contenedor.getChildren().add(columnas);
		contenedor.setAlignment(Pos.CENTER);
		return contenedor;
	}

	private void nuevoBotonPersonaje(HBox fila, Personaje personaje) {
		BotonCasilleroOcupadoEventHandler eventHandler = new BotonCasilleroOcupadoEventHandler(juego, personaje, labelAcciones, this);
		//Ver labels
		BotonInvisible boton = new BotonPersonaje(personaje, juego,sonidoError);
		boton.setOnAction(eventHandler);
		boton.habilitar();
		fila.getChildren().add(boton);
		this.botonesPersonajes.put(personaje, boton);
	}


	private void nuevoBotonCasilleroVacio(HBox fila, Posicion pos, Casillero casillero) {
		BotonInvisible boton = new BotonCasilleroVacio(casillero,sonidoError);

		BotonCasilleroVacioEventHandler eventHandler = new BotonCasilleroVacioEventHandler(juego,pos,labelAcciones, this,boton);
		//ver LABELS
		boton.setOnAction(eventHandler);
		boton.deshabilitar();
		fila.getChildren().add(boton);
		this.botonesCasilleros.add(boton);
	}
	
	private ImageView crearFondoTablero() {
		InputStream entradaImagen;
		try {
			entradaImagen = Files.newInputStream(Paths.get("src/vista/imagenes/fondo.jpg"));
			Image imagen = new Image(entradaImagen);
			entradaImagen.close();
			ImageView vistaImagen = new ImageView(imagen);
			vistaImagen.setFitWidth(Constantes.SIZE_TABLERO * BotonInvisible.anchoBoton);
			vistaImagen.setFitHeight(Constantes.SIZE_TABLERO * BotonInvisible.altoBoton);
			return vistaImagen;
		} catch (IOException e) {
			return null;
		}
		
	}
	
}
