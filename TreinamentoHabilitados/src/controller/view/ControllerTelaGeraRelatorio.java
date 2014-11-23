package controller.view;

import java.net.URL;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import controller.ReportUtil;
import exceptions.ErrorRelatorioException;
import model.enums.EnumTipoDeRelatorio;
import modelo.report.JRDataSourceFactory;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class ControllerTelaGeraRelatorio {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private RadioButton radioHtml;

	@FXML
	private Label lbTipoDeRelatorio;

	@FXML
	private ImageView icHtml;

	@FXML
	private RadioButton radioExcel;

	@FXML
	private DatePicker dtFinal;

	@FXML
	private Pane pnFundo;

	@FXML
	private DatePicker dtInicio;

	@FXML
	private ImageView icPDF;

	@FXML
	private Button btGerar;

	@FXML
	private AnchorPane panceAnchor;

	@FXML
	private RadioButton radioPDF;

	@FXML
	private Pane paneNavBar;

	@FXML
	private ComboBox<EnumTipoDeRelatorio> cbTipoRelatorio;

	@FXML
	private Label lbFinanceiro;

	@FXML
	private ImageView icExcel;

	@FXML
	private ImageView icClose;

	@FXML
	private CheckBox checkTodos;

	private JRDataSource data = null;
	private String nomeRelatorioString = "Relatorio";

	@FXML
	void selectPDF(MouseEvent event) {
		radioPDF.setSelected(true);
	}

	@FXML
	void GerarRelatorio(ActionEvent event) {
		try {
			// Verifica se ï¿½ uma pesquisa geral
			if (checkTodos.isSelected()) {
				// Verifica se possui um tipo relatorio
				if (cbTipoRelatorio.getSelectionModel().getSelectedIndex() < 0) {
					JOptionPane.showMessageDialog(null,
							"Selecione um Tipo de Relatorio");

				} else {

					// Ve o tipo de relatorio para montar a pesquisa
					if (cbTipoRelatorio.getSelectionModel().getSelectedItem() == EnumTipoDeRelatorio.Financeiro) {
						nomeRelatorioString = "Financeiro ";
						data = JRDataSourceFactory
								.createDatasource(JRDataSourceFactory.FINANCEIRO_LISTAGEM);

						generateReport();

					} else if (cbTipoRelatorio.getSelectionModel()
							.getSelectedItem() == EnumTipoDeRelatorio.AulasPorInstrutor) {

					}
				}

			} else {// Se nï¿½o, ï¿½ um relatorio por data

				// Verifica se possui uma data Valida
				if (dtInicio.getValue() != null & dtFinal.getValue() != null) {
					if (dtInicio.getValue().isAfter(dtFinal.getValue())) {
						throw new ErrorRelatorioException(
								"Data inicial maior que a data final");
					}

					if (cbTipoRelatorio.getSelectionModel().getSelectedIndex() < 0) {
						JOptionPane.showMessageDialog(null,
								"Selecione um Tipo de Relatorio");
					} else {

						Instant instantInicio = dtInicio.getValue()
								.atStartOfDay(ZoneId.systemDefault())
								.toInstant();
						Date dtInicio = new Date(instantInicio.toEpochMilli());

						Instant instantFinal = dtFinal.getValue()
								.atStartOfDay(ZoneId.systemDefault())
								.toInstant();
						Date dtFinal = new Date(instantFinal.toEpochMilli());

						nomeRelatorioString = "Financeiro "
								+ dtInicio.toString().replaceAll("/", "-")
								+ " atï¿½ "
								+ dtFinal.toString().replaceAll("/", "-");

						data = JRDataSourceFactory.createDatasourcePorData(
								JRDataSourceFactory.FINANCEIRO_LISTAGEM,
								dtInicio, dtFinal);

						btGerar.setDisable(true);
						generateReport();
						btGerar.setDisable(false);
						JOptionPane
								.showMessageDialog(null, "Relatorio Gerado!");
						limpaCampos();
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Entre com uma data válida");
				}
			}
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		} catch (ErrorRelatorioException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
		btGerar.setDisable(false);
	}

	private void limpaCampos() {
		dtFinal.setValue(LocalDate.now());
		dtInicio.setValue(LocalDate.now());
		cbTipoRelatorio.getSelectionModel().select(-1);
		radioExcel.setSelected(false);
		radioHtml.setSelected(false);
		radioPDF.setSelected(false);
		checkTodos.setSelected(false);

	}

	private void generateReport() throws JRException, ErrorRelatorioException {

		// Verificando o tipo de Relatorio
		if (cbTipoRelatorio.getSelectionModel().getSelectedItem() == EnumTipoDeRelatorio.Financeiro) {
			// Escolhe o tipo de formato
			if (radioExcel.isSelected()) {
				data = JRDataSourceFactory
						.createDatasource(JRDataSourceFactory.FINANCEIRO_LISTAGEM);
				ReportUtil.gerarRelatorio(data, ReportUtil.FINANCEIRO_LISTAGEM,
						nomeRelatorioString, ReportUtil.EXCEL_REPORT);

			} else if (radioHtml.isSelected()) {

				data = JRDataSourceFactory
						.createDatasource(JRDataSourceFactory.FINANCEIRO_LISTAGEM);

				ReportUtil.gerarRelatorio(data, ReportUtil.FINANCEIRO_LISTAGEM,
						nomeRelatorioString, ReportUtil.HTML_REPORT);

			} else if (radioPDF.isSelected()) {
				data = JRDataSourceFactory
						.createDatasource(JRDataSourceFactory.FINANCEIRO_LISTAGEM);
				ReportUtil.gerarRelatorio(data, ReportUtil.FINANCEIRO_LISTAGEM,
						nomeRelatorioString, ReportUtil.PDF_REPORT);
			} else {
				// TODO
				JOptionPane.showMessageDialog(null,
						"Selecione um tipo de saida");
			}
		}

	}

	@FXML
	void selectExcel(MouseEvent event) {
		radioExcel.setSelected(true);
	}

	@FXML
	void selectHtml(MouseEvent event) {
		radioHtml.setSelected(true);
	}

	@FXML
	void close(MouseEvent event) {
		icClose.getScene().getWindow().hide();
	}

	@FXML
	void initialize() {
		assert radioHtml != null : "fx:id=\"radioHtml\" was not injected: check your FXML file 'telaRelatorio.fxml'.";
		assert lbTipoDeRelatorio != null : "fx:id=\"lbTipoDeRelatorio\" was not injected: check your FXML file 'telaRelatorio.fxml'.";
		assert icHtml != null : "fx:id=\"icHtml\" was not injected: check your FXML file 'telaRelatorio.fxml'.";
		assert radioExcel != null : "fx:id=\"radioExcel\" was not injected: check your FXML file 'telaRelatorio.fxml'.";
		assert dtFinal != null : "fx:id=\"dtFinal\" was not injected: check your FXML file 'telaRelatorio.fxml'.";
		assert pnFundo != null : "fx:id=\"pnFundo\" was not injected: check your FXML file 'telaRelatorio.fxml'.";
		assert dtInicio != null : "fx:id=\"dtInicio\" was not injected: check your FXML file 'telaRelatorio.fxml'.";
		assert icPDF != null : "fx:id=\"icPDF\" was not injected: check your FXML file 'telaRelatorio.fxml'.";
		assert btGerar != null : "fx:id=\"btGerar\" was not injected: check your FXML file 'telaRelatorio.fxml'.";
		assert panceAnchor != null : "fx:id=\"panceAnchor\" was not injected: check your FXML file 'telaRelatorio.fxml'.";
		assert radioPDF != null : "fx:id=\"radioPDF\" was not injected: check your FXML file 'telaRelatorio.fxml'.";
		assert paneNavBar != null : "fx:id=\"paneNavBar\" was not injected: check your FXML file 'telaRelatorio.fxml'.";
		assert cbTipoRelatorio != null : "fx:id=\"cbTipoRelatorio\" was not injected: check your FXML file 'telaRelatorio.fxml'.";
		assert lbFinanceiro != null : "fx:id=\"lbFinanceiro\" was not injected: check your FXML file 'telaRelatorio.fxml'.";
		assert icExcel != null : "fx:id=\"icExcel\" was not injected: check your FXML file 'telaRelatorio.fxml'.";
		assert icClose != null : "fx:id=\"icClose\" was not injected: check your FXML file 'telaRelatorio.fxml'.";
		assert checkTodos != null : "fx:id=\"checkTodos\" was not injected: check your FXML file 'telaRelatorio.fxml'.";

		dtFinal.setValue(LocalDate.now());
		dtInicio.setValue(LocalDate.now());

		final ToggleGroup group = new ToggleGroup();
		radioExcel.setToggleGroup(group);
		radioHtml.setToggleGroup(group);
		radioPDF.setToggleGroup(group);

		ObservableList<EnumTipoDeRelatorio> tipoRelatorio = FXCollections
				.observableArrayList();
		tipoRelatorio.add(EnumTipoDeRelatorio.Financeiro);
		tipoRelatorio.add(EnumTipoDeRelatorio.AulasPorInstrutor);

		cbTipoRelatorio.setItems(tipoRelatorio);

	}

}
