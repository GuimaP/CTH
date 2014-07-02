package View;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import Model.Tarefa;

public class TableModelListaTarefas extends AbstractTableModel {

	private String colunas[] = {"Data","Hora","Descrição "};
	private List<Tarefa> listaTarefas;
	public TableModelListaTarefas(List<Tarefa> lista ) {
		this.listaTarefas = lista;
	}
	
	@Override
	public int getRowCount() {
		return listaTarefas.size();
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex){
		
		case 0:
			return new SimpleDateFormat("dd/MM/yyyy").format(listaTarefas.get(rowIndex).getDataCompromisso());
		case 1:
			return listaTarefas.get(rowIndex).getHoraCompromisso(); 
			
		case 2:
			
			return listaTarefas.get(rowIndex).getDescricao();
		default :
			return null;
		}
		
		
	}
	
	@Override
	public String getColumnName(int column) {
		return colunas[column];
	}

	public Tarefa getTarefa(int rowIndex) {
		return listaTarefas.get(rowIndex);
	}
}
