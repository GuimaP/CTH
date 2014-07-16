package View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
















import javassist.expr.NewArray;

















import javax.mail.Address;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

















import antlr.TreeParserSharedInputState;

















import com.itextpdf.text.log.SysoCounter;
import com.sun.media.rtsp.protocol.PauseMessage;

















import Controller.ConfigController;
import Controller.EmailController;
import Model.Login;
import Model.MensagemEmail;
import Model.ModelTableEmail;
import Model.UsuarioEmail;

public class Principal extends JFrame {

	private JMenuBar menuBarra;
	private JMenu menuArquivo, menuAgendamento, menuRelatorio;
	private JMenuItem itSair, itCadastroCliente, itCadastroFuncionario,
			itCalendario, itAgendamento, itCadastroPacote, itCadastroCarro;
	private JLabel redefinirSenha;
	private boolean painelMostrando = false;
	private JPanel painelInformativo, painelLateralGuia;
	private JButton btAbrirInformativo, btAbrirMenuLateral;
	protected static JFrame minhaFrame; // Frame para setar a dialogs
	private JToolBar barraLateral;
	private JScrollPane sp;
	private PainelCalendarioAgendamento painelCalendario;

	private int POSXButoon;
	protected JTree jtreeAtalhos;

	private EmailController email;
	protected JPanel painelEmail;
	private JTable jTableEmails;
	private List<String> listaEmails;
	private HashMap<String, List<String>> mapEmails;
	protected Login loginUser;
	private boolean isPainelEmailShow = false;
	private MensagemEmail mensagem;
	
	private Thread gerenciaEmal;
	private final int WIDTH_TAMANHO = 301;
	
	private boolean hasEmailReady = false;

	protected static boolean isFrameInstrutorOpen, isFrameClienteOpen,
			isFrameCadastroPacote, isFrameAgendamento, isFrameCarro;

