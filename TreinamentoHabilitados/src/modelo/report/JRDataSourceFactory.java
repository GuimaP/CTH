package modelo.report;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import model.Financeiro;
import model.relatorios.FinanceiroRelatorio;
import model.repository.RepositoryFinanceiro;
import net.sf.jasperreports.engine.JRDataSource;

public class JRDataSourceFactory {

	private static JRDataSource data;
	
	public static final int FINANCEIRO_LISTAGEM = 1;
	public static final int CLIENTES = 2;
	

	public static JRDataSource createDatasource(int tipoRelatorio) {
		switch (tipoRelatorio) {
		case 1:
			return listaDeFinances();

		default:
			return null;
		}
	}
	
	public static JRDataSource createDatasourcePorData(int tipoRelatorio,Date dtInicial,Date dtFinal) {
		switch (tipoRelatorio) {
		case 1:
			return listaDeFinancesPorData(dtInicial,dtFinal);

		default:
			return null;
		}
	}
	
	private static JRDataSource listaDeFinances(){
		try {
			List<Financeiro> lstF = new RepositoryFinanceiro().buscarTodos1();
			List<FinanceiroRelatorio> lstR = new ArrayList<FinanceiroRelatorio>();
			
			lstF.forEach(e->{
				lstR.add(new FinanceiroRelatorio(e));
			});
			
			if(lstR.size()> 0){
			data = new FinanceReportDataSource(lstR);
			}else {
				data = null;
			}
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	
	private static JRDataSource listaDeFinancesPorData(Date dtInicial,Date dtFinal){
		try {
			List<Financeiro> lstF = new RepositoryFinanceiro().buscarPorData(dtInicial, dtFinal);
			List<FinanceiroRelatorio> lstR = new ArrayList<FinanceiroRelatorio>();
			
			lstF.forEach(e->{
				lstR.add(new FinanceiroRelatorio(e, dtInicial, dtFinal));
			});
			
			
			//Verifica se possui resultados da pesquisa, se n�o, retorna null
			if(lstR.size()>0){
				data = new FinanceReportDataSource(lstR);
			}else {
				return null;
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	
	
	
}

