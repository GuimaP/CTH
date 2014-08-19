package model;

import java.awt.datatransfer.ClipboardOwner;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import model.enums.EnumQuestionario;


@Entity
@Table(name = "tb_pesquisa")
public class Pesquisa {
	@Id
	@GeneratedValue
	private long idPesquisa;
	private String tempoQueNaoDirige;
	private String temVeiculo;
	private String possivelDirigirNele;
	private EnumQuestionario pesquisa4;
	private String observacao;

	
	
	
	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	
	
	

	public EnumQuestionario getPesquisa4() {
		return pesquisa4;
	}

	public void setPesquisa4(EnumQuestionario pesquisa4) {
		this.pesquisa4 = pesquisa4;
	}

	public long getIdPesquisa() {
		return idPesquisa;
	}

	public void setIdPesquisa(long idPesquisa) {
		this.idPesquisa = idPesquisa;
	}

	public String getTempoQueNaoDirige() {
		return tempoQueNaoDirige;
	}

	public void setTempoQueNaoDirige(String tempoQueNaoDirige) {
		this.tempoQueNaoDirige = tempoQueNaoDirige;
	}



	public String getTemVeiculo() {
		return temVeiculo;
	}

	public void setTemVeiculo(String temVeiculo) {
		this.temVeiculo = temVeiculo;
	}

	public String getPossivelDirigirNele() {
		return possivelDirigirNele;
	}

	public void setPossivelDirigirNele(String possivelDirigirNele) {
		this.possivelDirigirNele = possivelDirigirNele;
	}



}
