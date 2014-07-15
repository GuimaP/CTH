package Controller;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;

import Main.Start;
/**
 * Classe responsavel pelas definições de configurações do programa, como por exemplo, definir imagem,definir
 * o "Lembrar senha" e entre outras coisas.
 * @author Guima
 *
 */

public class ConfigController {
	public static java.awt.Image defineIcon(){
		java.awt.Image img =new ImageIcon(ConfigController.class.getResource("/Resources/icons").getPath()+"/logo.png").getImage();
		return img;
	}
	
	public static Font definePrincipalFont(float size,int fontType){
		Start p = new Start();
		java.io.File fontFile = new File(p.getClass().getResource("/fonts").getPath()+"/BlissfulThinking.otf");
		Font fonte = null;
		try {
			fonte = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(fontType, size);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}

		return fonte;
	}

	 
}
