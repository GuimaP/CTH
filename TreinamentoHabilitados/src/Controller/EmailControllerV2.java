package Controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.FetchProfile;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.search.FlagTerm;
import javax.mail.search.SearchTerm;
import javax.swing.ListModel;

import org.eclipse.swt.internal.win32.MSG;
import org.hibernate.context.TenantIdentifierMismatchException;
import org.w3c.dom.ls.LSSerializer;

import com.itextpdf.awt.geom.misc.Messages;
import com.mysql.jdbc.jdbc2.optional.SuspendableXAConnection;

import Model.MensagemEmail;
import Model.UsuarioEmail;

public class EmailControllerV2 {
	private Session session;
	private Store store;

	private UsuarioEmail configEmail;
	private int nTentativas;
	
	private int countEmailAtual,countEmailTotal;
	private String nameFolderAtual;
	public boolean isLoadingEmail;

	private Map<String,Message[]>map;
	
	private Map<String, List<MensagemEmail>> mapArquivosEmail;

	private synchronized String getStatusLoadEmail(){
		String status;
		if(isLoadingEmail){
			status = "Carregando "+ countEmailAtual+ " de " + countEmailTotal + " da pasta "+ nameFolderAtual;
		}else {
			status = "Email Carregado";
		}
		return status;
	}
	
