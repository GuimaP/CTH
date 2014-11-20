package model;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import model.enums.EnumPagamento;
import model.enums.EnumTipoDespesa;

@Entity
@Table(name="tb_financeiro")
public class Financeiro {
	
	@Id
	@GeneratedValue
	private int idDespesa;
	private Date dtLancamento;
	private String descricao;
	private double valor;
	private EnumTipoDespesa tipoDespesa;
	@ManyToOne
	@JoinColumn(name="funcionario")
	private Funcionario funcionario;
	
	public int getIdDespesa() {
		return idDespesa;
	}
	public void setIdDespesa(int idDespesa) {
		this.idDespesa = idDespesa;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public EnumTipoDespesa getTipoPagamento() {
		return tipoDespesa;
	}
	public void setTipoPagamento(EnumTipoDespesa enumTipoDespesa) {
		this.tipoDespesa = enumTipoDespesa;
	}
	
	public Date getDtLancamento() {
		return dtLancamento;
	}
	public void setDtLancamento(LocalDate dtLancamento) {
		Instant instant = dtLancamento.atStartOfDay(ZoneId.systemDefault()).toInstant();
		Date dt = new Date(instant.toEpochMilli());
		this.dtLancamento = dt;
	}
	
	
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
}
