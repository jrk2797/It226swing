package jswing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Alarm extends JFrame {

	private static final String AUDIO_CLIP_PATH = "Rooster_Alarm";
	public static Clip getAudio() {
		try {
			
			AudioInputSteam audioInput = AudioSystem.getAudioInputStream(new File(AUDIO_CLIP_PATH).getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInput);
			return clip;
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	private JPanel contentPane;
	
	//variables for the alarm
	private Date date;
	private String display;
	private int clockCount = 0;

	/**
	 * Launch the application.
	 */
	
	//I believe this would be better to do
	private static final DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

	
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
		
		    JTextField dateText = new JTextField();
		    JTextField timeText = new JTextField();
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
		    
		
		
		  //Do you think this could be used for input?
		    /*
		    FieldListener listener = new FieldListener();
		    dateText.addActionListener(listener);
		    timeText.addActionListener(listener);
		    */
		
		    
		 // this is the current date, maybe helpful for letting users input the date and time? 
		 // Using console output to show how the timer works easier
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			Date date = new Date();
			System.out.println(dateFormat.format(date));
        //______________________________________________________________________________________________________
			TimerTask timerTask = new TimerTaskAlarm();
	        
	        Timer timer = new Timer(true);
	        
	        //runs timertask for three parameters, a timerTask object, time it executes (will execute immediately because new Date() is the current date), and how long before it executes again(in milliseconds)
	        // note, the third parameter can be taken out, but I wonder if it will be helpful for the snooze function?
	        timer.schedule( timerTask, new Date(), 1000);
	       
	        System.out.println("TimerTask begins! :" + new Date()); 
	       
	        // cancels after 5 seconds, to demonstrate how cancel works
	        try {
	            Thread.sleep(5000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        
	        
	        timer.cancel();
	        System.out.println("TimerTask cancelled! after 5 seconds of running:" + new Date());
	     //_________________________________________________________
		   
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
	
	// should we just create alarm variables that we can just use
	Alarm(Date date, String display){
		this.date = date;
		this.display = display;
		TimerTaskAlarm();
		
	}
	
	//
	public void snoozeClock() {
		clockCount++;
		if(date.getTime() < 0)
			date =  new Date();
		date.setTime(date.getTime() + 60);
		TimerTaskAlarm();
	}
	
	//put the timertaskalarm in here?
	private void TimerTaskAlarm() {
		
	}

	
	
	public void createAlarm(time, display) {
		return new createAlarm(new Date(time), display);
	}
	
	public void createAlarm(time) {
		return createAlarm(time, null);
	}
	
	public void createAlarm(date, display) throws ParseException{
		return createAlarm(dateFormat.parse(date),display);
	}
	
	public void createAlarm(date) throw ParseException{
		return createAlarm(date, null);
	}
	
	
	
	public class TimerTaskAlarm extends TimerTask {
		public int i = 0;
		 
	    @Override
	    public void run() {
	        System.out.println("Run has begun at time:" + new Date());
	        System.out.println( i++ + " seconds has passed");
	       
	    }
	 
	  
}
	class ButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("Add Alarm")) {
			}
			else
				System.out.println();
			
		}	
}}
