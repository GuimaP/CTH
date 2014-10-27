package view;

import java.awt.TextComponent;

import javax.swing.JOptionPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class DocumentoNumerico extends PlainDocument {
	
	public TextComponent componenteDeTexto;
	
	public DocumentoNumerico(TextComponent componenteDeTexto) {
		this.componenteDeTexto = componenteDeTexto;
	}
	
	@Override
	public void insertString(int offs, String str, AttributeSet a)
			throws BadLocationException {
		
		try {
			if(str !=  ","){
				int parse = Integer.parseInt(str);
			}else{
				JOptionPane.showMessageDialog(null, str);
			}
		} catch (Exception e) {
			return;
		}
		super.insertString(offs, str, a);
	}
}
