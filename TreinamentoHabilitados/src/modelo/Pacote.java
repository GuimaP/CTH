package modelo;

public class Pacote {

	private String aulas;
	private String preco;
	private String tipoPagamento;
	private String parcelas;
	private String totalAPagar;
	private String totalRecebido;
	private Funcionario instrutor;
	private Cliente cliente;

	public String getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public String getParcelas() {
		return parcelas;
	}

	public void setParcelas(String parcelas) {
		this.parcelas = parcelas;
	}

	public String getTotalAPagar() {
		return totalAPagar;
	}

	public void setTotalAPagar(String totalAPagar) {
		this.totalAPagar = totalAPagar;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getTotalRecebido() {
		return totalRecebido;
	}

	public void setTotalRecebido(String totalRecebido) {
		this.totalRecebido = totalRecebido;
	}

	public String getAulas() {
		return aulas;
	}

	public void setAulas(String aulas) {
		this.aulas = aulas;
	}

	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

	public Funcionario getInstrutor() {
		return instrutor;
	}

	public void setInstrutor(Funcionario instrutor) {
		this.instrutor = instrutor;
	}

}
