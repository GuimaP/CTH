package Main;

import javax.persistence.EntityManager;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import Model.Repository.ConnectionFactoryRepository;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import formulario.TelaLogin;
import java.awt.BorderLayout;
import java.sql.SQLException;
import javax.swing.UnsupportedLookAndFeelException;



public class Start {
    protected static boolean isLoading = true;
	public static EntityManager manager;
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException {
		try {
		  
			
		   for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        System.out.println(info.getName());
		    	if ("Nimbus".equals(info.getName())) {
		            System.out.println("is nimbus");
		    		UIManager.setLookAndFeel(info.getClassName());
                                Thread t = new Thread(new Runnable() {

                                @Override
                                public void run() {
                                    String[] mensagens = {"Carregando...","Mais Alguns Instantes..."};
                                    JFrame janela= new JFrame("Carregando");
                                    janela.setUndecorated(true);
                                    janela.setSize(200,50);
                                    janela.setDefaultCloseOperation(0);
                                    JLabel lb = new JLabel("Carregando");
                                    janela.add(lb,BorderLayout.CENTER);
                                    janela.setLocationRelativeTo(null);
                                    janela.setVisible(true);
                                    int cont = 0;
                                    while(Start.isLoading){
                                        try {
                                            lb.setText(mensagens[(cont%2)]);
                                            Thread.sleep(1*1000);
                                            cont++;
                                            
                                        Thread.sleep(1000);
                                        } catch (InterruptedException ex) {
                                            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        
                                        
                                    }
                                    janela.dispose();
                                }
                            });
                                t.start();
                                
                                manager = ConnectionFactoryRepository.getManager();
                                
                                
                                
		    		isLoading = false;
                                TelaLogin tela = new TelaLogin();
                                System.out.println(Start.class.getClass().getResource("/"));
		            break;
		        }
		    	
		   }
		} catch (SQLException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                }
	}
}


















