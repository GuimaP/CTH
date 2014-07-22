package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage.RecipientType;

import Model.MessageSerial;
import Model.UsuarioEmail;

public class EmailControllerV3 {
	private Session session;
	private Store store;
	private UsuarioEmail configEmail;

	private Map<String, List<String>> mapItensViews;
	private Map<String, MessageSerial[]> mapArquivoEmail;

	private String nameItensViews;
	private String nameArquivosMail;

	private File arqItensViews;
	private File arqFilesMail ;
	
	public EmailControllerV3(UsuarioEmail em) {
		try {
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
			autentica();
			preparaArquivos();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	private void preparaArquivos() throws MessagingException {
		try {
			
			List<String>lsFolders = getFolders();   //Pego todas pastas existentes

			if (arqFilesMail.exists()) { //Ve se o arquivo existe
				FileInputStream in = new FileInputStream(arqFilesMail); // se sim
				ObjectInputStream os = new ObjectInputStream(in);
				mapArquivoEmail = (Map<String,MessageSerial[]>)os.readObject();//Passo para a minha variavel local 
				os.close();
			}else {//Se não
				
				for(int i = 0; i < lsFolders.size(); i++){ //e popula de novo 
					String name = lsFolders.get(i);  
					Folder fd = store.getFolder(name);
						if(fd.list().length == 0){
							fd.open(Folder.READ_ONLY);
							Message[] vtrMsg = fd.getMessages();
							if(this.mapArquivoEmail== null){ //Se n existir, crie um novo
								this.mapArquivoEmail = new HashMap<String,MessageSerial[]>();
							}
							MessageSerial[] vtr = tranformaToSerial(vtrMsg);
							this.mapArquivoEmail.put(name, vtr);
							
						}
				}
				
				saveArquivo(arqFilesMail,mapArquivoEmail);
			}
			
			
			if (arqItensViews.exists()) {
				FileInputStream in = new FileInputStream(arqItensViews);
				ObjectInputStream os = new ObjectInputStream(in);
				mapItensViews = (Map<String, List<String>>)os.readObject();
				os.close();
			}else {
				lsFolders.forEach(name ->{
					
					if(mapItensViews == null){
						mapItensViews = new HashMap<String,List<String>>();
					}
					
					MessageSerial[] msgs = this.mapArquivoEmail.get(name);
					List<String>lsItens = new ArrayList<String>();
					for(MessageSerial m : msgs ){
					try {
						lsItens.add(this.transformaForViewItem(m));
					} catch (Exception e) {
						e.printStackTrace();
					}
						
					}
					mapItensViews.put(name, lsItens);
					
				});
				
				saveArquivo(arqItensViews,mapItensViews);
				
			}
			
			
		} catch (IOException | ClassNotFoundException|MessagingException e) {
			e.printStackTrace();
		}
	}
	
	private MessageSerial[] tranformaToSerial(Message[] vtrMsg) {
		MessageSerial [] vtr = new MessageSerial[vtrMsg.length];
		long in,f,res;
		
		System.out.println(vtr);
		System.out.println(vtrMsg);
		System.out.println(vtrMsg.length);
		
		for(int i = 0; i < vtr.length; i ++){
			
			in = System.currentTimeMillis();
			
			Message m = vtrMsg[i];
			try {
				vtr[i].setContent(m.getContent());
				vtr[i].setFrom(m.getFrom().toString());
				vtr[i].setGetDateReceive(m.getReceivedDate());
				vtr[i].setTo(m.getRecipients(RecipientType.TO).toString());
//				vtr[i].setReplyTo(m.getReplyTo().toString());
				vtr[i].setSubject(m.getSubject());
				boolean isUn = m.getFlags().contains(Flags.Flag.SEEN);
				vtr[i].setIsUnread(!isUn);
				
			} catch (IOException | MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			f = System.currentTimeMillis();
			
			res = f - in;
			System.out.println(new SimpleDateFormat("dd/MM/yyyy - hh:mm").format(new Date(res)));
			
		}
		return vtr;
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
	
	private String transformaForViewItem(MessageSerial m) throws MessagingException, IOException{
		
		boolean isUnread = m.getIsUnread();
		
		String from = m.getFrom();
		

		String replyTo = m.getReplyTo();
		
		
		

		String to = m.getTo();
	

		String assunto = m.getSubject();
	
		Date dataRecebida = m.getGetDateReceive();
		
		System.out.println(m.getContent().toString()	);
		
		if (!isUnread) {
			 return "<html><b>De: " + from + "  - Assunto: "
					+ assunto + " - " + dataRecebida
					+ "</b></html>";
		} else {
			return "De: " + from + "  - Assunto: " + assunto
					+ " - " + dataRecebida;
		}
		}
//	});
		
	


	
	public synchronized List<String> getFolders() throws MessagingException {

		List<String> lsFolders = new ArrayList<String>();
		Folder[] folders = store.getDefaultFolder().list();
		for (Folder f : folders) {
			lsFolders.add(f.getName());
		}

		return lsFolders;
	
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

		if (ssl) {
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

}
