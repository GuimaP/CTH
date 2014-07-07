package Controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.FetchProfile;
import javax.mail.Flags;
import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.URLName;
import javax.mail.event.MessageChangedListener;
import javax.mail.event.MessageCountEvent;
import javax.mail.event.MessageCountListener;
import javax.mail.event.StoreEvent;
import javax.mail.event.StoreListener;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.Message;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.w3c.dom.ls.LSSerializer;

import Model.GroupEmails;
import Model.MensagemEmail;
import antlr.debug.MessageEvent;

import com.itextpdf.awt.geom.misc.Messages;
import com.itextpdf.text.pdf.FdfReader;
import com.sun.faces.facelets.tag.jstl.core.ForEachHandler;
import com.sun.mail.pop3.POP3SSLStore;

public class EmailController {
	private String host;
	private String hostRecieve;
	private String user;
	private String pass;
	private int port;
	private boolean isSsl;
	private Store store;
	private Folder folderInbox;
	private Session session;

	public EmailController(String host, String hostRecieve, int port,
			boolean ssl) {
		this.host = host;
		this.port = port;
		this.isSsl = ssl;
		this.hostRecieve = hostRecieve;
	

		authenticOnEmail();

		// new Thread(() -> {
		// try {
		// int cont = 0;
		// int oldValue = 0;
		// while(true){
		// Properties prop = new Properties();
		// prop.setProperty("mail.smtp.host", host);
		// prop.setProperty("mail.smtp.port", String.valueOf(port));
		// prop.put("mail.smtp.port", String.valueOf(port));
		// prop.setProperty("mail.smtp.user", user);
		// prop.setProperty("mail.smtp.password", pass);
		// prop.put("mail.smtp.socketFactory.class",
		// "javax.net.ssl.SSLSocketFactory");
		// prop.put("mail.smtp.auth", "true");
		// Session emailSession = Session.getDefaultInstance(prop);
		//
		//
		// Store store2 = emailSession.getStore("imaps");
		// store2.connect("imap.gmail.com",user,pass);
		// Folder emailFolder = store2.getFolder("INBOX");
		//
		// emailFolder.open(Folder.READ_ONLY);
		//
		// int temp = emailFolder.getUnreadMessageCount();
		// System.out.println(oldValue + " - " + temp + "    NOVAS MENSAGENS "+
		// emailFolder.getNewMessageCount());
		// if(temp != oldValue){
		// readEmail(folderInbox.getMessages()[temp-1]);
		// }
		// oldValue = temp;
		// Thread.sleep(120*1000);
		//
		// }
		// } catch (Exception e) {
		//
		// e.printStackTrace();
		// }
		// }).start();
		//
		// PEGAR DE UM ARQUIVO CRIPTOGRAFADO E SERIALIZADO.
	}

	public EmailController() {
		

	}

