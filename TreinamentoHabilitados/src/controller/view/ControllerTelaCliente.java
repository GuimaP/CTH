package controller.view;

import java.io.FileInputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import controller.launcher.LauncherCameraWeb;
import controller.launcher.LauncherTelaCliente;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class ControllerTelaCliente {


    @FXML
    private TextField tfValorPago;

    @FXML
    private Button btAgendar;

    @FXML
    private Label lbComoSoube;

    @FXML
    private ImageView viewFoto;

    @FXML
    private Label lbAtuacao;

    @FXML
    private TableColumn<?, ?> statusDadosPaga;

    @FXML
    private TableColumn<?, ?> campoPreco;

    @FXML
    private Tab tableAgendamento;

    @FXML
    private Pane paneDadosAgendamento;

    @FXML
    private TableColumn<?, ?> campoDescricao;

    @FXML
    private TableView<?> tbStatusServicos;

    @FXML
    private Pane paneFotoCadastro;

    @FXML
    private TextField tfBairro;

    @FXML
    private TableColumn<?, ?> campoDescricaoServico;

    @FXML
    private Label lbInstrutores;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfAtuacao;

    @FXML
    private TabPane tablePane;

    @FXML
    private TextField tfEmail;

    @FXML
    private ImageView btClose;

    @FXML
    private Label lbCliente;

    @FXML
    private TableView<?> tableAulasAgendadas;

    @FXML
    private Label lbRua;

    @FXML
    private TableColumn<?, ?> campoPrecoDadosPaga;

    @FXML
    private Label lbTipoPagamento;

    @FXML
    private Label lbSelecionaCliente;

    @FXML
    private TextField tfRua;

    @FXML
    private Label lbDadosGerais;

    @FXML
    private Label lbNomeAluno;

    @FXML
    private Pane paneBuscaCliente;

    @FXML
    private Button btBuscaCliente;

    @FXML
    private Label lbValidadeCnh;

    @FXML
    private TextField tfQuantoTempo;

    @FXML
    private TableColumn<?, ?> campoStatus;

    @FXML
    private TextField tfCpf;

    @FXML
    private Label lbCpf;

    @FXML
    private TableColumn<?, ?> campoAulas;

    @FXML
    private Label lbFormacao;

    @FXML
    private ToggleGroup veiculo;

    @FXML
    private Label lbDataAgendamento;

    @FXML
    private TableView<?> tableServico;

    @FXML
    private Button btNovo;

    @FXML
    private TextField tfRg;

    @FXML
    private Label lbTelefone;

    @FXML
    private Label lbDataNascimento;

    @FXML
    private Label lbNavCliente;

    @FXML
    private Label lbTemVeiculo;

    @FXML
    private TableColumn<?, ?> campoNome;

    @FXML
    private Label lbValorPago;

    @FXML
    private Label lbRg;

    @FXML
    private Label lbNome;

    @FXML
    private DatePicker dtData;

    @FXML
    private Pane paneDadosPaga;

    @FXML
    private Pane paneFotoBusca;

    @FXML
    private Label lbDadosPagamento;

    @FXML
    private TableColumn<?, ?> campoServico;

    @FXML
    private Pane pnDadosPagamentos;

    @FXML
    private RadioButton rbTreinarNeleNao;

    @FXML
    private Label lbSexo;

    @FXML
    private Label lbDadosAgendamento;

    @FXML
    private TableColumn<?, ?> campoHora;

    @FXML
    private Label lbData;

    @FXML
    private RadioButton rbTemVeiculoNao;

    @FXML
    private Pane pnNavBar;

    @FXML
    private Label lbEmail;

    @FXML
    private TextField tfBuscaCliente;

    @FXML
    private Pane paneAulasAgendadas;

    @FXML
    private ComboBox<?> cbFormacao;

    @FXML
    private ToggleGroup treinar;

    @FXML
    private TextField tfNumero;

    @FXML
    private Label lbBairro;

    @FXML
    private Tab tableCadastro;

    @FXML
    private ComboBox<?> cbSexo;

    @FXML
    private Label lbObs;

    @FXML
    private TextField tfObs;

    @FXML
    private ComboBox<?> cbTipoPagamento;

    @FXML
    private RadioButton rbTreinarNeleSim;

    @FXML
    private Label lbCpfAluno;

    @FXML
    private DatePicker dtDataPermissao;

    @FXML
    private Button btSalvar;

    @FXML
    private Button btFoto;

    @FXML
    private Pane pnFundoTudo;

    @FXML
    private Pane pnAulasAgendadas;

    @FXML
    private Label lbHorarioAgendamento;

    @FXML
    private TextField tfNumeroCnh;

    @FXML
    private Pane paneDadosGerais;

    @FXML
    private Button btBuscarServico;

    @FXML
    private Label lbAulasAgendadas;

    @FXML
    private Label lbNumCnh;

    @FXML
    private Label lbPossivelTreinar;

    @FXML
    private Pane paneCliente;

    @FXML
    private Pane pnDadosCliente;

    @FXML
    private RadioButton rbTemVeiculoSim;

    @FXML
    private TextField tfBuscaServico;

    @FXML
    private TextField tfTel;

    @FXML
    private TextField tfValorPagar;

    @FXML
    private ComboBox<?> cbComoSoube;

    @FXML
    private Label lbDataPermissao;

    @FXML
    private ComboBox<?> cbHorarioAgendamento;

    @FXML
    private Pane paneServicos;

    @FXML
    private TableView<?> tableInstrutores;

    @FXML
    private Label lbServico;

    @FXML
    private Label lbCep;

    @FXML
    private DatePicker dtDataValidadeCnh;

    @FXML
    private Label lbQuantoTempo;

    @FXML
    private TableColumn<?, ?> camposValorPagoDadosPaga;

    @FXML
    private TextField tfCel;

    @FXML
    private Label lbCel;

    @FXML
    private Label lbNumero;

    @FXML
    private TextField tfCep;

    @FXML
    private AnchorPane paneAnchor;

    @FXML
    private Label lbValorPagar;

    @FXML
    private DatePicker dtDataNascimento;

    @FXML
    private TableColumn<?, ?> campoData;

    @FXML
    private Pane pnPesquisa;

    @FXML
    private DatePicker dtDataAgendamento;

    @FXML
    private TableColumn<?, ?> campoAula;
    
    @FXML
    private ImageView icEdit;
    
    @FXML
    private ImageView icLupa;

	protected double xOffset;

	protected double yOffset;

	protected static ImageView imgStaticView;
	
    @FXML
	void close(MouseEvent event) {
    	btClose.getScene().getWindow().hide();
    }
    
   
    @FXML
    void localizar(MouseEvent evt){
    	//Localizar
    }
    
    @FXML
    void edit(MouseEvent evt){
    	//EDIT
    }
    
    public static Pane panelFoto;

    
	    public ControllerTelaCliente() {
//			viewFoto = new ImageView();
		}
	    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	assert tfValorPago != null : "fx:id=\"tfValorPago\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert btAgendar != null : "fx:id=\"btAgendar\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert lbComoSoube != null : "fx:id=\"lbComoSoube\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert viewFoto != null : "fx:id=\"viewFoto\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert lbAtuacao != null : "fx:id=\"lbAtuacao\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert statusDadosPaga != null : "fx:id=\"statusDadosPaga\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert campoPreco != null : "fx:id=\"campoPreco\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert tableAgendamento != null : "fx:id=\"tableAgendamento\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert paneDadosAgendamento != null : "fx:id=\"paneDadosAgendamento\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert campoDescricao != null : "fx:id=\"campoDescricao\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert tbStatusServicos != null : "fx:id=\"tbStatusServicos\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert paneFotoCadastro != null : "fx:id=\"paneFotoCadastro\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert tfBairro != null : "fx:id=\"tfBairro\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert campoDescricaoServico != null : "fx:id=\"campoDescricaoServico\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert lbInstrutores != null : "fx:id=\"lbInstrutores\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert tfNome != null : "fx:id=\"tfNome\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert tfAtuacao != null : "fx:id=\"tfAtuacao\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert tablePane != null : "fx:id=\"tablePane\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert tfEmail != null : "fx:id=\"tfEmail\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert btClose != null : "fx:id=\"btClose\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert lbCliente != null : "fx:id=\"lbCliente\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert tableAulasAgendadas != null : "fx:id=\"tableAulasAgendadas\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert lbRua != null : "fx:id=\"lbRua\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert campoPrecoDadosPaga != null : "fx:id=\"campoPrecoDadosPaga\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert lbTipoPagamento != null : "fx:id=\"lbTipoPagamento\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert lbSelecionaCliente != null : "fx:id=\"lbSelecionaCliente\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert tfRua != null : "fx:id=\"tfRua\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert lbDadosGerais != null : "fx:id=\"lbDadosGerais\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert lbNomeAluno != null : "fx:id=\"lbNomeAluno\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert paneBuscaCliente != null : "fx:id=\"paneBuscaCliente\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert btBuscaCliente != null : "fx:id=\"btBuscaCliente\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert lbValidadeCnh != null : "fx:id=\"lbValidadeCnh\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert tfQuantoTempo != null : "fx:id=\"tfQuantoTempo\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert campoStatus != null : "fx:id=\"campoStatus\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert tfCpf != null : "fx:id=\"tfCpf\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert lbCpf != null : "fx:id=\"lbCpf\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert campoAulas != null : "fx:id=\"campoAulas\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert lbFormacao != null : "fx:id=\"lbFormacao\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert veiculo != null : "fx:id=\"veiculo\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert lbDataAgendamento != null : "fx:id=\"lbDataAgendamento\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert tableServico != null : "fx:id=\"tableServico\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert btNovo != null : "fx:id=\"btNovo\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert tfRg != null : "fx:id=\"tfRg\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert lbTelefone != null : "fx:id=\"lbTelefone\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert lbDataNascimento != null : "fx:id=\"lbDataNascimento\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert lbNavCliente != null : "fx:id=\"lbNavCliente\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert lbTemVeiculo != null : "fx:id=\"lbTemVeiculo\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert campoNome != null : "fx:id=\"campoNome\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert lbValorPago != null : "fx:id=\"lbValorPago\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert lbRg != null : "fx:id=\"lbRg\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert lbNome != null : "fx:id=\"lbNome\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert dtData != null : "fx:id=\"dtData\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert paneDadosPaga != null : "fx:id=\"paneDadosPaga\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert paneFotoBusca != null : "fx:id=\"paneFotoBusca\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert lbDadosPagamento != null : "fx:id=\"lbDadosPagamento\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert campoServico != null : "fx:id=\"campoServico\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert pnDadosPagamentos != null : "fx:id=\"pnDadosPagamentos\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert rbTreinarNeleNao != null : "fx:id=\"rbTreinarNeleNao\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert lbSexo != null : "fx:id=\"lbSexo\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert lbDadosAgendamento != null : "fx:id=\"lbDadosAgendamento\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert campoHora != null : "fx:id=\"campoHora\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert lbData != null : "fx:id=\"lbData\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert rbTemVeiculoNao != null : "fx:id=\"rbTemVeiculoNao\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert pnNavBar != null : "fx:id=\"pnNavBar\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert lbEmail != null : "fx:id=\"lbEmail\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert tfBuscaCliente != null : "fx:id=\"tfBuscaCliente\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert paneAulasAgendadas != null : "fx:id=\"paneAulasAgendadas\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert cbFormacao != null : "fx:id=\"cbFormacao\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert treinar != null : "fx:id=\"treinar\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert tfNumero != null : "fx:id=\"tfNumero\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert lbBairro != null : "fx:id=\"lbBairro\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert tableCadastro != null : "fx:id=\"tableCadastro\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert cbSexo != null : "fx:id=\"cbSexo\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert lbObs != null : "fx:id=\"lbObs\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert tfObs != null : "fx:id=\"tfObs\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert cbTipoPagamento != null : "fx:id=\"cbTipoPagamento\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert rbTreinarNeleSim != null : "fx:id=\"rbTreinarNeleSim\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert lbCpfAluno != null : "fx:id=\"lbCpfAluno\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert dtDataPermissao != null : "fx:id=\"dtDataPermissao\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert btSalvar != null : "fx:id=\"btSalvar\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert btFoto != null : "fx:id=\"btFoto\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert pnFundoTudo != null : "fx:id=\"pnFundoTudo\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert pnAulasAgendadas != null : "fx:id=\"pnAulasAgendadas\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert lbHorarioAgendamento != null : "fx:id=\"lbHorarioAgendamento\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert tfNumeroCnh != null : "fx:id=\"tfNumeroCnh\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert paneDadosGerais != null : "fx:id=\"paneDadosGerais\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert btBuscarServico != null : "fx:id=\"btBuscarServico\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert lbAulasAgendadas != null : "fx:id=\"lbAulasAgendadas\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert lbNumCnh != null : "fx:id=\"lbNumCnh\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert lbPossivelTreinar != null : "fx:id=\"lbPossivelTreinar\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert paneCliente != null : "fx:id=\"paneCliente\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert pnDadosCliente != null : "fx:id=\"pnDadosCliente\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert rbTemVeiculoSim != null : "fx:id=\"rbTemVeiculoSim\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert tfBuscaServico != null : "fx:id=\"tfBuscaServico\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert tfTel != null : "fx:id=\"tfTel\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert tfValorPagar != null : "fx:id=\"tfValorPagar\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert cbComoSoube != null : "fx:id=\"cbComoSoube\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert lbDataPermissao != null : "fx:id=\"lbDataPermissao\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert cbHorarioAgendamento != null : "fx:id=\"cbHorarioAgendamento\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert paneServicos != null : "fx:id=\"paneServicos\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert tableInstrutores != null : "fx:id=\"tableInstrutores\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert lbServico != null : "fx:id=\"lbServico\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert lbCep != null : "fx:id=\"lbCep\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert dtDataValidadeCnh != null : "fx:id=\"dtDataValidadeCnh\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert lbQuantoTempo != null : "fx:id=\"lbQuantoTempo\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert camposValorPagoDadosPaga != null : "fx:id=\"camposValorPagoDadosPaga\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert tfCel != null : "fx:id=\"tfCel\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert lbCel != null : "fx:id=\"lbCel\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert lbNumero != null : "fx:id=\"lbNumero\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert tfCep != null : "fx:id=\"tfCep\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert paneAnchor != null : "fx:id=\"paneAnchor\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert lbValorPagar != null : "fx:id=\"lbValorPagar\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert dtDataNascimento != null : "fx:id=\"dtDataNascimento\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert campoData != null : "fx:id=\"campoData\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert pnPesquisa != null : "fx:id=\"pnPesquisa\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert dtDataAgendamento != null : "fx:id=\"dtDataAgendamento\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert campoAula != null : "fx:id=\"campoAula\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert icEdit != null : "fx:id=\"icEdit\" was not injected: check your FXML file 'telaCliente.fxml'.";
        
          panelFoto = paneFotoCadastro;
          imgStaticView = viewFoto;
          icEdit.setVisible(false);
          
          pnNavBar.setOnMousePressed(new EventHandler<MouseEvent>() {
              @Override
              public void handle(MouseEvent event) {
                  xOffset = LauncherTelaCliente.stage.getX() - event.getScreenX();
                  yOffset = LauncherTelaCliente.stage.getY() - event.getScreenY();
              }
          });
  		
          pnNavBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
              @Override
              public void handle(MouseEvent event) {
            	  LauncherTelaCliente.stage.setX(event.getScreenX() + xOffset);
            	  LauncherTelaCliente.stage.setY(event.getScreenY() + yOffset);
              }
          });
          
          Font.loadFont(
  				ControllerTelaLogin.class.getResource("/fonts/Roboto-Light.ttf")
  						.toExternalForm(), 12);
          
          Font.loadFont(
    				ControllerTelaLogin.class.getResource("/fonts/Roboto-Thin.ttf")
    						.toExternalForm(), 12);
    }
    
    private boolean validaCampos(){
    	if(tfNome.getText().isEmpty() || tfNome.getText().length() < 3){
    		JOptionPane.showMessageDialog(null, "Insira um nome v�lido superior a 2 caracteres");
    		return false;
    	}
    	
    	if(dtData.getValue() == null){
    		JOptionPane.showMessageDialog(null, "Insira uma data Valida");
    		return false;
    	}
    	
    	if(tfRua.getText().isEmpty() || tfRua.getText().length() < 5){
    		JOptionPane.showMessageDialog(null, "Insira uma rua v�lida, superior a 5 caracteres");
    		return false;
    	}
    	
    	if(tfNumero.getText().isEmpty()){
    		JOptionPane.showMessageDialog(null, "Insira um n�mero v�lido");
    		return false;
    	}
    	
    	if(tfBairro.getText().isEmpty() || tfBairro.getText().length() < 4){
    		JOptionPane.showMessageDialog(null, "Insira um bairro v�lido, superior a 4 caracteres");
    		return false;
    	}
    	
    	if(tfCep.getText().replaceAll("-", "").isEmpty()){
    		JOptionPane.showMessageDialog(null, "Insira um CEP valido");
    		return false;
    	}
    	
    	return true;
    }
    
    @FXML
    void abrirCamera(){
    	ControllerWebCam.defineValores("Cliente");
    	new LauncherCameraWeb().start(LauncherTelaCliente.stage);
    	
    }
}
