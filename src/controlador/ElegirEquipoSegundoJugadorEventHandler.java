package controlador;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modelo.juego.DragonBall;
import vista.LibreriaSonidos;
import vista.MenuPrincipal;
import vista.VistaJuego;

public class ElegirEquipoSegundoJugadorEventHandler implements EventHandler<MouseEvent> {

	private String nombreEquipo;
	private Stage stage;
	private DragonBall juego;
	private LibreriaSonidos sonidos;
	private MenuPrincipal menu;

	public ElegirEquipoSegundoJugadorEventHandler(DragonBall juego, String nombreEquipo, Stage stage, LibreriaSonidos sonidos, MenuPrincipal menu) {
		this.juego = juego;
		this.nombreEquipo = nombreEquipo;
		this.stage = stage;
		this.sonidos = sonidos;
		this.menu = menu;
	}


	@Override
	public void handle(MouseEvent event) {
		juego.establecerEquipoJugador2(nombreEquipo);
		juego.iniciar();
		Boolean pantallaCompleta = this.comprobarPantallaCompleta();
		Scene scene = new Scene(new VistaJuego(juego, stage,this.sonidos));
		scene.setOnKeyPressed(new VolverAlMenuEventHandler(menu, stage, scene));
		stage.setScene(scene);
		stage.setFullScreen(pantallaCompleta);
		stage.show();
		
	}

	private Boolean comprobarPantallaCompleta() {
		return stage.isFullScreen();
	}

}
