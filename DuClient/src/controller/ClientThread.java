package controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.*;
import common.*;
import view.*;
import javax.swing.*;
import java.util.*;
public class ClientThread extends Thread {
	
	private Socket s;
	
	private JMenuItem add_to_group;
	private JMenuItem delete_friend;
	
	public Socket getS() {
		return s;
	}

	public void setS(Socket s) {
		this.s = s;
	}

	public ClientThread(Socket s){
		this.s = s;
	}
	
	public void run(){
		
		ObjectInputStream ois;
		try {
			while(true){
				ois = new ObjectInputStream(s.getInputStream());
				Message m=(Message)ois.readObject();
				MainInterface main = ManageChat.getView("1");
				if (m.getMesType().equals(MessageType.onLine_Friend)) {
					//update friend list
					//System.out.println("new user is:" + m.getCon());
					//MainInterface main = ManageChat.getView("1");
					if (main != null) {
						if(!RelationManage.getOnlineFriend().contains(m.getCon()))
						{
							RelationManage.getOnlineFriend().add(m.getCon());
						}
						main.updateOnlineFriendList(m.getCon());
					}
				} else if (m.getMesType().equals(MessageType.offLine_Friend)) {
					//MainInterface main = ManageChat.getView("1");
					if (main != null) {
						if(RelationManage.getOnlineFriend().contains(m.getCon()))
						{
							RelationManage.getOnlineFriend().remove(m.getCon());
						}
						main.updateOfflineFriendList(m.getCon());
					}
				}
				else if (m.getMesType().equals(MessageType.createFail))
				{
					JOptionPane.showMessageDialog(main.getContentPane(), "Group Name Exist");
				}
				else if(m.getMesType().equals(MessageType.createSuccess))
				{
					JOptionPane.showMessageDialog(main.getContentPane(), "Group Create!");
				}
				else if(m.getMesType().equals(MessageType.returnRelation))
				{
					ArrayList<String> friendList = m.getFriendList();

					RelationManage.clearRelation();
					RelationManage.setRelation(friendList);
					main.getContentPane().remove(main.getFriendlist());
					
					
					int total_relation = friendList.size();
					JPanel friendPanel = new JPanel();		
					friendPanel.setLayout(new GridLayout(total_relation, 1, 4, 4));
					JScrollPane friend_scro = new JScrollPane(friendPanel);
					
					ArrayList<String>OnlineFriend = RelationManage.getOnlineFriend();
					System.out.println("online friend");
					for(String s:OnlineFriend)
					{
						System.out.println(s);
					}
					int cnt_onlineFriend = OnlineFriend.size();
					
					
					HashMap<String,JLabel> friend_map = RelationManage.getFriendList();
					friend_map.clear();
					for(int i = 0 ; i < total_relation; i++)
					{
						String friend_i = friendList.get(i);
						RelationManage.getFriendList().put(friend_i, new JLabel(friend_i));

						
						((Component) friend_map.get(friend_i)).addMouseListener(new MouseAdapter(){
							@Override
							public void mouseClicked(MouseEvent e) {
								if(e.getClickCount() == 2){
									String friendID = ((JLabel)e.getSource()).getText();
									System.out.println("wish to talk to friend " + friendID);
									main.newChatBox(friendID);
								}
								if(e.getButton() == MouseEvent.BUTTON3)
								{
									JPopupMenu textMenu = new JPopupMenu();
									add_to_group = new JMenuItem("Add to Group");
									delete_friend = new JMenuItem("Delete Friend");
									textMenu.add(add_to_group);
									textMenu.add(delete_friend);
									textMenu.show(e.getComponent(), e.getX(), e.getY());
									
									
								}
							}

						});
						friendPanel.add((Component) friend_map.get(friend_i));
					}
					
					for(int i = 0; i < cnt_onlineFriend; i++) {
						((Component) friend_map.get(OnlineFriend.get(i))).setForeground(Color.GREEN);
					}
					
					friend_scro.setBounds(6, 166, 282, 345);
					
					main.setRelation(friendList);
					main.getContentPane().add(friend_scro);
					main.setFriend(friend_map);
					main.setFriendPanel(friendPanel);
					main.setFriendlist(friend_scro);
					
					main.getContentPane().revalidate();
					main.getContentPane().repaint();
					
					

				}
				
				
				else{
					System.out.println("Sender: " + m.getSender()+"to");
					System.out.println("Getter: " + m.getGetter());
					System.out.println("Message:"+m.getCon()+"\n");
					
					//MainInterface main= ManageChat.getView("1");
					
					System.out.println(main.getTarget());
					if(main.getTarget().equals(m.getSender()))
					{
						
						main.showMessage(m);
					}
					else{
						String s=ManageChat.getCon(m.getSender());
						String info=m.getSender()+" to "+m.getGetter()+" :"+m.getCon()+"\r\n";
						if(s == null)
							s = info;
						else
							s = s + info;
						ManageChat.addCon(m.getSender(), s);
						HashMap map = main.getFriend();
						((Component) map.get(m.getSender())).setForeground(Color.RED);
						((Component) map.get(m.getSender())).addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) 
							{
								if(e.getClickCount() == 2 && RelationManage.getOnlineFriend().contains(m.getSender())){
									((Component) map.get(m.getSender())).setForeground(Color.GREEN);
								}
								

							}
							
						});
						
					}
				}
			}
				
		}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
			
		
	}
	
}
	
	


