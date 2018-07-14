import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EtchedBorder;

public class Server_Admin {

	private JFrame ServerFrame;
	public static ArrayList<String> usernames = new ArrayList<String>();
	public static ArrayList<String> passwords = new ArrayList<String>();
	public static String table[] = new String[372];
	public static ArrayList<ArrayList<String>> user = new ArrayList<ArrayList<String>>();
	private JTable table_1;
	private JTable table_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Server_Admin window = new Server_Admin();
					window.ServerFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//*********************User Data Read*******************//
	public void UserDetailsRead() {
		try {
			Scanner lines = new Scanner(new File("C:\\Users\\s_U_m_O\\eclipse-workspace\\Server_520\\src\\User.txt"));

			int numberOfLines = 0;
			while (lines.hasNextLine()) {
				numberOfLines++;
				lines.nextLine();
			}
			lines.close();
			Scanner userDetails = new Scanner(
					new File("C:\\\\Users\\\\s_U_m_O\\\\eclipse-workspace\\\\Server_520\\\\src\\\\User.txt"));
			while (userDetails.hasNextLine()) {

				for (int i = 1; i <= numberOfLines / 5; i++) {
					ArrayList<String> details = new ArrayList<String>();

					details.add(userDetails.nextLine());
					details.add(userDetails.nextLine());
					details.add(userDetails.nextLine());
					details.add(userDetails.nextLine());
					details.add(userDetails.nextLine());
					user.add(details);
				}

			}
			userDetails.close();

		} catch (FileNotFoundException e) {
			System.out
					.println("User File not found");
		}

		// Storing Usernames and passwords
		for (int i = 0; i < user.size(); i++) {
			ArrayList<String> temp = new ArrayList<String>();
			temp = user.get(i);
			usernames.add(temp.get(3));
			passwords.add(temp.get(4));
		}
		

	}
//*********************Table Data Read*******************//
	public void tableDetailsRead() {
		String tableRow;
		try {
			Scanner tableDetails = new Scanner(
					new File("C:\\\\Users\\\\s_U_m_O\\\\eclipse-workspace\\\\Server_520\\\\src\\\\Table.txt"));
			while (tableDetails.hasNextLine()) {

				for (int i = 0; i < 372; i++) {
					tableRow = tableDetails.nextLine();
					table[i] = tableRow;

				}
			}
			tableDetails.close();

		} catch (FileNotFoundException ea) {
			System.out.println(
					"Table File not found");
		}
	}

	/**
	 * Create the application.
	 */
	public Server_Admin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		ServerFrame = new JFrame();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		ServerFrame.setExtendedState(ServerFrame.getExtendedState() | ServerFrame.MAXIMIZED_BOTH);
		ServerFrame.setBounds(100, 100, screenSize.width, screenSize.height);
		ServerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ServerFrame.getContentPane().setLayout(new CardLayout(0, 0));


		final JPanel ServerPage = new JPanel();
		ServerFrame.getContentPane().add(ServerPage, "name_260729800728435");
		ServerPage.setLayout(null);
		ServerPage.setVisible(true);
		
		JLabel ServerLabel = new JLabel("Server is running....");
		ServerLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		ServerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ServerLabel.setForeground(Color.GREEN);
		ServerLabel.setBounds(852, 31, 267, 58);
		ServerPage.add(ServerLabel);
		
		
		// **********************Admin Reports************************


				JLabel lblSelectUser = new JLabel("Enter username");
				lblSelectUser.setHorizontalAlignment(SwingConstants.CENTER);
				lblSelectUser.setFont(new Font("Tahoma", Font.BOLD, 17));
				lblSelectUser.setBounds(1067, 589, 235, 51);
				ServerPage.add(lblSelectUser);

				JComboBox DateComboBox = new JComboBox();
				for (int i = 1; i <= 31; i++) {
					DateComboBox.addItem(i);
				}

				DateComboBox.setBounds(1167, 252, 46, 26);
				ServerPage.add(DateComboBox);

				JTextField UserTxt = new JTextField();
				UserTxt.setBounds(1096, 653, 198, 51);
				ServerPage.add(UserTxt);
				UserTxt.setColumns(10);
				
