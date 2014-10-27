package controller;

import view.Principal;

import com.sun.imageio.stream.StreamCloser.CloseAction;
import com.sun.javafx.font.Disposer;
import com.sun.scenario.effect.impl.prism.PrImage;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class SceneBuilderTelaCarro extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/telaCarro.fxml"));
		primaryStage.setTitle("Carro");
		primaryStage.setScene(new Scene(root, 500,320));
		primaryStage.show();
		
		
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {		
			@Override
			public void handle(WindowEvent event) {
				ControllerTelaPrincipal.telaCarro = false;
				
			}
		});
		
	}
	
	

}
