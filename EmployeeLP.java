package jake380;

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
import java.awt.event.ActionEvent;

public class EmployeeLP extends JFrame {

	private JPanel contentPane;
	private static EmployeeLP employeeLP;
	
	/**
	 * Launch the application.
	 */
	public static void startEmployeeLP () {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					employeeLP = new EmployeeLP();
					employeeLP.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EmployeeLP() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome: User");
		lblNewLabel.setBounds(150, 11, 148, 19);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("View Inventory");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBounds(10, 40, 163, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("View Repair Requests");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton_1.setBounds(10, 76, 163, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Order Parts");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton_2.setBounds(10, 112, 163, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Create Pre-Builts");
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton_3.setBounds(10, 148, 163, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("View Orders");
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton_4.setBounds(10, 184, 163, 23);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("TestFeature");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "IT WORKS!!", "YURIKA", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnNewButton_5.setBounds(260, 126, 91, 23);
		contentPane.add(btnNewButton_5);
	}
}
