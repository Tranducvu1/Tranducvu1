package ChatApp;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.xml.transform.Source;



//@SuppressWarnings("serial")
@SuppressWarnings("unchecked")
public class server_fr extends JFrame{
	
	public int port = 7800;
	
	public static AudioFormat getAudioFormat() {
		float sampleRate = 8000.0F;
		int sameSizeInbits =16;
		int channel = 2;
		boolean signed = true;
		boolean bigEndian =   false;
		
		return new AudioFormat(sampleRate, sameSizeInbits, channel, signed, bigEndian);
		}
	public SourceDataLine audio_out;
	//TargetDataLine audio_in;

	public server_fr() {
		JFrame fc= new JFrame();
		JLabel lbvoice = new JLabel("VOICE");
		JButton btstart = new JButton("Start");
		JButton btstop = new JButton("Stop");
			lbvoice.setLayout(null);
			lbvoice.setBounds(110,60,100,30);
			lbvoice.setFont(new Font("Tahoma", Font.BOLD, 16));
			lbvoice.setForeground(Color.BLUE);
			fc.setLocation(500,200);
			fc.setLocation(500,200);
			fc.setLayout(null);
			fc.add(lbvoice);
			btstart.setBounds(40,150,80,30);
			btstart.setFont(new Font("Tahoma", Font.BOLD, 12));
			fc.add(btstart);
			btstop.setBounds(160,150,80,30);
			btstop.setFont(new Font("Tahoma", Font.BOLD, 12));
			fc.add(btstop);
			 //fc.pack();
			fc.setSize(300,300);
			fc.setVisible(true);

btstart.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		init_audio();
	}
});

btstop.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Voice.calling=false;
		btstart.setVisible(true);
		btstop.setVisible(false);
	}
});
}
	public static void main(String[] args)  {
		java.awt.EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
		
				new client_fr().setVisible(true);;
			}
		});
	
	}
	
	public void init_audio() {
		try {
			AudioFormat format =getAudioFormat();
			DataLine.Info info = new DataLine.Info(TargetDataLine.class,format);
			if(!AudioSystem.isLineSupported(info)) {
				System.out.println("Not support");
				System.exit(0);
			}
			audio_out = (SourceDataLine) AudioSystem.getLine(info);
			audio_out.open(format);
			audio_out.start();
        player_thread p = new player_thread();
      p.din = new DatagramSocket(port);
      p.audio_out = audio_out;
      server_voice.calling = true;
      p.start();
      btstart.setEnabled(false);
		} catch (LineUnavailableException  | SocketException ex) {
			Logger.getLogger(client_fr.class.getName()).log(Level.SEVERE,null,ex);
		}
	}


private javax.swing.JButton btstart;
private javax.swing.JButton btstop;
}

