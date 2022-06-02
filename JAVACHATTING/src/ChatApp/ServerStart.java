package ChatApp;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Spring;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;



public class ServerStart extends JFrame implements Runnable{
private JPanel contentPane;
private JTextField port;
static int pno;
static Socket socket;
public static Vector client = new Vector();
static String grpName;
public ServerStart(Socket socket) {
	try {
		ServerStart.socket=socket;
}
	catch(Exception e) {
		e.printStackTrace();
	}
}
public ServerStart() {
	setType(Type.POPUP);
	setResizable(false);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100,100,313,393);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5,5,5,5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	
	JPanel panel = new JPanel();
	panel.setBorder(new LineBorder(new Color(0,0,0),2,true));
	panel.setBackground(new Color(46,139,87));
	panel.setBounds(0,0,297,55);
	contentPane.add(panel);
	panel.setLayout(null);
	
	JLabel logo = new JLabel("");
	logo.setBounds(30,11,42,30);
	ImageIcon r = new ImageIcon("C:\\Users\\vutd2\\eclipse-workspace\\JAVACHATTING\\src\\ChatIcon\\messicon.jpg");
	Image rimage = r.getImage();
	Image ringScale = rimage.getScaledInstance(logo.getWidth(),logo.getHeight(),Image.SCALE_SMOOTH);
	ImageIcon rscaledIcon = new ImageIcon(ringScale);
	logo.setIcon(rscaledIcon);
	panel.add(logo);
	
	JLabel lbwhatapp = new JLabel("Messenger");
	lbwhatapp.setFont(new Font("Arial",Font.BOLD,25));
	lbwhatapp.setForeground(new Color(0,0,0));
	lbwhatapp.setHorizontalAlignment(SwingConstants.CENTER);
	lbwhatapp.setBounds(43,11,215,33);
	panel.add(lbwhatapp);
	
	JPanel panel_1 = new JPanel();
	panel_1.setBackground(new Color(60,179,113));
	panel_1.setBorder(new LineBorder(new Color(0,0,0),2,true));
	panel_1.setBounds(0,56,297,298);
	contentPane.add(panel_1);
	panel_1.setLayout(null);

	
	JLabel lblPortNo = new JLabel("Port No :");
	lblPortNo.setHorizontalAlignment(SwingConstants.CENTER);
	lblPortNo.setFont(new Font("Arial Black",Font.BOLD,16));
	lblPortNo.setBounds(22,91,95,23);
	panel_1.add(lblPortNo);
	
	port = new JTextField();
	port.setColumns(10);
	port.setBounds(125,94,160,23);
	panel_1.add(port);
	
	JLabel v2 = new JLabel("Moi ban nhap");
	v2.setHorizontalAlignment(SwingConstants.CENTER);
	v2.setForeground(Color.RED);
	v2.setBounds(115,123,160,17);
	panel_1.add(v2);
	
	JButton bntnewButton = new JButton("Start");
	bntnewButton.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String p = port.getText();		
			
			if(p.isEmpty()) {
				v2.setText("This Field is required");
			}
			else 
			{
				int po = Integer.parseInt(p);
				pno=po;
				dispose();
				
				ServerSocket s;
				try {
					s = new ServerSocket(po);
					JOptionPane.showMessageDialog(null, "SerVer Start");
					while(true) {
						Socket socket = s.accept();	
						ServerStart  server = new ServerStart(socket);
						Thread thread = new Thread(server);
						thread.start();
						
					}
			} catch (Exception e1) {
				// TODO: handle exception
				e1.printStackTrace();
			}
		}
		}
	});
	bntnewButton.setBackground(new Color(46,139,87));
	bntnewButton.setFont(new Font("Arial Black",Font.BOLD,18));
	bntnewButton.setBounds(21,151,254,32);
	panel_1.add(bntnewButton);
	}
public static void main(String[] args) {
EventQueue.invokeLater(new Runnable() {
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			ServerStart frame = new ServerStart();
			frame.setVisible(true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
});
}
	@Override
	public void run() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		
			client.add(writer);
			while(true) {
				String data = reader.readLine().trim();
				for(int i=0;i<client.size();i++) {
					try {
						PrintWriter bw = (PrintWriter)client.get(i);
						bw.write(data);
						bw.write("\r\n");
						bw.flush();
					} catch (Exception e) {}
						// TODO: handle exception	
				}
			
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		// TODO Auto-generated method stub	
	}

}
