package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import model.Carro;
import model.Funcionario;
import model.repository.RepositoryCarro;
import model.repository.RepositoryInstrutor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class ControllerTelaInstrutor {

	private Funcionario funcionario;
	private RepositoryInstrutor repoInstrutor;
	private Carro carro;
	private RepositoryCarro repoCarro;

	public ControllerTelaInstrutor() {
		try {
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@FXML
	// ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML
	// URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML
	// fx:id="abaConsulta"
	private Tab abaConsulta; // Value injected by FXMLLoader

	@FXML
	// fx:id="lbCarro"
	private Label lbCarro; // Value injected by FXMLLoader

	@FXML
	// fx:id="campoTel"
	private TableColumn<?, ?> campoTel; // Value injected by FXMLLoader

	@FXML
	// fx:id="campoCpf"
	private TableColumn<?, ?> campoCpf; // Value injected by FXMLLoader

	@FXML
	// fx:id="btFoto"
	private Button btFoto; // Value injected by FXMLLoader

	@FXML
	// fx:id="dtDataPermissao"
	private DatePicker dtDataPermissao; // Value injected by FXMLLoader

	@FXML
	// fx:id="btSalvar"
	private Button btSalvar; // Value injected by FXMLLoader

	@FXML
	// fx:id="tabePane"
	private TabPane tabePane; // Value injected by FXMLLoader

	@FXML
	// fx:id="lbDadosIstrutor"
	private Label lbDadosIstrutor; // Value injected by FXMLLoader

	@FXML
	// fx:id="tfNumeroCnh"
	private TextField tfNumeroCnh; // Value injected by FXMLLoader

	@FXML
	// fx:id="lbData"
	private Label lbData; // Value injected by FXMLLoader

	@FXML
	// fx:id="lbListaInstrutores"
	private Label lbListaInstrutores; // Value injected by FXMLLoader

	@FXML
	// fx:id="lbNumCnh"
	private Label lbNumCnh; // Value injected by FXMLLoader

	@FXML
	// fx:id="cbCarro"
	private ComboBox<Carro> cbCarro; // Value injected by FXMLLoader

	@FXML
	// fx:id="tfCpf"
	private TextField tfCpf; // Value injected by FXMLLoader

	@FXML
	// fx:id="cbStatus"
	private ComboBox<?> cbStatus; // Value injected by FXMLLoader

	@FXML
	// fx:id="lbTel"
	private Label lbTel; // Value injected by FXMLLoader

	@FXML
	// fx:id="tfTel"
	private TextField tfTel; // Value injected by FXMLLoader

	@FXML
	// fx:id="lbInstrutorNavBar"
	private Label lbInstrutorNavBar; // Value injected by FXMLLoader

	@FXML
	// fx:id="lbCpf"
	private Label lbCpf; // Value injected by FXMLLoader

	@FXML
	// fx:id="lbStatus"
	private Label lbStatus; // Value injected by FXMLLoader

	@FXML
	// fx:id="tfNome"
	private TextField tfNome; // Value injected by FXMLLoader

	@FXML
	// fx:id="lbDataPermissao"
	private Label lbDataPermissao; // Value injected by FXMLLoader

	@FXML
	// fx:id="paneDadosInstrutor"
	private Pane paneDadosInstrutor; // Value injected by FXMLLoader

	@FXML
	// fx:id="btExcluir"
	private Button btExcluir; // Value injected by FXMLLoader

	@FXML
	// fx:id="dtDataValidadeCnh"
	private DatePicker dtDataValidadeCnh; // Value injected by FXMLLoader

	@FXML
	// fx:id="paneFoto"
	private Pane paneFoto; // Value injected by FXMLLoader

	@FXML
	// fx:id="tfCel"
	private TextField tfCel; // Value injected by FXMLLoader

	@FXML
	// fx:id="lbCel"
	private Label lbCel; // Value injected by FXMLLoader

	@FXML
	// fx:id="lbValidade"
	private Label lbValidade; // Value injected by FXMLLoader

	@FXML
	// fx:id="btNovo"
	private Button btNovo; // Value injected by FXMLLoader

	@FXML
	// fx:id="paneAnchor"
	private AnchorPane paneAnchor; // Value injected by FXMLLoader

	@FXML
	// fx:id="tfRg"
	private TextField tfRg; // Value injected by FXMLLoader

	@FXML
	// fx:id="campoCel"
	private TableColumn<?, ?> campoCel; // Value injected by FXMLLoader

	@FXML
	// fx:id="paneTableBusca"
	private Pane paneTableBusca; // Value injected by FXMLLoader

	@FXML
	// fx:id="paneNavBar"
	private Pane paneNavBar; // Value injected by FXMLLoader

	@FXML
	// fx:id="abaCadastro"
	private Tab abaCadastro; // Value injected by FXMLLoader

	@FXML
	// fx:id="tableBuscaInstrutores"
	private TableView<?> tableBuscaInstrutores; // Value injected by FXMLLoader

	@FXML
	// fx:id="campoNome"
	private TableColumn<?, ?> campoNome; // Value injected by FXMLLoader

	@FXML
	// fx:id="lbRg"
	private Label lbRg; // Value injected by FXMLLoader

	@FXML
	// fx:id="lbNome"
	private Label lbNome; // Value injected by FXMLLoader

	@FXML
	// fx:id="dtData"
	private DatePicker dtData; // Value injected by FXMLLoader

	@FXML
	void foto(ActionEvent event) {

	}

	@FXML
	void novo(ActionEvent event) {

	}

	@FXML
	void salvar(ActionEvent event) {

		try {

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@FXML
	void excluir(ActionEvent event) {

	}

	@FXML
	// This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert abaConsulta != null : "fx:id=\"abaConsulta\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert lbCarro != null : "fx:id=\"lbCarro\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert campoTel != null : "fx:id=\"campoTel\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert campoCpf != null : "fx:id=\"campoCpf\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert btFoto != null : "fx:id=\"btFoto\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert dtDataPermissao != null : "fx:id=\"dtDataPermissao\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert btSalvar != null : "fx:id=\"btSalvar\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert tabePane != null : "fx:id=\"tabePane\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert lbDadosIstrutor != null : "fx:id=\"lbDadosIstrutor\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert tfNumeroCnh != null : "fx:id=\"tfNumeroCnh\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert lbData != null : "fx:id=\"lbData\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert lbListaInstrutores != null : "fx:id=\"lbListaInstrutores\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert lbNumCnh != null : "fx:id=\"lbNumCnh\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert cbCarro != null : "fx:id=\"cbCarro\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert tfCpf != null : "fx:id=\"tfCpf\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert cbStatus != null : "fx:id=\"cbStatus\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert lbTel != null : "fx:id=\"lbTel\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert tfTel != null : "fx:id=\"tfTel\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert lbInstrutorNavBar != null : "fx:id=\"lbInstrutorNavBar\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert lbCpf != null : "fx:id=\"lbCpf\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert lbStatus != null : "fx:id=\"lbStatus\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert tfNome != null : "fx:id=\"tfNome\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert lbDataPermissao != null : "fx:id=\"lbDataPermissao\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert paneDadosInstrutor != null : "fx:id=\"paneDadosInstrutor\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert btExcluir != null : "fx:id=\"btExcluir\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert dtDataValidadeCnh != null : "fx:id=\"dtDataValidadeCnh\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert paneFoto != null : "fx:id=\"paneFoto\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert tfCel != null : "fx:id=\"tfCel\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert lbCel != null : "fx:id=\"lbCel\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert lbValidade != null : "fx:id=\"lbValidade\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert btNovo != null : "fx:id=\"btNovo\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert paneAnchor != null : "fx:id=\"paneAnchor\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert tfRg != null : "fx:id=\"tfRg\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert campoCel != null : "fx:id=\"campoCel\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert paneTableBusca != null : "fx:id=\"paneTableBusca\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert paneNavBar != null : "fx:id=\"paneNavBar\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert abaCadastro != null : "fx:id=\"abaCadastro\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert tableBuscaInstrutores != null : "fx:id=\"tableBuscaInstrutores\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert campoNome != null : "fx:id=\"campoNome\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert lbRg != null : "fx:id=\"lbRg\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert lbNome != null : "fx:id=\"lbNome\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert dtData != null : "fx:id=\"dtData\" was not injected: check your FXML file 'telaInstrutor.fxml'.";

		try {
			repoCarro = new RepositoryCarro();
			ObservableList<Carro> listCarro = FXCollections
					.observableArrayList();

			listCarro = repoCarro.buscaCarroTeste();

			cbCarro.setItems(listCarro);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
