package duInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClientLogin extends JFrame implements ActionListener{
	
	JPanel jpm1,jpm2,jps;
	JLabel jbln, jpm1_jbl,jpm2_jbl;
	JTextField jtf;
    JPasswordField jpf;
   
    
    JButton jps_jb1,jps_jb2;
	
	public ClientLogin()
	{
		//north
		jbln=new JLabel(new ImageIcon("image_material/DuLogo.jpg"));

		//center
        jpm1=new JPanel();
        jpm1_jbl=new JLabel("Username");
        jpm1_jbl.setFont(new Font("Serif",Font.PLAIN,30));
        jpm1_jbl.setForeground(Color.blue);
        jtf=new JTextField(15);
        jtf.setFont(new Font("Serif",Font.BOLD,20));
        jpm1.add(jpm1_jbl);
        jpm1.add(jtf);

        jpm2=new JPanel();
        jpm2_jbl=new JLabel("Password ");
        jpm2_jbl.setFont(new Font("Serif",Font.PLAIN,30));
        jpm2_jbl.setForeground(Color.blue);
        jpf=new JPasswordField(15);
        jpf.setFont(new Font("Serif",Font.BOLD,20));
        jpm2.add(jpm2_jbl);
        jpm2.add(jpf);
		//south
		jps=new JPanel();
		jps_jb1=new JButton("Register");
		jps_jb1.setFont(new Font("Snap ITC", Font.PLAIN, 24));
		jps_jb1.addActionListener(this);
		jps_jb2=new JButton("  Login  ");
		jps_jb2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {
					MainInterface frame = new MainInterface();
					frame.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}				
			}
		});
		jps_jb2.setFont(new Font("Snap ITC", Font.PLAIN, 24));
		jps.add(jps_jb1);
		jps.add(jps_jb2);
		
		
		this.setLayout(new GridLayout(4,1));
		this.add(jbln,"North");
		this.add(jpm1);
		this.add(jpm2);
		this.add(jps,"South");
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

	public static void main(String[] args) {
		ClientLogin clientLogin=new ClientLogin();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource()==jps_jb1)
		{
			this.setVisible(false);
			new ClientRegister();
			
		}
	}

}
