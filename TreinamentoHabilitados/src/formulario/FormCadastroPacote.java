/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formulario;

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
import modelo.Cliente;
import modelo.ModeloTable;

/**
 *
 * @author Vitor
 */
public class FormCadastroPacote extends JInternalFrame{
    private JLabel lbBusca, lbAulas, lbPrecoAula;
    
    private JTextField tfBusca;
    
    private JPanel panelCliente;
    
    private JTable tabela;
    
    private JScrollPane scroll;
    
    private JInternalFrame internal;
    
    private ArrayList<Cliente> listCliente = new ArrayList<Cliente>();
    
    public FormCadastroPacote(){
        internal = this;
        inicializaComponentes();
        
    }
    
    public void inicializaComponentes(){
        // Paineis 
        
        panelCliente = new JPanel();
	Border border = BorderFactory.createTitledBorder("Cliente");
	panelCliente.setBorder(border);
	panelCliente.setLayout(new GridLayout(8,2));
	panelCliente.setSize(300, 250);
        panelCliente.setLocation(10, 10);
        
        //Table do cliente + busca
        
        lbBusca = new JLabel("Buscar");
        lbBusca.setSize(100, 20);
        lbBusca.setLocation(20, 40);
        panelCliente.add(lbBusca);
        
        tfBusca = new JTextField();
        tfBusca.setSize(280, 30);
        tfBusca.setLocation(20, 60);
        panelCliente.add(tfBusca);
        
        
        tabela = new JTable(new ModeloTable(listCliente));
        scroll = new JScrollPane(tabela);
        scroll.setSize(280, 150);
        scroll.setLocation(20, 100);
        panelCliente.add(scroll);
        
         add(panelCliente);
        
        
        


      
        
        
        
        
       
        
        
        
        
        
        
        
            
               getContentPane().setLayout(null);
		setSize(500, 500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setClosable(true);
		setTitle("Cadastro de Pacotes");
		setResizable(false);
		setIconifiable(true);
                
                
        
        
    }
    
}
