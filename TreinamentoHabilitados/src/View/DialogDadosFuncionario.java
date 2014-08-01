package View;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import Model.Funcionario;

public class DialogDadosFuncionario extends JDialog{
	private JLabel lbNome,lbDataNasc,lbCnh,lbRg,lbCpf,lbTelefone,lbCelular;
	private PainelFoto pnFoto;
	private Funcionario funcionario;
	public DialogDadosFuncionario(Funcionario funcionario){
		super(Principal.minhaFrame);
		this.funcionario = funcionario;
		initComponetes();
		populaComponentes();
				
		setVisible(true);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setModal(true);
		
		
		
	}
	private void populaComponentes() {
		try {
			lbNome.setText(funcionario.getNome());
			lbCelular.setText(funcionario.getCelular());
			lbCnh.setText(funcionario.getCnh());
			lbCpf.setText(funcionario.getCpf());
			lbDataNasc.setText(new SimpleDateFormat("dd/MM/yyyy").format(funcionario.getData()));
			lbRg.setText(funcionario.getRg());
			lbTelefone.setText(funcionario.getTelefone());
			
			
			String imgDir = funcionario.getImage(); //Recupero o diretorio da imagem
			BufferedImage imageBf;
			if(!"noImage".equals(imgDir)){ //Se houver um diretorio diferente da imagem eu crio um obj Imagem
				imageBf = ImageIO.read(new File(imgDir));
			}else { //Se não houver imagem eu crio com a img Default
				imageBf = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Resources/imgs/noImage.png"));
			}
			imageBf = ImageIO.read(new File(imgDir));
			pnFoto = new PainelFoto(imageBf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void initComponetes() {
		setSize(400,400);
		
		lbNome = new JLabel();
		lbNome.setBounds(5,30,getWidth() - 20,25);
		add(lbNome);
		
		lbDataNasc = new JLabel();
		lbDataNasc.setBounds(5, lbNome.getHeight() + lbNome.getY(), 80, 25);
		add(lbDataNasc);
		
		lbCpf = new JLabel();
		lbCpf.setBounds(5, lbDataNasc.getHeight() + lbDataNasc.getY(), 80, 25);
		add(lbCpf);
		
		lbRg = new JLabel();
		lbRg.setBounds(5, lbCpf.getHeight() + lbCpf.getY(), 80, 25);
		add(lbRg);
		
		lbCnh = new JLabel();
		lbCnh.setBounds(5, lbRg.getHeight() + lbRg.getY(), 80, 25);
		add(lbCnh);
		
		lbTelefone = new JLabel();
		lbTelefone.setBounds(5, lbCnh.getHeight() + lbCnh.getY(), 80, 25);
		add(lbTelefone);
		
		lbCelular = new JLabel();
		lbCelular.setBounds(5, lbTelefone.getHeight() + lbTelefone.getY(), 80, 25);
		add(lbCelular);
		
		pnFoto = new PainelFoto();
		pnFoto.setSize(65, 60);
		pnFoto.setLocation(getWidth() - pnFoto.getWidth(), 5);
		add(pnFoto);
		
		
		
		
		
	}
}
