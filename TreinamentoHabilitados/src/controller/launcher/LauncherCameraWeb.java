package controller.launcher;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 * This example demonstrates how to use Webcam Capture API via FXML in a JavaFX
 * application.
 * 
 * @author Rakesh Bhatt (rakeshbhatt10)
 */
public class LauncherCameraWeb extends Application {
	
	public static Stage stage;
	
	@Override
	public void start(Stage owner) {
		Stage primaryStage = new Stage();
		stage = primaryStage;
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/fxml/cameraWeb.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		primaryStage.initStyle(StageStyle.UTILITY);
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		primaryStage.initModality(Modality.APPLICATION_MODAL);
		primaryStage.initOwner(owner);
		primaryStage.setResizable( false );
		Scene scene = new Scene(root, 900, 690,Color.TRANSPARENT);
		
		
	
		primaryStage.setTitle("CTH  ");
		primaryStage.setScene(scene);
		primaryStage.centerOnScreen();
		primaryStage.show();
	}

	
	
	
}