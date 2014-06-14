package formulario;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
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
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeSelectionModel;


public class Principal extends JFrame {

	private JMenuBar menuBarra;
	private JMenu menuArquivo, menuAgendamento, menuRelatorio;
	private JMenuItem itSair, itCadastroCliente, itCadastroFuncionario,
			itCalendario, itAgendamento, itCadastroPacote, itCadastroCarro;
	private JLabel redefinirSenha;
	private boolean painelMostrando=false;
	private JPanel painelInformativo,painelLateralGuia;
	private JButton btAbrirInformativo;
        protected static JFrame minhaFrame; //Frame para setar a dialogs
        
        private JTree jtreeAtalhos;
	
	protected static boolean isFrameInstrutorOpen,isFrameClienteOpen, isFrameCadastroPacote, isFrameAgendamento;

	public Principal() {
		try{
                        minhaFrame = this;
			inicializaComponentes();
			definirEventos();
                        
			
			isFrameClienteOpen = isFrameInstrutorOpen = isFrameCadastroPacote  = isFrameAgendamento = false;
		}catch (Exception e){e.printStackTrace();}
	}

	public void inicializaComponentes() throws IOException {
		//Definindo o layout e a imagem de fundo	
		setLayout(null);
		JDesktopPane fundoDaPrincipal = new JDesktopPane();
		setContentPane(fundoDaPrincipal);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
                setSize(1024, 720);
		
		//
		menuBarra = new JMenuBar();
		//
		menuArquivo = new JMenu("Arquivo");
		menuAgendamento = new JMenu("Agendamento");
		menuRelatorio = new JMenu("Relatório");
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
		
		btAbrirInformativo = new JButton("<<");
		btAbrirInformativo.setBounds(1024 - 48,40 , 50, 30);
		add(btAbrirInformativo);
		
                //Painel para informativos
		painelInformativo = new JPanel();
		painelInformativo.setBounds(1024, 0, 300, 650);
		painelInformativo.setBackground(Color.GRAY);
                painelInformativo.setVisible(true);
		add(painelInformativo);
		
		
                
                
                DefaultMutableTreeNode root = new DefaultMutableTreeNode("Inicio");
                
                DefaultMutableTreeNode favItens = new DefaultMutableTreeNode("Favoritos");
                root.add(favItens);
                
                DefaultMutableTreeNode tarefaItens = new DefaultMutableTreeNode("Tarefas");
                root.add(tarefaItens);
                
                DefaultMutableTreeNode emailItens = new DefaultMutableTreeNode("E-mail");
                DefaultTreeCellRenderer imgEmail = new DefaultTreeCellRenderer();
                imgEmail.setLeafIcon(new ImageIcon("Resources/imgs/logo fundo.jog"));
                root.add(emailItens);
                
                DefaultMutableTreeNode fav1 = new DefaultMutableTreeNode("Arquivos1");
                DefaultMutableTreeNode fav2 = new DefaultMutableTreeNode("Arquivos1");
                emailItens.add(fav1);
                emailItens.add(fav2);
                
                
                jtreeAtalhos = new JTree(root);
                //jtreeAtalhos.setEnabled(false);
                jtreeAtalhos.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
                jtreeAtalhos.setCellRenderer(new MeuModeloTree());
                jtreeAtalhos.expandRow(0);
                
                JScrollPane sp = new JScrollPane(jtreeAtalhos);
                JToolBar barraLateral = new JToolBar();
                barraLateral.setEnabled(false);
                barraLateral.setLayout(null);
                barraLateral.setBounds(0,0, 170, minhaFrame.getHeight());
                sp.setBounds(8, 0, barraLateral.getWidth()-5, barraLateral.getHeight()-50);
                barraLateral.add(sp);
                add(barraLateral);
                
                
		
		setJMenuBar(menuBarra);		
		setLocationRelativeTo(null);
		setTitle("Karol Habilitados v 1.2.1");
		setResizable(false);
		setVisible(true);

	}

