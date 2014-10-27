package model.threads;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import view.Principal;

public class ThreadNotificationEmail extends Thread implements Runnable {
	private JPanel painelNotification;
	private JFrame minhaFrame;
	
	public  ThreadNotificationEmail(JPanel p, JFrame f) {
		this.painelNotification = p;
		this.minhaFrame = f;
	}
	
	
	@Override
	public void run(){
		int y = painelNotification.getY();
		try {
		System.out.println(y);
		if(y>= minhaFrame.getHeight()-40){
//		if(y>= 120){
			int yFinal = minhaFrame.getHeight() - painelNotification.getHeight() ;
//			int yFinal = 120;
			int pontoRetorno = y;
			while(y >= yFinal){
				painelNotification.setLocation(painelNotification.getX(),y);
				y--;
				Thread.sleep(2*20);
			}
			
			Thread.sleep(5*1000);
			
			while(y < pontoRetorno){
				painelNotification.setLocation(painelNotification.getX(),y);
				System.out.println(y);
				y++;
				Thread.sleep(2*20);
			}
			
		}
		
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage() + " thread Notification ");
		}
		
		
	}
}
