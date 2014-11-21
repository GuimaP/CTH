package controller.view;

import java.net.URL;
import java.util.ResourceBundle;

import javax.security.auth.callback.Callback;
import javax.swing.JOptionPane;

import org.apache.poi.ss.formula.functions.Even;

import controller.ControllerOnlyNumbers;
import controller.events.EventsText;
import controller.events.MaskFieldUtil;
import model.Carro;
import model.Funcionario;
import model.repository.RepositoryCarro;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import controller.launcher.LauncherTelaCarro;
import controller.mask.*;
import controller.view.ControllerTelaFuncionario.ItensProperty;
import controller.*;

public class ControllerTelaCarro {

	private Carro carro;
	private RepositoryCarro repoCarro;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Tab abaConsultaCarro;

	@FXML
	private TextField tfAno;

	@FXML
	private Button btSalvar;

	@FXML
	private TextField tfPlaca;

	@FXML
	private TextField tfModelo;

	@FXML
	private Label lbAno;

	@FXML
	private Tab abaCadastroCarros;

	@FXML
	private Pane paneFundo;

	@FXML
	private Label lbPlaca;

	@FXML
	private TableColumn<ItensProperty, Long> campoAno;

	@FXML
	private Label lbListaCarro;

	@FXML
	private Label lbMarca;

	@FXML
	private TabPane tablePane;

	@FXML
	private TableView<ItensProperty> tableCarro;

	@FXML
	private Pane paneCadastroCarro;

	@FXML
	private TextField tfMarca;

	@FXML
	private TableColumn<ItensProperty, String> campoPlaca;

	@FXML
	private Label lbModelo;

	@FXML
	private Label lbDadosCarro;

	@FXML
	private Button btNovo;

	@FXML
	private AnchorPane paneAnchor;

	@FXML
	private ImageView icLupa;

	@FXML
	private Pane paneNaveBar;

	@FXML
	private TextField tfBusca;

	@FXML
	private Label lbCarroNavBar;

	@FXML
	private Pane paneConsultaCarro;

	@FXML
	private TableColumn<ItensProperty, String> campoModelo;

	@FXML
	private ImageView icClose;

	@FXML
	void close(MouseEvent event) {
		paneAnchor.getScene().getWindow().hide();
		System.out.println("Bye!");
	}

	protected double xOffset;
	protected double yOffset;

