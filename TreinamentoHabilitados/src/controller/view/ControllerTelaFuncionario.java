package controller.view;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;
import controller.*;
import controller.ControllerRg;
import controller.VerificadorDeCpf;
import controller.events.EventsText;
import model.Funcionario;
import model.Instrutor;
import model.Login;
import model.enums.EnumPermissaoAcessoSistema;
import model.repository.LoginRepository;
import model.repository.RepositoryFuncionario;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import controller.launcher.LauncherTelaFuncionario;
import controller.mask.*;
import controller.view.ControllerTelaInstrutor.ItensProperty;

public class ControllerTelaFuncionario {
	private RepositoryFuncionario repo;
	private Funcionario funcionario;
	private VerificadorDeCpf validadorCpf;
	private ControllerErros erros;
	private EventsText upperCase;
	private LoginRepository repoLogin;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Tab abaConsulta;

	@FXML
	private TableColumn<ItensProperty, String> campoCpf;

	@FXML
	private Button btSalvar;

	@FXML
	private Pane paneCadastro;

	@FXML
	private Pane paneConsulta;

	@FXML
	private TableColumn<ItensProperty, String> campoCelular;

	@FXML
	private TextField tfCpf;

	@FXML
	private Label lbDadosFuncionario;

	@FXML
	private Label lbConsultaFuncionario;

	@FXML
	private TableView<ItensProperty> tableConsulta;

	@FXML
	private Label lbCpf;

	@FXML
	private ImageView close;

	@FXML
	private TextField tfNome;

	@FXML
	private TabPane tablePane;

	@FXML
	private ComboBox<EnumPermissaoAcessoSistema> cbPermissao;

	@FXML
	private Label lbPermissao;

	@FXML
	private Label lbCelular;

	@FXML
	private Pane pnFundo;

	@FXML
	private Button btNovo;

	@FXML
	private AnchorPane paneAnchor;

	@FXML
	private ImageView icLupa;

	@FXML
	private TextField tfRg;

	@FXML
	private Label lbTelefone;

	@FXML
	private TextField tfTelefone;

	@FXML
	private TableColumn<ItensProperty, String> campoTelefone;

	@FXML
	private Pane paneNavBar;

	@FXML
	private Tab abaCadastro;

	@FXML
	private TextField tfBusca;

	@FXML
	private TableColumn<ItensProperty, String> campoNome;

	@FXML
	private Label lbRg;

	@FXML
	private Label lbNome;

	@FXML
	private Label lbFuncionarioNav;

	@FXML
	private TextField tfCelular;

	@FXML
	void close(MouseEvent event) {
		close.getScene().getWindow().hide();
	}

	@FXML
	void buscaCPF(KeyEvent event) {

	}

	protected double xOffset;

	protected double yOffset;

	@FXML
	// This method is called by the FXMLLoader when initialization is complete
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
		assert close != null : "fx:id=\"close\" was not injected: check your FXML file 'telaFuncionario.fxml'.";
		assert tfNome != null : "fx:id=\"tfNome\" was not injected: check your FXML file 'telaFuncionario.fxml'.";
		assert tablePane != null : "fx:id=\"tablePane\" was not injected: check your FXML file 'telaFuncionario.fxml'.";
		assert cbPermissao != null : "fx:id=\"cbPermissao\" was not injected: check your FXML file 'telaFuncionario.fxml'.";
		assert lbPermissao != null : "fx:id=\"lbPermissao\" was not injected: check your FXML file 'telaFuncionario.fxml'.";
		assert lbCelular != null : "fx:id=\"lbCelular\" was not injected: check your FXML file 'telaFuncionario.fxml'.";
		assert pnFundo != null : "fx:id=\"pnFundo\" was not injected: check your FXML file 'telaFuncionario.fxml'.";
		assert btNovo != null : "fx:id=\"btNovo\" was not injected: check your FXML file 'telaFuncionario.fxml'.";
		assert paneAnchor != null : "fx:id=\"paneAnchor\" was not injected: check your FXML file 'telaFuncionario.fxml'.";
		assert icLupa != null : "fx:id=\"icLupa\" was not injected: check your FXML file 'telaFuncionario.fxml'.";
		assert tfRg != null : "fx:id=\"tfRg\" was not injected: check your FXML file 'telaFuncionario.fxml'.";
		assert lbTelefone != null : "fx:id=\"lbTelefone\" was not injected: check your FXML file 'telaFuncionario.fxml'.";
		assert tfTelefone != null : "fx:id=\"tfTelefone\" was not injected: check your FXML file 'telaFuncionario.fxml'.";
		assert campoTelefone != null : "fx:id=\"campoTelefone\" was not injected: check your FXML file 'telaFuncionario.fxml'.";
		assert paneNavBar != null : "fx:id=\"paneNavBar\" was not injected: check your FXML file 'telaFuncionario.fxml'.";
		assert abaCadastro != null : "fx:id=\"abaCadastro\" was not injected: check your FXML file 'telaFuncionario.fxml'.";
		assert tfBusca != null : "fx:id=\"tfBusca\" was not injected: check your FXML file 'telaFuncionario.fxml'.";
		assert campoNome != null : "fx:id=\"campoNome\" was not injected: check your FXML file 'telaFuncionario.fxml'.";
		assert lbRg != null : "fx:id=\"lbRg\" was not injected: check your FXML file 'telaFuncionario.fxml'.";
		assert lbNome != null : "fx:id=\"lbNome\" was not injected: check your FXML file 'telaFuncionario.fxml'.";
		assert lbFuncionarioNav != null : "fx:id=\"lbFuncionarioNav\" was not injected: check your FXML file 'telaFuncionario.fxml'.";
		assert tfCelular != null : "fx:id=\"tfCelular\" was not injected: check your FXML file 'telaFuncionario.fxml'.";

