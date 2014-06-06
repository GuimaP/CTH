package modelo;

import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;
public class ModeloTableFun extends AbstractTableModel{
	
	ArrayList<Funcionario> func;
	String [] colunas = {"Nome"};
	
	public ModeloTableFun(ArrayList<Funcionario> func){
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
		default:
			break;
		}
		return null;
	}
	public String getColumnName(int column) {

		return colunas[column];
	}
	
	
}
