package Model;

import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;
public class ModeloTableFuncionario extends AbstractTableModel{
	
	ArrayList<Funcionario> func;
	String [] colunas = {"Nome", "Cpf","Telefone","Celular"};
	
	public ModeloTableFuncionario(ArrayList<Funcionario> func){
		this.func = func;
		
	}
	@Override
	public int getColumnCount() {
		return colunas.length;
	}
	@Override
	public int getRowCount() {
		return func.size();
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex){
		case 0:
			return func.get(rowIndex).getNome();
                case 1:
                    return func.get(rowIndex).getCpf();
                case 2: 
                    return func.get(rowIndex).getTelefone();
                case 3: 
                    return func.get(rowIndex).getCelular();
		default:
			break;
		}
		return null;
	}
	public String getColumnName(int column) {

		return colunas[column];
	}
	
	
}
