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
		index -= (lsEmails.size() - 1);
		return index;
	}
	
	@Override
		public String getColumnName(int column) {
			return columns[column];
		}
	
	public void addItem(MensagemEmail m){
		String strNewMsg = "<html><b> De: ";
		for(Address a : m.getFrom()){
			strNewMsg += a.toString();
		}
		strNewMsg += "</b></html>";
		lsEmails.add(strNewMsg);
		this.fireTableDataChanged(); //Notifico que houve uma alteração na tabela
	}
	
}