	public EmailControllerV2(UsuarioEmail em) {
		try {
			this.configEmail = em;
			mapArquivosEmail = new HashMap<String,List<MensagemEmail>>();
			autentica();
			map = new HashMap<String,Message[]>();
			for (String name : getFolders()){
				System.out.println(name);
				Folder f = store.getFolder(name);
				if(f.list().length == 0){
					System.out.println(f.isSubscribed());
					f.open(Folder.READ_ONLY);
					map.put(name, f.getMessages());
				}
				
			}
			
//			if (!importaArquivoEmail()) { //Se não houve um aqruivo no diretorio
//				loadEmails(); // Eu começo tudo do zero
//				atualizaArquivoEmail(); // e salvo o arquivo
//				
//			}
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}
	
	
	
	public Map<String, Message[]> getMap() {
		return map;
	}

	public EmailControllerV2() {
		
	}
	
	public String getUser(){
		return configEmail.getUser();
	}

	public void loadEmails() {
		try {
			
			if(!store.isConnected()){
				if(nTentativas >10){
					return;
				}
				store.connect(configEmail.getHost(), configEmail.getUser(), configEmail.getPass());
				nTentativas++;
				loadEmails();
			}else {
				isLoadingEmail = true; //Informe que os itens estão sendo carregado
				
			nTentativas = 0;
			
			List<String> ls = getFolders(); //Pego todas as folder que existirem
			
																			 
			File arq;
			int cont = 0;
			for (String fn : ls) {
				List<MensagemEmail> lsEmails = new ArrayList<MensagemEmail>(); //list de Email referente a uma Folder
				try{
				int index = 0;
				int c =0;
				nameFolderAtual = fn; //Indico o nome da pasta atual
				
				arq = new File(getClass().getResource("/Resources/FilesConfig").getPath()+"/"
								+configEmail.getUser()+"@"+fn+".temp"); //Verifico se possui ja um arquivo de list no diretorio para continuar de onde parei...
				
				System.out.println(arq);
				
				Folder f = store.getFolder(fn); 
				f.open(Folder.READ_ONLY);
				
				System.out.println("PASTA>" + fn);
				
				Message[] msgs = f.getMessages();
				
				countEmailTotal = msgs.length; //Email total que tem na caixa;
				
				
				if(arq.exists()){
					System.out.println("o arquivo existe"); 
					
					FileInputStream in = new FileInputStream(arq);
					ObjectInputStream os = new ObjectInputStream(in);  
					List<MensagemEmail>lsEm = (List<MensagemEmail>)os.readObject(); //Leio o objeto serializado
					index = lsEm.size() - 1 ; // e continuo da onde parou 
					
					c = index; 
					
					lsEmails = lsEm; // e uso no meu arquivo
				}
				
				System.out.println("Index: -  "+index +  " || Tamanho : "+msgs.length);
				
				for(int i = index; i <msgs.length; i++){
//				for(Message m : msgs){
				Message m = msgs[i];
					System.out.println((++c)+" > Load");
					countEmailAtual = c;	
					lsEmails.add(lerEmails(m)); //Leio o Email
					saveListTemp(fn, lsEmails); // E Então ja atualizo o meu arquivo
				}
				mapArquivosEmail.put(fn, lsEmails); //E assim que acaba eu insiro no meu Map de Emails
				atualizaArquivoTempEmail(); // E Atualizo também o meu Map de Email

			}catch (MessagingException e) {
				System.out.println(e.getMessage());
				continue;
			}
			}
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
	}

	private void gerenciaEmails(){
		try{
			List<String>lsFolders = getFolders();
			lsFolders.forEach(folders ->{
				try {
					Folder f = this.store.getFolder(folders);
					f.open(Folder.READ_ONLY);
					double total = f.getMessageCount();
					
					int nThreads =(int)  Math.ceil(total / 10);
					System.out.println(nThreads);
					
					for(int i = 0 ; i <= nThreads; ++i){
						new Thread(()->{
							try{
							
							
							}catch(Exception e){
								e.printStackTrace();
							}
						});
					}
					

				} catch (Exception e) {
					
					e.printStackTrace();
				}
				
			});
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public synchronized  void loadEmails(String folderName,int part,int indexInicial,int indexFinal){
//		try {
//			
//			if(!store.isConnected()){
//				if(nTentativas >10){
//					return;
//				}
//				store.connect(configEmail.getHost(), configEmail.getUser(), configEmail.getPass());
//				nTentativas++;
//				loadEmails(folderName,part,indexInicial,indexFinal);
//			}else {
//			nTentativas = 0;
//			
//			
//			List<MensagemEmail> lsEmails = new ArrayList<MensagemEmail>(); /*
//																			 * list
//																			 * que
//																			 * vai
//																			 * conter
//																			 * todos
//																			 * os
//																			 * objetos
//																			 * de
//																			 * e
//																			 * -
//																			 * mail
//																			 */
//			
//			File arqEmail = new File(getClass().getResource("/Resources/FilesConfig").getPath()+"/"+this.getUser()+"@"+folderName+"-part"+part+".temp");
//			int index = indexInicial;
//			if(arqEmail.exists()){
//				FileInputStream in = new FileInputStream(arqEmail);
//				ObjectInputStream os = new ObjectInputStream(in);
//				lsEmails = (List<MensagemEmail>)os.readObject();
//				index = lsEmails.size() - 1;
//			}
//			
////				
////				Folder f = store.getFolder(folderName);
////				f.open(Folder.READ_ONLY);
////				System.out.println("PASTA>" + folderName + "part " + part);
//
//				
//				int c =0;
//				for(int i = index; i<= indexFinal; i++){
//					System.out.println((++c)+" > Load");
//					lsEmails.add(lerEmails(msgs[i]));
//					String nameFile = this.getUser()+"@"+folderName+"-part"+part+".temp";
//					saveListTemp(nameFile, lsEmails);
//				}
//				mapArquivosEmail.put(folderName, lsEmails);
//				atualizaArquivoEmail();
//
//			}
//		
//		} catch (MessagingException e) {
//			e.printStackTrace();
//		} catch (Exception e2) {
//			e2.printStackTrace();
//		}
	}
	
	
	
	
	public Map<String, List<MensagemEmail>> getMapArquivosEmail() {
		return mapArquivosEmail;
	}

	private boolean importaArquivoEmail() throws IOException,
			ClassNotFoundException {
		File arqMap = new File(getClass().getResource("/Resources/FilesConfig")
				.getPath() + "/" + configEmail.getUser() + "@itensEmail.cr");
		if (arqMap.exists()) {
			FileInputStream in = new FileInputStream(arqMap);
			ObjectInputStream os = new ObjectInputStream(in);
			mapArquivosEmail = (Map<String, List<MensagemEmail>>) os.readObject();

		}

		return mapArquivosEmail.size() > 0;
	}

	public List<MensagemEmail> getItensEmail(String folder){
		List<MensagemEmail> ls = new ArrayList<MensagemEmail>();
		if(!mapArquivosEmail.containsKey(folder)){
			loadEmails();
		}
		ls = mapArquivosEmail.get(folder);
		return ls;
	}
	
	private void saveListTemp(String name,List<MensagemEmail> ls){
		File arqList = new File(getClass().getResource("/Resources/FilesConfig")
								.getPath()+"/"+configEmail.getUser()+"@"+name+".temp");
		try{
			FileOutputStream ou = new FileOutputStream(arqList);
			ObjectOutputStream os = new ObjectOutputStream(ou);
			os.writeObject(ls);
			os.flush();
			os.close();
			
		}catch(Exception er){
			er.printStackTrace();
		}
	}
		
	private List<MensagemEmail> importListTemp(String name){
		File arqList = new File(getClass().getResource("/Resources/FilesConfig")
								.getPath()+"/"+configEmail.getUser()+"@"+name+".temp");
		List<MensagemEmail> lsEm = new ArrayList<MensagemEmail>();
		try{
			FileInputStream ou = new FileInputStream(arqList);
			ObjectInputStream os = new ObjectInputStream(ou);
			lsEm = (List<MensagemEmail>) os.readObject();
			
			
		}catch(Exception er){
			er.printStackTrace();
		}
		return lsEm;
	}
	
public synchronized List<String> getFolders() throws MessagingException {

		List<String> lsFolders = new ArrayList<String>();
		Folder[] folders = store.getDefaultFolder().list();
		for (Folder f : folders) {
			lsFolders.add(f.getName());
		}

		return lsFolders;
	
	}
	
	public synchronized void marcaComoLida(int index,String folderName){
		try {
			Folder folder;

			folder = store.getFolder(folderName);
			folder.open(Folder.READ_WRITE);
			Message[] messages = folder.getMessages();
			messages[index].setFlag(Flags.Flag.SEEN, true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public synchronized List<String> getListViewItensEmail(String folder) throws Exception{
		List<String> lsItens = new ArrayList<String>();
		if(!mapArquivosEmail.containsKey(folder)){
			Folder fold = store.getFolder(folder);
			fold.open(Folder.READ_ONLY);
			Message[] msgs = fold.getMessages();
			List<MensagemEmail> lsIt = new ArrayList<MensagemEmail>();
			int cont = msgs.length;
//			for(Message m : fold.getMessages()){
			for(int i = msgs.length-1; i >= 0; i--){
				Message m = msgs[i];
				System.out.println((--cont)+"/"+fold.getMessageCount());
				lsIt.add(lerEmails(m));
				saveListTemp(folder, lsIt);
			}
			mapArquivosEmail.put(folder, lsIt);
			atualizaArquivoTempEmail();
			importaArquivoEmail();
		}
		
		List<MensagemEmail>lsEm = mapArquivosEmail.get(folder);
		lsEm.forEach(em ->{
			

			System.out.println(em.getSubject());
			String from = em.getFrom();
			String assunto = em.getSubject();
			String dataRecebida = new SimpleDateFormat(
					"dd/MM/yyyy -  hh:mm").format(em.getDataRecebida());
			if (!em.isUnread()) {
				lsItens.add("<html><b>De: " + from + "  - Assunto: "
						+ assunto + " - " + dataRecebida
						+ "</b></html>");
			} else {
				lsItens.add("De: " + from + "  - Assunto: " + assunto
						+ " - " + dataRecebida);
			}
			
		});
			
		return lsItens;
		
	}
	
	public synchronized List<String> getListViewItensEmail(List<MensagemEmail> ls) throws Exception{
		List<String> lsItens = new ArrayList<String>();
		
			
		
		
		List<MensagemEmail>lsEm = ls;
		lsEm.forEach(em ->{
			

			System.out.println(em.getSubject());
			String from = em.getFrom();
			String assunto = em.getSubject();
			String dataRecebida = new SimpleDateFormat(
					"dd/MM/yyyy -  hh:mm").format(em.getDataRecebida());
			if (!em.isUnread()) {
				lsItens.add("<html><b>De: " + from + "  - Assunto: "
						+ assunto + " - " + dataRecebida
						+ "</b></html>");
			} else {
				lsItens.add("De: " + from + "  - Assunto: " + assunto
						+ " - " + dataRecebida);
			}
			
		});
			
		return lsItens;
		
	}
	
	
	private void atualizaArquivoTempEmail() throws IOException{
		File arqMap = new File(getClass().getResource("/Resources/FilesConfig")
				.getPath() + "/" + configEmail.getUser() + "@itensEmail.temp");
		
			FileOutputStream ou = new FileOutputStream(arqMap);
			ObjectOutputStream os = new ObjectOutputStream(ou);
			os.writeObject(mapArquivosEmail);
			os.flush();
			os.close();
			
	}
	
	private void atualizaArquivoEmail() throws IOException{
		File arqMap = new File(getClass().getResource("/Resources/FilesConfig")
				.getPath() + "/" + configEmail.getUser() + "@itensEmail.cr");
//		if (arqMap.exists()) {
			FileOutputStream ou = new FileOutputStream(arqMap);
			ObjectOutputStream os = new ObjectOutputStream(ou);
			os.writeObject(mapArquivosEmail);
			os.flush();
			os.close();
			

//		}else {
//			
//		}
	}
	
	
	public synchronized MensagemEmail lerEmails(Message msg) throws Exception {
		if (!store.isConnected()) {
			if(nTentativas > 10){ //Numero de tentativas se esgotou
				return null; //Então eu retorno null
			}
			store.connect(configEmail.getHost(), configEmail.getUser(), configEmail.getPass());
			nTentativas++;
			return lerEmails(msg); //SE NÃO, EU USO A RECURSIVIDADE
			
		} else {
			nTentativas = 0;
			MensagemEmail msEmail = new MensagemEmail();
			
			boolean isUnread = msg.getFlags().contains(Flags.Flag.SEEN);
	
			
			String from = InternetAddress.toString(msg.getFrom());
			if (from != null) {
				msEmail.setFrom(from);
			}

			String replyTo = InternetAddress.toString(msg.getReplyTo());
			if (replyTo != null) {
				msEmail.setReplyTo(replyTo);
			}
			
			msEmail.setUnread(isUnread);

			String to = InternetAddress.toString(msg
					.getRecipients(Message.RecipientType.TO));
			if (to != null) {
				msEmail.setTo(to);
			}

			String subject = msg.getSubject();
			if (subject != null) {
				msEmail.setSubject(subject);
			}
			Date sent = msg.getSentDate();
			if (sent != null) {
				msEmail.setDataRecebida(sent);
			}
			
			msEmail.setTexto(msg.getContent().toString());
//			lerBodyEmail(msg); //Leio o corpo de e-mail
			

			return msEmail;
		}

	}

	private String lerBodyEmail(Part p){
		String msg="";
		try{
		if (p.isMimeType("text/plain") || p.getContent() instanceof String) {
			String m = (String) p.getContent();
			// msEmail.setTexto(m);
		} else if (p.isMimeType("multipart/*")) {
			msg = renderMultiPart((Message)p);
		} else if (p.isMimeType("message/rfc822")) {
			msg = renderMultiPart((Message)p);
		} else {
			Object o = p.getContent();

			if (o instanceof InputStream) {
				InputStream is = (InputStream) o;
				int c;
				String m = "";
				while ((c = is.read()) != -1) {
					m += (char) c;
				}
				msg = m;
			} else {
				msg = "Não foi possivel identificar o tipo de formato!";
			}
		}
}catch(Exception e){
	e.printStackTrace();
}
			return msg;
	}

	private String renderMultiPart(Message msg) throws Exception {

		String body = "";
		Multipart multipart = (Multipart) msg.getContent();

		for (int x = 0; x < multipart.getCount(); x++) {
			BodyPart bodyPart = multipart.getBodyPart(x);

			String disposition = bodyPart.getDisposition();

			if (disposition != null
					&& (disposition.equals(BodyPart.ATTACHMENT))) {
				body += "Mail have some attachment : ";

				DataHandler handler = bodyPart.getDataHandler();
				body += "\n file name : " + handler.getName();
			} else {
				body += bodyPart.getContent() + "";
			}
		}
		return body;

	}

	private void autentica() throws MessagingException {
		String user = configEmail.getUser();
		String pass = configEmail.getPass();
		String host = configEmail.getHost();
		String hostRecieve = configEmail.getHostReceive();
		String port = String.valueOf(configEmail.getPort());
		boolean ssl = configEmail.isSsl();

		Properties prop = new Properties();
		// Definindo as configurações de conexão
		prop.setProperty("mail.smtp.host", host);
		prop.setProperty("mail.smtp.port", String.valueOf(port));
		prop.put("mail.smtp.port", String.valueOf(port));
		prop.setProperty("mail.smtp.user", user);
		prop.setProperty("mail.smtp.password", pass);
		
		if(ssl){
		prop.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		}
		prop.put("mail.smtp.auth", "true"); // e requer autenticação

		session = Session.getDefaultInstance(prop,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(user, pass);
					}
				});

		store = session.getStore("imaps");
		store.connect(hostRecieve, user, pass);
	}
	
	public synchronized List<String> getNovosEmails() throws Exception{
		try{
 			List<MensagemEmail> lsEm = new ArrayList<MensagemEmail>();
			Folder fold = store.getFolder("INBOX");
			System.out.println(fold.getName());
			
			fold.open(Folder.READ_ONLY);
			Message[] msgs = fold.search(new FlagTerm(new Flags(
					Flags.Flag.SEEN), false));
			
			FetchProfile fp = new FetchProfile();
			fp.add(FetchProfile.Item.ENVELOPE);
			fp.add(FetchProfile.Item.CONTENT_INFO);

			fold.fetch(msgs, fp);
			int cont =0;
			for(Message m : msgs){
				System.out.println("Thread > "+(++cont));
				lsEm.add(lerEmails(m));
			}
			
					
			mapArquivosEmail.put(fold.getName(), lsEm); //Subistituo ou insirom novo
			atualizaArquivoTempEmail(); //Atualizo o meu arquivo de itens de email...
			
			return getListViewItensEmail(fold.getName());
			
			
		}catch(MessagingException er){
			er.printStackTrace();
			throw new Exception(er);
		}
	}
	
	public synchronized int getCountNovosEmails(){
		
		int cont = 0;
		try{
			if(!store.isConnected()){
				if(nTentativas > 10){
					return 0;
				}
				store.connect(configEmail.getHost(), configEmail.getUser(), configEmail.getPass());
				nTentativas++;
				return getCountNovosEmails();
			}
			nTentativas = 0;
			Folder fold = store.getFolder("INBOX");
			System.out.println(fold.getName());
			
			fold.open(Folder.READ_ONLY);
			
			cont = fold.getMessageCount();
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return cont;
	}
	
	public boolean autentica(UsuarioEmail e)  {
		try{
		String user = e.getUser();
		String pass = e.getPass();
		String host = e.getHost();
		String hostRecieve = e.getHostReceive();
		String port = String.valueOf(e.getPort());
		boolean ssl = e.isSsl();

		Properties prop = new Properties();
		// Definindo as configurações de conexão
		prop.setProperty("mail.smtp.host", host);
		prop.setProperty("mail.smtp.port", String.valueOf(port));
		prop.put("mail.smtp.port", String.valueOf(port));
		prop.setProperty("mail.smtp.user", user);
		prop.setProperty("mail.smtp.password", pass);
		if(ssl){
			prop.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			}
		prop.put("mail.smtp.auth", "true"); // e requer autenticação

		session = Session.getDefaultInstance(prop,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(user, pass);
					}
				});

		store = session.getStore("imaps");
		store.connect(hostRecieve, user, pass);
		return true;
		}catch(MessagingException er){
			return false;
		}
	}

	public List<String> getTest(String nameFolder) {

		List<String>lsItens = new ArrayList<String>();
		try{
			Map<String,Message[]> ma = new HashMap<String, Message[]>();
		
		
		Message[] vtr = map.get(nameFolder);
		for(int i = 0; i < vtr.length; i++){
		
	
		Message m = vtr[i];
		boolean isUnread = true;
		
		
//		System.out.println(em.getSubject());
//		String from = em.getFrom().toString();
//		String assunto = em.getSubject();
//		String dataRecebida = new SimpleDateFormat(
//				"dd/MM/yyyy -  hh:mm").format(em.getDateReceive());
		
		String from = InternetAddress.toString(m.getFrom());
		

		String replyTo = InternetAddress.toString(m.getReplyTo());
		
		
		

		String to = InternetAddress.toString(m
				.getRecipients(Message.RecipientType.TO));
	

		String assunto = m.getSubject();
	
		Date dataRecebida = m.getSentDate();
		
		System.out.println(m.getContent().toString()	);
		
		if (!isUnread) {
			lsItens.add("<html><b>De: " + from + "  - Assunto: "
					+ assunto + " - " + dataRecebida
					+ "</b></html>");
		} else {
			lsItens.add("De: " + from + "  - Assunto: " + assunto
					+ " - " + dataRecebida);
		}
		}
//	});
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return lsItens;
	}
	


	
	
}