				table_1 = new JTable();
				table_1.setColumnSelectionAllowed(true);
				table_1.setCellSelectionEnabled(true);
				table_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				table_1.setModel(new DefaultTableModel(
					new Object[][] {
						{null, null},
						{null, null},
						{null, null},
						{null, null},
						{null, null},
						{null, null},
						{null, null},
						{null, null},
						{null, null},
						{null, null},
						{null, null},
						{null, null},
					},
					new String[] {
						"Username", "Table"
					}
				) {
					Class[] columnTypes = new Class[] {
						String.class, Integer.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
				table_1.getColumnModel().getColumn(0).setPreferredWidth(384);
				table_1.getColumnModel().getColumn(0).setMinWidth(19);
				table_1.getColumnModel().getColumn(1).setPreferredWidth(406);
				table_1.setBounds(290, 188, 699, 192);
				ServerPage.add(table_1);

				JButton btnGenerateDateReport = new JButton("Generate Date Report");
				btnGenerateDateReport.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						tableDetailsRead();
						


						int date = DateComboBox.getSelectedIndex();
						
						for(int i = 1; i<=12; i++) {
							int row = (31 * (i - 1)) + (date + 1 - 1);
							String value = table[row];
							if(value.equals("0")) {
								value = "Not reserved";
							}
							else {
								value = "Reserved by "+value;
							}
							table_1.setValueAt(value, i-1, 0);
							
						}
						
						for(int i = 1; i <= 12; i++) {
							table_1.setValueAt(i, i-1, 1);
						}
						


						
					}
				});
				btnGenerateDateReport.setFont(new Font("Tahoma", Font.BOLD, 16));
				btnGenerateDateReport.setBounds(1067, 304, 242, 64);
				ServerPage.add(btnGenerateDateReport);

				JButton btnGenerateUserReport = new JButton("Generate User Report");
				btnGenerateUserReport.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						tableDetailsRead();
						
						String username = UserTxt.getText();
						int row [] = new int [3]; 
						int index = 0;
						for(int i = 0; i<table.length; i++) {
							if(table[i].equals(username)) {
								row[index] = i+1;
								index++;
							}
							
							}
						
						for(int i = 0; i<row.length; i++) {
							if(row[i] == 0) {
								row[i] = 400;
							}
						}
							
							for(int i = 0; i<row.length; i++) {
								if(row[i] != 400) {
									int tableno = ((row[i]-1)/31) + 1;
									int resDate = ((row[i]-1)%31) + 1;
									table_2.setValueAt(resDate, i, 0);
									table_2.setValueAt(tableno, i, 1);
								}
							
							
						}
						
						for(int i = 1; i <= 12; i++) {
							table_1.setValueAt(i, i-1, 1);
						}
					}
				});
				btnGenerateUserReport.setFont(new Font("Tahoma", Font.BOLD, 16));
				btnGenerateUserReport.setBounds(1074, 729, 242, 64);
				ServerPage.add(btnGenerateUserReport);

				JLabel lblSelectDate = new JLabel("Select Date");
				lblSelectDate.setHorizontalAlignment(SwingConstants.CENTER);
				lblSelectDate.setFont(new Font("Tahoma", Font.BOLD, 17));
				lblSelectDate.setBounds(1067, 188, 235, 51);
				ServerPage.add(lblSelectDate);
				
				JLabel lblUsernameTable = new JLabel("Username                                                                                Table");
				lblUsernameTable.setFont(new Font("Tahoma", Font.BOLD, 15));
				lblUsernameTable.setBounds(407, 157, 724, 31);
				ServerPage.add(lblUsernameTable);
				
				table_2 = new JTable();
				table_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				table_2.setModel(new DefaultTableModel(
					new Object[][] {
						{null, null},
						{null, null},
						{null, null},
					},
					new String[] {
						"Table", "Date"
					}
				) {
					Class[] columnTypes = new Class[] {
						Integer.class, Integer.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
				table_2.getColumnModel().getColumn(0).setPreferredWidth(376);
				table_2.getColumnModel().getColumn(1).setPreferredWidth(412);
				table_2.setBounds(304, 671, 685, 48);
				ServerPage.add(table_2);
				
				JLabel lblDateDate = new JLabel("Date                                                                                Table");
				lblDateDate.setFont(new Font("Tahoma", Font.BOLD, 15));
				lblDateDate.setBounds(417, 640, 724, 31);
				ServerPage.add(lblDateDate);
				
				

				
	}
}
