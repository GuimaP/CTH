package Main;

import javax.persistence.EntityManager;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import Model.Repository.ConnectionFactoryRepositoryDois;
import formulario.TelaLogin;

public class Start {
	public static EntityManager manager;
	public static void main(String[] args) {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        System.out.println(info.getName());
		    	if ("Nimbus".equals(info.getName())) {
		            System.out.println("is nimbus");
		    		UIManager.setLookAndFeel(info.getClassName());
		    		manager = ConnectionFactoryRepositoryDois.getManager();
		    		new TelaLogin();
		            break;
		        }
		    	
		    }
		} catch (Exception e) {}
	}
}
