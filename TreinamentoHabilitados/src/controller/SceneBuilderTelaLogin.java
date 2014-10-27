package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneBuilderTelaLogin extends Application{
	
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/telaLogin.fxml"));
		primaryStage.setTitle("Login");
		primaryStage.setScene(new Scene(root, 465,223));
		primaryStage.show();
		
	
		
		
		
		
	}
}
