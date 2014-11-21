	package controller.view;

import java.awt.ItemSelectable;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;

import controller.*;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;

import javassist.runtime.Inner;

import com.itextpdf.text.log.SysoLogger;
import com.sun.corba.se.impl.orbutil.graph.Node;
import com.sun.deploy.uitoolkit.impl.fx.ui.FXConsole;
import com.sun.rmi.rmid.ExecPermission;

import controller.ControllerOnlyNumbers;
import controller.ControllerRg;
import controller.VerificadorDeCpf;
import controller.events.EventsDate;
import controller.events.EventsText;
import controller.events.MaskFieldUtil;
import model.Carro;
import model.Funcionario;
import model.Instrutor;
import model.enums.EnumStatus;
import model.interfaces.ITableInScreens;
import model.repository.RepositoryCarro;
import model.repository.RepositoryInstrutor;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.util.Callback;
import controller.launcher.LauncherCameraWeb;
import controller.launcher.LauncherTelaInstrutor;
import controller.mask.*;
import controller.view.ControllerTelaCarro.ItensProperty;

public class ControllerTelaInstrutor implements ITableInScreens {

	private Instrutor instrutor;
	private RepositoryInstrutor repoInstrutor;
	private Carro carro;
	private RepositoryCarro repoCarro;
	private VerificadorDeCpf validadorCpf;
	private LocalDate dataTransform, dataValidadeCnhTransform,
			dataPermiTransform;
	private Date dataTransformed, dataValidadeCnhTransformed,
			dataPermiTransformed;

