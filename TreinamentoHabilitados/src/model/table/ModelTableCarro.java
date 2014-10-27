package model.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Carro;

public class ModelTableCarro extends AbstractTableModel {

	List<Carro> listCarro;
	String[] colunas = { "Modelo", "Ano", "Placa" };

	public ModelTableCarro(List<Carro> listCarro) {
		this.listCarro = listCarro;
	}
	@Override
	public int getColumnCount() {
		return colunas.length;
	}
	@Override
	public int getRowCount() {
		return listCarro.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex){
        case 0:
            return listCarro.get(rowIndex).getModelo();
        case 1:
            return listCarro.get(rowIndex).getAno();
        case 2:
            return listCarro.get(rowIndex).getPlaca();
        default:
            break;
    }
    return null;
		
	}
	public String getColumnName(int column) {

		return colunas[column];
	}
	public Carro getCarro(int index){
		return listCarro.get(index);
	}

}
