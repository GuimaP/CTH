package controller;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class SceneBuilderTelaServico extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/telaServico.fxml"));
		primaryStage.setTitle("Pacote");
		primaryStage.setScene(new Scene(root, 600,350));
		primaryStage.show();
		
	
	
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				ControllerTelaPrincipal.telaServico = false;
				
			}
		});
	}	
}
