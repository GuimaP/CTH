/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formulario.Calendario;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import net.sourceforge.jdatepicker.JDateComponentFactory;
import net.sourceforge.jdatepicker.JDatePanel;

/**
 *
 * @author Guima
 */
public class Calendario extends JInternalFrame {

    private JButton btSair, btSelecionar;
    private JTable tbTarefas;
    private JDatePanel panel;

    public Calendario() {
        
        initComponents();
        defineEvents();
        
        
    }

    private void initComponents() {
        setClosable(true);
        setResizable(false);
        setIconifiable(true);
        
        JPanel jp = new JPanel();
        jp.setSize(70,120);
        panel = new JDateComponentFactory().createJDatePanel();
        panel.setShowYearButtons(true);
        jp.add((JComponent) panel);
        
        btSair = new JButton("Sair");
        btSair.setSize(40,20);
        jp.add(btSair,BorderLayout.SOUTH);
        
        btSelecionar = new JButton("Selecionar Data");
        btSelecionar.setSize(40,20);
        jp.add(btSelecionar,BorderLayout.SOUTH);
            
        add(jp);
        
        pack();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(250, 500);
        setVisible(true);
        
        
        
        
    }

    private void defineEvents() {
        btSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        panel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Calendar c = (Calendar) panel.getModel().getValue();
                Date d =(Date) c.getTime();
                
                System.out.println(d.toString() + "\n" + d + "\n"  );

            }
        });
    }
}
