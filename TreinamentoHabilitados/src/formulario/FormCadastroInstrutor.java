package formulario;

import DAO.DAOcarro;
import DAO.DAOinstrutor;
import Model.Repository.ConnectionFactoryRepository;
import Model.Repository.RepositoryInstrutor;
import com.towel.swing.img.JImagePanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.text.MaskFormatter;
import modelo.Carro;
import modelo.Funcionario;
import principal.VerificadorDeCpf;
import webcam.WebCamPhotoAutoEscola;

public class FormCadastroInstrutor extends JInternalFrame {

    private JLabel lbNome, lbData, lbRegistroCnh, lbValidadeCnh, lbPrimeiraCnh,
            lbTelefone, lbCelular, lbRg, lbCpf, lbCarro, lbStatus;
    private JTextField tfNome, tfRegistroCnh;
    private JFormattedTextField tfData, tfValidadeCnh, tfPrimeiraCnh,
            tfTelefone, tfCelular, tfCpf, tfRg;
    private MaskFormatter maskData, maskValidadeCnh, maskPrimeiraCnh,
            maskTelefone, maskCelular, maskCpf, maskRg;
    private JComboBox<Carro> jcCarro;
    private JComboBox<String> jcStatus;
    private JButton btSalvar, btBuscar, btExcluir;
    private String[] status = {"Ativo", "Inativo"}; //Substituir por enums
    List<Carro> carroList;
    Carro[] carroVetor; //Ver pra que serve. spaoksaposa
    private DAOcarro daoCarro = new DAOcarro();
    private Carro carro;
    private PainelFoto painelFotoInstrutor;

    private JInternalFrame minhaInternal;
    private String dirMyPicture;
    private MouseAdapter cliqueEmFoto;

