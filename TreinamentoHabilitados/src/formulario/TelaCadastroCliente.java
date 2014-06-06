package formulario;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import javax.swing.text.html.ListView;

import DAO.DAOcliente;
import principal.CadastroCliente;
import modelo.Cliente;
import modelo.Cnh;
import modelo.Endereco;
import modelo.ModeloTable;

public class TelaCadastroCliente extends JFrame {
	private JLabel lbNome, lbEmail, lbEscolaridade, lbProfissao, lbNascimento,
			lbTelefone, lbPrimeiraHabilitacao, lbValidadeCnh, lbRegistroCnh,
			lbLogradouro, lbBairro, lbNumero, lbCep, lbRg, lbCpf, lbSexo,
			lbObserva, lbQ1, lbQ2, lbQ3, lbQ4, lbData, lbCelular;
	private JTextField tfNome, tfEmail, tfProfissao, tfRegistroCnh,
			tfLogradouro, tfBairro, tfRg, tfQuestao1, tfNumero;
	private JFormattedTextField tfData, tfNascimento, tfCep, tfCpf, tfCelular,
			tfTelefone, tfValidadeCnh, tfPrimeiraHabilitacao;
	private JButton btSalvar, btExcluir, btBuscar;
	private JTextArea observa;
	private JScrollPane scroll, scTable;
	private ButtonGroup gQ1, gQ2;
	private JRadioButton jrQ1Yes, jrQ1No, jrQ3Yes, jrQ3No;
	private String[] sexo = { "M", "F" };
	private String[] escolaridade = { "Superior", "Tecnico", "Médio",
			"Fundamental" };
	private String[] pesquisa = { "Internet", "Indicação", "Outros" };
	private JComboBox<String> jcSexo, jcEscolaridade, jcPesquisa;
	private JTable table;
	private String data;
	private ArrayList<Cliente> listCliente = new ArrayList<Cliente>();
	private MaskFormatter dataMask, dataMaskNascimento, maskCep, maskNumero,
			maskCpf, maskTelefone, maskCelular, maskValidadeCnh,
			maskPrimeiraHabilitacao;
	private DAOcliente daoCliente; 
	Cliente cliente = new Cliente();
	Endereco endereco = new Endereco();
	Cnh cnh = new Cnh();
	CadastroCliente cadastro = new CadastroCliente();

