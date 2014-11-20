package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class ControllerDecimalNumbers implements EventHandler<KeyEvent>, ChangeListener<Boolean> {

	private int contador = 0;
	private String [] monetario;
	private TextField text;
	
	public ControllerDecimalNumbers() {
		
	}
	public ControllerDecimalNumbers(TextField text){
		this.text = text;
	}
	

	@Override
	public void handle(KeyEvent e) {
		TextField txt_TextField = (TextField) e.getSource();
		if (e.getCharacter().matches("[0-9 .]")) {
			monetario = txt_TextField.getText().split("\\.");
			if (txt_TextField.getText().length() >= 10) {
				e.consume();
			} else if (txt_TextField.getText().contains(".")
					&& e.getCharacter().matches("[.]")) {
				contador++;				
				if (contador >= 1) {
					e.consume();
				}				
				
			} else if (monetario != null && monetario.length==2) {
				String tamanho = monetario[1];
					if (tamanho.length()>=2) {
						e.consume();
					}				
			}
		} else {
			e.consume();
		}

	}

	@Override
	public void changed(ObservableValue<? extends Boolean> arg0, Boolean focusLost,
			Boolean arg2) {
		monetario = text.getText().split("\\.");			
		if (monetario != null && monetario.length == 2 && focusLost) {
			String tamanho = monetario[1];
			if (tamanho.length() > 2) {
				text.setText("0");
			}			
		}		
	}

}
