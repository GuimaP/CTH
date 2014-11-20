package controller;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class ControllerErros extends JFrame{

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
	
	//inicio tela Instrutor
	
	public static void erroCpf(){
		JOptionPane.showMessageDialog(null, "CPF obrigatório");
	}
	public static void erroNome(){
		JOptionPane.showMessageDialog(null, "Nome obrigatório");
	}
	public static void erroNumeroCnh(){
		JOptionPane.showMessageDialog(null, "Numero da CNH obrigatório");
	}
	public static void erroRg(){
		JOptionPane.showMessageDialog(null, "RG obrigatório");
	}
	public static void erroData(){
		JOptionPane.showMessageDialog(null, "Data obrigatória");
	}
	public static void erroDataPermissao(){
		JOptionPane.showMessageDialog(null, "Data da permissão obrigatória (dado encontrado na CNH)");
	}
	public static void erroDataValidadeCnh(){
		JOptionPane.showMessageDialog(null, "Data de validade da CNH obrigatório");
	}
	
	//fim tela instrutor
	
	//cpf invalido
	public static void cpfInvalido(){
		JOptionPane.showMessageDialog(null, "CPF inválido");
	}	
	//fim cpf invalido
	
	//Tela Instrutor
	public static void erroTelefone(){
		JOptionPane.showMessageDialog(null, "Telefone obrigatório");
	}
	public static void erroCelular(){
		JOptionPane.showMessageDialog(null, "Celular obrigatório");
	}
	
	//Fim tela instrutor
}	
