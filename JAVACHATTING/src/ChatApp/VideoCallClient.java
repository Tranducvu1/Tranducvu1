
package ChatApp;



import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.github.sarxos.webcam.Webcam;
public class VideoCallClient  extends JFrame{

	JFrame fcus= new JFrame("Call");
	static JLabel img_Client = new JLabel();


@SuppressWarnings("unchecked")
	public  VideoCallClient () {
		// TODO Auto-generated constructor stub
		fcus.setLocation(300,20);

		Container contc = fcus.getContentPane();
		contc.add(img_Client);


		img_Client.setBounds(0,0,439,304);
	fcus.add(img_Client);
		img_Client.setLayout(null);
		fcus.setSize(400,400);
		fcus.setVisible(true);
}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub

	
	java.awt.EventQueue.invokeLater(new Runnable() {
	
		@Override
		public void run() {
			// TODO Auto-generated method stub
			new VideoCallClient().setVisible(true);
}
	

	});
	Socket a = new Socket("192.168.1.5",6001);
ObjectOutputStream out = new ObjectOutputStream(a.getOutputStream());
	ImageIcon ic;
	BufferedImage br;

	Webcam cam = Webcam.getDefault();
	cam.open();
	while(true) {
		br = cam.getImage();
		ic = new ImageIcon(br);
		out.writeObject(ic);
		out.flush();
		img_Client.setIcon(ic);
	}
}
}
	


