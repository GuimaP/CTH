package controller.launcher;

import controller.view.ControllerTelaPrincipal;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class LauncherTelaGeraRelatorio extends Application {
protected static Stage stage;
	
	@Override
	public void start(Stage owner) throws Exception {
		Stage primaryStage = new Stage();
		stage = primaryStage;
		
		primaryStage.initModality(Modality.NONE);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		primaryStage.initOwner(owner);
		
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/telaRelatorio.fxml"));
		
		primaryStage.setTitle("GeraRelatorio");
		primaryStage.setScene(new Scene(root, Color.TRANSPARENT));
		primaryStage.show();
		
		
		primaryStage.setOnHidden(new EventHandler<WindowEvent>() {		
			@Override
			public void handle(WindowEvent event) {
				ControllerTelaPrincipal.telaCarro = false;
				
			}
		});
		
	}
	
	
	
}
