package principal;

import java.awt.datatransfer.ClipboardOwner;

import modelo.Cliente;
import modelo.Cnh;
import modelo.Endereco;

public class CadastroCliente {
	private String data;
	private String pesquisa1;
	private String pesquisa2;
	private String pesquisa3;
	private String pesquisa4;
	private String observacao;

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getPesquisa4() {
		return pesquisa4;
	}

	public void setPesquisa4(String pesquisa4) {
		this.pesquisa4 = pesquisa4;
	}

	public String getPesquisa1() {
		return pesquisa1;
	}

	public void setPesquisa1(String pesquisa1) {
		this.pesquisa1 = pesquisa1;
	}

	public String getPesquisa2() {
		return pesquisa2;
	}

	public void setPesquisa2(String pesquisa2) {
		this.pesquisa2 = pesquisa2;
	}

	public String getPesquisa3() {
		return pesquisa3;
	}

	public void setPesquisa3(String pesquisa3) {
		this.pesquisa3 = pesquisa3;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
