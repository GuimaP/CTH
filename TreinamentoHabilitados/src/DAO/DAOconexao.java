package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DAOconexao {
	private final String DATABASE = "bd_autoescola";
	private final String DRIVER = "com.mysql.jdbc.Driver";
	private final String URL = "jdbc:mysql://localhost:3306/" + DATABASE;
	private final String USUARIO = "root";
	private final String SENHA = "1q2w3e";
	public Connection conecta;

	public boolean abreConecxao() {
		boolean retorno = true;
		try {
			Class.forName(DRIVER);	
			conecta = DriverManager.getConnection(URL, USUARIO, SENHA);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	if (conecta == null) {
			retorno = false;

			return retorno;
		}

		return retorno;

	}

	public void desconectar() throws SQLException {
		conecta.close();

	}

}
