package Main;

import Controller.ConfigController;
import Controller.LogController;
import Model.Repository.ConnectionFactoryConfig;
import Model.Repository.ConnectionFactoryRepository;
import View.Principal;
import View.TelaLogin;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Window;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.management.RuntimeErrorException;
import javax.persistence.EntityManager;
import javax.swing.JFrame;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import org.hibernate.Session;
import org.hibernate.exception.GenericJDBCException;

import com.ibm.media.ShowDocumentEvent;

public class Start {

	protected static boolean isLoading = true;
	public static Session session;

	public static void main(String[] args) throws ClassNotFoundException,
			InstantiationException {
		try {

			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				System.out.println(info.getName());
				if ("Nimbus".equals(info.getName())) {
					System.out.println("is nimbus");
					UIManager.setLookAndFeel(info.getClassName());

					
					Thread t = new Thread(new Runnable() {

						@Override
						public void run() {
							String[] mensagens = { ".              Carregando...",
									". Mais Alguns Instantes..." };
							JFrame janela = new JFrame("Carregando");
							janela.setIconImage(ConfigController.defineIcon());
							janela.setUndecorated(true);
							janela.setSize(400, 400);
							janela.setOpacity(0.4f);
							janela.setDefaultCloseOperation(EXIT_ON_CLOSE);

							janela.setLocationRelativeTo(null);

							janela.setBackground(new Color(0,0,0,2)); //Fundo da Frame deixa transparente
							janela.setContentPane(new MyPainelInvisible()); //Defino a imagem como opaque e visivel
							janela.setLayout(new BorderLayout());
							JLabel lb = new JLabel("Carregando");
							
							lb.setFont(ConfigController.definePrincipalFont(30f, Font.BOLD));
							lb.setForeground(Color.black);
							janela.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
							
							
							janela.getContentPane().add(lb);
							janela.setAlwaysOnTop(true);

							janela.setVisible(true);
							

							int cont = 0;
							while (Start.isLoading) {
								try {
									lb.setText(mensagens[(cont % 2)]);
									Thread.sleep(1 * 1000);
									cont++;

									Thread.sleep(1000);
								} catch (InterruptedException ex) {
									Logger.getLogger(Start.class.getName())
											.log(Level.SEVERE, null, ex);
								}

							}
							janela.dispose();
						}
						/*
						 * http://stackoverflow.com/questions/11703794/how-to-set-jframe-background-transparent-but-jpanel-or-jlabel-background-opaque
						 */
					
						class MyPainelInvisible extends JPanel {

							public MyPainelInvisible() {
								setOpaque(false);
								
							}

							@Override
							public void paint(Graphics g) {
								super.paintComponents(g);
								java.awt.Image img = new javax.swing.ImageIcon(
										getClass().getResource(
												"/Resources/icons").getPath()
												+ "/loading.gif").getImage();
								g.drawImage(img, 0, 0, this.getWidth(),
										this.getHeight(), this);
							}
							
							@Override
						    protected void paintComponent(Graphics g) {

						        // Allow super to paint
						        super.paintComponent(g);

						        // Apply our own painting effect
						        Graphics2D g2d = (Graphics2D) g.create();
						        // 50% transparent Alpha
						        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));

						        g2d.setColor(getBackground());
						        g2d.fill(getBounds());

						        g2d.dispose();

						    }


						}

					});
					t.start();
					System.out.println("Inicando Log");
					// LogController.insertLog(new Exception("Iniciando"));

//					ConnectionFactoryConfig.openManger();
					session = ConnectionFactoryConfig.getSession().openSession();

					isLoading = false;
					TelaLogin tela = new TelaLogin();

				//	new Principal();
					break;
				}

			}
		} catch (IllegalAccessException | UnsupportedLookAndFeelException e) {
			//LogController.insertLog(e);
			e.printStackTrace();
		}catch (GenericJDBCException e) {
			JOptionPane.showMessageDialog(null, "Falha ao conectar no banco");
			throw new ExceptionInInitializerError(e);
		}

	}

}
