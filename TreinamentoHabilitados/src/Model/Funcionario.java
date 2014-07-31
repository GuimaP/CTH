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
        
        @Lob
        @Column(name="image", nullable = true,columnDefinition = "mediumblob")
        private byte[] image;
        
	@OneToOne
        @JoinColumn(name = "placa")//Digo que é uma chave estrangeira ...
	private Carro tbCarroPlacaCarro;
	
	public void setImage(byte[] image){
            this.image = image;
        }
        
        public Image getImage(){
        	String sep = System.getProperty("file.separator");
            String dir = System.getProperty("user.home");
        	try {
                
        		//Verifico se o diretorio de Resources existe...
        		File diretorioRes = new File(dir+"res");
        		if(!diretorioRes.exists()){
        			diretorioRes.mkdir();
        		}
        		//Verifico se o diretorio de imagem existe...
            	File diretorioImagens = new File(dir+"Treinamento"+sep+"Fotos-Consumidor");
            	if(!diretorioImagens.exists()){
            		diretorioImagens.mkdir();
            	}
            	
                //Especifico o diretorio que vai salvar a imagem
                File dirPhoto = new File(dir+sep+"Treinamento"+sep+"Fotos-Consumidor"+sep+nome+".jpg");

                Image imgFoto;
                
                if(dirPhoto.exists()){
                
                FileOutputStream ou = new FileOutputStream(dirPhoto);
                                
                //Apontos o vetor de bytes da imagem
                ou.write(image);
                //Fecho o meninão aqui
                ou.close();
                
                
                //Se encontrar o arquivo eu devolvo a imagem
                imgFoto = new ImageIcon(dirPhoto.getPath()).getImage();
                }else {
                	imgFoto = new ImageIcon(getClass().getClassLoader().getResource("Resources/imgs/noImage.png")).getImage();
                }

                return imgFoto;
               
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Funcionario.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Funcionario.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            String dirImgPadrao = getClass().getClassLoader().getResource("Resources"+sep+"imgs"+sep+"noImage.jpg").getPath();
            JOptionPane.showMessageDialog(null, dirImgPadrao);
            return new ImageIcon(dirImgPadrao).getImage();
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
