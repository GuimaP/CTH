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
<<<<<<< HEAD
import net.sf.jasperreports.engine.JasperManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
=======
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
>>>>>>> 27bb23bc526c9b3c27eb9af8a424da068ccb1ac3
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.oasis.JROdsExporter;
import net.sf.jasperreports.engine.util.JRLoader;
<<<<<<< HEAD
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.hibernate.Session;
=======

import org.hibernate.Session;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.engine.spi.SessionFactoryImplementor;
>>>>>>> 27bb23bc526c9b3c27eb9af8a424da068ccb1ac3
import org.hibernate.internal.SessionImpl;

public class ReportUtil {
	public static final int RELATORIO_PDF = 1; 
	public static final int RELATORIO_EXCEL = 2;
	public static final int RELATORIO_HTML = 3;
	public static final int RELATORIO_PLANILHA_OPEN_OFFICE = 4;
	
<<<<<<< HEAD
	public void gerarRelatorio(HashMap parametrosRelatorio,String nomeRelatorioJasper,String nomeRelatorioSaida,int tipoRelatorio){
		try{
			Session session = ConnectionFactoryConfig.openManger().openSession();
			String caminhoArquivoJasper = "C:\\Users\\Guima\\report1.jasper";
//			String caminhoJasper = "C:\\Users\\Guima\\report1.jrxml";
			String caminhoArquivoRelatorio = null;
			JasperReport relatorioJasper = (JasperReport) JRLoader.loadObject(caminhoArquivoJasper);
=======
	public void gerarRelatorio(Map parametrosRelatorio,String nomeRelatorioJasper,String nomeRelatorioSaida,int tipoRelatorio){
		try{
			Session session = ConnectionFactoryConfig.openManger().openSession();
			String caminhoRelatorio = System.getProperty("user.home")+File.separator + "demoPath";
			String caminhoJasper = "C:\\Users\\TecnicoN\\report1.jasper";
			String caminhoArquivoRelatorio = null;
			JasperReport relatorioJasper = (JasperReport) JRLoader.loadObject(caminhoJasper);
>>>>>>> 27bb23bc526c9b3c27eb9af8a424da068ccb1ac3
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
			
<<<<<<< HEAD
			caminhoArquivoRelatorio = "C:\\Users\\Guima\\Documents\\"+nomeRelatorioSaida+"."+extensaoArquivoExportado;
=======
			caminhoArquivoRelatorio = "C:\\Users\\TecnicoN\\Documents\\"+nomeRelatorioSaida+"."+extensaoArquivoExportado;
>>>>>>> 27bb23bc526c9b3c27eb9af8a424da068ccb1ac3
			arquivoGerado = new File(caminhoArquivoRelatorio);
			tipoArquivo.setParameter(JRExporterParameter.JASPER_PRINT, impressoraJasper);
			tipoArquivo.setParameter(JRExporterParameter.OUTPUT_FILE, arquivoGerado);
			tipoArquivo.exportReport();
//			arquivoGerado.deleteOnExit();
			InputStream conteudoRelatorio = new FileInputStream(arquivoGerado);
<<<<<<< HEAD
			System.out.println("feito");
=======
>>>>>>> 27bb23bc526c9b3c27eb9af8a424da068ccb1ac3
			
		}catch(Exception er){
			
		}
	}
	
<<<<<<< HEAD

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
=======
	public void imprimir(List<Carro> lstCarros){
			JasperReport report;
			try {
				report = JasperCompileManager.compileReport("C:\\Users\\TecnicoN\\report1.jrxml");
				JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(lstCarros));
				 
				JasperExportManager.exportReportToPdfFile(print, "C:\\Users\\TecnicoN\\Documents\\demo,pdf");
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
				
		
	}
	
	public static void main(String[] args) throws Exception {
		List<Carro> lstCarros;
		try {
			lstCarros = new RepositoryCarro().buscarTodos();
			HashMap<String, String>paramaters = new HashMap<String, String>();
			String nomeRelatorioJasper = "Carros";
			String nomeRelatorioSaida = "carross";
//			lstCarros.get(0).get
>>>>>>> 27bb23bc526c9b3c27eb9af8a424da068ccb1ac3
			lstCarros.forEach(c ->{
				paramaters.put("Marca", c.getMarca());
				paramaters.put("Placa", c.getPlaca());
				paramaters.put("Modelo", c.getMarca());
				paramaters.put("Ano",String.valueOf(c.getAno()));
			});
<<<<<<< HEAD
			
			
			

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
=======
			ReportUtil reportUtil = new ReportUtil();
			reportUtil.gerarRelatorio(paramaters, nomeRelatorioJasper, nomeRelatorioSaida, ReportUtil.RELATORIO_PDF);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception e){
			throw new Exception(e);
>>>>>>> 27bb23bc526c9b3c27eb9af8a424da068ccb1ac3
		}
		
	}
	
<<<<<<< HEAD
	private static Connection getConnection() {
=======
	private Connection getConnection() {
>>>>>>> 27bb23bc526c9b3c27eb9af8a424da068ccb1ac3
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
