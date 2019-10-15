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

	/**
	 * Launch the application.
	 */
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
		    JLabel daate = new JLabel("Date"); 
		    JLabel time = new JLabel("Time");
		    JButton addAlarm = new JButton("Add Alarm");
		    addAlarm.addActionListener(new ButtonActionListener());
		    contentPane.setLayout(new GridLayout(3, 2));
		    contentPane.add(daate);
		    contentPane.add(time);
		    contentPane.add(dateText);
		    contentPane.add(timeText);
		    contentPane.add(addAlarm);
		    
		    
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
