import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;

import java.awt.Font;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.border.MatteBorder;

/**
 * Class to approve or deny clients repair and retail orders
 * @author Jake Robinson
 */
public class FulfillOrders extends JFrame {


	private JPanel contentPane;
	private JTextArea textArea;

	/**
	 * The connection object for the database.
	 */
	private Connection con;

	/**
	 * The currently logged-in user.
	 */
	private User currUser;

	/**
	 * The list component for displaying string values.
	 */
	private JList<String> list;

	/**
	 * The list of display parts.
	 */
	private ArrayList<displayPart> displayParts;

	/**
	 * The list of display prebuilts.
	 */
	private ArrayList<displayPrebuilt> displayPrebuilts;

	/**
	 * The list of quantities for display parts.
	 */
	private ArrayList<Integer> displayPartQty;

	/**
	 * The list of quantities for display prebuilts.
	 */
	private ArrayList<Integer> displayPreBuiltQty;

	/**
	 * The list of orders.
	 */
	private ArrayList<Order> orderList;

	/**
	 * Create the frame.
	 */
	public FulfillOrders(Connection connection, User currentUSer) {

		con = connection;
		currUser = currentUSer;

		setTitle("Fulfill Order");
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 768, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Approve/Deny Order");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitle.setBounds(320, 11, 177, 29);
		contentPane.add(lblTitle);

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
		//Action listener to check for users mouse input
		list = new JList<>(listModel);
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if (me.getClickCount() == 1) {
					textArea.setText("");
					displayDetails(list.getSelectedIndex());
				}
			}
		});
		list.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));

		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(36, 54, 338, 273);
		contentPane.add(scrollPane);

		textArea = new JTextArea();
		textArea.setBounds(419, 54, 295, 273);
		textArea.setEditable(false);

		JScrollPane scrollText = new JScrollPane(textArea);
		scrollText.setBounds(419, 54, 295, 273);
		contentPane.add(scrollText);

		//Button with method call to approve an order
		JButton btnApprove = new JButton("Approve");
		btnApprove.setBounds(84, 338, 124, 29);
		btnApprove.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnApprove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					approveOrder(list.getSelectedIndex());
					new FulfillOrders(con, currUser);

					dispose();
				} catch (SQLException ex) {
					throw new RuntimeException(ex);
				}

			}
		});
		contentPane.add(btnApprove);

		//Button with method call to deny an order
		JButton btnDeny = new JButton("Deny");
		btnDeny.setBounds(573, 338, 94, 29);
		btnDeny.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDeny.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					denyOrder(list.getSelectedIndex());
					new FulfillOrders(con, currUser);

					dispose();
				} catch (SQLException ex) {
					throw new RuntimeException(ex);
				}

			}
		});
		contentPane.add(btnDeny);
		
		JButton btnBack = new JButton("<- Back");
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnBack.setBounds(10, 397, 94, 23);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();

			}
		});
		contentPane.add(btnBack);

	}

	/**
	 * Populates and returns a list of orders for the current user.
	 *
	 * @return an ArrayList of Order objects representing the user's orders
	 */
	public ArrayList<Order> populateList() {

		//Create queries to grab all orders and repair orders
		String queryOrders = "SELECT * FROM ORDERS;";

		String queryRepairs = "SELECT * FROM REPAIR_ORDERS;";

		//create a new arraylist to store order objects
		orderList = new ArrayList<>();

		try {
			Statement statement = con.createStatement();

			ResultSet result = statement.executeQuery(queryOrders);

			//Add retail orders to the list
			while(result.next()) {

				orderList.add(new Order("Retail Order", String.valueOf(result.getInt(1)), currUser.getUserID(), currUser.getUsername()
						, result.getString(3), result.getString(7)));

			}

			result = statement.executeQuery(queryRepairs);

			//add repair orders to the list
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

					//Check if the part_id is null, if not add the part to the details list
					if (result.getString(3) != null) {

						//call add part to add the part info to the text
						addPart(result.getString(3));

						//else grab the prebuilt using prebuilt name
					} else {

						//call getPrebuilt to add the prebuilt to the text
						getPrebuilt(result.getString(5));

					}

				}


			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		//If its not a retail select the repair order and display its info
		} else {

			String queryRepair = "SELECT * FROM REPAIR_ORDERS WHERE rep_order_id = '" + currentOrder.getOrderID() + "';";

			try {
				Statement statement = con.createStatement();

				ResultSet result = statement.executeQuery(queryRepair);

				while (result.next()) {

					textArea.append("Repair Time: " + result.getString(3) + "\n\n" + "Problem:\n" + result.getString(4));

				}


			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}

		//Create StringBuilder to populate into text area
		StringBuilder display = new StringBuilder();

		//add all parts to the StringBuilder
		for (int i = 0; i < displayParts.size(); i++) {
			display.append(displayParts.get(i)).append(" | Qty: ").append(displayPartQty.get(i)).append("\n");
		}

		display.append("\n");

		//add prebuilts to the StringBuiler
		for (int j = 0; j < displayPrebuilts.size(); j++) {

			display.append(displayPrebuilts.get(j)).append("\n");

		}

		textArea.append(String.valueOf(display));

	}

	/**
	 * Given a selected order from the list, approve that order and remove from the database
	 * @param index
	 * @throws SQLException
	 */
	public void approveOrder(int index) throws SQLException {

		//get the order at the current list index
		Order currentOrder = orderList.get(index);

		//Check if the order is a Repair or Retail order
		if (currentOrder.getOrderType().equals("Repair Order")) {

			//Execute Query to remove from database and output confirmation
			String queryRepair = "DELETE FROM repair_orders WHERE (rep_order_id = '" + currentOrder.getOrderID() + "');";
			Statement statement = con.createStatement();

			statement.execute(queryRepair);

			JOptionPane.showMessageDialog(null, "Repair Order Completed");

		}
		else {

			Statement statement;

			//Remove data from both order_lines and orders table
			String deleteRetail = "DELETE FROM order_lines WHERE (order_id = '" + currentOrder.getOrderID() + "');";
			statement = con.createStatement();
			statement.execute(deleteRetail);

			deleteRetail = "DELETE FROM orders WHERE (order_id = '" + currentOrder.getOrderID() + "');";
			statement = con.createStatement();
			statement.execute(deleteRetail);

			for(displayPrebuilt removePrebuilt : displayPrebuilts) {

				String deletePrebuilt = "DELETE FROM prebuilt WHERE (comp_name = '" + removePrebuilt.getComp_name() + "');";
				statement = con.createStatement();
				statement.execute(deletePrebuilt);

			}

			int i = 0;
			for(displayPart removePart : displayParts) {

				deductQty(removePart.getId(), displayPartQty.get(i));
				i += 1;

			}

			JOptionPane.showMessageDialog(null, "Order Approved");

		}

	}

	/**
	 * Given a selected order from the list, deny that order and remove from the database
	 * @param index
	 * @throws SQLException
	 */
	public void denyOrder(int index) throws SQLException {

		//get the order at the current list index
		Order currentOrder = orderList.get(index);

		//Check if the order is a Repair or Retail order
		if (currentOrder.getOrderType().equals("Repair Order")) {

			//Execute Query to remove from database and output confirmation
			String queryRepair = "DELETE FROM repair_orders WHERE (rep_order_id = '" + currentOrder.getOrderID() + "');";
			Statement statement = con.createStatement();
			statement.execute(queryRepair);

			JOptionPane.showMessageDialog(null, "Repair Order Denied");

		}
		else {

			//Remove data from both order_lines and orders table
			String queryRetail = "DELETE FROM order_lines WHERE (order_id = '" + currentOrder.getOrderID() + "');";
			Statement statement = con.createStatement();
			statement.execute(queryRetail);

			queryRetail = "DELETE FROM orders WHERE (order_id = '" + currentOrder.getOrderID() + "');";
			statement = con.createStatement();
			statement.execute(queryRetail);

			JOptionPane.showMessageDialog(null, "Order Denied");
		}

	}

		/**
		 * Adds a part to the displayParts list and updates the quantity in the displayPartQty list.
		 *
		 * @param partID         the ID of the part to add
		 */
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

		/**
		 * Retrieves a prebuilt from the database and updates the displayPrebuilts list and displayPreBuiltQty list.
		 *
		 * @param name                the name of the prebuilt to retrieve
		 */
		public void getPrebuilt(String name){

			boolean exists = false;
			displayPrebuilt theBuild = null;

			displayPreBuiltQty = new ArrayList<>();

			//query prebuilt by name
			String query = "SELECT * FROM Prebuilt WHERE comp_name = '" + name + "';";

			try {

				Statement statement = con.createStatement();

				ResultSet result = statement.executeQuery(query);

				while(result.next()) {

					//create prebuilt and add to global list
					displayPrebuilts.add(new displayPrebuilt(result.getString(1), getPart(result.getString(2)),
							getPart(result.getString(3)), getPart(result.getString(4)), getPart(result.getString(5)),
							getPart(result.getString(6)), getPart(result.getString(7)), getPart(result.getString(8)),
							result.getDouble(9)));

				}

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}

		/**
		 * Retrieves a part from the database based on the part ID.
		 *
		 * @param partID the ID of the part to retrieve
		 * @return the displayPart object representing the retrieved part, or null if not found
		 */
		public displayPart getPart(String partID) {

			displayPart thePart = null;

			//query the part
			String query = "SELECT * FROM Part WHERE part_id = '" + partID + "';";

			try {
				Statement statement = con.createStatement();

				ResultSet result = statement.executeQuery(query);

				while (result.next()) {

					//create a part from queried info
					thePart = new displayPart(result.getInt(1), result.getString(2), result.getString(3),
							result.getString(4), result.getDouble(5));

				}

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			return thePart;

		}

	/**
	 * Method to remove quantity from parts
	 * @param partID
	 * @param qtyToDeduct
	 */
		public void deductQty(int partID, int qtyToDeduct) {
			try {
				String update = "UPDATE part_inventory SET qty = qty - " + qtyToDeduct + " WHERE part_id = " + partID;
				Statement statement = con.createStatement();
				statement.execute(update);
			} catch (Exception e) {
				System.out.println("exception " + e.getMessage());
			}
		}

}
