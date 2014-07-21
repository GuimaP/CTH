//package Controller;
//
//import java.io.BufferedInputStream;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.ObjectOutputStream;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Properties;
//
//import javax.mail.Address;
//import javax.mail.Authenticator;
//import javax.mail.FetchProfile;
//import javax.mail.Flags;
//import javax.mail.Flags.Flag;
//import javax.mail.Folder;
//import javax.mail.Message.RecipientType;
//import javax.mail.MessagingException;
//import javax.mail.Multipart;
//import javax.mail.NoSuchProviderException;
//import javax.mail.Part;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Store;
//import javax.mail.Transport;
//import javax.mail.URLName;
//import javax.mail.event.MessageChangedListener;
//import javax.mail.event.MessageCountEvent;
//import javax.mail.event.MessageCountListener;
//import javax.mail.event.StoreEvent;
//import javax.mail.event.StoreListener;
//import javax.mail.internet.AddressException;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeMultipart;
//import javax.mail.search.FlagTerm;
//import javax.mail.Message;
//
//import org.eclipse.swt.layout.GridLayout;
//import org.eclipse.swt.widgets.Display;
//import org.eclipse.swt.widgets.Shell;
//import org.omg.PortableInterceptor.SUCCESSFUL;
//import org.w3c.dom.ls.LSSerializer;
//
//import Model.GroupEmails;
//import Model.MensagemEmail;
//import Model.UsuarioEmail;
//import antlr.debug.MessageEvent;
//
//import com.itextpdf.awt.geom.misc.Messages;
//import com.itextpdf.text.pdf.FdfReader;
//import com.sun.faces.facelets.tag.jstl.core.ForEachHandler;
//import com.sun.mail.imap.IMAPFolder;
//import com.sun.mail.imap.IMAPFolder.FetchProfileItem;
//import com.sun.mail.pop3.POP3SSLStore;
//
//public class EmailController {
//	private String host;
//	private String hostRecieve;
//	private String user;
//	private String pass;
//	private int port;
//	private boolean isSsl;
//	private Store store;
//	private Folder folderInbox;
//	private Session session;
//
//	private Map<String, List<MensagemEmail>> map;
//
//	int tentativas; // contador de tentativas
//
//	public EmailController(UsuarioEmail e) {
//		this.host = e.getHost();
//		this.port = e.getPort();
//		this.isSsl = e.isSsl();
//		this.hostRecieve = e.getHostReceive();
//		this.user = e.getUser();
//		this.pass = e.getPass();
//
//		this.tentativas = 0;
//
//		authenticOnEmail();
//		// getEmails();
//		// PEGAR DE UM ARQUIVO CRIPTOGRAFADO E SERIALIZADO.
//	}
//
//	public EmailController() {
//
//	}
//
//	public String getUser() {
//		return this.user;
//	}
//
//	public Map<String, List<MensagemEmail>> getEmails() {
//		int contador = 0;
//		try {
//			System.out.println(store.isConnected());
//			if (!store.isConnected()) {
//
//				if (tentativas > 3) {
//					return null;
//				}
//				tentativas++;
//				store.connect(host, user, pass);
//				return getEmails();
//			} else {
//				System.out.println("entro no metodo");
//				map = new HashMap<String, List<MensagemEmail>>();
//
//				Folder folders = store.getFolder("INBOX");
//
//				// for (Folder f : folders) {
//				Folder f = folders;
//				System.out.println(f.getName());
//
//				List<MensagemEmail> lsEmails = new ArrayList<MensagemEmail>();
//				System.out.println("abriu open da " + f.getName());
//				f.open(Folder.READ_ONLY);
//				System.out.println("Total de mensagens - " + f.getName()
//						+ " : " + f.getMessages().length + " / "
//						+ f.getMessageCount());
//				for (Message m : f.getMessages()) {
//					MensagemEmail ms = readEmail(m);
//					contador++;
//					System.out.println("COntador: " + contador);
//					lsEmails.add(ms);
//				}
//				contador = 0;
//
//				map.put(f.getName(), lsEmails);
//			}
//
//			// }
//		} catch (MessagingException e) {
//			System.out.println(e.getMessage());
//		}
//		return map;
//
//	}
//	
//	//
//	
//	
//	
//	public Map<String, List<MensagemEmail>> getEmails(int index,String folder,List<MensagemEmail>ls,Map<String,List<MensagemEmail>> ma) {
//		int contador = 0;
//		try {
//			System.out.println(store.isConnected());
//			if (!store.isConnected()) {
//
//				if (tentativas > 3) {
//					return null;
//				}
//				tentativas++;
//				store.connect(host, user, pass);
//				return getEmails(index,folder,ls,ma);
//			} else {
//				
//				
//				System.out.println("Entrei com os do parametros");
//				
//				
//				List<MensagemEmail> lsEmails = new ArrayList<MensagemEmail>();
//				map = new HashMap<String, List<MensagemEmail>>();
//				
//				System.out.println(ls.size() + "  ---   " + ma.size());
//				if(ls.size()>0){
//					lsEmails = ls;
//					contador = ls.size();
//				}
//				
//				if(ma.size()>0){
//					map = ma;
//				}
//				
//				
//				
//				
//				
//
//				Folder folders = store.getFolder(folder);
//
//				// for (Folder f : folders) {
//				Folder f = folders;
//				System.out.println(f.getName());
//
//				
//				System.out.println("abriu open da " + f.getName());
//				f.open(Folder.READ_ONLY);
//				System.out.println("Total de mensagens - " + f.getName()
//						+ " : " + f.getMessages().length + " / "
//						+ f.getMessageCount());
////				for (Message m : f.getMessages()) {
//				for(int i = index;i < f.getMessageCount();i++){
//					Message m = f.getMessages()[i];
//					MensagemEmail ms = readEmail(m);
//					contador++;
//					System.out.println("COntador: " + contador);
//					lsEmails.add(ms);
//					File arqTem = new File(getClass().getResource("/Resources/FilesConfig")
//							.getPath()+"/"+user+"@itensTemp.temp");
//					FileOutputStream ou  = new FileOutputStream(arqTem);
//					ObjectOutputStream os = new ObjectOutputStream(ou);
//					os.writeObject(lsEmails);
//				}
//				contador = 0;
//				map.put(f.getName(), lsEmails);
//				File arqMap = new File(getClass().getResource("/Resources/FilesConfig")
//						.getPath()+"/"+user+"@@itensMap.temp");
//				FileOutputStream ou  = new FileOutputStream(arqMap);
//				ObjectOutputStream os = new ObjectOutputStream(ou);
//				os.writeObject(map);
//				
//			}
//
//			// }
//		} catch (MessagingException | IOException e) {
//			System.out.println(e.getMessage());
//		}
//		return map;
//	}
//
//	
//	
//	
//	//
//
////	public List<Folder> listarFolderss() {
////		List<Folder> lsFolders = new ArrayList<Folder>();
////		try {
////			Store store = session.getStore("imaps");
////			store.connect(this.hostRecieve, this.user, this.pass);
////			Folder[] vtrFolder = store.getDefaultFolder().list();
////			for (Folder f : vtrFolder) {
////				lsFolders.add(f);
////			}
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
////
////		return lsFolders;
////	}
//
//	public List<String> getListagemFolders() {
//
//		List<String> listFolders = new ArrayList<String>();
//		try {
//			if (!store.isConnected()) {
//				store.connect(this.hostRecieve, this.user, this.pass);
//				if (tentativas > 10) { // se tentou 10 vezes, então eu paro de
//										// tentar conectar
//					return null;
//				}
//				tentativas++;
//				return getListagemFolders(); // e chamo o metodo de novo
//			} else {
//				tentativas = 0;
//				Store store = session.getStore("imaps");
//				store.connect(this.hostRecieve, this.user, this.pass);
//				Folder[] listagemFolders = store.getDefaultFolder().list();
//
//				for (Folder f : listagemFolders) {
//					f.open(Folder.READ_ONLY);
//					String name = f.getName();
//					listFolders.add(name);
//				}
//			}
//
//		} catch (Exception e) {
//
//		}
//
//		return listFolders;
//	}
//
//	// TODO FAZER METODO QUE PASSA O NOME DO FOLDER, E DEVOLVE UM LIST DE EMAIL
//	// DAQUELE FOLDER
//
//	/**
//	 * 
//	 * @param Folder
//	 * @return Retorna uma visão de itens de E-mail
//	 */
//	public synchronized List<String> listarViewEmails(String name) {
//		List<String> ls = new ArrayList<String>();
//
//		try {
//			if (!store.isConnected()) { // Se estiver desconectador por conta de
//										// muitos request
//				store.connect(this.hostRecieve, this.user, this.pass); // eu
//																		// conecto
//																		// de
//																		// novo
//				if (tentativas > 10) { // se tentou 10 vezes, então eu paro de
//										// tentar conectar
//					return null;
//				}
//				tentativas++;
//				return listarViewEmails(name); // e chamo em recursividade o
//												// metodo
//			} else {
//				tentativas = 0;
//				if (!"[Gmail]".equalsIgnoreCase(name)) {
//					
//					List<MensagemEmail> temp = map.get(name); //Pego a listagem de emails
//					
//					List<MensagemEmail> lsEmails = new ArrayList<MensagemEmail>();// map.get(name);
//					
//					
//					Folder folder = store.getFolder(name);
//
//					FetchProfile fp = new FetchProfile();
//					fp.add(FetchProfileItem.ENVELOPE);
//					fp.add(FetchProfileItem.FLAGS);
//					fp.add(FetchProfileItem.SIZE);
//					folder.open(Folder.READ_ONLY);
//					Message[] msgs = folder.getMessages();
//
//					int total = msgs.length - 1;
//					System.out.println("Total: " + total);
//					for (int i = total; i > 0; i--) {
//						boolean isRecent = msgs[i].getFlags().contains(
//								Flags.Flag.SEEN);
//						String from = "";
//
//						Address[] vtrAdres = msgs[i].getFrom();
//						for (Address a : vtrAdres) {
//							from += a.toString();
//						}
//
//						String assunto = msgs[i].getSubject();
//						String dataRecebida = new SimpleDateFormat(
//								"dd/MM/yyyy -  hh:mm").format(msgs[i]
//								.getReceivedDate());
//						if (!isRecent) {
//							ls.add("<html><b>De: " + from + "  - Assunto: "
//									+ assunto + " - " + dataRecebida
//									+ "</b></html>");
//						} else {
//							ls.add("De: " + from + "  - Assunto: " + assunto
//									+ " - " + dataRecebida);
//						}
//						lsEmails.add(readEmail(msgs[i]));
//					}
//					
//
//				
////					System.out
////							.println("Tamanho da list view" + lsEmails.size());
//					map.put(name, lsEmails);
//
//				}
//				
//			}
//
//		} catch (MessagingException e) {
//			e.printStackTrace();
//		}
//		return ls;
//	}
//
//	public MensagemEmail getEmail(String folderName, int index) {
//		MensagemEmail msg = new MensagemEmail(); //Instancia do objeto
//		
//		System.out.println("INDEX - " + index);
//		List<MensagemEmail> ls = map.get(folderName);
//		System.out.println("TAMANHO " + ls.size());
//		if (index >= ls.size()) {
//
//			try {
//				// para msg lida, sem interromper o processo de
//				// email
//				Folder folder;
//
//				folder = store.getFolder(folderName);
//
//				folder.open(Folder.READ_WRITE);
//				Message[] messages = folder.getMessages();
//				messages[index].setFlag(Flags.Flag.SEEN, true);
//
//				msg = readEmail(messages[index]);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		} else {
//			msg = ls.get(index);
//		}
//		return msg;
//	}
//
//	public List<MensagemEmail> getEmail(String folderName) {
//		MensagemEmail msg = new MensagemEmail();
//		
//		List<MensagemEmail> ls = new ArrayList<MensagemEmail>();
//		System.out.println("TAMANHO " + ls.size());
//		if (ls.size() > 0) {
//
//			try {
//				// para msg lida, sem interromper o processo de
//				// email
//				Folder folder;
//
//				folder = store.getFolder(folderName);
//
//				folder.open(Folder.READ_ONLY);
//				Message[] messages = folder.getMessages();
//				for (Message m : messages) {
//					msg = readEmail(m);
//					ls.add(msg);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			// msg = (map.get(folderName)).get(index);
//		}
//		return ls;
//	}
//
//	/**
//	 * 
//	 * @param msg
//	 * @return Retorna um objeto formatado com os dados do e-mail
//	 */
//	public MensagemEmail readEmail(Message msg) {
//
//		MensagemEmail msgEmail = new MensagemEmail();
//
//		try {
//			String subject = "SUBJECT: ";
//
//			subject = msg.getSubject();
//			msgEmail.setSubject(subject);
//			
//			boolean isRecente = msg.getFlags().contains(Flags.Flag.SEEN);
//
//			Address[] endr = null;
//
//			if ((endr = msg.getFrom()) != null) {
//				List<Address> lsFrom = new ArrayList<Address>();
//				for (int j = 0; j < endr.length; j++) {
//					lsFrom.add(endr[j]);
//				}
//				msgEmail.setFrom(lsFrom);
//			}
//
//			if ((endr = msg.getRecipients(RecipientType.TO)) != null) {
//				List<Address> lsDestinatario = new ArrayList<Address>();
//				for (int j = 0; j < endr.length; j++) {
//					lsDestinatario.add(endr[j]);
//				}
//				msgEmail.setTo(lsDestinatario);
//			}
//
//
//			msgEmail.setDataRecebida(msg.getReceivedDate());
//			msgEmail.setTexto(abrirMensagem(msg));
//			boolean unread = !isRecente;
//			msgEmail.setUnread(unread);
//
//		} catch (MessagingException e) {
//
//			e.printStackTrace();
//		}
//
//		return msgEmail;
//
//	}
//
//	/**
//	 * 
//	 * @param part
//	 * @return Retorna o conteudo (Texto) do e-mail
//	 */
//
//	public void markAsSeen(int index, String folderName) {
//		try {
//			// para msg lida, sem interromper o processo de
//			// email
//			Folder folder;
//
//			folder = store.getFolder(folderName);
//
//			folder.open(Folder.READ_WRITE);
//			Message[] messages = folder.getMessages();
//			messages[index].setFlag(Flags.Flag.SEEN, true);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	private String abrirMensagem(Part p) {
//		String body = "Message:    \n";
//		String teste = "";
//		try {
//
//			if (p.isMimeType("text/plain")) {
//				// pr("This is plain text");
//				// pr("---------------------------");
//				teste = "text/plain";
//				body = (String) p.getContent();
//			} else if (p.isMimeType("multipart/*")) {
//				// pr("This is a Multipart");
//				// pr("---------------------------");
//				Multipart mp = (Multipart) p.getContent();
//
//				int count = mp.getCount();
//				// System.out.println("multipart");
//				for (int i = 0; i < count; i++)
//					body += parseStreamToString(mp.getBodyPart(i)); // Eh um
//				// documento do
//				// tipo html que
//				// precisa ser
//				// interpretado
//				teste = "multipart";
//
//			} else if (p.isMimeType("message/rfc822")) {
//				// System.out.println("message/rfc");
//				// pr("This is a Nested Message");
//				// pr("---------------------------");
//				// level++;
//				body += parseStreamToString((Part) p.getContent());
//				teste = "message";
//				// level--;
//			} else {
//
//				/*
//				 * Não é um tipo de MIME ou texto simples, portante usar o
//				 * Casting pra pegar o dado
//				 */
//				Object o;
//
//				o = p.getContent();
//
//				if (o instanceof String) { // é um string?
//				// System.out.println("string");
//					body += (String) o;
//					teste = "string";
//				} else if (o instanceof InputStream) {
//					// System.out.println("input");
//					InputStream is = (InputStream) o;
//					int c;
//					teste = "inputStream";
//					System.out.println("bytes...");
//					while ((c = is.read()) != -1)
//						body += (char) c;
//				} else {
//					/*
//					 * É um tipo de dado desconhecido
//					 */
//					body = "Não foi possivel decodificar o e-mail";
//
//				}
//				// just a separator
//				// pr("---------------------------");
//
//				System.out.println(teste);
//			}
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return body;
//	}
//
//	/**
//	 * 
//	 * @param Tipo
//	 *            Part, que é um stream, e converte pra string
//	 * @return retorna uma string
//	 * @throws Exception
//	 */
//	private String parseStreamToString(Part p) throws Exception {
//		InputStream is = p.getInputStream(); // Pego os bytes
//
//		System.out.println("bytes to String");
//
//		/*
//		 * Se não recuperar um Stream então eu o coloco dentro de um Stream
//		 */
//		if (!(is instanceof BufferedInputStream)) {
//			is = new BufferedInputStream(is);
//		}
//		int c;
//		String msg = "";
//		while ((c = is.read()) != -1) {
//
//			msg += (char) c;
//			// System.out.println(c + "/"+msg);
//		}
//
//		return msg;
//	}
//
//	public void authenticOnEmail() {
//
//		try {
//
//			Properties prop = new Properties();
//			// Definindo as configurações de conexão
//			prop.setProperty("mail.smtp.host", host);
//			prop.setProperty("mail.smtp.port", String.valueOf(port));
//			prop.put("mail.smtp.port", String.valueOf(port));
//			prop.setProperty("mail.smtp.user", user);
//			prop.setProperty("mail.smtp.password", pass);
//			if (isSsl) { // Se o usuario precisar de uma conexão ssl, entao eu
//							// seto
//				prop.put("mail.smtp.socketFactory.class",
//						"javax.net.ssl.SSLSocketFactory");
//			}
//			prop.put("mail.smtp.auth", "true"); // e requer autenticação
//
//			session = Session.getDefaultInstance(prop,
//					new javax.mail.Authenticator() {
//						protected PasswordAuthentication getPasswordAuthentication() {
//							return new PasswordAuthentication(user, pass);
//						}
//					});
//
//
//
//			store = session.getStore("imaps");
//			store.connect(this.hostRecieve, this.user, this.pass);
//
//			store.addStoreListener(new StoreListener() {
//
//				@Override
//				public void notification(StoreEvent e) {
//					String s = ".";
//					if (e.getMessageType() == StoreEvent.ALERT)
//						s = "ALERT: ";
//					else
//						s = "NOTICE: ";
//					System.out.println(s + e.getMessage());
//
//				}
//			});
//
//			folderInbox = store.getFolder("Inbox");
//			try { // Tento abrir pra leitura e escrita dos email
//				folderInbox.open(Folder.READ_WRITE);
//			} catch (MessagingException e) {
//				folderInbox.open(Folder.READ_ONLY); // Abro como somente
//													// leitura...
//			}
//
//			folderInbox.addMessageCountListener(new MessageCountListener() {
//
//				@Override
//				public void messagesRemoved(MessageCountEvent arg0) {
//					// TODO Auto-generated method stub
//
//				}
//
//				@Override
//				public void messagesAdded(MessageCountEvent arg0) {
//					System.out.println("Adicionou uma msg");
//
//				}
//			});
//
//			System.out.println("Autenticado");
//		} catch (MessagingException e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * Testa se conseguiui logar no email
//	 * 
//	 * @param Usuario
//	 * @return sucesso ou não
//	 */
//	public boolean testaAuthenticOnEmail(UsuarioEmail u) {
//
//		boolean resultado = true;
//		try {
//
//			Properties prop = new Properties();
//			prop.setProperty("mail.smtp.host", u.getHost());
//			prop.setProperty("mail.smtp.port", String.valueOf(u.getPort()));
//			prop.put("mail.smtp.port", String.valueOf(u.getPort()));
//			prop.setProperty("mail.smtp.user", u.getUser());
//			prop.setProperty("mail.smtp.password", u.getPass());
//			if (u.isSsl()) { // Se o usuario precisar de uma conexão ssl, entao
//								// eu
//								// seto
//				prop.put("mail.smtp.socketFactory.class",
//						"javax.net.ssl.SSLSocketFactory");
//			}
//			prop.put("mail.smtp.auth", "true"); // e requer autenticação
//			//
//			Session se;
//
//			se = Session.getDefaultInstance(prop,
//					new javax.mail.Authenticator() {
//						protected PasswordAuthentication getPasswordAuthentication() {
//							return new PasswordAuthentication(u.getUser(), u
//									.getPass());
//						}
//					});
//
//			// session = Session.getDefaultInstance(prop);
//
//			Store st;
//
//			st = se.getStore("imaps");
//			st.connect(u.getHostReceive(), u.getUser(), u.getPass());
//
//			System.out.println("Autenticado");
//		} catch (MessagingException e) {
//			e.printStackTrace();
//			resultado = false;
//		}
//
//		return resultado;
//	}
//
//	public synchronized void sendEmail(Address to, String body, String subject) {
//		Message msg = new MimeMessage(session);
//		try {
//			msg.setRecipient(javax.mail.internet.MimeMessage.RecipientType.TO,
//					to);
//			msg.setSubject(subject);
//			msg.setText(body);
//
//			Transport.send(msg);
//			System.out.println("enviado");
//
//		} catch (MessagingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
//
//	public synchronized List<MensagemEmail> getMensagensNaoLida(String name) {
//		List<MensagemEmail> listEmail = new ArrayList<MensagemEmail>();
//
//		try {
//			Store st = session.getStore("imaps");
//			if (!st.isConnected()) {
//				st.connect(this.hostRecieve, this.user, this.pass);
//				if (tentativas > 3) { // se tentou 10 vezes, então eu paro de
//										// tentar conectar
//					return new ArrayList<MensagemEmail>();
//				}
//				tentativas++;
//				getMensagensNaoLida(name);
//			} else {
//				tentativas = 0;
//				Folder folderEmail = st.getFolder(name); // Abro o diretorio
//															// de
//															// acordo com o nome
//															// da
//															// pasta
//				folderEmail.open(Folder.READ_ONLY); // Abro para somente leitura
//				Message messages[] = folderEmail.search(new FlagTerm(new Flags( // Filtro
//																				// apenas
//																				// todas
//																				// as
//																				// mensagens
//																				// que
//																				// n
//																				// foram
//																				// vistas
//						Flags.Flag.SEEN), false));
//
//				FetchProfile fp = new FetchProfile();
//				fp.add(FetchProfile.Item.ENVELOPE);
//				fp.add(FetchProfile.Item.CONTENT_INFO);
//
//				folderEmail.fetch(messages, fp); // Atualizo o folder
//
//				folderEmail.open(Folder.READ_ONLY);
//
//				for (Message m : messages) { // e pego todas as mensagens e
//												// mostro
//					MensagemEmail msg = readEmail(m);
//					// System.out.println(msg.getFrom().toString());
//					listEmail.add(msg);
//				}
//
//				System.out.println("No. of Unread Messages : "
//						+ messages.length + "\n " + listEmail.size());
//			}
//		} catch (MessagingException e) {
//			e.printStackTrace();
//		} finally {
//
//		}
//
//		return listEmail;
//	}
//
//	public synchronized Map<String, List<MensagemEmail>> getMap() {
//		return map;
//	}
//
//}
