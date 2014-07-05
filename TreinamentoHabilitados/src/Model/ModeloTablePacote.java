/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Vitor
 */
public class ModeloTablePacote extends AbstractTableModel{
    ArrayList<Pacote> listPacote;
    String[] colunas = {"Descrição", "Aulas", "Preço"};
        
    public ModeloTablePacote(ArrayList<Pacote> listPacote){
        this.listPacote = listPacote;
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
