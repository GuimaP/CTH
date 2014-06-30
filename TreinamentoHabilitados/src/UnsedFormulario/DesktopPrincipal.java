package UnsedFormulario;

import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;

import com.towel.swing.img.JImagePanel;

public class DesktopPrincipal extends JDesktopPane {
	private MediaTracker track;
	private ImageIcon img;
	
	public DesktopPrincipal(){
		//img = new ImageIcon(this.getClass().getResource(new File("").getCanonicalFile()+"\\bin\\Resources\\imgs\\logo fundo.png"));
			img = new ImageIcon();
			img.setImage(new ImageIcon("C:\\Users\\Guima\\git\\tccsenai\\TreinamentoHabilitados\\src\\Resources\\imgs\\logo fundo.png").getImage());
		 track= new MediaTracker(this);  
		 track.addImage(img.getImage(), 0);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if(img!=null){
			g.drawImage(img.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}
}
