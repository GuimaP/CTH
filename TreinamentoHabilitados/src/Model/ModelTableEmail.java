package Model;

import java.util.List;

import javax.mail.Address;
import javax.swing.table.AbstractTableModel;

public class ModelTableEmail extends AbstractTableModel{
private String[] columns = {"<html><b>Email</b></html>"};

private List<String>lsEmails;

public ModelTableEmail(List<String>ls) {
	this.lsEmails = ls;
}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columns.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return lsEmails.size() ;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		
		return lsEmails.get(arg0);
	}
	
	public int getIdEmail(int index){
		index = (lsEmails.size()) - index;
		return index;
	}
	
	@Override
		public String getColumnName(int column) {
			return columns[column];
		}
	//
public void marcaComoLida(int index){
	String string = lsEmails.get(index);
	string = string.replaceAll("<html>","");
	string = string.replaceAll("</html>","");
	string = string.replaceAll("<b>","");
	string = string.replaceAll("</b>","");
	lsEmails.set(index, string);
	
	fireTableRowsUpdated(0, lsEmails.size()-1);
}
	
	
	public List<String> getList(){
		return lsEmails;
	}
	
	@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}
	
}
