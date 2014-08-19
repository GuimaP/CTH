package view;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import controller.ConfigController;


	public class MyPainelInvisible extends JPanel {

		public MyPainelInvisible() {
			setOpaque(false);
			
		}

		@Override
		public void paint(Graphics g) {
			super.paintComponents(g);
			java.awt.Image img;
			try {
//				img = ImageIO.read(getClass().getClassLoader().getResourceAsStream(
//								"/Resources/icons/loading.gif"));
				
				img = new ImageIcon(getClass().getClassLoader().getResource("Resources/icons/loading.gif")).getImage();
				g.drawImage(img, 0, 0, this.getWidth(),
						this.getHeight(), this);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			java.awt.Image img = ConfigController.defineIcon();
		
		}
		
		@Override
	    protected void paintComponent(Graphics g) {

	        // Allow super to paint
	        super.paintComponent(g);

	        // Apply our own painting effect
	        Graphics2D g2d = (Graphics2D) g.create();
	        // 50% transparent Alpha
	        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));

	        g2d.setColor(getBackground());
	        g2d.fill(getBounds());

	        g2d.dispose();

	    }


	}

