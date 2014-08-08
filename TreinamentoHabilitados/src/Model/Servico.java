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
	@Id//Digo que � uma ID
	@GeneratedValue //E que vai ser gerado automaticamente
	private long idPacote;
	
	@Column(nullable=false) //N�o pode ser nula o valor
    private String descricao;
	@Column(nullable=false)
    private String aulas;
	@Column(nullable=false)
    private String precoAula;
	@Column(nullable=false)
    private String precoPacote;
	@Column(nullable=false)
	private String descAulas;
	
	
	
	
	public long getIdPacote() {
		return idPacote;
	}

	public void setIdPacote(long idPacote) {
		this.idPacote = idPacote;
	}

	public String getDescAulas() {
		return descAulas;
	}

	public void setDescAulas(String descAulas) {
		this.descAulas = descAulas;
	}

	public String getPrecoAula() {
        return precoAula;
    }

    public void setPrecoAula(String precoAula) {
        this.precoAula = precoAula;
    }

    public String getPrecoPacote() {
        return precoPacote;
    }

    public void setPrecoPacote(String precoPacote) {
        this.precoPacote = precoPacote;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String Descricao) {
        this.descricao = Descricao;
    }

    public String getAulas() {
        return aulas;
    }

    public void setAulas(String aulas) {
        this.aulas = aulas;
    }

    @Override
    public String toString() {
        return (descricao);
    }
    
}
