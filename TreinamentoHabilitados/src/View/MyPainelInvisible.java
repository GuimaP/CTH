package View;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;


	public class MyPainelInvisible extends JPanel {

		public MyPainelInvisible() {
			setOpaque(false);
			
		}

		@Override
		public void paint(Graphics g) {
			super.paintComponents(g);
			java.awt.Image img = new javax.swing.ImageIcon(
					getClass().getResource(
							"/Resources/icons").getPath()
							+ "/loading.gif").getImage();
			g.drawImage(img, 0, 0, this.getWidth(),
					this.getHeight(), this);
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

