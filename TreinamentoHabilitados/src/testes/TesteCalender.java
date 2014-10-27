/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package testes;

import com.towel.swing.calendar.CalendarView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Guima
 */
public class TesteCalender {
    public TesteCalender(){
        JFrame frame = new JFrame("CalendarView");
        JPanel content = new JPanel();
        final CalendarView view = new CalendarView();
        JButton button = new JButton("X");
        content.add(view);
        content.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(view.getSelectedDate());
            }
        });
 
        frame.setContentPane(content);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
     public static void main(String[] args) {
        new TesteCalender();
    }
}
