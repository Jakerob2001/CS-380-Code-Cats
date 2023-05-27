package jake380;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
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
	private SignUp signUp;
	private User currentUser;
	private EmployeeLP employeeView;
	private UserDashboard clientView;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JTextField txtPass;
	private static Connection con;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignInPage frame = new SignInPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				connect();
			}
		});
	}
	
	public static void connect() {
		
		String url = "jdbc:mysql://localhost:3306/c_cats";
		String userName = "root";
		String pass = "Jrob2001!";
		
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
		
		JLabel lblNewLabel = new JLabel("Welcome");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 32));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 6;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 2;
		panel.add(lblUsername, gbc_lblNewLabel_1);
		
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtUsername.setColumns(10);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.gridwidth = 5;
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 2;
		panel.add(txtUsername, gbc_textField_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 4;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		txtPass = new JTextField();
		txtPass.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 5;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 4;
		panel.add(txtPass, gbc_textField);
		txtPass.setColumns(10);
		
		JButton btnNewButton = new JButton("Sign In");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				employeeView = new EmployeeLP();
				clientView = new UserDashboard();
				if(validateUser(txtUsername.getText(), txtPass.getText())) {
					currentUser = populateUser(txtUsername.getText());
					
					char status = currentUser.getUserID().charAt(0);
					switch(status) {
						case 'C':
							clientView.show();	
							break;
						case 'E':
							employeeView.show();	
							break;
						case 'M':
							System.out.println("Manager View");		
							break;
						default:
							JOptionPane.showMessageDialog(null, "User ID is Invalid", "Invalid ID", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 6;
		panel.add(btnNewButton, gbc_btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Sign Up");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				signUp = new SignUp();
				SignUp.startSignUp(con, currentUser);
				
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_1.gridx = 5;
		gbc_btnNewButton_1.gridy = 6;
		panel.add(btnNewButton_1, gbc_btnNewButton_1);
		
	}
	
	public boolean validateUser(String username, String pass) {
		
		String query = "SELECT * FROM users";
		
		Statement statement;
		try {
			statement = con.createStatement();
		
			ResultSet result = statement.executeQuery(query);
		
			while(result.next()) {
				
				if(result.getString("user_name").equals(username)) {
					if(result.getString("password").equals(pass))
						return true;
					else
						JOptionPane.showMessageDialog(null, "Invalid Password", "Invalid Password", JOptionPane.ERROR_MESSAGE);
						return false;
				}
			}
			JOptionPane.showMessageDialog(null, "Username is not in System", "Invalid Username", JOptionPane.ERROR_MESSAGE);
			return false;
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return user;
	}
	
	
}
