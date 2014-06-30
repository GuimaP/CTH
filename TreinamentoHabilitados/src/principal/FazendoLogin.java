package principal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.DAOlogin;
import Model.Login;

public class FazendoLogin {

	private Boolean logado;

	public Boolean getLogado() {
		return logado;
	}

	public Boolean log(String usuario, String senha) throws SQLException {
		new Login();
		DAOlogin loginBanco = new DAOlogin();
		// Vetor de login que vai receber o objeto carregando
		// a senha e o login do banco de dados
		Login[] banco = new Login[1];
		// Adicionando o objeto a um vetor de login pegando o primeiro indice
		banco[0] = loginBanco.buscar().get(0);
		if (usuario.equals(banco[0].getUsuario())
				&& senha.equals(banco[0].getSenha())) {

			logado = true;

		} else {
			logado = false;
		}
		return logado;
	}

}
