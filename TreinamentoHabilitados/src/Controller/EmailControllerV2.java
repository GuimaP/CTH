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

	private Map<String, List<MensagemEmail>> mapArquivosEmail;

	public EmailControllerV2(UsuarioEmail em) {
		try {
			this.configEmail = em;
			mapArquivosEmail = new HashMap<String,List<MensagemEmail>>();
			autentica();
			if (!importaArquivoEmail()) {
				loadEmails();
			}
		} catch (MessagingException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}

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
			nTentativas = 0;
			
			List<String> ls = getFolders();
			List<MensagemEmail> lsEmails = new ArrayList<MensagemEmail>(); /*
																			 * list
																			 * que
																			 * vai
																			 * conter
																			 * todos
																			 * os
																			 * objetos
																			 * de
																			 * e
																			 * -
																			 * mail
																			 */
			File arq;
			int cont = 0;
			for (String fn : ls) {
				arq = new File(getClass().getResource("/Resources/FilesConfig").getPath()+"/"
								+configEmail.getUser()+"@"+fn+".temp");
				System.out.println(arq);
				
				Folder f = store.getFolder(fn);
				f.open(Folder.READ_ONLY);
				System.out.println("PASTA>" + fn);
				Message[] msgs = f.getMessages();
				
				int index = msgs.length	;
				int c =0;
				if(arq.exists()){
					System.out.println("o arquivo existe");
					FileInputStream in = new FileInputStream(arq);
					ObjectInputStream os = new ObjectInputStream(in);
					List<MensagemEmail>lsEm = (List<MensagemEmail>)os.readObject();
					index = lsEm.size();
					index -= lsEm.size();
					c = index;
					lsEmails = lsEm;
				}
				
//				for(int i = index; i < msgs.length; i ++){
				for(int i = msgs.length-1; i >= 0; i--){
					Message m = msgs[i];
					System.out.println((++c)+" > Load");
					lsEmails.add(lerEmails(msgs[i]));
					saveListTemp(fn, lsEmails);
				}
				mapArquivosEmail.put(fn, lsEmails);
				atualizaArquivoEmail();

			}
		}
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
	}

	public void loadEmails(int index,String folderName,List<MensagemEmail> em){
		try {
			
			if(!store.isConnected()){
				if(nTentativas >10){
					return;
				}
				store.connect(configEmail.getHost(), configEmail.getUser(), configEmail.getPass());
				nTentativas++;
				loadEmails();
			}else {
			nTentativas = 0;
			
			
			List<MensagemEmail> lsEmails = new ArrayList<MensagemEmail>(); /*
																			 * list
																			 * que
																			 * vai
																			 * conter
																			 * todos
																			 * os
																			 * objetos
																			 * de
																			 * e
																			 * -
																			 * mail
																			 */
			int cont = em.size();
			index -= lsEmails.size();
				lsEmails = em;
				Folder f = store.getFolder(folderName);
				f.open(Folder.READ_ONLY);
				System.out.println("PASTA>" + folderName);

				Message[] msgs = f.getMessages();
				int c =0;
				for(int i = index; i >= 0; i--){
					System.out.println((++c)+" > Load");
					lsEmails.add(lerEmails(msgs[i]));
					saveListTemp(folderName, lsEmails);
				}
				mapArquivosEmail.put(folderName, lsEmails);
				atualizaArquivoEmail();

			}
		
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	public List<MensagemEmail> loadEmails(String folderName){
		List<MensagemEmail> lsEmails = new ArrayList<MensagemEmail>(); /*
		 * list
		 * que
		 * vai
		 * conter
		 * todos
		 * os
		 * objetos
		 * de
		 * e
		 * -
		 * mail
		 */
		try {
			
			if(!store.isConnected()){
				if(nTentativas >10){
					return new ArrayList<>();
				}
				store.connect(configEmail.getHost(), configEmail.getUser(), configEmail.getPass());
				nTentativas++;
				loadEmails();
			}else {
			nTentativas = 0;
			
			
			
			int cont = 0;
		
				Folder f = store.getFolder(folderName);
				f.open(Folder.READ_ONLY);
				System.out.println("PASTA>" + folderName);

				Message[] msgs = f.getMessages();
				int c =0;
//				for (Message m : msgs) {
				for(int i = msgs.length; i >= 0; i--){
					Message m = msgs[i];
					System.out.println((++c)+" > Load");
					lsEmails.add(lerEmails(m));
					saveListTemp(folderName, lsEmails);
				}
				mapArquivosEmail.put(folderName, lsEmails);
				atualizaArquivoEmail();

			}
		
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		return lsEmails;
	}
	
	
	public Map<String, List<MensagemEmail>> getMapArquivosEmail() {
		return mapArquivosEmail;
	}

	private boolean importaArquivoEmail() throws IOException,
			ClassNotFoundException {
		File arqMap = new File(getClass().getResource("/Resources/FilesConfig")
				.getPath() + "/" + configEmail.getUser() + "@itensEmail.ser");
		if (arqMap.exists()) {
			FileInputStream in = new FileInputStream(arqMap);
			ObjectInputStream os = new ObjectInputStream(in);
			mapArquivosEmail = (Map<String, List<MensagemEmail>>) os.readObject();

		}

		return mapArquivosEmail != null;
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
			atualizaArquivoEmail();
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
	
	
	private void atualizaArquivoEmail() throws IOException{
		File arqMap = new File(getClass().getResource("/Resources/FilesConfig")
				.getPath() + "/" + configEmail.getUser() + "@itensEmail.cr");
		if (arqMap.exists()) {
			FileOutputStream ou = new FileOutputStream(arqMap);
			ObjectOutputStream os = new ObjectOutputStream(ou);
			os.writeObject(mapArquivosEmail);
			os.flush();
			os.close();
			

		}
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
			
//			lerBodyEmail(msg);
			

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
			atualizaArquivoEmail(); //Atualizo o meu arquivo de itens de email...
			
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

	
}

