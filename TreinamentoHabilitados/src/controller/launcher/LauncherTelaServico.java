package controller.launcher;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

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

public class LauncherTelaServico extends Application{

	public static Stage stage;

	@Override
	public void start(Stage owner) throws Exception {
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/telaServico.fxml"));
		stage = primaryStage;
		primaryStage.initModality(Modality.NONE);
		primaryStage.initOwner(owner);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		
		primaryStage.setTitle("Pacote");
		primaryStage.setScene(new Scene(root,Color.TRANSPARENT));
		primaryStage.show();
		
	
	
		primaryStage.setOnHidden(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				ControllerTelaPrincipal.telaServico = false;
				
			}
		});
	}	
}
