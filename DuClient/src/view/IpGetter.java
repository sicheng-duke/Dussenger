package view;

import javax.swing.*;

import controller.*;
import controller.IPManage;
import common.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
public class IpGetter extends JFrame implements ActionListener{
	JPanel panel1,panel2;
	JLabel ip_label;
	JTextField ip_address;
	JButton cfm;
	
	public IpGetter()
	{
		panel1=new JPanel();
		ip_label=new JLabel("Type in Server IP");

		ip_address=new JTextField(15);
		cfm=new JButton("Confirm");
        ip_label.setFont(new Font("Serif",Font.PLAIN,30));
        ip_label.setForeground(Color.BLACK);
        ip_address.setFont(new Font("Serif",Font.BOLD,20));
        panel1.add(ip_label);
        panel1.add(ip_address);
        
        panel2=new JPanel();
        cfm.addActionListener(this);

        cfm.setFont(new Font("Serif",Font.PLAIN,20));
        cfm.setForeground(Color.BLACK);
        panel2.add(cfm);
        
        
		this.setLayout(new GridLayout(2,1));
		this.add(panel1);
		this.add(panel2);
		
		this.setSize(402, 150);
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension framesize = this.getSize();
		int x = (int)screensize.getWidth()/2 - (int)framesize.getWidth()/2;
		int y = (int)screensize.getHeight()/2 - (int)framesize.getHeight()/2;
		this.setLocation(x,y); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setTitle("Dussenger");
		
		
	}
	
	public static void main(String[] args) {
		IpGetter ipgetter=new IpGetter();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == cfm)
		{
			if(!validIP(ip_address.getText())){
				JOptionPane.showMessageDialog(panel2, "Invalid Input");
				ip_address.setText("");
			}
			else{
				
				String str=ip_address.getText();
				IPManage.setIP(str);
				Connection conn = new Connection();
				if(conn.first_connect(new UserInfo(2)))
				{					
					this.setVisible(false);
					new ClientLogin();
				}
				else
				{
					JOptionPane.showMessageDialog(panel2, "Invalid IP");
					ip_address.setText("");
				}
			}
		}
	}
	public static boolean validIP(String ip) {
	    if (ip == null || ip.isEmpty()) return false;
	    ip = ip.trim();
	    if ((ip.length() < 6) & (ip.length() > 15)) return false;

	    try {
	        Pattern pattern = Pattern.compile("^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
	        Matcher matcher = pattern.matcher(ip);
	        return matcher.matches();
	    } catch (PatternSyntaxException ex) {
	        return false;
	    }
	}

}
