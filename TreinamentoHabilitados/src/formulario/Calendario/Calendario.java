/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formulario.Calendario;

import com.toedter.calendar.JCalendar;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

import net.sourceforge.jdatepicker.JDatePanel;

/**
 *
 * @author Guima
 */
public class Calendario extends JPanel {

    private JButton btSair, btSelecionar;
    private JTable tbTarefas;
    private JCalendar calendario;

    public Calendario() {
        
        initComponents();
        defineEvents();
        
        
    }

    private void initComponents() {
//        setClosable(true);
//        setResizable(false);
//        setIconifiable(true);
        
        JPanel jp = new JPanel();
        //jp.setSize(80,140);
        jp.setLayout(new GridLayout(1,1));
//        panel = new JDateComponentFactory().createJDatePanel();
//        panel.setShowYearButtons(true);
        calendario = new JCalendar();
        calendario.setWeekdayForeground(Color.RED);
        calendario.setCalendar(Calendar.getInstance());
        Calendar min = Calendar.getInstance();
        min.add(Calendar.YEAR, 1900);
        min.add(Calendar.MONTH,1);
        min.add(Calendar.DATE, 1);
        //calendario.setMinSelectableDate(min.getTime());
        jp.add(calendario);
        add(jp,BorderLayout.NORTH);
        
        btSelecionar = new JButton("Selecionar Data");
        btSelecionar.setSize(40,20);
        //add(btSelecionar,BorderLayout.SOUTH);
            
        
        
//        pack();
//        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        setVisible(true);
        //setSize(300,200);
        
        
        
    }

    private void defineEvents() {
//        btSair.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                setVi
//            }
//        });
        
        
    }
}
