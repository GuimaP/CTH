package model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_pacote")
public class Pacote {

	@Id
	@GeneratedValue
	private long id;

	private Date data;

	@ManyToOne(targetEntity = Cliente.class)
	@JoinColumn(name = "idCliente")
	private Cliente cliente;

	@OneToOne(targetEntity = Servico.class)
	@JoinColumn(name = "idPacote")
	private Servico servico;

	@OneToMany(mappedBy = "pacote", targetEntity = Aula.class)
	private List<Aula> aulas;

	// dados do pagamento
	private double parcelas;
	private double valorPago;
	private double valorPendente;
	private Date dtPagamento;

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
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

	public Date getDtPagamento() {
		return dtPagamento;
	}

	public void setDtPagamento(Date dtPagamento) {
		this.dtPagamento = dtPagamento;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Aula> getAulas() {
		return aulas;
	}

	public void setAulas(List<Aula> aulas) {
		this.aulas = aulas;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}
