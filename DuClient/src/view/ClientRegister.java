package view;

import javax.swing.*;

import com.sun.awt.AWTUtilities;

import common.Message;
import common.UserInfo;
import controller.ManageChat;
import controller.ManageThread;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.io.ObjectOutputStream;
import java.util.Arrays;  
import controller.*;
public class ClientRegister extends JFrame implements ActionListener{
	//error message frame
	private JFrame errFrame;
	//should not contain %:%
	//set the components for the interface
	JPanel jpm1,jpm2,jpm3,jps;
	JLabel jbln, jpm1_jbl,jpm2_jbl,jpm3_jbl;
	JTextField usr_txt;
    JPasswordField passwd_txt,passwd_confirm;
    JButton register,cancel;
    
    //

    private JPanel imagePanel;
    private Point origin;
    private ImageIcon background;
    JPanel panel,panelx,panely;
    
    //
    
    char[] password1;
    char[] password2;
	
	public ClientRegister()
	{
		
		errFrame=new JFrame();
		this.setUndecorated(true);
		
		background = new ImageIcon("image_material/bluedevil.png");
		// display the icon in a JLabel
		JLabel label = new JLabel(background);
		
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
		
		panel=new JPanel();
		panelx=new JPanel();
		panely=new JPanel();
		panel.setOpaque(false);
		panelx.setOpaque(false);
		panely.setOpaque(false);
		  
		

		//center
        jpm1=new JPanel();
        jpm1_jbl=new JLabel("   Set username     ");
        jpm1_jbl.setFont(new Font("Serif",Font.PLAIN,20));
        jpm1_jbl.setForeground(Color.white);
        usr_txt=new JTextField(10);
        usr_txt.setFont(new Font("Serif",Font.BOLD,20));
        jpm1.add(jpm1_jbl);
        jpm1.add(usr_txt);

        jpm2=new JPanel();
        jpm2_jbl=new JLabel("   Set password     ");
        jpm2_jbl.setFont(new Font("Serif",Font.PLAIN,20));
        jpm2_jbl.setForeground(Color.white);
        passwd_txt=new JPasswordField(10);
        passwd_txt.setFont(new Font("Serif",Font.BOLD,20));
        jpm2.add(jpm2_jbl);
        jpm2.add(passwd_txt);
        
        jpm3=new JPanel();
        jpm3_jbl=new JLabel("Confirm password");
        jpm3_jbl.setFont(new Font("Serif",Font.PLAIN,20));
        jpm3_jbl.setForeground(Color.white);
        passwd_confirm=new JPasswordField(10);
        passwd_confirm.setFont(new Font("Serif",Font.BOLD,20));
        jpm3.add(jpm3_jbl);
        jpm3.add(passwd_confirm);
        
		//south
		jps=new JPanel();
		register=new JButton("Confirm");
		register.setPreferredSize(new Dimension(140,40));
		register.setFont(new Font("Serif", Font.PLAIN, 30));
		register.addActionListener(this);
		cancel=new JButton(" Cancel ");
		cancel.setPreferredSize(new Dimension(140,40));
		cancel.setFont(new Font("Serif", Font.PLAIN, 30));
		cancel.addActionListener(this);
		jps.add(register);
		jps.add(cancel);
		
		jpm1.setOpaque(false);
		jpm2.setOpaque(false);
		jpm3.setOpaque(false);
		jps.setOpaque(false);
		//add the components to the interface
		panel.setLayout(new GridLayout(4,1));
		
		panel.add(jpm1);
		panel.add(jpm2);
		panel.add(jpm3);
		panel.add(jps,"south");
		 
		
		
		//add the components to the interface
		panel.setLayout(new GridLayout(4,1));

		imagePanel.add(panelx);
		imagePanel.add(panely);
		imagePanel.add(panel);
		//set  the size, title, location and way to exit of the interface
		//this.setSize(510, 500);
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
        

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		//set the response for JButton jps_jb1 and jps_jb2 under different condition based on the content in JTextField jtf and JPasswordField jpf1,jpf2;
		if(e.getSource() == register){

		}
		if (e.getSource()==register)
		{   
            if(usr_txt.getText().length()==0||passwd_txt.getPassword().length==0)
            {
            	char[] password1 = passwd_txt.getPassword();
            	JOptionPane.showMessageDialog(errFrame,  
                        "The username or password can not be empty, please enter again.",  
                        "Error",  
                        JOptionPane.ERROR_MESSAGE); 
            	//erase the content of the password for security
            	Arrays.fill(password1, '0');  
            }
            else
            {
				char[] password2 = passwd_confirm.getPassword();  
	            if (isPasswordMatched(password2)) 
	            {
	    			try {
	    				
	    				UserInfo info = new UserInfo();
	    				info.setMsg_type(1);
	    				info.setUserId(usr_txt.getText());
	    				info.setPasswd(new String(passwd_txt.getPassword()));
	    				Connection conn = new Connection();
	    				if(conn.register(info))
	    				{
	    					this.setVisible(false);
	    	            	dispose();
	    	       	     try {
	    	    	    	 for (javax.swing.UIManager.LookAndFeelInfo inf : javax.swing.UIManager.getInstalledLookAndFeels()) {
	    	    	    		 if ("Nimbus".equals(inf.getName())) {
	    	    	    			 javax.swing.UIManager.setLookAndFeel(inf.getClassName());
	    	    	    			 break;
	    	    	                
	    	    	    		 }
	    	    	    	 }
	    	    	     }
	    	             catch (Exception m) {
	    	            	m.printStackTrace();
	    	        	 }
	    	    	       
	    	    	     JFrame.setDefaultLookAndFeelDecorated(true);  
	    	    	    
	    	    	     ClientLogin clientLogin=new ClientLogin();
	    	    	     AWTUtilities.setWindowShape(clientLogin,new RoundRectangle2D.Double(0.0D, 0.0D, clientLogin.getWidth(),clientLogin.getHeight(), 16.0D, 16.0D));
	    				}
	    				else
	    				{
	    					//JOptionPane.showMessageDialog(panel_south, "Authentication fail");
	    					JOptionPane.showMessageDialog(errFrame, "register fail, user exist");
	    					System.out.println("register fail");
	    					usr_txt.setText("");
	    					passwd_txt.setText("");
	    					passwd_confirm.setText("");
	    				}
	    			} catch (Exception e1) {
	    				e1.printStackTrace();
	    				// TODO: handle exception
	    			}

	            }
	            else
	            {
	                JOptionPane.showMessageDialog(errFrame,  
	                        "The passwords entered are not the same, please enter again.",  
	                        "Password Error",  
	                        JOptionPane.ERROR_MESSAGE);  
	            }  
	            //erase the content of the password for security
	            Arrays.fill(password2, '0');  
            }
        }

		if (e.getSource()==cancel)
		{
			dispose();
      	     try {
   	    	 for (javax.swing.UIManager.LookAndFeelInfo inf : javax.swing.UIManager.getInstalledLookAndFeels()) {
   	    		 if ("Nimbus".equals(inf.getName())) {
   	    			 javax.swing.UIManager.setLookAndFeel(inf.getClassName());
   	    			 break;
   	                
   	    		 }
   	    	 }
   	     }
            catch (Exception m) {
           	m.printStackTrace();
       	 }
   	       
   	     JFrame.setDefaultLookAndFeelDecorated(true);  
   	    
   	     ClientLogin clientLogin=new ClientLogin();
   	     AWTUtilities.setWindowShape(clientLogin,new RoundRectangle2D.Double(0.0D, 0.0D, clientLogin.getWidth(),clientLogin.getHeight(), 16.0D, 16.0D));
			
		}
	}

	private boolean isPasswordMatched(char[] password) {
        boolean isMatched = true;  
        char[] correctPassword = passwd_txt.getPassword();  
        //check if the content in JPasswordField jpf1 and jpf2 are matched
        if (password.length != correctPassword.length) {  
            isMatched = false;  
        } else {  
            isMatched = Arrays.equals(password, correctPassword);  
        }  
  
        //erase the content of the password for security
        Arrays.fill(correctPassword, '0');  
  
        return isMatched;  
    } 



}