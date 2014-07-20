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
import java.io.FileNotFoundException;
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

import org.w3c.dom.ls.LSException;

import antlr.TreeParserSharedInputState;

import com.itextpdf.text.Font;
import com.itextpdf.text.log.SysoCounter;
import com.sun.media.rtsp.protocol.PauseMessage;

import Controller.ConfigController;
import Controller.CriptografiaConfigEmail;
import Controller.EmailControllerV2;
import Model.Login;
import Model.MensagemEmail;
import Model.ModelTableEmail;
import Model.UsuarioEmail;

public class Principal extends JFrame {

	private JMenuBar menuBarra;
	private JMenu menuArquivo, menuAgendamento, menuRelatorio;
	private JMenuItem itSair, itCadastroCliente, itCadastroFuncionario,
			itCalendario, itAgendamento, itCadastroPacote, itCadastroCarro,
			itConfiguraEmail, itFazerLogoff;
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

	protected EmailControllerV2 email;
	protected JPanel painelEmail;
	private static JTable jTableEmails;
	private List<String> listaEmails;
	private HashMap<String, List<String>> mapEmails;
	protected Login loginUser;
	private boolean isPainelEmailShow = false;
	private MensagemEmail mensagem;

	private Map<String, List<MensagemEmail>> arquivosEmail;

	private Thread gerenciaEmal;
	private final int WIDTH_TAMANHO = 301;

	private boolean hasEmailReady = false;
	private JButton btRefreshItens;

	public static boolean finished = false;
	public static boolean carregado;
	protected static boolean isFrameInstrutorOpen, isFrameClienteOpen,
			isFrameCadastroPacote, isFrameAgendamento, isFrameCarro,
			isViewConfiguraEmail;

