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
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

/**
 * Class that creates the parts GUI to make orders
 * @author Brayden
 */
public class partsGUI extends JFrame {

	String url = "jdbc:mysql://localhost:3306/c_cats";
	String userName = "root";
	String pass = "Eyer8ser";


	/**
	 * method to test the database connection
	 */
	public void connect() {

		try {
			Connection con = DriverManager.getConnection(url, userName, pass);
			System.out.println("connected");
		} catch(Exception e) {
			System.out.println("exception " + e.getMessage());
		}

	}
	
	
	//global variables 
	private JPanel contentPane;

	private JTextField cardNumField;
	private JTextField expField;
	private JTextField cvvField;
	private JTextField paymentTtlField;

	//private JTextArea compTxtArea;

	private JScrollPane partScrollPane;
	private JScrollPane compScrollPane;

	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;

	private JButton backButton;
	private JButton refreshButton;
	private JButton btnNewButton;

	private JComboBox cardBox;
	private JComboBox partsComboBox;
	private JComboBox compComboBox;

	private JLabel cardLabel;
	private JLabel cardNumLabel;
	private JLabel cvvLabel;
	private JLabel paymentLabel;
	private JLabel expLabel;

	double sum = 0;
	List parts = new ArrayList<Part>();
	List comps = new ArrayList<Prebuilt>();
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		partsGUI database = new partsGUI();
		database.connect();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					partsGUI frame = new partsGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public partsGUI() {

		//creating the pane
		setTitle("Parts List");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1130, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);


		//text area to hold all pre-builts
		// compTxtArea = new JTextArea();
		// compTxtArea.setFont(new Font("Cambria", Font.PLAIN, 15));
		// compTxtArea.setBounds(606, 62, 278, 438);
		// compTxtArea.setEditable(false);
		// contentPane.add(compTxtArea);

		// compScrollPane = new JScrollPane(compTxtArea);
		// compScrollPane.setBounds(606, 63, 278, 438);
		// contentPane.add(compScrollPane);
		
		//display the most recent info from the database upon opening the window
		updateTable();
		
		// labels for text fields
		lblNewLabel = new JLabel("Available Parts");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(252, 36, 115, 16);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Available Pre-Built Computers");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(653, 35, 188, 16);
		contentPane.add(lblNewLabel_1);
		

		//Button to go back to the previous gui
		backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		backButton.setBounds(109, 11, 89, 23);
		contentPane.add(backButton);
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		
		//Button to refresh the pre-built and parts list
		refreshButton = new JButton("Refresh");
		refreshButton.setBounds(10, 11, 89, 23);
		contentPane.add(refreshButton);
		refreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//compTxtArea.setText("");
				updateTable();	
			}
		});

		cardBox = new JComboBox();
		cardBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cardBox.setModel(new DefaultComboBoxModel(new String[] {"", "Visa", "Discover", "Master"}));
		cardBox.setBounds(1005, 182, 76, 22);
		contentPane.add(cardBox);
		
		cardLabel = new JLabel("Card Type:");
		cardLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cardLabel.setBounds(932, 185, 64, 16);
		contentPane.add(cardLabel);
		
		cardNumLabel = new JLabel("Card number");
		cardNumLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cardNumLabel.setBounds(932, 257, 75, 16);
		contentPane.add(cardNumLabel);
		
		cardNumField = new JTextField();
		cardNumField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cardNumField.setBounds(932, 276, 120, 22);
		contentPane.add(cardNumField);
		cardNumField.setColumns(10);
		
		expLabel = new JLabel("Expiry date");
		expLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		expLabel.setBounds(932, 307, 63, 16);
		contentPane.add(expLabel);
		
		expField = new JTextField();
		expField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		expField.setBounds(932, 326, 60, 22);
		contentPane.add(expField);
		expField.setColumns(10);
		
		cvvField = new JTextField();
		cvvField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cvvField.setColumns(10);
		cvvField.setBounds(1005, 326, 60, 22);
		contentPane.add(cvvField);
		
		cvvLabel = new JLabel("CVV");
		cvvLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cvvLabel.setBounds(1004, 309, 77, 16);
		contentPane.add(cvvLabel);
		
		paymentLabel = new JLabel("Payment amount");
		paymentLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		paymentLabel.setBounds(946, 36, 96, 16);
		contentPane.add(paymentLabel);
		
		paymentTtlField = new JTextField();
		paymentTtlField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		paymentTtlField.setEditable(false);
		paymentTtlField.setBounds(936, 55, 116, 22);
		contentPane.add(paymentTtlField);
		paymentTtlField.setColumns(10);
		
		btnNewButton = new JButton("Checkout");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton.setBounds(948, 370, 96, 23);
		contentPane.add(btnNewButton);
		
		partsComboBox = new JComboBox();
		partsComboBox.setModel(new DefaultComboBoxModel(parts.toArray()));
		partsComboBox.setBounds(10, 66, 586, 22);
		contentPane.add(partsComboBox);
		
		compComboBox = new JComboBox();
		compComboBox.setModel(new DefaultComboBoxModel(comps.toArray()));
		compComboBox.setBounds(606, 66, 278, 22);
		contentPane.add(compComboBox);
		btnNewButton.addActionListener(new ActionListener() {
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
			Connection con = DriverManager.getConnection(url, userName, pass);
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(query);		
			while(result.next()) {
				Part tempPart = new Part((Integer.parseInt(result.getString("p.part_id"))), result.getString("part_name"), result.getString("part_desc"),Double.parseDouble(result.getString("part_price")));
				parts.add(tempPart);
			}		
		} catch(Exception e1) {
			System.out.println("exception" + e1.getMessage());
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
			Connection con = DriverManager.getConnection(url, userName, pass);
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
			System.out.println("exception" + e2.getMessage());
		}
	}

	/**
	 * Method that takes in the users payment information
	 * and purchases the parts
	 */
	public void checkout(){
		

	}

	/**
	 * Method that updates the total price of selected parts by adding the selected parts price
	 * @param part to add price of 
	 * @return new total price
	 */
	public double addTotal(Part part){
		return sum += part.getPrice();
	}

	/**
	 * Method that updates the total price of selected parts by subtracting the selected parts price
	 * @param part to remove price of 
	 * @return new total price
	 */
	public double removeTotal(Part part){
		return sum -= part.getPrice();
	}
}
