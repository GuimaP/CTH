import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class ControllerTelaLogin {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane paneAnchor;

    @FXML
    private Button btLogin;

    @FXML
    private TextField tfSenha;

    @FXML
    private Label lbSenha;

    @FXML
    private Pane paneNavBar;

    @FXML
    private Label lbUsuario;

    @FXML
    private TextField tfUsuario;

    @FXML
    private Label lbLoginNavBar;

    @FXML
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
