package modelo;

public class Funcionario {
	
	private String nome;
	private String data;
	private String cnh;
	private String validadeCnh;
	private String primeiraCnh;
	private String rg;
	private String cpf;
	private String telefone;
	private String celular;
	private String status;
	private Carro tbCarroPlacaCarro;
	
	public Carro getTbCarroPlacaCarro() {
		return tbCarroPlacaCarro;
	}
	public void setTbCarroPlacaCarro(Carro tbCarroPlacaCarro) {
		this.tbCarroPlacaCarro = tbCarroPlacaCarro;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getCnh() {
		return cnh;
	}
	public void setCnh(String cnh) {
		this.cnh = cnh;
	}
	public String getValidadeCnh() {
		return validadeCnh;
	}
	public void setValidadeCnh(String validadeCnh) {
		this.validadeCnh = validadeCnh;
	}
	public String getPrimeiraCnh() {
		return primeiraCnh;
	}
	public void setPrimeiraCnh(String primeiraCnh) {
		this.primeiraCnh = primeiraCnh;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return (nome);
	}
	

}