		validadorCpf = new VerificadorDeCpf();
		erros = new ControllerErros();
//		upperCase = new EventsText();
		
		Font.loadFont(
				ControllerTelaLogin.class.getResource("/fonts/Lobster.ttf")
						.toExternalForm(), 12);
		Font.loadFont(
				ControllerTelaLogin.class.getResource("/fonts/Roboto-Light.ttf")
						.toExternalForm(), 12);
		
		Font.loadFont(
				ControllerTelaLogin.class.getResource("/fonts/Roboto-Thin.ttf")
						.toExternalForm(), 12);
		
		populaTableView();

		tfCpf.focusedProperty().addListener(new CpfMask(tfCpf));
		tfCpf.setOnKeyReleased(new CpfMask(tfCpf));
		tfCpf.addEventFilter(KeyEvent.KEY_TYPED, new ControllerCpfMask());
//		tfNome.focusedProperty().addListener(new EventsText(tfNome));
		EventsText.focusUpperLost(tfNome);
		EventsText.upperCase(tfNome);
		tfRg.addEventFilter(KeyEvent.KEY_TYPED, new ControllerRg());
		tfTelefone.setOnKeyReleased(new TelMask(tfTelefone));
		tfTelefone.addEventFilter(KeyEvent.KEY_TYPED, new ControllerTelMask());
		tfTelefone.focusedProperty().addListener(new TelMask(tfTelefone));
		
		
		tfCelular.setOnKeyReleased(new CelMask(tfCelular));
		tfCelular.addEventFilter(KeyEvent.KEY_TYPED, new ControllerCelMask());
		tfCelular.focusedProperty().addListener(new CelMask(tfCelular));
		

		ObservableList<EnumPermissaoAcessoSistema> listComboBox = FXCollections
				.observableArrayList();
		listComboBox.add(EnumPermissaoAcessoSistema.Administrador);
		listComboBox.add(EnumPermissaoAcessoSistema.Funcionario);
		listComboBox.add(EnumPermissaoAcessoSistema.Gerente);
		cbPermissao.setItems(listComboBox);

