package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import model.Servico;
import model.repository.RepositoryServico;
import view.TelaErrosValidacao;


public class ControllerTelaServico {
	
	private RepositoryServico repoServico;
	private Servico servico;
	

	
	
	@FXML
	// ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML
	// URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML
	// fx:id="lbPrecoAula"
	private Label lbPrecoAula; // Value injected by FXMLLoader

	@FXML
	// fx:id="lbPrecoPacote"
	private Label lbPrecoPacote; // Value injected by FXMLLoader

	@FXML
	// fx:id="tfDescPacote"
	private TextField tfDescPacote; // Value injected by FXMLLoader

	@FXML
	// fx:id="lbPacoteNavBar"
	private Label lbPacoteNavBar; // Value injected by FXMLLoader

	@FXML
	// fx:id="btSalvar"
	private Button btSalvar; // Value injected by FXMLLoader

	@FXML
	// fx:id="paneCadastro"
	private Pane paneCadastro; // Value injected by FXMLLoader

	@FXML
	// fx:id="tfPrecoAula"
	private TextField tfPrecoAula; // Value injected by FXMLLoader

	@FXML
	// fx:id="paneConsulta"
	private Pane paneConsulta; // Value injected by FXMLLoader

	@FXML
	// fx:id="lbListaPacotes"
	private Label lbListaPacotes; // Value injected by FXMLLoader

	@FXML
	// fx:id="campoPreco"
	private TableColumn<?, ?> campoPreco; // Value injected by FXMLLoader

	@FXML
	// fx:id="campoDescPacotes"
	private TableColumn<?, ?> campoDescPacotes; // Value injected by FXMLLoader

	@FXML
	// fx:id="tableConsulta"
	private TableView<Servico> tableConsulta; // Value injected by FXMLLoader

	@FXML
	// fx:id="campoAulas"
	private TableColumn<?, ?> campoAulas; // Value injected by FXMLLoader

	@FXML
	// fx:id="tablePane"
	private TabPane tablePane; // Value injected by FXMLLoader

	@FXML
	// fx:id="abaConsultaPacote"
	private Tab abaConsultaPacote; // Value injected by FXMLLoader

	@FXML
	// fx:id="btExcluir"
	private Button btExcluir; // Value injected by FXMLLoader

	@FXML
	// fx:id="abaCadastroPacotes"
	private Tab abaCadastroPacotes; // Value injected by FXMLLoader

	@FXML
	// fx:id="lbDescPacote"
	private Label lbDescPacote; // Value injected by FXMLLoader

	@FXML
	// fx:id="lbDadosPacote"
	private Label lbDadosPacote; // Value injected by FXMLLoader

	@FXML
	// fx:id="btNovo"
	private Button btNovo; // Value injected by FXMLLoader

	@FXML
	// fx:id="tfPrecoPacote"
	private TextField tfPrecoPacote; // Value injected by FXMLLoader

	@FXML
	// fx:id="paneAnchor"
	private AnchorPane paneAnchor; // Value injected by FXMLLoader

	@FXML
	// fx:id="lbAulas"
	private Label lbAulas; // Value injected by FXMLLoader

	@FXML
	// fx:id="tfAulas"
	private TextField tfAulas; // Value injected by FXMLLoader

	@FXML
	// fx:id="paneNavBar"
	private Pane paneNavBar; // Value injected by FXMLLoader

