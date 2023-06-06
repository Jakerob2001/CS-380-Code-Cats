/**
* Class for new user registration
* @author Jake
*/
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class SignUpPage extends JFrame {

	private JPanel contentPane;
	private static SignUpPage signUpFrame;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JTextField txtStreetNum;
	private JTextField txtStreetName;
	private JTextField txtUnitNum;
	private static Connection con;
	private static User user;
	private JTextField txtState;
	private JTextField txtZip;
	private JTextField txtCity;


	/**
	 * Launch the application.
	 */
	public void startSignUp(Connection connection, User currentUser) {
		con = connection;
		user = currentUser;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					signUpFrame = new SignUpPage();
					signUpFrame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SignUpPage() {
		setTitle("Sign Up");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 569, 691);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 44, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblSignUp = new JLabel("Sign Up");
		lblSignUp.setFont(new Font("Tahoma", Font.BOLD, 22));
		GridBagConstraints gbc_lblSignUp = new GridBagConstraints();
		gbc_lblSignUp.anchor = GridBagConstraints.CENTER;
		gbc_lblSignUp.insets = new Insets(0, 0, 10, 30);
		gbc_lblSignUp.gridx = 3;
		gbc_lblSignUp.gridy = 1;
		contentPane.add(lblSignUp, gbc_lblSignUp);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_lblFirstName = new GridBagConstraints();
		gbc_lblFirstName.gridwidth = 2;
		gbc_lblFirstName.insets = new Insets(20, 0, 5, 5);
		gbc_lblFirstName.gridx = 0;
		gbc_lblFirstName.gridy = 3;
		contentPane.add(lblFirstName, gbc_lblFirstName);
		
		txtFirstName = new JTextField();
		txtFirstName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_txtState = new GridBagConstraints();
		gbc_txtState.gridwidth = 7;
		gbc_txtState.insets = new Insets(20, 0, 5, 0);
		gbc_txtState.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtState.gridx = 2;
		gbc_txtState.gridy = 3;
		contentPane.add(txtFirstName, gbc_txtState);
		txtFirstName.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_lblLastName = new GridBagConstraints();
		gbc_lblLastName.gridwidth = 2;
		gbc_lblLastName.insets = new Insets(20, 0, 5, 5);
		gbc_lblLastName.gridx = 0;
		gbc_lblLastName.gridy = 5;
		contentPane.add(lblLastName, gbc_lblLastName);
		
		txtLastName = new JTextField();
		txtLastName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtLastName.setColumns(10);
		GridBagConstraints gbc_txtLastName = new GridBagConstraints();
		gbc_txtLastName.gridwidth = 7;
		gbc_txtLastName.insets = new Insets(20, 0, 5, 0);
		gbc_txtLastName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtLastName.gridx = 2;
		gbc_txtLastName.gridy = 5;
		contentPane.add(txtLastName, gbc_txtLastName);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.gridwidth = 2;
		gbc_lblUsername.insets = new Insets(20, 0, 5, 5);
		gbc_lblUsername.gridx = 0;
		gbc_lblUsername.gridy = 7;
		contentPane.add(lblUsername, gbc_lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtUsername.setColumns(10);
		GridBagConstraints gbc_txtUsername = new GridBagConstraints();
		gbc_txtUsername.gridwidth = 7;
		gbc_txtUsername.insets = new Insets(20, 0, 5, 0);
		gbc_txtUsername.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUsername.gridx = 2;
		gbc_txtUsername.gridy = 7;
		contentPane.add(txtUsername, gbc_txtUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.gridwidth = 2;
		gbc_lblPassword.insets = new Insets(20, 0, 5, 5);
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 9;
		contentPane.add(lblPassword, gbc_lblPassword);
		
		txtPassword = new JTextField();
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridwidth = 7;
		gbc_passwordField.insets = new Insets(20, 0, 5, 0);
		gbc_passwordField.gridx = 2;
		gbc_passwordField.gridy = 9;
		contentPane.add(txtPassword, gbc_passwordField);
		
		JLabel lblStreetNum = new JLabel("Steet Number");
		lblStreetNum.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_lblStreetNum = new GridBagConstraints();
		gbc_lblStreetNum.gridwidth = 2;
		gbc_lblStreetNum.insets = new Insets(20, 0, 5, 5);
		gbc_lblStreetNum.gridx = 0;
		gbc_lblStreetNum.gridy = 11;
		contentPane.add(lblStreetNum, gbc_lblStreetNum);
		
		txtStreetNum = new JTextField();
		txtStreetNum.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtStreetNum.setColumns(10);
		GridBagConstraints gbc_txtStreetNum = new GridBagConstraints();
		gbc_txtStreetNum.gridwidth = 7;
		gbc_txtStreetNum.insets = new Insets(20, 0, 5, 0);
		gbc_txtStreetNum.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtStreetNum.gridx = 2;
		gbc_txtStreetNum.gridy = 11;
		contentPane.add(txtStreetNum, gbc_txtStreetNum);
		
		JLabel lblStreetName = new JLabel("Street Name");
		lblStreetName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_lblStreetName = new GridBagConstraints();
		gbc_lblStreetName.gridwidth = 2;
		gbc_lblStreetName.insets = new Insets(20, 0, 5, 5);
		gbc_lblStreetName.gridx = 0;
		gbc_lblStreetName.gridy = 13;
		contentPane.add(lblStreetName, gbc_lblStreetName);
		
		txtStreetName = new JTextField();
		txtStreetName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtStreetName.setColumns(10);
		GridBagConstraints gbc_txtStreetName = new GridBagConstraints();
		gbc_txtStreetName.gridwidth = 7;
		gbc_txtStreetName.insets = new Insets(20, 0, 5, 0);
		gbc_txtStreetName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtStreetName.gridx = 2;
		gbc_txtStreetName.gridy = 13;
		contentPane.add(txtStreetName, gbc_txtStreetName);
		
		JLabel lblUnitNum = new JLabel("Unit # (Optional)");
		lblUnitNum.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_lblUnitNum = new GridBagConstraints();
		gbc_lblUnitNum.gridwidth = 2;
		gbc_lblUnitNum.insets = new Insets(20, 0, 5, 5);
		gbc_lblUnitNum.gridx = 0;
		gbc_lblUnitNum.gridy = 15;
		contentPane.add(lblUnitNum, gbc_lblUnitNum);
		
		txtUnitNum = new JTextField();
		txtUnitNum.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtUnitNum.setColumns(10);
		GridBagConstraints gbc_txtUnitNum = new GridBagConstraints();
		gbc_txtUnitNum.insets = new Insets(20, 0, 5, 0);
		gbc_txtUnitNum.gridwidth = 7;
		gbc_txtUnitNum.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUnitNum.gridx = 2;
		gbc_txtUnitNum.gridy = 15;
		contentPane.add(txtUnitNum, gbc_txtUnitNum);
		
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_btnSignUp = new GridBagConstraints();
		gbc_btnSignUp.insets = new Insets(0, 0, 20, 40);
		gbc_btnSignUp.gridwidth = 2;
		gbc_btnSignUp.gridx = 7;
		gbc_btnSignUp.gridy = 19;
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean canAdd = verifySignUp();

				if (canAdd) {
					try {
						addUser();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				signUpFrame.setDefaultCloseOperation(HIDE_ON_CLOSE);
			}
		});
		contentPane.add(btnSignUp, gbc_btnSignUp);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setHorizontalAlignment(SwingConstants.CENTER);
		lblCity.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_lblCity = new GridBagConstraints();
		gbc_lblCity.anchor = GridBagConstraints.WEST;
		gbc_lblCity.insets = new Insets(20, 20, 5, 5);
		gbc_lblCity.gridx = 0;
		gbc_lblCity.gridy = 17;
		contentPane.add(lblCity, gbc_lblCity);
		
		txtCity = new JTextField();
		txtCity.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_txtCity = new GridBagConstraints();
		gbc_txtCity.insets = new Insets(20, 5, 5, 20);
		gbc_txtCity.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCity.gridx = 1;
		gbc_txtCity.gridy = 17;
		contentPane.add(txtCity, gbc_txtCity);
		txtCity.setColumns(10);
		
		JLabel lblState = new JLabel("State");
		lblState.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_lblState = new GridBagConstraints();
		gbc_lblState.anchor = GridBagConstraints.EAST;
		gbc_lblState.insets = new Insets(20, 20, 5, 5);
		gbc_lblState.gridx = 2;
		gbc_lblState.gridy = 17;
		contentPane.add(lblState, gbc_lblState);
		
		txtState = new JTextField();
		txtState.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_txtState1 = new GridBagConstraints();
		gbc_txtState1.insets = new Insets(20, 10, 5, 40);
		gbc_txtState1.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtState1.gridwidth = 1;
		gbc_txtState1.gridx = 3;
		gbc_txtState1.gridy = 17;
		contentPane.add(txtState, gbc_txtState1);
		txtState.setColumns(10);
		
		JLabel lblZip = new JLabel("Zipcode");
		lblZip.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_lblZip = new GridBagConstraints();
		gbc_lblZip.anchor = GridBagConstraints.EAST;
		gbc_lblZip.insets = new Insets(20, 0, 5, 5);
		gbc_lblZip.gridx = 7;
		gbc_lblZip.gridy = 17;
		contentPane.add(lblZip, gbc_lblZip);
		
		txtZip = new JTextField();
		txtZip.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_txtZip = new GridBagConstraints();
		gbc_txtZip.insets = new Insets(20, 0, 5, 20);
		gbc_txtZip.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtZip.gridx = 8;
		gbc_txtZip.gridy = 17;
		contentPane.add(txtZip, gbc_txtZip);
		txtZip.setColumns(10);
		
		
		JButton btnBack = new JButton("<- Go Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				goBack();
				
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_btnBack = new GridBagConstraints();
		gbc_btnBack.insets = new Insets(0, 0, 20, 0);
		gbc_btnBack.gridx = 1;
		gbc_btnBack.gridy = 19;
		contentPane.add(btnBack, gbc_btnBack);
		
		
	}
	/**
	* Function to verify all sign up fields are entered and correct
	*/
	public boolean verifySignUp () {
		
		boolean allFieldsFilled = txtFirstName.getText().matches("^[A-Za-z]{1,45}$") && txtLastName.getText().matches("^[A-Za-z]{1,45}$") && 
				txtUsername.getText().matches("^[A-Za-z]{1,20}$") && txtPassword.getText().matches("^[^;]{1,30}$") && 
				txtStreetName.getText().matches("^[ A-Za-z0-9]{1,20}$") && txtStreetNum.getText().matches("^[0-9A-Za-z]{1,10}$")
				&& txtCity.getText().matches("^[ A-Za-z]{1,45}$") && txtState.getText().matches("^[A-Z]{2}$") && txtZip.getText().matches("^[0-9]{5}$");
		
		if (allFieldsFilled) {
			
			String query = "SELECT * FROM users";
		
			Statement statement;
			
			try {
				statement = con.createStatement();
		
				ResultSet result = statement.executeQuery(query);
		
				while(result.next()) {
				
					if(result.getString("user_name").equals(txtUsername.getText())) {
						JOptionPane.showMessageDialog(null, "Username is already in use", "Invalid Username", JOptionPane.ERROR_MESSAGE);
						return false;
					}
				
				}
			
				JOptionPane.showMessageDialog(null, "Sign Up Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
				return true;
			
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		JOptionPane.showMessageDialog(null, "All required fields must be filled and correct", "Fields Empty", JOptionPane.ERROR_MESSAGE);
		return false;
		
	}
	
	/**
	* Function to add new user to database.
	*/
	public void addUser () throws SQLException {
		
		String query = "SELECT * FROM users WHERE user_name = 'variable';";
		
		String queryAdd = "SELECT MAX(address_id) FROM addresses;";
		
		Statement statement = con.createStatement();
		
		String variableID = "";
		int addressID = 0;
		
		try {
			statement = con.createStatement();
	
			ResultSet result = statement.executeQuery(query);
			
			statement = con.createStatement();
			
			ResultSet resultAdd = statement.executeQuery(queryAdd);
			
			while(result.next()) {
			
				variableID = result.getString(1);
	
			}
			
			while(resultAdd.next()) {
				
				addressID = resultAdd.getInt(1);
				
			}
			
		
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		addressID += 1;
		variableID = createID(variableID);
		
		query = "INSERT INTO `c_cats`.`addresses` (`address_id`, `street_number`, `street_name`, `unit`, `city`, `state`, `zipcode`) VALUES ('" +
						addressID + "', '" + txtStreetNum.getText() + "', '" + txtStreetName.getText() + "', '" + txtUnitNum.getText() 
						+ "', '" + txtCity.getText() + "', '" + txtState.getText() +  "', '" + txtZip.getText() + "');";
		
		statement.execute(query);
		
		query = "INSERT INTO `c_cats`.`users` (`user_id`, `user_name`, `password`, `f_name`, `l_name`, `address_id`) VALUES ('C" +
				(variableID) + "', '" + txtUsername.getText() + "', '" + txtPassword.getText() + "', '" + txtFirstName.getText() 
				+ "', '" + txtLastName.getText() + "', '" + addressID + "');";

		statement.execute(query);

		String queryUpdate = "UPDATE users SET user_id = '" + (variableID) + "' WHERE (user_name = 'variable');";
		
		statement.execute(queryUpdate);
		
		JOptionPane.showMessageDialog(contentPane, "Added Successfully", "User Added", JOptionPane.INFORMATION_MESSAGE);
		goBack();
	}
	
	
	/**
	* Function to create a new user ID based on last user ID
	* @param str The string to parse
	* @return String The new User ID
	*/
	
	public static String createID(String str) {

        String num = str.replaceAll("[^0-9]", "");
	        
	    int numValue = Integer.parseInt(num);

	    int newNum = numValue + 1;
	        
	    String formattedNumericPart = String.format("%0" + num.length() + "d", newNum);
	        
	    return str.replaceFirst("[0-9]+", formattedNumericPart);

	}
	
	/**
	* Function to return to sign in feature window
	*/
	public void goBack () {
		
		SignInPage signIn = new SignInPage();
		signUpFrame.setVisible(false);
		signIn.startUpSignIn();
	
	}

}
