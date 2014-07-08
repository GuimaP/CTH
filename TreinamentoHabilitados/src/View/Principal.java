package View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

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
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;













import antlr.TreeParserSharedInputState;

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
	private  JTree jtreeAtalhos;
	
	private EmailController email;
	private JPanel painelEmail;
	private JTable jTableEmails;
	private List<String>listaEmails;
	private HashMap<String,List<String>> mapEmails;
	protected Login loginUser;
	
	protected static boolean isFrameInstrutorOpen, isFrameClienteOpen,
			isFrameCadastroPacote, isFrameAgendamento, isFrameCarro;

	public Principal(Login usuario) {
		try {
			minhaFrame = this;
//			email = 
//					new EmailController("smtp.gmail.com","imap.gmail.com",465,true);
			
			this.loginUser = usuario;
			inicializaComponentes();
			definirEventos();

			Thread gerenciaEmal = new Thread(new CheckNewMessages(jTableEmails,jtreeAtalhos	));
			gerenciaEmal.start();
			
			isFrameClienteOpen = isFrameInstrutorOpen = isFrameCadastroPacote = isFrameAgendamento 
					= isFrameCarro = false;
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
		setSize(1024, 720);
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
		menuArquivo.add(itCalendario);
		menuArquivo.add(itSair);
		menuAgendamento.add(itAgendamento);
		//
		menuBarra.add(menuArquivo);
		menuBarra.add(menuAgendamento);
		menuBarra.add(menuRelatorio);
		//

		//

		
//		imgEmail.setLeafIcon(new ImageIcon("Resources/imgs/logo fundo.jpg"));
		

//		DefaultMutableTreeNode fav1 = new DefaultMutableTreeNode("Arquivos1");
//		DefaultMutableTreeNode fav2 = new DefaultMutableTreeNode("Arquivos1");
//		emailItens.add(fav1);
//		emailItens.add(fav2);
		
// 		java.util.List<String> lsEmail =  email.getListagemFolders();
//		for(String e : lsEmail){
//			System.out.println("- "+ e);
//			DefaultMutableTreeNode dmFolder = new DefaultMutableTreeNode(e);
//			emailItens.add(dmFolder);
//		}
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Inicio");

		DefaultMutableTreeNode favItens = new DefaultMutableTreeNode(
				"Favoritos");
		root.add(favItens);

		DefaultMutableTreeNode tarefaItens = new DefaultMutableTreeNode(
				"Tarefas");
		root.add(tarefaItens);

		jtreeAtalhos = new JTree(root);
		jtreeAtalhos.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		jtreeAtalhos.setCellRenderer(new MeuModeloTree());
		jtreeAtalhos.expandRow(60);
		

		painelEmail = new JPanel(); //INICANDO O PAINEL

		barraLateral = new JToolBar();
		barraLateral.setEnabled(false);
		barraLateral.setLayout(null);
		barraLateral.setBounds(0, 0, 170, minhaFrame.getHeight());
		barraLateral.setVisible(false);
		
		sp = new JScrollPane(jtreeAtalhos);
		sp.setBounds(0, 0, 170, barraLateral.getHeight());
		sp.setBounds(-(barraLateral.getWidth()), 0,
				barraLateral.getWidth() - 5, barraLateral.getHeight() - 50);
		barraLateral.add(sp);
		
		add(barraLateral);
		
		btAbrirMenuLateral = new JButton(">>");
		btAbrirMenuLateral.setFocusCycleRoot(true);
		btAbrirMenuLateral.setBounds((sp.getWidth() + sp.getX()), 10, 45, 30);
		add(btAbrirMenuLateral);

		POSXButoon = btAbrirMenuLateral.getX();

		java.awt.Point p = new Point(minhaFrame.getWidth() - 405, 100);
		painelCalendario = new PainelCalendarioAgendamento(p);//Definindo ele no canto esquerdo da tela
		
		
		jTableEmails = new JTable();
		jTableEmails.setRowHeight(60);
		JScrollPane spe = new JScrollPane(jTableEmails);

		painelEmail.add(spe);
		
		
		

		add(painelCalendario);

		setJMenuBar(menuBarra);
		setLocationRelativeTo(null);
		setTitle("Karol Habilitados v 1.2.2");
		//setResizable(false);
		setVisible(true);

	}

	public void definirEventos() {
		
		jtreeAtalhos.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent evt) {
				System.out.println(evt.getNewLeadSelectionPath());
				final int  WIDTH_TAMANHO = 300;
					if("[Inicio, E-mail, INBOX]".equalsIgnoreCase(evt.getNewLeadSelectionPath().toString())){
						System.out.println("eh inbox");
//						java.awt.Dimension d = barraLateral.getSize();
//						Point p = barraLateral.getLocation();
//						sp.setSize(d.width+100, d.height);
//						sp.setLocation(p.x+100,p.y);
//						barraLateral.setLocation(p.x+100, p.y);
						java.awt.Dimension d2 = barraLateral.getSize();
						barraLateral.setSize(d2.width+WIDTH_TAMANHO, d2.height);
						btAbrirMenuLateral.setLocation(btAbrirMenuLateral.getX()+WIDTH_TAMANHO, btAbrirMenuLateral.getY());
						
						
						painelEmail.removeAll();
						painelEmail.setLayout(new GridLayout(1,1));
						
						List<String> ls = email.listarViewEmails(evt.getNewLeadSelectionPath().toString());
						jTableEmails = new JTable(new ModelTableEmail(ls));
						jTableEmails.setRowHeight(60);
						JScrollPane spe = new JScrollPane(jTableEmails);

						
						painelEmail.add(spe);
						painelEmail.setSize(WIDTH_TAMANHO, barraLateral.getHeight());
						painelEmail.setLocation(jtreeAtalhos.getWidth()+10, 0);
						painelEmail.setBackground(Color.white);
						barraLateral.add(painelEmail);
						
						
						minhaFrame.revalidate();
						minhaFrame.repaint();
						
						
						
						
						
					}
				
			}
		});

		btAbrirMenuLateral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!painelMostrando) { // ABRINDO O PAINEL
					painelMostrando = true;
					Thread t = new Thread(new Runnable() {

						@Override
						public void run() {
							int XAtual = sp.getX(); // Local atual do view
							int XFinal = 6; // O Destino final que deve estar.
							int XPainel =painelEmail.getX();
							int posXButton = POSXButoon - 10;
							barraLateral.setVisible(true);
							while (XAtual <= XFinal) { // Enquanto o atual n
														// chegar no final
								try {

									XAtual++;
									XPainel++;
									posXButton++;
									

									sp.setLocation(XAtual, sp.getY());
									painelEmail.setLocation(XPainel, painelEmail.getY());
									btAbrirMenuLateral.setLocation(posXButton,
											btAbrirMenuLateral.getY());
									Thread.sleep(2);
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
					painelMostrando = false;
					Thread t = new Thread(new Runnable() {

						@Override
						public void run() {
							int XAtual = sp.getX(); // Local atual do view
							int XFinal = -(barraLateral.getWidth()); //Local atual do Container Barra
							int posXButton = btAbrirMenuLateral.getX(); //Pego a posiÁ„o do button para poder move-lo
							int XPainel = painelEmail.getX();

							while (XAtual >= XFinal) { // Enquanto o atual n
														// chegar no final
								try {
									XAtual--;
									posXButton--;
									XPainel--;

									sp.setLocation(XAtual, sp.getY());
									painelEmail.setLocation(XPainel, painelEmail.getY());
									btAbrirMenuLateral.setLocation(
											posXButton + 20,
											btAbrirMenuLateral.getY());
									Thread.sleep(2);
								} catch (InterruptedException ex) {
									Logger.getLogger(Principal.class.getName())
											.log(Level.SEVERE, null, ex);
								}
							}
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
				if (!isFrameCarro){
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

		itCalendario.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("cliq");
				//getContentPane().add(new View.PainelCalendarioAgendamento());
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
			DefaultMutableTreeNode no = (DefaultMutableTreeNode) value;
			String texto = no.getUserObject().toString();
			if (texto.equals("Inicio")) {
				ImageIcon img = new ImageIcon(Principal.class.getResource(
						"/Resources/icons/").getPath()
						+ "inicio.jpg");
				setIcon(img);
				setToolTipText(texto);

			} else if (texto.contains("E-mail")) {
				ImageIcon img = new ImageIcon(Principal.class.getResource(
						"/Resources/icons/").getPath()
						+ "email.png");
				setIcon(img);
				setToolTipText(texto);
			}
			if (texto.contains("Favoritos")) {
				ImageIcon img = new ImageIcon(Principal.class.getResource(
						"/Resources/icons/").getPath()
						+ texto + ".png");
				setIcon(img);
				setToolTipText(texto);
			}
			if (texto.contains("Tarefas")) {
				ImageIcon img = new ImageIcon(Principal.class.getResource(
						"/Resources/icons/").getPath()
						+ texto + ".png");
				setIcon(img);
				setToolTipText(texto);
			}

			return this;

		}

	}
	
	class CheckNewMessages implements Runnable {
		private EmailController controllerEmail; 
		private JTable jtable;
		private EmailController email;
		private JTree jtree;
		
		public CheckNewMessages(JTable Table, JTree jtreeAtalhos){
			
			this.jtable = Table;
			this.jtree = jtreeAtalhos;
			
			

//			DefaultMutableTreeNode emailItens = new DefaultMutableTreeNode("E-mail");
//			DefaultTreeCellRenderer imgEmail = new DefaultTreeCellRenderer();
			
			

			
			try{
			
			/*Verificar se possui um arquivo 
			*Criptografado e serializado com os Emails,
			*se n„o carrega tudo
			*/
			UsuarioEmail user = null;
			
			File fileEmailsEmails = new File(getClass().getResource("/Resources/FilesConfig")+"/email-"+Principal.this.loginUser.getUsuario()+".ser");
			if(fileEmailsEmails.exists()){
				FileInputStream input = new FileInputStream(fileEmailsEmails);	
				ObjectInputStream obj = new ObjectInputStream(input);
				user = (UsuarioEmail) obj.readObject();
				obj.close();
			}else{
				user = new UsuarioEmail();
				user.setHost("smtp.gmail.com");
				user.setHostReceive("imap.gmail.com");
				user.setPass("guimateste");
				user.setPort(465);
				user.setSsl(true);
				user.setUser("guima.teste.p@gmail.com");
				
				
				
				FileOutputStream ou = new FileOutputStream("te.ser");
				ObjectOutputStream os = new ObjectOutputStream(ou);
				os.writeObject(user);
				os.flush();
				os.close(); //TODO APLICAR CRIPTOGRAFIA
				
			}
			
			this.email = new EmailController(user);

			 
			 
			
			DefaultMutableTreeNode root = new DefaultMutableTreeNode();
			
			
			DefaultMutableTreeNode favItens = new DefaultMutableTreeNode(
					"Favoritos");
			root.add(favItens);

			DefaultMutableTreeNode tarefaItens = new DefaultMutableTreeNode(
					"Tarefas");
			root.add(tarefaItens);
			
			DefaultMutableTreeNode emailItem = new DefaultMutableTreeNode("E-mail");
			
			jtreeAtalhos.setCellRenderer(new MeuModeloTree());
			
			Map<String, List<String>> map = email.getEmails();
			
			Iterator it =  map.entrySet().iterator();
			 while(it.hasNext()){
				 Map.Entry mapEntry = (Map.Entry) it.next();
				 DefaultMutableTreeNode itEmail = new DefaultMutableTreeNode(mapEntry.getKey());
				 List<String> ls = (List<String>) mapEntry.getValue();
				 for(String s : ls){
					 DefaultMutableTreeNode item = new DefaultMutableTreeNode(s);
					 itEmail.add(item);
				 }
			 }
			
			 root.add(emailItem);
			
			 
			
			
			}catch(IOException | ClassNotFoundException erro){
				erro.printStackTrace();
				
			}
		}
		
		
		@Override
		public void run() {
			try {
				int numeroDeMsgs;
				numeroDeMsgs = 0; //inicializando
				
				while(true){
					
				
						jtable.revalidate();
						Principal.minhaFrame.repaint();
					}
					Thread.sleep(1000*60); //Espera 1 minutos para executar de novo
					numeroDeMsgs = lsEmail.size();
				}	
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
