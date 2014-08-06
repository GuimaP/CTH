package Model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ModeloTableAgendamento extends AbstractTableModel {

	ArrayList<Agendamento> listAgendamento;
	String[] colunas = { "Aula", "Descrição", "Data", "Hora" };

	public ModeloTableAgendamento(ArrayList<Agendamento> listAgendamento) {
		this.listAgendamento = listAgendamento;
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public int getRowCount() {
		return listAgendamento.size();

	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		switch (columnIndex) {
		case 0:
			return listAgendamento.get(rowIndex).getAulas().getId();
		case 1:
			return listAgendamento.get(rowIndex).getAulas().getDescricao();
		case 2:
			return listAgendamento.get(rowIndex).getData();
		case 3:
			return listAgendamento.get(rowIndex).getHora();
		default:
			break;

		}

		return null;
	}
	
	public String getColumnName(int column) {

		return colunas[column];
	}


}
