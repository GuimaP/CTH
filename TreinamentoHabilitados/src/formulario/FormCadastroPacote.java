/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formulario;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;

/**
 *
 * @author Vitor
 */
public class FormCadastroPacote extends JInternalFrame{
    private JLabel lbDescricao, lbAulas, lbPreçoAula;
    
    
    
    
    private JInternalFrame internal;
    
    public FormCadastroPacote(){
        internal = this;
        inicializaComponentes();
        
    }
    
    public void inicializaComponentes(){
        setLayout(null);
        
        lbDescricao = new JLabel("Descrição");
        lbDescricao.setBounds(10, 10, 100, 20);
        add(lbDescricao);
        
        
                setSize(500, 300);
		setLocation(10, 10);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setClosable(true);
		setIconifiable(true);
		setTitle("Cadastro de Pacote de Aulas");
		show();
        
        
    }
    
}
