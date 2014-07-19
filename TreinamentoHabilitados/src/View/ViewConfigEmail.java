package View;

import java.io.File;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Controller.CriptografiaConfigEmail;
import Controller.EmailControllerV2;
import Model.Login;
import Model.UsuarioEmail;


public class ViewConfigEmail extends JInternalFrame {

	private JTextField tfEmail,tfPort,tfHostSaida,tfHostEntrada;
	private JLabel lbEmail,lbPort,lbHostEntrada,lbHostSaida,lbSenha,lbSsl;
	private JPasswordField tfSenha;
	private JCheckBox chSsl;
	private JButton btTestar;
	private Login loginUser; 
	
	private UsuarioEmail user;
	
	public ViewConfigEmail(Login u){
		this.loginUser = u;
		initComponents();
		defineEvents();
		
	}
	
	private void initComponents(){
		setSize(350,380);
		setLayout(null);
		setClosable(true);
        setIconifiable(true);
        
        lbEmail = new JLabel("@E-mail: ");
        lbEmail.setBounds(5, 5, 70, 25);
        add(lbEmail);
        
        tfEmail = new JTextField();
        tfEmail.setBounds(5, lbEmail.getHeight()+lbEmail.getY(), getWidth()-20, 30);
        add(tfEmail);
        
        lbSenha = new JLabel("Senha: ");
        lbSenha.setBounds(5,tfEmail.getHeight()+tfEmail.getY(), 50, 20);
        add(lbSenha);
        
        tfSenha = new JPasswordField();
        tfSenha.setBounds(5, lbSenha.getHeight()+lbSenha.getY(), getWidth()-20, 30);
        tfSenha.setEchoChar('•');
        add(tfSenha);
        
        lbHostEntrada = new JLabel("Host de Entrada:  ");
        lbHostEntrada.setBounds(5, tfSenha.getY()+tfSenha.getHeight(), 100, 40);
        add(lbHostEntrada);
        
        tfHostEntrada = new JTextField();
        tfHostEntrada.setBounds(lbHostEntrada.getWidth(), lbHostEntrada.getY()+5, 230, 30);
        add(tfHostEntrada);
        
        lbHostSaida = new JLabel("Host de Saída");
        lbHostSaida.setBounds(5, lbHostEntrada.getY()+lbHostEntrada.getHeight()+5, 80, 20);
        add(lbHostSaida);
        
        tfHostSaida = new JTextField();
        tfHostSaida.setBounds(tfHostEntrada.getX(), lbHostSaida.getY(), 230, 30);
        add(tfHostSaida);
        
        lbPort = new JLabel("Porta: ");
        lbPort.setBounds(60, lbHostSaida.getHeight()+lbHostSaida.getY()+35, 50, 20);
        add(lbPort);
        
        tfPort = new JTextField();
        tfPort.setBounds(tfHostSaida.getX(), lbPort.getY(), 100, 30);
        add(tfPort);
        
        chSsl = new JCheckBox();
        chSsl.setBounds(lbPort.getX()+lbPort.getWidth(), lbPort.getHeight()+lbPort.getY()+10, 30, 20);
        add(chSsl);
        
        lbSsl = new JLabel("SSL");
        lbSsl.setBounds(chSsl.getWidth(), chSsl.getY(), 50, 20);
        add(lbSsl);
//        
        btTestar = new JButton("Testar");
        btTestar.setBounds(5, chSsl.getY()+chSsl.getHeight(),80, 30);
        add(btTestar);
        
        setVisible(true);
//        tfHostEntrada = new 
        
	}
	
	private void defineEvents(){
		
		
		
		btTestar.addActionListener(evt ->{
			String msg ="";
			String pass = String.valueOf(tfSenha.getPassword());
			boolean sucess = true;
			if(this.tfEmail.getText().isEmpty()){
				msg = "É necessario colocar um e-mail";
					sucess = false;
			}else if (!this.tfEmail.getText().contains("@")){
				msg = "Insira um e-mail valido";
				sucess = false;
			}else  if (pass.isEmpty()){
				msg = "coloque um senha!";
				sucess = false;
			}else if(this.tfHostEntrada.getText().isEmpty()){
				msg = "Coloque um endereço de entrada";
				sucess = false;
			}else if(this.tfHostSaida.getText().isEmpty()){
				msg = "Coloque um endereço de Saida";
				sucess = false;
			}else if(this.tfPort.getText().isEmpty()){
				msg = "Coloque uma porta";
				sucess = false;
			}
			//
			if(sucess){
				UsuarioEmail us = new UsuarioEmail();
				
				us.setHost(tfHostEntrada.getText());
				us.setHostReceive(tfHostSaida.getText());
				us.setUser(tfEmail.getText());
				us.setPass(pass);
				us.setSsl(chSsl.isSelected());
				 boolean autenticado =new  EmailControllerV2().autentica(us);
				 
				 if(autenticado){
					 
					 File dirArquivo = new File(getClass().getResource("/Resources/FilesConfig").getPath());
					 String nameFolder = loginUser.getUsuario()+"@emailConfig";
					 new CriptografiaConfigEmail().encrypt(us, dirArquivo, nameFolder);
					 JOptionPane.showMessageDialog(null, "Faça Log off para que as alterações sejam aplicadas!");
					 this.dispose();
				 }else {
					 JOptionPane.showMessageDialog(null, "Não Autenticado ");
				 }
				
			}else {
				JOptionPane.showMessageDialog(null, msg);
			}
		
	});
	}
}