    public FormCadastroInstrutor() {
        try {
            dirMyPicture = "";
            inicializaComponentes();
            

        } catch (ParseException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void inicializaComponentes() throws ParseException, SQLException {
        carro = new Carro();
        minhaInternal = this;
        // Label
        setLayout(null);
        //---Dados do Cliente
        lbNome = new JLabel("Nome");
        lbNome.setBounds(5, 10, 40, 20);
        add(lbNome);

        tfNome = new JTextField();
        tfNome.setBounds(50, 10, 305, 25);
        add(tfNome);

        //---
        lbData = new JLabel("Data");
        lbData.setBounds(5, 40, 40, 20);
        add(lbData);

        maskData = new MaskFormatter("##/##/####");
        maskData.setPlaceholderCharacter('_');
        tfData = new JFormattedTextField(maskData);
        tfData.setBounds(60, 40, 100, 25);
        add(tfData);

        lbRegistroCnh = new JLabel("Nº Cnh");
        lbRegistroCnh.setBounds(170, 40, 40, 20);
        add(lbRegistroCnh);

        tfRegistroCnh = new JTextField();
        tfRegistroCnh.setBounds(255, 40, 100, 25);
        add(tfRegistroCnh);

        //--
        lbValidadeCnh = new JLabel("Validade");
        lbValidadeCnh.setBounds(5, 65, 60, 20);
        add(lbValidadeCnh);

        maskValidadeCnh = new MaskFormatter("##/##/####");
        maskValidadeCnh.setPlaceholderCharacter('_');
        tfValidadeCnh = new JFormattedTextField(maskValidadeCnh);
        tfValidadeCnh.setBounds(60, 65, 100, 25);
        add(tfValidadeCnh);

        lbPrimeiraCnh = new JLabel("Permissão");
        lbPrimeiraCnh.setBounds(170, 65, 90, 20);
        add(lbPrimeiraCnh);

        maskPrimeiraCnh = new MaskFormatter("##/##/####");
        maskPrimeiraCnh.setPlaceholderCharacter('_');
        tfPrimeiraCnh = new JFormattedTextField(maskPrimeiraCnh);
        tfPrimeiraCnh.setBounds(255, 65, 100, 25);
        add(tfPrimeiraCnh);

        //--
        lbRg = new JLabel("Rg");
        lbRg.setBounds(5, 90, 40, 20);
        add(lbRg);

        maskRg = new MaskFormatter("##.###.###.-A");
        maskRg.setPlaceholder("_");
        maskRg.setValidCharacters("0123456789Xx");
        tfRg = new JFormattedTextField(maskRg);
        tfRg.setBounds(60, 90, 100, 25);
        add(tfRg);

        lbCpf = new JLabel("Cpf");
        lbCpf.setBounds(170, 90, 40, 20);
        add(lbCpf);

        maskCpf = new MaskFormatter("###.###.###-##");
        maskCpf.setPlaceholderCharacter('_');
        tfCpf = new JFormattedTextField(maskCpf);
        tfCpf.setBounds(255, 90, 100, 25);
        add(tfCpf);

        //--
        lbTelefone = new JLabel("Telefone");
        lbTelefone.setBounds(5, 115, 60, 20);
        add(lbTelefone);

        maskTelefone = new MaskFormatter("(##)####-####");
        maskTelefone.setPlaceholderCharacter('_');
        tfTelefone = new JFormattedTextField(maskTelefone);
        tfTelefone.setBounds(60, 115, 100, 25);
        add(tfTelefone);

        lbCelular = new JLabel("Celular");
        lbCelular.setBounds(165, 115, 60, 20);
        add(lbCelular);

        maskCelular = new MaskFormatter("(##)#-####-####");
        maskCelular.setPlaceholderCharacter('_');
        tfCelular = new JFormattedTextField(maskCelular);
        tfCelular.setBounds(255, 115, 100, 25);
        add(tfCelular);

        //---- Fim dos Dados do CLiente
        //--
        lbCarro = new JLabel("Carro");
        lbCarro.setBounds(5, 145, 40, 20);
        add(lbCarro);

        jcCarro = new JComboBox<Carro>();
        //carroList 
        for (Carro c : daoCarro.buscarCarro()) {
            jcCarro.addItem(c);
        }
        jcCarro.setBounds(60, 145, 230, 25);
        jcCarro.setSelectedIndex(-1);
        add(jcCarro);

        lbStatus = new JLabel("Status");
        lbStatus.setBounds(5, 170, 40, 20);
        add(lbStatus);

        jcStatus = new JComboBox<String>(status);
        jcStatus.setBounds(60, 170, 230, 25);
        jcStatus.setSelectedIndex(-1);
        add(jcStatus);

        btSalvar = new JButton("Salvar");
        btSalvar.setBounds(50, 210, 100, 30);
        add(btSalvar);

        btBuscar = new JButton("Buscar");
        btBuscar.setBounds(155, 210, 100, 30);
        add(btBuscar);

        btExcluir = new JButton("Excluir");
        btExcluir.setBounds(260, 210, 100, 30);
        add(btExcluir);
        java.io.File f = new java.io.File(dirMyPicture);
        if (f.exists()) {
            painelFotoInstrutor = new PainelFoto(dirMyPicture);//TODO IMPLEMENTAR
        } else {
            painelFotoInstrutor = new PainelFoto("");
        }
        painelFotoInstrutor.setBounds(370, 10, 110, 130);
        Border bordaColorida = BorderFactory.createLineBorder(Color.GRAY);
        Border bordaPainelFoto = BorderFactory.createTitledBorder(bordaColorida, "Foto do Instrutor");
        painelFotoInstrutor.setBorder(bordaPainelFoto);
        add(painelFotoInstrutor);

        setSize(500, 300);
        setLocation(10, 10);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setClosable(true);
        setIconifiable(true);
        setTitle("Cadastro Instrutor");
        show();
        
        definirEventos();

    }

    public void definirEventos() {
        this.cliqueEmFoto = new MouseAdapter() {
            private Object webcam;

            public void mouseClicked(MouseEvent e) {

                try {
                    if (e.getClickCount() == 2) {

                        //WebCamPhotoAutoEscola dialog = new WebCamPhotoAutoEscola(Principal.minhaFrame,diretorioParaSalvar,tfNome.getText());
                        String strPath;

                        strPath = new java.io.File("").getCanonicalPath() + "\\src\\Resources\\FotosInstrutor";

                        String strNameFile = tfNome.getText();
                        WebCamPhotoAutoEscola dialog = new WebCamPhotoAutoEscola(Principal.minhaFrame, strPath, strNameFile);
                        String path = "";
                        path = dialog.caminhoDaImagem;

                        if (!path.isEmpty()) {
                            System.out.println("Imagem tirada:" + path);
                            dirMyPicture = path;
//                            painelFotoInstrutor = null;
//                            painelFotoInstrutor = new JPanel();
//                            painelFotoInstrutor.setBounds(370, 10, 110, 130);
//                            Border bordaColorida = BorderFactory.createLineBorder(Color.GRAY);
//                            Border bordaPainelFoto = BorderFactory.createTitledBorder(bordaColorida, "Foto do Instrutor");
//                            painelFotoInstrutor.setBorder(bordaPainelFoto);
//                            painelFotoInstrutor.addMouseListener(this);
//                            add(painelFotoInstrutor);
//                            remove(painelFotoInstrutor);
                            getContentPane().removeAll();
                            inicializaComponentes();
                            /*PainelFoto p = new PainelFoto(path);
                             p.setToolTipText("Foto do Instrutor");
                             p.setBounds(370, 10, 110, 130);
                             Border bordaColorida = BorderFactory.createLineBorder(Color.GRAY);
                             Border bordaPainelFoto = BorderFactory.createTitledBorder(bordaColorida, "Foto do Instrutor");
                             p.setBorder(bordaPainelFoto);
                             p.addMouseListener(this);
                             //painelFotoInstrutor.repaint();
                             getContentPane().add(p);//
                             getContentPane().repaint();
                             */
                           // Principal.minhaFrame.repaint();

                        }

                    }

                } catch (IOException err) {
                    err.printStackTrace();
                } catch (ParseException ex) {
                    Logger.getLogger(FormCadastroInstrutor.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(FormCadastroInstrutor.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        };

        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfNome.setText(tfNome.getText().trim());
                tfRegistroCnh.setText(tfRegistroCnh.getText().trim());
                tfRg.setText(tfRg.getText().trim());

                VerificadorDeCpf verifica = new VerificadorDeCpf();

                if (tfNome.getText().isEmpty()) { // Validações, verifico se está vazio
                    JOptionPane.showMessageDialog(null, "Informar o nome");
                    lbNome.setForeground(Color.RED);
                    tfNome.requestFocus(true);
                } else if (tfData.getValue() == null) {
                    JOptionPane.showMessageDialog(null, "Informar a data");
                    lbData.setForeground(Color.RED);
                    tfData.requestFocus(true);
                } else if (tfRegistroCnh.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Informar o registro da CNH");
                    lbRegistroCnh.setForeground(Color.RED);
                    tfRegistroCnh.requestFocus(true);
                } else if (tfValidadeCnh.getValue() == null) {
                    JOptionPane.showMessageDialog(null,
                            "Informar a data de validade");
                    lbValidadeCnh.setForeground(Color.RED);
                    tfValidadeCnh.requestFocus(true);
                } else if (tfPrimeiraCnh.getValue() == null) {
                    JOptionPane.showMessageDialog(null,
                            "Informar a data da Permissão");
                    lbPrimeiraCnh.setForeground(Color.RED);
                    tfPrimeiraCnh.requestFocus(true);
                } else if (tfCpf.getValue() == null
                        || !verifica.verificarCpf(tfCpf.getText().toString())) {
                    JOptionPane.showMessageDialog(null, "Cpf invalido");
                    lbCpf.setForeground(Color.RED);
                    tfCpf.requestFocus(true);
                } else if (jcStatus.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(null, "Informar o Status");
                    lbStatus.setForeground(Color.RED);
                    jcStatus.requestFocus(true);
                } else if (jcCarro.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(null, "Carro não selecionado ou não cadastrado");
                    jcCarro.requestFocus(true);
                } else {
                    try {
                        Funcionario instrutor = new Funcionario();

                        // populando o objeto
                        instrutor.setNome(tfNome.getText());
                        Date dt = new Date(tfData.getValue().toString());
                        instrutor.setData(dt);
                        instrutor.setCnh(tfRegistroCnh.getText());
                        instrutor.setValidadeCnh(tfValidadeCnh.getText().toString());
                        instrutor.setPrimeiraCnh(tfPrimeiraCnh.getValue()
                                .toString());

                        instrutor.setCpf(tfCpf.getValue().toString());
                        instrutor.setRg(tfRg.getText());
                        instrutor.setTelefone(tfTelefone.getValue() == null ? "Não Há"
                                : tfTelefone.getValue().toString());

                        instrutor.setCelular(tfCelular.getValue() == null ? "Não Há"
                                : tfCelular.getValue().toString());
                        instrutor.setStatus(jcStatus.getSelectedItem().toString());

                        instrutor.setTbCarroPlacaCarro((Carro) jcCarro
                                .getSelectedItem());

                        JOptionPane.showMessageDialog(null, instrutor.getTbCarroPlacaCarro().getPlaca());
                        //DAOinstrutor daoInstrutor = new DAOinstrutor();

                        RepositoryInstrutor persistencia
                                = new RepositoryInstrutor(ConnectionFactoryRepository.getManager());

                        persistencia.adicionar(instrutor);

                    } catch (SQLException ex) {
                        ex.printStackTrace();;
                        Logger.getLogger(FormCadastroInstrutor.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            }
        });

        tfNome.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                int caracteres = 40;
                if (tfNome.getText().length() >= caracteres && e.getKeyCode() != 8) {
                    e.consume();
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {
                tfNome.setText(tfNome.getText().toUpperCase());
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // TODO Auto-generated method stub

            }
        });

        this.addInternalFrameListener(new InternalFrameAdapter() {
            public void internalFrameClosing(InternalFrameEvent arg0) {
                System.out.println("passo aqui");
                Principal.isFrameInstrutorOpen = false;

            }
        });

        painelFotoInstrutor.addMouseListener(cliqueEmFoto);

    }

}
