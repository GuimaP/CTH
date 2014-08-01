    package Model;

import java.io.File;
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
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import java.awt.Image;


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
	private String dirFoto;
        
        @Lob
        @Column(name="image", nullable = true,columnDefinition = "mediumblob")
        private byte[] image;
        
	@OneToOne
        @JoinColumn(name = "placa")//Digo que é uma chave estrangeira ...
	private Carro tbCarroPlacaCarro;
	
	public void setImage(byte[] image){
            this.image = image;
        }
        
        public String getImage(){
        	String sep = System.getProperty("file.separator");
            String dir = System.getProperty("user.home");
            dir +=sep+"Treinamento";
            String diretorioFoto = "noImage";
        	try {
                
        		//Verifico se o diretorio de Resources existe...
        		File diretorioRes = new File(dir+sep+"res");
        		if(!diretorioRes.exists()){
        			diretorioRes.mkdir();
        		}
        		//Verifico se o diretorio de imagem existe...
            	File diretorioImagens = new File(dir+sep+"Fotos-Consumidor");
            	if(!diretorioImagens.exists()){
            		diretorioImagens.mkdir();
            	}
            	
                //Especifico o diretorio que vai salvar a imagem
                File dirPhoto = new File(dir+sep+"Fotos-Consumidor"+sep+nome+".jpg");
                if(image != null){
                
                FileOutputStream ou = new FileOutputStream(dirPhoto);
                
                //Apontos o vetor de bytes da imagem
                ou.write(image);
                //Fecho o meninão aqui
                ou.close();
                diretorioFoto = dirPhoto.getPath();
                
                }
                
                
                //Se encontrar o arquivo eu devolvo a imagem
//                diretorioFoto = new ImageIcon(dirPhoto.getPath()).getImage();
                
//                }else {
//                	imgFoto = new ImageIcon(getClass().getClassLoader().getResource("Resources/imgs/noImage.png")).getImage();
//                }
                
              
               
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Funcionario.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Funcionario.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return diretorioFoto;
        }
        
        
        public Carro getTbCarroPlacaCarro() {
		return tbCarroPlacaCarro;
	}
	public void setTbCarroPlacaCarro(Carro tbCarroPlacaCarro) {
		this.tbCarroPlacaCarro = tbCarroPlacaCarro;
	}
	
	public String getDiretorioFoto(){
		return dirFoto;
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
