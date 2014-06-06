package Main;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import formulario.TelaLogin;

public class Start {
	public static void main(String[] args) {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        System.out.println(info.getName());
		    	if ("Nimbus".equals(info.getName())) {
		            System.out.println("is nimbus");
		    		UIManager.setLookAndFeel(info.getClassName());
		    		new TelaLogin();
		            break;
		        }
		    	
		    }
		} catch (Exception e) {}
	}
}
