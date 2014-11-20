package controller.launcher;

import java.io.IOException;

import controller.view.ControllerTelaFinanceiro;
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

public class LauncherTelaFinanceiro extends Application {

	public static Stage stage;

	@Override
	public void start(Stage owner) throws IOException {
		Stage primaryStage = new Stage();
		LauncherTelaFinanceiro.stage = primaryStage;
		
		primaryStage.initModality(Modality.NONE);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		primaryStage.initOwner(owner);
		
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/telaFinanceiro.fxml"));
		
		primaryStage.setTitle("Financeiro");
		primaryStage.setScene(new Scene(root, Color.TRANSPARENT));
		primaryStage.show();
		
		primaryStage.setOnHidden(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				ControllerTelaFinanceiro.telaFinanceiro= false;
				
			}
		});
		
		
	}
	
}
