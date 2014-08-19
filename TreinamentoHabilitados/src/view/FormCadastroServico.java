/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.Carro;
import model.Servico;
import model.repository.Repository;
import model.repository.RepositoryServico;
import model.table.ModeloTableServico;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

/**
 *
 * @author Vitor
 */
public class FormCadastroServico extends JInternalFrame {

	private JLabel lbDescricao, lbAulas, lbPrecoAula, lbPrecoPacote;

	private JTextField tfDescricao, tfAulas, tfPrecoAula, tfPrecoPacote;

	private JButton btSalvar, btBuscar, btExcluir, btNovo;

	private JTabbedPane aba;

	private JPanel pnGeral, pnBusca;

	private JTable tabela;

	private JScrollPane scroll;

	private List<Servico> listPacote = new ArrayList<Servico>();

	private Servico servico;

	public static boolean isDialogBuscaPacoteOpen;

	public FormCadastroServico() {
		try {
			isDialogBuscaPacoteOpen = false;
			listPacote = new RepositoryServico().buscarServico();
			inicializaComponentes();
			definirEventos();
			populaTable();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void populaTable() {

		try {
			List<Servico> ls = new ArrayList<Servico>();
			for (Servico p : listPacote) {

				listPacote = new RepositoryServico().buscarServico();
				tabela.setModel(new ModeloTableServico(listPacote));
				scroll.revalidate();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

	}

	public void limparCampos() {
		servico = null;
		tfAulas.setText("");
		tfDescricao.setText("");
		tfPrecoAula.setText("");
		tfPrecoPacote.setText("");
	}

	public void inicializaComponentes() {
		// Paineis das abas

		pnGeral = new JPanel();
		pnGeral.setLayout(null);
		pnBusca = new JPanel();
		pnBusca.setLayout(null);

		// Componentes da Aba de Cadastro
		// Descricao;
		lbDescricao = new JLabel("Descri��o");
		lbDescricao.setSize(100, 20); // 100 20
		lbDescricao.setLocation(5, 20); // 5 60
		pnGeral.add(lbDescricao);

		tfDescricao = new JTextField();
		tfDescricao.setSize(300, 25); // 300 25
		tfDescricao.setLocation(70, 20); // 70 60
		pnGeral.add(tfDescricao);

		// Aulas
		lbAulas = new JLabel("N� Aulas");
		lbAulas.setSize(100, 20);
		lbAulas.setLocation(5, 50);
		pnGeral.add(lbAulas);

		tfAulas = new JTextField();
		tfAulas.setSize(50, 25);
		tfAulas.setLocation(70, 50);
		pnGeral.add(tfAulas);

		// Preço Aula
		lbPrecoAula = new JLabel("Pre�o Aula");
		lbPrecoAula.setSize(100, 20);
		lbPrecoAula.setLocation(5, 100);
		pnGeral.add(lbPrecoAula);

		tfPrecoAula = new JTextField();
		tfPrecoAula.setSize(80, 25);
		tfPrecoAula.setLocation(70, 100);
		pnGeral.add(tfPrecoAula);

		// Preço Servico
		lbPrecoPacote = new JLabel("Servico");
		lbPrecoPacote.setSize(100, 20);
		lbPrecoPacote.setLocation(5, 130);
		pnGeral.add(lbPrecoPacote);

		tfPrecoPacote = new JTextField();
		tfPrecoPacote.setSize(80, 25);
		tfPrecoPacote.setLocation(70, 130);
		pnGeral.add(tfPrecoPacote);

		// Botão
		btSalvar = new JButton("Salvar");
		btSalvar.setSize(100, 30);
		btSalvar.setLocation(70, 160);
		pnGeral.add(btSalvar);

		btNovo = new JButton("Novo");
		btNovo.setBounds(200, 160, 100, 30);
		pnGeral.add(btNovo);

		// Componentes da Aba de Busca
		tabela = new JTable(new ModeloTableServico(listPacote));
		scroll = new JScrollPane(tabela);
		scroll.setSize(371, 150);
		scroll.setLocation(2, 5);
		pnBusca.add(scroll);

		btExcluir = new JButton("Excluir");
		btExcluir.setSize(100, 30);
		btExcluir.setLocation(140, 160);
		pnBusca.add(btExcluir);

		aba = new JTabbedPane();
		aba.setBounds(1, 1, 385, 295);
		aba.addTab("Cadastro", pnGeral);
		aba.addTab(" Busca  ", pnBusca);
		add(aba);

		getContentPane().setLayout(null);
		setSize(390, 280);
		setLocation(60, 10);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setClosable(true);
		setTitle("Cadastro de Servico");
		setResizable(false);
		setIconifiable(true);

	}

	public void definirEventos() {
		btSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tfDescricao.setText(tfDescricao.getText().trim());
				tfAulas.setText(tfAulas.getText().trim());
				tfPrecoAula.setText(tfPrecoAula.getText().trim());
				tfPrecoPacote.setText(tfPrecoPacote.getText().trim());

				if (tfDescricao.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Informar descri��o");
					tfDescricao.requestFocus();
					lbDescricao.setForeground(Color.red);
				} else if (tfAulas.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"Informar o numero de aulas");
					tfAulas.requestFocus();
					lbAulas.setForeground(Color.red);
				} else if (tfPrecoAula.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"Informar o pre�o da aula");
					tfPrecoAula.requestFocus();
					lbPrecoAula.setForeground(Color.red);
				} else if (tfPrecoPacote.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"Informar o pre�o do servico");
					tfPrecoPacote.requestFocus();
					lbPrecoPacote.setForeground(Color.red);
				} else {
					RepositoryServico repo = new RepositoryServico();
					if (servico == null) {
						servico = new Servico();
					}

					try {
						servico.setAulas(tfAulas.getText());
						servico.setDescAulas(tfDescricao.getText());
						servico.setDescricao(tfDescricao.getText());
						servico.setPrecoPacote(tfPrecoPacote.getText());
						servico.setPrecoAula(tfPrecoAula.getText());
						repo.adicionar(servico);
						listPacote = repo.buscarServico();
						populaTable();
						limparCampos();
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, e2.getMessage());
						e2.printStackTrace();
					}

				}

			}
		});

		tabela.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {

					int index = tabela.getSelectedRow();
					Servico servicoTable = ((ModeloTableServico) tabela
							.getModel()).getServico(index);
					servico = servicoTable;
					aba.setSelectedIndex(0);

					tfAulas.setText(servico.getAulas());
					tfDescricao.setText(servico.getDescricao());
					tfPrecoAula.setText(servico.getPrecoAula());
					tfPrecoPacote.setText(servico.getPrecoPacote());

				}

			}
		});
		btNovo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				limparCampos();

			}
		});
		
		this.addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
				super.internalFrameClosing(e);
				Principal.isFrameServico = false;
			}
		});
	}

}
