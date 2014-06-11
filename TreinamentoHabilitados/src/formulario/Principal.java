package formulario;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JInternalFrame.JDesktopIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.towel.swing.img.JImagePanel;

public class Principal extends JFrame {

	private JMenuBar menuBarra;
	private JMenu menuArquivo, menuAgendamento, menuRelatorio;
	private JMenuItem itSair, itCadastroCliente, itCadastroFuncionario,
			itCalendario, itAgendamento, itCadastroPacote, itCadastroCarro;
	private JLabel redefinirSenha;
	private boolean painelMostrando=false;
	private JPanel painelInformativo;
	private JButton btAbrirInformativo;
        protected static JFrame minhaFrame; //Frame para setar a dialogs
	
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
		//JImagePanel fundoDeImage = new JImagePanel(new File("").getCanonicalFile()+"\\bin\\Resources\\imgs\\logo fundo.png");
		JDesktopPane fundoDaPrincipal = new JDesktopPane();
		setContentPane(fundoDaPrincipal);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//
		menuBarra = new JMenuBar();
		//
		menuArquivo = new JMenu("Arquivo");
		menuAgendamento = new JMenu("Agendamento");
		menuRelatorio = new JMenu("Relatório");
		//
		itSair = new JMenuItem("Sair");
		itCadastroCliente = new JMenuItem("Cadastro Aluno");
		itCadastroFuncionario = new JMenuItem("Cadastro Pacote");
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
		
		painelInformativo = new JPanel();
		painelInformativo.setBounds(1024, 0, 300, 650);
		painelInformativo.setBackground(Color.GRAY);
		add(painelInformativo);
		
		
		
		setJMenuBar(menuBarra);
		setSize(1024, 720);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);		
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
			if(!isFrameAgendamento){
					isFrameAgendamento = true; // difinindo que ja tem uma janela aberta 
					getContentPane().add(new FormAgendamento());
                                        
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
		public void actionPerformed(ActionEvent arg0) {
			if(!isFrameCadastroPacote){
					isFrameCadastroPacote = true; // difinindo que ja tem uma janela aberta 
					getContentPane().add(new FormCadastroPacote());
                        }
		}
	});
	
	btAbrirInformativo.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(painelMostrando== false){
				painelMostrando = true;
				System.out.println("Tentando abrir");
				Thread t = new Thread(new Runnable() {
					public void run() {
						
						int localizacao = painelInformativo.getX();
						int localizacaoBt = btAbrirInformativo.getX();
						
						while(localizacao >= 770){
							try {	
								localizacao--;
								localizacaoBt--;
								painelInformativo.setLocation(localizacao, 0);
								btAbrirInformativo.setLocation(localizacaoBt, 40);
								
								Thread.sleep(2);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
						}
						btAbrirInformativo.setText(">>");
					}
				});
				t.start();
			}else{
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
	
	}

	
}
