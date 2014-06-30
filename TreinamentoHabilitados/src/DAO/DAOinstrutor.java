package DAO;

import java.util.List;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JOptionPane;

import Model.Carro;
import Model.Funcionario;

public class DAOinstrutor extends DAOconexao {
	private DAOconexao banco;
	private String sql;
	private PreparedStatement comando;
	private ResultSet consulta;

	public DAOinstrutor() {
		banco = new DAOconexao();
	}

	public void inserir(Funcionario funcionario) {
		sql = "INSERT INTO tb_instrutor(NOME_INSTRUTOR, DATA_INSTRUTOR, CNH_INSTRUTOR, "
				+ "VALIDADECNH_INSTRUTOR,PRIMEIRACNH_INSTRUTOR,"
				+ "RG_INSTRUTOR,CPF_INSTRUTOR,TELEFONE_INSTRUTOR,"
				+ "CELULAR_INSTRUTOR,STATUS_INSTRUTOR, tb_carro_PLACA_CARRO)VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		banco.abreConecxao();
		Carro carro = new Carro();
		try {
			comando = banco.conecta.prepareStatement(sql,
					PreparedStatement.RETURN_GENERATED_KEYS);
			comando.setString(1, funcionario.getNome());
			comando.setDate(2, (Date) funcionario.getData()); //TODO VEROFICAR FUNCIONAMENTO
			comando.setString(3, funcionario.getCnh());
			comando.setString(4, funcionario.getValidadeCnh());
			comando.setString(5, funcionario.getPrimeiraCnh());
			comando.setString(6, funcionario.getRg());
			comando.setString(7, funcionario.getCpf());
			comando.setString(8, funcionario.getTelefone());
			comando.setString(9, funcionario.getCelular());
			comando.setString(10, funcionario.getStatus());
			comando.setString(11, funcionario.getTbCarroPlacaCarro().getPlaca());
			comando.execute();
			banco.desconectar();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());

		}
	}

	public ArrayList<Funcionario> buscarFunc() throws SQLException {
		ArrayList<Funcionario> funcionarioList = new ArrayList<Funcionario>();
		Funcionario funcionario;
		Carro carro;
		banco.abreConecxao();
		sql = "SELECT * FROM VIEW_BUSCA_FUNCIONARIOS ";
		if (banco.abreConecxao()) {
			comando = banco.conecta.prepareStatement(sql);
			ResultSet rs = comando.executeQuery();
			while (rs.next()) {
				try {
					funcionario = new Funcionario();
					funcionario.setNome(rs.getString("NOME_INSTRUTOR"));
					funcionario.setData(rs.getDate("DATA_INSTRUTOR"));
					funcionario.setCnh(rs.getString("CNH_INSTRUTOR"));
					funcionario.setValidadeCnh(rs
							.getString("VALIDADECNH_INSTRUTOR"));
					funcionario.setPrimeiraCnh(rs
							.getString("PRIMEIRACNH_INSTRUTOR"));
					funcionario.setRg(rs.getString("RG_INSTRUTOR"));
					funcionario.setCpf(rs.getString("CPF_INSTRUTOR"));
					funcionario.setTelefone(rs.getString("TELEFONE_INSTRUTOR"));
					funcionario.setCelular(rs.getString("CELULAR_INSTRUTOR"));
					funcionario.setStatus(rs.getString("STATUS_INSTRUTOR"));
					
					carro = new Carro();
					carro.setPlaca(rs.getString("tb_carro_placa_carro"));
					funcionario.setTbCarroPlacaCarro(carro);
					
					
					funcionarioList.add(funcionario);
					
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					break;
				}

			}
			banco.desconectar();
		}

		return funcionarioList;
	}

}
