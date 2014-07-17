package View;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Panel;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import Controller.ConfigController;
import Main.Start;
import Model.Login;
import Model.Repository.LoginRepository;

import java.awt.Color;

public class TelaLogin extends JFrame {

    protected static boolean isLoading;
	private JButton btOk, btSair;
    private JLabel lbUsuario, lbSenha;
    private JTextField tfUsuario;
    private Font fonte;
    private JPasswordField jpSenha;
    private Point point;
    private JFrame minhaFrame; //Variavel da minha frame, necessaria para o efeito de arrastar a tela
    //private FazendoLogin fazendoLogin = new FazendoLogin();

    public TelaLogin() {
        try {

            inicializaComponentes();
            eventos();
            tfUsuario.setText("guima");
            jpSenha.setText("123");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void inicializaComponentes() throws IOException {
		// Font
        // depois

        minhaFrame = this;
        point = new Point();

		//JOptionPane.showConfirmDialog(null, ));
        getContentPane().setLayout(null);
        setSize(300, 150);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Login");
        setResizable(false);
        setUndecorated(true);
        
        
        setIconImage(ConfigController.defineIcon());

        // Button
        btOk = new JButton("Logar");
        btOk.setSize(80, 30);
        btOk.setLocation(100, 90);
        btOk.setFont(ConfigController.definePrincipalFont(14f, Font.PLAIN));
        add(btOk);

        btSair = new JButton("Sair");
        btSair.setSize(80, 30);
        btSair.setLocation(200, 90);
        btSair.setFont(ConfigController.definePrincipalFont(14f, Font.PLAIN));
        add(btSair);

        // Label
        lbUsuario = new JLabel("Usuário");
        lbUsuario.setSize(55, 10);
        lbUsuario.setLocation(20, 25);
        lbUsuario.setFont(ConfigController.definePrincipalFont(14f, Font.PLAIN));
        add(lbUsuario);

        lbSenha = new JLabel("Senha");
        lbSenha.setSize(60, 10);
        lbSenha.setLocation(20, 55);
        lbSenha.setFont(ConfigController.definePrincipalFont(14f, Font.PLAIN));
        add(lbSenha);

        // TextField
        tfUsuario = new JTextField();
        tfUsuario.setSize(160, 30);
        tfUsuario.setLocation(100, 22);
        tfUsuario.setFont(ConfigController.definePrincipalFont(14f, Font.PLAIN));
        add(tfUsuario);

        jpSenha = new JPasswordField();
        jpSenha.setSize(160, 30);
        jpSenha.setLocation(100, 52);
        add(jpSenha);

		//
        setVisible(true);

    }

    public void eventos() {
        btOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel lbVerificacao = new JLabel("*");
                lbVerificacao.setForeground(Color.RED);
                lbVerificacao.setLocation(-2, tfUsuario.getY());
                lbVerificacao.setVisible(false);
                if (tfUsuario.getText().trim().equals("")) {
                    lbVerificacao.setVisible(true);
                    lbVerificacao.setLocation(tfUsuario.getWidth() + lbVerificacao.getWidth(),tfUsuario.getY());
                } else if (String.valueOf(jpSenha.getPassword()).trim().equals("")) {
                    lbVerificacao.setVisible(true);
                    lbVerificacao.setLocation(tfUsuario.getWidth() + lbVerificacao.getWidth(),jpSenha.getY());

                } else {
                    try {
                        Login l = new Login();
                        l.setUsuario(tfUsuario.getText().trim());
                        l.setSenha(String.valueOf(jpSenha.getPassword()));
                        LoginRepository loginRepository = new LoginRepository();
 
//                       minhaFrame.setGlassPane(new MyPainelInvisible());
                        if (loginRepository.isAutentica(l)) {
                          
                           minhaFrame.setVisible(false);
                           new Principal(l);
                          
                            minhaFrame.dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Usuario ou senha incorretos");
                        }

                    } catch (Exception e1) {
                        e1.printStackTrace();

                    }
                }
            }

        });

        btSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });

        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                point.x = e.getX();//Pego a posi��o a localiza��o da frame quando clicado
                point.y = e.getY();
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                Point p = minhaFrame.getLocation();//pego a localiza��o da frame no ato de arrastar
                minhaFrame.setLocation(p.x + e.getX() - point.x, p.y + e.getY() - point.y); // e ent�o eu subtraio a localiza��o dela, mais a onde eu cliquei 
            }
        });

        //Metodo para aceitar o Enter
        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 13 && (tfUsuario.isFocusable() || jpSenha.isFocusable())) {
                    btOk.doClick();
                }

            }
        });
    }

}
