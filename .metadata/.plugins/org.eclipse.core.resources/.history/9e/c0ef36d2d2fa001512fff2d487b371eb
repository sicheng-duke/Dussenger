package view;
/*
 * this class is for initialize a new interface when friend requested in the search box isnt found
 */
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class friendNotFound extends JFrame {

	private JPanel contentPane;
	private JTextField tf_searchnewfriend;

	public friendNotFound() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel reminderPanel = new JPanel();
		contentPane.add(reminderPanel);
		
		JTextArea notFound = new JTextArea();
		notFound.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		notFound.setText("the person you are looking for is not your friend");
		reminderPanel.add(notFound);
		
		JPanel addfriendPanel = new JPanel();
		contentPane.add(addfriendPanel);
		addfriendPanel.setLayout(null);
		
		tf_searchnewfriend = new JTextField();
		tf_searchnewfriend.setBounds(31, 6, 246, 57);
		addfriendPanel.add(tf_searchnewfriend);
		tf_searchnewfriend.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(304, 6, 117, 57);
		addfriendPanel.add(btnAdd);
	}
}
