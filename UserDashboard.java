package jake380;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class UserDashboard extends JFrame {

	private JPanel contentPane;
	private static UserDashboard userDash;

	/**
	 * Launch the application.
	 */
	public static void startUserDash() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					userDash = new UserDashboard();
					userDash.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UserDashboard() {
		setTitle("User Dashboard");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 453, 144);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton_2 = new JButton("Schedule Repair");
		btnNewButton_2.setBounds(10, 67, 116, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_1 = new JButton("Order Parts");
		btnNewButton_1.setBounds(176, 67, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Order Prebuilds");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(303, 67, 124, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Welcome: User");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(163, 11, 146, 14);
		contentPane.add(lblNewLabel);
	}

}
