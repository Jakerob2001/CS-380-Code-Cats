import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class SignInPage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SignUpPage signUp;
	private static SignInPage frame;
	private User currentUser;
	private EmployeeDashboard employeeView;
	private UserDashboard clientView;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPass;
	private JCheckBox chkShowPass;
	private static Connection con;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		startUpSignIn();
			
	}

	public static void startUpSignIn () {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new SignInPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		connect();

	}
	
	public static void connect() {
		
		String url = "jdbc:mysql://localhost:3306/c_cats";
		String userName = "root";
		String pass = "Jrob2001";
		
		try {

			con = DriverManager.getConnection(url, userName, pass);
			System.out.println("Connected");

		}catch(Exception e) {
			System.out.println("exception " + e.getMessage());

		}
	}

	/**
	 * Create the frame.
	 */
	public SignInPage() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 645, 316);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{633, 0};
		gbl_contentPane.rowHeights = new int[]{437, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblWelcome = new JLabel("Welcome");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Tahoma", Font.BOLD, 32));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		//gbc_lblNewLabel.gridwidth = 0;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 0;
		panel.add(lblWelcome, gbc_lblNewLabel);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.fill = GridBagConstraints.RELATIVE;
		gbc_lblUsername.insets = new Insets(20, 5, 20, 5);
		gbc_lblUsername.gridwidth = 1;
		gbc_lblUsername.gridx = 0;
		gbc_lblUsername.gridy = 2;
		panel.add(lblUsername, gbc_lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtUsername.setColumns(10);
		GridBagConstraints gbc_txtUsername = new GridBagConstraints();
		gbc_txtUsername.gridwidth = 5;
		gbc_txtUsername.insets = new Insets(20, 0, 20, 5);
		gbc_txtUsername.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUsername.gridx = 1;
		gbc_txtUsername.gridy = 2;
		panel.add(txtUsername, gbc_txtUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.fill = GridBagConstraints.RELATIVE;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridwidth = 1;
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 4;
		panel.add(lblPassword, gbc_lblPassword);
		
		txtPass = new JPasswordField();
		txtPass.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_txtPass = new GridBagConstraints();
		gbc_txtPass.gridwidth = 5;
		gbc_txtPass.insets = new Insets(20, 0, 20, 0);
		gbc_txtPass.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPass.gridx = 1;
		gbc_txtPass.gridy = 4;
		panel.add(txtPass, gbc_txtPass);
		txtPass.setColumns(10);

		chkShowPass = new JCheckBox("Show"/*new ImageIcon("hide-password-icon.png")*/);
		chkShowPass.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_chkPass = new GridBagConstraints();
		gbc_chkPass.insets = new Insets(20, 5, 20, 30);
		gbc_chkPass.fill = GridBagConstraints.RELATIVE;
		gbc_chkPass.gridx = 6;
		gbc_chkPass.gridy = 4;
		gbc_chkPass.gridwidth = 6;
		chkShowPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				txtPass.setEchoChar(chkShowPass.isSelected() ? '\u0000' : '•');

			}
		});
		panel.add(chkShowPass, gbc_chkPass);


		JButton btnSignIn = new JButton("Sign In");
		btnSignIn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_btnSignIn = new GridBagConstraints();
		gbc_btnSignIn.insets = new Insets(20, 0, 20, 5);
		gbc_btnSignIn.fill = GridBagConstraints.RELATIVE;
		gbc_btnSignIn.gridx = 0;
		gbc_btnSignIn.gridy = 6;
		gbc_btnSignIn.gridwidth = 1;
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(validateUser(txtUsername.getText(), String.valueOf(txtPass.getPassword()))) {
					currentUser = populateUser(txtUsername.getText());
					
					char status = currentUser.getUserID().charAt(0);
					switch (status) {
						case 'C' -> {
							frame.setVisible(false);
							clientView = new UserDashboard(con, currentUser);
							clientView.setVisible(true);
						}
						case 'E', 'M' -> {
							frame.setVisible(false);
							employeeView = new EmployeeDashboard(con, currentUser);
							employeeView.setVisible(true);
						}
						default ->
								JOptionPane.showMessageDialog(null, "User ID is Invalid", "Invalid ID", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		panel.add(btnSignIn, gbc_btnSignIn);
		
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_btnSignUp = new GridBagConstraints();
		gbc_btnSignUp.insets = new Insets(20, 0, 20, 0);
		gbc_btnSignUp.gridx = 3;
		gbc_btnSignUp.gridy = 6;
		gbc_btnSignUp.gridwidth = 5;
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.setVisible(false);
				signUp = new SignUpPage();	
				signUp.startSignUp(con, currentUser);
				
			}
		});
		panel.add(btnSignUp, gbc_btnSignUp);
		
	}
	
	public boolean validateUser(String username, String pass) {
		
		String query = "SELECT * FROM users";
		
		Statement statement;

		try {
			statement = con.createStatement();
		
			ResultSet result = statement.executeQuery(query);
		
			while(result.next()) {
				
				if(result.getString("user_name").equals(username)) {
					if(result.getString("password").equals(pass)) {
						return true;
					} else {
						JOptionPane.showMessageDialog(null, "Invalid Password", "Invalid Password", JOptionPane.ERROR_MESSAGE);
						return false;
					}
				}
			}
			JOptionPane.showMessageDialog(null, "Username is not in System", "Invalid Username", JOptionPane.ERROR_MESSAGE);
			return false;
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return false;
	}
	
	public User populateUser(String username) {
		
		String query = "SELECT * FROM users WHERE user_name='" + username + "'";
		
		Statement statement;
		
		User user = null;
		
		try {
			statement = con.createStatement();
		
			ResultSet result = statement.executeQuery(query);
		
			while(result.next()) {
				
				user = new User(result.getString("user_id"), result.getString("user_name"), result.getString("password"),
					result.getString("f_name"), result.getString("l_name"), Integer.parseInt(result.getString("address_id")));
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return user;
	}

}
