package Model;

import javax.xml.crypto.Data;

public class Pagamento {
	private String tipoPagamento;
	private double parcelas;
	private double valorPago;
	private double valorPendente;
	private Data dtPagamento;
	
	
	public Data getDtPagamento() {
		return dtPagamento;
	}
	public void setDtPagamento(Data dtPagamento) {
		this.dtPagamento = dtPagamento;
	}
	public String getTipoPagamento() {
		return tipoPagamento;
	}
	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}
	public double getParcelas() {
		return parcelas;
	}
	public void setParcelas(double parcelas) {
		this.parcelas = parcelas;
	}
	public double getValorPago() {
		return valorPago;
	}
	public void setValorPago(double valorPago) {
		this.valorPago = valorPago;
	}
	public double getValorPendente() {
		return valorPendente;
	}
	public void setValorPendente(double valorPendente) {
		this.valorPendente = valorPendente;
	}
	

}
