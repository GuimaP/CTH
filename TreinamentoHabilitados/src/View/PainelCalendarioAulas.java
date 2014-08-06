package View;

import java.awt.Color;
import java.text.ParseException;
import java.util.Calendar;


import javax.swing.JPanel;


import com.toedter.calendar.JCalendar;

public class PainelCalendarioAulas extends JPanel{
	
	private JPanel painelCalendario;
	
	private JCalendar calendario;
	
	
	
	
	
	
	
	
	public PainelCalendarioAulas(JPanel painel){
		
			try {
				inicializaComponentes(painel);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
			
		
	}
	
	public void inicializaComponentes(JPanel painel) throws ParseException{
		
		
		painelCalendario = new JPanel();
		painelCalendario.setBounds(10, 130,400, 180);
		painelCalendario.setLayout(null);

		calendario = new JCalendar();
		calendario.setWeekdayForeground(Color.GRAY);
		calendario.setCalendar(Calendar.getInstance());
		calendario.setSize(painelCalendario.getWidth(),
				painelCalendario.getHeight());
		calendario.getDayChooser().setAlwaysFireDayProperty(true);
		painelCalendario.add(calendario); // Adicionando o objeto

		painel.add(painelCalendario);
		
		
		
		
		
		
		
		
		
		
	}
	
	

}
