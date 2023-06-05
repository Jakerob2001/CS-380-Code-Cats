//imports
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JScrollPane;

/**
 * Class that creates a GUI to see inventory
 * from the employees view
 * @author Brayden Qualman, Jake Robinson
 */
public class Inventory extends JFrame {

	//global swing variables
	private JPanel contentPane;
	JTextArea partArea;
	JButton backButton;
	JButton refreshBtn;
	Connection con;

	/**
	 * Create the frame.
	 */
	public Inventory(Connection connection, User currentUser) {

		con = connection;

		//creating the pane
		setTitle("View Store Inventory");
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 675, 566);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		// text area to hold info
		partArea = new JTextArea();
		partArea.setEditable(false);
		partArea.setFont(new Font("Cambria", Font.PLAIN, 15));
		partArea.setBounds(10, 63, 639, 453);
		contentPane.add(partArea);
		updateTable(); // updating table upon startup


		backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		backButton.setBounds(109, 11, 89, 23);
		contentPane.add(backButton);

		refreshBtn = new JButton("Refresh");
		refreshBtn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		refreshBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTable();
			}
		});
		refreshBtn.setBounds(10, 11, 89, 23);
		contentPane.add(refreshBtn);

		JScrollPane scrollPane = new JScrollPane(partArea);
		scrollPane.setBounds(10, 63, 639, 453);
		contentPane.add(scrollPane);
	}

	/**
	 * Method that updates the table
	 */
	private void updateTable(){
		String query =
				"SELECT p.part_id, " +
						"p.part_name, " +
						"p.part_desc, " +
						"p.part_cat, " +
						"p.part_price, " +
						"pi.qty " +
						"FROM part p " +
						"JOIN part_inventory pi " +
						"ON p.part_id = pi.part_id;";

		try {
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(query);
			while(result.next()){
				partArea.append(result.getString("p.part_name") + " #" + result.getString("p.part_id")
						+ "\nqty on hand: x" + result.getString("pi.qty")
						+ " \n$" + result.getString("p.part_price")
						+ "\ntype: " + result.getString("p.part_cat")
						+ "\n" + result.getString("p.part_desc") + "\n\n");
			}
			partArea.setCaretPosition(0);
		} catch (Exception e) {
			System.out.println("exception " + e.getMessage());
		}
	}
}