	public TelaCadastroCliente() {

		try {
			Cliente c = new Cliente();
			try {
				daoCliente = new DAOcliente();
				listCliente = daoCliente.buscarTodos();
			} catch (SQLException e) {

				JOptionPane.showMessageDialog(null, e.getMessage());
			}

			inicializaComponentes();

			definirEventos();
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());

		}

	}
	public void limparCampos(){
		tfNome.setText("");
		tfData.setValue(dataMask);
		tfLogradouro.setText("");
		tfNumero.setText("");
		tfBairro.setText("");
		jcSexo.setSelectedIndex(-1);
		tfRg.setText("");
		tfCpf.setValue(maskCpf);
		tfCep.setValue(maskCep);
		tfNascimento.setValue(dataMaskNascimento);
		tfTelefone.setValue(maskTelefone);
		tfCelular.setValue(maskCelular);
		tfEmail.setText("");
		jcEscolaridade.setSelectedIndex(-1);
		tfProfissao.setText("");
		tfPrimeiraHabilitacao.setValue(maskPrimeiraHabilitacao);
		tfValidadeCnh.setValue(maskValidadeCnh);
		tfRegistroCnh.setText("");
		tfQuestao1.setText("");
		jcPesquisa.setSelectedIndex(-1);
		observa.setText("");
		cliente = null;
		}
	
	
	
	public void populaList() {
		try {
			for (Cliente c : listCliente) {
				ArrayList<Cliente> l = new ArrayList<Cliente>();
				l = daoCliente.buscarTodos();

				listCliente = l;
				table.setModel(new ModeloTable(listCliente));
			}
		} catch (SQLException e1) {

		}
	}

	public void inicializaComponentes() throws ParseException {
		// Label
		lbNome = new JLabel("Nome");
		lbNome.setLocation(40, 40);
		lbNome.setSize(35, 10);
		getContentPane().add(lbNome);

		lbData = new JLabel("Data");
		lbData.setLocation(390, 40);
		lbData.setSize(35, 10);
		getContentPane().add(lbData);

		lbLogradouro = new JLabel("Rua");
		lbLogradouro.setLocation(40, 70);
		lbLogradouro.setSize(30, 10);
		getContentPane().add(lbLogradouro);

		lbNumero = new JLabel("Numero");
		lbNumero.setLocation(350, 70);
		lbNumero.setSize(50, 10);
		getContentPane().add(lbNumero);

		lbBairro = new JLabel("Bairro");
		lbBairro.setLocation(40, 100);
		lbBairro.setSize(50, 10);
		getContentPane().add(lbBairro);

		lbCep = new JLabel("Cep");
		lbCep.setLocation(300, 100);
		lbCep.setSize(30, 15);
		getContentPane().add(lbCep);

		lbNascimento = new JLabel("Data Nasc.");
		lbNascimento.setLocation(40, 130);
		lbNascimento.setSize(65, 15);
		getContentPane().add(lbNascimento);

		lbSexo = new JLabel("Sexo");
		lbSexo.setLocation(180, 130);
		lbSexo.setSize(30, 15);
		getContentPane().add(lbSexo);

		lbCpf = new JLabel("Cpf");
		lbCpf.setLocation(40, 160);
		lbCpf.setSize(30, 15);
		getContentPane().add(lbCpf);

		lbRg = new JLabel("Rg");
		lbRg.setLocation(190, 160);
		lbRg.setSize(50, 15);
		getContentPane().add(lbRg);

		lbTelefone = new JLabel("Telefone");
		lbTelefone.setLocation(330, 160);
		lbTelefone.setSize(50, 15);
		getContentPane().add(lbTelefone);

		lbCelular = new JLabel("Cel");
		lbCelular.setLocation(480, 160);
		lbCelular.setSize(30, 20);
		getContentPane().add(lbCelular);

		lbEmail = new JLabel("Email");
		lbEmail.setLocation(40, 190);
		lbEmail.setSize(50, 15);
		getContentPane().add(lbEmail);

		lbEscolaridade = new JLabel("Escolaridade");
		lbEscolaridade.setLocation(40, 220);
		lbEscolaridade.setSize(100, 15);
		getContentPane().add(lbEscolaridade);

		lbProfissao = new JLabel("Profissão");
		lbProfissao.setLocation(240, 220);
		lbProfissao.setSize(100, 15);
		getContentPane().add(lbProfissao);

		lbRegistroCnh = new JLabel("Nº Cnh");
		lbRegistroCnh.setLocation(40, 250);
		lbRegistroCnh.setSize(70, 15);
		getContentPane().add(lbRegistroCnh);

		lbValidadeCnh = new JLabel("Validade");
		lbValidadeCnh.setLocation(190, 250);
		lbValidadeCnh.setSize(80, 15);
		getContentPane().add(lbValidadeCnh);

		lbPrimeiraHabilitacao = new JLabel("Dt Permissão");
		lbPrimeiraHabilitacao.setLocation(325, 250);
		lbPrimeiraHabilitacao.setSize(100, 15);
		getContentPane().add(lbPrimeiraHabilitacao);

		lbObserva = new JLabel("Observações");
		lbObserva.setLocation(60, 400);
		lbObserva.setSize(120, 20);
		getContentPane().add(lbObserva);

		// Opções e Grupos de questões.
		lbQ1 = new JLabel("A quanto tempo nao dirige?");
		lbQ1.setLocation(40, 280);
		lbQ1.setSize(200, 20);
		getContentPane().add(lbQ1);

		lbQ2 = new JLabel("Tem veiculo proprio?");
		lbQ2.setLocation(40, 310);
		lbQ2.setSize(200, 20);
		getContentPane().add(lbQ2);

		lbQ3 = new JLabel("É possivel treinar nele?");
		lbQ3.setLocation(40, 340);
		lbQ3.setSize(150, 20);
		getContentPane().add(lbQ3);

		lbQ4 = new JLabel("Como você soube da Karol Treinamentos?");
		lbQ4.setLocation(40, 370);
		lbQ4.setSize(250, 20);
		getContentPane().add(lbQ4);

		jrQ1Yes = new JRadioButton("Sim", true);
		jrQ1Yes.setLocation(170, 310);
		jrQ1Yes.setSize(60, 20);

		jrQ1No = new JRadioButton("Não", false);
		jrQ1No.setLocation(240, 310);
		jrQ1No.setSize(60, 20);

		jrQ3Yes = new JRadioButton("Sim", true);
		jrQ3Yes.setLocation(190, 340);
		jrQ3Yes.setSize(60, 20);

		jrQ3No = new JRadioButton("Não", false);
		jrQ3No.setLocation(260, 340);
		jrQ3No.setSize(60, 20);

		gQ1 = new ButtonGroup();
		gQ1.add(jrQ1Yes);
		gQ1.add(jrQ1No);
		getContentPane().add(jrQ1Yes);
		getContentPane().add(jrQ1No);

		gQ2 = new ButtonGroup();
		gQ2.add(jrQ3Yes);
		gQ2.add(jrQ3No);
		getContentPane().add(jrQ3Yes);
		getContentPane().add(jrQ3No);
		// Obs
		observa = new JTextArea();
		observa.setLineWrap(true);
		scroll = new JScrollPane(observa);
		scroll.setLocation(60, 430);
		scroll.setSize(300, 60);
		getContentPane().add(scroll);
		// Botão
		btSalvar = new JButton("Salvar");
		btSalvar.setLocation(400, 520);
		btSalvar.setSize(120, 35);
		getContentPane().add(btSalvar);

		btExcluir = new JButton("Excluir");
		btExcluir.setLocation(530, 520);
		btExcluir.setSize(120, 35);
		getContentPane().add(btExcluir);

		btBuscar = new JButton("Buscar");
		btBuscar.setLocation(510, 40);
		btBuscar.setSize(80, 30);
		getContentPane().add(btBuscar);
		// Text
		tfNome = new JTextField();
		tfNome.setLocation(80, 37);
		tfNome.setSize(300, 20);
		getContentPane().add(tfNome);

		dataMask = new MaskFormatter("##/##/####");
		dataMask.setPlaceholderCharacter('_');
		tfData = new JFormattedTextField(dataMask);
		tfData.setLocation(420, 37);
		tfData.setSize(68, 20);
		getContentPane().add(tfData);

		tfLogradouro = new JTextField();
		tfLogradouro.setLocation(80, 67);
		tfLogradouro.setSize(250, 20);
		getContentPane().add(tfLogradouro);

		tfNumero = new JTextField();
		tfNumero.setLocation(410, 67);
		tfNumero.setSize(60, 20);
		getContentPane().add(tfNumero);

		tfBairro = new JTextField();
		tfBairro.setLocation(90, 97);
		tfBairro.setSize(180, 20);
		getContentPane().add(tfBairro);

		maskCep = new MaskFormatter("#####-###");
		maskCep.setPlaceholderCharacter('_');
		tfCep = new JFormattedTextField(maskCep);
		tfCep.setLocation(340, 97);
		tfCep.setSize(67, 20);
		getContentPane().add(tfCep);

		dataMaskNascimento = new MaskFormatter("##/##/####");
		dataMaskNascimento.setPlaceholderCharacter('_');
		tfNascimento = new JFormattedTextField(dataMaskNascimento);
		tfNascimento.setLocation(105, 127);
		tfNascimento.setSize(68, 20);
		getContentPane().add(tfNascimento);

		maskCpf = new MaskFormatter("###.###.###-##");
		maskCpf.setPlaceholderCharacter('_');
		tfCpf = new JFormattedTextField(maskCpf);
		tfCpf.setLocation(80, 157);
		tfCpf.setSize(94, 20);
		getContentPane().add(tfCpf);

		tfRg = new JTextField();
		tfRg.setLocation(220, 157);
		tfRg.setSize(100, 20);
		getContentPane().add(tfRg);

		maskTelefone = new MaskFormatter("(##)####-####");
		maskTelefone.setPlaceholderCharacter('_');
		tfTelefone = new JFormattedTextField(maskTelefone);
		tfTelefone.setLocation(385, 157);
		tfTelefone.setSize(88, 20);
		getContentPane().add(tfTelefone);

		maskCelular = new MaskFormatter("(##)#-####-####");
		maskCelular.setPlaceholderCharacter('_');
		tfCelular = new JFormattedTextField(maskCelular);
		tfCelular.setLocation(505, 157);
		tfCelular.setSize(98, 20);
		getContentPane().add(tfCelular);

		tfEmail = new JTextField();
		tfEmail.setLocation(80, 187);
		tfEmail.setSize(200, 20);
		getContentPane().add(tfEmail);

		tfProfissao = new JTextField();
		tfProfissao.setLocation(300, 217);
		tfProfissao.setSize(100, 20);
		getContentPane().add(tfProfissao);

		tfRegistroCnh = new JTextField();
		tfRegistroCnh.setLocation(80, 247);
		tfRegistroCnh.setSize(100, 20);
		getContentPane().add(tfRegistroCnh);

		maskValidadeCnh = new MaskFormatter("##/##/####");
		maskValidadeCnh.setPlaceholderCharacter('_');
		tfValidadeCnh = new JFormattedTextField(maskValidadeCnh);
		tfValidadeCnh.setLocation(245, 247);
		tfValidadeCnh.setSize(70, 20);
		getContentPane().add(tfValidadeCnh);

		maskPrimeiraHabilitacao = new MaskFormatter("##/##/####");
		maskPrimeiraHabilitacao.setPlaceholderCharacter('_');
		tfPrimeiraHabilitacao = new JFormattedTextField(maskPrimeiraHabilitacao);
		tfPrimeiraHabilitacao.setLocation(410, 247);
		tfPrimeiraHabilitacao.setSize(70, 20);
		getContentPane().add(tfPrimeiraHabilitacao);

		tfQuestao1 = new JTextField();
		tfQuestao1.setLocation(200, 280);
		tfQuestao1.setSize(70, 20);
		getContentPane().add(tfQuestao1);
		// Combo box
		jcSexo = new JComboBox<String>(sexo);
		jcSexo.setLocation(220, 127);
		jcSexo.setSize(50, 20);
		jcSexo.setSelectedIndex(-1);
		getContentPane().add(jcSexo);

		jcEscolaridade = new JComboBox<String>(escolaridade);
		jcEscolaridade.setLocation(120, 217);
		jcEscolaridade.setSize(105, 20);
		jcEscolaridade.setSelectedIndex(-1);
		getContentPane().add(jcEscolaridade);

		jcPesquisa = new JComboBox<String>(pesquisa);
		jcPesquisa.setLocation(290, 370);
		jcPesquisa.setSize(105, 20);
		jcPesquisa.setSelectedIndex(-1);
		getContentPane().add(jcPesquisa);
		// Table
		table = new JTable(new ModeloTable(listCliente));
		scTable = new JScrollPane(table);
		scTable.setSize(350, 450);
		scTable.setLocation(620, 40);
		getContentPane().add(scTable);

		// PAINEL//
		getContentPane().setLayout(null);
		setSize(1024, 600);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
		setTitle("Cadastro Cliente");
		setResizable(false);
		getContentPane().setBackground(Color.lightGray);

	}

	public void definirEventos() {

		btSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// validações
				tfNome.setText(tfNome.getText().trim());
				tfData.setText(tfData.getText().trim());
				tfLogradouro.setText(tfLogradouro.getText().trim());
				tfNumero.setText(tfNumero.getText().trim());
				tfBairro.setText(tfBairro.getText().trim());
				tfCep.setText(tfCep.getText().trim());
				tfNascimento.setText(tfNascimento.getText().trim());
				tfBairro.setText(tfBairro.getText().trim());
				tfRg.setText(tfRg.getText().trim());
				tfEmail.setText(tfEmail.getText().trim());
				tfProfissao.setText(tfProfissao.getText().trim());
				tfQuestao1.setText(tfQuestao1.getText().trim());

				if (tfNome.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Informar o nome");
					lbNome.setForeground(Color.red);
					tfNome.requestFocus(true);
				} else if (tfData.getValue() == null) {
					JOptionPane.showMessageDialog(null, "Informar a data");
					lbData.setForeground(Color.red);
					tfData.requestFocus(true);
				} else if (tfLogradouro.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Informar a rua");
					lbLogradouro.setForeground(Color.red);
					tfLogradouro.requestFocus(true);
				} else if (tfNumero.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Informar o numero");
					lbNumero.setForeground(Color.red);
					tfNumero.requestFocus(true);
				} else if (tfCep.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Informar o cep");
					lbCep.setForeground(Color.red);
					tfCep.requestFocus(true);
				} else if (tfNascimento.getValue() == null) {
					JOptionPane.showMessageDialog(null,
							"Informar a data de nascimento");
					lbNascimento.setForeground(Color.red);
					tfNascimento.requestFocus(true);
				} else if (tfCep.getValue() == null) {
					JOptionPane.showMessageDialog(null, "Informar o cep");
					lbCep.setForeground(Color.red);
					tfCep.requestFocus(true);
				} else if (jcSexo.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(null, "Informar o sexo");
					jcSexo.requestFocus(true);
				} else if (tfCpf.getValue() == null) {
					JOptionPane.showMessageDialog(null, "Informar o cpf");
					lbCpf.setForeground(Color.red);
					tfCpf.requestFocus(true);
				} else if (tfRegistroCnh.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"Informar o numero de registro da CNH");
					lbRegistroCnh.setForeground(Color.red);
					tfRegistroCnh.requestFocus(true);
				} else if (tfValidadeCnh.getValue() == null) {
					JOptionPane.showMessageDialog(null,
							"Informar a data de validade da CNH");
					lbValidadeCnh.setForeground(Color.red);
					tfValidadeCnh.requestFocus(true);
				} else if (tfPrimeiraHabilitacao.getValue() == null) {
					JOptionPane.showMessageDialog(null,
							"Informar a data da permição");
					lbPrimeiraHabilitacao.setForeground(Color.red);
					tfPrimeiraHabilitacao.requestFocus(true);
				} else if (jcPesquisa.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(null,
							"Selecionar uma das opções");
					jcPesquisa.requestFocus(true);
				} else {
					
					// populando os objetos
					cliente.setNome(tfNome.getText());
					cadastro.setData(tfData.getText());
					endereco.setLogradouro(tfLogradouro.getText());
					endereco.setNumero(tfNumero.getText());
					endereco.setBairro(tfBairro.getText());
					endereco.setCep(tfCep.getText());
					cliente.setNascimento(tfNascimento.getText());
					cliente.setSexo(jcSexo.getSelectedItem().toString());
					cliente.setCpf(tfCpf.getText());
					cliente.setRg(tfRg.getText());
					cliente.setTelefone(tfTelefone.getText());
					cliente.setCelular(tfCelular.getText());
					cliente.setEmail(tfEmail.getText());
					if (jcEscolaridade.getSelectedIndex() == -1) {
						cliente.setEscolaridade("");
					} else {
						cliente.setEscolaridade(jcEscolaridade
								.getSelectedItem().toString());
					}
					cliente.setProfissao(tfProfissao.getText());
					cnh.setRegistroCnh(tfRegistroCnh.getText());
					cnh.setDtValidade(tfValidadeCnh.getText());
					cnh.setPrimeiraHabilitacao(tfPrimeiraHabilitacao.getText());
					cadastro.setPesquisa1(tfQuestao1.getText());
					if (jrQ1Yes.isSelected()) {
						cadastro.setPesquisa2("sim");
					} else {
						cadastro.setPesquisa2("não");
					}
					if (jrQ3Yes.isSelected()) {
						cadastro.setPesquisa3("sim");
					} else {
						cadastro.setPesquisa3("não");
					}
					cadastro.setPesquisa4(jcPesquisa.getSelectedItem()
							.toString());
					cadastro.setObservacao(observa.getText());

					// inserindo no banco
					daoCliente = new DAOcliente();
					daoCliente.inserir(cliente, cnh, endereco, cadastro);
					populaList();
					limparCampos();

					// TODO não esquecer de aplicar o ternario para os
					// FORMATEDTEXTFIELD
					// limpar os campos após o cadastro.

				}

			}
		});

		tfNome.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				int caracteres = 50;
				if (tfNome.getText().length() >= caracteres
						&& e.getKeyCode() != 8) {
					e.consume();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				tfNome.setText(tfNome.getText().toUpperCase());
			}

			@Override
			public void keyPressed(KeyEvent e) {

			}
		});
		tfLogradouro.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				int caracteres = 50;
				if (tfLogradouro.getText().length() >= caracteres
						&& e.getKeyCode() != 8) {
					e.consume();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				tfLogradouro.setText(tfLogradouro.getText().toUpperCase());

			}

			@Override
			public void keyPressed(KeyEvent e) {

			}
		});
		tfNumero.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				int caracteres = 8;
				if (tfNumero.getText().length() >= caracteres
						&& e.getKeyCode() != 8) {
					e.consume();
				}

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {

			}
		});
		tfBairro.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				int caracteres = 30;
				if (tfBairro.getText().length() >= caracteres
						&& e.getKeyCode() != 8) {
					e.consume();
				}

			}

			@Override
			public void keyReleased(KeyEvent e) {
				tfBairro.setText(tfBairro.getText().toUpperCase());

			}

			@Override
			public void keyPressed(KeyEvent e) {

			}
		});
		tfEmail.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				int caracter = 40;
				if (tfEmail.getText().length() >= caracter
						&& e.getKeyCode() != 8) {
					e.consume();
				}

			}

			@Override
			public void keyReleased(KeyEvent e) {
				tfEmail.setText(tfEmail.getText().toLowerCase());

			}

			@Override
			public void keyPressed(KeyEvent e) {

			}
		});
		tfProfissao.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				int caracter = 25;
				if (tfProfissao.getText().length() >= caracter
						&& e.getKeyCode() != 8) {
					e.consume();
				}

			}

			@Override
			public void keyReleased(KeyEvent e) {
				tfProfissao.setText(tfProfissao.getText().toUpperCase());

			}

			@Override
			public void keyPressed(KeyEvent e) {

			}
		});

	}
}
