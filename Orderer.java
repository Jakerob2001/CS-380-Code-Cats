//imports
import java.awt.EventQueue;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

import java.text.NumberFormat;

/**
 * Class that creates the parts GUI to make orders
 * @author Brayden
 */
public class Orderer extends JFrame {

	//global variables 
	private JPanel contentPane;

	private JTextField cardNumField;
	private JTextField expField;
	private JTextField cvvField;
	private JTextField paymentTxtField;

	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;

	private JScrollPane scrollPane;

	private JButton backButton;
	private JButton refreshButton;
	private JButton checkoutButton;
	private JButton partAddButton;
	private JButton compAddButton;
	private JButton clearButton;

	private JTextArea cartTxtArea;

	private JComboBox cardBox;
	private JComboBox partsComboBox;
	private JComboBox compComboBox;

	private JLabel cardLabel;
	private JLabel cardNumLabel;
	private JLabel cvvLabel;
	private JLabel paymentLabel;
	private JLabel expLabel;
	private JLabel cartLabel;

	Connection con;

	double sum = 0;
	List parts = new ArrayList<Part>();
	List comps = new ArrayList<Prebuilt>();
	List cart = new ArrayList<>();

	/**
	 * Create the frame.
	 */
	public Orderer(Connection connection, User currentUser) {

		con = connection;

		//creating the pane
		setTitle("Order Parts");
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1130, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//display the most recent info from the database upon opening the window
		updateTable();
		

		/**
		 * JLabel 
		*/

		lblNewLabel = new JLabel("Available Parts");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(252, 36, 115, 16);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Available Pre-Built Computers");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(606, 36, 188, 16);
		contentPane.add(lblNewLabel_1);
		
		cardLabel = new JLabel("Card Type:");
		cardLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cardLabel.setBounds(932, 185, 64, 16);
		contentPane.add(cardLabel);
		
		cardNumLabel = new JLabel("Card number");
		cardNumLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cardNumLabel.setBounds(932, 257, 75, 16);
		contentPane.add(cardNumLabel);
		
		expLabel = new JLabel("Expiry date");
		expLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		expLabel.setBounds(932, 307, 63, 16);
		contentPane.add(expLabel);
		
		cvvLabel = new JLabel("CVV");
		cvvLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cvvLabel.setBounds(1004, 309, 77, 16);
		contentPane.add(cvvLabel);
		
		paymentLabel = new JLabel("Payment amount");
		paymentLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		paymentLabel.setBounds(946, 36, 96, 16);
		contentPane.add(paymentLabel);
		
		cartLabel = new JLabel("Shopping cart:");
		cartLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cartLabel.setBounds(252, 257, 84, 16);
		contentPane.add(cartLabel);


		/**
		 * TextField
		*/

		//text field for displaying the total amount
		paymentTxtField = new JTextField();
		paymentTxtField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		paymentTxtField.setEditable(false);
		paymentTxtField.setBounds(936, 55, 116, 22);
		contentPane.add(paymentTxtField);
		paymentTxtField.setColumns(10);

		//text field for entering the cards expiry date
		expField = new JTextField();
		expField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		expField.setBounds(932, 326, 60, 22);
		contentPane.add(expField);
		expField.setColumns(10);
		
		//text field for entering the cards cvv
		cvvField = new JTextField();
		cvvField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cvvField.setColumns(10);
		cvvField.setBounds(1005, 326, 60, 22);
		contentPane.add(cvvField);
		
		//text field for the cards number
		cardNumField = new JTextField();
		cardNumField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cardNumField.setBounds(932, 276, 120, 22);
		contentPane.add(cardNumField);
		cardNumField.setColumns(10);


		/**
		 * Combobox 
		*/

		//combo box that hold all the parts
		partsComboBox = new JComboBox();
		partsComboBox.setModel(new DefaultComboBoxModel(parts.toArray()));
		partsComboBox.setBounds(10, 66, 586, 22);
		contentPane.add(partsComboBox);
		
		//combo boc that hold every pre-built
		compComboBox = new JComboBox();
		compComboBox.setModel(new DefaultComboBoxModel(comps.toArray()));
		compComboBox.setBounds(606, 66, 278, 22);
		contentPane.add(compComboBox);
		
		//combo box for the card options 
		cardBox = new JComboBox();
		cardBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cardBox.setModel(new DefaultComboBoxModel(new String[] {"", "Visa", "Discover", "Master"}));
		cardBox.setBounds(1005, 182, 76, 22);
		contentPane.add(cardBox);


		/**
		 * TextArea
		 */

		cartTxtArea = new JTextArea();
		cartTxtArea.setFont(new Font("Cambria", Font.PLAIN, 15));
		cartTxtArea.setEditable(false);
		cartTxtArea.setBounds(10, 276, 586, 224);
		contentPane.add(cartTxtArea);
		

		/**
		 * ScrollPane 
		*/

		scrollPane = new JScrollPane(cartTxtArea);
		scrollPane.setBounds(10, 276, 586, 224);
		contentPane.add(scrollPane);
	

		/**
		 * Buttons
		*/
		
		//button that clears the cart
		clearButton = new JButton("Clear cart");
		clearButton.setBounds(606, 477, 96, 23);
		contentPane.add(clearButton);
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cartTxtArea.setText("");
				cart.clear();
				sum = 0;
				paymentTxtField.setText("");
			}
		});

		//button that adds a part to the cart
		partAddButton = new JButton("Add to cart");
		partAddButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		partAddButton.setBounds(507, 35, 89, 23);
		contentPane.add(partAddButton);
		partAddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cart.add(partsComboBox.getSelectedItem());
				cartTxtArea.append(partsComboBox.getSelectedItem().toString() + "\n\n");
				Part part = (Part) partsComboBox.getSelectedItem();
				addTotal(part);
			}
		});

		//button that adds a pre-built to the cart
		compAddButton = new JButton("Add to cart");
		compAddButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		compAddButton.setBounds(795, 35, 89, 23);
		contentPane.add(compAddButton);
		compAddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cart.add(compComboBox.getSelectedItem());
				cartTxtArea.append(compComboBox.getSelectedItem().toString() + "\n\n");
				Prebuilt prebuilt = (Prebuilt) compComboBox.getSelectedItem();
				compAddTotal(prebuilt);
			}
		});

		//button that checks out the user
		checkoutButton = new JButton("Checkout");
		checkoutButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		checkoutButton.setBounds(948, 370, 96, 23);
		contentPane.add(checkoutButton);
		checkoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean check = checkout();
				if (check){
					cart.clear();
					cartTxtArea.setText("");
					sum = 0;
					paymentTxtField.setText("");
					cardNumField.setText("");
					expField.setText("");
					cvvField.setText("");
					JOptionPane.showMessageDialog(contentPane, "Order Sucessful", "Order status", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		//Button to refresh the pre-built and parts list
		refreshButton = new JButton("Refresh");
		refreshButton.setBounds(10, 11, 89, 23);
		contentPane.add(refreshButton);
		refreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTable();	
			}
		});


		//Button to go back to the previous gui
		backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		backButton.setBounds(109, 11, 89, 23);
		contentPane.add(backButton);
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						
			}
		});
	}

	/**
	 * Method that updates the GUI with the most recent 
	 * data from the database
	 */
	private void updateTable(){
		//query to retrieve part name and description of every part that is in stock 
		String query = "SELECT * FROM part p JOIN part_inventory pi ON p.part_id = pi.part_id WHERE pi.qty > 0;";
				
		try {
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(query);		
			while(result.next()) {
				Part tempPart = new Part((Integer.parseInt(result.getString("p.part_id"))), result.getString("part_name"), result.getString("part_desc"),Double.parseDouble(result.getString("part_price")));
				parts.add(tempPart);
			}		
		} catch(Exception e1) {
			JOptionPane.showMessageDialog(contentPane, "error: " + e1.getMessage(), "Query error", JOptionPane.ERROR_MESSAGE);
		}
				
				
		//query to retrieve pre-builts
		String query2 = "SELECT * " +
		"FROM prebuilt " + 
			"JOIN part p " +
				"ON motherboard_id = p.part_id " + 
			"JOIN part pt " + 
				"ON ps_id = pt.part_id " + 
			"JOIN part pt1 " + 
				"ON gpu_id = pt1.part_id " + 
			"JOIN part pt2 " + 
				"ON cpu_id = pt2.part_id " +
			"JOIN part pt3 " +
				"ON ram_id = pt3.part_id " +
			"JOIN part pt4 " + 
				"ON case_id = pt4.part_id " +
			"JOIN part pt5 " +	
				"ON storage_id = pt5.part_id";
		try {
			Statement statement = con.createStatement();
			ResultSet result2 = statement.executeQuery(query2);
					
			while(result2.next()) {
				String name = result2.getString(1);
				Part motherbaord = new Part(result2.getInt(2), result2.getString(11), result2.getString(12), result2.getDouble(14));
				Part gpu = new Part(result2.getInt(4), result2.getString(21), result2.getString(22), result2.getDouble(24));
				Part ps = new Part(result2.getInt(3), result2.getString(16), result2.getString(17), result2.getDouble(19));
				Part cpu = new Part(result2.getInt(5), result2.getString(26), result2.getString(27), result2.getDouble(29));
				Part ram = new Part(result2.getInt(6), result2.getString(31), result2.getString(32), result2.getDouble(34));
				Part aCase = new Part(result2.getInt(8), result2.getString(36), result2.getString(37), result2.getDouble(39));
				Part storage = new Part(result2.getInt(7), result2.getString(41), result2.getString(42), result2.getDouble(44));
				double price = result2.getDouble(9);
				Prebuilt prebuilt = new Prebuilt(name, motherbaord, gpu, ps, cpu, ram, aCase, storage, price);
				comps.add(prebuilt);
			}
			//compTxtArea.setCaretPosition(0);		
		} catch(Exception e2) {
			JOptionPane.showMessageDialog(contentPane, "error: " + e2.getMessage(), "Query error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Method that takes in the users payment information
	 * and purchases the parts
	 * @return true if the order was successful; false if there was an error
	 */
	public boolean checkout(){
		
		// flags to see if info entered is valid 
		boolean flag; 

		flag = checkEmpty();

		if(flag){
			return false;
		}

		flag = checkValid();

		if(flag){
			return false;
		}
	
		//creating a new user and getting the current date
		User user = new User("C00001", "mikeynew", "securepassword", "Michael", "Jackson", 1);
		String time = java.time.LocalDate.now().toString();
		

		// query to get the highest order_id and increment by one for new orders
		int orId = 0;
		try {
			String query = "SELECT MAX(order_id) FROM orders";
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(query);

			//check for order_id to use (max + 1)
			while(result.next()) {
				orId = result.getInt(1) + 1;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane, "error: " + e.getMessage(), "Error fetching order ID", JOptionPane.ERROR_MESSAGE);
		}
		

		// query to insert the new order in orders
		try {
			String query = "INSERT INTO orders VALUES (" + orId + ", '" + user.getUserID() + "', '" + cardBox.getSelectedItem().toString().toUpperCase() + "', '" 
			+ expField.getText() + "', '" + cardNumField.getText() + "', '" + cvvField.getText() +"', '" + time + "')";
			Statement statement = con.createStatement();
			statement.executeUpdate(query);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane, "error: " + e.getMessage(), "Insertion error", JOptionPane.ERROR_MESSAGE);
		}


		// query to add each item from cart into order lines
		int orIdnum = 1;
		try{
			for(int i = 0; i < cart.size(); i++){
				if(cart.get(i) instanceof Prebuilt){
					String query = "INSERT INTO order_lines VALUES (" + orId + ", " + orIdnum + ", NULL, NULL, '" + cart.get(i).toString() + "')";
					Statement statement = con.createStatement();
					statement.executeUpdate(query);
					orIdnum++;
				} else {
					String query = "INSERT INTO order_lines VALUES (" + orId + ", " + orIdnum + ", " + ((Part) cart.get(i)).getId() + ", 1, NULL)";
					Statement statement = con.createStatement();
					statement.executeUpdate(query);
					orIdnum++;					
				}
			}

		} catch (Exception e2){
			System.out.println(e2.getMessage()); 
		}
		
		return true;
	}

	/**
	 * Method that checks to see if any information is missing to check out
	 */
	public boolean checkEmpty(){
		if (cart.isEmpty()){
			JOptionPane.showMessageDialog(contentPane, "No items in cart!", "Checkout error", JOptionPane.ERROR_MESSAGE);
			return true; 
		}

		if(cardBox.getSelectedItem() == ""){
			JOptionPane.showMessageDialog(contentPane, "Card info missing!", "Checkout error", JOptionPane.ERROR_MESSAGE);
			return true;
		}
	
		if(cardNumField.getText().equals("")){
			JOptionPane.showMessageDialog(contentPane, "Card info missing!", "Checkout error", JOptionPane.ERROR_MESSAGE);
			return true;
		}

		if(expField.getText().equals("")){
			JOptionPane.showMessageDialog(contentPane, "Card info missing!", "Checkout error", JOptionPane.ERROR_MESSAGE);
			return true;
		}

		if(cvvField.getText().equals("")){
			JOptionPane.showMessageDialog(contentPane, "Card info missing!", "Checkout error", JOptionPane.ERROR_MESSAGE);
			return true;
		}
		return false;
	}

	/**
	 * Method that checks to see if the info provided by the customer is valid
	 */
	public boolean checkValid(){
		if(cardNumField.getText().length() != 16){
			JOptionPane.showMessageDialog(contentPane, "Invalid card number length!", "Checkout error", JOptionPane.ERROR_MESSAGE);
			return true;
		}

		if(!(cardNumField.getText().matches("[0-9]+"))){
			JOptionPane.showMessageDialog(contentPane, "Card number must contain digits!", "Checkout error", JOptionPane.ERROR_MESSAGE);
			return true;
		}

		if(cvvField.getText().length() != 3){
			JOptionPane.showMessageDialog(contentPane, "Invalid CVV length!", "Checkout error", JOptionPane.ERROR_MESSAGE);
			return true;
		}

		if(!(cvvField.getText().matches("[0-9]+"))){
			JOptionPane.showMessageDialog(contentPane, "CVV must contain digits!", "Checkout error", JOptionPane.ERROR_MESSAGE);
			return true;
		}
		return false;
	}

	/**
	 * Method that updates the total price of selected parts by adding the selected parts price
	 * @param part to add price of 
	 */
	public void addTotal(Part part){
		sum += part.getPrice();
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);
		paymentTxtField.setText(String.valueOf(sum));
	}

	/**
	 * Method that updates the total price of prebuilts by adding the selected prebuilts price
	 * @param prebuilt to add price of 
	 */
	public void compAddTotal(Prebuilt prebuilt){
		sum += prebuilt.getPrice();
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);
		paymentTxtField.setText(String.valueOf(sum));
	}
}
