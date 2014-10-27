package controller;
/**

 * Sample Skeleton for 'telaPrincipal.fxml' Controller Class
 */

import java.io.Closeable;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.CloseAction;

import org.eclipse.swt.events.DisposeEvent;

import com.sun.media.controls.ActionControl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class ControllerTelaPrincipal {
	
	protected static boolean telaCarro, telaCliente, telaServico, telaFuncionario, telaInstrutor;
	
	
	public ControllerTelaPrincipal(){
		
		telaCarro = telaCliente = telaServico = telaFuncionario = telaInstrutor = false;
	}

	
	@FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btCliente"
    private Button btCliente; // Value injected by FXMLLoader

    @FXML // fx:id="btFuncionario"
    private Button btFuncionario; // Value injected by FXMLLoader

    @FXML // fx:id="btCarro"
    private Button btCarro; // Value injected by FXMLLoader

    @FXML // fx:id="brInstrutor"
    private Button brInstrutor; // Value injected by FXMLLoader

    @FXML // fx:id="btPacote"
    private Button btPacote; // Value injected by FXMLLoader

    @FXML
    void abreCarro(ActionEvent event) throws Exception {
    	if (!ControllerTelaPrincipal.telaCarro) {
    		ControllerTelaPrincipal.telaCarro = true;
    		new SceneBuilderTelaCarro().start(new Stage());
    		
		}
    }

    @FXML
    void abreInstrutor(ActionEvent event) throws Exception {
    	if (!ControllerTelaPrincipal.telaInstrutor) {
			ControllerTelaPrincipal.telaInstrutor = true;
			new SceneBuilderTelaInstrutor().start(new Stage());
    	}
    }

    @FXML
    void abreFuncionario(ActionEvent event) throws Exception {
    	if (!ControllerTelaPrincipal.telaFuncionario) {
			ControllerTelaPrincipal.telaFuncionario = true;
			new SceneBuilderTelaFuncionario().start(new Stage());
    	}
    	
    }

    @FXML
    void abreCliente(ActionEvent event) throws Exception {
    	if (!ControllerTelaPrincipal.telaCliente) {
			ControllerTelaPrincipal.telaCliente = true;
			new SceneBuilderTelaCliente().start(new Stage());
    	}
    	

    }

    @FXML
    void abrePacote(ActionEvent event) throws Exception {
    	if (!ControllerTelaPrincipal.telaServico) {
			ControllerTelaPrincipal.telaServico = true;
			new SceneBuilderTelaServico().start(new Stage());
    	}
    	

    }
    
    

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btCliente != null : "fx:id=\"btCliente\" was not injected: check your FXML file 'telaPrincipal.fxml'.";
        assert btFuncionario != null : "fx:id=\"btFuncionario\" was not injected: check your FXML file 'telaPrincipal.fxml'.";
        assert btCarro != null : "fx:id=\"btCarro\" was not injected: check your FXML file 'telaPrincipal.fxml'.";
        assert brInstrutor != null : "fx:id=\"brInstrutor\" was not injected: check your FXML file 'telaPrincipal.fxml'.";
        assert btPacote != null : "fx:id=\"btPacote\" was not injected: check your FXML file 'telaPrincipal.fxml'.";

    }
}
