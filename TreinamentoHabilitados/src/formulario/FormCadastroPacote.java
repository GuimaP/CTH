/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package formulario;

import javax.swing.JInternalFrame;

/**
 *
 * @author Vitor
 */
public class FormCadastroPacote extends JInternalFrame{
    
    
    public FormCadastroPacote(){
        inicializaComponentes();
    }
    
    public void inicializaComponentes(){
        getContentPane().setLayout(null);
		setSize(700, 650);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setClosable(true);
		setTitle("Cadastro de Pacotes");
		setResizable(false);
		setIconifiable(true);
    }
    
    
}
