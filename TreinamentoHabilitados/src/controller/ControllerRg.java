package controller;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class ControllerRg implements EventHandler<KeyEvent>{
	private int contador = 0;
	@Override
	public void handle(KeyEvent e) {

		TextField txt_TextField = (TextField) e.getSource();

		if (e.getCharacter().matches("[0-9 x]")) {
			if (txt_TextField.getText().contains(",")
					&& e.getCharacter().matches("[,]")) {
				e.consume();
			} else if (txt_TextField.getText().length() >= 16) {
				e.consume();

			} else if (txt_TextField.getText().length() == 0
					&& e.getCharacter().matches("[,]")) {
				e.consume();
			} else if (txt_TextField.getText().contains("x")
					&& e.getCharacter().matches("[x]")) {
				contador++;
				if (contador >= 1) {
					e.consume();
				}
			}

		} else {
			e.consume();
		}

		
	}

}
