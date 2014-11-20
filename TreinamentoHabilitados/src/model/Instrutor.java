    package model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.embed.swing.SwingFXUtils;

import javax.imageio.ImageIO;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import view.components.JavaFXConvertImage;
import model.enums.EnumStatus;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.IndexColorModel;
import java.awt.image.RenderedImage;
import java.awt.image.SampleModel;
import java.awt.image.WritableRaster;


@Entity
@Table(name = "tb_instrutor")
public class Instrutor implements Serializable{
	
	private String nome="";
	private Date data;
	

	private String cnh="";
	@Column(columnDefinition="date")
	private Date validadeCnh= null;



	private Date primeiraCnh= null;

	private String rg="";
	@Id
	private String cpf="";
	private String telefone="";
	private String celular="";
	private EnumStatus status;
	@Transient
	private String dirFoto;
        
	@Lob
    @Column(name="image", nullable = true,columnDefinition = "mediumblob")
    private byte[] image;
        
	@OneToOne(cascade={CascadeType.REFRESH,CascadeType.REFRESH})
        @JoinColumn(name = "placa")//Digo que é uma chave estrangeira ...	
	private Carro placa;
	
	public void setImage(byte[] image){
            this.image = image;
        }
        
        public javafx.scene.image.Image getImage(){
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
                
//                FileOutputStream ou = new FileOutputStream(dirPhoto);
                
                //Apontos o vetor de bytes da imagem
//                ou.write(image);
                //Fecho o meninão aqui
//                ou.close();
                diretorioFoto = dirPhoto.getPath();
//                JavaFXConvertImage.getJavaFXImage(image, 150, 152);
                javafx.scene.image.Image im;
                
                InputStream in = new ByteArrayInputStream(image);
                BufferedImage bImageFromConvert = ImageIO.read(in);
                im = SwingFXUtils.toFXImage(bImageFromConvert, null);
                im.widthProperty().subtract(im.widthProperty().intValue());
                im.widthProperty().add(152);
                
                im.heightProperty().subtract(im.heightProperty().intValue());
                im.heightProperty().add(152);
                return im;
                }
                
                
                //Se encontrar o arquivo eu devolvo a imagem
//                diretorioFoto = new ImageIcon(dirPhoto.getPath()).getImage();
                
//                }else {
//                	imgFoto = new ImageIcon(getClass().getClassLoader().getResource("Resources/imgs/noImage.png")).getImage();
//                }
                
              
               
            } catch (Exception ex) {
//                Logger.getLogger(Instrutor.class.getName()).log(Level.SEVERE, null, ex);
            	ex.printStackTrace();
            }
            return null;
        }
        
        
        public Carro getTbCarroPlacaCarro() {
		return placa;
	}
	public void setTbCarroPlacaCarro(Carro tbCarroPlacaCarro) {
		this.placa = tbCarroPlacaCarro;
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
	public void Data(Date Date) {
		this.data = Date;
	}
	public String getCnh() {
		return cnh;
	}
	public void setCnh(String cnh) {
		this.cnh = cnh;
	}
	public Date getValidadeCnh() {
		return validadeCnh;
	}
	public void setValidadeCnh(Date Date) {
		this.validadeCnh = Date;
	}
	public Date getPrimeiraCnh() {
		return primeiraCnh;
	}
	public void setPrimeiraCnh(Date Date) {
		this.primeiraCnh = Date;
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
	public EnumStatus getStatus() {
		return status;
	}
	public void setStatus(EnumStatus status) {
		this.status = status;
	}
	public void setData(Date data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return (nome);
	}
	
	
	

}
