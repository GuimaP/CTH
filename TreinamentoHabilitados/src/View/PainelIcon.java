package View;

import java.awt.Button;
import java.awt.Point;
import java.awt.dnd.DragGestureListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import org.eclipse.swt.events.MouseMoveListener;
import org.w3c.dom.events.MouseEvent;

public class PainelIcon extends JPanel implements MouseMotionListener,MouseListener{
	private Point point;
	private JLabel lbTexto;
	public PainelIcon(){
		point = new Point();
		initComponents();
		defineEvents();
	}
	
	
	
	private void defineEvents() {
		this.addMouseMotionListener(this);
		
	}

	private void initComponents() {
		add(new JLabel("TExto"));
		setSize(80,80);
	}

	@Override
	public void mouseDragged(java.awt.event.MouseEvent e) {
		setLocation(e.getX() - point.x + getLocation().x,
                e.getY() - point.y + getLocation().y);
		
	}
	
	
    
	
	@Override
	public void mouseMoved(java.awt.event.MouseEvent e) {
		 
	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		if(e.getButton() == 2){
			System.out.println("direito");
			new JPopupMenu().setVisible(true);;
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
	public void mousePressed(java.awt.event.MouseEvent e) {
		point.x= e.getX();
        point.y = e.getY();
		
	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		System.out.println("soltou");
		
	}




}
