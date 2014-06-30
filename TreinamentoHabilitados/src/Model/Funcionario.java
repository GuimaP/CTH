    package Model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;


@Entity
public class Funcionario implements Serializable{
	
	private String nome="";
	private Date data;
	private String cnh="";
	private String validadeCnh="";
	private String primeiraCnh="";
	private String rg="";
	@Id
	private String cpf="";
	private String telefone="";
	private String celular="";
	private String status="";
        
        @Lob
        @Column(name="image", nullable = true,columnDefinition = "mediumblob")
        private byte[] image;
        
	@OneToOne
        @JoinColumn(name = "placa")//Digo que é uma chave estrangeira ...
	private Carro tbCarroPlacaCarro;
	
	public void setImage(byte[] image){
            this.image = image;
        }
        
        public void getImage(){
            try {
                // Image img = Image.getInstance(image);
                
                //Especifico o diretorio que vai salvar a imagem
                FileOutputStream out = new FileOutputStream("C:\\Users\\Guima\\git\\tccsenai\\TreinamentoHabilitados\\tccsenai\\TreinamentoHabilitados\\src\\Resources\\FotosInstrutor\\test.jpg");
                //Apontos o vetor de bytes da imagem
                out.write(image);
                //Fecho o meninão aqui
                out.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Funcionario.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Funcionario.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        
        public Carro getTbCarroPlacaCarro() {
		return tbCarroPlacaCarro;
	}
	public void setTbCarroPlacaCarro(Carro tbCarroPlacaCarro) {
		this.tbCarroPlacaCarro = tbCarroPlacaCarro;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getCnh() {
		return cnh;
	}
	public void setCnh(String cnh) {
		this.cnh = cnh;
	}
	public String getValidadeCnh() {
		return validadeCnh;
	}
	public void setValidadeCnh(String validadeCnh) {
		this.validadeCnh = validadeCnh;
	}
	public String getPrimeiraCnh() {
		return primeiraCnh;
	}
	public void setPrimeiraCnh(String primeiraCnh) {
		this.primeiraCnh = primeiraCnh;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return (nome);
	}
	

}
