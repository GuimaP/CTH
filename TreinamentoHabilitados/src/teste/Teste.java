package teste;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Teste extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/telaLogin.fxml"));
		primaryStage.setTitle("CTH Login");
		primaryStage.setScene(new Scene(root,465,223));
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
