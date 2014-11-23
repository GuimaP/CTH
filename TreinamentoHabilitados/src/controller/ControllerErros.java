package controller;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class ControllerErros extends JFrame{

	//erros tela Servico
	
	public static void erroNumeroAulas(){
		JOptionPane.showMessageDialog(null, "Numero de aulas obrigatorio");
	}
	public static void erroDescPacote(){
		JOptionPane.showMessageDialog(null, "Descri莽茫o do pacote obrigat贸rio");
	}
	public static void erroPrecoAula(){
		JOptionPane.showMessageDialog(null, "Pre莽o da aula obrigat贸rio");
	}
	//fim erros tela Servico
	
	//erros tela carro
	public static void erroAno(){
		JOptionPane.showMessageDialog(null, "Ano obrigat贸rio");
	}
	public static void erroMarca(){
		JOptionPane.showMessageDialog(null, "Marca obrigat贸rio");
	}
	public static void erroModelo(){
		JOptionPane.showMessageDialog(null, "Modelo obrigat贸rio");
	}
	public static void erroPlaca(){
		JOptionPane.showMessageDialog(null, "Placa obrigat贸rio");
	}
	//fim erros tela carro
	
	//inicio tela Instrutor
	
	public static void erroCpf(){
		JOptionPane.showMessageDialog(null, "CPF obrigat贸rio");
	}
	public static void erroNome(){
		JOptionPane.showMessageDialog(null, "Nome obrigat贸rio");
	}
	public static void erroNumeroCnh(){
		JOptionPane.showMessageDialog(null, "Numero da CNH obrigat贸rio");
	}
	public static void erroRg(){
		JOptionPane.showMessageDialog(null, "RG obrigat贸rio");
	}
	public static void erroData(){
		JOptionPane.showMessageDialog(null, "Data obrigat贸ria");
	}
	public static void erroDataPermissao(){
		JOptionPane.showMessageDialog(null, "Data da permiss茫o obrigat贸ria (dado encontrado na CNH)");
	}
	public static void erroDataValidadeCnh(){
		JOptionPane.showMessageDialog(null, "Data de validade da CNH obrigat贸rio");
	}
	
	//fim tela instrutor
	
	//cpf invalido
	public static void cpfInvalido(){
		JOptionPane.showMessageDialog(null, "CPF inv醠ido");
	}	
	//fim cpf invalido
	
	//Tela Instrutor
	public static void erroTelefone(){
		JOptionPane.showMessageDialog(null, "Telefone obriat髍io");
	}
	public static void erroCelular(){
		JOptionPane.showMessageDialog(null, "Celular obrigat髍io");
	}
	
	//Fim tela instrutor
}	
