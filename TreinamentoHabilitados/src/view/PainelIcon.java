package view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.dnd.DragGestureListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Currency;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.layout.GridLayout;
import org.w3c.dom.events.MouseEvent;

import view.components.MenuSuperior;

public class PainelIcon extends JPanel implements MouseListener,MouseMotionListener {
	private Point point;
	private JLabel lbTexto;
	private MenuSuperior menuSup;
	private JButton btAdicionar;
	
	public PainelIcon(MenuSuperior menuSup){
		point = new Point();
		this.menuSup = menuSup;
		initComponents();
		defineEvents();
	}
	
	
	
	private void defineEvents() {
		addMouseListener(this);
		addMouseMotionListener(this);
		
	}

	private void initComponents() {
		setCursor(getCursor().getPredefinedCursor(Cursor.HAND_CURSOR));
	
		setBackground(new Color(0,0,0,80));
		add(new JLabel("TExto"));
		setSize(80,80);
	}


	
	
	

	@Override
	public void mouseDragged(java.awt.event.MouseEvent e) {
		Point p = this.getLocation();//pego a localiza��o da frame no ato de arrastar
        this.setLocation(p.x + e.getX() - point.x, p.y + e.getY() - point.y); // e ent�o eu subtraio a localiza��o dela, mais a onde eu cliquei 
        
	} 



	@Override
	public void mouseMoved(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		if(e.getButton() == java.awt.event.MouseEvent.BUTTON3){
			System.out.println("is esquerdo");
			JPopupMenu pop = new JPopupMenu("hehe");
			pop.add(new JMenuItem("osso"));
			pop.show(this, e.getX(), e.getY());
		}else if(e.getButton() == java.awt.event.MouseEvent.BUTTON2){
			System.out.println("is button 2");
		}else {
			System.out.println(e.getButton());
		}
		
	}



	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
		point.x = e.getX();
		point.y = e.getY();
		
	}



	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		if(this.getX() <= menuSup.getWidth() && this.getY() <= menuSup.getHeight()){
			menuSup.adicionar(this);
//			menuSup.update(Principal.minhaFrame.getGraphics());
			menuSup.revalidate();
			Principal.minhaFrame.repaint();
			Principal.minhaFrame.revalidate();
			Dimension tamanhoFrame = Principal.minhaFrame.getSize();
			menuSup.setSize(tamanhoFrame.width, 130);
			menuSup.setLocation(0, 0);
//			menuSup.repaint();
		}
		
		
		
	}



	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		BufferedImage img;
		try {
			img = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Resources/icons/btRefresh.png"));
			g.drawImage(img,0,0,this.getWidth(),this.getHeight(),this);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}

}
