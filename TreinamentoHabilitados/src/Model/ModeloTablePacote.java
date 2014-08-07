/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Vitor
 */
public class ModeloTablePacote extends AbstractTableModel{
    List<Pacote> listPacote;
    String[] colunas = {"Descri��o", "Aulas", "Pre�o"};
        
    public ModeloTablePacote(List<Pacote> listPacote2){
        this.listPacote = listPacote2;
    }
    @Override
    public int getRowCount() {
        return listPacote.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0:
                return listPacote.get(rowIndex).getDescricao();
            case 1:
                return listPacote.get(rowIndex).getAulas();
            case 2:
                return listPacote.get(rowIndex).getPrecoPacote();
            default:
                break;
        }
        return null;
    }
    public String getColumnName(int column) {

		return colunas[column];
	}
	
    
}
