import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class EmployeeDashboard extends JFrame {

	private JPanel contentPane;
	private static EmployeeDashboard employeeLP;
	private User currUser;

	private Connection con;

	/**
	 * Create the frame.
	 */
	public EmployeeDashboard(Connection connection, User currentUser) {

		con = connection;
		currUser = currentUser;
		System.out.println(currUser);

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 327);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Welcome: User");
		lblNewLabel.setBounds(150, 11, 148, 19);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblNewLabel);

		JButton btnViewInventory = new JButton("View Inventory");
		btnViewInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new Inventory(con, currUser);

			}
		});
		btnViewInventory.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnViewInventory.setBounds(10, 40, 163, 23);
		contentPane.add(btnViewInventory);

		JButton btnNewButton_1 = new JButton("View Repair Requests");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton_1.setBounds(10, 76, 163, 23);
		contentPane.add(btnNewButton_1);

		JButton btnOrderParts = new JButton("Order Parts");
		btnOrderParts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new PartOrderer(con, currUser);

				dispose();

			}
		});
		btnOrderParts.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnOrderParts.setBounds(10, 112, 163, 23);
		contentPane.add(btnOrderParts);

		JButton btnNewButton_3 = new JButton("Create Pre-Builts");
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton_3.setBounds(10, 148, 163, 23);
		contentPane.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("Approve Orders");
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton_4.setBounds(10, 184, 163, 23);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new FulfillOrders(con, currUser);

			}
		});
		contentPane.add(btnNewButton_4);

		JButton btnNewButton_5 = new JButton("TestFeature");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "IT WORKS!!", "YURIKA", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnNewButton_5.setBounds(260, 126, 91, 23);
		contentPane.add(btnNewButton_5);

		JButton btnBack = new JButton("<- Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				goBack();

			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBack.setBounds(10, 254, 102, 23);
		contentPane.add(btnBack);
	}

	public void goBack () {

		SignInPage signIn = new SignInPage();
		signIn.startUpSignIn();

		dispose();
	}
}
