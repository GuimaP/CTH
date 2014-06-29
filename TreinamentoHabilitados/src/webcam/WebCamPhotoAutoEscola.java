package webcam;

import Controller.FuncionarioController;

import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.Serializable;
import java.lang.Thread.UncaughtExceptionHandler;

import javax.swing.JFrame;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamEvent;
import com.github.sarxos.webcam.WebcamListener;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamPicker;
import com.github.sarxos.webcam.WebcamResolution;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Proof of concept of how to handle webcam video stream from Java link :
 * https://github.com/sarxos/webcam-capture/blob/master/README.md
 *
 * @author Bartosz Firyn (SarXos)
 */
public class WebCamPhotoAutoEscola extends JDialog implements Runnable, WebcamListener, WindowListener, UncaughtExceptionHandler, ItemListener,Serializable {
    
    public String pathImgTirada;
    private static final long serialVersionUID = 1L;
    private JDialog minhaDialog;
    private Webcam webcam = null;
    private WebcamPanel panel = null;
    private WebcamPicker picker = null;
    private JButton btTakePicture, btSair;
    private Point point;
    private boolean sucess = true;
    
    private String diretorioParaSalvar, nomeArquivo;
    
    public String caminhoDaImagem;
    
    public WebCamPhotoAutoEscola(JFrame minhaFrame, String strPath, String strNameFile) {
        super(minhaFrame);
        minhaDialog = this;
        caminhoDaImagem = ""; // inicializando a variavel
        diretorioParaSalvar = strPath;
        nomeArquivo = strNameFile;
        point = new Point();
        //setModalityType(ModalityType.APPLICATION_MODAL);
        if (!sucess) {
            this.dispose();
        } else {
            run();
            setUndecorated(true);
            setLocationRelativeTo(null);
            setModal(true);
            pack();
            setVisible(true);
        }
        
    }
    
    @Override
    public void run() {
        
        setTitle("Auto Escola");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        
        addWindowListener(this);
        
        picker = new WebcamPicker();
        picker.addItemListener(this);
        
        webcam = picker.getSelectedWebcam();
        
        if (webcam == null) {
            JOptionPane.showConfirmDialog(this, "Não foi encontrado uma Web cam conectada");
            sucess = false;
            
        } else {
            
            webcam.setViewSize(WebcamResolution.VGA.getSize());
            webcam.addWebcamListener(WebCamPhotoAutoEscola.this);
            
            panel = new WebcamPanel(webcam, false);
            panel.setFPSDisplayed(false);
            
            btTakePicture = new JButton("Tirar Foto");
            btTakePicture.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        System.out.println("cliq");
                        JOptionPane.showMessageDialog(rootPane, diretorioParaSalvar + "/" + nomeArquivo + ".jpg");
                        java.io.File f = new java.io.File(diretorioParaSalvar + "/" + nomeArquivo + ".jpg");
                        if (f.exists()) {
                            f.delete();
                        }
                        
                        BufferedImage img = webcam.getImage();
                        ImageIO.write(img, "jpg", f);
                        caminhoDaImagem = f.toString();
                        
                        dispose();
                    } catch (java.io.IOException err) {
                        JOptionPane.showMessageDialog(null, err.getMessage());
                    }
                }
            });
            btSair = new JButton("Sair");
            //btSair.addActionListener(evt -> minhaDialog.dispose());
            
            JPanel painelButtons = new JPanel();
            painelButtons.add(btTakePicture, BorderLayout.EAST);
            painelButtons.add(btSair, BorderLayout.WEST);
            
            add(painelButtons, BorderLayout.SOUTH);
            add(picker, BorderLayout.NORTH);
            add(panel, BorderLayout.CENTER);
            
            Thread t = new Thread() {
                
                @Override
                public void run() {
                    panel.start();
                }
            };
            t.setName("example-starter");
            t.setDaemon(true);
            t.setUncaughtExceptionHandler(this);
            t.start();
            
            this.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    point.x = e.getX();//Pego a posiï¿½ï¿½o a localizaï¿½ï¿½o da frame quando clicado
                    point.y = e.getY();
                }
            });
            
            this.addMouseMotionListener(new MouseMotionAdapter() {
                public void mouseDragged(MouseEvent e) {
                    Point p = minhaDialog.getLocation();//pego a localizaï¿½ï¿½o da frame no ato de arrastar
                    minhaDialog.setLocation(p.x + e.getX() - point.x, p.y + e.getY() - point.y); // e entï¿½o eu subtraio a localizaï¿½ï¿½o dela, mais a onde eu cliquei 
                }
            });
        }
        
    }
    
    @Override
    public void webcamOpen(WebcamEvent we) {
        System.out.println("webcam open");
        JOptionPane.showMessageDialog(rootPane, "open");
    }
    
    @Override
    public void webcamClosed(WebcamEvent we) {
        System.out.println("webcam closed");
        JOptionPane.showMessageDialog(rootPane, "open");
    }
    
    @Override
    public void webcamDisposed(WebcamEvent we) {
        System.out.println("webcam disposed");
    }
    
    @Override
    public void webcamImageObtained(WebcamEvent we) {
// do nothing
    }
    
    @Override
    public void windowActivated(WindowEvent e) {
    }
    
    @Override
    public void windowClosed(WindowEvent e) {
        if (webcam != null) {
            webcam.close();
        }
    }
    
    @Override
    public void windowClosing(WindowEvent e) {
    }
    
    @Override
    public void windowOpened(WindowEvent e) {
    }
    
    @Override
    public void windowDeactivated(WindowEvent e) {
    }
    
    @Override
    public void windowDeiconified(WindowEvent e) {
        System.out.println("webcam viewer resumed");
        panel.resume();
    }
    
    @Override
    public void windowIconified(WindowEvent e) {
        System.out.println("webcam viewer paused");
        panel.pause();
    }
    
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.err.println(String.format("Exception in thread %s", t.getName()));
        e.printStackTrace();
    }
    
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getItem() != webcam) {
            if (webcam != null) {
                
                panel.stop();
                
                remove(panel);
                
                webcam.removeWebcamListener(this);
                webcam.close();
                
                webcam = (Webcam) e.getItem();
                webcam.setViewSize(WebcamResolution.VGA.getSize());
                webcam.addWebcamListener(this);
                
                System.out.println("selected " + webcam.getName());
                
                panel = new WebcamPanel(webcam, false);
                
                add(panel, BorderLayout.CENTER);
                pack();
                
                Thread t = new Thread() {
                    
                    @Override
                    public void run() {
                        panel.start();
                    }
                };
                t.setName("example-stoper");
                t.setDaemon(true);
                t.setUncaughtExceptionHandler(this);
                t.start();
            }
        }
    }
}