	public ControllerTelaInstrutor() {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Tab abaConsulta;

	@FXML
	private Label lbCarro;

	@FXML
	private TableColumn<ItensProperty, String> campoTel;

	@FXML
	private TableColumn<ItensProperty, String> campoCpf;

	@FXML
	private Button btFoto;

	@FXML
	private Button btSalvar;

	@FXML
	private DatePicker dtDataPermissao;

	@FXML
	private TabPane tabePane;

	@FXML
	private Label lbDadosIstrutor;

	@FXML
	private TextField tfNumeroCnh;

	@FXML
	private Label lbData;

	@FXML
	private Label lbListaInstrutores;

	@FXML
	private Label lbNumCnh;

	@FXML
	private ComboBox<Carro> cbCarro;

	@FXML
	private TextField tfCpf;

	@FXML
	private ComboBox<EnumStatus> cbStatus;

	@FXML
	private Label lbTel;

	@FXML
	private TextField tfTel;

	@FXML
	private Label lbInstrutorNavBar;

	@FXML
	private Label lbCpf;

	@FXML
	private Label lbStatus;

	@FXML
	private ImageView close;

	@FXML
	private TextField tfNome;

	@FXML
	private Label lbDataPermissao;

	@FXML
	private Pane paneDadosInstrutor;

	@FXML
	private DatePicker dtDataValidadeCnh;

	@FXML
	private Pane paneFoto;

	@FXML
	private TextField tfCel;

	@FXML
	private Label lbCel;

	@FXML
	private Pane pnFundo;

	@FXML
	private ImageView pnFoto;

	@FXML
	private Label lbValidade;

	@FXML
	private Button btNovo;

	@FXML
	private AnchorPane paneAnchor;

	@FXML
	private ImageView icLupa;

	@FXML
	private TextField tfRg;

	@FXML
	private TableColumn<ItensProperty, String> campoCel;

	@FXML
	private Pane paneTableBusca;

	@FXML
	private Pane paneNavBar;

	@FXML
	private Tab abaCadastro;

	@FXML
	private TextField tfBusca;

	@FXML
	private TableView<ItensProperty> tableBuscaInstrutores;

	@FXML
	private TableColumn<ItensProperty, String> campoNome;

	@FXML
	private Label lbRg;

	@FXML
	private Label lbNome;

	@FXML
	private DatePicker dtData;
	
	protected static ImageView imgFoto;
	
	protected static Pane pnStaticFoto;
	
	@FXML
	void close(MouseEvent event) {
		close.getScene().getWindow().hide();
	}

	protected double xOffset;
	protected double yOffset;

	@FXML
	void foto(ActionEvent event) {
		ControllerWebCam.defineValores("Instrutor");
		new LauncherCameraWeb().start(LauncherTelaInstrutor.stage);
	}

	@FXML
	void novo(ActionEvent event) {
		limparCampos();
	}

	@FXML
	void buscaCPF(ActionEvent event) {

	}

	@FXML
	void salvar(ActionEvent event) {

		validadorCpf = new VerificadorDeCpf();
		ControllerErros erros = new ControllerErros();
		tfCel.setText(tfCel.getText().trim());
		tfCpf.setText(tfCpf.getText().trim());
		tfNome.setText(tfNome.getText().trim());
		tfNumeroCnh.setText(tfNumeroCnh.getText().trim());
		tfRg.setText(tfRg.getText().trim());
		tfTel.setText(tfTel.getText().trim());
		if (tfNome.getText().isEmpty()) {
			erros.erroNome();
			tfNome.requestFocus();
		} else if (dtData.getValue() == null) {
			erros.erroData();
			dtData.requestFocus();
		} else if (tfNumeroCnh.getText().isEmpty()) {
			erros.erroNumeroCnh();
			tfNumeroCnh.requestFocus();
		} else if (dtDataValidadeCnh.getValue() == null) {
			erros.erroDataValidadeCnh();
			dtDataValidadeCnh.requestFocus();
		} else if (dtDataPermissao.getValue() == null) {
			erros.erroDataPermissao();
			dtDataPermissao.requestFocus();
		} else if (tfRg.getText().isEmpty()) {
			erros.erroRg();
			tfRg.requestFocus();
		} else if (!validadorCpf.verificarCpf(tfCpf.getText())) {
			erros.cpfInvalido();
			tfRg.requestFocus();
		} else if (tfCpf.getText().isEmpty()) {
			erros.erroCpf();
			tfCpf.requestFocus();
		} else if (cbStatus.valueProperty().equals(null)) {
			System.out.println("teste combobox");
		} else {
			try {
				repoInstrutor = new RepositoryInstrutor();
				validadorCpf = new VerificadorDeCpf();

				if (instrutor == null) {
					instrutor = new Instrutor();
					if (carro == null) {
						carro = new Carro();
					}
					populaObjeto();
					repoInstrutor.adicionar(instrutor);
				} else {
					populaObjeto();
					repoInstrutor.atualizar(instrutor);
					clearSelectionInTable();
				}

				limparCampos();
				populaTableView();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}


	// Metodo respons√°vel para popular todos os dados do campos
	// para o objeto Instrutor
	private void populaObjeto() throws IOException {
		// populando objeto
		instrutor.setCelular(tfCel.getText());
		instrutor.setCnh(tfNumeroCnh.getText());

		if (validadorCpf.verificarCpf(tfCpf.getText())) {
			instrutor.setCpf(tfCpf.getText());
		} else {
			// erros.cpfInvalido();
			tfCpf.setText("");
			tfCpf.requestFocus();
			return;
		}
		// transformando LocalDate em Date
		dataTransform = dtData.getValue();
		dataTransformed = Date.from(dataTransform.atStartOfDay(
				ZoneId.systemDefault()).toInstant());
		instrutor.setData(dataTransformed);
		// transformando LocalDate em Date
		dataPermiTransform = dtDataPermissao.getValue();
		dataPermiTransformed = Date.from(dataPermiTransform.atStartOfDay(
				ZoneId.systemDefault()).toInstant());
		instrutor.setPrimeiraCnh(dataPermiTransformed);
		// transformando LocalDate em Date
		dataValidadeCnhTransform = dtDataValidadeCnh.getValue();
		dataValidadeCnhTransformed = Date.from(dataValidadeCnhTransform
				.atStartOfDay(ZoneId.systemDefault()).toInstant());
		instrutor.setValidadeCnh(dataValidadeCnhTransformed);
		// instrutor.setImage(image);
		instrutor.setNome(tfNome.getText());
		instrutor.setRg(tfRg.getText());
		instrutor.setTelefone(tfTel.getText());
		instrutor.setTbCarroPlacaCarro((Carro) cbCarro.getValue());
		instrutor.setStatus(cbStatus.getValue());
		
		if(imgFoto.getImage() != null){
			javafx.scene.image.Image im = imgFoto.getImage();
			BufferedImage bImage = SwingFXUtils.fromFXImage(im, null);
			ByteArrayOutputStream s = new ByteArrayOutputStream();
			ImageIO.write(bImage, "jpg", s);
			byte[] res  = s.toByteArray();
			instrutor.setImage(res);	
		}
	}

	@FXML
	// This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert abaConsulta != null : "fx:id=\"abaConsulta\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert lbCarro != null : "fx:id=\"lbCarro\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert campoTel != null : "fx:id=\"campoTel\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert campoCpf != null : "fx:id=\"campoCpf\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert btFoto != null : "fx:id=\"btFoto\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert btSalvar != null : "fx:id=\"btSalvar\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert dtDataPermissao != null : "fx:id=\"dtDataPermissao\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
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
		assert close != null : "fx:id=\"close\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert tfNome != null : "fx:id=\"tfNome\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert lbDataPermissao != null : "fx:id=\"lbDataPermissao\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert paneDadosInstrutor != null : "fx:id=\"paneDadosInstrutor\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert dtDataValidadeCnh != null : "fx:id=\"dtDataValidadeCnh\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert paneFoto != null : "fx:id=\"paneFoto\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert tfCel != null : "fx:id=\"tfCel\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert lbCel != null : "fx:id=\"lbCel\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert pnFundo != null : "fx:id=\"pnFundo\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert pnFoto != null : "fx:id=\"pnFoto\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert lbValidade != null : "fx:id=\"lbValidade\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert btNovo != null : "fx:id=\"btNovo\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert paneAnchor != null : "fx:id=\"paneAnchor\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert icLupa != null : "fx:id=\"icLupa\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert tfRg != null : "fx:id=\"tfRg\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert campoCel != null : "fx:id=\"campoCel\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert paneTableBusca != null : "fx:id=\"paneTableBusca\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert paneNavBar != null : "fx:id=\"paneNavBar\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert abaCadastro != null : "fx:id=\"abaCadastro\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert tfBusca != null : "fx:id=\"tfBusca\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert tableBuscaInstrutores != null : "fx:id=\"tableBuscaInstrutores\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert campoNome != null : "fx:id=\"campoNome\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert lbRg != null : "fx:id=\"lbRg\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert lbNome != null : "fx:id=\"lbNome\" was not injected: check your FXML file 'telaInstrutor.fxml'.";
		assert dtData != null : "fx:id=\"dtData\" was not injected: check your FXML file 'telaInstrutor.fxml'.";

		Font.loadFont(
				ControllerTelaLogin.class.getResource("/fonts/Lobster.ttf")
						.toExternalForm(), 12);
		Font.loadFont(
				ControllerTelaLogin.class.getResource("/fonts/Roboto-Light.ttf")
						.toExternalForm(), 12);
		
		Font.loadFont(
				ControllerTelaLogin.class.getResource("/fonts/Roboto-Thin.ttf")
						.toExternalForm(), 12);
		
		
		
		imgFoto = pnFoto;
		pnStaticFoto = paneFoto;
		
		tableBuscaInstrutores.addEventHandler(MouseEvent.MOUSE_CLICKED,
				new EventHandler<MouseEvent>() {
					ContextMenu cm = new ContextMenu();
					MenuItem mi1;
					MenuItem mi2;

					@Override
					public void handle(MouseEvent t) {
						if (t.getButton() == MouseButton.SECONDARY) {
							limparCampos();
//							System.out.println(tableBuscaInstrutores);
//							System.out.println(tableBuscaInstrutores.getSelectionModel());
//							System.out.println(tableBuscaInstrutores.getSelectionModel().getSelectedItem());
							ItensProperty instru = tableBuscaInstrutores
									.getSelectionModel().getSelectedItem();
							instrutor = instru.getInstrutor();
//							System.out.println(instru);
							cm.getItems().clear();
							mi1 = new MenuItem("Alterar");
							cm.getItems().add(mi1);
							mi2 = new MenuItem("Excluir");
							cm.getItems().add(mi2);
							tableBuscaInstrutores.getSelectionModel()
							.clearSelection();

							mi1.setOnAction(new EventHandler<ActionEvent>() {

								@Override
								public void handle(ActionEvent evt) {
									Instrutor i = instru.getInstrutor();
									tabePane.getSelectionModel().select(0);
									tfNome.setText(i.getNome());
									tfCel.setText(i.getCelular());
									tfCpf.setText(i.getCpf());
									tfNumeroCnh.setText(i.getCnh());
									tfRg.setText(i.getRg());
									tfTel.setText(i.getTelefone());
									cbStatus.getSelectionModel().select(
											i.getStatus());
									;
									cbCarro.getSelectionModel().select(
											i.getTbCarroPlacaCarro());
									LocalDate dt = i.getPrimeiraCnh()
											.toInstant()
											.atZone(ZoneId.systemDefault())
											.toLocalDate();
									dtDataPermissao.setValue(dt);
									dt = i.getValidadeCnh().toInstant()
											.atZone(ZoneId.systemDefault())
											.toLocalDate();
									dtDataValidadeCnh.setValue(dt);
									
									//Limpando os filhos do componente
									for(int k = 0; k < pnStaticFoto.getChildren().size();k++){
										pnStaticFoto.getChildren().remove(i);
									}
									
									if(i.getImage() != null){
									imgFoto.setImage(i.getImage());
									paneFoto.getChildren().add(imgFoto);
									}
									
									instrutor = i;
									tableBuscaInstrutores.getSelectionModel()
											.clearSelection();

								}
							});

							mi2.setOnAction(new EventHandler<ActionEvent>() {
								@Override
								public void handle(ActionEvent evt) {
									try{
										if(repoCarro == null){
											repoCarro = new RepositoryCarro();
										}
										//Ao deletar um instrutor, È necess·rio
										//Desocupar o carro em que ele estava usando.
										Carro c = instru.getInstrutor().getTbCarroPlacaCarro();
										c.setOcupado(false);
										repoCarro.atualizar(c);
										repoInstrutor.deletarInstrutor(instru.getInstrutor());
										populaTableView();
									}catch(Exception e){
										e.printStackTrace();
									}
								}
							});
							
							cm.show(tableBuscaInstrutores, t.getScreenX(),
									t.getScreenY());
						} else if (t.getButton() == MouseButton.PRIMARY) {
							cm.hide();
						}
					}
				});

		// populando tableview
		populaTableView();
		// fim populando tableview

		// populando enum
		ObservableList<EnumStatus> status = FXCollections.observableArrayList();
		status.add(EnumStatus.Ativos);
		status.add(EnumStatus.Inativo);
		cbStatus.setItems(status);
		// fim populando enum

		paneNavBar.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				xOffset = LauncherTelaInstrutor.stage.getX()
						- event.getScreenX();
				yOffset = LauncherTelaInstrutor.stage.getY()
						- event.getScreenY();
			}
		});
		paneNavBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				LauncherTelaInstrutor.stage.setX(event.getScreenX()
						+ xOffset);
				LauncherTelaInstrutor.stage.setY(event.getScreenY()
						+ yOffset);
			}
		});
		try {
			repoCarro = new RepositoryCarro();
			ObservableList<Carro> listCarro = FXCollections
					.observableArrayList();
			listCarro = repoCarro.buscaCarrosDisponiveis();
			cbCarro.setItems(listCarro);
		} catch (Exception e) {
			e.printStackTrace();
		}
