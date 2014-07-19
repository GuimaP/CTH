package View;

import javax.mail.Address;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Controller.EmailControllerV2;
import Model.MensagemEmail;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Menu;

public class ViewReplyEmail  extends JPanel{
	private int width;
	private int heigth;
	
	private JTextArea txtMsg;
	private JButton btEnviar;
	private MensagemEmail email;
	private EmailControllerV2 emailController;
	private JPopupMenu menu;
	private JInternalFrame jInternal;
	
//	public ViewReplyEmail(Dimension dimension,MensagemEmail msg,EmailController emailC,JPopupMenu popMenu,JInternalFrame internal) {
//	
//	}
	
	public ViewReplyEmail(Dimension dimension, MensagemEmail msg,
			EmailControllerV2 emailC, JPopupMenu popMenu,
			JInternalFrame internal) {
		this.width = (int) dimension.getWidth();
		this.heigth = (int) dimension.getHeight();
		this.email =msg;
		this.emailController = emailC;
		this.menu = popMenu;
		this.jInternal = internal;
		initComponents();
		defineEvents();
		setVisible(true);
	}

	

	private void initComponents() {
		setSize(width,heigth);
		setLayout(null);
		setBackground(Color.ORANGE	);
		
		txtMsg = new JTextArea();
		txtMsg.setWrapStyleWord(true);
		txtMsg.setLineWrap(true);
		JScrollPane sp = new JScrollPane(txtMsg);
		sp.setBounds(5, 5, width-10, heigth-50);
		add(sp);
		
		btEnviar =new JButton("Enviar");
		btEnviar.setSize(80, 25);
		btEnviar.setLocation(width - btEnviar.getWidth() - 10, heigth - 40);
		
		add(btEnviar);
		
		
		
	}
	//
	private void defineEvents(){
		btEnviar.addActionListener(e ->{
			String texto = txtMsg.getText();
//			Address to = email.getFrom().get(0);
//			
//			String subject = "RE: "+email.getSubject();
//			emailController.sendEmail(to, texto, subject);
			jInternal.dispose();
			menu.hide();
			menu.setVisible(false);
			JOptionPane.showMessageDialog(this, "E-mail Enviado", "Enviado", JOptionPane.INFORMATION_MESSAGE);
		});
	}
}
