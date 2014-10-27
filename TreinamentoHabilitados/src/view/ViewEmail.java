package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;



import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.mail.Address;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;








import javax.swing.LayoutStyle;

import controller.EmailControllerV3;
import model.MensagemEmail;

public class ViewEmail extends JInternalFrame{
	private JButton btReply;
	private JTextField tfRemetente,tfAssunto;
	private JEditorPane txTexto;
	private JLabel lbAssunto,lbRemetente,lbTexto;
	private MensagemEmail email;
	
	private EmailControllerV3 emailController;
	private JInternalFrame myInternal;
	private JPopupMenu menu;
	
	private JScrollPane sp;
	
	public ViewEmail(MensagemEmail e,EmailControllerV3 email2) {
		setClosable(true);
		setIconifiable(true);
		setMaximizable(false);
		this.myInternal = this;
		this.email = e;
		this.emailController = email2;
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
		
		
		String from = email.getFrom();
		
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
		txTexto.setText(email.getTexto());
//		txTexto.setWrapStyleWord(true);
//		txTexto.setLineWrap(true);
		sp = new JScrollPane(txTexto);
		sp.setBounds(5, (lbAssunto.getY() + lbAssunto.getHeight())+5, (this.getWidth() - 10)-10,  (this.getHeight() -  (lbAssunto.getY() - lbAssunto.getHeight() )) - 120);
		add(sp);
		
		btReply = new JButton("Responder");
		btReply.setSize(70,25);
		btReply.setLocation(this.getWidth() - btReply.getWidth()-40, this.getHeight() - 70);
		add(btReply);
//		
		
		menu = new JPopupMenu();
		menu.setPopupSize(200, 200);
		menu.setBackground(new Color(10,10,10,10));
		menu.setLayout(null);
		
		Dimension d = new Dimension(200, 200);
		ViewReplyEmail replyPainel = new ViewReplyEmail(d, email, emailController, menu, myInternal);
		menu.add(replyPainel);
		
		
		setVisible(true);
	}
	
	private void defineEvents(){
		btReply.addActionListener(evt ->{
			
			menu.show(btReply, 0, 0);
		});

	}
}
