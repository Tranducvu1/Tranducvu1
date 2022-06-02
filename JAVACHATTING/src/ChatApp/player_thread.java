package ChatApp;



import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.SourceDataLine;

public class player_thread extends Thread{
public DatagramSocket din;
public SourceDataLine audio_out;
byte[] buffer = new byte[512];
public InetAddress server_ip;
public int server_port;
@Override
public void run() {

	while(Voice.calling) {
	
	try {
		DatagramPacket incoming = new DatagramPacket(buffer,buffer.length);
din.receive(incoming);
buffer = incoming.getData();
audio_out.write(buffer,0,buffer.length);
	} catch (IOException ex) {
		// TODO Auto-generated catch block
		Logger.getLogger(recorder_thread.class.getName()).log(Level.SEVERE,null,ex);
	}
	}
audio_out.close();
audio_out.drain();
	System.out.print("Thread stop");
}
}