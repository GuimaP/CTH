/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author Guima
 */
public class Calendario extends javax.swing.JFrame {

    public Calendario() {
        setSize(300, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
        add(datePicker);
        
        setVisible(true);
    }
    
    public static void main(String arg[]){
        new Calendario();
    }
}
