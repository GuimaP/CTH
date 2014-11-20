package modelo.report;

import java.util.Iterator;
import java.util.List;

import model.Cliente;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class ClienteReportDataSource implements JRDataSource {

	private Cliente cursor;
	private Iterator<Cliente> iterator;

	public ClienteReportDataSource(List<Cliente> ls) {
		iterator = ls.iterator();
	}

	@Override
	public Object getFieldValue(JRField arg0) throws JRException {
		Cliente cliente = cursor;
		return null;
	}

	@Override
	public boolean next() throws JRException {
		boolean retorno = iterator.hasNext();

		if (retorno) {
			cursor = iterator.next();
		}
		return retorno;

	}

}
