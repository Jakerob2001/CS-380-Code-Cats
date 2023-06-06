import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.border.EmptyBorder;

public class UserDashboard extends JFrame {

	private JPanel contentPane;

	/**
	 * A list component for displaying strings.
	 */
	private JList<String> list;

	/**
	 * The currently logged-in user.
	 */
	private User currUser;

	/**
	 * An ArrayList of displayPart objects for displaying parts.
	 */
	private ArrayList<displayPart> displayParts;

	/**
	 * An ArrayList of displayPrebuilt objects for displaying prebuilt items.
	 */
	private ArrayList<displayPrebuilt> displayPrebuilts;

	/**
	 * An ArrayList of integers representing the quantity of displayPart objects.
	 */
	private ArrayList<Integer> displayPartQty;

	/**
	 * An ArrayList of integers representing the quantity of displayPrebuilt objects.
	 */
	private ArrayList<Integer> displayPreBuiltQty;

	/**
	 * An ArrayList of Order objects representing a list of orders.
	 */
	ArrayList<Order> orderList;

	/**
	 * The database connection object.
	 */
	Connection con;
	/**
	 * Create the frame.
	 */
	public UserDashboard(Connection connection, User currentUser) {

		currUser = currentUser;
		con = connection;

		setTitle("User Dashboard");
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 664, 313);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnRepair = new JButton("Schedule Repair");
		btnRepair.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRepair.setBounds(45, 98, 175, 23);
		btnRepair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new RepairOrderCreator(con, currUser);

