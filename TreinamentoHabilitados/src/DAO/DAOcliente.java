package DAO;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import principal.CadastroCliente;
import modelo.Cliente;
import modelo.Cnh;
import modelo.Endereco;



public class DAOcliente extends DAOconexao {
	private DAOconexao banco;
	private String sql;
	private PreparedStatement comando;
	private ResultSet consulta;

	public DAOcliente() {
		banco = new DAOconexao();
	}

	public ArrayList<Cliente> buscarTodos() throws SQLException{
		ArrayList<Cliente> lc = new  ArrayList<Cliente>();
		Cliente c; 
		banco.abreConecxao();
		
		sql = "SELECT * FROM TB_CLIENTE";
		if(banco.abreConecxao()){
			comando = banco.conecta.prepareStatement(sql);
			ResultSet rs = comando.executeQuery();
			while (rs.next()){
				c = new Cliente();
				c.setIdCliente(rs.getInt("ID_CLIENTE"));
				c.setNome(rs.getString("NOME_CLIENTE"));
				c.setNascimento(rs.getString("DATANASCIMENTO_CLIENTE"));
				c.setEmail(rs.getString("EMAIL_CLIENTE"));
				c.setEscolaridade(rs.getString("ESCOLARIDADE_CLIENTE"));
				c.setTelefone(rs.getString("TELEFONE_CLIENTE"));
				c.setProfissao(rs.getString("PROFISSAO_CLIENTE"));
				c.setRg(rs.getString("RG_CLIENTE"));
				c.setCpf(rs.getString("CPF_CLIENTE"));
				c.setSexo(rs.getString("SEXO_CLIENTE"));
				lc.add(c);
			}
			banco.desconectar();
			
		}
		
		
		
		
		return lc;
	}
	
	
	
	public void inserir(Cliente cliente, Cnh cnh, Endereco endereco, CadastroCliente cadastroCliente) {
		sql = "INSERT INTO tb_cliente(NOME_CLIENTE, DATANASCIMENTO_CLIENTE,EMAIL_CLIENTE,"
				+ "ESCOLARIDADE_CLIENTE,TELEFONE_CLIENTE,"
				+ "CELULAR_CLIENTE,PROFISSAO_CLIENTE,RG_CLIENTE,"
				+ "CPF_CLIENTE,SEXO_CLIENTE, REGISTROCNH_CLIENTE, "
				+ "VALIDADECNH_CLIENTE, PRIMEIRACNH_CLIENTE,LOGRADOURO_CLIENTE, NUMERO_CLIENTE,"
				+ "BAIRRO_CLIENTE,CEP_CLIENTE, DATACADASTRO_CLIENTE, PESQUISA1_CLIENTE, "
				+ "PESQUISA2_CLIENTE, PESQUISA3_CLIENTE, PESQUISA4_CLIENTE,"
				+ "OBSERVACAO_CLIENTE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
	
		
		banco.abreConecxao();
		try {
			comando = banco.conecta.prepareStatement(sql,
					PreparedStatement.RETURN_GENERATED_KEYS);
			comando.setString(1, cliente.getNome());
			comando.setString(2, cliente.getNascimento());
			comando.setString(3, cliente.getEmail());
			comando.setString(4, cliente.getEscolaridade());
			comando.setString(5, cliente.getTelefone());
			comando.setString(6, cliente.getCelular());
			comando.setString(7, cliente.getProfissao());
			comando.setString(8, cliente.getRg());
			comando.setString(9, cliente.getCpf());
			comando.setString(10, cliente.getSexo());
			comando.setString(11, cnh.getRegistroCnh());
			comando.setString(12, cnh.getDtValidade());
			comando.setString(13, cnh.getPrimeiraHabilitacao());
			comando.setString(14, endereco.getLogradouro());
			comando.setString(15, endereco.getNumero());
			comando.setString(16, endereco.getBairro());
			comando.setString(17, endereco.getCep());
			comando.setString(18, cadastroCliente.getData());
			comando.setString(19, cadastroCliente.getPesquisa1());
			comando.setString(20, cadastroCliente.getPesquisa2());
			comando.setString(21, cadastroCliente.getPesquisa3());
			comando.setString(22, cadastroCliente.getPesquisa4());
			comando.setString(23, cadastroCliente.getObservacao());
			comando.execute();
			banco.desconectar();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return;

		}
	}
}
	 
