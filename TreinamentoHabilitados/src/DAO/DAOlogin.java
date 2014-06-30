package DAO;

import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Model.Login;

public class DAOlogin extends DAOconexao {
	private DAOconexao banco;
	private String sql;
	private PreparedStatement comando;
	private ResultSet consulta;

	public DAOlogin() {
		banco = new DAOconexao();
	}

	public void inserir(Long login) {

	}

	public List<Login> buscar()  {
		List<Login> login = new ArrayList<>();
		Login login1 = new Login();
		banco.abreConecxao();
		sql = "SELECT USUARIO_LOGIN, SENHA_LOGIN FROM tb_login";

		try{
		
		if (banco.abreConecxao()) {
			comando = banco.conecta.prepareStatement(sql);
			ResultSet rs = comando.executeQuery();
			while (rs.next()) {
				login1.setUsuario(rs.getString("USUARIO_LOGIN"));
				login1.setSenha(rs.getString("SENHA_LOGIN"));
				login.add(login1);
			}

			banco.desconectar();
		}

		
		}catch (SQLException e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return login;
	}
}
