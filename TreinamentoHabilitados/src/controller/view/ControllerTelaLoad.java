package controller.view;

import java.awt.event.FocusEvent;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import controller.launcher.LauncherTelaLogin;
import model.repository.ConnectionFactoryRepository;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ControllerTelaLoad {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView icPontos;

    @FXML
    private Pane paneFundo;

    @FXML
    private Label lbCarregadno;

    @FXML
    private ImageView icImgWait;

    protected static Pane pane; 
    
    @FXML
    void initialize() {
        assert icPontos != null : "fx:id=\"icPontos\" was not injected: check your FXML file 'telaLoad.fxml'.";
        assert paneFundo != null : "fx:id=\"paneFundo\" was not injected: check your FXML file 'telaLoad.fxml'.";
        assert lbCarregadno != null : "fx:id=\"lbCarregadno\" was not injected: check your FXML file 'telaLoad.fxml'.";
        assert icImgWait != null : "fx:id=\"icImgWait\" was not injected: check your FXML file 'telaLoad.fxml'.";
        
        pane = paneFundo;
        
        Font.loadFont(
				ControllerTelaLogin.class.getResource("/fonts/Lobster.ttf")
						.toExternalForm(), 12);
        
        System.out.println(paneFundo.getScene());
        
        System.out.println("Load");
    }
    
    
    //Metodo executado quando a tela de login aparece
    public static void hasLoadEvent(){
    	new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					if (ConnectionFactoryRepository.getManager() == null)
						throw new Exception("");
					
					Thread.sleep(1000);
					initLogin();
				} catch (Exception e) {
					System.out.println("TEste");
					e.printStackTrace();
				}
			}
		}).start();
	}
    
    public static void initLogin(){
			Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					try {
						pane.getScene().getWindow().hide();	
						new LauncherTelaLogin().start(new Stage());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			});
			
    }
}
