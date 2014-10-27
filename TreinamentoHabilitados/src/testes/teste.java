/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javassist.tools.framedump;
import javax.swing.JComponent;
import javax.swing.JFrame;
import net.sourceforge.jdatepicker.JDateComponentFactory;
import net.sourceforge.jdatepicker.JDatePanel;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class teste {

    public static void main(String[] args) {
        JFrame testFrame = new JFrame();
//        JDatePanel panel = new JDateComponentFactory().createJDatePanel();
//        panel.setShowYearButtons(true);
//        testFrame.getContentPane().add((JComponent) panel);
//        panel.setShowYearButtons(false);
//        panel.setShowYearButtons(true);
        testFrame.setLayout(null);
        UtilDateModel model;
        model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        //datePanel.setSize(50, 40);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
        datePicker.setBounds(0, 40, 100, 30);
       testFrame.add(datePicker);
      
        testFrame.addWindowFocusListener(new WindowAdapter() {
            public void windowClosing(WindowEvent arg0) {
                System.exit(0);
            }
        });
        
        
        UtilDateModel model1;
        model1 = new UtilDateModel();
        JDatePanelImpl datePanel1 = new JDatePanelImpl(model1);
        //datePanel1.setSize(50, 40);
        JDatePickerImpl datePicker1 = new JDatePickerImpl(datePanel1);
        datePicker1.setBounds(50, 40, 100, 30);
        testFrame.add(datePicker1);
        
        testFrame.setSize(200, 100);
        testFrame.setVisible(true);
    }
}
