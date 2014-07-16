package Controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import javax.mail.search.FlagTerm;
import javax.mail.Message;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.w3c.dom.ls.LSSerializer;

import Model.GroupEmails;
import Model.MensagemEmail;
import Model.UsuarioEmail;
import antlr.debug.MessageEvent;

import com.itextpdf.awt.geom.misc.Messages;
import com.itextpdf.text.pdf.FdfReader;
import com.sun.faces.facelets.tag.jstl.core.ForEachHandler;
import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPFolder.FetchProfileItem;
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

	private Map<String, List<MensagemEmail>> map;

	public EmailController(UsuarioEmail e) {
		this.host = e.getHost();
		this.port = e.getPort();
		this.isSsl = e.isSsl();
		this.hostRecieve = e.getHostReceive();
		this.user = e.getUser();
		this.pass = e.getPass();

		authenticOnEmail();
		getEmails();
		// PEGAR DE UM ARQUIVO CRIPTOGRAFADO E SERIALIZADO.
	}

	public String getUser() {
		return this.user;
	}

	public Map<String, List<MensagemEmail>> getEmails() {
		map = new HashMap<String, List<MensagemEmail>>();
		try {
			Folder[] folders = store.getDefaultFolder().list();

			for (Folder f : folders) {
				System.out.println(f.getName());

				List<MensagemEmail> lsEmails = new ArrayList<MensagemEmail>();
				f.open(Folder.READ_ONLY);
				for (Message m : f.getMessages()) {
					MensagemEmail ms = readEmail(m);
					lsEmails.add(ms);
				}

				map.put(f.getName(), lsEmails);
			}
		} catch (MessagingException e) {

		}
		return map;

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

	public List<String> getListagemFolders() {
		
		List<String> listFolders = new ArrayList<String>();
		try {
			if (!store.isConnected()) {
				store.connect(this.hostRecieve, this.user, this.pass);
				return getListagemFolders(); //e chamo o metodo de novo
			}else {
			Store store = session.getStore("imaps");
			store.connect(this.hostRecieve, this.user, this.pass);
			Folder[] listagemFolders = store.getDefaultFolder().list();

			for (Folder f : listagemFolders) {
				f.open(Folder.READ_ONLY);
				String name = f.getName();
				listFolders.add(name);
			}
			}

		} catch (Exception e) {

		}

		return listFolders;
	}

	// TODO FAZER METODO QUE PASSA O NOME DO FOLDER, E DEVOLVE UM LIST DE EMAIL
	// DAQUELE FOLDER

	/**
	 * 
	 * @param Folder
	 * @return Retorna uma visão de itens do E-mail
	 */
	public synchronized List<String> listarViewEmails(String name) {
		List<String> ls = new ArrayList<String>();
		try {
			if (!store.isConnected()) { //Se estiver desconectador por conta de muitos request
				store.connect(this.hostRecieve, this.user, this.pass); // eu conecto de novo 
				return listarViewEmails(name); // e chamo em recursividade o metodo
			}else{

			if (!"[Gmail]".equalsIgnoreCase(name)) {
				Folder folder = store.getFolder(name);

				FetchProfile fp = new FetchProfile();
				fp.add(FetchProfileItem.ENVELOPE);
				fp.add(FetchProfileItem.FLAGS);
				fp.add(FetchProfileItem.SIZE);
				folder.open(Folder.READ_ONLY);
				Message[] msgs = folder.getMessages();

				int total = msgs.length - 1;
				for (int i = total; i > 0; i--) {
					boolean isRecent = msgs[i].getFlags().contains(
							Flags.Flag.SEEN);
					String from = "";

					Address[] vtrAdres = msgs[i].getFrom();
					for (Address a : vtrAdres) {
						from += a.toString();
					}

					String assunto = msgs[i].getSubject();
					String dataRecebida = new SimpleDateFormat(
							"dd/MM/yyyy -  hh:mm").format(msgs[i]
							.getReceivedDate());
					if (!isRecent) {
						ls.add("<html><b>De: " + from + "  - Assunto: "
								+ assunto + " - " + dataRecebida
								+ "</b></html>");
					} else {
						ls.add("De: " + from + "  - Assunto: " + assunto
								+ " - " + dataRecebida);
					}
				}
			}
			}

		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return ls;
	}

	public MensagemEmail getEmail(String folderName, int index) {
		MensagemEmail msg = new MensagemEmail();
		// if(!store.isConnected()){
		// store.connect(hostRecieve, user, pass);
		// }

		new Thread(() -> { // Inicio uma thread para mudar a status de nova msg
							// para msg lida, sem interromper o processo de
							// email
					Folder folder;
					try {
						folder = store.getFolder(folderName);

						folder.open(Folder.READ_WRITE);
						Message[] messages = folder.getMessages();
						messages[index].setFlag(Flags.Flag.SEEN, true);
					} catch (Exception e) {
						
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}).start();

		msg = (map.get(folderName)).get(index);

		// msg = (map.get(folderName)).get(index);
		return msg;
	}

	/**
	 * 
	 * @param msg
	 * @return Retorna um objeto formatado com os dados do e-mail
	 */
	public MensagemEmail readEmail(Message msg) {

		MensagemEmail msgEmail = new MensagemEmail();

		try {

			String recipients = "COM COPIA PARA: ";
			String subject = "SUBJECT: ";
			String body = "";

			subject = msg.getSubject();
			msgEmail.setSubject(subject);
			boolean isRecente = msg.getFlags().contains(Flags.Flag.SEEN);

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
				}
				msgEmail.setTo(lsDestinatario);
			}

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
			}
			boolean unread = !isRecente;
			msgEmail.setUnread(unread);

		} catch (MessagingException e) {

			e.printStackTrace();
		}

		return msgEmail;

	}

	// public void abrirFolder(String name){
	// try{
	// System.out.println(name);
	// Folder folder = (IMAPFolder) store.getFolder(name);
	// folder.open(Folder.READ_ONLY);
	// Message[] msg = folder.getMessages();
	// int total = msg.length -1;
	// for(int i = total; i > 0 ;i--){
	// System.out.println(readEmail(msg[i]));
	// }
	// }catch(MessagingException e){
	// e.printStackTrace();
	// }
	// }
	//

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
	 * @param Tipo
	 *            Part, que é um stream, e converte pra string
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

	public synchronized List<MensagemEmail> countUnredMessages(String name) {
		List<MensagemEmail> listEmail = new ArrayList<MensagemEmail>();
		try {
			if (!store.isConnected()) {
				store.connect(this.hostRecieve, this.user, this.pass);
				countUnredMessages(name);
			}else{

			Folder folderEmail = store.getFolder(name); // Abro o diretorio de
														// acordo com o nome da
														// pasta
			folderEmail.open(Folder.READ_ONLY); // Abro para somente leitura
			Message messages[] = folderEmail.search(new FlagTerm(new Flags( // Filtro
																			// apenas
																			// todas
																			// as
																			// mensagens
																			// que
																			// n
																			// foram
																			// vistas
					Flags.Flag.SEEN), false));

			FetchProfile fp = new FetchProfile();
			fp.add(FetchProfile.Item.ENVELOPE);
			fp.add(FetchProfile.Item.CONTENT_INFO);

			folderEmail.fetch(messages, fp); // Atualizo o folder

			for (Message m : messages) { // e pego todas as mensagens e mostro
				MensagemEmail msg = readEmail(m);
				// System.out.println(msg.getFrom().toString());
				listEmail.add(msg);
			}

			System.out.println("No. of Unread Messages : " + messages.length
					+ "\n " + listEmail.size());
			}
		} catch (MessagingException e) {
			e.printStackTrace();
		} finally {

		}

		return listEmail;
	}

	public static void main(String[] args) {
		try {
			// EmailController email =
			// new EmailController("smtp.gmail.com","imap.gmail.com",465,true);
			// List<Folder> f = email.listarFolders();
			// for(Folder fo : f){
			// System.out.println(fo);
			// for(Folder fold : fo.list()){
			// if("Todos os e-mails".equals(fold.getName())){
			// fold.open(Folder.READ_ONLY);
			// for(String s : email.listarViewEmails(fold))
			// System.out.println(s);
			// }
			// System.out.println(fold.getName());
			// }
			// }
			//
			// email.abrirFolder("[Gmail]");
			// System.out.println( email.getListagemFolders());
			// email.countUnredMessages("INBOX");
			//

		} catch (Exception er) {
			er.printStackTrace();
		}

	}

}
