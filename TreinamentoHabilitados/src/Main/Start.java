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



public class Start {
    protected static boolean isLoading = true;
	public static EntityManager manager;
	public static void main(String[] args) {
		try {
		  
			
		   for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        System.out.println(info.getName());
		    	if ("Nimbus".equals(info.getName())) {
		            System.out.println("is nimbus");
		    		UIManager.setLookAndFeel(info.getClassName());
                                Thread t = new Thread(new Runnable() {

                                @Override
                                public void run() {
                                    JFrame janela= new JFrame("Carregando");
                                    janela.setUndecorated(true);
                                    janela.setSize(200,200);
                                    JLabel lb = new JLabel("Carregando");
                                    janela.add(lb);
                                    janela.setVisible(true);
                                    
                                    while(Start.isLoading){
                                        try {
                                            lb.setText("Mais Alguns Instantes...");
                                            Thread.sleep(3*1000);
                                            Thread.sleep(3*1000);
                                            lb.setText("Mais Alguns Instantes...");
                                        Thread.sleep(3*1000);
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
                                new TelaLogin();
                                //new Principal();
                                //new webcam.WebCamPhotoAutoEscola(new JFrame());
		    		//new CapturaImagens();
		    		//new WebcamViewerExample();
                                //new FormCadastroPacote();
		            break;
		        }
		    	
		   }
		} catch (Exception e) {}
	}
}
