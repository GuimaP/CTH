package formulario;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import DAO.DAOcarro;
import DAO.DAOinstrutor;
import modelo.Carro;
import modelo.Funcionario;

public class CadastroInstrutor extends JFrame {

	private JLabel lbNome, lbData, lbRegistroCnh, lbValidadeCnh, lbPrimeiraCnh,
			lbTelefone, lbCelular, lbRg, lbCpf, lbCarro, lbStatus;
	private JTextField tfNome, tfRegistroCnh, tfRg;
	private JFormattedTextField tfData, tfValidadeCnh, tfPrimeiraCnh,
			tfTelefone, tfCelular, tfCpf;
	private MaskFormatter maskData, maskValidadeCnh, maskPrimeiraCnh,
			maskTelefone, maskCelular, maskCpf;
	private JComboBox<Carro> jcCarro;
	private JComboBox<String> jcStatus;
	private JButton btSalvar, btBuscar, btExcluir;
	private String[] status = { "Ativo", "Inativo" };
	List<Carro> carroList;
	Carro[] carroVetor;
	DAOcarro daoCarro = new DAOcarro();
	Carro carro = new Carro();

	public CadastroInstrutor() {
		try {
			inicializaComponentes();
			definirEventos();
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return;

		}
	}

	public void inicializaComponentes() throws ParseException, SQLException {
		// Label

		lbNome = new JLabel("Nome");
		lbNome.setLocation(30, 40);
		lbNome.setSize(50, 20);
		getContentPane().add(lbNome);

		lbData = new JLabel("Data");
		lbData.setLocation(350, 40);
		lbData.setSize(50, 20);
		getContentPane().add(lbData);

		lbRegistroCnh = new JLabel("Nº Cnh");
		lbRegistroCnh.setLocation(30, 80);
		lbRegistroCnh.setSize(100, 20);
		getContentPane().add(lbRegistroCnh);

		lbValidadeCnh = new JLabel("Validade");
		lbValidadeCnh.setLocation(180, 80);
		lbValidadeCnh.setSize(100, 20);
		getContentPane().add(lbValidadeCnh);

		lbPrimeiraCnh = new JLabel("Dt Permissão");
		lbPrimeiraCnh.setLocation(310, 80);
		lbPrimeiraCnh.setSize(100, 20);
		getContentPane().add(lbPrimeiraCnh);

		lbRg = new JLabel("Rg");
		lbRg.setLocation(120, 120);
		lbRg.setSize(100, 20);
		getContentPane().add(lbRg);

		lbCpf = new JLabel("Cpf");
		lbCpf.setLocation(255, 120);
		lbCpf.setSize(50, 20);
		getContentPane().add(lbCpf);

		lbTelefone = new JLabel("Telefone");
		lbTelefone.setLocation(30, 160);
		lbTelefone.setSize(100, 20);
		getContentPane().add(lbTelefone);

		lbCelular = new JLabel("Celular");
		lbCelular.setLocation(180, 160);
		lbCelular.setSize(100, 20);
		getContentPane().add(lbCelular);

		lbCarro = new JLabel("Carro");
		lbCarro.setLocation(335, 160);
		lbCarro.setSize(50, 20);
		getContentPane().add(lbCarro);

		lbStatus = new JLabel("Status");
		lbStatus.setLocation(150, 200);
		lbStatus.setSize(100, 20);
		getContentPane().add(lbStatus);
		// tfNome
		tfNome = new JTextField();
		tfNome.setLocation(75, 40);
		tfNome.setSize(260, 20);
		getContentPane().add(tfNome);

		tfRegistroCnh = new JTextField();
		tfRegistroCnh.setLocation(73, 80);
		tfRegistroCnh.setSize(100, 20);
		getContentPane().add(tfRegistroCnh);

		tfRg = new JTextField();
		tfRg.setLocation(140, 120);
		tfRg.setSize(100, 20);
		getContentPane().add(tfRg);

		maskData = new MaskFormatter("##/##/####");
		maskData.setPlaceholderCharacter('_');
		tfData = new JFormattedTextField(maskData);
		tfData.setLocation(385, 40);
		tfData.setSize(68, 20);
		getContentPane().add(tfData);

		maskValidadeCnh = new MaskFormatter("##/##/####");
		maskValidadeCnh.setPlaceholderCharacter('_');
		tfValidadeCnh = new JFormattedTextField(maskValidadeCnh);
		tfValidadeCnh.setLocation(235, 80);
		tfValidadeCnh.setSize(68, 20);
		getContentPane().add(tfValidadeCnh);

		maskPrimeiraCnh = new MaskFormatter("##/##/####");
		maskPrimeiraCnh.setPlaceholderCharacter('_');
		tfPrimeiraCnh = new JFormattedTextField(maskPrimeiraCnh);
		tfPrimeiraCnh.setLocation(390, 80);
		tfPrimeiraCnh.setSize(68, 20);
		getContentPane().add(tfPrimeiraCnh);

		maskCpf = new MaskFormatter("###.###.###-##");
		maskCpf.setPlaceholderCharacter('_');
		tfCpf = new JFormattedTextField(maskCpf);
		tfCpf.setLocation(280, 120);
		tfCpf.setSize(93, 20);
		getContentPane().add(tfCpf);

		maskTelefone = new MaskFormatter("(##)####-####");
		maskTelefone.setPlaceholderCharacter('_');
		tfTelefone = new JFormattedTextField(maskTelefone);
		tfTelefone.setLocation(85, 160);
		tfTelefone.setSize(88, 20);
		getContentPane().add(tfTelefone);

		maskCelular = new MaskFormatter("(##)#-####-####");
		maskCelular.setPlaceholderCharacter('_');
		tfCelular = new JFormattedTextField(maskCelular);
		tfCelular.setLocation(227, 160);
		tfCelular.setSize(98, 20);
		getContentPane().add(tfCelular);
		// Combobox

		jcCarro = new JComboBox<Carro>();
		carroList = daoCarro.buscarCarro();
		for (int i = 0; i < carroList.size(); i++) {
			jcCarro.addItem(carroList.get(i));
		}

		jcCarro.setLocation(375, 160);
		jcCarro.setSize(120, 20);
		jcCarro.setSelectedIndex(-1);
		getContentPane().add(jcCarro);

		jcStatus = new JComboBox<String>(status);
		jcStatus.setLocation(195, 200);
		jcStatus.setSize(100, 20);
		jcStatus.setSelectedIndex(-1);
		getContentPane().add(jcStatus);
		// Button
		btSalvar = new JButton("Salvar");
		btSalvar.setLocation(80, 230);
		btSalvar.setSize(100, 30);
		getContentPane().add(btSalvar);

		btBuscar = new JButton("Buscar");
		btBuscar.setLocation(180, 230);
		btBuscar.setSize(100, 30);
		getContentPane().add(btBuscar);

		btExcluir = new JButton("Excluir");
		btExcluir.setLocation(280, 230);
		btExcluir.setSize(100, 30);
		getContentPane().add(btExcluir);
		//
		getContentPane().setLayout(null);
		setSize(500, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
		setTitle("Cadastro Instrutor");
		setResizable(false);
		getContentPane().setBackground(Color.lightGray);

	}

	public void definirEventos() {
		btSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tfNome.setText(tfNome.getText().trim());
				tfRegistroCnh.setText(tfRegistroCnh.getText().trim());
				tfRg.setText(tfRg.getText().trim());
				if (tfNome.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Informar o nome");
					lbNome.setForeground(Color.RED);
					tfNome.requestFocus(true);
				} else if (tfData.getValue() == null) {
					JOptionPane.showMessageDialog(null, "Informar a data");
					lbData.setForeground(Color.RED);
					tfData.requestFocus(true);
				} else if (tfRegistroCnh.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"Informar o registro da CNH");
					lbRegistroCnh.setForeground(Color.RED);
					tfRegistroCnh.requestFocus(true);
				} else if (tfValidadeCnh.getValue() == null) {
					JOptionPane.showMessageDialog(null,
							"Informar a data de validade");
					lbValidadeCnh.setForeground(Color.RED);
					tfValidadeCnh.requestFocus(true);
				} else if (tfPrimeiraCnh.getValue() == null) {
					JOptionPane.showMessageDialog(null,
							"Informar a data da Permissão");
					lbPrimeiraCnh.setForeground(Color.RED);
					tfPrimeiraCnh.requestFocus(true);
				} else if (tfCpf.getValue() == null) {
					JOptionPane.showMessageDialog(null, "Informar Cpf");
					lbCpf.setForeground(Color.RED);
					tfCpf.requestFocus(true);
				} else if (jcStatus.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(null, "Informar o Status");
					lbStatus.setForeground(Color.RED);
					jcStatus.requestFocus(true);
				} else if (jcCarro.getSelectedIndex() == -1){
					JOptionPane.showMessageDialog(null, "Carro não selecionado ou não cadastrado");
					jcCarro.requestFocus(true);
				}else {
					Funcionario instrutor = new Funcionario();
					// populando o objeto
					instrutor.setNome(tfNome.getText());
					instrutor.setData(tfData.getValue().toString());
					instrutor.setCnh(tfRegistroCnh.getText());
					instrutor
							.setValidadeCnh(tfValidadeCnh.getText().toString());
					instrutor.setPrimeiraCnh(tfPrimeiraCnh.getValue()
							.toString());
					instrutor.setCpf(tfCpf.getValue().toString());
					instrutor.setRg(tfRg.getText());
					instrutor.setTelefone(tfTelefone.getValue() == null ? "NÃO HÁ"
							: tfTelefone.getValue().toString());
					instrutor.setCelular(tfCelular.getValue() == null ? "NÃO HÁ"
							: tfCelular.getValue().toString());
					instrutor.setStatus(jcStatus.getSelectedItem().toString());
					instrutor.setTbCarroPlacaCarro((Carro) jcCarro
							.getSelectedItem());
					DAOinstrutor daoInstrutor = new DAOinstrutor();
					daoInstrutor.inserir(instrutor);

				}

			}
		});
			tfNome.addKeyListener(new KeyListener() {
				@Override
				public void keyTyped(KeyEvent e) {
					int caracteres = 40;
					if (tfNome.getText().length() >= caracteres && e.getKeyCode() != 8){
						e.consume();
					}
					
				}
				@Override
				public void keyReleased(KeyEvent e) {
					tfNome.setText(tfNome.getText().toUpperCase());
					}
				
				@Override
				public void keyPressed(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
	}

}
