package modelo.report;

import java.text.NumberFormat;
import java.util.Iterator;
import java.util.List;

import model.enums.EnumTipoDespesa;
import model.relatorios.FinanceiroRelatorio;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class FinanceReportDataSource implements JRDataSource {

	private Iterator<FinanceiroRelatorio> iterator;
	private double total = 0;
	
	private FinanceiroRelatorio cursor;
	
	public FinanceReportDataSource(List<FinanceiroRelatorio> f) {
		super();
		iterator = f.iterator();
	}
	
	
	@Override
	public Object getFieldValue(JRField nome) throws JRException {
		FinanceiroRelatorio finance = cursor;
		
		if(nome.getName().equalsIgnoreCase("dataPeriodo")){
			return finance.getDataPeriodo();
		}
		
		if(nome.getName().equalsIgnoreCase("DATA")){
			return finance.getDataLancamento();
		}
		
		if (nome.getName().equalsIgnoreCase("DESCRICAO")) {
			return finance.getDescricao();
		}
		if (nome.getName().equalsIgnoreCase("tipoPagamento")) {
			return finance.getTipoPagamento();
		}
		if (nome.getName().equalsIgnoreCase("VALOR")) {
			switch (finance.getTipoPagamentoEnum()) {
			case Desp :
			case Est :
			case Manu :
			case Salario:
				total -= finance.getValor();
				break;
			default:
				total += finance.getValor();
				break;
			}
			return finance.getValorStr();
		}
		
		if(nome.getName().equalsIgnoreCase("NOME")){
			return finance.getNomeFuncionario();
		}
		
		
		
		if(nome.getName().equalsIgnoreCase("total")){
			NumberFormat formatter = NumberFormat.getCurrencyInstance();
			String moneyString = formatter.format(this.total);
			if(total < 0 ){
				return "TOTAL : " + moneyString;
			}else {
				return "TOTAL : +" + moneyString;
			}
			
		}
		return null;

	}

	@Override
	public boolean next() throws JRException {
		boolean retorno = iterator.hasNext();
		
		if(retorno){
			cursor=iterator.next();
		}
		return retorno;

	}
	
}
