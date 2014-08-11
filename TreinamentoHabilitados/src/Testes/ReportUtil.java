package Testes;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;

import model.Carro;
import model.repository.ConnectionFactoryConfig;
import model.repository.RepositoryCarro;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.oasis.JROdsExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;

public class ReportUtil {
	public static final int RELATORIO_PDF = 1; 
	public static final int RELATORIO_EXCEL = 2;
	public static final int RELATORIO_HTML = 3;
	public static final int RELATORIO_PLANILHA_OPEN_OFFICE = 4;
	
	public void gerarRelatorio(HashMap parametrosRelatorio,String nomeRelatorioJasper,String nomeRelatorioSaida,int tipoRelatorio){
		try{
			Session session = ConnectionFactoryConfig.openManger().openSession();
			String caminhoArquivoJasper = "C:\\Users\\Guima\\report1.jasper";
//			String caminhoJasper = "C:\\Users\\Guima\\report1.jrxml";
			String caminhoArquivoRelatorio = null;
			JasperReport relatorioJasper = (JasperReport) JRLoader.loadObject(caminhoArquivoJasper);
			Connection con = this.getConnection();
			JasperPrint impressoraJasper = JasperFillManager.fillReport(relatorioJasper, parametrosRelatorio,con);
			JRExporter tipoArquivo = null;
			String extensaoArquivoExportado = "";
			
			File arquivoGerado = null;
			
			switch (tipoRelatorio) {
			case RELATORIO_PDF:
				tipoArquivo = new JRPdfExporter();
				extensaoArquivoExportado = "pdf";
				break;
			case RELATORIO_EXCEL:
				tipoArquivo = new JRXlsExporter();
				extensaoArquivoExportado = "xls";
				break;
			case RELATORIO_HTML:
				tipoArquivo = new JRHtmlExporter();
				extensaoArquivoExportado = "html";
				break;
			case RELATORIO_PLANILHA_OPEN_OFFICE:
				tipoArquivo = new JROdsExporter();
				extensaoArquivoExportado = "ods";
				break;
			
			default:
				tipoArquivo = new JRPdfExporter();
				extensaoArquivoExportado = "pdf";
				break;
			}
			
			caminhoArquivoRelatorio = "C:\\Users\\Guima\\Documents\\"+nomeRelatorioSaida+"."+extensaoArquivoExportado;
			arquivoGerado = new File(caminhoArquivoRelatorio);
			tipoArquivo.setParameter(JRExporterParameter.JASPER_PRINT, impressoraJasper);
			tipoArquivo.setParameter(JRExporterParameter.OUTPUT_FILE, arquivoGerado);
			tipoArquivo.exportReport();
//			arquivoGerado.deleteOnExit();
			InputStream conteudoRelatorio = new FileInputStream(arquivoGerado);
			System.out.println("feito");
			
		}catch(Exception er){
			
		}
	}
	

	public void imprimir(HashMap parameter,String file,Connection con) throws JRException{
		JasperDesign jasperDesign = JasperManager.loadXmlDesign(file);
		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
		JasperPrint print = JasperFillManager.fillReport(jasperReport, parameter, con);		
		JRExporter exporter = new JRPdfExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
		String caminhoArquivoRelatorio = "C:\\Users\\Guima\\Documents\\"+"asdsa"+"."+"pdf";
		File arquivoGerado = new File(caminhoArquivoRelatorio);
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE, arquivoGerado);
		exporter.exportReport();
		
	}
	
	public static void main(String[] args) {
		List<Carro> lstCarros;
		try {

			
			
			
			
			
			lstCarros = new RepositoryCarro().buscarTodos();
			HashMap<String, Object>paramaters = new HashMap<String, Object>();
			String nomeRelatorioJasper = "Carros";
			String nomeRelatorioSaida = "q12123carross";
		//	lstCarros.get(0).get
			lstCarros.forEach(c ->{
				paramaters.put("Marca", c.getMarca());
				paramaters.put("Placa", c.getPlaca());
				paramaters.put("Modelo", c.getMarca());
				paramaters.put("Ano",String.valueOf(c.getAno()));
			});
			
			
			

//			 exportacao do relatorio para outro formato, no caso PDF
//			JasperExportManager.exportReportToPdf	(print,
//					"C:\\Users\\Guima\\report1.xls");
			
			ReportUtil reportUtil = new ReportUtil();
//			reportUtil.gerarRelatorio(paramaters, nomeRelatorioJasper, nomeRelatorioSaida, ReportUtil.RELATORIO_PDF);
			reportUtil.imprimir(paramaters,"C:\\Users\\Guima\\report1.jrxml",reportUtil.getConnection());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static Connection getConnection() {
		Connection connection = null;
		try{
			Context initContext= new InitialContext();
//			Context evtContext = (Context)initContext.lookup("java:/comp/env/");
//			javax.sql.DataSource ds = (javax.sql.DataSource) evtContext.lookup("jdbc/db");
			Session session = ConnectionFactoryConfig.openManger().openSession();
			
		    connection = ((SessionImpl)session).connection();
		     
//			connection = (Connection) ds.getConnection();
		}catch(Exception e){
			e.printStackTrace();
		}
		return connection;
		
	}
}
