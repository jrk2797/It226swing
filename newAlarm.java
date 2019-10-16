
package jswing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.GridBagLayout;//changed

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Alarm extends JFrame {

	
	
	private JPanel contentPane;
	
	//variables for the alarm
	private Date date;
	private String display;
	private int clockCount = 0;
	 JTextField dateText = new JTextField();
	 JTextField timeText = new JTextField();
	 

	/**
	 * Launch the application.
	 */
	
	//I believe this would be better to do
	private static final DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	private static final DateFormat curDateFormat = new SimpleDateFormat("MM/dd/yyyy");
	private static final DateFormat curTimeFormat = new SimpleDateFormat("HH:mm:ss");
	
	private static Timer timer = new Timer(true); 
	

	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Alarm frame = new Alarm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	

	/**
	 * Create the frame.
	 */
	public Alarm() {
		//Feel free to edit anything, UI is very ugly right now as well

	
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.setForeground(Color.BLACK);
		
			Date currentDate = new Date();
			dateText.setText(curDateFormat.format(currentDate));
			timeText.setText(curTimeFormat.format(currentDate));
		
			 
		
		
		
		   
		    JLabel daate = new JLabel("Enter Date (MM/dd/yyyy)");// added instruction 
		    daate.setForeground(Color.blue);//add color
		    JLabel time = new JLabel("Enter Time (HH:mm:ss)");//added instruction
		    time.setForeground(Color.blue);//added color
		    JButton addAlarm = new JButton("Add Alarm");
		    addAlarm.setForeground(Color.red);//added color
		    addAlarm.addActionListener(new ButtonActionListener());
		    
		    GridBagLayout grid = new GridBagLayout();//added gridbaglayout
		    GridBagConstraints con = new GridBagConstraints();
		    contentPane.setLayout(grid);
		    con.fill = GridBagConstraints.HORIZONTAL;
		    
		   
		    
		    con.weightx = 0.5;//daate format
		    con.gridx = 0;
		    con.gridy = 0;
		    con.ipady = 40;
		    grid.setConstraints(daate, con);
		    contentPane.add(daate);
		    
		    con.weightx = 0.5; //dateText format
		    con.gridx = 1;
		    con.gridy = 0;
		    grid.setConstraints(dateText, con);
		    contentPane.add(dateText);
		    
		    con.weightx = 0.5;//time format
		    con.gridx = 0;
		    con.gridy = 1;
		    grid.setConstraints(time, con);
		    contentPane.add(time);
		    
		    con.weightx = 1.0;//timeText format
		    con.gridx = 1;
		    con.gridy = 1;
		    grid.setConstraints(timeText, con);
		    contentPane.add(timeText);
		    
		  con.gridx = 0;//addAlarm format
		  con.gridy = 2;
		  con.gridwidth = 3;
		  con.ipadx = 100;
		  grid.setConstraints(addAlarm, con);
		  contentPane.add(addAlarm);
		    
		
		
		    
		 
	       

	       
		   
	}
	
	
	//was thinking if we made the time like this?
	/*
	private int hour;
	private int minute;
	private int second;
	
	public void getTime() {
		while(true) {
			Calendar cal = Calendar.getInstance();
			hour = cal.get(Calendar.HOUR_OF_DAY);
			minute = cal.get(Calendar.MINUTE);
			second = cal.get(Calendar.SECOND);
			//12 hour time, aa for am or pm
			SimpleDateFormat sd = new SimpleDateFormat("HH:mm:ss  aa");
			Date date = cal.getTime();
			String twelveTime = sd.format(date);
			
		}
	}
	
	*/
	

	

	public class TimerTaskAlarm extends TimerTask {
		private String optionalMessage =null;
		public TimerTaskAlarm(String optionalMessage) {
			this.optionalMessage = optionalMessage;
		}
		
		
	   
	    public void run() {
	    	
	    	Object[] options = { "Snooze", "Dismiss" };
		Object selectedValue =JOptionPane.showOptionDialog(null, optionalMessage, "Alarm",
			JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
			null, options, options[0]);	
			
			System.out.println(selectedValue);
			if (selectedValue == "1")
				timer.cancel();
			
	       
	    }



	
	 
	  
}
	
	
	class ButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("Add Alarm")) {
				
				Date alarmDate = new Date();
				String userDate = dateText.getText();
				String userTime = timeText.getText();
				userDate += " ";
				userDate+= userTime;
				try {
					 alarmDate = dateFormat.parse(userDate);
					 	String optionalMessage = null;
					    optionalMessage = JOptionPane.showInputDialog("Please put in an optional message if you like");
						System.out.println(userDate);
						TimerTask timerTask = new TimerTaskAlarm(optionalMessage);
				     
				        timer.schedule( timerTask, alarmDate, 60000);
				  
				      
				} catch (ParseException e1) {
					
					JOptionPane.showMessageDialog(null, "You put in an invalid date or time, please remember to include '/' in date, or ':' in time", "Invalid Date Error", JOptionPane.ERROR_MESSAGE);
				}
				
				
		       
			}
			else
				System.out.println();
			
		}	
}}
