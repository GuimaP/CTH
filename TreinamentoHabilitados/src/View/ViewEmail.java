package View;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Model.MensagemEmail;

public class ViewEmail extends JInternalFrame{
	private JButton btReply;
	private JTextField tfRemetente,tfAssunto;
	private JTextArea txTexto;
	private JLabel lbAssunto,lbRemetente,lbTexto;
	private MensagemEmail email;
	public ViewEmail(MensagemEmail e) {
		setClosable(true);
		setIconifiable(true);
		setMaximizable(false);
		this.email = e;
		initComponents();
	}
	
	private void initComponents(){
		setSize(600,400);
		setLayout(null);
		
		lbRemetente = new JLabel("Remetente: ");
		lbRemetente.setBounds(5, 5, 80, 25);
		add(lbRemetente);
		
		tfRemetente = new JTextField();
		tfRemetente.setBounds(lbRemetente.getWidth() + 5, lbRemetente.getY(), (this.getWidth()-lbRemetente.getWidth())-20, 25);
		add(tfRemetente);
		
		lbAssunto = new JLabel("Assunto: ");
		lbAssunto.setBounds(5, lbRemetente.getHeight()+5, 80, 25);
		add(lbAssunto);
		
		tfAssunto = new JTextField();
		tfAssunto.setBounds(lbAssunto.getWidth()+5, lbAssunto.getY(), (this.getWidth() - lbAssunto.getWidth())-20, 25);
		add(tfAssunto);
		
		txTexto = new JTextArea();
		txTexto.setWrapStyleWord(true);
		txTexto.setLineWrap(true);
		txTexto.setBounds(5, (lbAssunto.getY() + lbAssunto.getHeight())+5, (this.getWidth() - 10)-20,  (this.getHeight() -  (lbAssunto.getY() - lbAssunto.getHeight() )) - 80);
		add(txTexto);
		
		btReply = new JButton("Responder");
		btReply.setSize(40,25);
		btReply.setLocation(this.getWidth() - btReply.getWidth(), this.getHeight() - 25);
		add(btReply);
//		
		
		setVisible(true);
	}
}