/*		// perda de foco (mask)
		tfCpf.focusedProperty().addListener(new CpfMask(tfCpf));
		// coloca a mask
		tfCpf.setOnKeyReleased(new CpfMask(tfCpf));
		// somente numeros, (.) e (-)
		tfCpf.addEventFilter(KeyEvent.KEY_TYPED, new ControllerCpfMask());*/
		
		
		// somente numeros
		tfNumeroCnh.addEventFilter(KeyEvent.KEY_TYPED,
				new ControllerOnlyNumbers());
//		tfRg.addEventFilter(KeyEvent.KEY_TYPED, new ControllerRg());
		
		MaskFieldUtil.cpfField(tfCpf);
		MaskFieldUtil.rgField(tfRg);
		
		// telefone mask
		// perda de foco (mask)
		tfTel.focusedProperty().addListener(new TelMask(tfTel));
		// mask
		tfTel.setOnKeyReleased(new TelMask(tfTel));
		tfTel.addEventFilter(KeyEvent.KEY_TYPED, new ControllerTelMask());
//		tfNome.focusedProperty().addListener(new EventsText(tfNome));
		EventsText.upperCase(tfNome);
		EventsDate.setAfterDateDisble(dtDataPermissao);
		EventsDate.setBeforeDateDisable(dtDataValidadeCnh);
		dtData.setValue(LocalDate.now());
		dtData.setDisable(true);
		tfCel.setOnKeyReleased(new CelMask(tfCel));
		tfCel.addEventFilter(KeyEvent.KEY_TYPED, new ControllerCelMask());
		tfCel.focusedProperty().addListener(new CelMask(tfCel));
		
	}

	public void limparCampos() {
		tfCel.setText("");
		tfCpf.setText("");
		tfNome.setText("");
		tfNumeroCnh.setText("");
		tfRg.setText("");
		tfTel.setText("");
		dtDataPermissao.setValue(null);
		dtDataValidadeCnh.setValue(null);
		
		cbCarro.getSelectionModel().select(-1);
		cbCarro.getItems().remove(instrutor.getTbCarroPlacaCarro());
		cbStatus.setValue(null);
		instrutor = null;
		for(int i = 0; i < paneFoto.getChildren().size();i++){
			paneFoto.getChildren().remove(i);
		}
	}

	private void clearSelectionInTable(){
		ObservableList<ItensProperty> ls = tableBuscaInstrutores.getItems();
		tableBuscaInstrutores.getItems().clear();
		tableBuscaInstrutores.setItems(ls);
	}
	
	@Override
	public void populaTableView() {
		repoInstrutor = new RepositoryInstrutor();
		ObservableList<Instrutor> listIns = FXCollections.observableArrayList();
		ObservableList<ItensProperty> itens = FXCollections
				.observableArrayList();
		campoNome
				.setCellValueFactory(new PropertyValueFactory<ItensProperty, String>(
						"Nome"));
		campoCel.setCellValueFactory(new PropertyValueFactory<ItensProperty, String>(
				"Celular"));
		campoCpf.setCellValueFactory(new PropertyValueFactory<ItensProperty, String>(
				"Cpf"));
		campoTel.setCellValueFactory(new PropertyValueFactory<ItensProperty, String>(
				"Telefone"));
		try {
			listIns = repoInstrutor.buscaInstrutorTeste();
			for (Instrutor f : listIns) {
				System.out.println(f.getCpf());
				itens.add(new ItensProperty(f));
			}
			tableBuscaInstrutores.setItems(itens);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// / innerClass para popular a tableview
	public class ItensProperty {
		private Instrutor f;
		private final SimpleStringProperty nome = new SimpleStringProperty("");
		private final SimpleStringProperty cpf = new SimpleStringProperty("");
		private final SimpleStringProperty telefone = new SimpleStringProperty("");
		private final SimpleStringProperty celular = new SimpleStringProperty("");

		public ItensProperty(Instrutor f) {
			this.f = f;
			this.setNome((f.getNome()));
			this.setCpf((f.getCpf()));
			this.setTelefone((f.getTelefone()));
			this.setCelular((f.getCelular()));

		}

		public Instrutor getInstrutor() {
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
