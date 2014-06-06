package formulario;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import DAO.DAOcliente;
import DAO.DAOinstrutor;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import modelo.Carro;
import modelo.Cliente;
import modelo.Funcionario;
import modelo.ModeloTable;
import modelo.ModeloTableFun;

public class PacoteAulas extends JFrame {

	private JComboBox<Cliente> jcCliente;
	private JComboBox<Funcionario> jcFuncionario;
	private JComboBox<String> jcTipoPagamento;

	private JLabel lbCliente, lbFuncionario, lbAulas, lbPreco, lbTipoPagamento,
			lbParcelas, lbTotalAPagar, lbTotalRecebido;

	private JTextField tfAulas, tfPreco, tfParcelas, tfTotalAPagar,
			tfTotalRecebidoField;

	private JTable tableFun, tableCli;
	private JScrollPane scroll, scTable;

	private JButton btSalvar;
	private String[] pagamento = { "Dinheiro", "Débito", "Crédito" };

	private ArrayList<Funcionario> funcionarioList = new ArrayList<Funcionario>();
	private ArrayList<Cliente> clienteList = new ArrayList<Cliente>();

	private DAOinstrutor dao;
	private DAOcliente daoC;

	public PacoteAulas() {
		try {
			

			dao = new DAOinstrutor();
			daoC = new DAOcliente();
			funcionarioList = dao.buscarFunc();
			clienteList = daoC.buscarTodos();
			populaListCli();
			populaListFunc();
			inicializarComponentes();

		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}

	}

	public void populaListFunc() {
		ArrayList<Funcionario> l = new ArrayList<Funcionario>();
		try {
			for (Funcionario f : funcionarioList) {
				l = dao.buscarFunc();
				funcionarioList = l;
				tableFun = new JTable(new ModeloTableFun(funcionarioList));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void populaListCli() {
		ArrayList<Cliente> l = new ArrayList<Cliente>();
		try {
			for (Cliente c : clienteList) {
				l = daoC.buscarTodos();
				clienteList = l;
				tableCli = new JTable(new ModeloTable(clienteList));
			}

		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	public void inicializarComponentes() throws ParseException, SQLException {
		// Label
		lbCliente = new JLabel("Cliente");
		lbCliente.setLocation(40, 40);
		lbCliente.setSize(50, 20);
		getContentPane().add(lbCliente);

		lbFuncionario = new JLabel("Instrutor");
		lbFuncionario.setLocation(40, 70);
		lbFuncionario.setSize(70, 20);
		getContentPane().add(lbFuncionario);

		lbAulas = new JLabel("Numero de aulas");
		lbAulas.setLocation(40, 100);
		lbAulas.setSize(120, 20);
		getContentPane().add(lbAulas);

		lbPreco = new JLabel("Preço Aula");
		lbPreco.setLocation(220, 100);
		lbPreco.setSize(100, 20);
		getContentPane().add(lbPreco);

		lbParcelas = new JLabel("Parcelas");
		lbParcelas.setLocation(400, 100);
		lbParcelas.setSize(100, 20);
		getContentPane().add(lbParcelas);

		lbTipoPagamento = new JLabel("Tipo Pagamento");
		lbTipoPagamento.setLocation(40, 130);
		lbTipoPagamento.setSize(100, 20);
		getContentPane().add(lbTipoPagamento);

		lbTotalRecebido = new JLabel("Total Recebido");
		lbTotalRecebido.setLocation(40, 160);
		lbTotalRecebido.setSize(100, 20);
		getContentPane().add(lbTotalRecebido);

		lbTotalAPagar = new JLabel("Total");
		lbTotalAPagar.setLocation(40, 190);
		lbTotalAPagar.setSize(50, 20);
		getContentPane().add(lbTotalAPagar);
		// Combobox
		tableCli = new JTable(new ModeloTable(clienteList));
		scTable = new JScrollPane(tableCli);
		scTable.setSize(350, 200);
		scTable.setLocation(100, 40);
		getContentPane().add(scTable);

		/*
		 * jcFuncionario = new JComboBox<Funcionario>(); funcionarioList =
		 * dao.buscarFunc(); for (int i = 0; i < funcionarioList.size(); i++) {
		 * jcFuncionario.addItem(funcionarioList.get(i)); }
		 * jcFuncionario.setLocation(100, 70); jcFuncionario.setSize(400, 20);
		 * jcFuncionario.setSelectedIndex(-1);
		 * getContentPane().add(jcFuncionario);
		 */

		jcTipoPagamento = new JComboBox<String>(pagamento);
		jcTipoPagamento.setLocation(145, 130);
		jcTipoPagamento.setSize(80, 20);
		jcTipoPagamento.setSelectedIndex(-1);
		getContentPane().add(jcTipoPagamento);

		tfAulas = new JTextField();
		tfAulas.setLocation(145, 100);
		tfAulas.setSize(50, 20);
		getContentPane().add(tfAulas);

		tfPreco = new JTextField();
		tfPreco.setLocation(290, 100);
		tfPreco.setSize(70, 20);
		getContentPane().add(tfPreco);

		tfParcelas = new JTextField();
		tfParcelas.setLocation(460, 100);
		tfParcelas.setSize(70, 20);
		getContentPane().add(tfParcelas);

		tfTotalRecebidoField = new JTextField();
		tfTotalRecebidoField.setLocation(145, 160);
		tfTotalRecebidoField.setSize(70, 20);
		getContentPane().add(tfTotalRecebidoField);

		tfTotalAPagar = new JTextField();
		tfTotalAPagar.setLocation(145, 190);
		tfTotalAPagar.setSize(70, 20);
		getContentPane().add(tfTotalAPagar);
		// button
		btSalvar = new JButton("Salvar");
		btSalvar.setLocation(220, 250);
		btSalvar.setSize(150, 100);
		getContentPane().add(btSalvar);

		getContentPane().setLayout(null);
		setSize(600, 400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
		setTitle("Pacote de aulas");
		setResizable(false);
		getContentPane().setBackground(Color.lightGray);

	}
}
