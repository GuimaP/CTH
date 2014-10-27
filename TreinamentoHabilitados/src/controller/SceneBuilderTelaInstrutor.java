package controller;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class SceneBuilderTelaInstrutor  extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/telaInstrutor.fxml"));
		primaryStage.setTitle("Instrutor");
		primaryStage.setScene(new Scene(root, 600,400));
		primaryStage.show();
		
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				ControllerTelaPrincipal.telaInstrutor = false;
				
			}
		});
		
		
	}

}
