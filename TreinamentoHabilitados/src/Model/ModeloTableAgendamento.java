package Model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ModeloTableAgendamento extends AbstractTableModel {

	ArrayList<Aula> aulas;
	String[] colunas = { "Aula","Data", "Hora", "Descrição" };

	public ModeloTableAgendamento(ArrayList<Aula> aulas) {
		this.aulas = aulas;
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public int getRowCount() {
		return aulas.size();

	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		switch (columnIndex) {
		case 0:
			return rowIndex;
		case 1:
			return aulas.get(rowIndex).getData();
		case 2:
			
			return aulas.get(rowIndex).getHora();
		case 3:
			return aulas.get(rowIndex).getDescAulas();
		default:
			break;

		}

		return null;
	}
	
	public String getColumnName(int column) {

		return colunas[column];
	}


}
