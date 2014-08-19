package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;

import model.Login;
import model.threads.ThreadConfigEmail;
import model.threads.ThreadNotificationEmail;
import controller.ConfigController;
import controller.EmailControllerV3;

public class Principal extends JFrame {

	private JMenuBar menuBarra;
	private JMenu menuArquivo, menuAgendamento, menuRelatorio;
	private JMenuItem itSair, itCadastroCliente, itCadastroFuncionario,
			itAgendamento, itCadastroPacote, itCadastroCarro, itConfiguraEmail,
			itFazerLogoff;

	private static boolean painelMostrando = false;
	private JToolBar barraLateral;
	private JScrollPane sp;
	private PainelCalendarioAgendamento painelCalendario;
	protected JPanel pnMenuSuperior;

	// private int POSXButoon;
	protected static JTree jtreeAtalhos;

	protected static EmailControllerV3 email;
	protected static Login loginUser;

	protected static JTextField txtBuscaEmail;

	protected static JButton btRefreshItens;
	protected static JButton btAbrirMenuLateral;
	protected static JFrame minhaFrame; // Frame para setar a dialogs

	private JPopupMenu popup;
	private JMenuItem itMnAdicionar;
	protected static JPanel painelNotification;

	// Thread
	private static ThreadNotificationEmail tNotification;
	private static ThreadConfigEmail tConfigEmail;
	// Telas
	private FormAgendamento formAgendamento;
	private FormCadastroCarro formCarro;
	private FormCadastroCliente formCliente;
	private FormCadastroInstrutor formInstrutor;
	private FormCadastroServico formServico;
	private static FormConfigEmail formEmail;

	private int xMouse, yMouse;

	public static boolean finished = false;
	public static boolean carregado;
	protected static boolean isFrameInstrutorOpen, isFrameClienteOpen,
			isFrameCadastroPacote, isFrameAgendamento, isFrameCarro,
			isViewConfiguraEmail, isPainelEmailShow, isMenuSuperiorShow,
			isFrameConfigEmail, isFrameServico;

	public Principal() {

		try {
			isMenuSuperiorShow = true;
			minhaFrame = this;
			inicializaComponentes();
			definirEventos();

			tNotification = new ThreadNotificationEmail(painelNotification,
					minhaFrame);
			tConfigEmail = new ThreadConfigEmail();

			// Inicando variaveis booleanas
			isFrameClienteOpen = isFrameInstrutorOpen = isFrameCadastroPacote = isFrameAgendamento = isFrameCarro = isViewConfiguraEmail = isFrameConfigEmail = isFrameServico = false;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(
							null,
							"Houve um problema na constru��o da tela,a aplica��o ser� finalizada...\n "
							+ "caso o erro persista favor entrar em contato com o administrador do sitema");

			e.printStackTrace();
			System.exit(0);
		}
	}

	public static Login getLoginUser() {
		return loginUser;
	}

	public static void setLoginUser(Login loginUser) {
		Principal.loginUser = loginUser;
		tConfigEmail.setUser(loginUser);
		tConfigEmail.start();
		formEmail = new FormConfigEmail(loginUser);
		formEmail.setVisible(false);
		minhaFrame.getContentPane().add(formEmail);

	}

