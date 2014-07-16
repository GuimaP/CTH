package View;

import java.awt.Point;



import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.mail.Address;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.JTextField;





import Model.MensagemEmail;

public class ViewEmail extends JInternalFrame{
	private JButton btReply;
	private JTextField tfRemetente,tfAssunto;
	private JEditorPane txTexto;
	private JLabel lbAssunto,lbRemetente,lbTexto;
	private MensagemEmail email;
	
	private JPopupMenu menu;
	
	public ViewEmail(MensagemEmail e) {
		setClosable(true);
		setIconifiable(true);
		setMaximizable(false);
		this.email = e;
		initComponents();
		defineEvents();
		
	}
	
	private void initComponents(){
		setSize(600,400);
		setLayout(null);
		
		lbRemetente = new JLabel("Remetente: ");
		lbRemetente.setBounds(5, 5, 80, 25);
		add(lbRemetente);
		
		tfRemetente = new JTextField();
		tfRemetente.setBounds(lbRemetente.getWidth() + 5, lbRemetente.getY(), (this.getWidth()-lbRemetente.getWidth())-20, 25);
		String from ="";
		for(Address a : email.getFrom()){
			from +=a.toString()+";";
		}
		tfRemetente.setText(from);
		add(tfRemetente);
		
		lbAssunto = new JLabel("Assunto: ");
		lbAssunto.setBounds(5, lbRemetente.getHeight()+5, 80, 25);
		add(lbAssunto);
		
		tfAssunto = new JTextField();
		tfAssunto.setBounds(lbAssunto.getWidth()+5, lbAssunto.getY(), (this.getWidth() - lbAssunto.getWidth())-20, 25);
		tfAssunto.setText(email.getSubject());
		add(tfAssunto);
		JFrame f =  new JFrame();
		
		txTexto = new JEditorPane();
		txTexto.setContentType("text/html");  
//		txTexto.setWrapStyleWord(true);
//		txTexto.setLineWrap(true);
		
		txTexto.setBounds(5, (lbAssunto.getY() + lbAssunto.getHeight())+5, (this.getWidth() - 10)-10,  (this.getHeight() -  (lbAssunto.getY() - lbAssunto.getHeight() )) - 120);
		txTexto.setText(email.getTexto());
		add(txTexto);
		
		btReply = new JButton("Responder");
		btReply.setSize(70,25);
		btReply.setLocation(this.getWidth() - btReply.getWidth()-40, this.getHeight() - 70);
		add(btReply);
//		
		
		menu = new JPopupMenu();
		JTextArea txt = new JTextArea();
		txt.setSize(100,100);
		menu.add(txt);
		
		
		setVisible(true);
	}
	
	private void defineEvents(){
		btReply.addActionListener(evt ->{
			menu.setSize(100, 100);
			menu.setPopupSize(100, 100);
			menu.show(btReply, 0, 0);
		});

	}
}
