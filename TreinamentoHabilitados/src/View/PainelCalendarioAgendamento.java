/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Main.Start;
import Model.Tarefa;
import Model.Enums.Prioridade;
import Model.Repository.Repository;
import Model.Repository.RepositoryTarefa;
import Testes.painelTeste1;






import com.sun.media.rtsp.protocol.PauseMessage;
import com.toedter.calendar.JCalendar;






import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;






import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;

import org.hibernate.Hibernate;


/**
 *
 * @author Guima
 */
public class PainelCalendarioAgendamento extends JPanel {

	private RepositoryTarefa repoTarefa;
	
	private JButton btAgendarTarefa;
	
	private JTable tbTarefas;
	private JCalendar calendario;

	//Lista de Tarefas
	private JPanel painelListaTarefa;
	private boolean isShowingList;
	
	//Criação de Novas Tarefas
	private JTextField tfDataSelecionada;
	private JTextArea tfDescricaoTarefa;
	private JSpinner spHora, spMinutos;
	private boolean areOpen;
	private JLabel btSaveTask, btCloseTask;
	private JPanel painelNewTask;

	private JPanel myPanel, painelCalendario;

	private JScrollPane spDescricao;
	private ImageIcon imgClose;

	private java.util.Date dateSelcionada;

	public PainelCalendarioAgendamento() {
		isShowingList = false;
		
		imgClose = new ImageIcon(PainelCalendarioAgendamento.class.getResource(
				"/Resources/icons").getPath()
				+ "/closeDesc.png");
		repoTarefa = new RepositoryTarefa();
		initComponents();
		defineEvents();

	}

	private void initComponents() {

		myPanel = this; // para manipulação da Panel dentro de eventos...
		setSize(385, 300);
		setLayout(null);
		setBackground(new Color(0, 0, 0, 40));
		setOpaque(true);

		painelCalendario = new JPanel();
		painelCalendario.setBounds(0, 5, myPanel.getWidth(), 210);
		painelCalendario.setLayout(null);
		
		calendario = new JCalendar();
		calendario.setWeekdayForeground(Color.GRAY);
		calendario.setCalendar(Calendar.getInstance());
		calendario.setSize(painelCalendario.getWidth(), painelCalendario.getHeight());
		calendario.setTodayButtonVisible(true);
		calendario.setTodayButtonText("Hoje");
		calendario.getDayChooser().setAlwaysFireDayProperty(true);
		painelCalendario.add(calendario);

		add(painelCalendario);

		
		painelListaTarefa = new JPanel();
		painelNewTask = new JPanel();
		
		javax.swing.ImageIcon img = new javax.swing.ImageIcon(Start.class
				.getResource("/Resources/icons").getPath() + "/Tarefas.png");
		btAgendarTarefa = new JButton("Agendar Tarefa",img);
		btAgendarTarefa.setSize(140, 30);
		btAgendarTarefa.setLocation(
				(myPanel.getWidth() / 2) - (btAgendarTarefa.getWidth() / 2),
				220);
		add(btAgendarTarefa);

		setVisible(true);

	}

	private void defineEvents() {

			calendario.getDayChooser().addPropertyChangeListener("day", evt -> {
				
			if(areOpen){
				hideNewTask();
			}
			if(!isShowingList){ //Mostra somente, se n estiver aberto
				createPainelList(calendario.getDate());
			}else {
				atualizaLista(repoTarefa.getAllTarefasToday(calendario.getDate()));
			}
			
				
			
			
			
		});

		btAgendarTarefa.addActionListener(evt -> {
			
			if(!areOpen){ //Mostra somente, se n estiver aberto
				showNewTask();
			}

		});

	}
	
