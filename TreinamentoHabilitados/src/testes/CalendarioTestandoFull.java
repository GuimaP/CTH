/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JComponent;
import javax.swing.JFrame;
import net.sourceforge.jdatepicker.JDateComponentFactory;
import net.sourceforge.jdatepicker.JDatePanel;

/**
 *
 * @author Guima
 */
public class CalendarioTestandoFull {

    public static void main(String[] args) {
        JFrame testFrame = new JFrame();
        JDatePanel panel = new JDateComponentFactory().createJDatePanel();
        panel.setShowYearButtons(true);
        testFrame.getContentPane().add((JComponent) panel);
        panel.setShowYearButtons(false);
        panel.setShowYearButtons(true);
        testFrame.setSize(300, 300);
        testFrame.addWindowFocusListener(new WindowAdapter() {
            public void windowClosing(WindowEvent arg0) {
                System.exit(0);
            }
        });
        testFrame.setVisible(true);
    }

}
