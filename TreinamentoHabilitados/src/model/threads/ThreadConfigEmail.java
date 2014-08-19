package model.threads;

import java.io.File;

import javax.swing.JOptionPane;

import view.Principal;
import model.Login;
import model.UsuarioEmail;
import controller.CriptografiaConfigEmail;
import controller.EmailControllerV3;

public class ThreadConfigEmail extends Thread {

		private EmailControllerV3 emailC;
		private Login user;
		private UsuarioEmail confEmail;

		public ThreadConfigEmail() {

//			this.user = u;
			
			

			
		}

		public Login getUser() {
			return user;
		}

		public void setUser(Login user) {
			this.user = user;
			String diretorio = System.getProperty("user.home");
			String sep = System.getProperty("file.separator");

			String nameFolder = user.getUsuario() + "@emailConfig"; // e digo o nome
																	// padr�o dos
																	// arquivos de
																	// e-mail's
			
			File fileConfigEmail = new File(diretorio+sep+nameFolder); // Pego o
																	   // diretorio
																	   // que se
																	   // encontra
																	   // o arquivo
			
			confEmail = new CriptografiaConfigEmail().unCrypt(// e tento localizo-lo
																// e
																// descriptografa-lo
					nameFolder);

		}

		public void run() {
			try{	
			if (confEmail != null) { // Se no construtor conseguiu localizar o
											// arquivo ent�o eu tento autentica-lo
					System.out.println("Tem config");

					emailC = new EmailControllerV3(confEmail);
					while (true) {
						int atual = emailC.countMessage();
						System.out.println(atual+"    has email?");
						if (emailC.hasNewEmail(atual)) {
							System.out.println("Chegou nova msg!");
							Principal.showNotication();
						}

						Thread.sleep(60 * 1000);
					}
				} else {
					System.out.println("N Tem config  - PARTIU");
				}
				
				
				
			}catch(Exception e){
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.getMessage() + " thread Config Email ");
			}
		}
	}