		paneNavBar.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				xOffset = LauncherTelaFuncionario.stage.getX()
						- event.getScreenX();
				yOffset = LauncherTelaFuncionario.stage.getY()
						- event.getScreenY();
			}
		});

		paneNavBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				LauncherTelaFuncionario.stage.setX(event.getScreenX()
						+ xOffset);
				LauncherTelaFuncionario.stage.setY(event.getScreenY()
						+ yOffset);
			}
		});

		btSalvar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				tfNome.setText(tfNome.getText().trim());
				tfRg.setText(tfRg.getText().trim());
				tfCpf.setText(tfCpf.getText().trim());
				tfTelefone.setText(tfTelefone.getText().trim());
				tfCelular.setText(tfTelefone.getText().trim());

				if (tfNome.getText().isEmpty()) {
					tfNome.requestFocus();
					erros.erroNome();
				} else if (tfRg.getText().isEmpty()) {
					tfRg.requestFocus();
					erros.erroRg();
				} else if (tfCpf.getText().isEmpty()) {
					tfCpf.requestFocus();
					erros.erroCpf();
				} else if (!validadorCpf.verificarCpf(tfCpf.getText())) {
					tfCpf.requestFocus();
					tfCpf.setText("");
					erros.cpfInvalido();
				} else if (tfTelefone.getText().isEmpty()) {
					tfTelefone.requestFocus();
					erros.erroTelefone();
				} else if (tfCelular.getText().isEmpty()) {
					tfCelular.requestFocus();
					erros.erroCelular();
				} else {

					try {
						if(repo == null){
							repo = new RepositoryFuncionario();
						}
						if(repoLogin == null){
							repoLogin = new LoginRepository();
						}
						if (funcionario == null) {
							funcionario = new Funcionario();
							populaObjeto();
							repoLogin.adicionar(funcionario.getLoginUsuario());
							repo.addFuncionario(funcionario);
						}else {
							populaObjeto();
							repo.atualizar(funcionario);
							clearSelectionInTable();
						}
						limparCampos();
						populaTableView();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		

		btNovo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				limparCampos();
			}
		});
		
		
		
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
					funcionario = func.getFunc();
					
					cm.getItems().clear();
					mi1 = new MenuItem("Alterar");
					cm.getItems().add(mi1);
					mi2 = new MenuItem("Excluir");
					cm.getItems().add(mi2);

					mi1.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent evt) {
							Funcionario f = func.getFunc();
							tablePane.getSelectionModel().select(0);
							tfNome.setText(f.getNome());
							tfCelular.setText(f.getCelular());
							tfCpf.setText(f.getCpf());
							tfRg.setText(f.getRg());
							tfTelefone.setText(f.getTelefone());
							cbPermissao.setValue(f.getPermissaoAcesso());

							funcionario = func.getFunc();
							tableConsulta.getSelectionModel()
									.clearSelection();
						}
					});

					mi2.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent evt) {
							try{
								repo.deleteFuncionario(func.getFunc());
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

	private void clearSelectionInTable(){
		ObservableList<ItensProperty> ls = tableConsulta.getItems();
		tableConsulta.getItems().clear();
		tableConsulta.setItems(ls);
	}
	
	public void populaObjeto(){
		funcionario.setCelular(tfCelular.getText());
		funcionario.setCpf(tfCpf.getText());
		funcionario.setNome(tfNome.getText());
		funcionario.setPermissaoAcesso(cbPermissao.getValue());
		funcionario.setRg(tfRg.getText());
		funcionario.setTelefone(tfTelefone.getText());
		
		
		//Criando o usuario e senha
		String[] vtrNome = funcionario.getNome().split(" ");
		String usuario = "";
		String senha = "";
		for(int i = 0 ; i < vtrNome.length;i++){
			if(i == vtrNome.length-1){
				usuario += vtrNome[i];
			}else {
				usuario += vtrNome[i].substring(0,1); 
			}
		}
		
		Login login = new Login();
		login.setUsuario(usuario);
		login.setSenha("");
		
		funcionario.setLoginUsuario(login);
		
		
	}
	
	public void limparCampos() {
		tfCelular.setText("");
		tfCpf.setText("");
		tfNome.setText("");
		tfRg.setText("");
		tfTelefone.setText("");
		cbPermissao.setValue(null);
		funcionario = null;
	}
	public void populaTableView(){
		repo = new RepositoryFuncionario();
		ObservableList<Funcionario> listFuncionario = FXCollections.observableArrayList();
		ObservableList<ItensProperty> listItens = FXCollections.observableArrayList();
		
		campoCelular.setCellValueFactory(new PropertyValueFactory<ItensProperty, String>("Celular"));
		campoCpf.setCellValueFactory(new PropertyValueFactory<ItensProperty, String>("Cpf"));
		campoNome.setCellValueFactory(new PropertyValueFactory<ItensProperty, String>("Nome"));
		campoTelefone.setCellValueFactory(new PropertyValueFactory<ItensProperty, String>("Telefone"));
		
		try {
			listFuncionario = repo.buscaTodosFuncionarios();
			
			for(Funcionario f : listFuncionario){
				listItens.add(new ItensProperty(f));
			}
			tableConsulta.setItems(listItens);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public class ItensProperty {
		private Funcionario f;
		private final SimpleStringProperty nome = new SimpleStringProperty("!");
		private final SimpleStringProperty cpf = new SimpleStringProperty("!");
		private final SimpleStringProperty telefone = new SimpleStringProperty("!");
		private final SimpleStringProperty celular = new SimpleStringProperty("!");		
		public ItensProperty(Funcionario f) {			
			this.f = f;
			this.setNome((f.getNome()));
			this.setCpf((f.getCpf()));
			this.setTelefone((f.getTelefone()));
			this.setCelular((f.getCelular()));
			
		}
		public Funcionario getFunc(){
			return f;
		}						
		public String getNome() {
			return nome.get();
		}
		public void setNome(String nome) {
			this.nome.set(nome);
		}
		public String getCpf() {
			return cpf.get();
		}
		public void setCpf(String cpf) {
			this.cpf.set(cpf);
		}
		public String getTelefone() {
			return telefone.get();
		}
		public void setTelefone(String telefone) {
			this.telefone.set(telefone);
		}
		public String getCelular() {
			return celular.get();
		}
		public void setCelular(String celular) {
			this.celular.set(celular);
		}		
	}
	
}
