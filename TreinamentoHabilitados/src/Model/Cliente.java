package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import model.enums.EnumFormaca;
import model.enums.EnumSexo;

@Entity
@Table(name = "tb_cliente")
public class Cliente {

	// Dados do cliente
	@Id
	@GeneratedValue
	private long idCliente;
	private String nome;
	private Date nascimento;
	private String email;
	private EnumFormaca escolaridade; 
	private String telefone;
	private String profissao;
	private String rg;
	private String cpf;
	private EnumSexo sexo;
	private String celular;

	// dados da CNH

	private Date primeiraHabilitacao;
	private String registroCnh;
	private Date dtValidade;

	// dados do endereço

	private String logradouro;
	private String Bairro;
	private long numero;
	private String cep;

	// pesquisa
	@OneToOne(targetEntity = Pesquisa.class)
	private Pesquisa pesquisa;

	public Pesquisa getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(Pesquisa pesquisa) {
		this.pesquisa = pesquisa;
	}

	public String getRegistroCnh() {
		return registroCnh;
	}

	public void setRegistroCnh(String registroCnh) {
		this.registroCnh = registroCnh;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public Date getPrimeiraHabilitacao() {
		return primeiraHabilitacao;
	}

	public void setPrimeiraHabilitacao(Date primeiraHabilitacao) {
		this.primeiraHabilitacao = primeiraHabilitacao;
	}

	public Date getDtValidade() {
		return dtValidade;
	}

	public void setDtValidade(Date dtValidade) {
		this.dtValidade = dtValidade;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return Bairro;
	}

	public void setBairro(String bairro) {
		Bairro = bairro;
	}

	public long getNumero() {
		return numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@Lob
	@Column(name = "image", nullable = true, columnDefinition = "mediumblob")
	private byte[] image;

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public EnumSexo getSexo() {
		return sexo;
	}

	public void setSexo(EnumSexo sexo) {
		this.sexo = sexo;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public EnumFormaca getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(EnumFormaca escolaridade) {
		this.escolaridade = escolaridade;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}

	@Override
	public String toString() {
		return (nome);
	}
}
