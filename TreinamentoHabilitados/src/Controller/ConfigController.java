package Controller;

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
		java.awt.Image img =new ImageIcon(Start.class.getResource("/Resources/icons").getPath()+"/logo.png").getImage();
		return img;
	}
}
