package view;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.*;

import common.Message;
import common.MessageType;
import controller.IPManage;
import controller.ManageThread;
 
public class FileFolder implements ActionListener{
    
   private JFrame mainFrame;
   private JLabel headerLabel;
   private JLabel statusLabel;
   private JPanel controlPanel;

   
   private JButton cancel_btn;
   private JButton send_btn;
   private String path;
   private String usr;
   private String target;
   public FileFolder(String usr,String target){
	   this.usr = usr;
	   this.target = target;
	   prepareGUI();
       this.path = null;
      this.fileFolder();
   }

   private void prepareGUI(){
	  
      mainFrame = new JFrame("File Transfer");
      mainFrame.setLocation(100, 100);
      mainFrame.setSize(400,200);
      mainFrame.setLayout(new GridLayout(3, 1));
  
      headerLabel = new JLabel("", JLabel.CENTER);        
      statusLabel = new JLabel("",JLabel.CENTER);    

      statusLabel.setSize(350,100);

      controlPanel = new JPanel();

      //controlPanel.setLayout(new GridLayout(1, 3));
      
      cancel_btn = new JButton("Cancel");
      cancel_btn.setBounds(15,150,30,30);
      cancel_btn.addActionListener(this);
      send_btn = new JButton("Send");
      send_btn.addActionListener(this);

      mainFrame.add(headerLabel);
      mainFrame.add(controlPanel);
      mainFrame.add(statusLabel);
      mainFrame.setVisible(true);  
   }

   private void fileFolder(){
      headerLabel.setText("Choose the File you want"); 
      
      final JFileChooser  fileDialog = new JFileChooser();
      JButton showFileDialogButton = new JButton("Open File");
      showFileDialogButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            int returnVal = fileDialog.showOpenDialog(mainFrame);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
               java.io.File file = fileDialog.getSelectedFile();
               
               statusLabel.setText("File Selected :" 
               + file.getPath());
               path = file.getPath();
            }
            else{
               statusLabel.setText("Open command cancelled by user." ); 
               path = null;
            }      
         }
      });
      controlPanel.add(showFileDialogButton);
      controlPanel.add(cancel_btn);
      controlPanel.add(send_btn);
      
      mainFrame.setVisible(true);  
   }
   
   public void replyMessage(String filePath)
   {
	   Message m = new Message();
	   m.setSender(this.usr);
	   m.setMesType(MessageType.send_file_req);
	   m.setGetter(this.target);
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream
			(ManageThread.getClientConServerThread(this.usr).getS().getOutputStream());
			oos.writeObject(m);
			
			Thread.sleep(100);
			Socket socket = new Socket(IPManage.getIP(),3456);
			File file = new File(filePath);			
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(filePath)));  
            int buffferSize=10240;  
            byte[]bufArray=new byte[buffferSize];  
            dos.writeUTF(file.getName());   
            dos.flush();   
            dos.writeLong((long) file.length());   
            dos.flush();   
            while (true) {   
                int read = 0;   
                if (dis!= null) {   
                  read = dis.read(bufArray);   
                }   
  
                if (read == -1) {   
                  break;   
                }   
                dos.write(bufArray, 0, read);   
              }   
              dos.flush(); 
              if(dos != null) 
  				dos.close();
              if(dis != null)
            	  dis.close();
              if(socket != null)
            	  socket.close();
              
              
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
   }
   

   
   
   @Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == cancel_btn)
		{
			mainFrame.dispose();
		}
		if(e.getSource() == send_btn)
		{
			if(path == null)
			{
				JOptionPane.showMessageDialog(mainFrame, "Choose a file to send");
			}
			else
			{
				replyMessage(path);
				mainFrame.dispose();
			}
		}
		
	}
}