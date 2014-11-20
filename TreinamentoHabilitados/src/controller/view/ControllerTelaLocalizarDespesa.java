package controller.view;


import java.net.URL;
import java.sql.Date;
import java.text.NumberFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import controller.launcher.LauncherLocalizarDespesa;
import controller.view.ControllerTelaFuncionario.ItensProperty;
import model.Financeiro;
import model.Funcionario;
import model.enums.EnumTipoDespesa;
import model.repository.RepositoryFinanceiro;
import model.repository.RepositoryFuncionario;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class ControllerTelaLocalizarDespesa {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

   

    @FXML
    private DatePicker dtFinal;

    @FXML
    private Pane pnFundo;

    @FXML
    private DatePicker dtInicio;

    @FXML
    private Label lbTipoDeDespesa;

    @FXML
    private AnchorPane panceAnchor;

    @FXML
    private ImageView icLupa;

    @FXML
    private Pane paneNavBar;

    @FXML
    private ComboBox<EnumTipoDespesa> cbTipoDespesa;

    @FXML
    private Label lbFinanceiro;

    @FXML
    private TableColumn<ItensProperty,String> columnValor;
    
    @FXML
    private TableColumn<ItensProperty,String> columnFunc;
    
    @FXML
    private TableColumn<ItensProperty, String> columnTipo;

    @FXML
    private TableColumn<ItensProperty,String> columnDesc;
    
    @FXML
    private TableView<ItensProperty> tbDespesas;

  

    @FXML
    private ImageView icClose;

	protected double xOffset;

	protected double yOffset;
	
	private Financeiro financeiro;
	
	private RepositoryFinanceiro repoFinanceiro;
	
	public ControllerTelaLocalizarDespesa() {
		repoFinanceiro= new RepositoryFinanceiro();
	}

    @FXML
    void localizaDespesa(MouseEvent event) {
    	execPesquisa();
    }
    
    private void execPesquisa(){
    	try{
        	//Validações
        	if(dtInicio.getValue() == null || dtFinal.getValue() == null ){
        		JOptionPane.showMessageDialog(null, "Entre com uma data válida");
        	}else if(cbTipoDespesa.getSelectionModel().getSelectedIndex()<0){
        		JOptionPane.showMessageDialog(null, "Selecione um tipo de despesa");
        	}else {
        		if(dtInicio.getValue().isAfter(dtFinal.getValue())){
        			JOptionPane.showMessageDialog(null, "Coloque um intervalo válido");
        			return;
        		}
        		
        		Instant  instant = dtInicio.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant();
        		Date dtIni = new Date(instant.toEpochMilli());
        		instant = dtFinal.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant();
        		Date dtFim = new Date(instant.toEpochMilli());
        		List<Financeiro>ls = new ArrayList<Financeiro>();
        		
        		if(cbTipoDespesa.getSelectionModel().getSelectedItem() != EnumTipoDespesa.Todas){
        			ls = repoFinanceiro.buscarPorDataTipo(dtIni, dtFim, cbTipoDespesa.getSelectionModel().getSelectedItem());
        		}else {
        			ls = repoFinanceiro.buscarPorData(dtIni, dtFim);
        		}
        		ObservableList<ItensProperty> lsF = FXCollections.observableArrayList();
        		ls.forEach(e->{
        			lsF.add(new ItensProperty(e));
        		});
        		
        		System.out.println(ls.size());
        		
        		if(ls.size()>0){
        			tbDespesas.setItems(lsF);
        		}else {	
        			tbDespesas.getItems().clear();
        		}
        	}
        		
        	}
        	catch(Exception  e){
        		JOptionPane.showMessageDialog(null, e.getMessage());
        		e.printStackTrace();
        	}
    }

    @FXML
    void close(MouseEvent event) {
    	icClose.getScene().getWindow().hide();
    }

   
    
    @FXML
    void initialize() {
        assert columnFunc != null : "fx:id=\"columnFunc\" was not injected: check your FXML file 'telaLocalizar.fxml'.";
        assert dtFinal != null : "fx:id=\"dtFinal\" was not injected: check your FXML file 'telaLocalizar.fxml'.";
        assert pnFundo != null : "fx:id=\"pnFundo\" was not injected: check your FXML file 'telaLocalizar.fxml'.";
        assert dtInicio != null : "fx:id=\"dtInicio\" was not injected: check your FXML file 'telaLocalizar.fxml'.";
        assert lbTipoDeDespesa != null : "fx:id=\"lbTipoDeDespesa\" was not injected: check your FXML file 'telaLocalizar.fxml'.";
        assert panceAnchor != null : "fx:id=\"panceAnchor\" was not injected: check your FXML file 'telaLocalizar.fxml'.";
        assert icLupa != null : "fx:id=\"icLupa\" was not injected: check your FXML file 'telaLocalizar.fxml'.";
        assert paneNavBar != null : "fx:id=\"paneNavBar\" was not injected: check your FXML file 'telaLocalizar.fxml'.";
        assert cbTipoDespesa != null : "fx:id=\"cbTipoDespesa\" was not injected: check your FXML file 'telaLocalizar.fxml'.";
        assert lbFinanceiro != null : "fx:id=\"lbFinanceiro\" was not injected: check your FXML file 'telaLocalizar.fxml'.";
        assert columnValor != null : "fx:id=\"columnValor\" was not injected: check your FXML file 'telaLocalizar.fxml'.";
        assert tbDespesas != null : "fx:id=\"tbDespesas\" was not injected: check your FXML file 'telaLocalizar.fxml'.";
        assert columnDesc != null : "fx:id=\"columnDesc\" was not injected: check your FXML file 'telaLocalizar.fxml'.";
        assert icClose != null : "fx:id=\"icClose\" was not injected: check your FXML file 'telaLocalizar.fxml'.";
        assert columnTipo != null : "fx:id=\"columnTipo\" was not injected: check your FXML file 'telaLocalizar.fxml'.";

        try{
        Font.loadFont(
				ControllerTelaLogin.class.getResource("/fonts/Lobster.ttf")
						.toExternalForm(), 12);
		Font.loadFont(
				ControllerTelaLogin.class.getResource("/fonts/Roboto-Light.ttf")
						.toExternalForm(), 12);
		
		Font.loadFont(
				ControllerTelaLogin.class.getResource("/fonts/Roboto-Thin.ttf")
						.toExternalForm(), 12);
		
		paneNavBar.setOnMousePressed(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					xOffset = LauncherLocalizarDespesa.stage.getX()
							- event.getScreenX();
					yOffset = LauncherLocalizarDespesa.stage.getY()
							- event.getScreenY();
				}
			});

		 paneNavBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					LauncherLocalizarDespesa.stage.setX(event.getScreenX()
							+ xOffset);
					LauncherLocalizarDespesa.stage.setY(event.getScreenY()
							+ yOffset);
				}
			});
		 
		 tbDespesas.addEventHandler(MouseEvent.MOUSE_CLICKED,
					new EventHandler<MouseEvent>() {
				ContextMenu cm = new ContextMenu();
				MenuItem mi1;
				MenuItem mi2;

				@Override
				public void handle(MouseEvent t) {
					if (t.getButton() == MouseButton.SECONDARY) {

						ItensProperty f = tbDespesas
								.getSelectionModel().getSelectedItem();
						financeiro = f.getFinanceiro();
						
						cm.getItems().clear();
						mi1 = new MenuItem("Alterar");
						cm.getItems().add(mi1);
						mi2 = new MenuItem("Excluir");
						cm.getItems().add(mi2);

						mi1.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent evt) {
								
								ControllerTelaFinanceiro.tfDescricaoStatic.setText(f.getDescricao());
								ControllerTelaFinanceiro.tfValorStatic.setText(f.getValor());
								ControllerTelaFinanceiro.cbTipoDespesaStatic.getSelectionModel().select(f.getFinanceiro().getTipoPagamento());
								ControllerTelaFinanceiro.financeiro = financeiro;
								tbDespesas.getSelectionModel().clearSelection();
								tbDespesas.getScene().getWindow().hide();
							}
						});

						mi2.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent evt) {
								try{
									/*
									 * Implementar A Dialog de motivo de confirmação
									 * 
									repoFinanceiro.deleteDespesa(financeiro);
									populaTableView();
									limparCampos();
									*/
								}catch(Exception e){
									e.printStackTrace();
									JOptionPane.showMessageDialog(null, e.getMessage());
								}
							}
						});
						
						cm.show(tbDespesas, t.getScreenX(),
								t.getScreenY());
					} else if (t.getButton() == MouseButton.PRIMARY) {
						cm.hide();
					}
				}
			});

		 
		 
		 ObservableList<EnumTipoDespesa> ob = FXCollections.observableArrayList();
		 ob.addAll(EnumTipoDespesa.values());
		 
		 cbTipoDespesa.setItems(ob);
		
		 dtInicio.setValue(LocalDate.now());
		 dtFinal.setValue(LocalDate.now());
		 
		 populaTableView();
        }catch (Exception er){
        	
        }
    }
    
    private void limparCampos(){
    	dtInicio.setValue(LocalDate.now());
    	dtFinal.setValue(LocalDate.now());
    	cbTipoDespesa.getSelectionModel().select(-1);
    }
    
    
    private void populaTableView() throws Exception{
    
//    	repoFinanceiro;
		ObservableList<Financeiro> listFinanceiro = FXCollections.observableArrayList();
		ObservableList<ItensProperty> listItens = FXCollections.observableArrayList();
		
		columnDesc.setCellValueFactory(new PropertyValueFactory<ItensProperty, String>("Descricao"));
		columnValor.setCellValueFactory(new PropertyValueFactory<ItensProperty, String>("Valor"));
		columnFunc.setCellValueFactory(new PropertyValueFactory<ItensProperty, String>("Funcionario"));
		columnTipo.setCellValueFactory(new PropertyValueFactory<ItensProperty, String>("Tipo"));
		
		Instant  instant = dtInicio.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant();
		Date dtIni = new Date(instant.toEpochMilli());
		instant = dtFinal.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant();
		Date dtFim = new Date(instant.toEpochMilli());
		List<Financeiro>ls = new ArrayList<Financeiro>();
		
		ls = repoFinanceiro.buscarPorData(dtIni, dtFim);
		
		ObservableList<ItensProperty> lsF = FXCollections.observableArrayList();
		ls.forEach(e->{
			lsF.add(new ItensProperty(e));
		});
		
		tbDespesas.setItems(lsF);
		
    }
    
    public class ItensProperty {
		private final SimpleStringProperty descricao = new SimpleStringProperty("");
		private final SimpleStringProperty valor = new SimpleStringProperty("");
		private final SimpleStringProperty tipoDespesa = new SimpleStringProperty("");
		private final SimpleStringProperty funcionario = new SimpleStringProperty("");
		
		private Financeiro financeiro;

		public ItensProperty(Financeiro f) {
			financeiro = f;
			this.setDescricao((f.getDescricao()));
			this.setValor((f.getValor()+""));
			this.setTipoDespesa((f.getTipoPagamento().toString()));
//			this.setTipoDespesa("1");
			this.setFuncionario(f.getFuncionario().getNome());
			

		}
		

		public Financeiro getFinanceiro() {
			return financeiro;
		}
		
		public String getFuncionario(){
			return funcionario.get();
		}
		
		public void setFuncionario(String nomeFuncionario){
			funcionario.set(nomeFuncionario);
		}


		public String getDescricao() {
			return descricao.get();
		}

		public void setDescricao(String descricao) {
			this.descricao.set(descricao);
		}

		public String getValor() {
			NumberFormat formatter = NumberFormat.getCurrencyInstance();
			double t = Double.parseDouble(valor.get());
			String moneyString = formatter.format(t);
			return moneyString;
		}

		public void setValor(String valor) {
			this.valor.set(valor);
		}

		public String getTipo() {
			return financeiro.getTipoPagamento().toString();
		}

		public void setTipoDespesa(String tipoDespesa) {
			this.tipoDespesa.set(tipoDespesa);
		}

	}
    
}
