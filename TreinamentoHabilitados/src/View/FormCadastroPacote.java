/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.ModeloTablePacote;
import Model.Pacote;
import Model.Repository.Repository;
import Model.Repository.RepositoryPacote;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

/**
 *
 * @author Vitor
 */
public class FormCadastroPacote extends JInternalFrame {

    private JLabel lbDescricao, lbAulas, lbPrecoAula, lbPrecoPacote;

    private JTextField tfDescricao, tfAulas, tfPrecoAula, tfPrecoPacote;

    private JButton btSalvar, btBuscar, btExcluir;

    private JTabbedPane aba;

    private JPanel pnGeral, pnBusca;

    private JTable tabela;

    private JScrollPane scroll;

    private List<Pacote> listPacote = new ArrayList<Pacote>();

    private Pacote pacote;

    public static boolean isDialogBuscaPacoteOpen;

    public FormCadastroPacote() {
        isDialogBuscaPacoteOpen = false;
        listPacote = new RepositoryPacote().buscarPacote();
        inicializaComponentes();
        definirEventos();
        populaTable();
    }

    public void populaTable() {
    	List<Pacote> ls = new ArrayList<Pacote>();
        for (Pacote p : listPacote) {
        
        	listPacote = new RepositoryPacote().buscarPacote();
        	tabela.setModel(new ModeloTablePacote(listPacote));
        	scroll.revalidate();
        	
        }
    }

    public void limparCampos() {

    }

    public void inicializaComponentes() {
        // Paineis das abas

        pnGeral = new JPanel();
        pnGeral.setLayout(null);
        pnBusca = new JPanel();
        pnBusca.setLayout(null);

        //Componentes da Aba de Cadastro
        //Descricao;
        lbDescricao = new JLabel("Descri��o");
        lbDescricao.setSize(100, 20);  //100 20
        lbDescricao.setLocation(5, 20); //5 60
        pnGeral.add(lbDescricao);

        tfDescricao = new JTextField();
        tfDescricao.setSize(300, 25); //300 25
        tfDescricao.setLocation(70, 20); // 70 60
        pnGeral.add(tfDescricao);

        //Aulas
        lbAulas = new JLabel("N� Aulas");
        lbAulas.setSize(100, 20);
        lbAulas.setLocation(5, 50);
        pnGeral.add(lbAulas);

        tfAulas = new JTextField();
        tfAulas.setSize(50, 25);
        tfAulas.setLocation(70, 50);
        pnGeral.add(tfAulas);

        //Preço Aula
        lbPrecoAula = new JLabel("Pre�o Aula");
        lbPrecoAula.setSize(100, 20);
        lbPrecoAula.setLocation(5, 100);
        pnGeral.add(lbPrecoAula);

        tfPrecoAula = new JTextField();
        tfPrecoAula.setSize(80, 25);
        tfPrecoAula.setLocation(70, 100);
        pnGeral.add(tfPrecoAula);

        //Preço Pacote
        lbPrecoPacote = new JLabel("Pacote");
        lbPrecoPacote.setSize(100, 20);
        lbPrecoPacote.setLocation(5, 130);
        pnGeral.add(lbPrecoPacote);

        tfPrecoPacote = new JTextField();
        tfPrecoPacote.setSize(80, 25);
        tfPrecoPacote.setLocation(70, 130);
        pnGeral.add(tfPrecoPacote);

        //Botão
        btSalvar = new JButton("Salvar");
        btSalvar.setSize(100, 30);
        btSalvar.setLocation(140, 160);
        pnGeral.add(btSalvar);

        // Componentes da Aba de Busca
        tabela = new JTable(new ModeloTablePacote(listPacote));
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
        setTitle("Cadastro de Pacote");
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
                    JOptionPane.showMessageDialog(null, "Informar o numero de aulas");
                    tfAulas.requestFocus();
                    lbAulas.setForeground(Color.red);
                } else if (tfPrecoAula.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Informar o pre�o da aula");
                    tfPrecoAula.requestFocus();
                    lbPrecoAula.setForeground(Color.red);
                } else if (tfPrecoPacote.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Informar o pre�o do pacote");
                    tfPrecoPacote.requestFocus();
                    lbPrecoPacote.setForeground(Color.red);
                } else {
                	RepositoryPacote repo= new RepositoryPacote();
                	if(pacote == null){
                		pacote = new Pacote();
                	}
                	
                	pacote.setAulas(tfAulas.getText());
                	pacote.setDescAulas(tfDescricao.getText());
                	pacote.setDescricao(tfDescricao.getText());
                	pacote.setPrecoPacote(tfPrecoPacote.getText());
                	pacote.setPrecoAula(tfPrecoAula.getText());
                    repo.adicionar(pacote);
<<<<<<< HEAD
=======
                    listPacote = new RepositoryPacote().buscarPacote();
>>>>>>> b466833785be140a9edc44a6aed6f7c4ffa50afc
                    populaTable();
                }

            }
        });

    }

}
