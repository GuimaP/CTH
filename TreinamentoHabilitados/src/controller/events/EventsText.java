package controller.events;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener.Change;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Cursor;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public abstract class EventsText implements ChangeListener<Boolean>{
	private TextField textField;
				
	public EventsText(TextField textField) {
		this.textField = textField;
	}
	
	public EventsText() {
		
	}
	
	public static void upperCase(TextField text){
		text.addEventHandler(KeyEvent.KEY_TYPED, e->{
			
			
			String tex = e.getCharacter().toUpperCase();
				if (!e.isShortcutDown()) {
					text.appendText(e.getCharacter().toUpperCase());
					e.consume();
				} 
		}); 
	}
	
	public static void focusUpperLost(TextField text){
		text.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0,
					Boolean focusLost, Boolean arg2) {
				if (focusLost) {
					text.setText(text.getText().toUpperCase());
				}
				
			}
		});
	}

	@Override
	public void changed(ObservableValue<? extends Boolean> arg0, Boolean focusLost,
			Boolean arg2) {
		if (focusLost) {
			textField.setText(textField.getText().toUpperCase());
		}
		
		
	}
	


}
