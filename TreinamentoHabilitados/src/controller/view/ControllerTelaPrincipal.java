package controller.view;
/**

 * Sample Skeleton for 'telaPrincipal.fxml' Controller Class
 */

import java.io.Closeable;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.CloseAction;

import org.controlsfx.dialog.Dialogs;
import org.eclipse.swt.events.DisposeEvent;

import com.sun.media.controls.ActionControl;

import controller.launcher.LauncherPrincipal;
import controller.launcher.LauncherTelaGeraRelatorio;
import controller.launcher.LauncherTelaCarro;
import controller.launcher.LauncherTelaCliente;
import controller.launcher.LauncherTelaFinanceiro;
import controller.launcher.LauncherTelaFuncionario;
import controller.launcher.LauncherTelaInstrutor;
import controller.launcher.LauncherTelaServico;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class ControllerTelaPrincipal {
	
	public static boolean telaCarro, telaCliente, telaServico, telaFuncionario, telaInstrutor,telaFinanceiro;

	public static boolean telaLocalizar;

	
	
	public ControllerTelaPrincipal(){
		
		telaCarro = telaCliente = telaServico = telaFuncionario = telaInstrutor = telaFinanceiro = false;
	}

	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane paneAnchor;

    @FXML
    private ImageView icoCarro;

    @FXML
    private ImageView icoPacote;

    @FXML
    private Pane paneNavBar;

    @FXML
    private ImageView icoFuncionario;

    @FXML
    private ImageView icoCliente;
    
    @FXML
    private ImageView icFinanceiro;

    @FXML
    private Pane pnLateral;

    @FXML
    private ImageView icoMinimize;

    @FXML
    private ImageView icoMinimize1;

    @FXML
    private ImageView icoInstrutor;

    @FXML
    private ImageView icClose;
    
    @FXML
    private ImageView icRelatorio;

protected double xOffset;

protected double yOffset;



	   

@FXML
void brInstrutor(MouseEvent event) {
   	try {
    	if (!ControllerTelaPrincipal.telaInstrutor) {
    		ControllerTelaPrincipal.telaInstrutor = true;
   		
				new LauncherTelaInstrutor().start(new Stage());	
		}
   	}catch(Exception e){
		Dialogs.create().owner(LauncherPrincipal.stage)
		.title("Erro").masthead("Parece que ocorreu um erro ao abrir essa tela")
		.message("Por favor, contate o administrador do sistema").showError();
	}
    }


@SuppressWarnings("deprecation")
@FXML
void abreCarro(MouseEvent event){
	try{
	if (!ControllerTelaPrincipal.telaCarro) {
		ControllerTelaPrincipal.telaCarro = true;
		new LauncherTelaCarro().start(LauncherPrincipal.stage);
		
	}
	}catch(Exception e){
		Dialogs.create().owner(LauncherPrincipal.stage)
		.title("Erro").masthead("Parece que ocorreu um erro ao abrir essa tela")
		.message("Por favor, contate o administrador do sistema").showError();
		e.printStackTrace();
	}
}

@FXML
void abreInstrutor(MouseEvent event) {
try{
	if (!ControllerTelaPrincipal.telaInstrutor) {
		ControllerTelaPrincipal.telaInstrutor = true;
		new LauncherTelaInstrutor().start(LauncherPrincipal.stage);
	}
}catch(Exception e){
	Dialogs.create().owner(LauncherPrincipal.stage)
	.title("Erro").masthead("Parece que ocorreu um erro ao abrir essa tela")
	.message("Por favor, contate o administrador do sistema").showError();
}
}

@FXML
void abreFuncionario(MouseEvent event){
	try{
	if (!ControllerTelaPrincipal.telaFuncionario) {
		ControllerTelaPrincipal.telaFuncionario = true;
		new LauncherTelaFuncionario().start(LauncherPrincipal.stage);
	}
	}catch(Exception e){
		Dialogs.create().owner(LauncherPrincipal.stage)
		.title("Erro").masthead("Parece que ocorreu um erro ao abrir essa tela")
		.message("Por favor, contate o administrador do sistema").showError();
	}
	
}

@FXML
void abreCliente(MouseEvent event)  {
	try{
	if (!ControllerTelaPrincipal.telaCliente) {
		ControllerTelaPrincipal.telaCliente = true;
		new LauncherTelaCliente().start(LauncherPrincipal.stage);
	}
	}catch(Exception e){
		Dialogs.create().owner(LauncherPrincipal.stage)
		.title("Erro").masthead("Parece que ocorreu um erro ao abrir essa tela")
		.message("Por favor, contate o administrador do sistema").showError();
	}
	

}

@FXML
void minimize(MouseEvent event) {
	LauncherPrincipal.stage.setIconified(true);
}

@FXML
void close(MouseEvent event) {
	icClose.getScene().getWindow().hide();
}

@FXML
void maximize(MouseEvent event) {
	LauncherPrincipal.stage.setFullScreen(true);
}


@FXML
void abrePacote(MouseEvent event)   {
	try{
	if (!ControllerTelaPrincipal.telaServico) {
		ControllerTelaPrincipal.telaServico = true;
		new LauncherTelaServico().start(LauncherPrincipal.stage);
	}
	}catch(Exception e){
		Dialogs.create().owner(LauncherPrincipal.stage)
		.title("Erro").masthead("Parece que ocorreu um erro ao abrir essa tela")
		.message("Por favor, contate o administrador do sistema").showError();
	}

}

@FXML
void abreFinanceiro(MouseEvent event) {
	try{
	if (!ControllerTelaFinanceiro.telaFinanceiro) {
		ControllerTelaPrincipal.telaFinanceiro = true;
		new LauncherTelaFinanceiro().start(LauncherPrincipal.stage);
	}
	}catch(Exception e){
		Dialogs.create().owner(LauncherPrincipal.stage)
		.title("Erro").masthead("Parece que ocorreu um erro ao abrir essa tela")
		.message("Por favor, contate o administrador do sistema").showError();
		e.printStackTrace();
	}
}


@FXML
void abreRelatorio(MouseEvent event) {
	try{
//	if (!ControllerTelaFinanceiro.telaFinanceiro) {
//		ControllerTelaPrincipal.telaFinanceiro = true;
		new LauncherTelaGeraRelatorio().start(LauncherPrincipal.stage);
//	}
	}catch(Exception e){
		Dialogs.create().owner(LauncherPrincipal.stage)
		.title("Erro").masthead("Parece que ocorreu um erro ao abrir essa tela")
		.message("Por favor, contate o administrador do sistema").showError();
		e.printStackTrace();
	}
}
    

    

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	 assert paneAnchor != null : "fx:id=\"paneAnchor\" was not injected: check your FXML file 'telaPrincipal.fxml'.";
         assert icoCarro != null : "fx:id=\"icoCarro\" was not injected: check your FXML file 'telaPrincipal.fxml'.";
         assert icoPacote != null : "fx:id=\"icoPacote\" was not injected: check your FXML file 'telaPrincipal.fxml'.";
         assert paneNavBar != null : "fx:id=\"paneNavBar\" was not injected: check your FXML file 'telaPrincipal.fxml'.";
         assert icoFuncionario != null : "fx:id=\"icoFuncionario\" was not injected: check your FXML file 'telaPrincipal.fxml'.";
         assert icoCliente != null : "fx:id=\"icoCliente\" was not injected: check your FXML file 'telaPrincipal.fxml'.";
         assert pnLateral != null : "fx:id=\"pnLateral\" was not injected: check your FXML file 'telaPrincipal.fxml'.";
         assert icoMinimize != null : "fx:id=\"icoMinimize\" was not injected: check your FXML file 'telaPrincipal.fxml'.";
         assert icoMinimize1 != null : "fx:id=\"icoMinimize1\" was not injected: check your FXML file 'telaPrincipal.fxml'.";
         assert icoInstrutor != null : "fx:id=\"icoInstrutor\" was not injected: check your FXML file 'telaPrincipal.fxml'.";
         assert icClose != null : "fx:id=\"icClose\" was not injected: check your FXML file 'telaPrincipal.fxml'.";
         assert icFinanceiro != null : "fx:id=\"icFinanceiro\" was not injected: check your FXML file 'telaPrincipal.fxml'.";
         assert icRelatorio != null : "fx:id=\"icRelatorio\" was not injected: check your FXML file 'telaPrincipal.fxml'.";
         

        Tooltip.install(icoFuncionario, new Tooltip("Gerenciar Funcionario"));
        Tooltip.install(icoCliente, new Tooltip("Gerenciar Cliente"));
        Tooltip.install(icoCarro, new Tooltip("Gerenciar Carro"));
        Tooltip.install(icoInstrutor, new Tooltip("Gerenciar Instrutor"));
        
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
                xOffset = LauncherPrincipal.stage.getX() - event.getScreenX();
                yOffset = LauncherPrincipal.stage.getY() - event.getScreenY();
            }
        });
		
		paneNavBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            	LauncherPrincipal.stage.setX(event.getScreenX() + xOffset);
            	LauncherPrincipal.stage.setY(event.getScreenY() + yOffset);
            }
        });
    }
}
