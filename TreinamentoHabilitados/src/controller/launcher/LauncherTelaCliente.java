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

public class LauncherTelaCliente extends Application{
public static Stage stage;
	@Override
	public void start(Stage owner) throws Exception {
		Stage stage = new Stage(); 
		LauncherTelaCliente.stage = stage;
		
		stage.initModality(Modality.NONE);
		stage.initOwner(owner);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.initStyle(StageStyle.TRANSPARENT);
		
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/telaCliente.fxml"));
		stage.setTitle("Cliente");
		stage.setScene(new Scene(root, 1200,700,Color.TRANSPARENT));
		
		stage.show();		
		
		stage.setOnHidden(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				ControllerTelaPrincipal.telaCliente = false;
				
			}
		});
		
	}
	
	
	
}
