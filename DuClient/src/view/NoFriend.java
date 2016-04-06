package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import common.Message;
import common.MessageType;
import controller.ManageThread;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;

public class NoFriend extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textField;
	private JButton submit_btn;
	private String usr;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public NoFriend(String usr) {
		this.usr = usr;
		
		setBounds(100, 100, 300, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTypeInThe = new JLabel("Type in the friend you want to add");
		lblTypeInThe.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblTypeInThe.setBounds(18, 22, 256, 16);
		contentPane.add(lblTypeInThe);
		
		textField = new JTextField();
		textField.setBounds(22, 68, 130, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		submit_btn = new JButton("Submit");
		submit_btn.setBounds(157, 68, 117, 29);
		submit_btn.addActionListener(this);
		contentPane.add(submit_btn);
		this.setVisible(true);
		this.setResizable(false);
	
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == submit_btn)
		{
			if(textField.getText().equals(this.usr))
			{
				JOptionPane.showMessageDialog(contentPane, "You can't add yourself");
			}
			else
			{
				Message m = new Message();
				m.setSender(usr);
				m.setCon(textField.getText());
				m.setMesType(MessageType.add_friend);

				ObjectOutputStream oos;
				try {
					oos = new ObjectOutputStream
					(ManageThread.getClientConServerThread(usr).getS().getOutputStream());
					oos.writeObject(m);
					dispose();

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}

		
	}

}
