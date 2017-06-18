package vista;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controlador.eventosAccionesJuego.BotonAtacarEventHandler;
import controlador.eventosAccionesJuego.BotonAtaqueEspecialEventHandler;
import controlador.eventosAccionesJuego.BotonCancelarAccionEventHandler;
import controlador.eventosAccionesJuego.BotonMoverEventHandler;
import controlador.eventosAccionesJuego.BotonTerminarTurnoEventHandler;
import controlador.eventosAccionesJuego.BotonTransformarEventHandler;
import controlador.eventosBotonesJuego.BotonCasilleroOcupadoEventHandler;
import controlador.eventosBotonesJuego.BotonCasilleroVacioEventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import javafx.stage.Stage;
import modelo.juego.Casillero;
import modelo.juego.DragonBall;
import modelo.juego.Jugador;
import modelo.juego.Posicion;
import modelo.juego.Tablero;
import modelo.personajes.Personaje;
import modelo.utilidades.Constantes;
import vista.botones.BotonAccion;
import vista.botones.BotonCasilleroVacio;
import vista.botones.BotonInvisible;
import vista.botones.BotonPersonaje;
import vista.botones.ImagenFondo;

public class VistaJuego extends VBox{

	private DragonBall juego;
	private List <BotonInvisible> botonesCasilleros;
	private Map <Personaje,BotonInvisible> botonesPersonajes;
	private Text informacionAcciones;
	private HBox contenedorBotonesAcciones;
	public LibreriaSonidos sonidos;
	private Stage stage;
	private String rutaFuente;
	
	

	
	public VistaJuego(DragonBall juego, Stage stage,LibreriaSonidos sonidos){
		
		this.stage = stage;
		this.sonidos = sonidos;
		this.juego = juego;
		this.informacionAcciones = new Text();
		informacionAcciones.setFont(Font.font("Calibri", 18));
		rutaFuente = "file:src/vista/imagenes/Saiyan-Sans.ttf";
		
		botonesCasilleros = new ArrayList<BotonInvisible>();
		botonesPersonajes = new HashMap<Personaje,BotonInvisible>();
		
		Image imagen = new Image("file:src/vista/imagenes/fondos/Fondo 13.jpg");
		BackgroundSize size = new BackgroundSize(ConstantesPantalla.altoImagenFondo,ConstantesPantalla.anchoImagenFondo,false,false,true,true);
		BackgroundImage imagenDeFondo = new BackgroundImage(imagen, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,size);
		this.setBackground(new Background(imagenDeFondo));
		
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
		Scene fin = new Scene (new VistaFinDelJuego(juego,stage,this.sonidos));
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
		Text nombre = new Text();
		nombre.setText(jugador.getEquipo().getNombre());
		nombre.setStroke(Color.WHITE);
		nombre.setFont(Font.loadFont(rutaFuente, ConstantesPantalla.tamFuenteNombreEquipo)); 
		VBox contenedor = new VBox (nombre);
		int cantidadEsferas = jugador.getEquipo().getCantidadEsferas();
		InputStream entradaImagen;
		try {
			entradaImagen = Files.newInputStream(Paths.get("src/vista/imagenes/esferas/" + cantidadEsferas + "Esferas.png"));
			Image imagen = new Image(entradaImagen);
			entradaImagen.close();
			ImageView vistaImagen = new ImageView(imagen);
			vistaImagen.setFitHeight(ConstantesPantalla.tamEsfera); 
			vistaImagen.setFitWidth(ConstantesPantalla.tamEsfera);  
			contenedor.getChildren().add(vistaImagen);
		} catch (IOException e) {
		}
		for (Personaje personaje: jugador.getEquipo().getMiembros().values()){
			contenedor.getChildren().add(this.crearBoxPersonaje(personaje));
		}
		contenedor.setAlignment(Pos.TOP_CENTER);
		contenedor.setSpacing(10);
		return contenedor;
	}

