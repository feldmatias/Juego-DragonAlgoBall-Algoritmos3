package vista.controlador;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import vista.MenuPrincipal;

public class VolverAlMenuEventHandler implements EventHandler<KeyEvent> {
	
	private MenuPrincipal menu;
	private Stage stage;
	private Scene vistaJuego;

	public VolverAlMenuEventHandler(MenuPrincipal menu, Stage stage, Scene vistaJuego) {
		this.menu = menu;
		this.stage = stage;
		this.vistaJuego = vistaJuego;
	}

	@Override
	public void handle(KeyEvent event) {
		if (event.getCode() == KeyCode.ESCAPE) {
			menu.habilitarContinuar(vistaJuego);
			
			stage.setScene(menu.getScene());
			stage.setFullScreen(true);
			stage.setFullScreenExitHint("");
			stage.setResizable(false);
			stage.show();
        }
	}

}
