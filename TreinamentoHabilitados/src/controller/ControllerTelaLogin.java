package controller; 

/**
 * Sample Skeleton for 'telaLogin.fxml' Controller Class
 */

import java.net.URL;
import java.util.ResourceBundle;

import view.Principal;
import main.Start;
import model.Login;
import model.repository.LoginRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ControllerTelaLogin {
	
	private Login login;
	private LoginRepository repository;
	private Principal telaPrincipal;
	
	
	

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="paneAnchor"
    private AnchorPane paneAnchor; // Value injected by FXMLLoader

    @FXML // fx:id="btLogin"
    private Button btLogin; // Value injected by FXMLLoader

    @FXML // fx:id="tfSenha"
    private PasswordField tfSenha; // Value injected by FXMLLoader

    @FXML // fx:id="lbSenha"
    private Label lbSenha; // Value injected by FXMLLoader

    @FXML // fx:id="paneNavBar"
    private Pane paneNavBar; // Value injected by FXMLLoader

    @FXML // fx:id="lbUsuario"
    private Label lbUsuario; // Value injected by FXMLLoader

    @FXML // fx:id="tfUsuario"
    private TextField tfUsuario; // Value injected by FXMLLoader

    @FXML // fx:id="lbLoginNavBar"
    private Label lbLoginNavBar; // Value injected by FXMLLoader

    @FXML
    void btClick(ActionEvent event) {
    	try {
			
    		login = new Login();
    		login.setUsuario(tfUsuario.getText().trim());
    		login.setSenha(String.valueOf(tfSenha.getText()));
    		
    		repository = new LoginRepository();
    		
    		if (repository.isAutentica(login)) {
    			
    			
    			new SceneBuilderTelaPrincipal().start(new Stage());
    			
    			
    			
    			
    			
    			
				
			} else {

			}
    		
    		
    		
    		
		} catch (Exception e) {
			// TODO: handle exception
		} 
    	
    	 
    	
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert paneAnchor != null : "fx:id=\"paneAnchor\" was not injected: check your FXML file 'telaLogin.fxml'.";
        assert btLogin != null : "fx:id=\"btLogin\" was not injected: check your FXML file 'telaLogin.fxml'.";
        assert tfSenha != null : "fx:id=\"tfSenha\" was not injected: check your FXML file 'telaLogin.fxml'.";
        assert lbSenha != null : "fx:id=\"lbSenha\" was not injected: check your FXML file 'telaLogin.fxml'.";
        assert paneNavBar != null : "fx:id=\"paneNavBar\" was not injected: check your FXML file 'telaLogin.fxml'.";
        assert lbUsuario != null : "fx:id=\"lbUsuario\" was not injected: check your FXML file 'telaLogin.fxml'.";
        assert tfUsuario != null : "fx:id=\"tfUsuario\" was not injected: check your FXML file 'telaLogin.fxml'.";
        assert lbLoginNavBar != null : "fx:id=\"lbLoginNavBar\" was not injected: check your FXML file 'telaLogin.fxml'.";

    }
}
