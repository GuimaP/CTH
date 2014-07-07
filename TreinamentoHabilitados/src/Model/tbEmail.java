package Model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class tbEmail extends AbstractTableModel{
private String[] columns = {"Email"};

private List<String>lsEmails;

public tbEmail(List<String>ls) {
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
	
}
