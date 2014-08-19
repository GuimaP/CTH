package testes;

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

import com.itextpdf.awt.geom.misc.Messages;


public class EmailControllerV2 {
//	private Session session;
//	private Store store;
//
//	private UsuarioEmail configEmail;
//	private int nTentativas;
//
//	private Map<String, List<MensagemEmail>> mapArquivosEmail;
//
//	public EmailControllerV2(UsuarioEmail em) {
//		try {
//			this.configEmail = em;
//			mapArquivosEmail = null;
//			autentica();
//			if (!importaArquivoEmail()) {
//				loadEmails();
//			}
//		} catch (MessagingException | ClassNotFoundException | IOException e) {
//			e.printStackTrace();
//		}
//
//	}
//	
//	public EmailControllerV2() {
//		
//	}
//	
//	public String getUser(){
//		return configEmail.getUser();
//	}
//
//	public void loadEmails() {
//		try {
//			List<String> ls = getFolders();
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
//			int cont = 0;
//			for (String fn : ls) {
//
//				Folder f = store.getFolder(fn);
//				f.open(Folder.READ_ONLY);
//				System.out.println("PASTA>" + fn);
//
//				Message[] msgs = f.getMessages();
//
//				for (Message m : msgs) {
//
//					lsEmails.add(lerEmails(m));
//					saveListTemp(fn, lsEmails);
//				}
//				mapArquivosEmail.put(fn, lsEmails);
//				atualizaArquivoEmail();
//
//			}
//		} catch (MessagingException e) {
//			e.printStackTrace();
//		} catch (Exception e2) {
//			e2.printStackTrace();
//		}
//	}
//
//	public void loadEmails(int index,String folderName){
//		
//	}
//	
//	private boolean importaArquivoEmail() throws IOException,
//			ClassNotFoundException {
//		File arqMap = new File(getClass().getResource("/Resources/FilesConfig")
//				.getPath() + "/" + configEmail.getUser() + "@itensEmail.ser");
//		if (arqMap.exists()) {
//			FileInputStream in = new FileInputStream(arqMap);
//			ObjectInputStream os = new ObjectInputStream(in);
//			mapArquivosEmail = (Map<String, List<MensagemEmail>>) os.readObject();
//
//		}
//
//		return mapArquivosEmail != null;
//	}
//
//	private void saveListTemp(String name,List<MensagemEmail> ls){
//		File arqList = new File(getClass().getResource("/Resources/FilesConfig")
//								.getPath()+"/"+configEmail.getUser()+"@"+name+".temp");
//		try{
//			FileOutputStream ou = new FileOutputStream(arqList);
//			ObjectOutputStream os = new ObjectOutputStream(ou);
//			os.writeObject(ls);
//			os.flush();
//			os.close();
//			
//		}catch(Exception er){
//			er.printStackTrace();
//		}
//	}
//		
//	private List<MensagemEmail> importListTemp(String name){
//		File arqList = new File(getClass().getResource("/Resources/FilesConfig")
//								.getPath()+"/"+configEmail.getUser()+"@"+name+".temp");
//		List<MensagemEmail> lsEm = new ArrayList<MensagemEmail>();
//		try{
//			FileInputStream ou = new FileInputStream(arqList);
//			ObjectInputStream os = new ObjectInputStream(ou);
//			lsEm = (List<MensagemEmail>) os.readObject();
//			
//			
//		}catch(Exception er){
//			er.printStackTrace();
//		}
//		return lsEm;
//	}
//	
//	public synchronized List<String> getFolders() throws MessagingException {
//
//		List<String> lsFolders = new ArrayList<String>();
//		Folder[] folders = store.getDefaultFolder().list();
//		for (Folder f : folders) {
//			lsFolders.add(f.getName());
//		}
//
//		return lsFolders;
//
//	}
//	
//	public synchronized void marcaComoLida(int index,String folderName){
//		try {
//			Folder folder;
//
//			folder = store.getFolder(folderName);
//			folder.open(Folder.READ_WRITE);
//			Message[] messages = folder.getMessages();
//			messages[index].setFlag(Flags.Flag.SEEN, true);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public synchronized List<String> getListViewItensEmail(String folder) throws Exception{
//		List<String> lsItens = new ArrayList<String>();
//		if(!mapArquivosEmail.containsKey(folder)){
//			Folder fold = store.getFolder(folder);
//			fold.open(Folder.READ_ONLY);
//			List<MensagemEmail> lsIt = new ArrayList<MensagemEmail>();
//			for(Message m : fold.getMessages()){
//				lsIt.add(lerEmails(m));
//			}
//			mapArquivosEmail.put(folder, lsIt);
//			atualizaArquivoEmail();
//			importaArquivoEmail();
//		}
//		
//		List<MensagemEmail>lsEm = mapArquivosEmail.get(folder);
//		lsEm.forEach(em ->{
//			
//
//			
//			String from = em.getFrom();
//			String assunto = em.getSubject();
//			String dataRecebida = new SimpleDateFormat(
//					"dd/MM/yyyy -  hh:mm").format(em.getDataRecebida());
//			if (!em.isUnread()) {
//				lsItens.add("<html><b>De: " + from + "  - Assunto: "
//						+ assunto + " - " + dataRecebida
//						+ "</b></html>");
//			} else {
//				lsItens.add("De: " + from + "  - Assunto: " + assunto
//						+ " - " + dataRecebida);
//			}
//			
//		});
//			
//		return lsItens;
//		
//	}
//	
//	
//	private void atualizaArquivoEmail() throws IOException{
//		File arqMap = new File(getClass().getResource("/Resources/FilesConfig")
//				.getPath() + "/" + configEmail.getUser() + "@itensEmail.cr");
//		if (arqMap.exists()) {
//			FileOutputStream ou = new FileOutputStream(arqMap);
//			ObjectOutputStream os = new ObjectOutputStream(ou);
//			os.writeObject(mapArquivosEmail);
//			os.flush();
//			os.close();
//			
//
//		}
//	}
//	
//	public synchronized MensagemEmail lerEmails(Message msg) throws Exception {
//		if (store.isConnected()) {
//			if(nTentativas > 4){ //Numero de tentativas se esgotou
//				return null; //Ent�o eu retorno null
//			}
//			nTentativas++;
//			return lerEmails(msg); //SE N�O, EU USO A RECURSIVIDADE
//			
//		} else {
//			nTentativas = 0;
//			MensagemEmail msEmail = new MensagemEmail();
//			
//			boolean isUnread = msg.getFlags().contains(Flags.Flag.SEEN);
//			
//			String from = InternetAddress.toString(msg.getFrom());
//			if (from != null) {
//				msEmail.setFrom(from);
//			}
//
//			String replyTo = InternetAddress.toString(msg.getReplyTo());
//			if (replyTo != null) {
//				msEmail.setReplyTo(replyTo);
//			}
//			
//			msEmail.setUnread(isUnread);
//
//			String to = InternetAddress.toString(msg
//					.getRecipients(Message.RecipientType.TO));
//			if (to != null) {
//				msEmail.setTo(to);
//			}
//
//			String subject = msg.getSubject();
//			if (subject != null) {
//				msEmail.setSubject(subject);
//			}
//			Date sent = msg.getSentDate();
//			if (sent != null) {
//				msEmail.setDataRecebida(sent);
//			}
//			Part p = msg;
//
//			if (p.isMimeType("text/plain") || p.getContent() instanceof String) {
//				String m = (String) p.getContent();
//				// msEmail.setTexto(m);
//			} else if (p.isMimeType("multipart/*")) {
//				msEmail.setTexto(renderMultiPart(msg));
//			} else if (p.isMimeType("message/rfc822")) {
//				msEmail.setTexto(renderMultiPart(msg));
//			} else {
//				Object o = p.getContent();
//
//				if (o instanceof InputStream) {
//					InputStream is = (InputStream) o;
//					int c;
//					String m = "";
//					while ((c = is.read()) != -1) {
//						m += (char) c;
//					}
//					msEmail.setTexto(m);
//				} else {
//					msEmail.setTexto("N�o foi possivel identificar o tipo de formato!");
//				}
//			}
//			
//
//			return msEmail;
//		}
//
//	}
//
//	private String renderMultiPart(Message msg) throws Exception {
//
//		String body = "";
//		Multipart multipart = (Multipart) msg.getContent();
//
//		for (int x = 0; x < multipart.getCount(); x++) {
//			BodyPart bodyPart = multipart.getBodyPart(x);
//
//			String disposition = bodyPart.getDisposition();
//
//			if (disposition != null
//					&& (disposition.equals(BodyPart.ATTACHMENT))) {
//				body += "Mail have some attachment : ";
//
//				DataHandler handler = bodyPart.getDataHandler();
//				body += "\n file name : " + handler.getName();
//			} else {
//				body += bodyPart.getContent() + "";
//			}
//		}
//		return body;
//
//	}
//
//	private void autentica() throws MessagingException {
//		String user = configEmail.getUser();
//		String pass = configEmail.getPass();
//		String host = configEmail.getHost();
//		String hostRecieve = configEmail.getHostReceive();
//		String port = String.valueOf(configEmail.getPort());
//		boolean ssl = configEmail.isSsl();
//
//		Properties prop = new Properties();
//		// Definindo as configura��es de conex�o
//		prop.setProperty("mail.smtp.host", host);
//		prop.setProperty("mail.smtp.port", String.valueOf(port));
//		prop.put("mail.smtp.port", String.valueOf(port));
//		prop.setProperty("mail.smtp.user", user);
//		prop.setProperty("mail.smtp.password", pass);
//		
//		if(ssl){
//		prop.put("mail.smtp.socketFactory.class",
//				"javax.net.ssl.SSLSocketFactory");
//		}
//		prop.put("mail.smtp.auth", "true"); // e requer autentica��o
//
//		session = Session.getDefaultInstance(prop,
//				new javax.mail.Authenticator() {
//					protected PasswordAuthentication getPasswordAuthentication() {
//						return new PasswordAuthentication(user, pass);
//					}
//				});
//
//		store = session.getStore("imaps");
//		store.connect(hostRecieve, user, pass);
//	}
//	
//	public synchronized List<String> getNovosEmails() throws Exception{
//		try{
// 			List<MensagemEmail> lsEm = new ArrayList<MensagemEmail>();
//			Folder fold = store.getDefaultFolder();
//			System.out.println(fold.getName());
//			
//			fold.open(Folder.READ_ONLY);
//			Message[] msgs = fold.search(new FlagTerm(new Flags(
//					Flags.Flag.SEEN), false));
//			
//			FetchProfile fp = new FetchProfile();
//			fp.add(FetchProfile.Item.ENVELOPE);
//			fp.add(FetchProfile.Item.CONTENT_INFO);
//
//			fold.fetch(msgs, fp);
//			
//			for(Message m : msgs){
//				lsEm.add(lerEmails(m));
//			}
//			
//					
//			mapArquivosEmail.put(fold.getName(), lsEm); //Subistituo ou insirom novo
//			atualizaArquivoEmail(); //Atualizo o meu arquivo de itens de email...
//			
//			return getListViewItensEmail(fold.getName());
//			
//			
//		}catch(MessagingException er){
//			er.printStackTrace();
//			throw new Exception(er);
//		}
//	}
//	
//	public boolean autentica(UsuarioEmail e)  {
//		try{
//		String user = e.getUser();
//		String pass = e.getPass();
//		String host = e.getHost();
//		String hostRecieve = e.getHostReceive();
//		String port = String.valueOf(e.getPort());
//		boolean ssl = e.isSsl();
//
//		Properties prop = new Properties();
//		// Definindo as configura��es de conex�o
//		prop.setProperty("mail.smtp.host", host);
//		prop.setProperty("mail.smtp.port", String.valueOf(port));
//		prop.put("mail.smtp.port", String.valueOf(port));
//		prop.setProperty("mail.smtp.user", user);
//		prop.setProperty("mail.smtp.password", pass);
//		if(ssl){
//			prop.put("mail.smtp.socketFactory.class",
//					"javax.net.ssl.SSLSocketFactory");
//			}
//		prop.put("mail.smtp.auth", "true"); // e requer autentica��o
//
//		session = Session.getDefaultInstance(prop,
//				new javax.mail.Authenticator() {
//					protected PasswordAuthentication getPasswordAuthentication() {
//						return new PasswordAuthentication(user, pass);
//					}
//				});
//
//		store = session.getStore("imaps");
//		store.connect(hostRecieve, user, pass);
//		return true;
//		}catch(MessagingException er){
//			return false;
//		}
//	}
//
//	
}