	public void inicializaComponentes() throws IOException {
		// Definindo o layout e a imagem de fundo
		java.awt.Font fonteP = ConfigController.definePrincipalFont(15f,
				Font.PLAIN);
		setLayout(null);
		tNotification = new ThreadNotificationEmail(painelNotification,
				minhaFrame);
		setContentPane(new DesktopPaneCustom());
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
		itAgendamento = new JMenuItem("Agendar Aula");
		itCadastroPacote = new JMenuItem("Cadastro Servico");
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

		barraLateral = new JToolBar();
		barraLateral.setEnabled(false);
		barraLateral.setLayout(null);
		barraLateral.setSize(170, minhaFrame.getHeight() - 490);
		barraLateral.setLocation(-(barraLateral.getWidth()), 120);
		barraLateral.setVisible(false);

		sp = new JScrollPane(jtreeAtalhos);
		sp.setBounds(barraLateral.getX(), 0, 170, barraLateral.getHeight());
		barraLateral.add(sp);

		add(barraLateral);

		btAbrirMenuLateral = new JButton(">>");
		btAbrirMenuLateral.setFocusCycleRoot(true);
		btAbrirMenuLateral.setBounds(5, 130, 45, 30);
		add(btAbrirMenuLateral);

		java.awt.Point p = new Point(minhaFrame.getWidth() - 405, 100);
		painelCalendario = new PainelCalendarioAgendamento(p);/*
															 * Definindo ele no
															 * canto esquerdo da
															 * tela
															 */
		painelCalendario.setFont(fonteP);

		add(painelCalendario);

		btRefreshItens = new JButton(new ImageIcon(getClass().getResource(
				"/resources/icons").getPath()
				+ "/load.gif"));
		btRefreshItens.setContentAreaFilled(false);
		btRefreshItens.setSize(40, 40);
		btRefreshItens.setLocation(
				(btAbrirMenuLateral.getX() + btAbrirMenuLateral.getWidth()),
				btAbrirMenuLateral.getY());
		btRefreshItens.setToolTipText("Atualizando");
		btRefreshItens.setVisible(false);
		add(btRefreshItens);

		painelNotification = new JPanel();
		painelNotification.setSize(300, 120);
		int y = 120 + this.getHeight() - 10;
		painelNotification.setLocation(
				this.getWidth() - painelNotification.getWidth(), y);
		painelNotification.setBackground(new Color(201, 229, 0, 160));
		JLabel lb = new JLabel("Voc� recebeu uma nova mensagem!");
		painelNotification.add(lb);
		add(painelNotification);

		/**
		 * Criando as telas
		 */
		formAgendamento = new FormAgendamento();
		formAgendamento.setVisible(false);
		getContentPane().add(formAgendamento);

		formCarro = new FormCadastroCarro();
		formCarro.setVisible(false);
		getContentPane().add(formCarro);

		formCliente = new FormCadastroCliente();
		formCliente.setVisible(false);
		getContentPane().add(formCliente);

		formInstrutor = new FormCadastroInstrutor();
		formInstrutor.setVisible(false);
		getContentPane().add(formInstrutor);

		formServico = new FormCadastroServico();
		formServico.setVisible(false);
		getContentPane().add(formServico);

		popup = new JPopupMenu("Menu");
		itMnAdicionar = new JMenuItem("Adicionar Atalho");
		popup.add(itMnAdicionar);
		add(popup);

		// getGlassPane().setVisible(true);
		setJMenuBar(menuBarra);
		setLocationRelativeTo(null);
		setTitle("Karol Habilitados v 1.3.1");

	}

	public static void showNotication() {
		tNotification.start();
	}

	//
	public void definirEventos() {

		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				if (e.getButton() == java.awt.event.MouseEvent.BUTTON3) {
					popup.show(minhaFrame, e.getX(), e.getY());
					xMouse = e.getX();
					yMouse = e.getY();
				}
			};
		});

		itMnAdicionar.addActionListener(e -> {
			PainelIcon icon = new PainelIcon(null);
			icon.setLocation(this.xMouse, this.yMouse);

			add(icon);
		});

		btAbrirMenuLateral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				long time = (1 / 2) + (2 / 5);

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
									Thread.sleep(time);
								} catch (InterruptedException ex) {
									Logger.getLogger(Principal.class.getName())
											.log(Level.SEVERE, null, ex);
								}
							}

							btAbrirMenuLateral.setText("<<");
						}
					});
					t.start();

				} else { // FECHANDO

					// barraLateral.revalidate();

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
									Thread.sleep(time);

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
					try {
						isFrameClienteOpen = true;
						JInternalFrame internalFrame = new FormCadastroCliente();
						getContentPane().add(internalFrame);
						internalFrame.setSelected(true);
					} catch (PropertyVetoException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		itCadastroFuncionario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!Principal.isFrameInstrutorOpen) {
					try {
						Principal.isFrameInstrutorOpen = true; // difinindo que
																// ja tem uma
						// janela aberta
						JInternalFrame internalFrame = new FormCadastroInstrutor();
						getContentPane().add(internalFrame);
						internalFrame.setSelected(true);
					} catch (PropertyVetoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});

		itAgendamento.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		itCadastroCarro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!isFrameCarro) {
					try {
						isFrameCarro = true;
						JInternalFrame internalFrame = new FormCadastroCarro();
						getContentPane().add(internalFrame);
						internalFrame.setSelected(true);
					} catch (PropertyVetoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		itCadastroPacote.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!isFrameCadastroPacote) { // defino se não houver uma
												// instancia já criada, eu

					try {
						isFrameCadastroPacote = true; // difinindo que ja tem
														// uma janela aberta
						JInternalFrame internalFrame = new FormCadastroServico();
						getContentPane().add(internalFrame);
						internalFrame.setSelected(true);
					} catch (PropertyVetoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		});

		itConfiguraEmail.addActionListener(e -> {
			if (!isViewConfiguraEmail) {
				try {
					isViewConfiguraEmail = true;
					JInternalFrame internalFrame = new FormConfigEmail(
							loginUser);
					getContentPane().add(internalFrame);
					internalFrame.setSelected(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	})	;

		itFazerLogoff.addActionListener(e -> {
			this.dispose();
			// Thread.currentThread().stop();

				new TelaLogin();
				minhaFrame = null;

			});

	}

}

/**
 * 
 * INNERS CLASS
 * 
 * @author gprodrigues
 *
 */

class MeuModeloTree extends DefaultTreeCellRenderer {

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean sel, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,
				row, hasFocus); // To change body of generated
								// methods, choose Tools |
								// Templates.
								// if(value != null){
		DefaultMutableTreeNode no = (DefaultMutableTreeNode) value;
		String texto = no.getUserObject().toString();
		System.out.println();
		if (texto.equals("Inicio")) {
			ImageIcon img = new ImageIcon(getClass().getClassLoader()
					.getResource("resources/icons/inicio.png"));
			setIcon(img);
			setToolTipText(texto);

		} else if (texto.contains("E-mail")) {
			ImageIcon img = new ImageIcon(getClass().getClassLoader()
					.getResource("resources/icons/email.png"));
			setIcon(img);
			setToolTipText(texto);
		}
		if (texto.contains("Favoritos")) {
			ImageIcon img = new ImageIcon(getClass().getClassLoader()
					.getResource("resources/icons/" + texto + ".png"));
			setIcon(img);
			setToolTipText(texto);
		}
		if (texto.contains("Tarefas")) {
			ImageIcon img = new ImageIcon(getClass().getClassLoader()
					.getResource("resources/icons/" + texto + ".png"));
			setIcon(img);
			setToolTipText(texto);
		} else {
		}

		// }
		// }
		return this;

	}

}

