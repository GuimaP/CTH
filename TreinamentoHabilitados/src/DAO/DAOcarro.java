package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Carro;

public class DAOcarro extends DAOconexao {
	private DAOconexao banco;
	private String sql;
	private PreparedStatement comando;
	private ResultSet consulta;

	
	public DAOcarro (){
		banco = new DAOconexao();
		
	}
	
	public void inserir(Carro carro){
		sql = "INSERT INTO tb_carro(ANO_CARRO, MARCA_CARRO, "
				+ "MODELO_CARRO,PLACA_CARRO) VALUES (?,?,?,?)";
		
		
		banco.abreConecxao();
		try {
			comando = banco.conecta.prepareStatement(sql, 
					PreparedStatement.RETURN_GENERATED_KEYS);
			comando.setLong(1, carro.getAno());
			comando.setString(2, carro.getMarca());
			comando.setString(3, carro.getModelo());
			comando.setString(4,carro.getPlaca());
			comando.execute();
			banco.desconectar();
		
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return;
		}
	}
	public List<Carro> buscarCarro() throws SQLException{
		List<Carro> carroLista = new ArrayList<>();
		Carro carroObj = new Carro();
		banco.abreConecxao();
		
		sql = "SELECT ANO_CARRO, MARCA_CARRO, MODELO_CARRO, PLACA_CARRO FROM tb_carro";
				
		if(banco.abreConecxao()){
			comando = banco.conecta.prepareStatement(sql);
			ResultSet rs = comando.executeQuery();
			while (rs.next()){
				carroObj = new Carro();
				carroObj.setAno(rs.getLong("ANO_CARRO"));
				carroObj.setMarca(rs.getString("MARCA_CARRO"));
				carroObj.setModelo(rs.getString("MODELO_CARRO"));
				carroObj.setPlaca(rs.getString("PLACA_CARRO"));
				carroLista.add(carroObj);
			}
			banco.desconectar();
		}
		
		return carroLista;
	}
	
}
