package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class ControllerTelaFuncionario {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="abaConsulta"
    private Tab abaConsulta; // Value injected by FXMLLoader

    @FXML // fx:id="campoCpf"
    private TableColumn<?, ?> campoCpf; // Value injected by FXMLLoader

    @FXML // fx:id="btSalvar"
    private Button btSalvar; // Value injected by FXMLLoader

    @FXML // fx:id="paneCadastro"
    private Pane paneCadastro; // Value injected by FXMLLoader

    @FXML // fx:id="paneConsulta"
    private Pane paneConsulta; // Value injected by FXMLLoader

    @FXML // fx:id="campoCelular"
    private TableColumn<?, ?> campoCelular; // Value injected by FXMLLoader

    @FXML // fx:id="tfCpf"
    private TextField tfCpf; // Value injected by FXMLLoader

    @FXML // fx:id="lbDadosFuncionario"
    private Label lbDadosFuncionario; // Value injected by FXMLLoader

    @FXML // fx:id="lbConsultaFuncionario"
    private Label lbConsultaFuncionario; // Value injected by FXMLLoader

    @FXML // fx:id="tableConsulta"
    private TableView<?> tableConsulta; // Value injected by FXMLLoader

    @FXML // fx:id="lbCpf"
    private Label lbCpf; // Value injected by FXMLLoader

    @FXML // fx:id="tfNome"
    private TextField tfNome; // Value injected by FXMLLoader

    @FXML // fx:id="tablePane"
    private TabPane tablePane; // Value injected by FXMLLoader

    @FXML // fx:id="cbPermissao"
    private ComboBox<?> cbPermissao; // Value injected by FXMLLoader

    @FXML // fx:id="btExcluir"
    private Button btExcluir; // Value injected by FXMLLoader

    @FXML // fx:id="lbPermissao"
    private Label lbPermissao; // Value injected by FXMLLoader

    @FXML // fx:id="lbCelular"
    private Label lbCelular; // Value injected by FXMLLoader

    @FXML // fx:id="btNovo"
    private Button btNovo; // Value injected by FXMLLoader

    @FXML // fx:id="paneAnchor"
    private AnchorPane paneAnchor; // Value injected by FXMLLoader

    @FXML // fx:id="tfRg"
    private TextField tfRg; // Value injected by FXMLLoader

    @FXML // fx:id="lbTelefone"
    private Label lbTelefone; // Value injected by FXMLLoader

    @FXML // fx:id="tfTelefone"
    private TextField tfTelefone; // Value injected by FXMLLoader

    @FXML // fx:id="campoTelefone"
    private TableColumn<?, ?> campoTelefone; // Value injected by FXMLLoader

    @FXML // fx:id="paneNavBar"
    private Pane paneNavBar; // Value injected by FXMLLoader

    @FXML // fx:id="abaCadastro"
    private Tab abaCadastro; // Value injected by FXMLLoader

    @FXML // fx:id="campoNome"
    private TableColumn<?, ?> campoNome; // Value injected by FXMLLoader

    @FXML // fx:id="lbRg"
    private Label lbRg; // Value injected by FXMLLoader

    @FXML // fx:id="lbNome"
    private Label lbNome; // Value injected by FXMLLoader

    @FXML // fx:id="lbFuncionarioNav"
    private Label lbFuncionarioNav; // Value injected by FXMLLoader

    @FXML // fx:id="tfCelular"
    private TextField tfCelular; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert abaConsulta != null : "fx:id=\"abaConsulta\" was not injected: check your FXML file 'telaFuncionario.fxml'.";
        assert campoCpf != null : "fx:id=\"campoCpf\" was not injected: check your FXML file 'telaFuncionario.fxml'.";
        assert btSalvar != null : "fx:id=\"btSalvar\" was not injected: check your FXML file 'telaFuncionario.fxml'.";
        assert paneCadastro != null : "fx:id=\"paneCadastro\" was not injected: check your FXML file 'telaFuncionario.fxml'.";
        assert paneConsulta != null : "fx:id=\"paneConsulta\" was not injected: check your FXML file 'telaFuncionario.fxml'.";
        assert campoCelular != null : "fx:id=\"campoCelular\" was not injected: check your FXML file 'telaFuncionario.fxml'.";
        assert tfCpf != null : "fx:id=\"tfCpf\" was not injected: check your FXML file 'telaFuncionario.fxml'.";
        assert lbDadosFuncionario != null : "fx:id=\"lbDadosFuncionario\" was not injected: check your FXML file 'telaFuncionario.fxml'.";
        assert lbConsultaFuncionario != null : "fx:id=\"lbConsultaFuncionario\" was not injected: check your FXML file 'telaFuncionario.fxml'.";
        assert tableConsulta != null : "fx:id=\"tableConsulta\" was not injected: check your FXML file 'telaFuncionario.fxml'.";
        assert lbCpf != null : "fx:id=\"lbCpf\" was not injected: check your FXML file 'telaFuncionario.fxml'.";
        assert tfNome != null : "fx:id=\"tfNome\" was not injected: check your FXML file 'telaFuncionario.fxml'.";
        assert tablePane != null : "fx:id=\"tablePane\" was not injected: check your FXML file 'telaFuncionario.fxml'.";
        assert cbPermissao != null : "fx:id=\"cbPermissao\" was not injected: check your FXML file 'telaFuncionario.fxml'.";
        assert btExcluir != null : "fx:id=\"btExcluir\" was not injected: check your FXML file 'telaFuncionario.fxml'.";
        assert lbPermissao != null : "fx:id=\"lbPermissao\" was not injected: check your FXML file 'telaFuncionario.fxml'.";
        assert lbCelular != null : "fx:id=\"lbCelular\" was not injected: check your FXML file 'telaFuncionario.fxml'.";
        assert btNovo != null : "fx:id=\"btNovo\" was not injected: check your FXML file 'telaFuncionario.fxml'.";
        assert paneAnchor != null : "fx:id=\"paneAnchor\" was not injected: check your FXML file 'telaFuncionario.fxml'.";
        assert tfRg != null : "fx:id=\"tfRg\" was not injected: check your FXML file 'telaFuncionario.fxml'.";
        assert lbTelefone != null : "fx:id=\"lbTelefone\" was not injected: check your FXML file 'telaFuncionario.fxml'.";
        assert tfTelefone != null : "fx:id=\"tfTelefone\" was not injected: check your FXML file 'telaFuncionario.fxml'.";
        assert campoTelefone != null : "fx:id=\"campoTelefone\" was not injected: check your FXML file 'telaFuncionario.fxml'.";
        assert paneNavBar != null : "fx:id=\"paneNavBar\" was not injected: check your FXML file 'telaFuncionario.fxml'.";
        assert abaCadastro != null : "fx:id=\"abaCadastro\" was not injected: check your FXML file 'telaFuncionario.fxml'.";
        assert campoNome != null : "fx:id=\"campoNome\" was not injected: check your FXML file 'telaFuncionario.fxml'.";
        assert lbRg != null : "fx:id=\"lbRg\" was not injected: check your FXML file 'telaFuncionario.fxml'.";
        assert lbNome != null : "fx:id=\"lbNome\" was not injected: check your FXML file 'telaFuncionario.fxml'.";
        assert lbFuncionarioNav != null : "fx:id=\"lbFuncionarioNav\" was not injected: check your FXML file 'telaFuncionario.fxml'.";
        assert tfCelular != null : "fx:id=\"tfCelular\" was not injected: check your FXML file 'telaFuncionario.fxml'.";

    }
}
