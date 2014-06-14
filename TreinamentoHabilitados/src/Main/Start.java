package Main;

import javax.persistence.EntityManager;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import formulario.*;
import Model.Repository.ConnectionFactoryRepository;
import javax.swing.JFrame;


public class Start {
	public static EntityManager manager;
	public static void main(String[] args) {
		try {
		  
			
		   for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        System.out.println(info.getName());
		    	if ("Nimbus".equals(info.getName())) {
		            System.out.println("is nimbus");
		    		UIManager.setLookAndFeel(info.getClassName());

                                //manager = ConnectionFactoryRepository.getManager();
		    		
//                                new TelaLogin();
                                new Principal();
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