	private VBox crearBoxPersonaje(Personaje personaje) {
		
		VBox boxPersonaje = new VBox();
		HBox boxDatos = new HBox(new ImagenFondo(personaje, ConstantesPantalla.anchoPersonaje, ConstantesPantalla.altoPersonaje));
		VBox parametrosPersonaje = new VBox();
		parametrosPersonaje.setPadding(new Insets(10));
		
		Text nombre = new Text();
		nombre.setText(personaje.getNombre());
		nombre.setStroke(Color.WHITE);
		nombre.setFont(Font.loadFont(rutaFuente, ConstantesPantalla.tamFuenteNombrePersonaje));
		nombre.setUnderline(true);
		
		
		Label vida = new Label();
		vida.setText("Vida: " + String.valueOf((int)personaje.getVidaActual()));
		vida.setTextFill(Color.WHITE);
		Label ki = new Label();
		ki.setText("Ki: " + String.valueOf(personaje.getKi()));
		ki.setTextFill(Color.WHITE);
		Label velocidad = new Label();
		velocidad.setTextFill(Color.WHITE);
		velocidad.setText("Velocidad: " + String.valueOf(personaje.getVelocidad()));
		Label distanciaAtaque = new Label();
		distanciaAtaque.setText("Distancia Ataque: " + String.valueOf(personaje.getDistanciaAtaque()));
		distanciaAtaque.setTextFill(Color.WHITE);
		
		String letraParametros = "Calibri";
		double tamanioParametros = 16;
		FontWeight font = FontWeight.EXTRA_BOLD;
		
		vida.setFont(Font.font(letraParametros, font, tamanioParametros));
		ki.setFont(Font.font(letraParametros,font, tamanioParametros));
		velocidad.setFont(Font.font(letraParametros,font, tamanioParametros));
		distanciaAtaque.setFont(Font.font(letraParametros,font, tamanioParametros));
		
		parametrosPersonaje.getChildren().addAll(nombre,vida,ki,velocidad,distanciaAtaque);
		boxDatos.getChildren().add(parametrosPersonaje);
		boxDatos.setAlignment(Pos.CENTER);
		boxPersonaje.getChildren().addAll(nombre, boxDatos);
		boxPersonaje.setAlignment(Pos.CENTER);
		return boxPersonaje;
	}


	private void crearBotonesAcciones() {
		BotonTerminarTurnoEventHandler eventHandler = new BotonTerminarTurnoEventHandler(juego, this);
		BotonAccion terminarTurno = new BotonAccion ("Terminar Turno", eventHandler, sonidos);
		
		BotonTransformarEventHandler eventHandler2 = new BotonTransformarEventHandler(juego, this, informacionAcciones, botonesPersonajes);
		BotonAccion transformar = new BotonAccion ("Transformar", eventHandler2, sonidos);
		
		BotonMoverEventHandler eventHandler3 = new BotonMoverEventHandler( informacionAcciones, botonesCasilleros, botonesPersonajes);
		BotonAccion mover = new BotonAccion ("Mover", eventHandler3, sonidos);
		
		BotonAtacarEventHandler eventHandler4 = new BotonAtacarEventHandler( informacionAcciones, botonesCasilleros, botonesPersonajes, juego, this);
		BotonAccion atacar = new BotonAccion ("Atacar", eventHandler4, sonidos);
		
		BotonAtaqueEspecialEventHandler eventHandler5 = new BotonAtaqueEspecialEventHandler( informacionAcciones, botonesCasilleros, botonesPersonajes, juego, this);
		BotonAccion ataqueEspecial = new BotonAccion ("Ataque Especial", eventHandler5, sonidos);
		
		BotonCancelarAccionEventHandler eventHandler6 = new BotonCancelarAccionEventHandler(this);
		BotonAccion cancelarAccion = new BotonAccion ("Cancelar Accion", eventHandler6, sonidos);
		
		contenedorBotonesAcciones = new HBox(mover,atacar,ataqueEspecial, transformar, cancelarAccion, terminarTurno);
		contenedorBotonesAcciones.setPadding(new Insets(20));
		contenedorBotonesAcciones.setSpacing(50);
		
		contenedorBotonesAcciones.setAlignment(Pos.CENTER);
		
	}



	private VBox actualizarTurnos() {
		Text turnos = new Text();
		turnos.setText("Turno de: " + juego.getJugadorActual().getEquipo().getNombre());
		turnos.setFont(Font.loadFont(rutaFuente, ConstantesPantalla.tamFuenteTurno)); 
		turnos.setUnderline(true);
		turnos.setStroke(Color.WHITE);
		informacionAcciones.setText("Selecciona un personaje");
		VBox contenedorDatos = new VBox(turnos, informacionAcciones);
		contenedorDatos.setAlignment(Pos.CENTER);
		contenedorDatos.setSpacing(12);
		return contenedorDatos;
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
		BotonInvisible boton = new BotonPersonaje(personaje, juego,this.sonidos);
		BotonCasilleroOcupadoEventHandler eventHandler = new BotonCasilleroOcupadoEventHandler(juego, personaje, informacionAcciones, this, boton);

		boton.setOnAction(eventHandler);
		boton.habilitar();
		fila.getChildren().add(boton);
		this.botonesPersonajes.put(personaje, boton);
	}


	private void nuevoBotonCasilleroVacio(HBox fila, Posicion pos, Casillero casillero) {
		BotonInvisible boton = new BotonCasilleroVacio(casillero,this.sonidos);

		BotonCasilleroVacioEventHandler eventHandler = new BotonCasilleroVacioEventHandler(juego,pos,informacionAcciones, this, boton);
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
			vistaImagen.setFitWidth(ConstantesPantalla.anchoImagenTablero);
			vistaImagen.setFitHeight(ConstantesPantalla.altoImagenTablero);
			return vistaImagen;
		} catch (IOException e) {
			return null;
		}
		
	}

	
}
