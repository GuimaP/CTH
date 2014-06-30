package Model;

public class Pacote {

    private String descricao;
    private String aulas;
    private String precoAula;
    private String precoPacote;

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
