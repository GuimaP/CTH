package controller.launcher;


import javax.swing.JOptionPane;

import com.sun.imageio.stream.StreamCloser.CloseAction;
import com.sun.javafx.font.Disposer;
import com.sun.scenario.effect.impl.prism.PrImage;

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

public class LauncherTelaCarro extends Application{

	public static Stage stage;
	
	@Override
	public void start(Stage owner) throws Exception {
		Stage primaryStage = new Stage();
		stage = primaryStage;
		
		primaryStage.initModality(Modality.NONE);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		primaryStage.initOwner(owner);
		
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/telaCarro.fxml"));
		
		primaryStage.setTitle("Carro");
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
