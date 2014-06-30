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

	private JButton btCloseTask, btAgendarTarefa,btSaveTask;
	private JTable tbTarefas;
	private JCalendar calendario;

	private JTextField tfDataSelecionada;
	private JTextArea tfDescricaoTarefa;
	private JSpinner spHora, spMinutos;

	private JPanel myPanel;

	private JScrollPane spDescricao;

	public PainelCalendarioAgendamento() {

		initComponents();
		defineEvents();

	}

	private void initComponents() {

		myPanel = this; // para manipulação da Panel dentro de eventos...
		setSize(385, 260);
		setLayout(null);

		JPanel jp = new JPanel();
		jp.setLayout(new GridLayout(1, 1));
		jp.setBounds(0, 5, myPanel.getWidth(), 210);

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
		jp.add(calendario);

		add(jp);

		btAgendarTarefa = new JButton("Agendar Tarefa");
		javax.swing.ImageIcon img = new javax.swing.ImageIcon(Start.class
				.getResource("/Resources/icons").getPath() + "/Tarefas.png");
		btAgendarTarefa.setIcon(img);
		btAgendarTarefa.setSize(140, 30);
		btAgendarTarefa.setLocation(
				(myPanel.getWidth() / 2) - (btAgendarTarefa.getWidth() / 2),
				220);
		add(btAgendarTarefa);

		tfDataSelecionada = new JTextField();
		tfDataSelecionada.setEnabled(false);
		tfDataSelecionada.setBounds(10, jp.getHeight() + 50, 100, 25);
		tfDataSelecionada.setVisible(false);
		add(tfDataSelecionada);

		tfDescricaoTarefa = new JTextArea();
		tfDescricaoTarefa.setLineWrap(true);
		tfDescricaoTarefa.setWrapStyleWord(true);
		spDescricao = new JScrollPane(tfDescricaoTarefa);
		spDescricao.setSize(myPanel.getWidth() - 20, 50);
		spDescricao.setLocation(10, tfDataSelecionada.getY() + 30);
		spDescricao.setToolTipText("Insira a Descrição da Tarefa");
		spDescricao.setVisible(false); // Escondo os detalhes de Agendamento
		add(spDescricao);

		SpinnerNumberModel spModelHora,spModelMinutos;
		LocalTime horaAtual = LocalTime.now();
		spModelHora = new SpinnerNumberModel(horaAtual.getHour(), 00, 24, 1); /** http://docs.oracle.com/javase/tutorial/uiswing/components/spinner.html **/
		spModelMinutos = new SpinnerNumberModel(horaAtual.getMinute(),00,59,1);
		
		spHora = new JSpinner(spModelHora);
		spHora.setBounds(tfDataSelecionada.getX() + tfDataSelecionada.getWidth() + 5,
				tfDataSelecionada.getY(), 45, 30);
		spHora.setVisible(false);
		add(spHora);
		
		spMinutos = new JSpinner(spModelMinutos);
		spMinutos.setBounds(spHora.getX() + spHora.getWidth() + 5, spHora.getY(), 45, 30);
		spMinutos.setVisible(false);
		add(spMinutos);
		
		ImageIcon imgClose = new ImageIcon(PainelCalendarioAgendamento.class.getResource("/Resources/icons").getPath()+"/closeDesc.png");
		btCloseTask = new JButton(imgClose);
		btCloseTask.setSize(30, 30);
		btCloseTask.setLocation(myPanel.getWidth() - 40, spMinutos.getY());
		btCloseTask.setContentAreaFilled(false);
		btCloseTask.setVisible(false);
		btCloseTask.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); //Mudo o cursor quando estiver em cima do botão
		add(btCloseTask);
		
		ImageIcon imgSave = new ImageIcon(PainelCalendarioAgendamento.class.getResource("/Resources/icons").getPath()+"/iconSaveTask.png");
		btSaveTask = new JButton(imgSave);
		btSaveTask.setSize(imgSave.getIconWidth(), imgSave.getIconHeight());
		btSaveTask.setContentAreaFilled(false);
		btSaveTask.setLocation(myPanel.getWidth() - 70, spDescricao.getY() + spDescricao.getHeight());
		btSaveTask.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btSaveTask.setVisible(false);
		add(btSaveTask);
		
		setVisible(true);

	}

	private void mostrar() {
		// TODO AÇÃO PARA MOSTRAR O CALENDARIO INTEIRO
	}

	private void esconder() {
		// TODO AÇÃO PARA ESCONDER O CALENDARIO
	}

	private void defineEvents() {
		btAgendarTarefa.addActionListener(evt -> {
			showNewTask();
			myPanel.setSize(myPanel.getWidth(), myPanel.getHeight()+ 145);
			tfDataSelecionada.setText(new SimpleDateFormat("dd/MM/yyyy")
					.format(calendario.getDate()));
		});
		//Aplico o efeito de mouse em cima do botão fechar
		btCloseTask.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				javax.swing.ImageIcon img = 
						new javax.swing.ImageIcon(PainelCalendarioAgendamento.class.getResource("/Resources/icons").getPath()
								+"/closeDescOn.png");
				btCloseTask.setIcon(img);
			}
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				javax.swing.ImageIcon img = 
						new javax.swing.ImageIcon(PainelCalendarioAgendamento.class.getResource("/Resources/icons").getPath()
								+"/closeDesc.png");
				btCloseTask.setIcon(img);
			}
		});
		
		btCloseTask.addActionListener(evt -> {
			hideNewTask();
			myPanel.setSize(myPanel.getWidth(), myPanel.getHeight()- 145);
		});
		
		btSaveTask.addActionListener(evt -> {
			Tarefa task = new Tarefa();
			
			int hours = Integer.parseInt(spHora.getValue().toString());
			int minutes = Integer.parseInt(spMinutos.getValue().toString());
			
			LocalTime hora = LocalTime.of(hours, minutes);
			
//			java.util.Calendar dt = calendario.getDate();
			
			String data = new SimpleDateFormat("dd/MM/yyyy").format(calendario.getDate());
			String[]vtrData = data.split("/");
			int dia = Integer.parseInt(vtrData[0]);
			int mes = Integer.parseInt(vtrData[1]);
			int ano = Integer.parseInt(vtrData[2]);
			LocalDate dataCompromisso = LocalDate.of(ano, mes, dia);
		
			LocalDateTime taskTime = dataCompromisso.atTime(hora);

			task.setDataTarefa(taskTime);
			task.setDescricao(tfDescricaoTarefa.getText());
			task.setPrioridade(Prioridade.Alta);
			
			Repository<Tarefa> repo = new Repository<Tarefa>();
			repo.Adicionar(task);
			hideNewTask();
			
		});
	}

	/**
	 * Evento para mostrar os campos para inserir uma nova tarefa
	 */
	private void showNewTask() {
		tfDataSelecionada.setVisible(true);
		spDescricao.setVisible(true);
		spHora.setVisible(true);
		spMinutos.setVisible(true);
		btCloseTask.setVisible(true);
		btSaveTask.setVisible(true);
	}

	/**
	 * Para esconder os campos da nova tarefa
	 */
	private void hideNewTask() {
		tfDataSelecionada.setVisible(false);
		spDescricao.setVisible(false);
		spHora.setVisible(false);
		spMinutos.setVisible(false);
		btCloseTask.setVisible(false);
		btSaveTask.setVisible(false);
	}
}
