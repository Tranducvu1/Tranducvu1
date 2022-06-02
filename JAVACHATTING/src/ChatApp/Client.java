package ChatApp;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.annotation.processing.Messager;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Client extends JFrame{
private JPanel contentPane;
private static JTextArea textArea;
private static JTextField message;
static ServerSocket server;
static Socket client;
static BufferedReader br;
static PrintWriter out;

public Client() {
    JButton document = new JButton("Open");
	setResizable(false);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100,100,322,522);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5,5,5,5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	
	JPanel panel = new JPanel();
	panel.setBorder(new LineBorder(new Color(0,0,0),1,true));
	panel.setBackground(new Color(46,139,87));
	panel.setBounds(0,0,316,55);
	contentPane.add(panel);
	panel.setLayout(null);
	
	JLabel back = new JLabel("");
	back.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
		try {
			int yesorno = JOptionPane.showConfirmDialog(null, "Do you want to close Connection to Server...");
			if(yesorno==0) {
				dispose();
				client.close();
				JOptionPane.showConfirmDialog(null, "Connection Closed");
			}
		} catch (Exception e1) {
			// TODO: handle exception
			JOptionPane.showConfirmDialog(null, "Connection Closed");
		}	
		}
	});
	back.setBounds(0,11,33,33);
	ImageIcon i = new ImageIcon("C:\\Users\\vutd2\\eclipse-workspace\\aJAVACHATTING\\src\\ChatIcon\\back1.jpg");
	Image image = i.getImage();
	Image imgScale = image.getScaledInstance(back.getWidth(), back.getHeight(), image.SCALE_SMOOTH);
	ImageIcon scaledIcon = new ImageIcon(imgScale);
	back.setIcon(scaledIcon);
	panel.add(back);
	
	JLabel profile = new JLabel("");
	profile.setBounds(32,9,39,40);
	ImageIcon i1 = new ImageIcon("C:\\Users\\vutd2\\eclipse-workspace\\JAVACHATTING\\src\\ChatIcon\\profile.jpg");
	Image image1 =i1.getImage();
	Image imgScale1=image1.getScaledInstance(profile.getWidth(), profile.getHeight(), Image.SCALE_SMOOTH);
	ImageIcon scaledIcon1 = new ImageIcon(imgScale1);
	profile.setIcon(scaledIcon1);
	panel.add(profile);
	
	 
	
	JLabel lblVudev = new JLabel("Friends");
	lblVudev.setForeground(Color.WHITE);
	lblVudev.setFont(new Font("Arial Black",Font.BOLD,20));
	lblVudev.setBounds(71,11,140,24);
	panel.add(lblVudev);
	
	JLabel phone = new  JLabel("");
	phone.setBounds(190,11,30,30);
	ImageIcon i2 = new ImageIcon("C:\\Users\\vutd2\\eclipse-workspace\\JAVACHATTING\\src\\ChatIcon\\phonecall.jpg");
	Image image2 = i2.getImage();
	Image imageScale2 = image2.getScaledInstance(phone.getWidth(), phone.getHeight(), Image.SCALE_SMOOTH);
	ImageIcon scaledIcon2 = new ImageIcon(imageScale2);
	phone.setIcon(scaledIcon2);
	panel.add(phone);
	
	JLabel video = new JLabel("");
	video.setBounds(230,11,30,30);
	ImageIcon i3 = new ImageIcon("C:\\Users\\vutd2\\eclipse-workspace\\JAVACHATTING\\src\\ChatIcon\\video-call.jpg");
	Image image3 = i3.getImage();
	Image imgScaled3 = image3.getScaledInstance(video.getWidth(), video.getHeight(), Image.SCALE_SMOOTH);
	ImageIcon scaledIcon3 = new ImageIcon(imgScaled3);
	video.setIcon(scaledIcon3);
	panel.add(video);
	
	JLabel option = new JLabel("");
	option.setBounds(270, 11,30, 30);
	ImageIcon i4 = new ImageIcon("C:\\\\Users\\\\vutd2\\\\eclipse-workspace\\\\JAVACHATTING\\\\src\\\\ChatIcon\\\\voice.jpg");
	Image image4 =i4.getImage();
	Image imgScale4 =image4.getScaledInstance(option.getWidth(),option.getHeight(), Image.SCALE_SMOOTH);
	ImageIcon scaledIcon4 = new ImageIcon(imgScale4);
	option.setIcon(scaledIcon4);
	panel.add(option);
	
	JPanel panel_1 = new JPanel();
	panel_1.setBounds(0,440,316,40);
	contentPane.add(panel_1);
	panel_1.setLayout(null);
	
	JLabel emoji = new JLabel("");
	emoji.setBounds(0,0,39,40);
	ImageIcon i5=new ImageIcon("C:\\Users\\vutd2\\eclipse-workspace\\JAVACHATTING\\src\\ChatIcon\\emoji.png");
	Image image5=i5.getImage();
	Image imgScaled5 = image5.getScaledInstance(emoji.getWidth(), emoji.getHeight(),Image.SCALE_SMOOTH);
	ImageIcon scaledIcon5 = new ImageIcon(imgScaled5);
	emoji.setIcon(scaledIcon5);
	panel_1.add(emoji);
	
	message = new JTextField();
	message.setBounds(40,0,189,40);
	panel_1.add(message);
	message.setColumns(10);
	
	JLabel send = new JLabel("");
	send.addMouseListener(new MouseAdapter() {
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			startWriting();
		}	
});
 
    
	send.setBounds(261, 0, 45, 40);
	ImageIcon i6 = new ImageIcon("C:\\\\Users\\\\vutd2\\\\eclipse-workspace\\\\JAVACHATTING\\\\src\\\\ChatIcon\\\\send.jpg");
	Image image6 =i6.getImage();
	Image imgScale6=image6.getScaledInstance(send.getWidth(), send.getHeight(), Image.SCALE_SMOOTH);
	ImageIcon scaledIcon6= new ImageIcon(imgScale6);
	send.setIcon(scaledIcon6);
	panel_1.add(send);
	
	//JLabel document = new JLabel("");
	document.setBounds(230,5,30,30);
	ImageIcon i7 = new ImageIcon("C:\\\\Users\\\\vutd2\\\\eclipse-workspace\\\\JAVACHATTING\\\\src\\\\ChatIcon\\\\url.jpg");
	Image image7 = i7.getImage();
	Image imgscaled7=image7.getScaledInstance(document.getWidth(), document.getHeight(),Image.SCALE_SMOOTH);
	ImageIcon scaledIcon7 = new ImageIcon(imgscaled7);
	document.setIcon(scaledIcon7);
	panel_1.add(document);
	
	document.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                document();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    });
	
	JScrollPane scroolPane = new JScrollPane();
	scroolPane.setBounds(0,55,308,386);
	contentPane.add(scroolPane);
	
	textArea = new JTextArea();
	textArea.setEditable(false);
	textArea.setFont(new Font("SansSerif",Font.BOLD,15));
	scroolPane.setViewportView(textArea);
	
	startClient();
	startReading();
	
	
}

