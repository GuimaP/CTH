package Controller;

import Model.Cliente;
import Model.Pacote;
import Model.Pagamento;

public class ClientePacotePagamento {
	private Cliente c;
	private Pacote p;
	private Pagamento pagamento;
	
	
	public Pagamento getPagamento() {
		return pagamento;
	}
	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}
	public Cliente getC() {
		return c;
	}
	public void setC(Cliente c) {
		this.c = c;
	}
	public Pacote getP() {
		return p;
	}
	public void setP(Pacote p) {
		this.p = p;
	}
	

}
