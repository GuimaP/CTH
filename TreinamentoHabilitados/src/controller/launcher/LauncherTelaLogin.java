package controller.launcher;

import org.controlsfx.dialog.Dialogs;

import controller.view.ControllerTelaLogin;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LauncherTelaLogin extends Application{
	//inicializando Variavel
	public static Stage stage = null;
	

	@Override
	public void start(Stage primaryStage) throws Exception {
//		primaryStage.initStyle(StageStyle.UNDECORATED);
//		Parent root = FXMLLoader.load(getClass().getResource("/fxml/telaLogin.fxml"));
//		primaryStage.resizableProperty().setValue(false);
//		
//		primaryStage.setTitle("Login");
//		primaryStage.setScene(new Scene(root, 865,223));
//		
//		primaryStage.show();
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/resources/icons/logo.png")));
		LauncherTelaLogin.stage = primaryStage;
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.initStyle(StageStyle.TRANSPARENT);

		FXMLLoader loader = new FXMLLoader(
		  getClass().getResource(
		    "/fxml/telaLogin.fxml"
		  )
		);

		primaryStage.setScene(
		  new Scene(
		    (Parent) loader.load(),Color.TRANSPARENT
		  )
		);

		ControllerTelaLogin controller = 
		  loader.<ControllerTelaLogin>getController();
//		controller.registerStage(primaryStage);

		primaryStage.show();
		
		
		
	}
	
	
	
	
}
