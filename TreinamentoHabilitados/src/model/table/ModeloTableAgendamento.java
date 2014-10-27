package model.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Aula;

public class ModeloTableAgendamento extends AbstractTableModel {

	List<Aula> aulas;
	String[] colunas = { "Aula","Data", "Hora", "Descrição" };

	public ModeloTableAgendamento(List<Aula> aulas) {
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
			
			return aulas.get(rowIndex).getData().getHours();
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
