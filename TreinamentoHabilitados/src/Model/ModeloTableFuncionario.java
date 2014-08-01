package Model;

import java.io.ObjectInputStream.GetField;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.media.Format;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.MaskFormatter;
public class ModeloTableFuncionario extends AbstractTableModel{
	
	List<Funcionario> func;
	String [] colunas = {"Nome", "Cpf","Telefone","Celular"};
	
	public ModeloTableFuncionario(List<Funcionario> func){
		this.func = func;
		
	}
	@Override
	public int getColumnCount() {
		return colunas.length;
	}
	@Override
	public int getRowCount() {
		return func.size();
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex){
		case 0:
			return func.get(rowIndex).getNome();
                case 1:
                    return func.get(rowIndex).getCpf();
                case 2: 
                	
                	String cpf = func.get(rowIndex).getTelefone();
                	try{
                	MaskFormatter mask =new MaskFormatter("(##) ####-####");
                	mask.setValueContainsLiteralCharacters(false);
                	cpf = mask.valueToString(func.get(rowIndex).getTelefone());
                	}catch(ParseException e){/*handled Execpetion*/}
                    return cpf;
                case 3: 
                    return func.get(rowIndex).getCelular();
		default:
			break;
		}
		return null;
	}
	public String getColumnName(int column) {

		return colunas[column];
	}
	
	public Funcionario getFuncionario(int index){
		return func.get(index);
	}
	
	
}
