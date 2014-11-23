package controller;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.annotations.Sort;

import exceptions.ErrorRelatorioException;
import modelo.report.JRDataSourceFactory;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.oasis.JROdsExporter;
import net.sf.jasperreports.view.JasperViewer;

public class ReportUtil{
	public static final int HTML_REPORT = 1;
	public static final int PDF_REPORT = 2;
	public static final int EXCEL_REPORT = 3;
	public static final int OPEN_REPORT = 4;
	
	public static final int FINANCEIRO_LISTAGEM = 1;
	public static final int CLIENTES = 2;
	
	
	
	public static void gerarRelatorio(JRDataSource source,int tipoRelatorio,String nomeRelatorio,int tipoFormato) throws JRException,ErrorRelatorioException {
		
		//Verifica se possui dados na fonte de dados
		if(source == null){
			throw new ErrorRelatorioException("nao ha dados para gerar este relatorio");
		}
		
		
		Map map = new HashMap();
		
		/*
		 * Escolhe o tipo de relatorio, se � de Financeiro, listagem de clientes e etc.
		 */
		
		switch (tipoRelatorio) {
		case 1:
			JasperCompileManager.compileReport(ReportUtil.class.getResourceAsStream("/report/jrxml/financeiro.jrxml"));
			JasperPrint print = JasperFillManager.fillReport(ReportUtil.class.getResourceAsStream("/report/jasper/financeiro.jasper"), map,source);
			
			JRExporter export = null;
			
			String local = System.getProperty("user.home"); // Pego o diretorio do
			// usuario
			String nameFile = System.getProperty("file.separator") + "CTH";
			String dt = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yy"));
			
			
			
			String extensao = "";
			/*
			 * Escolhe o tipo de extensão
			 */
			switch (tipoFormato) {
			case 1:
				extensao = "html";
				export = new JRHtmlExporter();
				
				break;
				
			case 2:
				extensao = "pdf";
				export = new JRPdfExporter();
				break;
			case 3:
				extensao = "xls";
				export = new JRXlsExporter();
				break;
				
			case 4:
				extensao = "ods";
				export = new JROdsExporter();
				break;
			default:
				break;
			}
			
			File diretorioReport = new File(local+nameFile+File.separator+"Relatorios"+File.separator+nomeRelatorio+" "+dt.toString()+"."+extensao);
			export.setParameter(JRExporterParameter.JASPER_PRINT, print);
			export.setParameter(JRExporterParameter.OUTPUT_FILE, diretorioReport);
			export.exportReport();
			
			break;

		default:
			break;
		}
		

	}

	
}