	@FXML
	void salvar(ActionEvent event) {
		ControllerErros erros = new ControllerErros();

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
				//Verifica se existe uma instancia do objeto
				if(repoCarro == null)
					repoCarro = new RepositoryCarro();
				
				//Se estiver nulo,  então é um novo registro
				if(carro == null){
					carro = new Carro();
					popularObjeto();
					repoCarro.adicionar(carro);
				}else { //Caso contrário, irá atualizar
					popularObjeto();
					repoCarro.atualizar(carro);
					clearSelectionInTable();
				}
				limparCampos();
				populaTableView();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void popularObjeto(){
		carro.setAno(Long.parseLong(tfAno.getText()));
		carro.setMarca(tfMarca.getText());
		carro.setModelo(tfModelo.getText());
		carro.setPlaca(tfPlaca.getText());
	}
	
	@FXML
	void novo(ActionEvent event) {
		limparCampos();
	}

	@FXML
	void buscaCPF(KeyEvent event) {

	}

	@FXML
	void excluir(ActionEvent event) {

	}

	// metodos diversos
	public void limparCampos() {
		tfAno.setText(null);
		tfMarca.setText(null);
		tfModelo.setText(null);
		tfPlaca.setText(null);
		carro = null;
	}

	// fim metodos diversos

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
		assert paneFundo != null : "fx:id=\"paneFundo\" was not injected: check your FXML file 'telaCarro.fxml'.";
		assert lbPlaca != null : "fx:id=\"lbPlaca\" was not injected: check your FXML file 'telaCarro.fxml'.";
		assert campoAno != null : "fx:id=\"campoAno\" was not injected: check your FXML file 'telaCarro.fxml'.";
		assert lbListaCarro != null : "fx:id=\"lbListaCarro\" was not injected: check your FXML file 'telaCarro.fxml'.";
		assert lbMarca != null : "fx:id=\"lbMarca\" was not injected: check your FXML file 'telaCarro.fxml'.";
		assert tablePane != null : "fx:id=\"tablePane\" was not injected: check your FXML file 'telaCarro.fxml'.";
		assert tableCarro != null : "fx:id=\"tableCarro\" was not injected: check your FXML file 'telaCarro.fxml'.";
		assert paneCadastroCarro != null : "fx:id=\"paneCadastroCarro\" was not injected: check your FXML file 'telaCarro.fxml'.";
		assert tfMarca != null : "fx:id=\"tfMarca\" was not injected: check your FXML file 'telaCarro.fxml'.";
		assert campoPlaca != null : "fx:id=\"campoPlaca\" was not injected: check your FXML file 'telaCarro.fxml'.";
		assert lbModelo != null : "fx:id=\"lbModelo\" was not injected: check your FXML file 'telaCarro.fxml'.";
		assert lbDadosCarro != null : "fx:id=\"lbDadosCarro\" was not injected: check your FXML file 'telaCarro.fxml'.";
		assert btNovo != null : "fx:id=\"btNovo\" was not injected: check your FXML file 'telaCarro.fxml'.";
		assert paneAnchor != null : "fx:id=\"paneAnchor\" was not injected: check your FXML file 'telaCarro.fxml'.";
		assert icLupa != null : "fx:id=\"icLupa\" was not injected: check your FXML file 'telaCarro.fxml'.";
		assert paneNaveBar != null : "fx:id=\"paneNaveBar\" was not injected: check your FXML file 'telaCarro.fxml'.";
		assert tfBusca != null : "fx:id=\"tfBusca\" was not injected: check your FXML file 'telaCarro.fxml'.";
		assert lbCarroNavBar != null : "fx:id=\"lbCarroNavBar\" was not injected: check your FXML file 'telaCarro.fxml'.";
		assert paneConsultaCarro != null : "fx:id=\"paneConsultaCarro\" was not injected: check your FXML file 'telaCarro.fxml'.";
		assert campoModelo != null : "fx:id=\"campoModelo\" was not injected: check your FXML file 'telaCarro.fxml'.";
		assert icClose != null : "fx:id=\"icClose\" was not injected: check your FXML file 'telaCarro.fxml'.";

		paneNaveBar.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				xOffset = LauncherTelaCarro.stage.getX()
						- event.getScreenX();
				yOffset = LauncherTelaCarro.stage.getY()
						- event.getScreenY();
			}
		});

		paneNaveBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				LauncherTelaCarro.stage.setX(event.getScreenX() + xOffset);
				LauncherTelaCarro.stage.setY(event.getScreenY() + yOffset);
			}
		});

		EventsText.focusUpperLost(tfMarca);
		EventsText.upperCase(tfMarca);

		EventsText.focusUpperLost(tfModelo);
		EventsText.upperCase(tfModelo);

		tfAno.addEventFilter(KeyEvent.KEY_TYPED, new ControllerOnlyNumbers());

		tfPlaca.focusedProperty().addListener(new PlacaMask(tfPlaca));
		tfPlaca.setOnKeyReleased(new PlacaMask(tfPlaca));
		tfPlaca.addEventFilter(KeyEvent.KEY_TYPED, new ControllerPlacaMask());

		EventsText.focusUpperLost(tfPlaca);
		EventsText.upperCase(tfPlaca);
		
		populaTableView();
		
		tableCarro.addEventHandler(MouseEvent.MOUSE_CLICKED,
				new EventHandler<MouseEvent>() {
			ContextMenu cm = new ContextMenu();
			MenuItem mi1;
			MenuItem mi2;

			@Override
			public void handle(MouseEvent t) {
				if (t.getButton() == MouseButton.SECONDARY) {

					ItensProperty c = tableCarro
							.getSelectionModel().getSelectedItem();
					
					carro = c.getC();
					
					cm.getItems().clear();
					mi1 = new MenuItem("Alterar");
					cm.getItems().add(mi1);
					mi2 = new MenuItem("Excluir");
					cm.getItems().add(mi2);

					mi1.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent evt) {
//							Funcionario f = carro.getFunc();
							tablePane.getSelectionModel().select(0);
							tfAno.setText(String.valueOf(c.getAno()));
							tfMarca.setText(c.getC().getMarca());
							tfModelo.setText(c.getModelo());
							tfPlaca.setText(c.getPlaca());
							tableCarro.getSelectionModel()
									.clearSelection();
						}
					});

					mi2.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent evt) {
							try{
								repoCarro.deleteCarro(carro);
								populaTableView();
								limparCampos();
								tableCarro.getSelectionModel()
								.clearSelection();
							}catch(Exception e){
								e.printStackTrace();
								JOptionPane.showMessageDialog(null, e.getMessage());
							}
						}
					});
					
					cm.show(tableCarro, t.getScreenX(),
							t.getScreenY());
				} else if (t.getButton() == MouseButton.PRIMARY) {
					cm.hide();
				}
			}
		});
		
	}
	
	private void clearSelectionInTable(){
		ObservableList<ItensProperty> ls = tableCarro.getItems();
		tableCarro.getItems().clear();
		tableCarro.setItems(ls);
	}

	public void populaTableView(){
		repoCarro = new RepositoryCarro();
		ObservableList<Carro> listCarro = FXCollections.observableArrayList();
		ObservableList<ItensProperty> itens = FXCollections.observableArrayList();
		
		campoAno.setCellValueFactory(new PropertyValueFactory<ItensProperty, Long>("Ano"));
		campoModelo.setCellValueFactory(new PropertyValueFactory<ItensProperty, String>("Modelo"));
		campoPlaca.setCellValueFactory(new PropertyValueFactory<ItensProperty, String>("Placa"));
		
		try {
			listCarro = repoCarro.buscaCarroObservable();
			for(Carro c : listCarro){
				itens.add(new ItensProperty(c));
			}
			tableCarro.setItems(itens);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public class ItensProperty {
		private Carro c;
		private SimpleStringProperty modelo = new SimpleStringProperty("");
		private SimpleLongProperty ano = new SimpleLongProperty();
		private SimpleStringProperty placa = new SimpleStringProperty("");

		public ItensProperty(Carro c) {
			this.c = c;
			this.setModelo(c.getModelo());
			this.setAno(c.getAno());
			this.setPlaca(c.getPlaca());
		}

		public Carro getC() {
			return c;
		}

		public void setModelo(String modelo) {
			this.modelo.set(modelo);
		}

		public void setAno(long ano) {
			this.ano.set(ano);
		}

		public void setPlaca(String placa) {
			this.placa.set(placa);
		}

		public String getModelo() {
			return modelo.get();
		}

		public long getAno() {
			return ano.get();
		}

		public String getPlaca() {
			return placa.get();
		}

	}
}
