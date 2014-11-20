package model.relatorios;

import java.sql.Date;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Generated;
import javax.mail.Session;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Query;
import javax.persistence.Table;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import model.Financeiro;
import model.enums.EnumTipoDespesa;
import model.repository.ConnectionFactoryRepository;
import model.repository.RepositoryFinanceiro;

public class FinanceiroRelatorio {
	private String descricao;
	private EnumTipoDespesa tipoPagamento;
	private double valor;
	private String nomeFuncionario;
	private String dataLancamento;
	
	private String dataPeriodo;
	
	public FinanceiroRelatorio(Financeiro f){
		dataPeriodo= new SimpleDateFormat("dd/MM/yyyy").
				format(new Date(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()));
		descricao = f.getDescricao();
		tipoPagamento = f.getTipoPagamento();
		valor = f.getValor();
		nomeFuncionario = f.getFuncionario().getNome();
		SimpleDateFormat formata = new SimpleDateFormat();
		formata.applyPattern("dd/MM/yyyy");
		dataLancamento = formata.format(f.getDtLancamento());
	}
	
	public FinanceiroRelatorio(Financeiro f,Date dtDateInicio,Date dtDateFim){
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		dataPeriodo= format.format(dtDateInicio) + " at� " + format.format(dtDateFim);
		descricao = f.getDescricao();
		tipoPagamento = f.getTipoPagamento();
		valor = f.getValor();
		nomeFuncionario = f.getFuncionario().getNome();
		SimpleDateFormat formata = new SimpleDateFormat();
		formata.applyPattern("dd/MM/yyyy");
		dataLancamento = formata.format(f.getDtLancamento());
	}
	
	public String getDataLancamento(){
		return dataLancamento;
	}
	
	public String getDataPeriodo(){
		return dataPeriodo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getTipoPagamento() {
		return this.tipoPagamento.toString();
	}
	
	public EnumTipoDespesa getTipoPagamentoEnum(){
		return  this.tipoPagamento;
	}
	
	public void setTipoPagamento(EnumTipoDespesa tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}
	
	public String getNomeFuncionario() {
		return nomeFuncionario;
	}
	
	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}
	
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	public String getValorStr(){
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		String moneyString = formatter.format(valor);
		return moneyString;
	}
	
	public double getValor(){
		return valor;
	}
	
	public static void main(String[] args) {
		try {
			List<Financeiro> lstF = new RepositoryFinanceiro().buscarTodos1();
			List<FinanceiroRelatorio> lstR = new ArrayList<FinanceiroRelatorio>();
			lstF.forEach(e->{
				lstR.add(new FinanceiroRelatorio(e));
			});
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
}
