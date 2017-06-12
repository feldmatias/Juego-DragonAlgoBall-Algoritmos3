package vista;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.juego.DragonBall;
import modelo.personajes.Personaje;
import modelo.juego.Casillero;
import modelo.juego.Posicion;
import modelo.juego.Tablero;
import modelo.utilidades.Constantes;

public class Vista extends Application {

	private DragonBall juego;
	private Stage stage;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	@Override
	public void start(Stage stage) throws Exception {
		
		this.stage = stage;
		this.stage.setTitle("Dragon AlgoBall");
		juego = new DragonBall();
		juego.establecerEquipoJugador1(Constantes.GUERREROS);
		juego.establecerEquipoJugador2(Constantes.ENEMIGOS);
		juego.iniciar();
		
		this.actualizarVista();

        this.stage.show();
		
	}


	public void actualizarVista() {
		
		List <Button> botonesCasilleros = new ArrayList<Button>();
		Map <Personaje,Button> botonesPersonajes = new HashMap<Personaje,Button>();
		
		Label labelTurnos = new Label();
		labelTurnos.setText("Turno de: " + juego.getJugadorActual().getEquipo().getNombre());
		Label labelPersonajeSeleccionado = new Label();
		Label labelAcciones = new Label();
		modelo.juego.Tablero tablero = juego.getTablero();
		VBox contenedorPrincipal = new VBox(labelTurnos, labelPersonajeSeleccionado, labelAcciones);
		HBox fila;
		Button botonCasillero;
		for (int i = 0; i < Constantes.SIZE_TABLERO; i++){
			fila = new HBox();
			for (int j = 0; j < Constantes.SIZE_TABLERO; j++){
				modelo.juego.Posicion pos = new modelo.juego.Posicion(i,j);
				botonCasillero = new Button();
				botonCasillero.setMinWidth(100);
				botonCasillero.setMinHeight(60);
				modelo.juego.Casillero casillero = tablero.getCasillero(pos);
				if (casillero.estaVacio()){
					BotonCasilleroVacioEventHandler eventHandler = new BotonCasilleroVacioEventHandler(juego,pos,labelAcciones, this);
					botonCasillero.setOnAction(eventHandler);
					botonCasillero.setDisable(true);
					botonesCasilleros.add(botonCasillero);
				}else{
					Personaje ocupante = casillero.getPersonaje();
					BotonCasilleroOcupadoEventHandler eventHandler = new BotonCasilleroOcupadoEventHandler(juego, ocupante, labelPersonajeSeleccionado, labelAcciones);
					botonCasillero.setOnAction(eventHandler);
					botonCasillero.setText(ocupante.getNombre() + "\n Ki: " + ocupante.getKi() + "\n Vida: " + ocupante.getPorcentajeVida() + "%");
					botonesPersonajes.put(ocupante,botonCasillero);
				}
				fila.getChildren().add(botonCasillero);
			}
			contenedorPrincipal.getChildren().add(fila);
		}
		
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
		contenedorPrincipal.getChildren().add(botones);
		Scene scene = new Scene(contenedorPrincipal);

        stage.setScene(scene);
	}

}
