package Controller;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Main.Start;
/**
 * Classe responsavel pelas definições de configurações do programa, como por exemplo, definir imagem,definir
 * o "Lembrar senha" e entre outras coisas.
 * @author Guima
 *
 */

public class ConfigController {
	public static java.awt.Image defineIcon(){
		BufferedImage bf = null;
		try {
			bf = ImageIO.read(ConfigController.class.getResourceAsStream("/Resources/icons/logo.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		java.awt.Image i = bf;
//		java.awt.Image img =new ImageIcon(new ConfigController().getClass().getResource("/Resources/icons").getPath()+"/logo.png").getImage();
		
		return bf;
	}

	public static Font definePrincipalFont(float size,int fontType){
		Start p = new Start();
		InputStream in = ConfigController.class.getResourceAsStream("/fonts/BlissfulThinking.otf");
//		java.io.File fontFile = new File(new ConfigController().getClass().getResource("/fonts").getPath()+"/BlissfulThinking.otf");
		Font fonte = null;
		try {
			fonte = Font.createFont(Font.TRUETYPE_FONT, in).deriveFont(fontType, size);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}

		return fonte;
	}


}