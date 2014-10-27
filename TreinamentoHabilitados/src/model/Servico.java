package model;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GeneratorType;

@Entity
@Table (name = "tb_servico")
public class Servico {
	@Id//Digo que ��� uma ID
	@GeneratedValue //E que vai ser gerado automaticamente
	private long idServico;
	
	@Column(nullable=false) //N���o pode ser nula o valor
    private String descricao;
	@Column(nullable=false)
    private int aulas;
	@Column(nullable=false)
    private double precoAula;
	@Column(nullable=false)
    private double precoPacote;
	
	
	
	
	
	public long getIdPacote() {
		return idServico;
	}

	public void setIdPacote(long idPacote) {
		this.idServico = idPacote;
	}

	public Double getPrecoAula() {
        return precoAula;
    }

    public void setPrecoAula(Double precoAula) {
        this.precoAula = precoAula;
    }

    public Double getPrecoPacote() {
        return precoPacote;
    }

    public void setPrecoPacote(Double precoPacote) {
        this.precoPacote = precoPacote;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String Descricao) {
        this.descricao = Descricao;
    }

    public int getAulas() {
        return aulas;
    }

    public void setAulas(int aulas) {
        this.aulas = aulas;
    }

    @Override
    public String toString() {
        return (descricao);
    }
    
}