	public void definirEventos() {
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
				if(!isFrameClienteOpen){
					isFrameClienteOpen = true;
					getContentPane().add(new FormCadastroCliente());
				}
			}
		});
		itCadastroFuncionario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!isFrameInstrutorOpen){
					isFrameInstrutorOpen = true; // difinindo que ja tem uma janela aberta 
					getContentPane().add(new FormCadastroInstrutor());
				}
				
				
			}
		});
	
	itAgendamento.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!isFrameAgendamento){ //Se não houver uma instancia crida, eu crio uma nova.
                                isFrameAgendamento = true; // definindo que ja tem uma janela aberta 
                                getContentPane().add(new FormAgendamento()); //Adiciono a Internal Frame na tela
                            }
                }
	});
	
        
        itCadastroCarro.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
                    new FormCadastroCarro();
		}
	});
	
        itCadastroPacote.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) 
                {
                    if(!isFrameCadastroPacote){ //defino se não houver uma instancia já criada, eu posso criar
                        isFrameCadastroPacote = true; // difinindo que ja tem uma janela aberta 
                        getContentPane().add(new FormCadastroPacote());
                    }
                }
	});
	
	btAbrirInformativo.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if(painelMostrando== false){ // verifica se o painel está escondido ou não
                    painelMostrando = true;
                    Thread t = new Thread(new Runnable() 
                    {
                        public void run() 
                        {		
                            int localizacao = painelInformativo.getX();
                            int localizacaoBt = btAbrirInformativo.getX();
                                    
                            while(localizacao >= 770){ //até que localização devo parar 
                                try {	
                                    localizacao--;//Localização do frame vai decrementando
                                    localizacaoBt--; //e a do botão também
                                
                                    btAbrirInformativo.setLocation(localizacaoBt, 40); //vou abrindo de pouco em pocuo
                                    Thread.sleep(2);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                                 btAbrirInformativo.setText(">>");//Mudo o texto
			}
                    });
		
                    
                        t.start(); // inicio a minha thread aqui
                    }else{ //Se não ,a eu acrescento para esconder 
                        painelMostrando = false; 
                       
			System.out.println("Tentand fechar");
			Thread t = new Thread(new Runnable() {
                            public void run() {
						
                                int localizacao = 770;
				int localizacaoBt = btAbrirInformativo.getX();
						
				while(localizacao < 1024){
                                    try {	
					localizacao++;
					localizacaoBt++;
					painelInformativo.setLocation(localizacao, 0);	
					btAbrirInformativo.setLocation(localizacaoBt, 40);
                                        Thread.sleep(2);
                                    } catch (InterruptedException e) {
					e.printStackTrace();
                                    }
                                }
                                btAbrirInformativo.setText("<<");
                            }
					
                        });
			
                        t.start();
			
		}
			
            }
        });
        
        this.addWindowListener(new WindowListener() {

                    @Override
                    public void windowOpened(WindowEvent e) {}

                    @Override
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }

                    @Override
                    public void windowClosed(WindowEvent e) {}

                    @Override
                    public void windowIconified(WindowEvent e) {}

                    @Override
                    public void windowDeiconified(WindowEvent e) {}

                    @Override
                    public void windowActivated(WindowEvent e) {}

                    @Override
                    public void windowDeactivated(WindowEvent e) {}
                });
    }


        class MeuModeloTree extends DefaultTreeCellRenderer{

            
        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
            super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus); //To change body of generated methods, choose Tools | Templates.
            DefaultMutableTreeNode no = (DefaultMutableTreeNode) value;
            String texto = no.getUserObject().toString();
            System.out.println(texto);
            
            if(texto.equals("Inicio")){
                 try {
                    ImageIcon img = new ImageIcon(new File("").getCanonicalPath()+"\\bin\\Resources\\icons\\inicio.png");
                    setIcon(img);
                    setToolTipText(texto);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            
            }else if(texto.contains("E-mail")){
                try {
                    ImageIcon img = new ImageIcon(new File("").getCanonicalPath()+"\\bin\\Resources\\icons\\email.png");
                    setIcon(img);
                    setToolTipText(texto);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if(texto.contains("Favoritos")){
                try {
                    ImageIcon img = new ImageIcon(new File("").getCanonicalPath()+"\\bin\\Resources\\icons\\Favoritos.png");
                    setIcon(img);
                    setToolTipText(texto);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if(texto.contains("Tarefas")){
                try {
                    ImageIcon img = new ImageIcon(new File("").getCanonicalPath()+"\\bin\\Resources\\icons\\Tarefas.png");
                    setIcon(img);
                    setToolTipText(texto);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        
            return this;
            
        }
            
        }
}
