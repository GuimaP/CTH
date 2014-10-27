package controller;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class SceneBuilderTelaCliente extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/telaCliente.fxml"));
		stage.setTitle("Cliente");
		stage.setScene(new Scene(root, 1110,630));
		stage.show();		
		
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				ControllerTelaPrincipal.telaCliente = false;
				
			}
		});
	}
}
