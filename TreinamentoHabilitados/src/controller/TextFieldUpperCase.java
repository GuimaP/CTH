package controller;

import javafx.scene.control.TextField;

public class TextFieldUpperCase extends TextField {

		final String restictTo = "[A-Z\\s]*";

		@Override
		public void replaceText(int start, int end, String text) {
			if (matchTest(text)) {
				super.replaceText(start, end, text);
			}
		}

		@Override
		public void replaceSelection(String text) {
			if (matchTest(text)) {
				super.replaceSelection(text);
			}
		}

		private boolean matchTest(String text) {
			return text.isEmpty() || text.matches(restictTo);
		}
	
	

}
