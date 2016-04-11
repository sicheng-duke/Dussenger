package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import common.Message;
import common.MessageType;
import controller.IPManage;
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
			  else
			  {
				  replyMessage(m,MessageType.deny_file);
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
			  
			  
			  setTitle("You have " + message_list.size() +" unread message ");

			  if(m.getMesType().equals(MessageType.add_request))
			  {
				  MessagePanel.remove(index-times);
				  ManageRequest.removeRequest(index-times);
				  times++;
				  contentPane.revalidate();
				  contentPane.repaint();
				  replyMessage(m,MessageType.accept_add_request);
			  }
			  else
			  {
				  String s = folderChooser();
				  if(s != null)
				  {
					  MessagePanel.remove(index-times);
					  ManageRequest.removeRequest(index-times);
					  times++;
					  contentPane.revalidate();
					  contentPane.repaint();
					  replyMessage(m,MessageType.receive_file);
					  				  
					  try {
						Thread.sleep(100);
						saveFile(s);	
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (Throwable e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				  }

			  }
			  
			  
			  
			  
		  }
		});
		
		
		MessagePanel.add(medium_Panel);
	}
	public void saveFile(String path) throws Throwable
	{
		Socket socket = new Socket(IPManage.getIP(),4567);
		DataInputStream dis = null;
		dis = new DataInputStream(new BufferedInputStream(socket   
                .getInputStream()));  
        int bufferSize = 10240;    
        byte[] buf = new byte[bufferSize];   
        int passedlen = 0;   
        long len = 0;  
        int  i = 0;
        String savePath = path + "/";
        savePath += dis.readUTF().replaceFirst(this.usr+"-", "");   
        DataOutputStream fileOut = new DataOutputStream(   
            new BufferedOutputStream(new BufferedOutputStream(   
                new FileOutputStream(savePath))));   
        len = dis.readLong();  
        while (true) 
        {   
            int read = 0;   
            if (dis!= null) 
            {   
              read = dis.read(buf);   
            }   
            passedlen += read;   
            if (read == -1) 
            {   
              break;   
            }   
            //System.out.println("File Receive from" + m.getSender() + (passedlen * 100 / len) + "%");   
            fileOut.write(buf, 0, read);   
            i++;
          } 
        fileOut.close(); 

        socket.close();
	}
	
	public String folderChooser()
	{

        
		JFileChooser chooser = new JFileChooser(); 
	    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    chooser.setAcceptAllFileFilterUsed(false);
	    if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
	        return chooser.getSelectedFile() + "";

	    }
	    return null;
	}
	
	public void replyMessage(Message m,String MesType)
	{
		  Message reply = new Message();
		  reply.setSender(usr);
		  reply.setGetter(m.getSender());
		  
		  reply.setMesType(MesType);
		  reply.setPath(m.getPath());
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
