package View.Components;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.text.FlowView.FlowStrategy;

public class MenuSuperior extends JPanel {
	FlowLayout s = new FlowLayout(FlowLayout.LEFT);
	GroupLayout l = new GroupLayout(this);
	public MenuSuperior() {
		setLayout(l);
		s.setHgap(40);
		s.setVgap(40);
	}
	
	public void adicionar(Component comp) {
//		s.preferredLayoutSize(this);
		l.setHorizontalGroup(l.createSequentialGroup()
			    .addComponent(comp).addGroup(l.createParallelGroup(GroupLayout.Alignment.LEADING)));
		comp.setSize(100,100);
		
//		this.add(comp);
		
	}
}
