import java.awt.EventQueue;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class RepairOrders extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void startFufillRepairs (Connection connection, User currentUser) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RepairOrders frame = new RepairOrders();
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
	public RepairOrders() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 604, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
	}

}