	public Principal(Login usuario) {
		try {

			minhaFrame = this;
			inicializaComponentes();

			UsuarioEmail user = null;

			File fileConfigEmail = new File(getClass().getResource(
					"/Resources/FilesConfig").getPath());
			String nameFolder = usuario.getUsuario() + "@emailConfig";
			user = new CriptografiaConfigEmail().unCrypt(fileConfigEmail,
					nameFolder);
			if (user != null) {

				JButton bt = btRefreshItens;/*
											 * Crio um sombra da instancia para
											 * manipular na tread
											 */
				UsuarioEmail u = user;

				new Thread(
						// Uma thread para ser feita a autentiicação do e-mail
						// para n atrapalhar o processo central do app
						() -> {

							bt.setVisible(true);

							try{
							this.email = new EmailControllerV2(u);

							DefaultMutableTreeNode root = new DefaultMutableTreeNode(
									"Inicio");

							DefaultMutableTreeNode favItens = new DefaultMutableTreeNode(
									"Favoritos");
							root.add(favItens);

							DefaultMutableTreeNode tarefaItens = new DefaultMutableTreeNode(
									"Tarefas");
							root.add(tarefaItens);

							DefaultMutableTreeNode dmEmail = new DefaultMutableTreeNode(
									"E-mail"); // Recrio todo a JTree com os
												// itens

							List<String> folders = email.getFolders();
							folders.forEach(fo -> {
								DefaultMutableTreeNode dm = new DefaultMutableTreeNode(
										fo);
								dmEmail.add(dm);
							});

							root.add(dmEmail);
							DefaultTreeModel model = new DefaultTreeModel(root);

							jtreeAtalhos.setModel(model);

							bt.setVisible(false);
							minhaFrame.revalidate(); // Atualizo a minha frame
							minhaFrame.repaint();
							}catch(Exception e){
								e.printStackTrace();
							}
							
							System.out.println("repaint na tela");
							gerenciaEmal = new Thread(new CheckNewMessages(
									jTableEmails, jtreeAtalhos, painelEmail,
									email));
							gerenciaEmal.setDaemon(true);
							gerenciaEmal.start();
							
						}).start();

			}
			this.loginUser = usuario;

			definirEventos();

			sp.setViewportView(jtreeAtalhos);

			isFrameClienteOpen = isFrameInstrutorOpen = isFrameCadastroPacote = isFrameAgendamento = isFrameCarro = isViewConfiguraEmail = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void inicializaComponentes() throws IOException {
		// Definindo o layout e a imagem de fundo
		java.awt.Font fonteP = ConfigController.definePrincipalFont(15f,
				Font.NORMAL);
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
		menuBarra.setFont(fonteP);
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
		itConfiguraEmail = new JMenuItem("Configurar E-mail");
		itFazerLogoff = new JMenuItem("Fazer Logoff");
		//
		menuArquivo.add(itCadastroCliente);
		menuArquivo.add(itCadastroFuncionario);
		menuArquivo.add(itCadastroPacote);
		menuArquivo.add(itCadastroCarro);
		menuArquivo.add(itConfiguraEmail);

		menuArquivo.add(itFazerLogoff);
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

		jtreeAtalhos = new JTree(root);
		jtreeAtalhos.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		jtreeAtalhos.setCellRenderer(new MeuModeloTree());
		jtreeAtalhos.expandRow(60);
		jtreeAtalhos.setFont(fonteP);

		painelEmail = new JPanel(); // INICANDO O PAINEL
		painelEmail.setLayout(new GridLayout(1, 1));

		barraLateral = new JToolBar();
		barraLateral.setEnabled(false);
		barraLateral.setLayout(null);
		barraLateral.setSize(170, minhaFrame.getHeight() - 60);
		barraLateral.setLocation(-(barraLateral.getWidth()), 0);
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
		painelCalendario = new PainelCalendarioAgendamento(p);/*
															 * Definindo ele no
															 * canto esquerdo da
															 * tela
															 */
		painelCalendario.setFont(fonteP);

		jTableEmails = new JTable(new ModelTableEmail(new ArrayList<String>()));
		jTableEmails.setFont(fonteP);
		jTableEmails.setRowHeight(20);
		JScrollPane spe = new JScrollPane(jTableEmails);

		painelEmail.add(spe);

		add(painelCalendario);

		btRefreshItens = new JButton(new ImageIcon(getClass().getResource(
				"/Resources/icons").getPath()
				+ "/load.gif"));
		btRefreshItens.setContentAreaFilled(false);
		btRefreshItens.setSize(40, 40);
		btRefreshItens.setLocation(
				(btAbrirMenuLateral.getX() + btAbrirMenuLateral.getWidth()),
				btAbrirMenuLateral.getY());
		btRefreshItens.setToolTipText("Atualizando");
		btRefreshItens.setVisible(false);
		add(btRefreshItens);

		setJMenuBar(menuBarra);
		setLocationRelativeTo(null);
		setTitle("Karol Habilitados v 1.3.1");
		// setResizable(false);
		setVisible(true);

	}

	//
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
							// int XPainel = painelEmail.getX();
							int posXButton = btAbrirMenuLateral.getX();
							int posXBtRefresh = btRefreshItens.getX();
							barraLateral.setVisible(true);
							while (XAtual <= XFinal) { // Enquanto o atual n
														// chegar no final
								try {

									XAtual++;
									posXButton++;
									posXBtRefresh++;

									btRefreshItens.setLocation(posXBtRefresh,
											btRefreshItens.getY());
									sp.setLocation(XAtual, sp.getY());
									barraLateral.setLocation(XAtual,
											barraLateral.getY());
									btAbrirMenuLateral.setLocation(posXButton,
											btAbrirMenuLateral.getY());
									Thread.sleep(2);
								} catch (InterruptedException ex) {
									Logger.getLogger(Principal.class.getName())
											.log(Level.SEVERE, null, ex);
								}
							}
							if (isPainelEmailShow) {
								barraLateral.add(painelEmail);
							}

							btAbrirMenuLateral.setText("<<");
						}
					});
					t.start();

				} else { // FECHANDO

					barraLateral.remove(painelEmail);
					barraLateral.revalidate();

					painelMostrando = false;
					Thread t = new Thread(new Runnable() {

						@Override
						public void run() {

							int XAtual = sp.getX(); // Local atual do view
							int XFinal = -(barraLateral.getWidth()); // O
																		// Destino
																		// final
																		// que
																		// deve
																		// estar.
							int posXButton = barraLateral.getWidth();
							int posXBtRefresh = btRefreshItens.getX();

							while (XAtual >= XFinal) { // Enquanto o atual n
														// chegar no final
								try {
									XAtual--;
									posXButton--;
									posXBtRefresh--;

									btRefreshItens.setLocation(posXBtRefresh,
											btRefreshItens.getY());
									sp.setLocation(XAtual, sp.getY());
									barraLateral.setLocation(XAtual,
											barraLateral.getY());
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
				if ("[Inicio, E-mail, INBOX]".equalsIgnoreCase(evt // Testa se é
																	// pasta
																	// INBOX
						.getNewLeadSelectionPath().toString())) {

					String[] itens = evt.getNewLeadSelectionPath().toString()
							.split(",");
					String nameFolder = itens[itens.length - 1]; /*
																 * Pego o ultimo
																 * nome da
																 * arvore de
																 * arquivos
																 */

					nameFolder = nameFolder.replace(']', ' '); /*
																 * e retiro os
																 * caracteres
																 */
					nameFolder = nameFolder.replace(" ", "");
					final String name = nameFolder;
					System.out.println(nameFolder);
					Map<String, List<MensagemEmail>> map = new HashMap<String, List<MensagemEmail>>();
					List<String> ls = new ArrayList<String>();
					System.out.println(this.getClass()
							.getResource("/Resources/FilesConfig").getPath());

					File arqEmail = new File(this.getClass()
							.getResource("/Resources/FilesConfig").getPath()
							+ "/"
							+ loginUser.getUsuario()
							+ "@itensEmail.ser");
					
					System.out.println("procurando o bang serializado");
					if (arqEmail.exists()) { // Verifica se ja existe um arquivo
												// no diretorio com os itens do
												// email
						System.out.println("Acho o bang serializado");
						FileInputStream input;
						
						try {
							input = new FileInputStream(arqEmail);
							ObjectInputStream obj = new ObjectInputStream(input);
							map = (Map<String, List<MensagemEmail>>) obj
									.readObject(); // Se existir eu carrego para
													// a memoria
							arquivosEmail = map;
							List<MensagemEmail> lsEmails = map.get(nameFolder);
							for (int i = lsEmails.size() - 1; i >= 0; i--) { // Montando
																				// a
																				// vizualização
																				// dos
																				// itens
																				// de
																				// Email
								MensagemEmail e = lsEmails.get(i);
								String from = e.getFrom();
								String assunto = e.getSubject();
								String vizualizacao;


								if (e.isUnread()) {
									vizualizacao = "<html><b>De: " + from
											+ "  - Assunto: " + assunto + " - "// +
																				// dataRecebida
											+ "</b></html>";
								} else {
									vizualizacao = "De: " + from
											+ "  - Assunto: " + assunto + " - ";// +
																				// dataRecebida;
								}
								ls.add(vizualizacao);
							}
						} catch (IOException | ClassNotFoundException e) {

							e.printStackTrace();
						}
						jTableEmails.setModel(new ModelTableEmail(ls));
						//
					}

					JButton bt = btRefreshItens;/*
												 * Crio um sombra da instancia
												 * para manipular na tread
												 */

					new Thread(
							() -> { // THREAD PARA BAIXAR OS E-MAILS DO SERVIDOR

								bt.setVisible(true);
								System.out.println("go thread");
								try {

									List<String> temp = new ArrayList<String>();
									Map<String,List<MensagemEmail>> tempMap = new HashMap<String,List<MensagemEmail>>();
									
									System.out
											.println("Começando a carregar os emails");

									int index = 0;
									
									File arqTem = new File(getClass().getResource("/Resources/FilesConfig")
											.getPath()+"/"+email.getUser()+"@INBOX.temp");
									
									File arqMap = new File(getClass().getResource("/Resources/FilesConfig")
											.getPath()+"/"+email.getUser()+"@itensMap.temp");
									 
									System.out.println(arqTem+"");
									List<MensagemEmail>lst = new ArrayList<MensagemEmail>();
									carregado = false;
									new Thread(()->{
										try{
											while(!carregado){
											File arqList = new File(getClass().getResource("/Resources/FilesConfig")
													.getPath()+"/"+email.getUser()+"@"+name+".temp");
											if(arqList.exists()){
												FileInputStream in = new FileInputStream(arqList);
												ObjectInputStream os = new ObjectInputStream(in);
												List<MensagemEmail>l = (List<MensagemEmail>)os.readObject();
												List<String> lsItens = new ArrayList<String>();
												l.forEach(em ->{
													

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
												Principal.jTableEmails.setModel(new ModelTableEmail(lsItens));	
											}
											
											Thread.sleep(1000*1);
											}
										}catch(Exception e){
											e.printStackTrace();
										}
									}).start();
									
									if(arqTem.exists()){
										System.out.println("list existe");
										FileInputStream in = new FileInputStream(arqTem);
										ObjectInputStream os = new ObjectInputStream(in);
										lst = (List<MensagemEmail>) os.readObject();
										jTableEmails.setModel(new ModelTableEmail(email.getListViewItensEmail(lst)));
										System.out.println(lst.size());
										index = lst.size();
										email.loadEmails(index,name,lst);
									}else {
										System.out.println("carregando tudo");
										
										jTableEmails.setModel(new ModelTableEmail(email.getListViewItensEmail("INBOX")));
										email.loadEmails();
									}
									
									if(arqMap.exists()){
										FileInputStream in = new FileInputStream(arqTem);
										ObjectInputStream os = new ObjectInputStream(in);
										tempMap = (Map<String,List<MensagemEmail>>) os.readObject();

									}
									
									System.out.println("vai tentar");
									Map<String, List<MensagemEmail>> maps = email.getMapArquivosEmail();
									
									System.out.println("volto");
									
									arquivosEmail = maps;
									List<String>t = email.getListViewItensEmail(maps.get("INBOX"));
									jTableEmails.setModel(new ModelTableEmail(t));
									carregado = true;
									
									
									List<MensagemEmail> listEmails = maps
											.get("INBOX");
									System.out.println("é nulo  a list?");
//									if (listEmails != null) {
//										System.out.println("Não é :)");
//										for (int i = listEmails.size() - 1; i >= 0; i--) {
//											System.out.println(i+"/" + listEmails.size());
//											MensagemEmail m = listEmails.get(i);
//											String from;
//											String subject;
//											String body;
//											String dateReceived;
//
//											from = subject = body = dateReceived = "";// iniciando
//																						// as
//																						// variaveis
//
//											from = m.getFrom();
//
//											subject = m.getSubject();
//											// dateReceived = new
//											// SimpleDateFormat(
//											// "dd/MM/yyyy - hh:mm").format(m
//											// .getDataRecebida());
//
//											body = m.getTexto();
//
//											String viewItensEmail; // String que
//																	// de como
//																	// vai ficar
//																	// o item
//																	// do e-mail
//
//											if (m.isUnread()) { // Se n estiver
//																// lida, eu
//																// deixo o item
//																// em
//																// destaque
//												viewItensEmail = "<html><b>De: "
//														+ from
//														+ "  - Assunto: "
//														+ subject + " - " // +
//																			// dateReceived
//														+ "</b></html>";
//											} else {
//												viewItensEmail = "De: " + from
//														+ "  - Assunto: "
//														+ subject + " - "; // +
//																			// dateReceived;
//											}
//											temp.add(viewItensEmail);
//										}
//										
//
//										jTableEmails
//												.setModel(new ModelTableEmail(
//														temp));
//
//										FileOutputStream ou;
//
//										ou = new FileOutputStream(arqEmail); /* e
//																			 / então
//																			   subistituo
//																			   o
//																			   arquivo
//																			   com
//																			   o
//																			   atual
//																			 */
//
//										ObjectOutputStream os = new ObjectOutputStream(
//												ou);
//										os.writeObject(maps);
//										os.flush();
//										os.close(); // TODO APLICAR CRIPTOGRAFIA
//									}
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} finally {
									bt.setVisible(false);
								}

							}).start();

					if (!isPainelEmailShow) {

						isPainelEmailShow = true;
						java.awt.Dimension d2 = barraLateral.getSize();
						barraLateral.setSize(d2.width + WIDTH_TAMANHO,
								d2.height);
						jTableEmails.setRowHeight(20);
						btAbrirMenuLateral.setLocation(
								btAbrirMenuLateral.getX() + WIDTH_TAMANHO,
								btAbrirMenuLateral.getY());
						btRefreshItens.setLocation(btAbrirMenuLateral.getX()
								+ btAbrirMenuLateral.getWidth(),
								btAbrirMenuLateral.getY());
						painelEmail.setSize(WIDTH_TAMANHO,
								barraLateral.getHeight());
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
				if (!isFrameAgendamento) { // Se nÃ£o houver uma instancia
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
				if (!isFrameCadastroPacote) { // defino se nÃ£o houver uma
												// instancia jÃ¡ criada, eu
												// posso criar
					isFrameCadastroPacote = true; // difinindo que ja tem uma
													// janela aberta
					getContentPane().add(new FormCadastroPacote());
				}
			}
		});

		itConfiguraEmail.addActionListener(e -> {
			if (!isViewConfiguraEmail) {
				isViewConfiguraEmail = true;
				getContentPane().add(new ViewConfigEmail(loginUser));
			}
		});

		itFazerLogoff.addActionListener(e -> {
			this.dispose();
//			Thread.currentThread().stop();
			
			new TelaLogin();
			minhaFrame = null;
			

		});

		jTableEmails.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if (e.getClickCount() == 2) {
					try {
						int index = jTableEmails.getSelectedRow();
						System.out.println("INDEX PRINCIPAL - " + index);
						index = ((ModelTableEmail) jTableEmails.getModel())
								.getIdEmail(index);
						index *= -1;
						System.out.println("INDEX PELA TABLE -" + index);
						// arquivosEmail = email.getEmails();
						List<MensagemEmail> l = arquivosEmail.get("INBOX");
						mensagem = l.get(index - 1); // TODO

						// mensagem = email.getEmail("INBOX", (index*-1));

						((ModelTableEmail) jTableEmails.getModel())
								.marcaComoLida(jTableEmails.getSelectedRow()); // Marco
																				// como
																				// lida
																				// visualmente,
																				// enquanto
																				// a
																				// thread
																				// faz
																				// isso
																				// lá
																				// no
																				// servidor
						getContentPane().add(new ViewEmail(mensagem, email));
						email.marcaComoLida(index - 1, "INBOX");
						System.out.println("cliq");
					} catch (Exception exc) {
						exc.printStackTrace();
					}

				}
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

			// }
			// }
			return this;

		}

	}

	class CheckNewMessages implements Runnable {

		private JTable jtable;
		private EmailControllerV2 email;
		private JTree jtree;
		private JPanel painel;

		public CheckNewMessages(JTable Table, JTree jtreeAtalhos,
				JPanel painel, EmailControllerV2 e) {
			this.painel = painel;
			this.jtable = Table;
			this.jtree = jtreeAtalhos;
			this.email = e;

		}

		@Override
		public void run() {
			try {
				List<String> lsEmailsAtualizada = new ArrayList<String>();
				int emailsNaCaixal = email.getCountNovosEmails(); // Verifico
																				// a
																				// quantidade
																				// total
																				// de
																				// emails,
				int cont = 0;
				while (true) {

//					List<MensagemEmail> lsTemp = email.getItensEmail("INBOX");

					
					int temp = email.getCountNovosEmails();
					// System.out.println(emailsNaCaixal + "/"
					// + lsEmailsAtualizada.size());
					if (temp > emailsNaCaixal) { // Se
																			// houver
																			// um
																			// email
						// novo
						lsEmailsAtualizada = email.getListViewItensEmail("INBOX"); // e
																				// principal
						arquivosEmail = email.getMapArquivosEmail();
						jtable.setModel(new ModelTableEmail(lsEmailsAtualizada)); // e
																					// atualizo
																					// a
																					// minha
																					// lista]

						// Depois que atualizar, entao eu pego o map, e
						// serializo o arquivo, para atualizar
						File arqEmail = new File(this.getClass()
								.getResource("/Resources/FilesConfig")
								.getPath()
								+ "/"
								+ loginUser.getUsuario()
								+ "@itens-emails.ser");

						ObjectOutputStream os;
						FileOutputStream ou;
						try {
							ou = new FileOutputStream(arqEmail);
							os = new ObjectOutputStream(ou);
							os.writeObject(arquivosEmail);
							os.flush();
							os.close(); // TODO APLICAR CRIPTOGRAFIA
						} catch (IOException e) {

							e.printStackTrace(); // TODO APLICAR LOG AQUI
						}

					}

					emailsNaCaixal = temp;

					Thread.sleep(1000 * 60); // Espera 1 minutos para atualizar
												// de novo
												// de novo
					System.out.println(cont);
					// Thread.sleep(1*1000);
					System.out.println("2");
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				 e.printStackTrace();
			}

		}

	}
}
