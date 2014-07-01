package Testes;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.text.DateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class painelTeste1 {

    public static void main(String[] args) {
        new painelTeste1();
    }

    public painelTeste1() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                final PaintPane pane = new PaintPane();
                JFrame frame = new JFrame("Test");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(pane);

                JButton change = new JButton("Change");
                change.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        pane.changeBackground();
                        pane.repaint();
                    }
                });

                frame.add(change, BorderLayout.SOUTH);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }

        });
    }

    public class PaintPane extends JPanel {

        private BufferedImage bg;
        private int changes = 0;

        public PaintPane() {
            changeBackground();
        }

        public void changeBackground() {

            bg = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = bg.createGraphics();
            FontMetrics fm = g.getFontMetrics();
            g.setColor(getForeground());
            String[] text = {
                "I've been changed " + changes + " times", 
                "Last changed at " + DateFormat.getDateTimeInstance().format(new Date())};
            int y = (200 - (fm.getHeight() * 2)) / 2;
            for (String value : text) {
                int x = (200 - fm.stringWidth(value)) / 2;
                g.drawString(value, x, y + fm.getAscent());
                y += fm.getHeight();
            }
            g.dispose();
            changes++;

        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(200, 200);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); 
            int x = (getWidth() - bg.getWidth()) / 2;
            int y = (getHeight() - bg.getHeight()) / 2;
            g.drawImage(bg, x, y, this);
        }

    }

}