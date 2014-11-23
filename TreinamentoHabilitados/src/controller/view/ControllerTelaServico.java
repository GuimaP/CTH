package controller.view;

import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import controller.ControllerDecimalNumbers;
import controller.ControllerOnlyNumbers;
import controller.OnlyNumbers;
import controller.events.EventsText;
import controller.events.MaskFieldUtil;
import controller.launcher.LauncherTelaServico;
import controller.view.ControllerTelaCarro.ItensProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import model.Funcionario;
import model.Servico;
import model.repository.RepositoryServico;
import controller.*;
public class ControllerTelaServico {

	private RepositoryServico repoServico;
	private Servico servico;
	private EventsText upper;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Label lbPrecoAula;

	@FXML
	private Label lbPrecoPacote;

	@FXML
	private TextField tfDescPacote;

	@FXML
	private Label lbPacoteNavBar;

	@FXML
	private Button btSalvar;

	@FXML
	private Pane paneCadastro;

	@FXML
	private TextField tfPrecoAula;

	@FXML
	private Pane paneConsulta;

	@FXML
	private Label lbListaPacotes;

	@FXML
	private TableColumn<ItensProperty, String> campoPreco;

	@FXML
	private TableColumn<ItensProperty, String> campoDescPacotes;

	@FXML
	private TableView<ItensProperty> tableConsulta;

	@FXML
	private TableColumn<ItensProperty, Integer> campoAulas;

	@FXML
	private TabPane tablePane;

	@FXML
	private Tab abaConsultaPacote;

	@FXML
	private Tab abaCadastroPacotes;

	@FXML
	private Label lbDescPacote;

	@FXML
	private Label lbDadosPacote;

	@FXML
	private Button btNovo;

	@FXML
	private TextField tfPrecoPacote;

	@FXML
	private AnchorPane paneAnchor;

	@FXML
	private ImageView icLupa;

	@FXML
	private Label lbAulas;

	@FXML
	private TextField tfAulas;

	@FXML
	private Pane PnFundo;

	@FXML
	private Pane paneNavBar;

	@FXML
	private TextField tfBusca;

	@FXML
	private ImageView icClose;

	@FXML
	void close(MouseEvent event) {
		paneAnchor.getScene().getWindow().hide();
		;
		System.out.println("Bye!");
	}

	protected double xOffset;
	protected double yOffset;
	
	//Variavel que guarda o valor do pacote
	private double valorPacote;