	public Principal(Login usuario) {
		try {
			minhaFrame = this;
			UsuarioEmail user;
			File fileEmailsEmails = new File(getClass().getResource(
					"/Resources/FilesConfig")
					+ "/email-" + usuario.getUsuario() + ".ser");
			if (fileEmailsEmails.exists()) {
				FileInputStream input = new FileInputStream(fileEmailsEmails);
				ObjectInputStream obj = new ObjectInputStream(input);
				user = (UsuarioEmail) obj.readObject();
				obj.close();
			} else {
				user = new UsuarioEmail();
				user.setHost("smtp.gmail.com");
				user.setHostReceive("imap.gmail.com");
				user.setPort(465);
				user.setSsl(true);
				user.setUser("guima.teste.p@gmail.com");
				user.setPass("guimateste");

				FileOutputStream ou = new FileOutputStream("te.ser");
				ObjectOutputStream os = new ObjectOutputStream(ou);
				os.writeObject(user);
				os.flush();
				os.close(); // TODO APLICAR CRIPTOGRAFIA

			}
			
			inicializaComponentes();
			
			new Thread(()->{
				this.email = new EmailController(user);
				
				DefaultMutableTreeNode root = new DefaultMutableTreeNode("Inicio");

				DefaultMutableTreeNode favItens = new DefaultMutableTreeNode(
						"Favoritos");
				root.add(favItens);

				DefaultMutableTreeNode tarefaItens = new DefaultMutableTreeNode(
						"Tarefas");
				root.add(tarefaItens);

				DefaultMutableTreeNode dmEmail = new DefaultMutableTreeNode("E-mail");

			
				List<String> folders = email.getListagemFolders();
				folders.forEach(fo -> {
					DefaultMutableTreeNode dm = new DefaultMutableTreeNode(fo);
					dmEmail.add(dm);
				});
				

				root.add(dmEmail);
				DefaultTreeModel model = new DefaultTreeModel(root);
			
				
				jtreeAtalhos.setModel(model);
				minhaFrame.revalidate();
				minhaFrame.repaint();
				System.out.println("repaint na tela");
				gerenciaEmal = new Thread(new CheckNewMessages(jTableEmails,
						jtreeAtalhos, painelEmail,email));
				gerenciaEmal.setDaemon(true);
				gerenciaEmal.start();
			}).start();
			

			this.loginUser = usuario;
			

			definirEventos();

			

			
			System.out.println("iniciando a thread");
			
			sp.setViewportView(jtreeAtalhos);
			
			System.out.println("ao que parece iniciou a thread");
			
			

			isFrameClienteOpen = isFrameInstrutorOpen = isFrameCadastroPacote = isFrameAgendamento = isFrameCarro = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void inicializaComponentes() throws IOException {
		// Definindo o layout e a imagem de fundo
		setLayout(null);
		JDesktopPane fundoDaPrincipal = new JDesktopPane();
		setContentPane(fundoDaPrincipal);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Toolkit toolkit = Toolkit.getDefaultToolkit();  
		Dimension scrnsize = toolkit.getScreenSize();  
		setSize(scrnsize);
		
		setIconImage(ConfigController.defineIcon());

		//
		menuBarra = new JMenuBar();
		//

		
		menuArquivo = new JMenu("Arquivo");
		menuAgendamento = new JMenu("Agendamento");
		menuRelatorio = new JMenu("Relatorio");
		//
		itSair = new JMenuItem("Sair");
		itCadastroCliente = new JMenuItem("Cadastro Aluno");
		itCadastroFuncionario = new JMenuItem("Cadastro Funcionario");
		itCalendario = new JMenuItem("Calendario");
		itAgendamento = new JMenuItem("Agendar Aula");
		itCadastroPacote = new JMenuItem("Cadastro Pacote");
		itCadastroCarro = new JMenuItem("Cadastro Carro");
		//
		menuArquivo.add(itCadastroCliente);
		menuArquivo.add(itCadastroFuncionario);
		menuArquivo.add(itCadastroPacote);
		menuArquivo.add(itCadastroCarro);
//		menuArquivo.add(itCalendario);
		menuArquivo.add(itSair);
		menuAgendamento.add(itAgendamento);
		//
		menuBarra.add(menuArquivo);
		menuBarra.add(menuAgendamento);
		menuBarra.add(menuRelatorio);


		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Inicio");

		DefaultMutableTreeNode favItens = new DefaultMutableTreeNode(
				"Favoritos");
		root.add(favItens);

		DefaultMutableTreeNode tarefaItens = new DefaultMutableTreeNode(
				"Tarefas");
		root.add(tarefaItens);

		DefaultMutableTreeNode dmEmail = new DefaultMutableTreeNode("E-mail");

		if(hasEmailReady){
		List<String> folders = email.getListagemFolders();
		folders.forEach(fo -> {
			DefaultMutableTreeNode dm = new DefaultMutableTreeNode(fo);
			dmEmail.add(dm);
		});
		

		root.add(dmEmail);
		}

		jtreeAtalhos = new JTree(root);
		jtreeAtalhos.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		jtreeAtalhos.setCellRenderer(new MeuModeloTree());
		jtreeAtalhos.expandRow(60);

		
		painelEmail = new JPanel(); // INICANDO O PAINEL
		painelEmail.setLayout(new GridLayout(1, 1));

		barraLateral = new JToolBar();
		barraLateral.setEnabled(false);
		barraLateral.setLayout(null);
		barraLateral.setSize(170,minhaFrame.getHeight()-60);
		barraLateral.setLocation(-(barraLateral.getWidth()),0);
		barraLateral.setVisible(false);

		sp = new JScrollPane(jtreeAtalhos);
		sp.setBounds(barraLateral.getX(), 0, 170, barraLateral.getHeight());
		barraLateral.add(sp);

		add(barraLateral);

		btAbrirMenuLateral = new JButton(">>");
		btAbrirMenuLateral.setFocusCycleRoot(true);
		btAbrirMenuLateral.setBounds(5, 10, 45, 30);
		add(btAbrirMenuLateral);

		POSXButoon = btAbrirMenuLateral.getX();

		java.awt.Point p = new Point(minhaFrame.getWidth() - 405, 100);
		painelCalendario = new PainelCalendarioAgendamento(p);/* Definindo ele
																												 * no canto
																												 * esquerdo da
																												 * tela
																												*/
		
		 jTableEmails = new JTable(new ModelTableEmail(new ArrayList<String>()));
		 
		 jTableEmails.setRowHeight(20);
		 JScrollPane spe = new JScrollPane(jTableEmails);
		
		 painelEmail.add(spe);
		

		add(painelCalendario);

		setJMenuBar(menuBarra);
		setLocationRelativeTo(null);
		setTitle("Karol Habilitados v 1.2.2");
		// setResizable(false);
		setVisible(true);

	}

	public void definirEventos() {

		btAbrirMenuLateral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (!painelMostrando) { // ABRINDO O PAINEL
					
					
					painelMostrando = true;
					Thread t = new Thread(new Runnable() {

						@Override
						public void run() {
							int XAtual = sp.getX(); // Local atual do view
							int XFinal = 0; // O Destino final que deve estar.
//							int XPainel = painelEmail.getX();
							int posXButton = btAbrirMenuLateral.getX();
							barraLateral.setVisible(true);
							while (XAtual <= XFinal) { // Enquanto o atual n
														// chegar no final
								try {
									System.out.println(posXButton+"\n"+XAtual + "\n ---");
									XAtual++;
									posXButton++;

									sp.setLocation(XAtual, sp.getY());
									barraLateral.setLocation(XAtual, barraLateral.getY());
									btAbrirMenuLateral.setLocation(posXButton,
											btAbrirMenuLateral.getY());
									Thread.sleep(2);
								} catch (InterruptedException ex) {
									Logger.getLogger(Principal.class.getName())
											.log(Level.SEVERE, null, ex);
								}
							}
							if(isPainelEmailShow){
								barraLateral.add(painelEmail);
							}

							btAbrirMenuLateral.setText("<<");
						}
					});
					t.start();
					
				} else { // FECHANDO
//					jtreeAtalhos.clearSelection();
//					sp.removeAll();
//					painelEmail.removeAll();
//					barraLateral.remove(painelEmail);
//					btAbrirMenuLateral.setLocation(btAbrirMenuLateral.getX() - WIDTH_TAMANHO, btAbrirMenuLateral.getY());
//					barraLateral.setSize(barraLateral.getWidth() -WIDTH_TAMANHO, barraLateral.getHeight()); //Diminuo o mesmo tamanho
					barraLateral.remove(painelEmail);
					barraLateral.revalidate();
					
					painelMostrando = false;
					Thread t = new Thread(new Runnable() {

						@Override
						public void run() {
//							int XAtual = sp.getX(); // Local atual do view
//							int XFinal = -(barraLateral.getWidth()); // Local
//																		// atual
//																		// do
//																		// Container
//																		// Barra
//							int posXButton = btAbrirMenuLateral.getX(); // Pego
//																		// a
//																		// posiÁ„o
//																		// do
//																		// button
//																		// para
//																		// poder
//																		// move-lo
							int XAtual = sp.getX(); // Local atual do view
							int XFinal = -(barraLateral.getWidth()); // O Destino final que deve estar.
							int posXButton = barraLateral.getWidth();
							

							while (XAtual >= XFinal) { // Enquanto o atual n
														// chegar no final
								try {
									XAtual--;
									posXButton--;
									
									sp.setLocation(XAtual, sp.getY());
									barraLateral.setLocation(XAtual, barraLateral.getY());
									btAbrirMenuLateral.setLocation(posXButton,
											btAbrirMenuLateral.getY());
									Thread.sleep(2);

								} catch (InterruptedException ex) {
									Logger.getLogger(Principal.class.getName())
											.log(Level.SEVERE, null, ex);
								}
							}
							btAbrirMenuLateral.setLocation(posXButton + 5,
									btAbrirMenuLateral.getY());
							barraLateral.setVisible(false);
							btAbrirMenuLateral.setText(">>");
						}
					});
					t.start();
				}
			}
		});

