package Model;

import javax.xml.crypto.Data;

public class Pagamento {
	private double parcelas;
	private double valorPago;
	private double valorPendente;
	private Data dtPagamento;
	
	private EnumPagamento tipoPagamento;
	
	
	public EnumPagamento getTipoPagamento() {
		return tipoPagamento;
	}
	public void setTipoPagamento(EnumPagamento tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}
	public Data getDtPagamento() {
		return dtPagamento;
	}
	public void setDtPagamento(Data dtPagamento) {
		this.dtPagamento = dtPagamento;
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
