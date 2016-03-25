package view;

import javax.swing.*;

import common.UserInfo;
import controller.*;
import java.awt.*;
import java.awt.event.*;

public class ClientLogin extends JFrame implements ActionListener{
	//set the components for the interface
	JPanel panel_cent1,panel_cent2,panel_south;
	JLabel logo, username_label,passwd_label;
	JTextField usr_txt;
    JPasswordField passwd_txt;
   
    
    JButton register_btn,login_btn;
	
	public ClientLogin()
	{
		//north
		logo=new JLabel(new ImageIcon("image_material/DuLogo.jpg"));

		//center
        panel_cent1=new JPanel();
        username_label=new JLabel("Username");
        username_label.setFont(new Font("Serif",Font.PLAIN,30));
        username_label.setForeground(Color.blue);
        usr_txt=new JTextField(15);
        usr_txt.setFont(new Font("Serif",Font.BOLD,20));
        panel_cent1.add(username_label);
        panel_cent1.add(usr_txt);

        panel_cent2=new JPanel();
        passwd_label=new JLabel("Password");
        passwd_label.setFont(new Font("Serif",Font.PLAIN,30));
        passwd_label.setForeground(Color.blue);
        passwd_txt=new JPasswordField(15);
        passwd_txt.setFont(new Font("Serif",Font.BOLD,20));
        panel_cent2.add(passwd_label);
        panel_cent2.add(passwd_txt);
        
		//south
		panel_south=new JPanel();
		register_btn=new JButton("Register");
		register_btn.setFont(new Font("Snap ITC", Font.PLAIN, 24));
		register_btn.addActionListener(this);
		login_btn=new JButton("  Login  ");
		login_btn.addActionListener(this);
		login_btn.setFont(new Font("Snap ITC", Font.PLAIN, 24));
		panel_south.add(register_btn);
		panel_south.add(login_btn);
		
		//add the components to the interface
		this.setLayout(new GridLayout(4,1));
		this.add(logo,"North");
		this.add(panel_cent1);
		this.add(panel_cent2);
		this.add(panel_south,"South");
		
		//set  the size, title, location and way to exit of the interface
		this.setSize(502, 340);
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension framesize = this.getSize();
		int x = (int)screensize.getWidth()/2 - (int)framesize.getWidth()/2;
		int y = (int)screensize.getHeight()/2 - (int)framesize.getHeight()/2;
		this.setLocation(x,y); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setTitle("Dussenger");
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		//set the response for JButton jps_jb1
		if (e.getSource()==register_btn)
		{
			this.setVisible(false);
			new ClientRegister();
			
		}
		if(e.getSource() == login_btn)
		{
			if(usr_txt.getText().length() == 0|| passwd_txt.getPassword().length == 0){
				JOptionPane.showMessageDialog(panel_south, "Invalid Input");
				usr_txt.setText("");
				passwd_txt.setText("");
				
			}
			else{
				
				
				UserInfo info = new UserInfo();
				info.setMsg_type(0);
				info.setUserId(usr_txt.getText());
				info.setPasswd(new String(passwd_txt.getPassword()));
				Connection conn = new Connection();
				if(conn.loginInfo(info))
				{
					this.setVisible(false);
					MainInterface main = new MainInterface(usr_txt.getText());
					ManageChat.addView("1", main);
				}
				else
				{
					JOptionPane.showMessageDialog(panel_south, "Authentication fail");
					usr_txt.setText("");
					passwd_txt.setText("");
				}
				
				
			}
		}
	}

}