	@FXML
	void salvar(ActionEvent event) {
		ControllerErros erros = new ControllerErros();

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
				
				if(servico == null){
					servico = new Servico();
					popularObjeto();
					repoServico.adicionar(servico);
				}else {
					popularObjeto();
					repoServico.atualizar(servico);
					clearSelectionInTable();
				}

				limparCampos();
				populaTableView();

				System.out.println("passei");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	private void popularObjeto(){
		String valor = tfPrecoAula.getText();
		if(valor.contains(".")){
			String tempValor;
			tempValor = valor.replace('.', ' ');
			valor = tempValor;
			tempValor = "";
			//Formatando o valor monetario para converter em Double
			for(char c : valor.toCharArray()) {
				if(c == ','){
					c= '.';
				}
				if(c != ' '){
					tempValor+= c ;
				}
			}
			valor = tempValor;
			double t = Double.parseDouble(valor);
			
		}
		valor = valor.replaceAll(",", ".");
		
		servico.setDescricao(tfDescPacote.getText());
		servico.setPrecoPacote(valorPacote);
		servico.setPrecoAula(Double.parseDouble(valor));
		servico.setAulas(Integer.parseInt(tfAulas.getText()));
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
		tfAulas.setText("");
		tfDescPacote.setText("");
		tfPrecoAula.setText("");
		tfPrecoPacote.setText("");

		servico = null;
	}

	@FXML
	void buscaCPF(ActionEvent event) {

	}

	// fim metodos diversos

	// Fim keyEvent

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
		assert abaCadastroPacotes != null : "fx:id=\"abaCadastroPacotes\" was not injected: check your FXML file 'telaServico.fxml'.";
		assert lbDescPacote != null : "fx:id=\"lbDescPacote\" was not injected: check your FXML file 'telaServico.fxml'.";
		assert lbDadosPacote != null : "fx:id=\"lbDadosPacote\" was not injected: check your FXML file 'telaServico.fxml'.";
		assert btNovo != null : "fx:id=\"btNovo\" was not injected: check your FXML file 'telaServico.fxml'.";
		assert tfPrecoPacote != null : "fx:id=\"tfPrecoPacote\" was not injected: check your FXML file 'telaServico.fxml'.";
		assert paneAnchor != null : "fx:id=\"paneAnchor\" was not injected: check your FXML file 'telaServico.fxml'.";
		assert icLupa != null : "fx:id=\"icLupa\" was not injected: check your FXML file 'telaServico.fxml'.";
		assert lbAulas != null : "fx:id=\"lbAulas\" was not injected: check your FXML file 'telaServico.fxml'.";
		assert tfAulas != null : "fx:id=\"tfAulas\" was not injected: check your FXML file 'telaServico.fxml'.";
		assert PnFundo != null : "fx:id=\"PnFundo\" was not injected: check your FXML file 'telaServico.fxml'.";
		assert paneNavBar != null : "fx:id=\"paneNavBar\" was not injected: check your FXML file 'telaServico.fxml'.";
		assert tfBusca != null : "fx:id=\"tfBusca\" was not injected: check your FXML file 'telaServico.fxml'.";
		assert icClose != null : "fx:id=\"icClose\" was not injected: check your FXML file 'telaServico.fxml'.";

		

		tfAulas.addEventHandler(KeyEvent.KEY_TYPED, new OnlyNumbers());
		tfAulas.addEventFilter(KeyEvent.KEY_TYPED, new ControllerOnlyNumbers());
		tfPrecoAula.addEventHandler(KeyEvent.KEY_TYPED, new ControllerDecimalNumbers());
		tfPrecoAula.focusedProperty().addListener(new ControllerDecimalNumbers(tfPrecoAula));

		EventsText.focusUpperLost(tfDescPacote);
		EventsText.upperCase(tfDescPacote);

		tfPrecoAula.setText(tfPrecoAula.getText().trim());
		tfPrecoPacote.setDisable(true);
		tfPrecoAula.setOnKeyReleased(new EventHandler<KeyEvent>() {
			

			@Override
			public void handle(KeyEvent e) {
				if (e.getCode() == KeyCode.BACK_SPACE && tfPrecoAula.getText().length() == 0 ) {
					tfPrecoAula.setText("");
					tfPrecoPacote.setText("0");
				} else if (e.getCode() == KeyCode.TAB && tfPrecoAula.getText().isEmpty()){ 
					tfPrecoAula.setText("");
					tfPrecoPacote.setText("0");
				}else {
					calcularValorPacote();
				}

			}
		});
		MaskFieldUtil.monetaryField(tfPrecoAula);

		paneNavBar.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				xOffset = LauncherTelaServico.stage.getX()
						- event.getScreenX();
				yOffset = LauncherTelaServico.stage.getY()
						- event.getScreenY();
			}
		});

		paneNavBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				LauncherTelaServico.stage.setX(event.getScreenX() + xOffset);
				LauncherTelaServico.stage.setY(event.getScreenY() + yOffset);
			}
		});
		populaTableView();
		
		
		//Implementando Cliq com botão direito
		tableConsulta.addEventHandler(MouseEvent.MOUSE_CLICKED,
				new EventHandler<MouseEvent>() {
			ContextMenu cm = new ContextMenu();
			MenuItem mi1;
			MenuItem mi2;

			@Override
			public void handle(MouseEvent t) {
				if (t.getButton() == MouseButton.SECONDARY) {

					ItensProperty func = tableConsulta
							.getSelectionModel().getSelectedItem();
					servico = func.getS();
					
					cm.getItems().clear();
					mi1 = new MenuItem("Alterar");
					cm.getItems().add(mi1);
					mi2 = new MenuItem("Excluir");
					cm.getItems().add(mi2);

					mi1.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent evt) {
//							Funcionario f = func.getS();
							tablePane.getSelectionModel().select(0);
							tfAulas.setText(String.valueOf(servico.getAulas()));
							tfDescPacote.setText(servico.getDescricao());
							tfPrecoAula.setText(String.valueOf(servico.getPrecoAula()));
							tfPrecoPacote.setText(String.valueOf(servico.getMonetaryMoney()));
							tableConsulta.getSelectionModel()
									.clearSelection();
						}
					});

					mi2.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent evt) {
							try{
								repoServico.deleteServico(servico);
								populaTableView();
								limparCampos();
							}catch(Exception e){
								e.printStackTrace();
								JOptionPane.showMessageDialog(null, e.getMessage());
							}
						}
					});
					
					cm.show(tableConsulta, t.getScreenX(),
							t.getScreenY());
				} else if (t.getButton() == MouseButton.PRIMARY) {
					cm.hide();
				}
			}
		});
		
	}
	
	private void calcularValorPacote(){
		String valor = tfPrecoAula.getText();
		if(valor.contains(".")){
			String tempValor;
			tempValor = valor.replace('.', ' ');
			valor = tempValor;
			tempValor = "";
			//Formatando o valor monetario para converter em Double
			for(char c : valor.toCharArray()) {
				if(c == ','){
					c= '.';
				}
				if(c != ' '){
					tempValor+= c ;
				}
			}
			valor = tempValor;
			double t = Double.parseDouble(valor);
			
		}
		valor = valor.replaceAll(",", ".");
		
		double numAulas = Double.parseDouble(tfAulas.getText());
		double precoAula = Double.parseDouble(valor);
		double result = (numAulas * precoAula);
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		//Formata para valor monetario
		tfPrecoPacote.setText(formatter.format(result));
		//E guarda o valor em DOUBLE em um atributo da classe para persistir
		valorPacote = result;
	}
	
	private void clearSelectionInTable(){
		ObservableList<ItensProperty> ls = tableConsulta.getItems();
		tableConsulta.getItems().clear();
		tableConsulta.setItems(ls);
	}
	
	public void populaTableView(){
		repoServico = new RepositoryServico();
		ObservableList<Servico> listServico = FXCollections.observableArrayList();
		ObservableList<ItensProperty> itens = FXCollections.observableArrayList();
		
		tableConsulta.getItems().clear();
		
		campoAulas.setCellValueFactory(new PropertyValueFactory<ItensProperty, Integer>("aulas"));
		campoDescPacotes.setCellValueFactory(new PropertyValueFactory<ItensProperty, String>("Descricao"));
		campoPreco.setCellValueFactory(new PropertyValueFactory<ItensProperty, String>("PrecoPacote"));		
		try {
			listServico = repoServico.buscaServicoTeste();
			for(Servico s : listServico){
				itens.add(new ItensProperty(s));
				tableConsulta.setItems(itens);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}
	
	
	public class ItensProperty {
		private Servico s;		
		private SimpleIntegerProperty aulas = new SimpleIntegerProperty();
		private SimpleStringProperty descricao = new SimpleStringProperty("");
		private SimpleStringProperty precoPacote = new SimpleStringProperty();
	
		public ItensProperty(Servico s) {
			this.s = s;
			this.setAulas(s.getAulas());
			this.setDescricao(s.getDescricao());
			this.setPrecoPacote(s.getMonetaryMoney());
		}				
		
		
		public Servico getS() {
			return s;
		}
		public void setS(Servico s) {
			this.s = s;
		}
		public int getAulas() {
			return aulas.get();
		}
		public void setAulas(Integer aulas) {
			this.aulas.set(aulas);
		}
		public String getDescricao() {
			return descricao.get();
		}
		public void setDescricao(String descricao) {
			this.descricao.set(descricao);
		}
		public String getPrecoPacote() {
			return precoPacote.get();
		}
		public void setPrecoPacote(String precoPacote) {
			this.precoPacote.set(precoPacote);
		}
		
		
		
		
	}
}
