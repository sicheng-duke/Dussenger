package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.RelationManage;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JButton;

import controller.*;

public class GroupMember extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton btnOk ;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public GroupMember(String s,String name) {
		setResizable(false);
		setBounds(100, 100, 300, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Title = new JLabel("Group Name:");
		Title.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		Title.setBounds(32, 6, 100, 30);
		contentPane.add(Title);
		
		JLabel groupName = new JLabel(name);
		groupName.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		groupName.setBounds(158, 6, 100, 30);
		contentPane.add(groupName);
		
		String [] member = s.split("%:%");

		JPanel panel = new JPanel();
		int total_relation = member.length < 9? 9 : member.length;
		panel.setLayout(new GridLayout(total_relation, 1, 4, 4));
		for(int i = 0 ; i < member.length ; i++)
		{
			panel.add(new JLabel(member[i]));
		}

		JScrollPane scrollPane = new JScrollPane(panel);
		
		scrollPane.setBounds(32, 48, 244, 189);
		contentPane.add(scrollPane);
		
		
		
		btnOk = new JButton("OK");
		btnOk.setBounds(101, 243, 117, 29);
		btnOk.addActionListener(this);
		contentPane.add(btnOk);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnOk)
		{
			dispose();
		}
	
	}
}