	@FXML
	void salvar(ActionEvent event) {
		TelaErrosValidacao erros = new TelaErrosValidacao();
		
		if (servico == null) {
			servico = new Servico();
		}
		tfAulas.setText(tfAulas.getText().trim());
		tfDescPacote.setText(tfDescPacote.getText().trim());
		tfPrecoAula.setText(tfPrecoAula.getText().trim());
		tfPrecoPacote.setText(tfPrecoPacote.getText().trim());

		if (tfAulas.getText().isEmpty()) {
			erros.erroNumeroAulas();
			tfAulas.requestFocus();
		} else if (tfDescPacote.getText().isEmpty()) {
			erros.erroDescPacote();
			tfDescPacote.requestFocus();
		} else if (tfPrecoAula.getText().isEmpty()) {
			erros.erroPrecoAula();
			tfPrecoAula.requestFocus();
		} else if (tfPrecoPacote.getText().isEmpty()) {
			System.out.println("Passei preco pacote");
			tfPrecoPacote.requestFocus();
		} else {
			repoServico = new RepositoryServico();
			try {
				servico.setDescricao(tfDescPacote.getText());
				servico.setPrecoPacote(Double.parseDouble(tfPrecoPacote
						.getText()));
				servico.setPrecoAula(Double.parseDouble(tfPrecoAula.getText()));
				servico.setAulas(Integer.parseInt(tfAulas.getText()));

				repoServico.adicionar(servico);

				limparCampos();

				System.out.println("passei");
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

	// metodos diversos

	public void limparCampos() {
		tfAulas.setText(null);
		tfDescPacote.setText(null);
		tfPrecoAula.setText(null);
		tfPrecoPacote.setText(null);

		servico = null;
	}

	// fim metodos diversos
	
	
	//Fim keyEvent

	@FXML
	// This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert lbPrecoAula != null : "fx:id=\"lbPrecoAula\" was not injected: check your FXML file 'telaServico.fxml'.";
		assert lbPrecoPacote != null : "fx:id=\"lbPrecoPacote\" was not injected: check your FXML file 'telaServico.fxml'.";
		assert tfDescPacote != null : "fx:id=\"tfDescPacote\" was not injected: check your FXML file 'telaServico.fxml'.";
		assert lbPacoteNavBar != null : "fx:id=\"lbPacoteNavBar\" was not injected: check your FXML file 'telaServico.fxml'.";
		assert btSalvar != null : "fx:id=\"btSalvar\" was not injected: check your FXML file 'telaServico.fxml'.";
		assert paneCadastro != null : "fx:id=\"paneCadastro\" was not injected: check your FXML file 'telaServico.fxml'.";
		assert tfPrecoAula != null : "fx:id=\"tfPrecoAula\" was not injected: check your FXML file 'telaServico.fxml'.";
		assert paneConsulta != null : "fx:id=\"paneConsulta\" was not injected: check your FXML file 'telaServico.fxml'.";
		assert lbListaPacotes != null : "fx:id=\"lbListaPacotes\" was not injected: check your FXML file 'telaServico.fxml'.";
		assert campoPreco != null : "fx:id=\"campoPreco\" was not injected: check your FXML file 'telaServico.fxml'.";
		assert campoDescPacotes != null : "fx:id=\"campoDescPacotes\" was not injected: check your FXML file 'telaServico.fxml'.";
		assert tableConsulta != null : "fx:id=\"tableConsulta\" was not injected: check your FXML file 'telaServico.fxml'.";
		assert campoAulas != null : "fx:id=\"campoAulas\" was not injected: check your FXML file 'telaServico.fxml'.";
		assert tablePane != null : "fx:id=\"tablePane\" was not injected: check your FXML file 'telaServico.fxml'.";
		assert abaConsultaPacote != null : "fx:id=\"abaConsultaPacote\" was not injected: check your FXML file 'telaServico.fxml'.";
		assert btExcluir != null : "fx:id=\"btExcluir\" was not injected: check your FXML file 'telaServico.fxml'.";
		assert abaCadastroPacotes != null : "fx:id=\"abaCadastroPacotes\" was not injected: check your FXML file 'telaServico.fxml'.";
		assert lbDescPacote != null : "fx:id=\"lbDescPacote\" was not injected: check your FXML file 'telaServico.fxml'.";
		assert lbDadosPacote != null : "fx:id=\"lbDadosPacote\" was not injected: check your FXML file 'telaServico.fxml'.";
		assert btNovo != null : "fx:id=\"btNovo\" was not injected: check your FXML file 'telaServico.fxml'.";
		assert tfPrecoPacote != null : "fx:id=\"tfPrecoPacote\" was not injected: check your FXML file 'telaServico.fxml'.";
		assert paneAnchor != null : "fx:id=\"paneAnchor\" was not injected: check your FXML file 'telaServico.fxml'.";
		assert lbAulas != null : "fx:id=\"lbAulas\" was not injected: check your FXML file 'telaServico.fxml'.";
		assert tfAulas != null : "fx:id=\"tfAulas\" was not injected: check your FXML file 'telaServico.fxml'.";
		assert paneNavBar != null : "fx:id=\"paneNavBar\" was not injected: check your FXML file 'telaServico.fxml'.";

	}
}
