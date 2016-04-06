package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import common.Message;
import common.MessageType;
import controller.ManageRequest;
import controller.ManageThread;

import javax.swing.JScrollPane;

public class MessageTable extends JFrame {

	private JPanel contentPane;
	private String usr;
	private JPanel MessagePanel;
	private ArrayList<Message> message_list;
	/**
	 * Launch the application.
	 */
	private int times;


	/**
	 * Create the frame.
	 */
	public MessageTable(String s) {
		times = 0;
		this.usr = s;
		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		MessagePanel = new JPanel();
		MessagePanel.setBounds(2, 2, 492, 262);
		
		
		message_list = ManageRequest.getRequest_list();
		int total_relation = message_list.size() < 10 ? 10 : message_list.size();
		setTitle("You have " + message_list.size() +" unread message ");
		MessagePanel.setLayout(new GridLayout(total_relation, 1, 30, 10));
		
		contentPane.add(MessagePanel);
		
		for(int i = 0 ; i < message_list.size(); i++)
		{
			
			addPanel(i,message_list.get(i));
			
		}
		
		JScrollPane scrollPane = new JScrollPane(MessagePanel);
		
		scrollPane.setBounds(6, 6, 492, 266);
		contentPane.add(scrollPane);
		this.setVisible(true);
		this.setResizable(false);
		
		
	}
	
	public void addPanel(int index_num,Message m)
	{
		int index = index_num;
		JPanel medium_Panel = new JPanel();
		medium_Panel.setLayout(new GridLayout(1, 2, 30, 10));
		medium_Panel.add(new JLabel(m.getCon()));
		JButton reject = new JButton("Reject");
		JPanel small_Panel = new JPanel();
		small_Panel.setLayout(new GridLayout(1, 2, 30, 10));
		small_Panel.add(reject);
		reject.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
		    // display/center the jdialog when the button is pressed
			  MessagePanel.remove(index-times);
			  ManageRequest.removeRequest(index-times);
			  setTitle("You have " + message_list.size() +" unread message ");
			  times++;
			  contentPane.revalidate();
			  contentPane.repaint();
			  if(m.getMesType().equals(MessageType.add_request))
			  {
				  replyMessage(m,MessageType.denny_add_request);
			  }

		  }
		});
		//Message Type, Message Receiver, Message index 
		JButton accept = new JButton("Accept");
		small_Panel.add(accept);
		
		medium_Panel.add(small_Panel);
		accept.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
		    // display/center the jdialog when the button is pressed
			  MessagePanel.remove(index-times);
			  ManageRequest.removeRequest(index-times);
			  setTitle("You have " + message_list.size() +" unread message ");
			  times++;
			  contentPane.revalidate();
			  contentPane.repaint();
			  if(m.getMesType().equals(MessageType.add_request))
			  {
				  replyMessage(m,MessageType.accept_add_request);
			  }
			  
			  
			  
			  
		  }
		});
		
		
		MessagePanel.add(medium_Panel);
	}
	
	public void replyMessage(Message m,String MesType)
	{
		  Message reply = new Message();
		  reply.setSender(usr);
		  reply.setGetter(m.getSender());
		  
		  reply.setMesType(MesType);
		  
		  ObjectOutputStream oos;
		  try {
			  oos = new ObjectOutputStream
					  (ManageThread.getClientConServerThread(usr).getS().getOutputStream());
			  oos.writeObject(reply);


		  } catch (Exception e1) {
			 // TODO Auto-generated catch block
			  e1.printStackTrace();
		  }
	}


}
