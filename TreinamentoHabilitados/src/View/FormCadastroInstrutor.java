package View;

import Controller.FuncionarioController;
import Controller.LogController;
import DAO.DAOcarro;
import Model.Carro;
import Model.Funcionario;
import Model.ModeloTableFuncionario;
import Model.Repository.ConnectionFactoryRepository;
import Model.Repository.Repository;
import Model.Repository.RepositoryInstrutor;

import com.toedter.calendar.JDateChooser;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.text.MaskFormatter;

import principal.VerificadorDeCpf;

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
//    Carro[] carroVetor; //Ver pra que serve. spaoksaposa
    private DAOcarro daoCarro = new DAOcarro();
    private Carro carro;
    private PainelFoto painelFotoInstrutor;

    private JInternalFrame minhaInternal;
    private String dirMyPicture;
    private MouseAdapter cliqueEmFoto;



    private JTabbedPane aba;
    private JPanel pnGeral, pnBusca;

    private ArrayList<Funcionario> listFunc = new ArrayList<Funcionario>();
    private JTable tabela;

    private JScrollPane scroll;
    private JDateChooser dcDataNascimento,dcDataValidadeCnh;
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
        
        dcDataNascimento = new JDateChooser();
        Calendar minimo = Calendar.getInstance();
        minimo.set(Calendar.YEAR,1900);
        minimo.set(Calendar.MONTH,1);
        minimo.set(Calendar.DATE, 1);
        dcDataNascimento.setMinSelectableDate(minimo.getTime());
        Date dataAtual = new Date(System.currentTimeMillis());
        dcDataNascimento.setMaxSelectableDate(dataAtual);
        dcDataNascimento.setBounds(60, 40, 120, 25);
        
        pnGeral.add(dcDataNascimento);

        lbRegistroCnh = new JLabel("N� Cnh");
        lbRegistroCnh.setBounds(213, 40, 40, 20);
        pnGeral.add(lbRegistroCnh);

        tfRegistroCnh = new JTextField();
        tfRegistroCnh.setBounds(255, 40, 100, 25);
        pnGeral.add(tfRegistroCnh);

        //--
        lbValidadeCnh = new JLabel("Validade");
        lbValidadeCnh.setBounds(5, 70, 60, 20);
        pnGeral.add(lbValidadeCnh);

