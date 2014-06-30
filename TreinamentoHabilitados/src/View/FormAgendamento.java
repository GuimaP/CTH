/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import Model.Cliente;
import Model.ModeloTableCliente;

/**
 *
 * @author Vitor
 */
public class FormAgendamento extends JInternalFrame{
    private JLabel lbBusca, lbAulas, lbPrecoAula;
    
    private JTextField tfBusca;
    
    private JPanel panelAluno, panelInstrutor;
    
    private JTable tabelaAluno;
    
    private JScrollPane scroll;
    
    private JInternalFrame internal;
    
    private ArrayList<Cliente> listCliente = new ArrayList<Cliente>();
    
    public FormAgendamento(){
        internal = this;
        inicializaComponentes();
        
    }
    
    public void inicializaComponentes(){
        // Paineis 
        
        panelAluno = new JPanel();
	Border border = BorderFactory.createTitledBorder("Aluno");
	panelAluno.setBorder(border);
	panelAluno.setLayout(new GridLayout(8,2));
	panelAluno.setSize(300, 250);
        panelAluno.setLocation(10, 10);
 
        //Table do cliente + busca
        
        lbBusca = new JLabel("Buscar");
        lbBusca.setSize(100, 20);
        lbBusca.setLocation(20, 40);
        panelAluno.add(lbBusca);
        
        tfBusca = new JTextField();
        tfBusca.setSize(280, 30);
        tfBusca.setLocation(20, 60);
        panelAluno.add(tfBusca);
        
        
        tabelaAluno = new JTable(new ModeloTableCliente(listCliente));
        scroll = new JScrollPane(tabelaAluno);
        scroll.setSize(280, 150);
        scroll.setLocation(20, 100);
        panelAluno.add(scroll);
        
         add(panelAluno);
        
        
        
         

      
        
        
        
        
       
        
        
        
        
        
        
        
                getContentPane().setLayout(null);
		setSize(700, 650);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setClosable(true);
		setTitle("Agendamento de aulas");
		setResizable(false);
		setIconifiable(true);
                
                
        
        
    }
    
}
