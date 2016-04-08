package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
public class FileFolder implements ActionListener{
    
   private JFrame mainFrame;
   private JLabel headerLabel;
   private JLabel statusLabel;
   private JPanel controlPanel;

   
   private JButton cancel_btn;
   private JButton send_btn;
   
   private String usr;
   public FileFolder(String usr){
	   this.usr = usr;
	   prepareGUI();
        
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
            }
            else{
               statusLabel.setText("Open command cancelled by user." );           
            }      
         }
      });
      controlPanel.add(showFileDialogButton);
      controlPanel.add(cancel_btn);
      controlPanel.add(send_btn);
      
      mainFrame.setVisible(true);  
   }
   
   
   
   
   @Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == cancel_btn)
		{
			mainFrame.dispose();
		}
		
	}
}