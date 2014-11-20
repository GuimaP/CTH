package controller.view;

import groovy.util.ObservableList;

import java.net.URL;
import java.time.LocalDate;
import java.util.Observable;
import java.util.ResourceBundle;

import org.controlsfx.dialog.Dialogs;

import controller.events.EventsText;
import controller.events.MaskFieldUtil;
import controller.launcher.LauncherPrincipal;
import controller.launcher.LauncherLocalizarDespesa;
import controller.launcher.LauncherTelaFinanceiro;
import model.Financeiro;
import model.enums.EnumTipoDespesa;
import model.repository.RepositoryFinanceiro;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ControllerTelaFinanceiro {

	public static boolean telaFinanceiro;

	public static Stage stage;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btLocalizar;

	@FXML
	private Label lbDescricao;

	@FXML
	private Button btSalvar;

	@FXML
	private TextField tfDescricao;

	@FXML
	private Label lbValor;

	@FXML
	private Pane pnFundo;

	@FXML
	private Label lbTipoDeDespesa;

	@FXML
	private Button btNovo;

	@FXML
	private AnchorPane panceAnchor;

	@FXML
	private Pane paneNavBar;

	@FXML
	private ComboBox<EnumTipoDespesa> cbTipoDespesa;

	@FXML
	private Label lbFinanceiro;

	@FXML
	private TextField tfValor;

	@FXML
	private ImageView icClose;

	protected double xOffset;

	protected double yOffset;

	protected static Financeiro financeiro;

	private RepositoryFinanceiro repoFinanceiro;
	
	protected static TextField tfDescricaoStatic,tfValorStatic;
	
	protected static ComboBox<EnumTipoDespesa> cbTipoDespesaStatic;
	
	

	public ControllerTelaFinanceiro() {
		repoFinanceiro = new RepositoryFinanceiro();
	}

	@FXML
	void localizar(ActionEvent event) {
		try {
			new LauncherLocalizarDespesa()
					.start(LauncherTelaFinanceiro.stage);
			// new LauncherTelaFuncionario().start(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	@FXML
	void novo(ActionEvent event) {
		limpa();
	}

	@FXML
	void salvar(ActionEvent event) {
		if (tfDescricao.getText().isEmpty()) {
			Dialogs.create().message("Verifique o nome da despesa ")
					.masthead("Verifique a descrição").title("Atenção")
					.showWarning();
		} else if (tfValor.getText().isEmpty() & !tfValor.getText().contains(",")) {
			Dialogs.create().message("Verifique o valor da despesa ")
					.masthead("Verifique a despesa").title("Atenção")
					.showWarning();
		} else if (cbTipoDespesa.getSelectionModel().isEmpty()) {
			Dialogs.create().message("Selecione um tipo de despesa ")
					.masthead("Verifique a despesa").title("Atenï¿½ï¿½o")
					.showWarning();
		} else {
			try {
				if (financeiro == null) {
					financeiro = new Financeiro();
					populaObjeto();
					repoFinanceiro.adicionar(financeiro);
				}else {
					populaObjeto();
					repoFinanceiro.atualizar(financeiro);
				}
				limpa();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void populaObjeto() {
		financeiro.setDescricao(tfDescricao.getText());
		String valor = tfValor.getText();
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
		financeiro.setValor(Double.parseDouble(valor));
		financeiro.setTipoPagamento(cbTipoDespesa.getSelectionModel()
				.getSelectedItem());
		financeiro.setDtLancamento(LocalDate.now());
		financeiro.setFuncionario(LauncherPrincipal.funcionarioLogado);
	}

	private void limpa() {
		tfDescricao.setText("");
		tfValor.setText("");
		cbTipoDespesa.getSelectionModel().clearSelection();
		financeiro = null;
	}

	@FXML
	void close(MouseEvent event) {
		icClose.getScene().getWindow().hide();
	}

	@FXML
	void initialize() {
		assert btLocalizar != null : "fx:id=\"btLocalizar\" was not injected: check your FXML file 'financeiro.fxml'.";
		assert lbDescricao != null : "fx:id=\"lbDescricao\" was not injected: check your FXML file 'financeiro.fxml'.";
		assert btSalvar != null : "fx:id=\"btSalvar\" was not injected: check your FXML file 'financeiro.fxml'.";
		assert tfDescricao != null : "fx:id=\"tfDescricao\" was not injected: check your FXML file 'financeiro.fxml'.";
		assert lbValor != null : "fx:id=\"lbValor\" was not injected: check your FXML file 'financeiro.fxml'.";
		assert pnFundo != null : "fx:id=\"pnFundo\" was not injected: check your FXML file 'financeiro.fxml'.";
		assert lbTipoDeDespesa != null : "fx:id=\"lbTipoDeDespesa\" was not injected: check your FXML file 'financeiro.fxml'.";
		assert btNovo != null : "fx:id=\"btNovo\" was not injected: check your FXML file 'financeiro.fxml'.";
		assert panceAnchor != null : "fx:id=\"panceAnchor\" was not injected: check your FXML file 'financeiro.fxml'.";
		assert paneNavBar != null : "fx:id=\"paneNavBar\" was not injected: check your FXML file 'financeiro.fxml'.";
		assert cbTipoDespesa != null : "fx:id=\"cbTipoDespesa\" was not injected: check your FXML file 'financeiro.fxml'.";
		assert lbFinanceiro != null : "fx:id=\"lbFinanceiro\" was not injected: check your FXML file 'financeiro.fxml'.";
		assert tfValor != null : "fx:id=\"tfValor\" was not injected: check your FXML file 'financeiro.fxml'.";
		assert icClose != null : "fx:id=\"icClose\" was not injected: check your FXML file 'financeiro.fxml'.";

		tfDescricaoStatic = tfDescricao;
		tfValorStatic = tfValor;
		cbTipoDespesaStatic = cbTipoDespesa;
		
		Font.loadFont(
				ControllerTelaLogin.class.getResource("/fonts/Lobster.ttf")
						.toExternalForm(), 12);
		Font.loadFont(
				ControllerTelaLogin.class
						.getResource("/fonts/Roboto-Light.ttf")
						.toExternalForm(), 12);

		Font.loadFont(
				ControllerTelaLogin.class.getResource("/fonts/Roboto-Thin.ttf")
						.toExternalForm(), 12);

		paneNavBar.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				xOffset = LauncherTelaFinanceiro.stage.getX()
						- event.getScreenX();
				yOffset = LauncherTelaFinanceiro.stage.getY()
						- event.getScreenY();
			}
		});

		paneNavBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				LauncherTelaFinanceiro.stage.setX(event.getScreenX()
						+ xOffset);
				LauncherTelaFinanceiro.stage.setY(event.getScreenY()
						+ yOffset);
			}
		});
		javafx.collections.ObservableList<EnumTipoDespesa> ob = FXCollections
				.observableArrayList();
		ob.add(EnumTipoDespesa.Desp);
		ob.add(EnumTipoDespesa.Est);
		ob.add(EnumTipoDespesa.Manu);
		ob.add(EnumTipoDespesa.Salario);
		cbTipoDespesa.setItems(ob);

		// Aplicando Mascara
		MaskFieldUtil.monetaryField(tfValor);
		EventsText.upperCase(tfDescricao);

	}
}
