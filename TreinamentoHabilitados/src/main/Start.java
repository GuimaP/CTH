package main;

import java.io.File;

import javafx.application.Application;
import javafx.stage.Stage;

import javax.swing.JOptionPane;

import model.Funcionario;
import model.Login;
import model.enums.EnumPermissaoAcessoSistema;
import model.repository.ConnectionFactoryRepository;
import model.repository.LoginRepository;
import model.repository.RepositoryFuncionario;
import controller.launcher.LauncherPrincipal;
import controller.launcher.LauncherTelaLoad;
import controller.launcher.LauncherTelaLogin;

public class Start extends Application {

	// public static Session session;

	private static RepositoryFuncionario repoFunc = new RepositoryFuncionario();
	private static LoginRepository repoLogin = new LoginRepository();
	
	private static File diretorioReport;

	public static void main(String[] args) {
		try {
			setPrimeiraConfig();

			launch(args);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Falha ao carregar os recursos da tela!");
			e.printStackTrace();
		}

	}

	private static void setPrimeiraConfig() {

		String local = System.getProperty("user.home"); // Pego o diretorio do
														// usuario
		String nameFile = System.getProperty("file.separator") + "CTH";// e o
																		// nome
																		// da
																		// pasta
		File diretorioProgram = new File(local + nameFile);
		if (!diretorioProgram.exists()) { // Verifico se existe a pasta
			diretorioProgram.mkdir(); // Se não houve, eu crio uma
		}
		
		//Verifica se a pasta de relatorios existe
		File diretorioReports = new File(local+nameFile+File.separator+"Relatorios");
		if(!diretorioReports.exists()){
			diretorioReports.mkdir();
			Start.diretorioReport =  diretorioReports;
		}

		// Verifica se Existe um usuário root ja definido na base de dados
		if (!repoLogin.verifyUserRootExists()) {
			try {
				// Inserindo na base de dados o usuário root por default
				Login login = new Login();
				login.setUsuario("root");
				login.setSenha("r00t!123");

				repoLogin.adicionar(login);

				Funcionario fun = new Funcionario();
				fun.setCpf("111.111.111-11");
				fun.setCelular("(11)1111-1111");
				fun.setLoginUsuario(login);
				fun.setNome("CTH Root");
				fun.setPermissaoAcesso(EnumPermissaoAcessoSistema.Administrador);
				fun.setRg("22.222.222-2");
				fun.setTelefone("(33)3333-3333");

				repoFunc.addFuncionario(fun);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		new LauncherTelaLoad().start(primaryStage);

	}
}
