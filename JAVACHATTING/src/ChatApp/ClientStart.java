package ChatApp;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
//import data.DataFile;
//import tags.Decode;
//import tags.Encode;
//import tags.Tags;
public class ClientStart extends JFrame{
private JPanel contentPane;
private JTextField name;
private JTextField port;
static String cName;
static int pNo;
static Socket socket;
static String ip;
static JTextField i;
public ClientStart(Socket socket) {
	try {
		ClientStart.socket=socket;
}
	catch(Exception e) {
		e.printStackTrace();
	}
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
EventQueue.invokeLater(new Runnable() {
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			ClientStart frame = new ClientStart();
			frame.setVisible(true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
});
	}
public ClientStart() {
	setResizable(false);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100,313,393);
	contentPane = new  JPanel();
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
	logo.setBounds(10,11,52,33);
	ImageIcon r = new ImageIcon("C:\\Users\\vutd2\\eclipse-workspace\\JAVACHATTING\\src\\ChatIcon\\messicon.jpg");
	Image rimage = r.getImage();
	Image rimgScale = rimage.getScaledInstance(logo.getWidth(),logo.getHeight(),Image.SCALE_SMOOTH);
	ImageIcon rscaledIcon = new ImageIcon(rimgScale);
	logo.setIcon(rscaledIcon);
	panel.add(logo);

	
	JLabel lbwhatapp = new JLabel("Messenger");
	lbwhatapp.setFont(new Font("Arial",Font.BOLD,30));
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
	
	JLabel lblChatAs = new JLabel("Ac:");
	lblChatAs.setFont(new Font("Arial Black",Font.BOLD,16));
	lblChatAs.setHorizontalAlignment(SwingConstants.CENTER);
	lblChatAs.setBounds(21,42,84,23);
	panel_1.add(lblChatAs);
	
	name = new JTextField();
	name.setBounds(125,45,160,23);
	panel_1.add(name);
	name.setColumns(10);
	
	JLabel v1 =  new  JLabel("^");
	v1.setForeground(new Color(255,0,0));
	v1.setHorizontalAlignment(SwingConstants.CENTER);
	v1.setBounds(115,71,160,17);
	panel_1.add(v1);
	
	JLabel lblportno = new JLabel("Port No: ");
	lblportno.setHorizontalAlignment(SwingConstants.CENTER);
	lblportno.setFont(new Font("Arial Black",Font.BOLD,16));
	lblportno.setBounds(20,91,95,23);
	panel_1.add(lblportno);
	
	port = new JTextField();
	port.setColumns(10);
	port.setBounds(125,95,160,20);
	panel_1.add(port);
	
	JLabel v2 = new JLabel("^");
	v2.setHorizontalAlignment(SwingConstants.CENTER);
	v2.setForeground(Color.RED);
	v2.setBounds(115,123,160,17);
	panel_1.add(v2);
	
	JLabel lblipaddress = new JLabel("IP Address: ");
	lblipaddress.setHorizontalAlignment(SwingConstants.CENTER);
	lblipaddress.setFont(new Font("Arial Black",Font.BOLD,16));
	lblipaddress.setBounds(10,140,110,23);
	panel_1.add(lblipaddress);
	
	i = new JTextField();
	i.setColumns(10);
	i.setBounds(125,143,160,23);
	panel_1.add(i);
	
	JLabel v3 = new JLabel("^");
	v3.setHorizontalAlignment(SwingConstants.CENTER);
	v3.setForeground(Color.RED);
	v3.setBounds(115,172,160,17);
	panel_1.add(v3);

	JButton bntnewButton = new JButton("Start");
	bntnewButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String n = name.getText();
			String p = port.getText();
			String ipaddress=i.getText();
			if(n.isEmpty()) {
				v1.setText("This Field is required");
			} else if(p.isEmpty()) {
				v2.setText("This Field is required");
			}
			else if(ipaddress.isEmpty()) {
				v3.setText("This Field is required");
			
			} else {
				int po=Integer.parseInt(p);
			cName=n;
			pNo=po;
			ip=ipaddress;
			
			Client c = new Client();
			dispose();
			c.setVisible(true);
			
			}
		}
	});
	bntnewButton.setBackground(new Color(46,139,87));
	bntnewButton.setFont(new Font("Arial Black",Font.BOLD,18));
	bntnewButton.setBounds(21,218,254,32);
	panel_1.add(bntnewButton);

}	
}