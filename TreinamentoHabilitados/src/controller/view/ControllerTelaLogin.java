package controller.view;

/**
 * Sample Skeleton for 'telaLogin.fxml' Controller Class
 */

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import org.controlsfx.dialog.Dialogs;





import controller.launcher.LauncherPrincipal;
import controller.launcher.LauncherTelaLogin;
//import view.Principal;
import main.Start;
import model.Funcionario;
import model.Login;
import model.repository.LoginRepository;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ControllerTelaLogin {

	private Login login;
	private LoginRepository repository;
//	private Principal telaPrincipal;

	 @FXML
	    private AnchorPane paneAnchor;

	    @FXML
	    private Button btLogin;

	    @FXML
	    private PasswordField tfSenha;

	    @FXML
	    private Label lbSenha;

	    @FXML
	    private Pane paneNavBar;

	    @FXML
	    private Label lbUsuario;

	    @FXML
	    private ImageView icoMinimize;

	    @FXML
	    private TextField tfUsuario;

	    @FXML
	    private Pane pnFundo;

	    @FXML
	    private Label lbLoginNavBar;

	    @FXML
	    private ImageView icClose;	

	protected static double xOffset =0 ;
	protected static double yOffset = 0;
	
	@FXML
	void close(MouseEvent event) {
		 Scene scene = icClose.getScene();
	        scene.getWindow().hide();
	}

	@FXML
	void minimize(MouseEvent event) {
		LauncherTelaLogin.stage.toBack();
	}

	@FXML
	void btClick(ActionEvent event) {
		authentic();
		
	}
	
	@FXML
	void pressEnter(KeyEvent evt){
		if(evt.getCode() == KeyCode.ENTER){
			authentic();
		}
	}

	@SuppressWarnings("deprecation")
	private void authentic(){
		try {

			login = new Login();
			login.setUsuario(tfUsuario.getText().trim());
			login.setSenha(tfSenha.getText());

			repository = new LoginRepository();
			
			
			if (repository.isAutentica(login)) {
				// Pegando a janela atual
				Stage window = (Stage) btLogin.getScene().getWindow();

				// Fechando a janela
				window.close();
				Funcionario f = repository.getFuncionario(login);
				
				if(login.getSenha().equals("")){
					int cont = 0;
					boolean hasSenha = false;
					while(!hasSenha || cont < 3){
					String senha1 = JOptionPane.showInputDialog(null, "Identificamos que esse � o seu primeiro login, por favor defina uma senha de acesso");
					String senha2 = JOptionPane.showInputDialog(null, "Por favor, confirme sua senha");
					if(senha1.equals(senha2)){
						hasSenha = true;
						login.setSenha(senha1);
						new LoginRepository().atualizar(login);
						break;
					}
						cont++;
					}
					//Se houver um numero de tentativas igual a 3, o programa � encerrado
					if(cont == 3){
						System.exit(0);
					}
				}
				if(f!=null){
					new LauncherPrincipal(f).start(new Stage());
				}else {
					JOptionPane.showMessageDialog(null, "N�o foi poss�vel encontrar um funcion�rio atrelado a este usuario");
					System.exit(0);
				}
//				new LauncherTelaFuncionario().start(new Stage());
				

			} else {
//				JOptionPane.showMessageDialog(null, "Senha ou usuário incorretos");
//				Dialogs.create().owner(SceneBuilderTelaPrincipal.stage)
//				.title("Erro na Autenticação").masthead("Verifique seu usuario e senha")
//				.message("Usuario ou senha n�o existem, por favor tente novamente").showWarning();
				JOptionPane.showMessageDialog(null, "Usu�rio ou senha incorretos");
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	@FXML
	// This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert paneAnchor != null : "fx:id=\"paneAnchor\" was not injected: check your FXML file 'telaLogin.fxml'.";
        assert btLogin != null : "fx:id=\"btLogin\" was not injected: check your FXML file 'telaLogin.fxml'.";
        assert tfSenha != null : "fx:id=\"tfSenha\" was not injected: check your FXML file 'telaLogin.fxml'.";
        assert lbSenha != null : "fx:id=\"lbSenha\" was not injected: check your FXML file 'telaLogin.fxml'.";
        assert paneNavBar != null : "fx:id=\"paneNavBar\" was not injected: check your FXML file 'telaLogin.fxml'.";
        assert lbUsuario != null : "fx:id=\"lbUsuario\" was not injected: check your FXML file 'telaLogin.fxml'.";
        assert icoMinimize != null : "fx:id=\"icoMinimize\" was not injected: check your FXML file 'telaLogin.fxml'.";
        assert tfUsuario != null : "fx:id=\"tfUsuario\" was not injected: check your FXML file 'telaLogin.fxml'.";
        assert pnFundo != null : "fx:id=\"pnFundo\" was not injected: check your FXML file 'telaLogin.fxml'.";
        assert lbLoginNavBar != null : "fx:id=\"lbLoginNavBar\" was not injected: check your FXML file 'telaLogin.fxml'.";
        assert icClose != null : "fx:id=\"icClose\" was not injected: check your FXML file 'telaLogin.fxml'.";

		// btLogin.getScene().getStylesheets().add("https://fonts.googleapis.com/css?family=Lobster");
		Font.loadFont(
				ControllerTelaLogin.class.getResource("/fonts/Lobster.ttf")
						.toExternalForm(), 12);
		lbLoginNavBar.setStyle("-fx-font-family:Lobster");
		paneAnchor.setStyle("-fx-background-color: null");
		
		paneNavBar.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = LauncherTelaLogin.stage.getX() - event.getScreenX();
                yOffset = LauncherTelaLogin.stage.getY() - event.getScreenY();
            }
        });
		
		paneNavBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                LauncherTelaLogin.stage.setX(event.getScreenX() + xOffset);
                LauncherTelaLogin.stage.setY(event.getScreenY() + yOffset);
            }
        });
	}
	
}
