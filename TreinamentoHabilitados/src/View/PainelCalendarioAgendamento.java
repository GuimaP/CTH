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

import com.toedter.calendar.JCalendar;

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
import java.util.Calendar;

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

/**
 *
 * @author Guima
 */
public class PainelCalendarioAgendamento extends JPanel {

	private JButton btAgendarTarefa;
	private JLabel btSaveTask, btCloseTask;
	private JTable tbTarefas;
	private JCalendar calendario;

	private JTextField tfDataSelecionada;
	private JTextArea tfDescricaoTarefa;
	private JSpinner spHora, spMinutos;
	private boolean areOpen;

	private JPanel myPanel, painelNewTask, painelCalendario;

	private JScrollPane spDescricao;
	private ImageIcon imgClose;

	private java.util.Date dateSelcionada;

	public PainelCalendarioAgendamento() {
		imgClose = new ImageIcon(PainelCalendarioAgendamento.class.getResource(
				"/Resources/icons").getPath()
				+ "/closeDesc.png");
		initComponents();
		defineEvents();

	}

	private void initComponents() {

		myPanel = this; // para manipulação da Panel dentro de eventos...
		setSize(385, 360);
		setLayout(null);
		setBackground(new Color(0, 0, 0, 30));
		setOpaque(true);

		painelCalendario = new JPanel();
		painelCalendario.setLayout(new GridLayout(1, 1));
		painelCalendario.setBounds(0, 5, myPanel.getWidth(), 210);

		calendario = new JCalendar();
		calendario.setWeekdayForeground(Color.GRAY);
		calendario.setCalendar(Calendar.getInstance());
		calendario.setSize(30, 30);
		calendario.setTodayButtonVisible(true);
		calendario.setTodayButtonText("Hoje");
		calendario.getDayChooser().setAlwaysFireDayProperty(true);
		Calendar min = Calendar.getInstance();
		min.add(Calendar.YEAR, 1900);
		min.add(Calendar.MONTH, 1);
		min.add(Calendar.DATE, 1);
		painelCalendario.add(calendario);

		add(painelCalendario);

		btAgendarTarefa = new JButton("Agendar Tarefa");
		javax.swing.ImageIcon img = new javax.swing.ImageIcon(Start.class
				.getResource("/Resources/icons").getPath() + "/Tarefas.png");
		btAgendarTarefa.setIcon(img);
		btAgendarTarefa.setSize(140, 30);
		btAgendarTarefa.setLocation(
				(myPanel.getWidth() / 2) - (btAgendarTarefa.getWidth() / 2),
				220);
		add(btAgendarTarefa);

		setVisible(true);

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
		btCloseTask.setOpaque(false);
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
				LocalDateTime time = LocalDateTime.now();
				time.withYear(dateSelcionada.getYear());
				time.withMonth(dateSelcionada.getMonth());
				time.withDayOfMonth(dateSelcionada.getDate());
				time.withHour(dateSelcionada.getHours());
				time.withMinute(dateSelcionada.getMinutes());
				
				Tarefa t = new Tarefa();
				t.setDescricao(tfDescricaoTarefa.getText());
				t.setPrioridade(Prioridade.Alta);
				t.setDataTarefa(time);
				RepositoryTarefa repoTarefa = new RepositoryTarefa();
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
				hideNewTask();

			}
		});

		add(painelNewTask);

	}

	private void mostrar() {
		// TODO AÇÃO PARA MOSTRAR O CALENDARIO INTEIRO
	}

	private void esconder() {
		// TODO AÇÃO PARA ESCONDER O CALENDARIO
	}

	private void defineEvents() {

		calendario.getDayChooser().addPropertyChangeListener("day", evt -> {
			java.util.Date dt = calendario.getDate();
			new RepositoryTarefa().getAllTarefasToday(dt);
		});

		btAgendarTarefa.addActionListener(evt -> {
			showNewTask();

		});

	}

	/**
	 * Evento para mostrar os campos para inserir uma nova tarefa
	 */
	private void showNewTask() {
		if(areOpen){
			myPanel.setSize(myPanel.getWidth(), myPanel.getHeight() + 145);
		}
		areOpen = true;
		createNewTaskMenu();
		tfDataSelecionada.setVisible(true);
		spDescricao.setVisible(true);
		spHora.setVisible(true);
		spMinutos.setVisible(true);
		btCloseTask.setVisible(true);
		btSaveTask.setVisible(true);
		painelNewTask.setVisible(true);
		
		
		
		Color c = new Color(0, 0, 0, 30);
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
		if(!areOpen){
		myPanel.setSize(myPanel.getWidth(), myPanel.getHeight() - 145);
		}
		tfDataSelecionada.setVisible(false);
		spDescricao.setVisible(false);
		spHora.setVisible(false);
		spMinutos.setVisible(false);
		btCloseTask.setVisible(false);
		btSaveTask.setVisible(false);
		painelNewTask.setVisible(false);
		
		
		painelNewTask.removeAll();
		myPanel.revalidate();
		myPanel.repaint();
		Principal.minhaFrame.revalidate();
		Principal.minhaFrame.repaint();
	}
}