//        maskValidadeCnh = new MaskFormatter("##/##/####");
//        maskValidadeCnh.setPlaceholderCharacter('_');
//        tfValidadeCnh = new JFormattedTextField(maskValidadeCnh);
//        tfValidadeCnh.setBounds(60, 75, 100, 25);
//        pnGeral.add(tfValidadeCnh);
        dcDataValidadeCnh = new JDateChooser();
        dcDataValidadeCnh.setMinSelectableDate(minimo.getTime());
        dcDataValidadeCnh.setBounds(60, 70, 120, 25);
        pnGeral.add(dcDataValidadeCnh);

        lbPrimeiraCnh = new JLabel("Permiss��o");
        lbPrimeiraCnh.setBounds(185, 70, 90, 20);
        pnGeral.add(lbPrimeiraCnh);

        maskPrimeiraCnh = new MaskFormatter("##/##/####");
        maskPrimeiraCnh.setPlaceholderCharacter('_');
        tfPrimeiraCnh = new JFormattedTextField(maskPrimeiraCnh);
        tfPrimeiraCnh.setBounds(255, 70, 100, 25);
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
        lbCpf.setBounds(230, 100, 40, 20);
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
        lbCelular.setBounds(210, 125, 60, 20);
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

        java.io.File f = new java.io.File(dirMyPicture);//Verifico se existe alguma diretorio
        if (f.exists()) { //Verifica se tem alguma foto existente para carregar
            BufferedImage img = null; 
            try { 
                img = ImageIO.read(f);//Tento ler o arquivo e carregar numa imagem.
            } catch (IOException ex) {
                Logger.getLogger(FormCadastroInstrutor.class.getName()).log(Level.SEVERE, null, ex);
            }
            painelFotoInstrutor = new PainelFoto(img);//e passo como parametro para desenhar na tela
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

                        
                        //Antes de abrir a camera, eu salvo todas as informações para restaura depois
                        populaObjInstrutor();
                        FuncionarioController.saveInformacao(instrutor);

                        String strPath;
                        
                        strPath = FormCadastroInstrutor.class.getResource("/Resources/FotosInstrutor").getPath();
//                        JOptionPane.showMessageDialog(rootPane, strPath);

                        String strNameFile = tfNome.getText(); //Pego o nome que será a imagem
                        
                        //Instancio a janela de Cam
                        WebCamPhotoAutoEscola dialog = new WebCamPhotoAutoEscola(Principal.minhaFrame, strPath, strNameFile);
                        String path = "";//Inicializo a Variavel

                        //Pego o caminho da imagem;
                        path = dialog.caminhoDaImagem;
                        
                        //Crio um File a partir da foto
                        File fotoTirada = new File(path);
                        
                        //Verifica se existe uma foto, pois pode muito bem o fulano não ter tirado uma foto.
                        if (!path.isEmpty() & fotoTirada.exists()) {
                            System.out.println("Imagem tirada:" + path); //Log...
                            
                            dirMyPicture = path;//passo o path para a variavel Global...
                            getContentPane().removeAll(); //Removo todos os componentes...
                            inicializaComponentes(); // Redesenho todos os commponentes...
                            minhaInternal.revalidate(); //revalido as alterações
                            minhaInternal.repaint(); //e "Atualizo"
                            Principal.minhaFrame.revalidate();
                            Principal.minhaFrame.repaint();
                            
                            FuncionarioController.loadInformacao(); //Recupero os dados do meu arquivo temporario
                            populaCampos(); //E populo os campos 

                        }

                    }

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

                if (tfNome.getText().isEmpty()) { // Valida��es, verifico se est�o vazio
                    JOptionPane.showMessageDialog(null, "Informar o nome");
                    lbNome.setForeground(Color.RED);
                    tfNome.requestFocus(true);
                } else if (dcDataNascimento.getDate() == null) {
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
                            "Informar a data da Permiss�o");
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
                    JOptionPane.showMessageDialog(null, "Carro n�o selecionado ou não cadastrado");
                    jcCarro.requestFocus(true);
                }else {
                    try {
                        // populando o objeto
                        populaObjInstrutor();
                        RepositoryInstrutor persistencia
                                = new RepositoryInstrutor(ConnectionFactoryRepository.getManager());

                        persistencia.adicionar(instrutor);

                        minhaInternal.dispose();

                    } catch (SQLException ex) {
                    	LogController.insertLog(ex);
                    	ex.printStackTrace();
                    }

                }

            }
        });

        tfNome.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            	String texto = tfNome.getText().toUpperCase();
            	tfNome.setText(texto);

            }

            @Override
            public void keyReleased(KeyEvent e) {
                String texto = tfNome.getText().toUpperCase();
            	tfNome.setText(texto);
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

    private void populaObjInstrutor() {
        try {
            if (!tfNome.getText().isEmpty()) {
                instrutor.setNome(tfNome.getText());
            }

            if (dcDataNascimento.getDate() != null) {
                instrutor.setData(dcDataNascimento.getDate());
            }

            if (!tfRegistroCnh.getText().isEmpty()) {
                instrutor.setCnh(tfRegistroCnh.getText());
            }

            if (dcDataValidadeCnh.getDate() != null) {
                instrutor.setValidadeCnh(tfValidadeCnh.getText().toString());
            }

            if (tfPrimeiraCnh.getValue() != null) {
                instrutor.setPrimeiraCnh(tfPrimeiraCnh.getValue().toString());
            }

            if (tfCpf.getValue() != null) {
                instrutor.setCpf(tfCpf.getValue().toString());
            }else {instrutor.setCpf("");}

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
            dcDataNascimento.setDate(instrutor.getData());
        }
        
        if(instrutor.getTbCarroPlacaCarro()!= null){
            jcCarro.setSelectedItem(instrutor.getTbCarroPlacaCarro());
        }
        
        

    }
}
