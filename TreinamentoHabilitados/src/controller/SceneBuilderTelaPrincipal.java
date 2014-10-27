package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneBuilderTelaPrincipal extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/telaPrincipal.fxml"));
		primaryStage.setTitle("CTH Manager");
		primaryStage.setScene(new Scene(root, 1024,640));
		primaryStage.show();
		
		
		
	
		
	}

}
