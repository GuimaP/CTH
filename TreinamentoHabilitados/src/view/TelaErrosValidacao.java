package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class TelaErrosValidacao extends JFrame{

	//erros tela Servico
	
	public static void erroNumeroAulas(){
		JOptionPane.showMessageDialog(null, "Numero de aulas obrigatorio");
	}
	public static void erroDescPacote(){
		JOptionPane.showMessageDialog(null, "Descrição do pacote obrigatório");
	}
	public static void erroPrecoAula(){
		JOptionPane.showMessageDialog(null, "Preço da aula obrigatório");
	}
	//fim erros tela Servico
	
	//erros tela carro
	public static void erroAno(){
		JOptionPane.showMessageDialog(null, "Ano obrigatório");
	}
	public static void erroMarca(){
		JOptionPane.showMessageDialog(null, "Marca obrigatório");
	}
	public static void erroModelo(){
		JOptionPane.showMessageDialog(null, "Modelo obrigatório");
	}
	public static void erroPlaca(){
		JOptionPane.showMessageDialog(null, "Placa obrigatório");
	}
	//fim erros tela carro
	
}
