package vista.controlador;


import java.awt.MouseInfo;

import javafx.animation.PathTransition;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Screen;
import javafx.util.Duration;
import modelo.excepciones.AtaqueNoPosible;
import modelo.juego.DragonBall;
import modelo.personajes.Personaje;
import vista.BotonInvisible;
import vista.BotonPersonaje;
import vista.VistaJuego;

public class BotonPersonajeAtacableEventHandler implements EventHandler<MouseEvent> {

	private DragonBall juego;
	private Personaje personaje;
	private Label acciones;
	private VistaJuego vista;
	private BotonInvisible boton;
	private BotonPersonaje atacante;

	public BotonPersonajeAtacableEventHandler(DragonBall juego, Personaje personaje, Label labelAcciones, VistaJuego vista, BotonInvisible boton) {
		this.juego = juego;
		this.personaje = personaje;
		this.acciones = labelAcciones;
		this.vista = vista;
		this.boton = boton;
		this.atacante = vista.getBotonPersonajeSeleccionado();
	}



	@Override
	public void handle(MouseEvent event) {
		try {
			
			
			juego.jugadorActualAtacarAEnemigo(personaje);
		
			
			
//			double posDestinoX = boton.localToScene(Point3D.ZERO).getX();
//			double posDestinoX =boton.localToScene(boton.getBoundsInLocal().getMaxX());
		

			//double PosDestinoY = boton.localToScene(boton.getBoundsInLocal().getMaxY();
//			double posDestinoY = boton.localToScene(Point3D.ZERO).getY();



//			double x = MouseInfo.getPointerInfo().getLocation().x;
//			double y = MouseInfo.getPointerInfo().getLocation().y;			

			
//			TranslateTransition transicionTraslado = new TranslateTransition(Duration.seconds(2), atacante);
//			transicionTraslado.setToY(y);
//			transicionTraslado.setToX(x);
//			transicionTraslado.setAutoReverse(true);
		
//			transicionTraslado.play();
			
			
		
			boton.parpadear(Color.RED);
			PauseTransition pausa = new PauseTransition(Duration.seconds(1));
			pausa.setOnFinished(finPausa -> {
				vista.actualizarVista();
			});
			pausa.play();
		} catch (AtaqueNoPosible error) {
			acciones.setText("No puede atacar: " + error.getMensaje());
			boton.reproducirSonidoError();
		}
	}

}
