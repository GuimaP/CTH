package controller.launcher;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.NoResultException;
import javax.swing.JOptionPane;

import controller.view.ControllerTelaLoad;
import main.Start;
import model.repository.ConnectionFactoryRepository;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class LauncherTelaLoad extends Application {
public static Stage stage;
public static boolean isLoading = true;
	
	@Override
	public void start(Stage owner) throws Exception {
		Stage primaryStage = new Stage();
		stage = primaryStage;
		
		primaryStage.initModality(Modality.NONE);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		primaryStage.initOwner(owner);
		
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/telaLoad.fxml"));
		
		primaryStage.setTitle("Load");
		
		//Registra um evento de carregar os Hibernate quando a tela aparece.
		primaryStage.addEventHandler(WindowEvent.WINDOW_SHOWN, e->{
			ControllerTelaLoad.hasLoadEvent();
			
		});

		primaryStage.setScene(new Scene(root, Color.TRANSPARENT));
		primaryStage.show();		
				
	}
	
	
	
}
