package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.LineBorder;

import Controller.EmailControllerV3;
import Model.MensagemEmail;


public class PainelSendNewEmail extends JPanel{
	private int widht,heigth;
	private JLabel lbEmail,lbAssunto;
	private JTextField txtEmail,txtAssunto;
	private JButton btEnviar;
	private JTextArea txtTexto;
	private JScrollPane spTexto;
	private EmailControllerV3 emailC;
	private JPanel pnEmail;
	private JToolBar barNav;
	private Dimension size;
	
	
	
	public PainelSendNewEmail(Dimension size,EmailControllerV3 em,JPanel pnEmail,JToolBar barNav,Dimension sizeOriginal) {
		
		this.widht = size.width;
		this.heigth = size.height;
		this.emailC = em;
		this.size = sizeOriginal;
		this.barNav = barNav;
		this.pnEmail = pnEmail;
		
		
		initialize();
		events();
	}

	private void initialize() {
		setSize(widht,heigth);
		setLayout(null);
		
		lbEmail = new JLabel("E-mail: ");
		lbEmail.setBounds(5, 5, 55, 25);
		add(lbEmail);
		
		txtEmail = new JTextField();
		txtEmail.setLocation(lbEmail.getX()+lbEmail.getWidth(), lbEmail.getY());
		txtEmail.setSize(this.widht - (txtEmail.getX()+txtEmail.getWidth())- 20, 25);
		add(txtEmail);
		
		lbAssunto = new JLabel("Assunto: ");
		lbAssunto.setBounds(5, lbEmail.getY()+ lbEmail.getHeight(), 55, 25);
		add(lbAssunto);
		
		txtAssunto = new JTextField();
		txtAssunto.setLocation(lbAssunto.getX()+lbAssunto.getWidth(), lbAssunto.getY());
		txtAssunto.setSize(this.widht - (txtAssunto.getX()+txtAssunto.getWidth())-20, 25);
		add(txtAssunto);
			
		/**
		 * Montando o espaço restante de onde vai ficar o e-mail
		 */
		Dimension tamanho = new Dimension();
		tamanho.width = widht - 15;
		tamanho.height = heigth - (lbAssunto.getY() + lbEmail.getHeight()) - 40;
		
		Point local = new Point();
		local.x = 5;
		local.y = lbAssunto.getY() + lbAssunto.getHeight();
		
		txtTexto = new JTextArea();
		txtTexto.setSize(tamanho);
		txtTexto.setLocation(local);
		add(txtTexto);
		
		btEnviar = new JButton("Enviar!");
		btEnviar.setSize(60, 30);
		btEnviar.setLocation(widht - btEnviar.getWidth() - 5,heigth - btEnviar.getHeight());
		add(btEnviar);
		
		setVisible(true);
		
	}

	private void events() {
		btEnviar.addActionListener(evt->{
			MensagemEmail em = new MensagemEmail();
			em.setSubject(txtAssunto.getText());
			em.setFrom(emailC.getUser());
			em.setReplyTo(em.getFrom());
			em.setTexto(txtTexto.getText());
			
			emailC.sendEmail(em.getTo(), em.getTexto(), em.getSubject());
			
			
			pnEmail.removeAll();
			barNav.setSize(size);
			Principal.btAbrirMenuLateral.setLocation(barNav.getWidth() , 
								Principal.btAbrirMenuLateral.getY());
			
			Principal.btRefreshItens.setLocation(Principal.btAbrirMenuLateral.getWidth()+Principal.btAbrirMenuLateral.getX(),
								Principal.btAbrirMenuLateral.getY());
			
			ConfiguraEmail.isJtreeAumentou = false;
			ConfiguraEmail.isShowing = false;
			pnEmail.revalidate();
			barNav.remove(pnEmail);
			barNav.revalidate();
			barNav.repaint();
			Principal.minhaFrame.revalidate();
			//			
		});
		
	}
}