	private void createNewTaskMenu() {

		painelNewTask = new JPanel();
		painelNewTask.removeAll();
		painelNewTask.setLayout(null);
		painelNewTask.setOpaque(false);
		painelNewTask.setBackground(new Color(90, 90, 90, 255));
		painelNewTask.setBounds(0, painelCalendario.getHeight() + 50,
				painelCalendario.getWidth() - 20, 140);

		tfDataSelecionada = new JTextField("");
		tfDataSelecionada.setEnabled(false);
		tfDataSelecionada.setBounds(10, 10, 100, 25);
		tfDataSelecionada.setVisible(false);
		tfDataSelecionada.setText(new SimpleDateFormat("dd/MM/yyyy")
				.format(calendario.getDate()));
		painelNewTask.add(tfDataSelecionada);

		tfDescricaoTarefa = new JTextArea();
		tfDescricaoTarefa.setLineWrap(true);
		tfDescricaoTarefa.setWrapStyleWord(true);
		spDescricao = new JScrollPane(tfDescricaoTarefa);
		spDescricao.setSize(painelNewTask.getWidth() - 20, 50);
		spDescricao.setLocation(10, tfDataSelecionada.getY() + 30);
		spDescricao.setToolTipText("Insira a Descrição da Tarefa");
		spDescricao.setVisible(false); // Escondo os detalhes de Agendamento
		painelNewTask.add(spDescricao);

		SpinnerNumberModel spModelHora, spModelMinutos;
		LocalTime horaAtual = LocalTime.now();
		spModelHora = new SpinnerNumberModel(horaAtual.getHour(), 00, 24, 1);
		/**
		 * http://docs.oracle.com/javase/tutorial/uiswing/components/spinner.
		 * html
		 **/
		spModelMinutos = new SpinnerNumberModel(horaAtual.getMinute(), 00, 59,
				1);

		spHora = new JSpinner(spModelHora);
		spHora.setBounds(
				tfDataSelecionada.getX() + tfDataSelecionada.getWidth() + 5,
				tfDataSelecionada.getY(), 45, 30);
		spHora.setVisible(false);
		painelNewTask.add(spHora);

		spMinutos = new JSpinner(spModelMinutos);
		spMinutos.setBounds(spHora.getX() + spHora.getWidth() + 5,
				spHora.getY(), 45, 30);
		spMinutos.setVisible(false);
		painelNewTask.add(spMinutos);

		btCloseTask = new JLabel(imgClose);
		btCloseTask.setSize(30, 30);
		btCloseTask
				.setLocation(painelNewTask.getWidth() - 40, spMinutos.getY());
		btCloseTask.setVisible(false);
		btCloseTask.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
		btCloseTask.setBackground(new Color(0, 0, 0, 0));
		btCloseTask.setOpaque(true);
		painelNewTask.add(btCloseTask);

		ImageIcon imgSave = new ImageIcon(PainelCalendarioAgendamento.class
				.getResource("/Resources/icons").getPath()
				+ "/iconSaveTask.png");

		btSaveTask = new JLabel(imgSave);
		btSaveTask.setSize(imgSave.getIconWidth(), imgSave.getIconHeight());
		btCloseTask.setBackground(new Color(0, 0, 0, 0));
		btSaveTask.setLocation(painelNewTask.getWidth() - 60,
				spDescricao.getY() - 10 + spDescricao.getHeight());
		btSaveTask.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btSaveTask.setOpaque(false);
		btSaveTask.setVisible(false);
		btSaveTask.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				
				dateSelcionada = calendario.getDate();
				
				
				Tarefa t = new Tarefa();
				t.setDescricao(tfDescricaoTarefa.getText());
				t.setPrioridade(Prioridade.Alta);
				t.setDataCompromisso(dateSelcionada);
				repoTarefa.Adicionar(t);
				hideNewTask();
				calendario.setDate(new java.util.Date(System.currentTimeMillis()));
			}
		});
		
		painelNewTask.add(btSaveTask);

		btCloseTask.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if(areOpen){ //Fecha somente, se já estiver aberto
					hideNewTask();
				}

			}
		});

		myPanel.add(painelNewTask);

	}
	
	
	
	/**
	 * Metodo responsavel pela criação das listas de tarefas do dia...
	 */
	private void createPainelList(Date dt){
		
		createListTask(dt);

		painelListaTarefa.repaint();
		painelListaTarefa.revalidate();
		
		painelCalendario.repaint();
		painelCalendario.revalidate();
		
		Color c = new Color(0, 0, 0, 60);
		myPanel.setBackground(c);
		myPanel.repaint();
		myPanel.revalidate();
		
		Principal.minhaFrame.repaint();
		Principal.minhaFrame.revalidate();
	//	tbTarefas.validate();
		
		
		
		
	}
	
	private void createListTask(java.util.Date dt){
		painelCalendario.remove(painelListaTarefa);
		painelListaTarefa.removeAll();
		painelListaTarefa = new JPanel();
		painelListaTarefa.setLayout(new GridLayout(1,1));
		
		tbTarefas = new JTable();
		
		
		JScrollPane sp = new JScrollPane(tbTarefas);
		sp.setSize(300,300);
		
	List<Tarefa> lista = repoTarefa.getAllTarefasToday(dt);//new ArrayList<Tarefa>();
//		
//		for(int conta = 0; conta < 10 ; conta++){
//			Tarefa t = new Tarefa();
//			t.setDescricao(conta+"");
//			
//			lista.add(t);
//			
//		}
//		
		
		//
		
		if(lista.size()>0){
			atualizaLista(lista);
		}
		
		
		
		
			isShowingList= true;
			int widhtMyPaine = myPanel.getWidth()+310;
			int heightMyPaine = myPanel.getHeight();
			myPanel.setSize(widhtMyPaine,heightMyPaine);
			
			int xMyPainel = myPanel.getWidth() - 420;
			int yMyPainel = myPanel.getY();
			myPanel.setLocation(xMyPainel, yMyPainel);
			
			int widhtPainelC =painelCalendario.getWidth()+250;
			int heightPainelC = painelCalendario.getHeight();
			painelCalendario.setSize(widhtPainelC+50,heightPainelC);
			
			//int x = myPanel.getWidth() - calendario.getWidth()+20;
			int x = painelCalendario.getWidth() - calendario.getWidth()+80;
			int width  = myPanel.getWidth() - calendario.getWidth();
			painelListaTarefa.setBounds(x, 0, width, painelCalendario.getHeight()+300);
		
		
		
		
		
//		tbTarefas.getColumnModel().getColumn(0).setPreferredWidth(10);
//		tbTarefas.getColumnModel().getColumn(1).setPreferredWidth(10);
//		tbTarefas.getColumnModel().getColumn(2).setPreferredWidth(80);
		painelListaTarefa.add(sp);
		
		painelCalendario.add(painelListaTarefa);
		

		
			
	}
	
	private void atualizaLista(java.util.List<Tarefa> tarefas){
		
		tbTarefas.setModel(new TableModelListaTarefas(tarefas));
	}
	
	private void mostrar() {
		// TODO AÇÃO PARA MOSTRAR O CALENDARIO INTEIRO
	}

	private void esconder() {
		// TODO AÇÃO PARA ESCONDER O CALENDARIO
	}

	

	/**
	 * Evento para mostrar os campos para inserir uma nova tarefa
	 */
	private void showNewTask() {
		areOpen = true;
		createNewTaskMenu();
		tfDataSelecionada.setVisible(true);
		spDescricao.setVisible(true);
		spHora.setVisible(true);
		spMinutos.setVisible(true);
		btCloseTask.setVisible(true);
		btSaveTask.setVisible(true);
		painelNewTask.setVisible(true);
		
		
		myPanel.setSize(myPanel.getWidth(),myPanel.getHeight() + 125);
		
		painelNewTask.validate();
		painelNewTask.repaint();
		Color c = new Color(0, 0, 0, 60);
		myPanel.setBackground(c);
		myPanel.revalidate();
		myPanel.repaint();
		Principal.minhaFrame.revalidate();
		Principal.minhaFrame.repaint();

	}

	/**
	 * Para esconder os campos da nova tarefa
	 */
	private void hideNewTask() {
		
		areOpen = false;
		tfDataSelecionada.setVisible(false);
		spDescricao.setVisible(false);
		spHora.setVisible(false);
		spMinutos.setVisible(false);
		btCloseTask.setVisible(false);
		btSaveTask.setVisible(false);
		painelNewTask.setVisible(false);
		
		Color c = new Color(0, 0, 0, 40);
		myPanel.setBackground(c);
		myPanel.setSize(myPanel.getWidth(),myPanel.getHeight() - 125);
		
		painelNewTask.removeAll();
		painelNewTask.validate();
		painelNewTask.repaint();
		myPanel.revalidate();
		myPanel.repaint();
		Principal.minhaFrame.revalidate();
		Principal.minhaFrame.repaint();
	}
}
