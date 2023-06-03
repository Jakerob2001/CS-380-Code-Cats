import javax.swing.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

public class UserDashboard extends JFrame {

	private JPanel contentPane;
	private static UserDashboard userDash;
	private User currUser;

	private Connection con;

	/**
	 * Create the frame.
	 */
	public UserDashboard(Connection connection, User currentUser) {

		currUser = currentUser;
		con = connection;

		setTitle("User Dashboard");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 533, 313);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton_2 = new JButton("Schedule Repair");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_2.setBounds(10, 67, 175, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_1 = new JButton("Order Parts/Prebuilts");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(10, 112, 175, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("View Pending Orders");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(contentPane, "This\nIS\nA\nTest of how many lines a Option Pane can be");
			}
		});
		btnNewButton.setBounds(10, 156, 175, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Welcome: " + currUser);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(197, 11, 146, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnBack = new JButton("<- Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				goBack();
				
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnBack.setBounds(10, 244, 89, 23);
		contentPane.add(btnBack);
		
		JList list = new JList();
		list.setFont(new Font("Tahoma", Font.PLAIN, 15));
		list.setBounds(268, 58, 243, 209);
		
		JScrollPane scroll = new JScrollPane(list);
		scroll.setBounds(268, 58, 243, 209);
		contentPane.add(scroll);

		
		String[] values = {"Hello", "There", "How", "Are", "You", "Suffering", "Suffering", "Suffering", "Suffering", "Suffering",
				 "Suffering", "Suffering", "Suffering"};
		
		list.setListData(values);
		
	}

	public void goBack () {
		
		SignInPage signIn = new SignInPage();
		signIn.startUpSignIn();

		dispose();
	
	}
}
