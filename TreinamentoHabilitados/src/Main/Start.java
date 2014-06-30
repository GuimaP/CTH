package Main;

import Controller.ConfigController;
import Controller.LogController;
import Model.Repository.ConnectionFactoryRepository;
import View.Principal;
import View.TelaLogin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.swing.JFrame;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

public class Start {

    protected static boolean isLoading = true;
    public static EntityManager manager;
    

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException {
        try {

            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                System.out.println(info.getName());
                if ("Nimbus".equals(info.getName())) {
                    System.out.println("is nimbus");
                    UIManager.setLookAndFeel(info.getClassName());
                    Thread t = new Thread(new Runnable() {

                        @Override
                        public void run() {
                            String[] mensagens = {"Carregando...", "Mais Alguns Instantes..."};
                            JFrame janela = new JFrame("Carregando");
                            janela.setContentPane(new MyPainelInvisible());
                            janela.setUndecorated(true);
                            janela.setSize(300, 250);
                            janela.setOpacity(0.0f); //Deixando Transparente                            
                            janela.setDefaultCloseOperation(EXIT_ON_CLOSE);
                            JLabel lb = new JLabel("Carregando");
                            //lb.setOpaque(true);
                            //janela.add(lb, BorderLayout.CENTER);
                            janela.setLocationRelativeTo(null);
                            janela.setVisible(true);
                            janela.setIconImage(ConfigController.defineIcon());
                            
                            int cont = 0;
                            while (Start.isLoading) {
                                try {
                                    lb.setText(mensagens[(cont % 2)]);
                                    Thread.sleep(1 * 1000);
                                    cont++;

                                    Thread.sleep(1000);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            }
                            janela.dispose();
                        }
                        
                        class MyPainelInvisible extends JPanel{

                        	public MyPainelInvisible() {
								setForeground(Color.black);
								setBackground(new Color(0, 0, 200, 255));
							}
                        		
                        	@Override
                        	public void paint(Graphics g) {
                        		super.paintComponents(g);
                        		java.awt.Image img = new javax.swing.ImageIcon
                        				(Start.class.getResource("/Resources/icons").getPath()+"/loading.gif").getImage();
                        		g.drawImage(img,0,0,this.getWidth(),this.getHeight(),this);
                        		
                        		
                        		
                        		// desenha um retângulo em toda a extensão do painel  
                                //g.fillRect( 0, 0, getWidth(), getHeight() ); 
                        	}
                        	
                        }
                        
                        

                    });
                    t.start();
                    System.out.println("Inicando Log");
                    //LogController.insertLog(new Exception("Iniciando"));
                   
                    manager = ConnectionFactoryRepository.getManager();

                    isLoading = false;
                    TelaLogin tela = new TelaLogin();
                    
//                    new Principal();
                    break;
                }

            }
        } catch (IllegalAccessException | UnsupportedLookAndFeelException | SQLException e) {
            LogController.insertLog(e);
            e.printStackTrace();
        }
        
    }
    
    
}
