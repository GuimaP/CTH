package controller;

import java.net.URL;
import java.util.ResourceBundle;

import view.TelaErrosValidacao;
import model.Carro;
import model.repository.RepositoryCarro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class ControllerTelaCarro {

	private Carro carro;
	private RepositoryCarro repoCarro;

	@FXML
	// ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML
	// URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML
	// fx:id="abaConsultaCarro"
	private Tab abaConsultaCarro; // Value injected by FXMLLoader

	@FXML
	// fx:id="tfAno"
	private TextField tfAno; // Value injected by FXMLLoader

	@FXML
	// fx:id="btSalvar"
	private Button btSalvar; // Value injected by FXMLLoader

	@FXML
	// fx:id="tfPlaca"
	private TextField tfPlaca; // Value injected by FXMLLoader

	@FXML
	// fx:id="tfModelo"
	private TextField tfModelo; // Value injected by FXMLLoader

	@FXML
	// fx:id="lbAno"
	private Label lbAno; // Value injected by FXMLLoader

	@FXML
	// fx:id="abaCadastroCarros"
	private Tab abaCadastroCarros; // Value injected by FXMLLoader

	@FXML
	// fx:id="lbPlaca"
	private Label lbPlaca; // Value injected by FXMLLoader

	@FXML
	// fx:id="campoAno"
	private TableColumn<?, ?> campoAno; // Value injected by FXMLLoader

	@FXML
	// fx:id="lbListaCarro"
	private Label lbListaCarro; // Value injected by FXMLLoader

	@FXML
	// fx:id="lbMarca"
	private Label lbMarca; // Value injected by FXMLLoader

	@FXML
	// fx:id="tablePane"
	private TabPane tablePane; // Value injected by FXMLLoader

	@FXML
	// fx:id="tableCarro"
	private TableView<?> tableCarro; // Value injected by FXMLLoader

	@FXML
	// fx:id="btExcluir"
	private Button btExcluir; // Value injected by FXMLLoader

	@FXML
	// fx:id="paneCadastroCarro"
	private Pane paneCadastroCarro; // Value injected by FXMLLoader

	@FXML
	// fx:id="tfMarca"
	private TextField tfMarca; // Value injected by FXMLLoader

	@FXML
	// fx:id="campoPlaca"
	private TableColumn<?, ?> campoPlaca; // Value injected by FXMLLoader

	@FXML
	// fx:id="lbModelo"
	private Label lbModelo; // Value injected by FXMLLoader

	@FXML
	// fx:id="lbDadosCarro"
	private Label lbDadosCarro; // Value injected by FXMLLoader

	@FXML
	// fx:id="btNovo"
	private Button btNovo; // Value injected by FXMLLoader

	@FXML
	// fx:id="paneAnchor"
	private AnchorPane paneAnchor; // Value injected by FXMLLoader

	@FXML
	// fx:id="paneNaveBar"
	private Pane paneNaveBar; // Value injected by FXMLLoader

	@FXML
	// fx:id="lbCarroNavBar"
	private Label lbCarroNavBar; // Value injected by FXMLLoader

	@FXML
	// fx:id="paneConsultaCarro"
	private Pane paneConsultaCarro; // Value injected by FXMLLoader

	@FXML
	// fx:id="campoModelo"
	private TableColumn<?, ?> campoModelo; // Value injected by FXMLLoader

	@FXML
	void salvar(ActionEvent event) {
		TelaErrosValidacao erros = new TelaErrosValidacao();

		tfAno.setText(tfAno.getText().trim());
		tfMarca.setText(tfMarca.getText().trim());
		tfModelo.setText(tfModelo.getText().trim());
		tfPlaca.setText(tfPlaca.getText().trim());

		if (carro == null) {
			carro = new Carro();
		}
		if (tfMarca.getText().isEmpty()) {
			erros.erroMarca();
			tfMarca.requestFocus();
		} else if (tfModelo.getText().isEmpty()) {
			erros.erroModelo();
			tfModelo.requestFocus();
		} else if (tfAno.getText().isEmpty()) {
			erros.erroAno();
			tfAno.requestFocus();
		} else if (tfPlaca.getText().isEmpty()) {
			erros.erroPlaca();
			tfPlaca.requestFocus();
		} else {
			try {
				repoCarro = new RepositoryCarro();

				carro.setAno(Long.parseLong(tfAno.getText()));
				carro.setMarca(tfMarca.getText());
				carro.setModelo(tfModelo.getText());
				carro.setPlaca(tfPlaca.getText());
				repoCarro.adicionar(carro);
				
				limparCampos();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@FXML
	void novo(ActionEvent event) {
		limparCampos();
	}

	@FXML
	void excluir(ActionEvent event) {

	}
	
	//metodos diversos
		public void limparCampos(){
			tfAno.setText(null);
			tfMarca.setText(null);
			tfModelo.setText(null);
			tfPlaca.setText(null);
			carro = null;
		}
	
	
	//fim metodos diversos

	@FXML
	// This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert abaConsultaCarro != null : "fx:id=\"abaConsultaCarro\" was not injected: check your FXML file 'telaCarro.fxml'.";
		assert tfAno != null : "fx:id=\"tfAno\" was not injected: check your FXML file 'telaCarro.fxml'.";
		assert btSalvar != null : "fx:id=\"btSalvar\" was not injected: check your FXML file 'telaCarro.fxml'.";
		assert tfPlaca != null : "fx:id=\"tfPlaca\" was not injected: check your FXML file 'telaCarro.fxml'.";
		assert tfModelo != null : "fx:id=\"tfModelo\" was not injected: check your FXML file 'telaCarro.fxml'.";
		assert lbAno != null : "fx:id=\"lbAno\" was not injected: check your FXML file 'telaCarro.fxml'.";
		assert abaCadastroCarros != null : "fx:id=\"abaCadastroCarros\" was not injected: check your FXML file 'telaCarro.fxml'.";
		assert lbPlaca != null : "fx:id=\"lbPlaca\" was not injected: check your FXML file 'telaCarro.fxml'.";
		assert campoAno != null : "fx:id=\"campoAno\" was not injected: check your FXML file 'telaCarro.fxml'.";
		assert lbListaCarro != null : "fx:id=\"lbListaCarro\" was not injected: check your FXML file 'telaCarro.fxml'.";
		assert lbMarca != null : "fx:id=\"lbMarca\" was not injected: check your FXML file 'telaCarro.fxml'.";
		assert tablePane != null : "fx:id=\"tablePane\" was not injected: check your FXML file 'telaCarro.fxml'.";
		assert tableCarro != null : "fx:id=\"tableCarro\" was not injected: check your FXML file 'telaCarro.fxml'.";
		assert btExcluir != null : "fx:id=\"btExcluir\" was not injected: check your FXML file 'telaCarro.fxml'.";
		assert paneCadastroCarro != null : "fx:id=\"paneCadastroCarro\" was not injected: check your FXML file 'telaCarro.fxml'.";
		assert tfMarca != null : "fx:id=\"tfMarca\" was not injected: check your FXML file 'telaCarro.fxml'.";
		assert campoPlaca != null : "fx:id=\"campoPlaca\" was not injected: check your FXML file 'telaCarro.fxml'.";
		assert lbModelo != null : "fx:id=\"lbModelo\" was not injected: check your FXML file 'telaCarro.fxml'.";
		assert lbDadosCarro != null : "fx:id=\"lbDadosCarro\" was not injected: check your FXML file 'telaCarro.fxml'.";
		assert btNovo != null : "fx:id=\"btNovo\" was not injected: check your FXML file 'telaCarro.fxml'.";
		assert paneAnchor != null : "fx:id=\"paneAnchor\" was not injected: check your FXML file 'telaCarro.fxml'.";
		assert paneNaveBar != null : "fx:id=\"paneNaveBar\" was not injected: check your FXML file 'telaCarro.fxml'.";
		assert lbCarroNavBar != null : "fx:id=\"lbCarroNavBar\" was not injected: check your FXML file 'telaCarro.fxml'.";
		assert paneConsultaCarro != null : "fx:id=\"paneConsultaCarro\" was not injected: check your FXML file 'telaCarro.fxml'.";
		assert campoModelo != null : "fx:id=\"campoModelo\" was not injected: check your FXML file 'telaCarro.fxml'.";

	}
}
