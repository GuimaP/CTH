/**
 * Sample Skeleton for 'telaCliente.fxml' Controller Class
 */

import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class ControllerTelaCliente {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="tfValorPago"
    private TextField tfValorPago; // Value injected by FXMLLoader

    @FXML // fx:id="btAgendar"
    private Button btAgendar; // Value injected by FXMLLoader

    @FXML // fx:id="lbComoSoube"
    private Label lbComoSoube; // Value injected by FXMLLoader

    @FXML // fx:id="lbAtuacao"
    private Label lbAtuacao; // Value injected by FXMLLoader

    @FXML // fx:id="statusDadosPaga"
    private TableColumn<?, ?> statusDadosPaga; // Value injected by FXMLLoader

    @FXML // fx:id="campoPreco"
    private TableColumn<?, ?> campoPreco; // Value injected by FXMLLoader

    @FXML // fx:id="tableAgendamento"
    private Tab tableAgendamento; // Value injected by FXMLLoader

    @FXML // fx:id="paneDadosAgendamento"
    private Pane paneDadosAgendamento; // Value injected by FXMLLoader

    @FXML // fx:id="campoDescricao"
    private TableColumn<?, ?> campoDescricao; // Value injected by FXMLLoader

    @FXML // fx:id="tbStatusServicos"
    private TableView<?> tbStatusServicos; // Value injected by FXMLLoader

    @FXML // fx:id="paneFotoCadastro"
    private Pane paneFotoCadastro; // Value injected by FXMLLoader

    @FXML // fx:id="tfBairro"
    private TextField tfBairro; // Value injected by FXMLLoader

    @FXML // fx:id="campoDescricaoServico"
    private TableColumn<?, ?> campoDescricaoServico; // Value injected by FXMLLoader

    @FXML // fx:id="lbInstrutores"
    private Label lbInstrutores; // Value injected by FXMLLoader

    @FXML // fx:id="tfNome"
    private TextField tfNome; // Value injected by FXMLLoader

    @FXML // fx:id="tfAtuacao"
    private TextField tfAtuacao; // Value injected by FXMLLoader

    @FXML // fx:id="tablePane"
    private TabPane tablePane; // Value injected by FXMLLoader

    @FXML // fx:id="tfEmail"
    private TextField tfEmail; // Value injected by FXMLLoader

    @FXML // fx:id="lbCliente"
    private Label lbCliente; // Value injected by FXMLLoader

    @FXML // fx:id="tableAulasAgendadas"
    private TableView<?> tableAulasAgendadas; // Value injected by FXMLLoader

    @FXML // fx:id="lbRua"
    private Label lbRua; // Value injected by FXMLLoader

    @FXML // fx:id="campoPrecoDadosPaga"
    private TableColumn<?, ?> campoPrecoDadosPaga; // Value injected by FXMLLoader

    @FXML // fx:id="lbTipoPagamento"
    private Label lbTipoPagamento; // Value injected by FXMLLoader

    @FXML // fx:id="lbSelecionaCliente"
    private Label lbSelecionaCliente; // Value injected by FXMLLoader

    @FXML // fx:id="tfRua"
    private TextField tfRua; // Value injected by FXMLLoader

    @FXML // fx:id="lbDadosGerais"
    private Label lbDadosGerais; // Value injected by FXMLLoader

    @FXML // fx:id="lbNomeAluno"
    private Label lbNomeAluno; // Value injected by FXMLLoader

    @FXML // fx:id="paneBuscaCliente"
    private Pane paneBuscaCliente; // Value injected by FXMLLoader

    @FXML // fx:id="btBuscaCliente"
    private Button btBuscaCliente; // Value injected by FXMLLoader

    @FXML // fx:id="lbValidadeCnh"
    private Label lbValidadeCnh; // Value injected by FXMLLoader

    @FXML // fx:id="tfQuantoTempo"
    private TextField tfQuantoTempo; // Value injected by FXMLLoader

    @FXML // fx:id="campoStatus"
    private TableColumn<?, ?> campoStatus; // Value injected by FXMLLoader

    @FXML // fx:id="tfCpf"
    private TextField tfCpf; // Value injected by FXMLLoader

    @FXML // fx:id="lbCpf"
    private Label lbCpf; // Value injected by FXMLLoader

    @FXML // fx:id="campoAulas"
    private TableColumn<?, ?> campoAulas; // Value injected by FXMLLoader

    @FXML // fx:id="veiculo"
    private ToggleGroup veiculo; // Value injected by FXMLLoader

    @FXML // fx:id="lbFormacao"
    private Label lbFormacao; // Value injected by FXMLLoader

    @FXML // fx:id="lbDataAgendamento"
    private Label lbDataAgendamento; // Value injected by FXMLLoader

    @FXML // fx:id="tableServico"
    private TableView<?> tableServico; // Value injected by FXMLLoader

    @FXML // fx:id="btNovo"
    private Button btNovo; // Value injected by FXMLLoader

    @FXML // fx:id="tfRg"
    private TextField tfRg; // Value injected by FXMLLoader

    @FXML // fx:id="lbTelefone"
    private Label lbTelefone; // Value injected by FXMLLoader

    @FXML // fx:id="lbDataNascimento"
    private Label lbDataNascimento; // Value injected by FXMLLoader

    @FXML // fx:id="lbNavCliente"
    private Label lbNavCliente; // Value injected by FXMLLoader

    @FXML // fx:id="lbTemVeiculo"
    private Label lbTemVeiculo; // Value injected by FXMLLoader

    @FXML // fx:id="campoNome"
    private TableColumn<?, ?> campoNome; // Value injected by FXMLLoader

    @FXML // fx:id="lbValorPago"
    private Label lbValorPago; // Value injected by FXMLLoader

    @FXML // fx:id="lbRg"
    private Label lbRg; // Value injected by FXMLLoader

    @FXML // fx:id="lbNome"
    private Label lbNome; // Value injected by FXMLLoader

    @FXML // fx:id="dtData"
    private DatePicker dtData; // Value injected by FXMLLoader

    @FXML // fx:id="paneDadosPaga"
    private Pane paneDadosPaga; // Value injected by FXMLLoader

    @FXML // fx:id="paneFotoBusca"
    private Pane paneFotoBusca; // Value injected by FXMLLoader

    @FXML // fx:id="campoServico"
    private TableColumn<?, ?> campoServico; // Value injected by FXMLLoader

    @FXML // fx:id="lbDadosPagamento"
    private Label lbDadosPagamento; // Value injected by FXMLLoader

    @FXML // fx:id="rbTreinarNeleNao"
    private RadioButton rbTreinarNeleNao; // Value injected by FXMLLoader

    @FXML // fx:id="lbSexo"
    private Label lbSexo; // Value injected by FXMLLoader

    @FXML // fx:id="lbDadosAgendamento"
    private Label lbDadosAgendamento; // Value injected by FXMLLoader

    @FXML // fx:id="campoHora"
    private TableColumn<?, ?> campoHora; // Value injected by FXMLLoader

    @FXML // fx:id="lbData"
    private Label lbData; // Value injected by FXMLLoader

    @FXML // fx:id="rbTemVeiculoNao"
    private RadioButton rbTemVeiculoNao; // Value injected by FXMLLoader

    @FXML // fx:id="pnNavBar"
    private Pane pnNavBar; // Value injected by FXMLLoader

    @FXML // fx:id="lbEmail"
    private Label lbEmail; // Value injected by FXMLLoader

    @FXML // fx:id="tfBuscaCliente"
    private TextField tfBuscaCliente; // Value injected by FXMLLoader

    @FXML // fx:id="paneAulasAgendadas"
    private Pane paneAulasAgendadas; // Value injected by FXMLLoader

    @FXML // fx:id="cbFormacao"
    private ComboBox<?> cbFormacao; // Value injected by FXMLLoader

    @FXML // fx:id="treinar"
    private ToggleGroup treinar; // Value injected by FXMLLoader

    @FXML // fx:id="tfNumero"
    private TextField tfNumero; // Value injected by FXMLLoader

    @FXML // fx:id="lbBairro"
    private Label lbBairro; // Value injected by FXMLLoader

    @FXML // fx:id="tableCadastro"
    private Tab tableCadastro; // Value injected by FXMLLoader

    @FXML // fx:id="cbSexo"
    private ComboBox<?> cbSexo; // Value injected by FXMLLoader

    @FXML // fx:id="lbObs"
    private Label lbObs; // Value injected by FXMLLoader

    @FXML // fx:id="tfObs"
    private TextField tfObs; // Value injected by FXMLLoader

    @FXML // fx:id="cbTipoPagamento"
    private ComboBox<?> cbTipoPagamento; // Value injected by FXMLLoader

    @FXML // fx:id="rbTreinarNeleSim"
    private RadioButton rbTreinarNeleSim; // Value injected by FXMLLoader

    @FXML // fx:id="lbCpfAluno"
    private Label lbCpfAluno; // Value injected by FXMLLoader

    @FXML // fx:id="dtDataPermissao"
    private DatePicker dtDataPermissao; // Value injected by FXMLLoader

    @FXML // fx:id="btSalvar"
    private Button btSalvar; // Value injected by FXMLLoader

    @FXML // fx:id="btFoto"
    private Button btFoto; // Value injected by FXMLLoader

    @FXML // fx:id="lbHorarioAgendamento"
    private Label lbHorarioAgendamento; // Value injected by FXMLLoader

    @FXML // fx:id="tfNumeroCnh"
    private TextField tfNumeroCnh; // Value injected by FXMLLoader

    @FXML // fx:id="paneDadosGerais"
    private Pane paneDadosGerais; // Value injected by FXMLLoader

    @FXML // fx:id="btBuscarServico"
    private Button btBuscarServico; // Value injected by FXMLLoader

    @FXML // fx:id="lbAulasAgendadas"
    private Label lbAulasAgendadas; // Value injected by FXMLLoader

    @FXML // fx:id="lbNumCnh"
    private Label lbNumCnh; // Value injected by FXMLLoader

    @FXML // fx:id="lbPossivelTreinar"
    private Label lbPossivelTreinar; // Value injected by FXMLLoader

    @FXML // fx:id="paneCliente"
    private Pane paneCliente; // Value injected by FXMLLoader

    @FXML // fx:id="rbTemVeiculoSim"
    private RadioButton rbTemVeiculoSim; // Value injected by FXMLLoader

    @FXML // fx:id="tfBuscaServico"
    private TextField tfBuscaServico; // Value injected by FXMLLoader

    @FXML // fx:id="tfTel"
    private TextField tfTel; // Value injected by FXMLLoader

    @FXML // fx:id="tfValorPagar"
    private TextField tfValorPagar; // Value injected by FXMLLoader

    @FXML // fx:id="cbComoSoube"
    private ComboBox<?> cbComoSoube; // Value injected by FXMLLoader

    @FXML // fx:id="lbDataPermissao"
    private Label lbDataPermissao; // Value injected by FXMLLoader

    @FXML // fx:id="cbHorarioAgendamento"
    private ComboBox<?> cbHorarioAgendamento; // Value injected by FXMLLoader

    @FXML // fx:id="paneServicos"
    private Pane paneServicos; // Value injected by FXMLLoader

    @FXML // fx:id="tableInstrutores"
    private TableView<?> tableInstrutores; // Value injected by FXMLLoader

    @FXML // fx:id="dtDataValidadeCnh"
    private DatePicker dtDataValidadeCnh; // Value injected by FXMLLoader

    @FXML // fx:id="lbCep"
    private Label lbCep; // Value injected by FXMLLoader

    @FXML // fx:id="lbServico"
    private Label lbServico; // Value injected by FXMLLoader

    @FXML // fx:id="lbQuantoTempo"
    private Label lbQuantoTempo; // Value injected by FXMLLoader

    @FXML // fx:id="camposValorPagoDadosPaga"
    private TableColumn<?, ?> camposValorPagoDadosPaga; // Value injected by FXMLLoader

    @FXML // fx:id="tfCel"
    private TextField tfCel; // Value injected by FXMLLoader

    @FXML // fx:id="lbCel"
    private Label lbCel; // Value injected by FXMLLoader

    @FXML // fx:id="lbNumero"
    private Label lbNumero; // Value injected by FXMLLoader

    @FXML // fx:id="tfCep"
    private TextField tfCep; // Value injected by FXMLLoader

    @FXML // fx:id="paneAnchor"
    private AnchorPane paneAnchor; // Value injected by FXMLLoader

    @FXML // fx:id="lbValorPagar"
    private Label lbValorPagar; // Value injected by FXMLLoader

    @FXML // fx:id="dtDataNascimento"
    private DatePicker dtDataNascimento; // Value injected by FXMLLoader

    @FXML // fx:id="campoData"
    private TableColumn<?, ?> campoData; // Value injected by FXMLLoader

    @FXML // fx:id="dtDataAgendamento"
    private DatePicker dtDataAgendamento; // Value injected by FXMLLoader

    @FXML // fx:id="campoAula"
    private TableColumn<?, ?> campoAula; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert tfValorPago != null : "fx:id=\"tfValorPago\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert btAgendar != null : "fx:id=\"btAgendar\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert lbComoSoube != null : "fx:id=\"lbComoSoube\" was not injected: check your FXML file 'telaCliente.fxml'.";
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
        assert veiculo != null : "fx:id=\"veiculo\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert lbFormacao != null : "fx:id=\"lbFormacao\" was not injected: check your FXML file 'telaCliente.fxml'.";
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
        assert campoServico != null : "fx:id=\"campoServico\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert lbDadosPagamento != null : "fx:id=\"lbDadosPagamento\" was not injected: check your FXML file 'telaCliente.fxml'.";
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
        assert lbHorarioAgendamento != null : "fx:id=\"lbHorarioAgendamento\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert tfNumeroCnh != null : "fx:id=\"tfNumeroCnh\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert paneDadosGerais != null : "fx:id=\"paneDadosGerais\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert btBuscarServico != null : "fx:id=\"btBuscarServico\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert lbAulasAgendadas != null : "fx:id=\"lbAulasAgendadas\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert lbNumCnh != null : "fx:id=\"lbNumCnh\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert lbPossivelTreinar != null : "fx:id=\"lbPossivelTreinar\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert paneCliente != null : "fx:id=\"paneCliente\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert rbTemVeiculoSim != null : "fx:id=\"rbTemVeiculoSim\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert tfBuscaServico != null : "fx:id=\"tfBuscaServico\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert tfTel != null : "fx:id=\"tfTel\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert tfValorPagar != null : "fx:id=\"tfValorPagar\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert cbComoSoube != null : "fx:id=\"cbComoSoube\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert lbDataPermissao != null : "fx:id=\"lbDataPermissao\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert cbHorarioAgendamento != null : "fx:id=\"cbHorarioAgendamento\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert paneServicos != null : "fx:id=\"paneServicos\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert tableInstrutores != null : "fx:id=\"tableInstrutores\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert dtDataValidadeCnh != null : "fx:id=\"dtDataValidadeCnh\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert lbCep != null : "fx:id=\"lbCep\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert lbServico != null : "fx:id=\"lbServico\" was not injected: check your FXML file 'telaCliente.fxml'.";
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
        assert dtDataAgendamento != null : "fx:id=\"dtDataAgendamento\" was not injected: check your FXML file 'telaCliente.fxml'.";
        assert campoAula != null : "fx:id=\"campoAula\" was not injected: check your FXML file 'telaCliente.fxml'.";

    }
}
