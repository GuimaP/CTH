package controller;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class ControllerOnlyNumbers implements EventHandler<KeyEvent>{

	@Override
	public void handle(KeyEvent e) {

		TextField txt_TextField = (TextField) e.getSource();
		if (e.getCharacter().matches("[0-9]")) {
			if (txt_TextField.getText().contains(",")
					&& e.getCharacter().matches("[,]")) {
				e.consume();
			} else if (txt_TextField.getText().length() >= 16) {
				e.consume();

			} else if (txt_TextField.getText().length() == 0
					&& e.getCharacter().matches("[,]")) {
				e.consume();
			} 

		} else {
			e.consume();
		}

		
	}

}
