package view;

import javax.swing.*;

import common.UserInfo;
import controller.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import com.sun.awt.AWTUtilities;
import javax.swing.UIManager;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;

public class ClientLogin extends JFrame implements ActionListener{
	//set the components for the interface
    private JPanel panel_cent1,panel_cent2,panel_south;
    private JLabel logo, username_label,passwd_label;
    private JTextField usr_txt;
    private JPasswordField passwd_txt;
    private ClientRegister clientregister;
    private JPanel imagePanel;
    private Point origin;
    private ImageIcon background;
    private JPanel panel,panelx,panely;
    private JButton register_btn;
    private JButton login_btn;
    private String laf;
	
	public ClientLogin()
	{			
		laf = UIManager.getSystemLookAndFeelClassName();
		//Nimbus Effect 
		try {
	    	 for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
	    		 if ("Nimbus".equals(info.getName())) {
	    			 javax.swing.UIManager.setLookAndFeel(info.getClassName());
	    			 break;
	                
	    		 }
	    	 }
	     }
         catch (Exception e) {
        	e.printStackTrace();
    	 }
	       
	     JFrame.setDefaultLookAndFeelDecorated(true); 
	     
		//remove frame
        this.setUndecorated(true);

	    //JFrame.setDefaultLookAndFeelDecorated(true);  
		//AWTUtilities.setWindowShape(this,new RoundRectangle2D.Double(0.0D, 0.0D, this.getWidth(),this.getHeight(), 26.0D, 26.0D));
		
		background = new ImageIcon("image_material/bluedevil.png");
		// display the icon in a JLabel
		JLabel label = new JLabel(background);
		//set the size of the label based on the image
		label.setBounds(0, 0, background.getIconWidth(),background.getIconHeight());
		imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		
		imagePanel.setLayout(new GridLayout(3,1));


		this.getLayeredPane().setLayout(null);
		// set the image as background
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
		  
		this.setSize(background.getIconWidth(), background.getIconHeight());
		this.setResizable(false);
		this.setVisible(true);
		  
		
		/////////////////////////////////////////////////// 
		
		//north
		//logo=new JLabel(new ImageIcon("image_material/DuLogo.jpg"));
		panel=new JPanel();
		panelx=new JPanel();
		panely=new JPanel();
		panel.setOpaque(false);
		panelx.setOpaque(false);
		panely.setOpaque(false);

		//center
        panel_cent1=new JPanel();
        username_label=new JLabel("   Username     ");
        username_label.setFont(new Font("Serif",Font.PLAIN,30));
        username_label.setForeground(Color.white);
        usr_txt=new JTextField(10);
        usr_txt.setFont(new Font("Serif",Font.BOLD,20));
        panel_cent1.add(username_label);
        panel_cent1.add(usr_txt);

        panel_cent2=new JPanel();
        passwd_label=new JLabel("   Password      ");
        passwd_label.setFont(new Font("Serif",Font.PLAIN,30));
        passwd_label.setForeground(Color.white);
        passwd_txt=new JPasswordField(10);
        passwd_txt.setFont(new Font("Serif",Font.BOLD,20));
        panel_cent2.add(passwd_label);
        panel_cent2.add(passwd_txt);
        
		//south
		panel_south=new JPanel();
		register_btn=new JButton("Register");
		register_btn.setFont(new Font("Serif", Font.PLAIN, 24));
		register_btn.addActionListener(this);
		login_btn=new JButton("  Login  ");
		//login_btn.setSize(10, 20);
		login_btn.addActionListener(this);
		login_btn.setFont(new Font("Serif", Font.PLAIN, 24));
		panel_south.add(register_btn);
		panel_south.add(login_btn);
		
		panel_cent1.setOpaque(false);
		panel_south.setOpaque(false);
		panel_cent2.setOpaque(false);
		//add the components to the interface
		panel.setLayout(new GridLayout(3,1));
		
		panel.add(panel_cent1);
		panel.add(panel_cent2);
		panel.add(panel_south,"South");
											 
//		panel.setLayout(new GridLayout(3,2));
//		panel.add(username_label);
//		panel.add(usr_txt);
//		panel.add(passwd_label);
//		panel.add(passwd_txt);
//		panel.add(register_btn);
//		panel.add(login_btn);
		//set  the size, title, location and way to exit of the interface
		//this.setSize(502, 340);
		
		imagePanel.add(panelx);
		imagePanel.add(panely);
		imagePanel.add(panel);
		
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension framesize = this.getSize();
		int x = (int)screensize.getWidth()/2 - (int)framesize.getWidth()/2;
		int y = (int)screensize.getHeight()/2 - (int)framesize.getHeight()/2;
		this.setLocation(x,y); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        //this.setTitle("Dussenger");
        
        this.origin=new Point();
        panelx.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                origin.x = e.getX();
                origin.y = e.getY();
            }

            // right click to close the window
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3)
                    System.exit(0);
            }

            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                repaint();
            }
        });

        panelx.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                Point p = getLocation();
                setLocation(p.x + e.getX() - origin.x, p.y + e.getY()
                        - origin.y);
            }
        });
        
        panely.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                origin.x = e.getX();
                origin.y = e.getY();
            }

            // right click to close the window
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3)
                    System.exit(0);
            }

            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                repaint();
            }
        });

        panely.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                Point p = getLocation();
                setLocation(p.x + e.getX() - origin.x, p.y + e.getY()
                        - origin.y);
            }
        });
        //AWTUtilities.setWindowShape(this,new RoundRectangle2D.Double(0.0D, 0.0D, this.getWidth(),this.getHeight(), 16.0D, 16.0D));
	    
	    //reset to system's default lookandfeel
	    try {
	    	javax.swing.UIManager.setLookAndFeel(laf);
	     }
        catch (Exception e) {
       	e.printStackTrace();
   	 }

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//set the response for JButton jps_jb1
		if (e.getSource()==register_btn)
		{
			this.setVisible(false);
			try {
		           for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
		               if ("Nimbus".equals(info.getName())) {
		                   javax.swing.UIManager.setLookAndFeel(info.getClassName());
		                   break;
		                
		               }
		           }
		    	}
	        catch (Exception m) {
	               m.printStackTrace();
	    	    }
		       
		    JFrame.setDefaultLookAndFeelDecorated(true);  
			clientregister=new ClientRegister();
			AWTUtilities.setWindowShape(clientregister,new RoundRectangle2D.Double(0.0D, 0.0D, clientregister.getWidth(),clientregister.getHeight(), 16.0D, 16.0D));
			
			
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

