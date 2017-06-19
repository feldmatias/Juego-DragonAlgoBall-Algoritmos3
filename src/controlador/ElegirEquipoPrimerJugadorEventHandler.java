package controlador;

import javafx.event.EventHandler;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modelo.juego.DragonBall;
import vista.LibreriaSonidos;
import vista.MenuPrincipal;

public class ElegirEquipoPrimerJugadorEventHandler implements EventHandler<MouseEvent>{

	private VBox equipoActual;
	private VBox otroEquipo;
	private DragonBall juego;
	private String nombreEquipoActual;
	private String nombreOtroEquipo;
	private Stage stage;
	private LibreriaSonidos sonidos;
	private MenuPrincipal menu;
	private Text title;

	public ElegirEquipoPrimerJugadorEventHandler(DragonBall juego, String nombreEquipoActual, String nombreOtroEquipo, VBox equipoActual, VBox otroEquipo,
			Stage stage, LibreriaSonidos sonidos, MenuPrincipal menu, Text title){
		this.juego = juego;
		this.nombreEquipoActual = nombreEquipoActual;
		this.nombreOtroEquipo = nombreOtroEquipo;
		this.equipoActual = equipoActual;
		this.otroEquipo = otroEquipo;
		this.stage = stage;
		this.sonidos = sonidos;
		this.menu = menu;
		this.title = title;
	}
	
	@Override
	public void handle(MouseEvent event) {
		juego.establecerEquipoJugador1(nombreEquipoActual);
		title.setText("Jugador II: Elegir equipo");
		this.deshabilitarOpcionEquipo();
		otroEquipo.setOnMouseClicked(new ElegirEquipoSegundoJugadorEventHandler(juego, nombreOtroEquipo, stage, sonidos, menu));
	}

	private void deshabilitarOpcionEquipo() {
		DropShadow sombra = new DropShadow( 20, Color.GRAY);
		equipoActual.setEffect(sombra);
		equipoActual.setOpacity(0.7);
		equipoActual.setDisable(true);
	}

}