				dispose();

			}
		});
		contentPane.add(btnRepair);

		JButton btnOrderParts = new JButton("Order Parts/Prebuilts");
		btnOrderParts.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnOrderParts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new Orderer(con, currUser);

				dispose();

			}
		});
		btnOrderParts.setBounds(45, 162, 175, 23);
		contentPane.add(btnOrderParts);

		JLabel lblNewLabel = new JLabel("Welcome: " + currUser.getFirstName());
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(223, 11, 211, 23);
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

		// Create a JList and set the data using a DefaultListModel
		DefaultListModel<String> listModel = new DefaultListModel<>();
		for (Order item : populateList()) {
			if (item.getOrderType().equals("Retail Order")) {
				listModel.addElement(String.valueOf(item.toStringRetail()));
			}
			else {
				listModel.addElement(String.valueOf(item.toStringRepair()));
			}
		}
		list = new JList<>(listModel);
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if (me.getClickCount() == 2) {
					displayDetails(list.getSelectedIndex());
				}
			}
		});
		list.setFont(new Font("Tahoma", Font.PLAIN, 15));
		list.setBounds(268, 58, 243, 209);

		JScrollPane scroll = new JScrollPane(list);
		scroll.setBounds(252, 54, 360, 209);
		contentPane.add(scroll);

	}

	/**
	 * Method to return to the previous frame
	 */
	public void goBack () {

		SignInPage signIn = new SignInPage();
		signIn.startUpSignIn();

		dispose();

	}

	/**
	 * Populates and returns a list of orders for the current user.
	 *
	 * @return an ArrayList of Order objects representing the user's orders
	 */
	public ArrayList<Order> populateList() {

		String queryOrders = "SELECT * FROM ORDERS WHERE cust_id = '" + currUser.getUserID() + "';";

		String queryRepairs = "SELECT * FROM REPAIR_ORDERS WHERE cust_id = '" + currUser.getUserID() + "';";

		orderList = new ArrayList<>();

		try {
			Statement statement = con.createStatement();

			ResultSet result = statement.executeQuery(queryOrders);

			while(result.next()) {

				orderList.add(new Order("Retail Order", String.valueOf(result.getInt(1)), currUser.getUserID(), currUser.getUsername()
						, result.getString(3), result.getString(7)));

			}

			result = statement.executeQuery(queryRepairs);

			while(result.next()) {

				orderList.add(new Order("Repair Order", String.valueOf(result.getInt(2)), currUser.getUserID(), currUser.getUsername()
						, " ", result.getString(3)));

			}


		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return orderList;

	}

	/**
	 * Displays the details of an order at the specified index.
	 *
	 * @param index the index of the order to display
	 */
	public void displayDetails(int index) {

		Order currentOrder = orderList.get(index);

		StringBuilder display = new StringBuilder();

		displayParts = new ArrayList<>();
		displayPrebuilts = new ArrayList<>();
		displayPartQty = new ArrayList<>();
		displayPreBuiltQty = new ArrayList<>();

		if (currentOrder.getOrderType().equals("Retail Order")) {

			String queryRetail = "SELECT * FROM ORDER_LINES WHERE order_id = '" + currentOrder.getOrderID() + "';";

			try {
				Statement statement = con.createStatement();

				ResultSet result = statement.executeQuery(queryRetail);

				while(result.next()) {

					if (result.getString(3) != null) {

						addPart(result.getString(3));

					} else {

						getPrebuilt(result.getString(5));

					}

				}


			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		} else {

			String queryRepair = "SELECT * FROM REPAIR_ORDERS WHERE rep_order_id = '" + currentOrder.getOrderID() + "';";

			try {
				Statement statement = con.createStatement();

				ResultSet result = statement.executeQuery(queryRepair);

				while (result.next()) {


					display.append("Repair Time: ").append(result.getString(3)).append("\n\n").append("Problem:\n").append(result.getString(4));

				}


			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}

		for (int i = 0; i < displayParts.size(); i++) {
			display.append(displayParts.get(i)).append(" | Qty: ").append(displayPartQty.get(i)).append("\n");
		}

		for (int j = 0; j < displayPrebuilts.size(); j++) {

			display.append(displayPrebuilts.get(j)).append("\n");

		}

		JOptionPane.showMessageDialog(null, display);

	}

	public void addPart(String partID){

			boolean exists = false;
			displayPart thePart = null;

			String query= "SELECT * FROM Part WHERE part_id = '" + partID + "';";

			try {
				Statement statement = con.createStatement();

				ResultSet result = statement.executeQuery(query);

				while(result.next()) {

					for (displayPart part: displayParts) {

						if (part.getName().equals(result.getString(2))) {

							exists = true;
							thePart = part;

						}
					}

					if (exists) {
						int indexOfPart = displayParts.indexOf(thePart);
						displayPartQty.set(indexOfPart, displayPartQty.get(indexOfPart) + 1);

					} else {

						displayParts.add(new displayPart(result.getInt(1), result.getString(2),
								result.getString(3), result.getString(4), result.getDouble(5)));

						displayPartQty.add(1);

					}
				}

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

	}

	public void getPrebuilt(String name){

		boolean exists = false;
		displayPrebuilt theBuild = null;

		displayPreBuiltQty = new ArrayList<>();

		String query = "SELECT * FROM Prebuilt WHERE comp_name = '" + name + "';";

		try {

			Statement statement = con.createStatement();

			ResultSet result = statement.executeQuery(query);

			while(result.next()) {

				displayPrebuilts.add(new displayPrebuilt(result.getString(1), getPart(result.getString(2)),
											getPart(result.getString(3)), getPart(result.getString(4)), getPart(result.getString(5)),
											getPart(result.getString(6)), getPart(result.getString(7)), getPart(result.getString(8)),
											result.getDouble(9)));

			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

	}

	public displayPart getPart(String partID) {
		displayPart thePart = null;

		String query = "SELECT * FROM Part WHERE part_id = '" + partID + "';";

		try {
			Statement statement = con.createStatement();

			ResultSet result = statement.executeQuery(query);

			while (result.next()) {

				thePart = new displayPart(result.getInt(1), result.getString(2), result.getString(3),
						result.getString(4), result.getDouble(5));

			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return thePart;

	}

}

