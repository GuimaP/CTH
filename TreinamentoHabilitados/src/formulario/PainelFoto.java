/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package formulario;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Guima
 */
public class PainelFoto extends JPanel {    
    private String path="";
    public PainelFoto(String path){
        this.path = path;
    }

    public PainelFoto(){
        
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
         if(!path.equals("")&&!path.equals(null)){
             g.drawImage(new ImageIcon(path).getImage(),0,0,this.getWidth(),this.getHeight(),this);
        }else {
            this.setBackground(Color.GRAY);
            super.paint(g);
        }
    }
    
    
}
