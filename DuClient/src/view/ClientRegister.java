package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;  

public class ClientRegister extends JFrame implements ActionListener{
	//error message frame
	private JFrame errFrame;
	
	//set the components for the interface
	JPanel jpm1,jpm2,jpm3,jps;
	JLabel jbln, jpm1_jbl,jpm2_jbl,jpm3_jbl;
	JTextField jtf;
    JPasswordField jpf1,jpf2;
    JButton jps_jb1,jps_jb2;
    
    char[] password1;
    char[] password2;
	
	public ClientRegister()
	{
		errFrame=new JFrame();
		//north
		jbln=new JLabel(new ImageIcon("image_material/Register.jpg"));

		//center
        jpm1=new JPanel();
        jpm1_jbl=new JLabel("   Set username     ");
        jpm1_jbl.setFont(new Font("Serif",Font.PLAIN,30));
        jpm1_jbl.setForeground(Color.blue);
        jtf=new JTextField(15);
        jtf.setFont(new Font("Serif",Font.BOLD,20));
        jpm1.add(jpm1_jbl);
        jpm1.add(jtf);

        jpm2=new JPanel();
        jpm2_jbl=new JLabel("   Set password     ");
        jpm2_jbl.setFont(new Font("Serif",Font.PLAIN,30));
        jpm2_jbl.setForeground(Color.blue);
        jpf1=new JPasswordField(15);
        jpf1.setFont(new Font("Serif",Font.BOLD,20));
        jpm2.add(jpm2_jbl);
        jpm2.add(jpf1);
        
        jpm3=new JPanel();
        jpm3_jbl=new JLabel("Confirm password ");
        jpm3_jbl.setFont(new Font("Serif",Font.PLAIN,30));
        jpm3_jbl.setForeground(Color.blue);
        jpf2=new JPasswordField(15);
        jpf2.setFont(new Font("Serif",Font.BOLD,20));
        jpm3.add(jpm3_jbl);
        jpm3.add(jpf2);
        
		//south
		jps=new JPanel();
		jps_jb1=new JButton("Confirm");
		jps_jb1.setFont(new Font("Serif", Font.PLAIN, 30));
		jps_jb1.addActionListener(this);
		jps_jb2=new JButton("  Cancel  ");
		jps_jb2.setFont(new Font("Serif", Font.PLAIN, 30));
		jps_jb2.addActionListener(this);
		jps.add(jps_jb1);
		jps.add(jps_jb2);
		
		//add the components to the interface
		this.setLayout(new GridLayout(5,1));
		this.add(jbln,"North");
		this.add(jpm1);
		this.add(jpm2);
		this.add(jpm3);
		this.add(jps,"South");
		
		//set  the size, title, location and way to exit of the interface
		this.setSize(502, 500);
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
		ClientRegister clientRegister=new ClientRegister();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//set the response for JButton jps_jb1 and jps_jb2 under different condition based on the content in JTextField jtf and JPasswordField jpf1,jpf2;
		if (e.getSource()==jps_jb1)
		{   
            if(jtf.getText().length()==0||jpf1.getPassword().length==0)
            {
            	char[] password1 = jpf1.getPassword();
            	JOptionPane.showMessageDialog(errFrame,  
                        "The username or password can not be empty, please enter again.",  
                        "Error",  
                        JOptionPane.ERROR_MESSAGE); 
            	//erase the content of the password for security
            	Arrays.fill(password1, '0');  
            }
            else
            {
				char[] password2 = jpf2.getPassword();  
	            if (isPasswordMatched(password2)) 
	            {
	            	dispose();
	            	new ClientLogin();
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

		if (e.getSource()==jps_jb2)
		{
			dispose();
			new ClientLogin();
			
		}
	}

	private boolean isPasswordMatched(char[] password) {
        boolean isMatched = true;  
        char[] correctPassword = jpf1.getPassword();  
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
