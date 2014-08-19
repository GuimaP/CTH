package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;

import javax.swing.JButton;
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
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.text.MaskFormatter;





import controller.ControllerFormCarro;
import model.*;
import model.repository.*;
import model.table.ModelTableCarro;

import java.util.ArrayList;
import java.util.List;

public class FormCadastroCarro extends JInternalFrame {

	private JLabel lbAno, lbMarca, lbModelo, lbPlaca;
	private JTextField tfAno, tfMarca, tfModelo;
	private JFormattedTextField tfPlaca;
	private MaskFormatter maskPalca;
	private JButton btSalvar, btExcluir, btNovo;
	private JPanel pnGeral, pnBusca;
	private JTable tabela;
	private JScrollPane scroll;
	private JTabbedPane abas;
	
	private JInternalFrame myInternal;
	
	private Carro carro;
	
	private List<Carro> listCarro = new ArrayList<Carro>();
	
	private RepositoryCarro repoCarro;

	public FormCadastroCarro() {

		try {
			carro = new Carro();
			inicializaComponentes();
			definirEventos();
			listCarro = new ControllerFormCarro().buscarCarro();
			populaTable();
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return;
		}catch (Exception e2){
			JOptionPane.showMessageDialog(null,e2.getMessage());
		}
	}

	public void limparCampos(){
		carro = null;
		tfModelo.setText("");
		tfMarca.setText("");
		tfAno.setText("");
		tfPlaca.setText("");
	}
	
	
	public void populaTable(){
		try {
			for(Carro c : listCarro){
				listCarro = new ControllerFormCarro().buscarCarro();
				tabela.setModel(new ModelTableCarro(listCarro));
				scroll.revalidate();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	
	
	public void inicializaComponentes() throws ParseException {
		myInternal = this;
		
		// Paineis
		pnGeral = new JPanel();
		pnGeral.setLayout(null);
		pnBusca = new JPanel();
		pnBusca.setLayout(null);

		// informa��es do carro
		lbAno = new JLabel("Ano");
		lbAno.setLocation(5, 10);
		lbAno.setSize(30, 20);
		pnGeral.add(lbAno);

		tfAno = new JTextField();
		tfAno.setLocation(60, 10);
		tfAno.setSize(70, 25);
		pnGeral.add(tfAno);

		lbMarca = new JLabel("Marca");
		lbMarca.setLocation(5, 40);
		lbMarca.setSize(100, 20);
		pnGeral.add(lbMarca);
		
		tfMarca = new JTextField();
		tfMarca.setLocation(60, 40);
		tfMarca.setSize(70, 25);
		pnGeral.add(tfMarca);
		

		lbModelo = new JLabel("Modelo");
		lbModelo.setLocation(5, 70);
		lbModelo.setSize(100, 20);
		pnGeral.add(lbModelo);
		
		tfModelo = new JTextField();
		tfModelo.setLocation(60, 70);
		tfModelo.setSize(70, 25);
		pnGeral.add(tfModelo);
		

		lbPlaca = new JLabel("Placa");
		lbPlaca.setLocation(5, 100);
		lbPlaca.setSize(100, 20);
		pnGeral.add(lbPlaca);
		
		maskPalca = new MaskFormatter("***-####");
		maskPalca.setPlaceholderCharacter('_');
		tfPlaca = new JFormattedTextField(maskPalca);
		tfPlaca.setLocation(60, 100);
		tfPlaca.setSize(70, 25);
		pnGeral.add(tfPlaca);
		// Button
		btSalvar = new JButton("Salvar");
		btSalvar.setLocation(10, 135);
		btSalvar.setSize(100, 30);
		pnGeral.add(btSalvar);
		
		btNovo = new JButton("Novo");
		btNovo.setBounds(125, 135, 100, 30);
		pnGeral.add(btNovo);

		//Componentes do pnBusca.
		
		tabela = new JTable(new ModelTableCarro(listCarro));
		scroll = new JScrollPane(tabela);
		scroll.setSize(220, 120);
		scroll.setLocation(7, 10);
		pnBusca.add(scroll);
		
		btExcluir = new JButton("Excluir");
		btExcluir.setLocation(70, 135);
		btExcluir.setSize(100, 30);
		pnBusca.add(btExcluir);
		
		//-----------------------------------//
		abas = new JTabbedPane();
		abas.setBounds(1, 1, 245, 245);
		abas.addTab("Cadastro", pnGeral);
		abas.addTab("Busca", pnBusca);
		add(abas);

		// PAINEL//
		getContentPane().setLayout(null);
		setSize(250, 250);
		setLocation(50, 10);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setClosable(true);
		setTitle("Cadastro Carro");
		setResizable(false);
		setIconifiable(true);
	}

	public void definirEventos() {
		btSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tfAno.setText(tfAno.getText().trim());
				tfMarca.setText(tfMarca.getText().trim());
				tfModelo.setText(tfModelo.getText().trim());
				tfPlaca.setText(tfPlaca.getText().trim());
				if (tfAno.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Informar o ano");
					lbAno.setForeground(Color.RED);
					tfAno.requestFocus(true);
				} else if (tfMarca.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Informar a marca");
					lbMarca.setForeground(Color.RED);
					tfMarca.requestFocus(true);
				} else {

					try {
						Carro carro = new Carro();
						carro.setAno(Long.parseLong(tfAno.getText()));
						carro.setMarca(tfMarca.getText());
						carro.setModelo(tfModelo.getText());
						carro.setPlaca(tfPlaca.getValue().toString());
						if(repoCarro == null){
							repoCarro = new RepositoryCarro();
						}
						repoCarro.adicionar(carro);
						listCarro = new ControllerFormCarro().buscarCarro();
						populaTable();
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, e2.getMessage());
					}
					

				}
			}
		});
		tfAno.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				int caracteres = 10;
				if (tfAno.getText().length() > caracteres) {
					e.consume();
				}

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		tfMarca.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				int caracteres = 20;
				if (tfMarca.getText().length() >= caracteres
						&& e.getKeyCode() != 8) {
					e.consume();
				}

			}

			@Override
			public void keyReleased(KeyEvent e) {
				tfMarca.setText(tfMarca.getText().toUpperCase());

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
		tabela.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2){
					int index = tabela.getSelectedRow();
					Carro carroTable = ((ModelTableCarro)tabela.getModel()).getCarro(index);
					carro = carroTable;
					
					
					abas.setSelectedIndex(0);
					
					tfMarca.setText(carro.getMarca());
					tfModelo.setText(carro.getModelo());
					
					tfAno.setText(carro.getAno()+"");
					tfPlaca.setText(carro.getPlaca());
					
				}
				
			}
		});
		btNovo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			limparCampos();
				
			}
		});
		
		this.addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
				super.internalFrameClosing(e);
				Principal.isFrameCarro = false;
				setVisible(false);
				limparCampos();
			}
		});
	}
	
}
