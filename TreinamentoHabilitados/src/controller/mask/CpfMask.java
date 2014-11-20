package controller.mask;

import java.security.KeyException;

import org.dom4j.Text;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class CpfMask implements ChangeListener<Boolean>, EventHandler<KeyEvent> {
	private TextField text;
	private String validador = "";
	public CpfMask(TextField text) {
		this.text = text;
	}
	@Override
	public void handle(KeyEvent k) {

		String sub1, sub2, sub3, sub4, sub5, sub6, sub7, sub8, sub9, sub10, sub11, par1, part2, part3, part4;
		String cpf = text.getText();

		if (k.getCode().equals(k.getCode().BACK_SPACE)
				|| k.getCode().equals(k.getCode().DELETE)) {
			text.setText("");
		}

		if (text.getText().length() == 11) {

			sub1 = cpf.substring(0, 1); // Instruções para quebrar
			sub2 = cpf.substring(1, 2); // Cada caracter da String
			sub3 = cpf.substring(2, 3); // e dividi-los individualmente.
			sub4 = cpf.substring(3, 4);
			sub5 = cpf.substring(4, 5);
			sub6 = cpf.substring(5, 6);
			sub7 = cpf.substring(6, 7);
			sub8 = cpf.substring(7, 8);
			sub9 = cpf.substring(8, 9);
			sub10 = cpf.substring(9, 10);
			sub11 = cpf.substring(10, 11);

			par1 = sub1 + sub2 + sub3 + ".";
			part2 = sub4 + sub5 + sub6 + ".";
			part3 = sub7 + sub8 + sub9 + "-";
			part4 = sub10 + sub11;

			cpf = par1 + part2 + part3 + part4;

			text.setText(cpf);

			validador = text.getText();

		} else if (text.getText().length() > 11
				&& text.getText().length() <= 14) {
			validador = text.getText();
		} else if (text.getText().length() >= 14) {
			text.setText(validador);
		}

	}

	@Override
	public void changed(ObservableValue<? extends Boolean> arg0,
			Boolean focusLost, Boolean arg2) {
		if (focusLost && text.getText().length() < 14) {
			text.setText("");

		}

	}

}