	public List<Folder> listarFolders() {
		List<Folder> lsFolders = new ArrayList<Folder>();
		try {
			Store store = session.getStore("imaps");
			store.connect(this.hostRecieve, this.user, this.pass);
			Folder[] vtrFolder = store.getDefaultFolder().list();
			for (Folder f : vtrFolder) {
				lsFolders.add(f);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lsFolders;
	}

	public List<Folder> listarFolders(Folder paramFolder) {
		List<Folder> lsFolders = new ArrayList<Folder>();
		try {
			Store store = session.getStore("imaps");
			store.connect(this.hostRecieve, this.user, this.pass);
			Folder[] vtrFolder = paramFolder.list();
			for (Folder f : vtrFolder) {
				lsFolders.add(f);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lsFolders;
	}

	/**
	 * 
	 * @param Folder
	 * @return Retorna uma visão de itens do E-mail
	 */
	public List<String> listarViewEmails(Folder f) {
		List<String> ls = new ArrayList<String>();
		try {
			Message[] msgs = f.getMessages();
			int total = msgs.length - 1;
			for (int i = total; i > 0; i--) {
				String from = msgs[i].getFrom().toString();
				String assunto = msgs[i].getSubject();
				String dataRecebida = new SimpleDateFormat("dd/MM/yyyy -  hh:mm").format(msgs[i].getReceivedDate());
				ls.add("De: " + from + "  - " + dataRecebida + "\n" + assunto);
			}
			f.close(true);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return ls;
	}

	/**
	 * 
	 * @param msg
	 * @return Retorna um objeto formatado com os dados do e-mail
	 */
	public MensagemEmail readEmail(Message msg) {

		MensagemEmail msgEmail = new MensagemEmail();
		try {

			String to = "-- \nTO: ";
			String from = "FROM: ";
			String recipients = "COM COPIA PARA: ";
			String subject = "SUBJECT: ";
			String body = "";

			subject = msg.getSubject();
			msgEmail.setSubject(subject);

			Address[] endr = null;

			if ((endr = msg.getFrom()) != null) {
				List<Address> lsFrom = new ArrayList<Address>();
				for (int j = 0; j < endr.length; j++) {
					// from+= ", "+endr[j].toString();
					lsFrom.add(endr[j]);
				}
				msgEmail.setFrom(lsFrom);
			}

			if ((endr = msg.getRecipients(RecipientType.TO)) != null) {
				List<Address> lsDestinatario = new ArrayList<Address>();
				for (int j = 0; j < endr.length; j++) {
					recipients += ", " + endr.toString();
					lsDestinatario.add(endr[j]);
					// InternetAddress ia = (InternetAddress)endr[j];
					// if (ia.isGroup()) {
					// InternetAddress[] aa = ia.getGroup(false);
					// for (int k = 0; k < aa.length; k++)
					// System.out.println(("  GROUP: " + aa[k].toString()));
					// }
				}
				msgEmail.setTo(lsDestinatario);
			}

			// System.out.println("SUBJECT: " + subject );
			//
			// System.out.println("DATA RECEBIDA " +msg.getReceivedDate());
			//
			// System.out.println(abrirMensagem(msg));

			Flags flags = msg.getFlags();
			Flags.Flag[] sf = flags.getSystemFlags(); // get the system flags

			for (int i = 0; i < sf.length; i++) {
				String s = ".";
				boolean unread = true;
				Flags.Flag f = sf[i];
				if (f == Flags.Flag.ANSWERED) {
					s = "\\Answered";
					unread = false;
				} else if (f == Flags.Flag.DELETED) {
					s = "\\Deleted";
					unread = false;
				} else if (f == Flags.Flag.DRAFT) {
					s = "\\Draft";
					unread = false;
				} else if (f == Flags.Flag.FLAGGED) {
					s = "\\Flagged";
					unread = false;
				} else if (f == Flags.Flag.RECENT) {
					s = "\\Recent";
					unread = false;
				} else if (f == Flags.Flag.SEEN) {
					s = "\\Seen";
					unread = false;
				} else {
					s = "Nothing";
					unread = true;
				}

				msgEmail.setTexto(abrirMensagem(msg));

				// StringBuilder bodyEmail = new StringBuilder();
				// bodyEmail.append(to);
				// bodyEmail.append(from);
				// bodyEmail.append(subject);
				// bodyEmail.append(msg.getReceivedDate());
				// bodyEmail.append(abrirMensagem(msg));
				// bodyEmail.append(recipients);
				//
				// System.out.println(s);
			}

		} catch (MessagingException e) {

			e.printStackTrace();
		}

		return msgEmail;

	}

	public void abrirFolder(String name){
		try{
		System.out.println(name);
		Folder folder = store.getFolder(name);
		folder.open(Folder.READ_ONLY);
		Message[] msg = folder.getMessages();
		int total = msg.length;
		for(int i = total; i > 0 ;i--){
			System.out.println(readEmail(msg[i]));
		}
		}catch(MessagingException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param part
	 * @return Retorna o conteudo (Texto) do e-mail
	 */
	private String abrirMensagem(Part p) {
		String body = "Message:    \n";
		try {

			if (p.isMimeType("text/plain")) {
				// pr("This is plain text");
				// pr("---------------------------");

				body = (String) p.getContent();
			} else if (p.isMimeType("multipart/*")) {
				// pr("This is a Multipart");
				// pr("---------------------------");
				Multipart mp = (Multipart) p.getContent();

				int count = mp.getCount();
				System.out.println("multipart");
				for (int i = 0; i < count; i++)
					body += parseStreamToString(mp.getBodyPart(i)); // Eh um
																// documento do
																// tipo html que
																// precisa ser
																// interpretado

			} else if (p.isMimeType("message/rfc822")) {
				System.out.println("message/rfc");
				// pr("This is a Nested Message");
				// pr("---------------------------");
				// level++;
				body += parseStreamToString((Part) p.getContent());
				// level--;
			} else {

				/*
				 * Não é um tipo de MIME ou texto simples, portante usar o
				 * Casting pra pegar o dado
				 */
				Object o;

				o = p.getContent();

				if (o instanceof String) { // é um string?
					System.out.println("string");
					body += (String) o;
				} else if (o instanceof InputStream) {
					System.out.println("input");
					InputStream is = (InputStream) o;
					int c;
					while ((c = is.read()) != -1)
						body += (char) c;
				} else {
					/*
					 * É um tipo de dado desconhecido
					 */
					body = "Não foi possivel decodificar o e-mail";

				}
				// just a separator
				// pr("---------------------------");

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return body;
	}

	/**
	 * 
	 * @param Tipo Part, que é um stream, e converte pra string
	 * @return retorna uma string
	 * @throws Exception
	 */
	private String parseStreamToString(Part p) throws Exception {
		InputStream is = p.getInputStream(); // Pego os bytes

		/*
		 * Se não recuperar um Stream então eu o coloco dentro de um Stream
		 */
		if (!(is instanceof BufferedInputStream)) {
			is = new BufferedInputStream(is);
		}
		int c;
		String msg = "";
		while ((c = is.read()) != -1) {
			msg += (char) c;
		}

		return msg;
	}

	
	
	public void authenticOnEmail() {

		try {
			// this.user = user;
			// this.pass = pass;

			Properties prop = new Properties();
			// Definindo as configurações de conexão
			prop.setProperty("mail.smtp.host", host);
			prop.setProperty("mail.smtp.port", String.valueOf(port));
			prop.put("mail.smtp.port", String.valueOf(port));
			prop.setProperty("mail.smtp.user", user);
			prop.setProperty("mail.smtp.password", pass);
			if (isSsl) { // Se o usuario precisar de uma conexão ssl, entao eu
							// seto
				prop.put("mail.smtp.socketFactory.class",
						"javax.net.ssl.SSLSocketFactory");
			}
			prop.put("mail.smtp.auth", "true"); // e requer autenticação

			session = Session.getDefaultInstance(prop);

			store = session.getStore("imaps");
			store.connect("imap.gmail.com", this.user, this.pass);

			store.addStoreListener(new StoreListener() {

				@Override
				public void notification(StoreEvent e) {
					String s = ".";
					if (e.getMessageType() == StoreEvent.ALERT)
						s = "ALERT: ";
					else
						s = "NOTICE: ";
					System.out.println(s + e.getMessage());

				}
			});

			folderInbox = store.getFolder("Inbox");
			try { // Tento abrir pra leitura e escrita dos email
				folderInbox.open(Folder.READ_WRITE);
			} catch (MessagingException e) {
				folderInbox.open(Folder.READ_ONLY); // Abro como somente
													// leitura...
			}

			folderInbox.addMessageCountListener(new MessageCountListener() {

				@Override
				public void messagesRemoved(MessageCountEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void messagesAdded(MessageCountEvent arg0) {
					System.out.println("Adicionou uma msg");

				}
			});

			System.out.println("Autenticado");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public void sendEmail(String to, String body, String subject) {
		Message msg = new MimeMessage(session);

		try {
			// msg.setFrom(new InternetAddress());
			msg.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
			msg.setSubject(subject);
			msg.setText(body);

			Transport.send(msg);
			System.out.println("enviado");

		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int countUnredMessages() {
		try {
			return folderInbox.getUnreadMessageCount();
		} catch (MessagingException e) {
			return -1;
		}
	}

	public static void main(String[] args) {
		try {
			EmailController email = 
					new EmailController("smtp.gmail.com","imap.gmail.com",465,true);
//		List<Folder> f = email.listarFolders();
//		for(Folder fo : f){
//			System.out.println(fo);
//			for(Folder fold : fo.list()){
//				if("Todos os e-mails".equals(fold.getName())){
//					fold.open(Folder.READ_ONLY);
//					for(String s : email.listarViewEmails(fold))
//							System.out.println(s);
//				}
//				System.out.println(fold.getName());
//			}
//		}
//		
//		email.abrirFolder("[Gmail]");
		email.abrirFolder("INBOX");
		

		} catch (Exception er) {
			er.printStackTrace();
		}
	

	}

}