// Thread Para Configurar e baixar os E-mails
/*
 * class ConfiguraEmail implements Runnable { private EmailControllerV3 emailC;
 * private Login user;
 * 
 * 
 * 
 * 
 * 
 * protected static boolean isShowing; private UsuarioEmail confEmail;
 * 
 * public ConfiguraEmail(Login u) {
 * 
 * isShowing = false; this.user = u;
 * 
 * 
 * 
 * String diretorio = System.getProperty("user.home"); String sep =
 * System.getProperty("file.separator");
 * 
 * String nameFolder = user.getUsuario() + "@emailConfig"; // e digo o nome //
 * padr�o dos // arquivos de // e-mail's
 * 
 * File fileConfigEmail = new File(diretorio+sep+nameFolder); // Pego o //
 * diretorio // que se // encontra // o arquivo
 * 
 * confEmail = new CriptografiaConfigEmail().unCrypt(// e tento localizo-lo // e
 * // descriptografa-lo nameFolder);
 * 
 * }
 * 
 * public void run() { if (confEmail != null) { // Se no construtor conseguiu
 * localizar o // arquivo ent�o eu tento autentica-lo
 * System.out.println("Tem config");
 * 
 * emailC = new EmailControllerV3(confEmail); Thread threadUpdateEmail = new
 * Thread(new CheckNewMessages(emailC)); threadUpdateEmail.start(); } else {
 * System.out.println("N Tem config  - PARTIU"); }
 * 
 * } }
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * class CheckNewMessages implements Runnable {
 * 
 * 
 * private EmailControllerV3 email;
 * 
 * 
 * 
 * public CheckNewMessages(EmailControllerV3 emailC) { this.email = emailC; }
 * 
 * //
 * 
 * @Override public void run() { try { while (true) { int atual =
 * email.countMessage(); System.out.println(atual+"    has email?"); if
 * (email.hasNewEmail(atual)) { System.out.println("Chegou nova msg!");
 * Principal.showNotication(); }
 * 
 * Thread.sleep(60 * 1000); }
 * 
 * } catch (Exception e) { // TODO Auto-generated catch block
 * e.printStackTrace(); } // } //
 * 
 * 
 * }
 */

// ----------

class DesktopPaneCustom extends JDesktopPane {
	private javax.swing.JPopupMenu pop;
	private JMenuItem itMnAdicionar;
	private JDesktopPane myDesk;

	public DesktopPaneCustom() {
		myDesk = this;
		pop = new javax.swing.JPopupMenu();
		itMnAdicionar = new JMenuItem("Adicionar");

		itMnAdicionar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Clique");

			}
		});
		pop.add(itMnAdicionar);
		pop.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if (e.getButton() == java.awt.event.MouseEvent.BUTTON3) {
					pop.show(myDesk, e.getX(), e.getY());
				}
			}
		});
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			BufferedImage imagem;

			imagem = ImageIO.read(getClass().getClassLoader()
					.getResourceAsStream("resources/imgs/logo fundo.png"));

			if (imagem != null) {
				g.drawImage(imagem, 0, 0, this.getWidth(), this.getHeight(),
						this);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
