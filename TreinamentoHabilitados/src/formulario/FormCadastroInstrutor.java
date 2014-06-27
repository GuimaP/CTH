package formulario;

import Controller.FuncionarioController;
import DAO.DAOcarro;
import DAO.DAOinstrutor;
import Model.Repository.ConnectionFactoryRepository;
import Model.Repository.Repository;
import Model.Repository.RepositoryInstrutor;
import com.itextpdf.text.log.SysoCounter;
import com.towel.swing.calendar.CalendarView;
import com.towel.swing.calendar.DatePicker;
import com.towel.swing.img.JImagePanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.text.MaskFormatter;
import modelo.Carro;
import modelo.Funcionario;
import modelo.ModeloTableFuncionario;
import net.sourceforge.jdatepicker.JDateComponentFactory;
import net.sourceforge.jdatepicker.JDatePanel;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
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
    private JButton btSalvar, btExcluir, btShowCalendar;
    private String[] status = {"Ativo", "Inativo"}; //Substituir por enums
    List<Carro> carroList;
    Carro[] carroVetor; //Ver pra que serve. spaoksaposa
    private DAOcarro daoCarro = new DAOcarro();
    private Carro carro;
    private PainelFoto painelFotoInstrutor;

    private JInternalFrame minhaInternal;
    private String dirMyPicture;
    private MouseAdapter cliqueEmFoto;

    private JDatePickerImpl datePicker;

    private JTabbedPane aba;

    private JPanel pnGeral, pnBusca;

    private ArrayList<Funcionario> listFunc = new ArrayList<Funcionario>();

    private JTable tabela;

    private JScrollPane scroll;

    private Funcionario instrutor;

    public FormCadastroInstrutor() {
        try {
            dirMyPicture = "";
            instrutor = new Funcionario();
            inicializaComponentes();

        } catch (ParseException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void inicializaComponentes() throws ParseException, SQLException {
        // Paineis da aba
        pnGeral = new JPanel();
        pnGeral.setLayout(null);
        pnBusca = new JPanel();
        pnBusca.setLayout(null);

        // Componentes da aba "Geral"
        carro = new Carro();
        minhaInternal = this;
        // Label
        setLayout(null);
        //---Dados do Cliente
        lbNome = new JLabel("Nome");
        lbNome.setBounds(5, 10, 40, 20);
        pnGeral.add(lbNome);

        tfNome = new JTextField();
        tfNome.setBounds(50, 10, 305, 25);
        pnGeral.add(tfNome);

        //---
        lbData = new JLabel("Data");
        lbData.setBounds(5, 40, 40, 20);
        pnGeral.add(lbData);

//        maskData = new MaskFormatter("##/##/####");
//        maskData.setPlaceholderCharacter('_');
//        tfData = new JFormattedTextField(maskData);
//        tfData.setBounds(60, 40, 100, 25);
//        add(tfData);
        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        datePicker = new JDatePickerImpl(datePanel);
        datePicker.setBounds(60, 40, 110, 30);
        pnGeral.add(datePicker);
        System.out.print(datePicker);

        lbRegistroCnh = new JLabel("Nº Cnh");
        lbRegistroCnh.setBounds(200, 40, 40, 20);
        pnGeral.add(lbRegistroCnh);

        tfRegistroCnh = new JTextField();
        tfRegistroCnh.setBounds(255, 40, 100, 25);
        pnGeral.add(tfRegistroCnh);

        //--
        lbValidadeCnh = new JLabel("Validade");
        lbValidadeCnh.setBounds(5, 75, 60, 20);
        pnGeral.add(lbValidadeCnh);

        maskValidadeCnh = new MaskFormatter("##/##/####");
        maskValidadeCnh.setPlaceholderCharacter('_');
        tfValidadeCnh = new JFormattedTextField(maskValidadeCnh);
        tfValidadeCnh.setBounds(60, 75, 100, 25);
        pnGeral.add(tfValidadeCnh);

        lbPrimeiraCnh = new JLabel("Permissão");
        lbPrimeiraCnh.setBounds(170, 75, 90, 20);
        pnGeral.add(lbPrimeiraCnh);

        maskPrimeiraCnh = new MaskFormatter("##/##/####");
        maskPrimeiraCnh.setPlaceholderCharacter('_');
        tfPrimeiraCnh = new JFormattedTextField(maskPrimeiraCnh);
        tfPrimeiraCnh.setBounds(255, 75, 100, 25);
        pnGeral.add(tfPrimeiraCnh);

        //--
        lbRg = new JLabel("Rg");
        lbRg.setBounds(5, 100, 40, 20);
        pnGeral.add(lbRg);

        maskRg = new MaskFormatter("##.###.###.-A");
        maskRg.setPlaceholder("_");
        maskRg.setValidCharacters("0123456789Xx");
        tfRg = new JFormattedTextField(maskRg);
        tfRg.setBounds(60, 100, 100, 25);
        pnGeral.add(tfRg);

        lbCpf = new JLabel("Cpf");
        lbCpf.setBounds(170, 100, 40, 20);
        pnGeral.add(lbCpf);

        maskCpf = new MaskFormatter("###.###.###-##");
        maskCpf.setPlaceholderCharacter('_');
        tfCpf = new JFormattedTextField(maskCpf);
        tfCpf.setBounds(255, 100, 100, 25);
        pnGeral.add(tfCpf);

        //--
        lbTelefone = new JLabel("Telefone");
        lbTelefone.setBounds(5, 125, 60, 20);
        pnGeral.add(lbTelefone);

        maskTelefone = new MaskFormatter("(##)####-####");
        maskTelefone.setPlaceholderCharacter('_');
        tfTelefone = new JFormattedTextField(maskTelefone);
        tfTelefone.setBounds(60, 125, 100, 25);
        pnGeral.add(tfTelefone);

        lbCelular = new JLabel("Celular");
        lbCelular.setBounds(165, 125, 60, 20);
        pnGeral.add(lbCelular);

        maskCelular = new MaskFormatter("(##)#-####-####");
        maskCelular.setPlaceholderCharacter('_');
        tfCelular = new JFormattedTextField(maskCelular);
        tfCelular.setBounds(255, 125, 100, 25);
        pnGeral.add(tfCelular);

        //---- Fim dos Dados do CLiente
        //--
        lbCarro = new JLabel("Carro");
        lbCarro.setBounds(5, 155, 40, 20);
        pnGeral.add(lbCarro);

        jcCarro = new JComboBox<Carro>();
        //carroList 
        List<Carro> lista = (List<Carro>) new Repository<Carro>().pegarTodos(new Carro());
        for (Carro c : lista) {
            jcCarro.addItem(c);
        }
        jcCarro.setBounds(60, 155, 230, 25);
        jcCarro.setSelectedIndex(-1);
        pnGeral.add(jcCarro);

        lbStatus = new JLabel("Status");
        lbStatus.setBounds(5, 180, 40, 20);
        pnGeral.add(lbStatus);

        jcStatus = new JComboBox<String>(status);
        jcStatus.setBounds(60, 180, 230, 25);
        jcStatus.setSelectedIndex(-1);
        pnGeral.add(jcStatus);

        btSalvar = new JButton("Salvar");
        btSalvar.setBounds(180, 220, 100, 30);
        pnGeral.add(btSalvar);

        java.io.File f = new java.io.File(dirMyPicture);
        if (f.exists()) {
            BufferedImage img = null;
            try {
                img = ImageIO.read(f);
            } catch (IOException ex) {
                Logger.getLogger(FormCadastroInstrutor.class.getName()).log(Level.SEVERE, null, ex);
            }
            painelFotoInstrutor = new PainelFoto(img);//TODO IMPLEMENTAR
        } else {
            painelFotoInstrutor = new PainelFoto();
        }
        painelFotoInstrutor.setBounds(370, 10, 110, 130);
        Border bordaColorida = BorderFactory.createLineBorder(Color.GRAY);
        Border bordaPainelFoto = BorderFactory.createTitledBorder(bordaColorida, "Foto do Instrutor");
        painelFotoInstrutor.setBorder(bordaPainelFoto);
        pnGeral.add(painelFotoInstrutor);

        // Componentes da aba "Busca"
        btExcluir = new JButton("Excluir");
        btExcluir.setBounds(180, 220, 100, 30);
        pnBusca.add(btExcluir);

        tabela = new JTable(new ModeloTableFuncionario(listFunc));
        scroll = new JScrollPane(tabela);
        scroll.setLocation(2, 5);
        scroll.setSize(482, 210);
        pnBusca.add(scroll);

        aba = new JTabbedPane();
        aba.setBounds(1, 1, 490, 295);
        aba.addTab("Cadastro", pnGeral);
        aba.addTab(" Busca  ", pnBusca);
        add(aba);

        pack();
        setSize(500, 340);
        setLocation(60, 10);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setClosable(true);
        setIconifiable(true);
        setTitle("Cadastro Instrutor");
        setVisible(true);
        requestFocusInWindow();

        definirEventos();

    }

    public void definirEventos() {
        this.cliqueEmFoto = new MouseAdapter() {
            private Object webcam;

            public void mouseClicked(MouseEvent e) {

                try {
                    if (e.getClickCount() == 2) {

                        //WebCamPhotoAutoEscola dialog = new WebCamPhotoAutoEscola(Principal.minhaFrame,diretorioParaSalvar,tfNome.getText());
                        //Antes de arbrir a camera, eu salvo todas as informações para restaura depois
                        populaObjInstrutor();

                        FuncionarioController.saveInformacao(instrutor);

                        String strPath;

                        strPath = new java.io.File("").getCanonicalPath() + "\\src\\Resources\\FotosInstrutor";

                        String strNameFile = tfNome.getText();
                        WebCamPhotoAutoEscola dialog = new WebCamPhotoAutoEscola(Principal.minhaFrame, strPath, strNameFile);
                        String path = "";
                        populaCampos();
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
                            minhaInternal.revalidate();
                            minhaInternal.repaint();
                            Principal.minhaFrame.revalidate();
                            Principal.minhaFrame.repaint();
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
                } else if (datePicker.getModel().getValue() == null) {
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
                        //Funcionario instrutor = new Funcionario();

                        // populando o objeto
                        populaObjInstrutor();
                        RepositoryInstrutor persistencia
                                = new RepositoryInstrutor(ConnectionFactoryRepository.getManager());

                        persistencia.adicionar(instrutor);

                        minhaInternal.dispose();

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

        datePicker.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Date c = (Date) datePicker.getModel().getValue();
                System.out.println(c.toString());
            }
        });
    }

    private void populaObjInstrutor() {
        try {
            if (!tfNome.getText().isEmpty()) {
                instrutor.setNome(tfNome.getText());
            }

            if (datePicker.getModel().getValue() != null) {
                Date dt = (Date) datePicker.getModel().getValue();
                System.out.println(dt.toString());
                instrutor.setData(dt);
            }

            if (!tfRegistroCnh.getText().isEmpty()) {
                instrutor.setCnh(tfRegistroCnh.getText());
            }

            if (tfValidadeCnh.getValue() != null) {
                instrutor.setValidadeCnh(tfValidadeCnh.getText().toString());
            }

            if (tfPrimeiraCnh.getValue() != null) {
                instrutor.setPrimeiraCnh(tfPrimeiraCnh.getValue().toString());
            }

            if (tfCpf.getValue() != null) {
                instrutor.setCpf(tfCpf.getValue().toString());
            }

            if (tfRg.getValue() != null) {
                instrutor.setRg(tfRg.getText());
            }

            instrutor.setTelefone(tfTelefone.getValue() == null ? "Não Há"
                    : tfTelefone.getValue().toString());

            instrutor.setCelular(tfCelular.getValue() == null ? "Não Há"
                    : tfCelular.getValue().toString());

            if (jcStatus.getSelectedIndex() != -1) {
                instrutor.setStatus(jcStatus.getSelectedItem().toString());
            }

            if (jcCarro.getSelectedIndex() != -1) {
                instrutor.setTbCarroPlacaCarro((Carro) jcCarro
                        .getSelectedItem());
            }
            //Pegando os bytes para salvar a imagem
            java.io.File f = new java.io.File(dirMyPicture);
            //Verifica se existe uma foto ja tirada
            if (f.exists()) {
                byte[] bImg = new byte[(int) f.length()]; //Pegando o tamanho de bytes da imagem;
                FileInputStream imgStream = new FileInputStream(f);
                imgStream.read(bImg);
                imgStream.close();
                instrutor.setImage(bImg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void populaCampos() {
        if (!instrutor.getNome().isEmpty()) {
            tfNome.setText(instrutor.getNome());
        }

        if (!instrutor.getCelular().isEmpty()) {
            tfRegistroCnh.setText(instrutor.getCnh());
        }
        
        if(!instrutor.getCelular().isEmpty()){
            tfCelular.setText(instrutor.getCelular());
        }
        
        if(!instrutor.getCpf().isEmpty()){
            tfCpf.setText(instrutor.getCpf());
        }
        
        if(!instrutor.getValidadeCnh().isEmpty()){
            tfValidadeCnh.setText(instrutor.getValidadeCnh());
        }
        
        if(instrutor.getData() != null){
            int day = instrutor.getData().getDay();
            int month = instrutor.getData().getMonth();
            int year = instrutor.getData().getYear();
            
            datePicker.getModel().setDate(day, month, year);
        }
        
        if(instrutor.getTbCarroPlacaCarro()!= null){
            jcCarro.setSelectedItem(instrutor.getTbCarroPlacaCarro());
        }
        
        

    }
}
