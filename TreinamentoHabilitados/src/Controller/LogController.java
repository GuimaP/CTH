package Controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import Main.Start;

public class LogController {
	public static void insertLog(Exception e){
		 try{
		 Date dt = new Date(System.currentTimeMillis());
         String nameFile = "log-" + new SimpleDateFormat("dd//MM/yy").format(dt) + ".txt"; //Montro um nome de arquivo de acordo com o dia do log
         String dirPath = Start.class.getClass().getResource("/").getPath() + nameFile; //Monto o caminho que será salvo esse arquivo
         
         
         File arquivoLog = new File(dirPath); //Instancio um arquivo
         if (!arquivoLog.exists()) { //Verifico se o arquivo existe
        	 arquivoLog.createNewFile();//Se não eu crio um novo
         }
         
         System.out.println(arquivoLog.getPath());
         
         String msg = dt + " -> " + e.getMessage(); //Monto a mensagem de erro
         
         FileWriter arq = new FileWriter(arquivoLog);
         PrintWriter gravarArq;
         gravarArq = new PrintWriter(arq);
         gravarArq.println(msg); //e gravo no arquivo

         arq.close();
		 }catch(IOException er){
			 er.printStackTrace();
		 }
	}
}
