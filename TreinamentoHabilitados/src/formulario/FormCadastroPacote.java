/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formulario;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author Vitor
 */
public class FormCadastroPacote extends JInternalFrame {

    private JLabel lbDescricao, lbAulas, lbPrecoAula, lbPrecoPacote;
    
    private JTextField tfDescricao, tfAulas, tfPrecoAula, tfPrecoPacote;
    
    private JButton btSalvar, btBuscar, btExcluir;
    
    public static boolean isDialogBuscaPacoteOpen;
    
    public FormCadastroPacote() {
        isDialogBuscaPacoteOpen = false;
        inicializaComponentes();
        definirEventos();
    }

    public void inicializaComponentes() {
        //Descricao;
        
        lbDescricao = new JLabel("Descrição");
        lbDescricao.setSize(100, 20);
        lbDescricao.setLocation(5, 10);
        add(lbDescricao);
        
        tfDescricao = new JTextField();
        tfDescricao.setSize(300, 25);
        tfDescricao.setLocation(70, 10);
        add(tfDescricao);
        
        //Aulas
        
        lbAulas = new JLabel("Nº Aulas");
        lbAulas.setSize(100, 20);
        lbAulas.setLocation(5, 40);
        add(lbAulas);
        
       tfAulas = new JTextField();
       tfAulas.setSize(50, 25);
       tfAulas.setLocation(70, 40);
        add(tfAulas);
        
        //Preço Aula
        
        lbPrecoAula = new JLabel("Preço Aula");
        lbPrecoAula.setSize(100, 20);
        lbPrecoAula.setLocation(5, 90);
        add(lbPrecoAula);
        
        tfPrecoAula = new JTextField();
        tfPrecoAula.setSize(80, 25);
        tfPrecoAula.setLocation(70, 90);
        add(tfPrecoAula);
        
        //Preço Pacote
        
        lbPrecoPacote = new JLabel("Pacote");
        lbPrecoPacote.setSize(100, 20);
        lbPrecoPacote.setLocation(5, 120);
        add(lbPrecoPacote);
        
        tfPrecoPacote = new JTextField();
        tfPrecoPacote.setSize(80, 25);
        tfPrecoPacote.setLocation(70, 120);
        add(tfPrecoPacote);
        
        //Botão
        
        btSalvar = new JButton("Salvar");
        btSalvar.setSize(100, 30);
        btSalvar.setLocation(30, 150);
        add(btSalvar);
        
        btBuscar = new JButton("Buscar");
        btBuscar.setSize(100, 30);
        btBuscar.setLocation(140, 150);
        add(btBuscar);
        
        btExcluir = new JButton("Excluir");
        btExcluir.setSize(100, 30);
        btExcluir.setLocation(250, 150);
        add(btExcluir);

        getContentPane().setLayout(null);
        setSize(390, 240);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setClosable(true);
        setTitle("Cadastro de Pacote");
        setResizable(false);
        setIconifiable(true);

    }
    
    public void definirEventos(){
        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfDescricao.setText(tfDescricao.getText().trim());
                tfAulas.setText(tfAulas.getText().trim());
                tfPrecoAula.setText(tfPrecoAula.getText().trim());
                tfPrecoPacote.setText(tfPrecoPacote.getText().trim());
                
                if(tfDescricao.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Informar descrição");
                    tfDescricao.requestFocus();
                    lbDescricao.setForeground(Color.red);
                }else if (tfAulas.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Informar o numero de aulas");
                    tfAulas.requestFocus();
                    lbAulas.setForeground(Color.red);
                }else if (tfPrecoAula.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Informar o preço da aula");
                    tfPrecoAula.requestFocus();
                    lbPrecoAula.setForeground(Color.red);
                }else if (tfPrecoPacote.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Informar o preço do pacote");
                    tfPrecoPacote.requestFocus();
                    lbPrecoPacote.setForeground(Color.red);
                }else {
                    
                    //Popular objeto Pacote e implementar a inserção no banco através do hybernate.
                    
                }
                
                
            }
        });
        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // implantar dialog
            }
        });
        btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // implementar o botao excluir atraves do objeto selecionado na dialog de busca
            }
        });
    }
       
}