		jtreeAtalhos.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent evt) {
				System.out.println(evt.getNewLeadSelectionPath());
//				final int WIDTH_TAMANHO = 300;
				if ("[Inicio, E-mail, INBOX]".equalsIgnoreCase(evt
						.getNewLeadSelectionPath().toString())) {
					// java.awt.Dimension d = barraLateral.getSize();
					// Point p = barraLateral.getLocation();
					// sp.setSize(d.width+100, d.height);
					// sp.setLocation(p.x+100,p.y);
					// barraLateral.setLocation(p.x+100, p.y);
					
					
					
					
					

//					painelEmail.removeAll();
//					painelEmail.setLayout(new GridLayout(1, 1));

					String[] itens = evt.getNewLeadSelectionPath().toString()
							.split(",");
					String nameFolder = itens[itens.length - 1]; //Pego o ultimo nome da arvore de arquivos
					nameFolder = nameFolder.replace(']', ' '); //e retiro os caracteres
					nameFolder = nameFolder.replace(" ", "");
					final String name = nameFolder;
					System.out.println(nameFolder);
					List<String> ls = new ArrayList<String>();
					new Thread(() ->{
						boolean sucess = false;
						List<String>temp = new ArrayList<String>();
						while(!sucess){
							try{
							temp = email.listarViewEmails(name);
							sucess = true;
							}catch(Exception e){System.out.println("erro,trying again");}
						}
						barraLateral.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
						jtreeAtalhos.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
						
						
						jtreeAtalhos.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						barraLateral.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						jTableEmails.setModel(new ModelTableEmail(temp));
						
					}).start();
					
					
					
					
//					JScrollPane spe = new JScrollPane(jTableEmails);

//					painelEmail.add(spe);
					if(!isPainelEmailShow){
						
						isPainelEmailShow = true;
					java.awt.Dimension d2 = barraLateral.getSize();
					barraLateral.setSize(d2.width + WIDTH_TAMANHO, d2.height);
					jTableEmails.setRowHeight(20);
					btAbrirMenuLateral.setLocation(btAbrirMenuLateral.getX()
							+ WIDTH_TAMANHO, btAbrirMenuLateral.getY());
					painelEmail.setSize(WIDTH_TAMANHO, barraLateral.getHeight());
					painelEmail.setLocation(jtreeAtalhos.getWidth() + 10, 0);
					painelEmail.setBackground(Color.white);
					barraLateral.add(painelEmail);
					minhaFrame.revalidate();
					minhaFrame.repaint();

					}
					

					
				}

			}
		});

		itSair.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(rootPane, "deseja sair?",
						"Confirmar saida?", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE) == 0) {
					System.exit(0);
				}
			}
		});

		itCadastroCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!isFrameClienteOpen) {
					isFrameClienteOpen = true;
					getContentPane().add(new FormCadastroCliente());
				}
			}
		});
		itCadastroFuncionario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!isFrameInstrutorOpen) {
					isFrameInstrutorOpen = true; // difinindo que ja tem uma
													// janela aberta
					getContentPane().add(new FormCadastroInstrutor());
				}

			}
		});

		itAgendamento.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!isFrameAgendamento) { // Se n√£o houver uma instancia
											// crida, eu crio uma nova.
					isFrameAgendamento = true; // definindo que ja tem uma
												// janela aberta
					getContentPane().add(new FormAgendamento()); // Adiciono a
																	// Internal
																	// Frame na
																	// tela
				}
			}
		});

		itCadastroCarro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!isFrameCarro) {
					isFrameCarro = true;
					getContentPane().add(new FormCadastroCarro());
				}
			}
		});

		itCadastroPacote.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!isFrameCadastroPacote) { // defino se n√£o houver uma
												// instancia j√° criada, eu
												// posso criar
					isFrameCadastroPacote = true; // difinindo que ja tem uma
													// janela aberta
					getContentPane().add(new FormCadastroPacote());
				}
			}
		});

		jTableEmails.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if(e.getClickCount() == 2){
					int index = jTableEmails.getSelectedRow();
					System.out.println(index);
					index = ((ModelTableEmail)jTableEmails.getModel()).getIdEmail(index);
					System.out.println(index);
					
					mensagem = email.getEmail("INBOX", (index*-1));
					((ModelTableEmail)jTableEmails.getModel()).marcaComoLida(jTableEmails.getSelectedRow());
					getContentPane().add(new ViewEmail(mensagem));
					System.out.println("cliq");
				
				}
			}
		});

		this.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
			}

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

			@Override
			public void windowClosed(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowActivated(WindowEvent e) {
				painelCalendario.rederenzarTela();
			}

			@Override
			public void windowDeactivated(WindowEvent e) {

			}
		});
	}

	class MeuModeloTree extends DefaultTreeCellRenderer {

		@Override
		public Component getTreeCellRendererComponent(JTree tree, Object value,
				boolean sel, boolean expanded, boolean leaf, int row,
				boolean hasFocus) {
			super.getTreeCellRendererComponent(tree, value, sel, expanded,
					leaf, row, hasFocus); // To change body of generated
											// methods, choose Tools |
											// Templates.
											// if(value != null){
			DefaultMutableTreeNode no = (DefaultMutableTreeNode) value;
			// if(no.toString() != null ){
			String texto = no.getUserObject().toString();
			System.out.println();
			if (texto.equals("Inicio")) {
				ImageIcon img = new ImageIcon(Principal.class.getResource(
						"/Resources/icons/").getPath()
						+ "inicio.png");
				setIcon(img);
				setToolTipText(texto);

			} else if (texto.contains("E-mail")) {
				ImageIcon img = new ImageIcon(getClass().getResource(
						"/Resources/icons/").getPath()
						+ "email.png");
				setIcon(img);
				setToolTipText(texto);
			}
			if (texto.contains("Favoritos")) {
				ImageIcon img = new ImageIcon(getClass().getResource(
						"/Resources/icons/").getPath()
						+ texto + ".png");
				setIcon(img);
				setToolTipText(texto);
			}
			if (texto.contains("Tarefas")) {
				ImageIcon img = new ImageIcon(getClass().getResource(
						"/Resources/icons/").getPath()
						+ texto + ".png");
				setIcon(img);
				setToolTipText(texto);
			} else {
			}

//			 }
//			 }
			return this;

		}

	}

	class CheckNewMessages implements Runnable {
		private EmailController controllerEmail;
		private JTable jtable;
		private EmailController email;
		private JTree jtree;
		private JPanel painel;

		public CheckNewMessages(JTable Table, JTree jtreeAtalhos, JPanel painel,EmailController e) {
			this.painel = painel;
			this.jtable = Table;
			this.jtree = jtreeAtalhos;
			this.email = e;
			

		}


		@Override
		public void run() {
			try {
				List<String>lsEmailsAtualizada = new ArrayList<String>();
				int emailsNaCaixal = email.countUnredMessages("INBOX").size(); //Verifico a quantidade total de emails,
				int cont = 0 ;
				while (true) {
					
					List<MensagemEmail>lsTemp = email.countUnredMessages("INBOX");
					
					
					System.out.println(emailsNaCaixal+"/"+lsEmailsAtualizada.size());
					if (lsTemp.size() > emailsNaCaixal) {	//Se houver um email novo
						lsEmailsAtualizada = email.listarViewEmails("INBOX"); // e Carrego os e-mails na caixa principal
						jtable.setModel(new ModelTableEmail(lsEmailsAtualizada)); // e atualizo a minha lista
					}
			
					emailsNaCaixal  = lsTemp.size();
					
					Thread.sleep(1000 * 60); // Espera 1 minutos para atualizar de novo
												// de novo
					System.out.println(cont);
//					Thread.sleep(1*1000);
					System.out.println("2");
				}
				
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
			}
			
		}

	}
}
