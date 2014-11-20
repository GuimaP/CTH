package controller.mask;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class PlacaMask implements ChangeListener<Boolean>, EventHandler<KeyEvent>{
	
	private TextField text;
	private String validador = "";

	public PlacaMask(TextField text){
		this.text = text;
	}
	
	
	@Override
	public void handle(KeyEvent k) {
		
		String sub1, sub2, sub3, sub4, sub5, sub6, sub7, part1, part2;
		String placa = text.getText();
		
		if (k.getCode().equals(k.getCode().BACK_SPACE)
				|| k.getCode().equals(k.getCode().DELETE)) {
			text.setText("");
		}				
		
		if (text.getText().length() == 7) {
			
			sub1 = placa.substring(0, 1); 
			sub2 = placa.substring(1, 2); 
			sub3 = placa.substring(2, 3);
			sub4 = placa.substring(3, 4);
			sub5 = placa.substring(4, 5);
			sub6 = placa.substring(5, 6);
			sub7 = placa.substring(6, 7);
			
			part1 = sub1+sub2+sub3;
			part2 = sub4+sub5+sub6+sub7;
			
			placa = part1+"-"+part2;
			
			text.setText(placa);
			validador = text.getText();
		} else if (text.getText().length() > 7
				&& text.getText().length() <= 8) {
			validador = text.getText();
		} else if (text.getText().length() >= 8) {
			text.setText(validador);
		}
		
		
	}

	@Override
	public void changed(ObservableValue<? extends Boolean> arg0, Boolean focusLost,
			Boolean arg2) {
		if (focusLost && text.getText().length() < 7) {
			text.setText("");
		}
		
	}

}
