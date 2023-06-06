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
import javax.swing.SwingConstants;

public class EmployeeDashboard extends JFrame {

	private JPanel contentPane;

	/**
	 * Field to store current users instance
	 */
	private User currUser;

	/**
	 * store current instance of the databsse
	 */
	private Connection con;

	/**
	 * Create the frame.
	 */
	public EmployeeDashboard(Connection connection, User currentUser) {

		con = connection;
		currUser = currentUser;
		System.out.println(currUser);

		setTitle("Employee Dashboard");
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 321, 297);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		//Button with call to create a viewable part inventory
		JButton btnViewInventory = new JButton("View Inventory");
		btnViewInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new Inventory(con, currUser);

			}
		});
		btnViewInventory.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnViewInventory.setBounds(71, 41, 163, 23);
		contentPane.add(btnViewInventory);

		//Button with call to partOrderer to order new parts for shop/database
		JButton btnOrderParts = new JButton("Order Parts");
		btnOrderParts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new PartOrderer(con, currUser);

				dispose();

			}
		});
		btnOrderParts.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnOrderParts.setBounds(71, 75, 163, 23);
		contentPane.add(btnOrderParts);

		//Button with call to PrebuiltAssembler to create new pre builts from existing
		//part inventory
		JButton btnPreBuilts = new JButton("Create Pre-Builts");
		btnPreBuilts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new PrebuiltAssembler(con, currUser);

				dispose();

			}
		});
		btnPreBuilts.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnPreBuilts.setBounds(71, 109, 163, 23);
		contentPane.add(btnPreBuilts);

		//Button with call to frame that allows you to view orders and approve
		//or complete them (Repair orders)
		JButton btnApproveOrder = new JButton("Approve Order");
		btnApproveOrder.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnApproveOrder.setBounds(71, 143, 163, 23);
		btnApproveOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new FulfillOrders(con, currUser);

			}
		});
		contentPane.add(btnApproveOrder);

		//Button with call to return to previous frame
		JButton btnBack = new JButton("<- Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				goBack();

			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBack.setBounds(100, 212, 102, 23);
		contentPane.add(btnBack);
		
		//Button with call to Create parts/add new parts to the database for
		//available shipment
		JButton btnCreateParts = new JButton("Create New Part");
		btnCreateParts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new PartCreator(con, currUser);

				dispose();

			}
		});
		btnCreateParts.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCreateParts.setBounds(71, 177, 163, 23);
		contentPane.add(btnCreateParts);
		
		JLabel lblWelcome = new JLabel("Welcome: " + currUser.getFirstName());
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblWelcome.setBounds(60, 11, 181, 19);
		contentPane.add(lblWelcome);
	}

	/**
	 * Function to return back to previous frame (SignInPage)
	 */
	public void goBack () {

		SignInPage signIn = new SignInPage();
		//Call to eventQueue startUp method
		signIn.startUpSignIn();

		dispose();
	}
}