void document() throws IOException {
    JFileChooser jFileChooser = new JFileChooser();
    int returnValue = jFileChooser.showOpenDialog(null);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
        File selectedFile = jFileChooser.getSelectedFile();
        FileInputStream inputStream = new FileInputStream(selectedFile.getAbsolutePath());
        int i=0;
        String data = "";
        while ((i=inputStream.read())!=-1){
            data += (char)i;
        }
        textArea.setText(data);
    }
}
public static void startClient() {
	try {
		client = new Socket(ClientStart.ip,ClientStart.pNo);
		br=new BufferedReader(new InputStreamReader(client.getInputStream()));
		out=new PrintWriter(client.getOutputStream());
	} catch (Exception e) {
		// TODO: handle exception
	}
}
public void startReading() {
	Runnable r1=()->{
			
			try 
			{
				while(true)
				{
					String msg = br.readLine();
					if(!msg.isEmpty()) {
						if(textArea.getText().isEmpty()) {
							Calendar cal = Calendar.getInstance();
							SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
							textArea.setText(msg+"\n"+sdf.format(cal.getTime()));
							msg="";
						}
						else {
							Calendar cal = Calendar.getInstance();
							SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
							textArea.setText(textArea.getText()+"\n"+msg+"\n"+sdf.format(cal.getTime()));
							msg="";
						}
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "Connection Closed");
			}
	};
			
	new Thread(r1).start();
}


public static void  startWriting() {
	// TODO Auto-generated method stub
	try {
		String msg= message.getText();
		if(!msg.isEmpty()) 
		{
			if(textArea.getText().isEmpty())
			{
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat sdf  = new SimpleDateFormat("HH:mm");
				textArea.setText("\t\t"+msg+"\n\t\t\t"+sdf.format(cal.getTime()));
                message.getText();
                out.println(msg);
                 out.flush();

			}else {
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat sdf  = new SimpleDateFormat("HH:mm");
				textArea.setText(textArea.getText()+"\n\t\t"+msg+"\n\t\t\t"+sdf.format(cal.getTime()));
                message.getText();
                out.println(msg);
                 out.flush();
			}
		}
		
	} catch (Exception e) {
		// TODO: handle exception
		JOptionPane.showMessageDialog(null, "Connection Closed");
	}
}
}

