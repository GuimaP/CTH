package model.table;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebParam.Mode;
import javax.swing.table.AbstractTableModel;

import model.Cliente;

public class ModeloTableCliente extends AbstractTableModel {

	List<Cliente> cliente;
	String[] colunas = { "Aluno", "Cpf"};

	public ModeloTableCliente(List<Cliente> cliente) {
		this.cliente = cliente;
	}

	public int getRowCount() {
		return cliente.size();
	}

	public int getColumnCount() {

		return colunas.length;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {

		switch (columnIndex) {
		case 0:
			return cliente.get(rowIndex).getNome();
		case 1:
			return cliente.get(rowIndex).getCpf();
		default:
			break;
		}

		return null;
	}

	public String getColumnName(int column) {

		return colunas[column];
	}
	

}
