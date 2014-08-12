package View;

import java.io.File;






import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;






import controller.CriptografiaConfigEmail;
import controller.EmailControllerV3;
import model.Login;
import model.UsuarioEmail;


public class ViewConfigEmail extends JInternalFrame {

	private JTextField tfEmail,tfPort,tfHostSaida,tfHostEntrada,tfPortSaida;
	private JLabel lbEmail,lbPort,lbHostEntrada,lbHostSaida,lbSenha,lbSsl,lbPortSaida;
	private JPasswordField tfSenha;
	private JCheckBox chSsl;
	private JButton btTestar;
	private Login loginUser; 
	public  static JButton btRefreshItens;
	private UsuarioEmail user;
	
	public ViewConfigEmail(Login u){
		this.loginUser = u;
		initComponents();
		defineEvents();
		
	}
	
	private void initComponents(){
		setSize(390,380);
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

        tfSenha.setEchoChar(' ');

        tfSenha.setEchoChar('_');

        add(tfSenha);
        
        lbHostEntrada = new JLabel("Host de Entrada:  ");
        lbHostEntrada.setBounds(5, tfSenha.getY()+tfSenha.getHeight(), 100, 50);
        add(lbHostEntrada);
        
        tfHostEntrada = new JTextField();
        tfHostEntrada.setBounds(lbHostEntrada.getX() +lbHostEntrada.getWidth(), lbHostEntrada.getY()+5, 180, 30);
        add(tfHostEntrada);
        
        lbPort = new JLabel("Porta: ");
        lbPort.setBounds(tfHostEntrada.getX()+tfHostEntrada.getWidth(), tfHostEntrada.getY(), 50, 20);
        add(lbPort);
        
        tfPort = new JTextField();
        tfPort.setBounds(lbPort.getX() +40, lbPort.getY(), 50, 30);
        add(tfPort);
        //--
        lbHostSaida = new JLabel("Host de Sa�da");
        lbHostSaida.setBounds(5, lbHostEntrada.getY()+lbHostEntrada.getHeight()+5, 80, 20);
        add(lbHostSaida);
       
        tfHostSaida = new JTextField();
        tfHostSaida.setBounds(tfHostEntrada.getX(), lbHostSaida.getY(), 180, 30);
        add(tfHostSaida);
       //--- 
        lbPortSaida = new JLabel("Porta: ");
        lbPortSaida.setBounds(tfHostSaida.getX()+tfHostSaida.getWidth(),tfHostSaida.getY(), 50, 20);
        add(lbPortSaida);

        tfPortSaida = new JTextField();
        tfPortSaida.setBounds(lbPortSaida.getX() +40, lbPortSaida.getY(), 50, 30);
        add(tfPortSaida);
        //--
        chSsl = new JCheckBox();
        chSsl.setBounds(tfPortSaida.getX(), lbHostSaida.getHeight()+lbHostSaida.getY()+10, 30, 20);
        add(chSsl);
        
        lbSsl = new JLabel("SSL");
        lbSsl.setBounds(chSsl.getWidth()+chSsl.getX() , chSsl.getY(), 50, 20);
        add(lbSsl);
//        
        btTestar = new JButton("Testar");
        btTestar.setBounds(5, chSsl.getY()+chSsl.getHeight(),80, 30);
        add(btTestar);
        String separador = System.getProperty("file.separator");
        btRefreshItens = new JButton(new ImageIcon(getClass().getClassLoader().getResource(
				"Resources"+separador+"icons"+separador+"load.gif")));
		btRefreshItens.setContentAreaFilled(false);
		btRefreshItens.setLocation(this.getWidth()- 60,this.getHeight()-60 );
		btRefreshItens.setSize(40, 40);
		btRefreshItens.setToolTipText("Atualizando");
		btRefreshItens.setVisible(false);
		add(btRefreshItens);
        
        setVisible(true);
//        tfHostEntrada = new 
        
	}
	
	private void defineEvents(){
		
		
		
		btTestar.addActionListener(evt ->{
			String msg ="";
			String pass = String.valueOf(tfSenha.getPassword());
			try{	 
			boolean sucess = true;
			if(this.tfEmail.getText().isEmpty()){
				msg = "� necessario colocar um e-mail";
					sucess = false;
			}else if (!this.tfEmail.getText().contains("@")){
				msg = "Insira um e-mail valido";
				sucess = false;
			}else  if (pass.isEmpty()){
				msg = "coloque um senha!";
				sucess = false;
			}else if(this.tfHostEntrada.getText().isEmpty()){
				msg = "Coloque um endere�o de entrada";
				sucess = false;
			}else if(this.tfHostSaida.getText().isEmpty()){
				msg = "Coloque um endere�o de Saida";
				sucess = false;
			}else if(this.tfPort.getText().isEmpty()){
				msg = "Coloque uma porta";
				sucess = false;
			}else if(this.tfPortSaida.getText().isEmpty()){
			//
				msg = "Coloque uma porta de saida";
				sucess = false;
			
			}
				if(sucess){
				
				UsuarioEmail us = new UsuarioEmail();
				
				us.setHost(tfHostEntrada.getText());
				us.setHostReceive(tfHostSaida.getText());
				us.setUser(tfEmail.getText());
				us.setPass(pass);
				us.setSsl(chSsl.isSelected());
				us.setPortReceive(Integer.parseInt(tfPortSaida.getText()));
				us.setPort(Integer.parseInt(tfPort.getText()));
				
				
				
				
				btRefreshItens.setVisible(true);	
				new Thread(()->{
					 boolean autenticado; 
					try {
						autenticado = new EmailControllerV3().autentica(us);
						 if(autenticado){
								
							 String nameFolder = loginUser.getUsuario()+"@emailConfig";
							 new CriptografiaConfigEmail().encrypt(us, nameFolder);
							 JOptionPane.showMessageDialog(null, "Fa�a log-off para que as alter��es sejam feitas");
					
							 this.dispose();
						 }else {
							 JOptionPane.showMessageDialog(null, "N�o Autenticado ");
						 }
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						ViewConfigEmail.btRefreshItens.setVisible(false);
					}
					
					
			}).start();;
				
				
			
				 
			}else {
				JOptionPane.showMessageDialog(null, msg);
			}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, "erro \n " + e1.getMessage());
				e1.printStackTrace();
			}
		
			
	});
		}
}
