package Controller;

import java.io.File;
import java.io.FileInputStream;
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
import java.util.concurrent.ExecutionException;

import javassist.bytecode.stackmap.BasicBlock.Catch;

import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage.RecipientType;

import org.w3c.dom.ls.LSInput;

import Model.MensagemEmail;
import Model.MessageSerial;
import Model.UsuarioEmail;

public class EmailControllerV3 {
	private Session session;
	private Store store;
	private UsuarioEmail configEmail;

	private Map<String, List<String>> mapItensViews;
	private Map<String, MensagemEmail[]> mapArquivoEmail;

	private String nameItensViews;
	private String nameArquivosMail;

	private File arqItensViews;
	private File arqFilesMail ;
	private int nTentativas;
	
	private List<String> lstItensEmails;
	
	private List<String>lstFolder;
	
	public EmailControllerV3(UsuarioEmail em) {
		try {
			lstItensEmails = new ArrayList<String>();
			nTentativas = 0;
			nameArquivosMail = em.getUser()+"@mapMail";
			nameItensViews = em.getUser()+"@itemMail";
			arqItensViews = new File(getClass().getResource(
					"/Resources/FilesConfig").getPath()
					+ "/" + nameItensViews + ".cr");
			arqFilesMail = new File(getClass().getResource(
					"/Resources/FilesConfig").getPath()
					+ "/" + nameArquivosMail + ".cr");
			
			this.configEmail = em;
			System.out.println(arqFilesMail);
			autentica(configEmail);
			preparaArquivos();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * Construtor para usar o metodo autentica
	 * @throws MessagingException
	 */
	public EmailControllerV3() {
			
	}
	
	private void preparaArquivos() throws MessagingException {
		try {
			
			List<String>lsFolders = getFolders();   //Pego todas pastas existentes
	
			
			if (arqItensViews.exists()) {
				FileInputStream in = new FileInputStream(arqItensViews);
				ObjectInputStream os = new ObjectInputStream(in);
				mapItensViews = (Map<String, List<String>>)os.readObject();
				os.close();
			}else {
				lsFolders.forEach(name ->{
					try {
					if(mapItensViews == null){
						mapItensViews = new HashMap<String,List<String>>();
					}
					List<String>lsItens = new ArrayList<String>();
					int indexInicial, indexFinal;
					Folder folder = store.getFolder(name);
					System.out.println("Prepara Arquivo > "+ folder.getName());
					if(folder.list().length == 0){
						folder.open(Folder.READ_ONLY);
						indexInicial= folder.getMessageCount() - 1;
						
						
						if (indexInicial - 50  < 0){
							indexFinal = 0;
						}else {
							indexFinal = indexInicial - 50;
						}
						lstItensEmails =  transformaForViewItem(folder.getMessages(), indexInicial, indexFinal);
						
						mapItensViews.put(name, lstItensEmails);
						saveArquivo(arqItensViews, mapItensViews);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
				
				
				
			}
			
			
		} catch (IOException | ClassNotFoundException|MessagingException e) {
			e.printStackTrace();
		}
	}
	
	public MensagemEmail pegaEmail(String folderName, int index){
		MensagemEmail em = new MensagemEmail();
		try{
		
				Folder folder = store.getFolder(folderName);
				folder.open(Folder.READ_ONLY);
				index = (folder.getMessageCount()-1) - index;
				System.out.println("INDEX> " + index + " / "+ folder.getMessageCount());
				Message msg = folder.getMessages()[index];
				em = lerEmails(msg);
		
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return em;
	}
	
	public synchronized MensagemEmail lerEmails(Message msg) throws Exception {
		MensagemEmail msEmail = new MensagemEmail();
		try{ 
			nTentativas = 0;
			
			
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
			
			String ms = "";
			Part p = (Part)msg;
			if(p.isMimeType("text/html") ){
				System.out.println("com text");
				ms = msg.getContentType();
			}else if (p.isMimeType("text/plain")) {
				System.out.println("Texto");
				ms =  p.getContent().toString();
				// msEmail.setTexto(m);
			}else if(p.getContent() instanceof String){
				ms = renderMultiPart(msg);
			}else if (p.isMimeType("multipart/*")) {
				ms = renderMultiPart((Message)p);
			} else if (p.isMimeType("message/rfc822")) {
				ms = renderMultiPart((Message)p);
			} else {
				Object o = p.getContent();

				if (o instanceof InputStream) {
					InputStream is = (InputStream) o;
					int c;
					String m = "";
					while ((c = is.read()) != -1) {
						m += (char) c;
					}
					ms = m;
				} else {
					ms = "Não foi possivel identificar o tipo de formato!";
				}
			System.out.println(ms);

//			lerBodyEmail(msg); //Leio o corpo de e-mail
			

			
			}
			msEmail.setTexto(ms);
		
			
		}
		catch(Exception e){
			if (e instanceof MessagingException){
				if(nTentativas<10){
					store.connect(configEmail.getHostReceive(), configEmail.getUser(), configEmail.getPass());
				}
				++nTentativas;
				return lerEmails(msg);
			}
		}
		return msEmail;
		
	}

	public synchronized boolean hasNewEmail(){
		try{
//			Folder f = store.getFolder("INBOX");
			Folder f = store.getFolder("INBOX");
			System.out.println(f.getName());
			f.open(Folder.READ_ONLY);
			int contAtual = lstItensEmails.size();
			int contCaixa = f.getMessageCount();
			
			return contAtual < contCaixa;
			
		}catch(Exception e){
			e.printStackTrace(); //TODO handle excpetion
		}
		
		return false;
	}
	
	public List<String> getNewEmails(){
		try{
			Folder folder = store.getFolder("INBOX");
			folder.open(Folder.READ_ONLY);
			Message[] vtrMsg = folder.getMessages();
			
			for(int i = lstFolder.size()-1; i < vtrMsg.length; i++){
				Message em = vtrMsg[i];
				
				boolean isUnread = em.getFlags().contains(Flags.Flag.SEEN);
				
				String from = InternetAddress.toString(em.getFrom());
				String assunto = em.getSubject();
				String dataRecebida = new SimpleDateFormat(
						"dd/MM/yyyy -  hh:mm").format(em.getReceivedDate());
				if (!isUnread) {
					lstItensEmails.add("<html><b>De: " + from + "  - Assunto: "
							+ assunto + " - " + dataRecebida
							+ "</b></html>");
				} else {
					lstItensEmails.add("De: " + from + "  - Assunto: " + assunto
							+ " - " + dataRecebida);
				}
			}
		}catch(Exception e){
			e.printStackTrace(); //HANDLE EXCEPTION
		}
		return lstItensEmails;
	}
	
	private String renderMultiPart(Message msg) throws Exception {

				String body = "";
				Multipart multipart = (Multipart) msg.getContent();

				for (int x = 1; x < multipart.getCount(); x++) {
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
	
	private List<MessageSerial> tranformaToSerial(Message[] vtrMsg) {
		List<MessageSerial> ls = new ArrayList<MessageSerial>();
//		MessageSerial [] vtr = new MessageSerial[vtrMsg.length];
		long in,f,res;
		
		System.out.println(ls.size());
		System.out.println(vtrMsg);
		System.out.println(vtrMsg.length);
		
		for(int i = 0; i < vtrMsg.length; i ++){
			
			in = System.currentTimeMillis();
			
			Message m = vtrMsg[i];
			try {
				MessageSerial vtr= new MessageSerial();
				vtr.setContent(vtrMsg[i].getContent());
				vtr.setFrom(vtrMsg[i].getFrom().toString());
				vtr.setGetDateReceive(vtrMsg[i].getReceivedDate());
				vtr.setTo(vtrMsg[i].getRecipients(RecipientType.TO).toString());
//				vtr.setReplyTo(m.getReplyTo().toString());
				vtr.setSubject(vtrMsg[i].getSubject());
				boolean isUn = vtrMsg[i].getFlags().contains(Flags.Flag.SEEN);
				vtr.setIsUnread(!isUn);
				
			} catch (IOException | MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			f = System.currentTimeMillis();
			
			res = f - in;
			System.out.println(new SimpleDateFormat("dd/MM/yyyy - hh:mm").format(new Date(res)));
			
		}
		return ls;
	}

	private void saveArquivo(File arg,Object obj){
		try{
			 FileOutputStream ou = new FileOutputStream(arg);
			 ObjectOutputStream os = new ObjectOutputStream(ou);
			 os.writeObject(obj);
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	
	private List<String> getItens(String name){
		if(mapItensViews.containsKey(name))
			return mapItensViews.get(name);
		
		return new ArrayList<String>();
		
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
	
	private List<String> transformaForViewItem(Message[] vtrM, int inicio,int limite) throws MessagingException, IOException{
		List<String>lsItens = new ArrayList<String>();
		for(int i = inicio; i>= limite; i--){
			
		Message em = vtrM[i];
		
		boolean isUnread = em.getFlags().contains(Flags.Flag.SEEN);
		
		String from = InternetAddress.toString(em.getFrom());
		String assunto = em.getSubject();
		String dataRecebida = new SimpleDateFormat(
				"dd/MM/yyyy -  hh:mm").format(em.getReceivedDate());
		if (!isUnread) {
			lsItens.add("<html><b>De: " + from + "  - Assunto: "
					+ assunto + " - " + dataRecebida
					+ "</b></html>");
		} else {
			lsItens.add("De: " + from + "  - Assunto: " + assunto
					+ " - " + dataRecebida);
		}
		}
			return lsItens;
		}
//	});
		
	
	public List<String> pegaItens(String folder){
		List<String> itens = new ArrayList<String>();
		try{
			if(mapItensViews.containsKey(folder)){
				itens = mapItensViews.get(folder);
			}
		}catch (Exception e){
			
		}
		return itens;
	}


	
	
	
	public synchronized List<String> getFolders() throws MessagingException {
		if(lstFolder != null){
			return lstFolder;
		}
		List<String> lsFolders = new ArrayList<String>();
		Folder[] folders = store.getDefaultFolder().list();
		for (Folder f : folders) {
			System.out.println(f.getName());
			if(f.list().length == 0){ //Somente se não houver sub pastas
				lsFolders.add(f.getName());
			}
		}
		lstFolder = lsFolders;

		return lsFolders;
	
	}

	public boolean autentica(UsuarioEmail e) throws MessagingException {
		
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

		if (ssl) {
			prop.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
		}
		prop.put("mail.smtp.auth", "true"); // e requer autenticação
try{
		session = Session.getDefaultInstance(prop,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(user, pass);
					}
				});

		store = session.getStore("imaps");
		store.connect(hostRecieve, user, pass);
		return true;
	}catch(Exception er){
		er.printStackTrace(); //TODO POSTERGAR EXCESSÃO
		return false;
	}
		
	}
}
