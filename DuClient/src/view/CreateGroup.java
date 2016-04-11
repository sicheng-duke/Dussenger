package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;

import java.awt.event.*;

import javax.swing.border.EmptyBorder;

import common.Message;
import common.MessageType;
import controller.ManageChat;
import controller.ManageThread;
import controller.RelationManage;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;


import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.awt.event.ActionEvent;


import java.util.*;
public class CreateGroup extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField groupName;
	private JScrollPane scrollPane;
	private JPanel friendPanel;
	private JButton cancelBtn;
	private JButton submitBtn;
	
	private ArrayList<String> chosenFriend;
	private String nameStr;
	private String owner;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public CreateGroup(String usr) {
		owner = usr;
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 300);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel title = new JLabel("Please set name and member");
		title.setBounds(63, 6, 189, 26);
		contentPane.add(title);
		
		JLabel lblNewLabel = new JLabel("Group Name");
		lblNewLabel.setBounds(41, 33, 83, 30);
		contentPane.add(lblNewLabel);
		
		groupName = new JTextField();
		groupName.setBounds(136, 35, 134, 26);
		contentPane.add(groupName);
		groupName.setColumns(10);
		
		
		ArrayList<String> relation = RelationManage.getRelation();
		int total_relation = relation.size() < 6 ? 6 :relation.size();
		friendPanel = new JPanel();
		friendPanel.setLayout(new GridLayout(total_relation, 1, 4, 4));
		scrollPane = new JScrollPane(friendPanel);

		for(int i = 0; i < relation.size(); i++)
		{
			String friend_i = relation.get(i);
			friendPanel.add(new JCheckBox(friend_i));
		}
		
		
		
		cancelBtn = new JButton("Cancel");
		scrollPane.setBounds(41, 70, 229, 160);
		contentPane.add(scrollPane);
		
		cancelBtn.setBounds(41, 243, 117, 29);
		cancelBtn.addActionListener(this);
		contentPane.add(cancelBtn);
		
		submitBtn = new JButton("Submit");
		submitBtn.setBounds(153, 243, 117, 29);
		contentPane.add(submitBtn);
		submitBtn.addActionListener(this);
		
		
		this.setVisible(true);

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cancelBtn)
		{
			dispose();
		}
		if(e.getSource() == submitBtn)
		{
			
			getAllJCheckBoxValue(this.friendPanel);
			if(chosenFriend.size() == 0 || groupName.getText().trim().length() == 0)
			{
				JOptionPane.showMessageDialog(contentPane, "Input group name and choose at least one friend");
			}
			else
			{
				sendGroupInfo();
			}
			
		}
		
	}
	public void sendGroupInfo()
	{
		nameStr = groupName.getText();
		for(String s:chosenFriend)
		{
			System.out.println(s);
		}
		System.out.println(nameStr);

		Message m = new Message();
		m.setSender(owner);
		m.setCon(nameStr);
		m.setMesType(MessageType.createGroup);
		chosenFriend.add(owner);
		m.setFriendList(chosenFriend);
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream
			(ManageThread.getClientConServerThread(owner).getS().getOutputStream());
			oos.writeObject(m);
			dispose();

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

    public  void getAllJCheckBoxValue(Container ct){
        if(chosenFriend==null){
            chosenFriend=new ArrayList<String>();
        }
        int count=ct.getComponentCount();
        for(int i=0;i<count;i++){
            Component c=ct.getComponent(i);
            if(c instanceof JCheckBox ){
            	if(((JCheckBox) c).isSelected())
            		chosenFriend.add(((JCheckBox) c).getText());
                
            }
        }
        

       
    }
}
