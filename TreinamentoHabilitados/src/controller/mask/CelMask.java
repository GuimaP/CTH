package controller.mask;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class CelMask implements ChangeListener<Boolean>, EventHandler<KeyEvent> {

	private TextField text;
	private String validador = "";

	public CelMask(TextField text) {
		this.text = text;
	}

	@Override
	public void handle(KeyEvent k) {

		if (k.getCode().equals(k.getCode().BACK_SPACE)
				|| k.getCode().equals(k.getCode().DELETE)) {
			text.setText("");
		}
		
		String sub1, sub2, sub3, par1, sub4, sub5, sub6, sub7, sub8, sub9, sub10, sub11, part2;
		String cel = text.getText();
		if (text.getText().length() == 11) {
			sub1 = cel.substring(0, 1);
			sub2 = cel.substring(1, 2);
			sub3 = cel.substring(2, 3);
			sub4 = cel.substring(3, 4);
			sub5 = cel.substring(4, 5);
			sub6 = cel.substring(5, 6);
			sub7 = cel.substring(6, 7);
			sub8 = cel.substring(7, 8);
			sub9 = cel.substring(8, 9);
			sub10 = cel.substring(9, 10);
			sub11 = cel.substring(10, 11);
			par1 = "(" + sub1 + sub2 + ")";
			part2 = sub3 + sub4 + sub5 + sub6 + sub7 + sub8 + sub9
					+ sub10 + sub11;
			cel = par1 + part2;
			text.setText(cel);
			validador = text.getText();
		} else if (text.getText().length() > 11
				&& text.getText().length() <= 13) {
			validador = text.getText();
		} else if (text.getText().length() >= 13) {
			text.setText(validador);
		}
	}
	@Override
	public void changed(ObservableValue<? extends Boolean> arg0,
			Boolean focusLost, Boolean arg2) {
		if (focusLost && text.getText().length() < 13) {
			text.setText("");
		}
	}

}
