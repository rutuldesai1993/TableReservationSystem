//package assignment2;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TimerTask;
import java.lang.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class Client_array extends Thread {

	private static String uname;
	static ArrayList<Integer> cart = new ArrayList<Integer>();
	public static int[] Reservations = new int[6];
	
	public static Timer tm;
	
	
	private JFrame frame;
	private JPanel UserLoginPage;
	private JPanel ActivateNewAccount;
	private JPanel Homepage;
	private JPanel Reservation;
	private JPanel Cancellation;
	private JPanel Admin;

	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField UserTxt;

	private Socket socket;

	private ObjectOutputStream out;
	private ObjectInputStream in;
	private JTextField Reservation1Txt;
	private JTextField Table1Txt;
	private JTextField Date1Txt;
	private JTextField Reservation2Txt;
	private JTextField Table2Txt;
	private JTextField Date2Txt;
	private JTextField Reservation3Txt;
	private JTextField Table3Txt;
	private JTextField Date3Txt;
	private JTextField TimerTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		   System.out.println("Start: "+startTime);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client_array window = new Client_array();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		long endTime = System.currentTimeMillis();
		   System.out.println("End: "+endTime);
	}



	/**
	 * Create the application.
	 */
	public Client_array() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		//connectToServer();


		frame = new JFrame();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setExtendedState(frame.getExtendedState() | frame.MAXIMIZED_BOTH);
		frame.setBounds(100, 100, screenSize.width, screenSize.height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));



		// ********************User Login Page***************************

		final JPanel UserLoginPage = new JPanel();
		frame.getContentPane().add(UserLoginPage, "name_260729800728435");
		UserLoginPage.setLayout(null);
		UserLoginPage.setVisible(true);

		final JPanel ActivateNewAccount = new JPanel();
		frame.getContentPane().add(ActivateNewAccount, "name_260741077107088");
		ActivateNewAccount.setLayout(null);
		ActivateNewAccount.setVisible(false);

		final JPanel Homepage = new JPanel();
		frame.getContentPane().add(Homepage, "name_260742741443310");
		Homepage.setLayout(null);
		Homepage.setVisible(false);

		final JPanel Reservation = new JPanel();
		frame.getContentPane().add(Reservation, "name_260744278955576");
		Reservation.setLayout(null);
		Reservation.setVisible(false);

		final JPanel Cancellation = new JPanel();
		frame.getContentPane().add(Cancellation, "name_260746045100758");
		Cancellation.setLayout(null);
		Cancellation.setVisible(false);

		final JPanel Admin = new JPanel();
		frame.getContentPane().add(Admin, "name_260747790170380");
		Admin.setLayout(null);
		Admin.setVisible(false);

		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setBounds(543, 231, 118, 20);
		UserLoginPage.add(lblUserName);

		JLabel labelUsername = new JLabel("Welcome");
		labelUsername.setHorizontalAlignment(SwingConstants.CENTER);

		labelUsername.setBounds(173, 44, 195, 27);
		Reservation.add(labelUsername);

		JLabel lblWelcome = new JLabel("WELCOME!!!");

		lblWelcome.setBounds(337, 13, 297, 130);
		Homepage.add(lblWelcome);

		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(543, 310, 69, 20);
		UserLoginPage.add(lblNewLabel_1);
		
		JTextField UserNameTxt = new JTextField();
		UserNameTxt.setBounds(792, 225, 146, 26);
		UserLoginPage.add(UserNameTxt);
		UserNameTxt.setColumns(10);

		JPasswordField passwordField = new JPasswordField();
		passwordField.setBounds(792, 307, 146, 26);
		UserLoginPage.add(passwordField);

		
		Table1Txt = new JTextField();
		Table1Txt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Table1Txt.setBounds(643, 219, 168, 52);
		Homepage.add(Table1Txt);
		Table1Txt.setColumns(10);
		
		Table2Txt = new JTextField();
		Table2Txt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Table2Txt.setColumns(10);
		Table2Txt.setBounds(889, 219, 168, 52);
		Homepage.add(Table2Txt);
		
		Table3Txt = new JTextField();
		Table3Txt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Table3Txt.setColumns(10);
		Table3Txt.setBounds(1126, 219, 168, 52);
		Homepage.add(Table3Txt);
		
		Date1Txt = new JTextField();
		Date1Txt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Date1Txt.setBounds(643, 157, 168, 52);
		Homepage.add(Date1Txt);
		Date1Txt.setColumns(10);
		
		Date2Txt = new JTextField();
		Date2Txt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Date2Txt.setColumns(10);
		Date2Txt.setBounds(889, 157, 168, 52);
		Homepage.add(Date2Txt);
		
		Date3Txt = new JTextField();
		Date3Txt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Date3Txt.setColumns(10);
		Date3Txt.setBounds(1126, 155, 168, 52);
		Homepage.add(Date3Txt);
		
		JButton btnNewUser = new JButton("New user?");
		btnNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ActivateNewAccount.setVisible(true);
				UserNameTxt.setText(null);
				passwordField.setText(null);
				UserLoginPage.setVisible(false);
			}
		});
		btnNewUser.setBounds(672, 540, 115, 29);
		UserLoginPage.add(btnNewUser);

		

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				uname = UserNameTxt.getText().trim();
				String pwd = new String(passwordField.getPassword());
				

				boolean match = false;
				connectToServer();
				try {
					out.writeObject(3);
					out.flush();
					out.writeObject(uname);
					out.flush();
					out.writeObject(pwd);
					out.flush();
					match = (boolean) in.readObject();
					
				} catch (IOException | ClassNotFoundException ex) {
					System.out.println("Error while sending data to server");
				}
				
				
				if(match == true) {
					
					connectToServer();
					try {
						out.writeObject(12);
						out.flush();
						out.writeObject(uname);
						out.flush();
						Reservations = (int[]) in.readObject();
						for(int i = 0; i < Reservations.length; i++) {
							System.out.println(Reservations[i]);
						}
				
						
					} catch (IOException | ClassNotFoundException ex) {
						System.out.println("Error while sending data to server");
					}
					
					Table1Txt.setText(Integer.toString(Reservations[0]));
					Table2Txt.setText(Integer.toString(Reservations[2]));
					Table3Txt.setText(Integer.toString(Reservations[4]));
					Date1Txt.setText(Integer.toString(Reservations[1]));
					Date2Txt.setText(Integer.toString(Reservations[3]));
					Date3Txt.setText(Integer.toString(Reservations[5]));
					
					
					Homepage.setVisible(true);
					UserNameTxt.setText(null);
					passwordField.setText(null);
					UserLoginPage.setVisible(false);
				}
				else {
					JOptionPane.showMessageDialog(null,"Invalid Login credentials OR User already logged in...Please try again!");
				}

			}
		});
		btnSubmit.setBounds(672, 410, 115, 29);
		UserLoginPage.add(btnSubmit);


		// ********************Activate New Account********************

		JLabel lblNewLabel = new JLabel("User Name");
		lblNewLabel.setBounds(598, 188, 119, 20);
		ActivateNewAccount.add(lblNewLabel);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(598, 394, 119, 20);
		ActivateNewAccount.add(lblPassword);

		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setBounds(598, 474, 146, 20);
		ActivateNewAccount.add(lblConfirmPassword);

		JTextField textFieldUserName = new JTextField();
		textFieldUserName.setBounds(819, 182, 146, 26);
		ActivateNewAccount.add(textFieldUserName);
		textFieldUserName.setColumns(10);

		JPasswordField ActivateNewPasswordField = new JPasswordField();
		ActivateNewPasswordField.setBounds(819, 391, 146, 26);
		ActivateNewAccount.add(ActivateNewPasswordField);

		JPasswordField passwordConfirm = new JPasswordField();
		passwordConfirm.setBounds(819, 471, 146, 26);
		ActivateNewAccount.add(passwordConfirm);

		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(598, 252, 119, 20);
		ActivateNewAccount.add(lblFirstName);

		JTextField textFieldFirstName = new JTextField();
		textFieldFirstName.setColumns(10);
		textFieldFirstName.setBounds(819, 249, 146, 26);
		ActivateNewAccount.add(textFieldFirstName);

		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(598, 321, 119, 20);
		ActivateNewAccount.add(lblLastName);

		JTextField textField_LastName = new JTextField();
		textField_LastName.setColumns(10);
		textField_LastName.setBounds(819, 318, 146, 26);
		ActivateNewAccount.add(textField_LastName);

		JLabel lblEmailId = new JLabel("Email ID");
		lblEmailId.setBounds(598, 121, 119, 20);
		ActivateNewAccount.add(lblEmailId);

		JTextField textFieldEmailID = new JTextField();
		textFieldEmailID.setColumns(10);
		textFieldEmailID.setBounds(819, 118, 146, 26);
		ActivateNewAccount.add(textFieldEmailID);

		JLabel lblShouldBeBetween = new JLabel("Should be between 6-12 characters");
		lblShouldBeBetween.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lblShouldBeBetween.setBounds(1022, 188, 281, 20);
		ActivateNewAccount.add(lblShouldBeBetween);

		JLabel label = new JLabel("Should be between 6-12 characters");
		label.setFont(new Font("Tahoma", Font.ITALIC, 13));
		label.setBounds(1022, 394, 281, 20);
		ActivateNewAccount.add(label);

		JButton btnSignup = new JButton("SignUp");
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username;
				String password;
				String ConfPassword;
				String fname = textFieldFirstName.getText().trim();
				String lname = textField_LastName.getText().trim();
				String email = textFieldEmailID.getText().trim();
				username = textFieldUserName.getText().trim();
				int userNameLength = username.length();
				password = new String(ActivateNewPasswordField.getPassword());
				int passwordLength = password.length();
				ConfPassword = new String(passwordConfirm.getPassword());
				boolean usernameAvailable = false;
				
				connectToServer();
				try {
					out.writeObject(10);
					out.flush();
					out.writeObject(username);
					out.flush();
					
					usernameAvailable = (boolean) in.readObject();
					
				} catch (IOException | ClassNotFoundException ex) {
					System.out.println("Error while sending data to server");
				}
				
				
				

				if (userNameLength >= 6 && userNameLength <= 12) {

					if (password.equals(ConfPassword)&&userNameLength >= 6 && userNameLength <= 12) {

						if(usernameAvailable == true) {
							
							connectToServer();
							try {
								out.writeObject(2);
								out.flush();
								out.writeObject(email);
								out.flush();
								out.writeObject(username);
								out.flush();
								out.writeObject(fname);
								out.flush();
								out.writeObject(lname);
								out.flush();
								out.writeObject(password);
								out.flush();								
							} catch (IOException ex) {
								System.out.println("Error while sending data to server");
							}
							uname = username;
							ActivateNewAccount.setVisible(false);
							
							
							
							textFieldFirstName.setText(null);
							textField_LastName.setText(null);
							textFieldEmailID.setText(null);
							textFieldUserName.setText(null);
							ActivateNewPasswordField.setText(null);
							passwordConfirm.setText(null);
							UserLoginPage.setVisible(true);
							
							JOptionPane.showMessageDialog(null,"User account created. Please log in");
						}
						else {
							JOptionPane.showMessageDialog(null,"Username not available. Please try a different one!");
						}
	
						}

						
					else {
						JOptionPane.showMessageDialog(null,
								"Passwords do not match OR password length is not in between 6-12 characters. Please try again!");
					}

				} else {
					JOptionPane.showMessageDialog(null, "Username should be between 6-12 characters");
				}
				
				
			}

		});
		btnSignup.setBounds(708, 550, 115, 29);
		ActivateNewAccount.add(btnSignup);

		// ************************HomePage*********************

		JLabel avail_1 = new JLabel("");
		avail_1.setHorizontalAlignment(SwingConstants.LEFT);
		avail_1.setBounds(115, 667, 97, 51);
		Homepage.add(avail_1);

		JLabel avail_2 = new JLabel("");
		avail_2.setHorizontalAlignment(SwingConstants.LEFT);
		avail_2.setBounds(240, 667, 107, 51);
		Homepage.add(avail_2);

		JLabel avail_3 = new JLabel("");
		avail_3.setHorizontalAlignment(SwingConstants.LEFT);
		avail_3.setBounds(359, 667, 97, 51);
		Homepage.add(avail_3);

		JLabel avail_4 = new JLabel("");
		avail_4.setHorizontalAlignment(SwingConstants.LEFT);
		avail_4.setBounds(489, 667, 88, 51);
		Homepage.add(avail_4);

		JLabel avail_5 = new JLabel("");
		avail_5.setHorizontalAlignment(SwingConstants.LEFT);
		avail_5.setBounds(604, 667, 88, 51);
		Homepage.add(avail_5);

		JLabel avail_6 = new JLabel("");
		avail_6.setHorizontalAlignment(SwingConstants.LEFT);
		avail_6.setBounds(723, 667, 88, 51);
		Homepage.add(avail_6);

		JLabel avail_7 = new JLabel("");
		avail_7.setHorizontalAlignment(SwingConstants.LEFT);
		avail_7.setBounds(842, 667, 97, 51);
		Homepage.add(avail_7);

		JLabel avail_8 = new JLabel("");
		avail_8.setHorizontalAlignment(SwingConstants.LEFT);
		avail_8.setBounds(987, 667, 88, 51);
		Homepage.add(avail_8);

		JLabel avail_9 = new JLabel("");
		avail_9.setHorizontalAlignment(SwingConstants.LEFT);
		avail_9.setBounds(1144, 667, 87, 51);
		Homepage.add(avail_9);

		JLabel avail_10 = new JLabel("");
		avail_10.setHorizontalAlignment(SwingConstants.LEFT);
		avail_10.setBounds(1301, 667, 80, 51);
		Homepage.add(avail_10);

		JLabel avail_11 = new JLabel("");
		avail_11.setHorizontalAlignment(SwingConstants.LEFT);
		avail_11.setBounds(1422, 667, 80, 51);
		Homepage.add(avail_11);

		JLabel avail_12 = new JLabel("");
		avail_12.setHorizontalAlignment(SwingConstants.LEFT);
		avail_12.setBounds(1547, 667, 88, 51);
		Homepage.add(avail_12);
		
		JComboBox HomeDatecomboBox = new JComboBox();
		for (int i = 1; i <= 31; i++) {
			HomeDatecomboBox.addItem(i);
		}
		HomeDatecomboBox.setBounds(903, 448, 36, 26);
		Homepage.add(HomeDatecomboBox);
		
		JButton btnReserve = new JButton("Reserve");
		btnReserve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Homepage.setVisible(false);
				
				avail_1.setText(null);
				avail_2.setText(null);
				avail_3.setText(null);
				avail_4.setText(null);
				avail_5.setText(null);
				avail_6.setText(null);
				avail_7.setText(null);
				avail_8.setText(null);
				avail_9.setText(null);
				avail_10.setText(null);
				avail_11.setText(null);
				avail_12.setText(null);
				HomeDatecomboBox.setSelectedItem(1);
				
				
				
				
				Reservation.setVisible(true);
				
			}
		});
		btnReserve.setBounds(600, 316, 115, 29);
		Homepage.add(btnReserve);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Cancellation.setVisible(true);
				avail_1.setText(null);
				avail_2.setText(null);
				avail_3.setText(null);
				avail_4.setText(null);
				avail_5.setText(null);
				avail_6.setText(null);
				avail_7.setText(null);
				avail_8.setText(null);
				avail_9.setText(null);
				avail_10.setText(null);
				avail_11.setText(null);
				avail_12.setText(null);
				Homepage.setVisible(false);

			}
		});
		btnCancel.setBounds(1116, 316, 115, 29);
		Homepage.add(btnCancel);

		

		JButton btnLogoff = new JButton("Log Off");
		btnLogoff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserLoginPage.setVisible(true);
				avail_1.setText(null);
				avail_2.setText(null);
				avail_3.setText(null);
				avail_4.setText(null);
				avail_5.setText(null);
				avail_6.setText(null);
				avail_7.setText(null);
				avail_8.setText(null);
				avail_9.setText(null);
				avail_10.setText(null);
				avail_11.setText(null);
				avail_12.setText(null);
				HomeDatecomboBox.setSelectedItem(1);
				
				for(int k = 0; k < cart.size(); k++) {
					int tableno = k/31 + 1;
					int resDate = k%31 + 1;
					
					connectToServer();
					try {
						out.writeObject(9);
						out.flush();
						out.writeObject(resDate);
						out.flush();
						out.writeObject(tableno);
						out.flush();
					
					} catch (IOException ex) {
						System.out.println("Error while sending data to server");
					}
				}
				cart.clear();
				
				connectToServer();
				try {
					out.writeObject(11);
					out.flush();
					out.writeObject(uname);
					out.flush();
				
				} catch (IOException ex) {
					System.out.println("Error while sending data to server");
				}
				
				Homepage.setVisible(false);
			}
		});
		btnLogoff.setBounds(1288, 30, 115, 29);
		Homepage.add(btnLogoff);

		JLabel table1_lbl = new JLabel("Table 1");
		table1_lbl.setBounds(139, 599, 52, 29);
		Homepage.add(table1_lbl);

		JLabel table2_lbl = new JLabel("Table 2");
		table2_lbl.setBounds(263, 599, 52, 29);
		Homepage.add(table2_lbl);

		JLabel table3_lbl = new JLabel("Table 3");
		table3_lbl.setBounds(390, 599, 52, 29);
		Homepage.add(table3_lbl);
		

		JLabel table4_lbl = new JLabel("Table 4");
		table4_lbl.setBounds(515, 599, 52, 29);
		Homepage.add(table4_lbl);

		JLabel table5_lbl = new JLabel("Table 5");
		table5_lbl.setBounds(632, 599, 52, 29);
		Homepage.add(table5_lbl);

		JLabel table6_lbl = new JLabel("Table 6");
		table6_lbl.setBounds(749, 599, 52, 29);
		Homepage.add(table6_lbl);

		JLabel table7_lbl = new JLabel("Table 7");
		table7_lbl.setBounds(863, 599, 52, 29);
		Homepage.add(table7_lbl);

		JLabel table8_lbl = new JLabel("Table 8");
		table8_lbl.setBounds(1012, 599, 52, 29);
		Homepage.add(table8_lbl);

		JLabel table9_lbl = new JLabel("Table 9");
		table9_lbl.setBounds(1162, 599, 52, 29);
		Homepage.add(table9_lbl);

		JLabel table10_lbl = new JLabel("Table 10");
		table10_lbl.setBounds(1317, 599, 52, 29);
		Homepage.add(table10_lbl);

		JLabel table11_lbl = new JLabel("Table 11");
		table11_lbl.setBounds(1438, 599, 52, 29);
		Homepage.add(table11_lbl);

		JLabel table12_lbl = new JLabel("Table 12");
		table12_lbl.setBounds(1567, 599, 52, 29);
		Homepage.add(table12_lbl);

		JButton btnShowTablesFor = new JButton("Check availability");
		btnShowTablesFor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int dateAvail = (int) HomeDatecomboBox.getSelectedItem();
				int availability[] = new int[12];
				
				connectToServer();
				try {
					out.writeObject(4);
					out.flush();
					out.writeObject(dateAvail);
					out.flush();
					availability = (int[]) in.readObject();
					
				} catch (IOException | ClassNotFoundException ex) {
					System.out.println("Error while sending data to server");
				}
				

				if (availability[0] == 0) {
					avail_1.setText("Reserved");
					avail_1.setForeground(Color.RED);
				} else {
					avail_1.setText("Available");
					avail_1.setForeground(Color.GREEN);
				}
				if (availability[1] == 0) {
					avail_2.setText("Reserved");
					avail_2.setForeground(Color.RED);
				} else {
					avail_2.setText("Available");
					avail_2.setForeground(Color.GREEN);
				}
				if (availability[2] == 0) {
					avail_3.setText("Reserved");
					avail_3.setForeground(Color.RED);
				} else {
					avail_3.setText("Available");
					avail_3.setForeground(Color.GREEN);
				}
				if (availability[3] == 0) {
					avail_4.setText("Reserved");
					avail_4.setForeground(Color.RED);
				} else {
					avail_4.setText("Available");
					avail_4.setForeground(Color.GREEN);
				}
				if (availability[4] == 0) {
					avail_5.setText("Reserved");
					avail_5.setForeground(Color.RED);
				} else {
					avail_5.setText("Available");
					avail_5.setForeground(Color.GREEN);
				}
				if (availability[5] == 0) {
					avail_6.setText("Reserved");
					avail_6.setForeground(Color.RED);
				} else {
					avail_6.setText("Available");
					avail_6.setForeground(Color.GREEN);
				}
				if (availability[6] == 0) {
					avail_7.setText("Reserved");
					avail_7.setForeground(Color.RED);
				} else {
					avail_7.setText("Available");
					avail_7.setForeground(Color.GREEN);
				}
				if (availability[7] == 0) {
					avail_8.setText("Reserved");
					avail_8.setForeground(Color.RED);
				} else {
					avail_8.setText("Available");
					avail_8.setForeground(Color.GREEN);
				}
				if (availability[8] == 0) {
					avail_9.setText("Reserved");
					avail_9.setForeground(Color.RED);
				} else {
					avail_9.setText("Available");
					avail_9.setForeground(Color.GREEN);
				}
				if (availability[9] == 0) {
					avail_10.setText("Reserved");
					avail_10.setForeground(Color.RED);
				} else {
					avail_10.setText("Available");
					avail_10.setForeground(Color.GREEN);
				}
				if (availability[10] == 0) {
					avail_11.setText("Reserved");
					avail_11.setForeground(Color.RED);
				} else {
					avail_11.setText("Available");
					avail_11.setForeground(Color.GREEN);
				}
				if (availability[11] == 0) {
					avail_12.setText("Reserved");
					avail_12.setForeground(Color.RED);
				} else {
					avail_12.setText("Available");
					avail_12.setForeground(Color.GREEN);
				}

			}
		});
		btnShowTablesFor.setBounds(827, 514, 189, 29);
		Homepage.add(btnShowTablesFor);

		// ******************************Reservation****************************************
		JComboBox ReservationComboBox = new JComboBox();
		for (int i = 1; i <= 31; i++) {
			ReservationComboBox.addItem(i);
		}


		ReservationComboBox.setBounds(488, 385, 36, 26);
		Reservation.add(ReservationComboBox);

		JComboBox TableComboBox = new JComboBox();
		for (int i = 1; i <= 12; i++) {
			TableComboBox.addItem(i);
		}

		TableComboBox.setBounds(1007, 385, 36, 26);
		Reservation.add(TableComboBox);

		

		JLabel ReservationScreenLbl = new JLabel("Reservation Screen");
		ReservationScreenLbl.setHorizontalAlignment(SwingConstants.CENTER);
		ReservationScreenLbl.setFont(new Font("Tahoma", Font.ITALIC, 18));
		ReservationScreenLbl.setBounds(732, 44, 449, 58);
		Reservation.add(ReservationScreenLbl);

		JLabel TableNumberLbl = new JLabel("Select Table number to be booked");
		TableNumberLbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		TableNumberLbl.setHorizontalAlignment(SwingConstants.CENTER);
		TableNumberLbl.setBounds(831, 308, 368, 58);
		Reservation.add(TableNumberLbl);

		JLabel DateSelectionLbl = new JLabel("Select October date to reserve table");
		DateSelectionLbl.setHorizontalAlignment(SwingConstants.CENTER);
		DateSelectionLbl.setFont(new Font("Tahoma", Font.ITALIC, 18));
		DateSelectionLbl.setBounds(325, 314, 335, 44);
		Reservation.add(DateSelectionLbl);

		JSeparator separator = new JSeparator();
		separator.setBounds(291, 254, 996, 2);
		Reservation.add(separator);

		JLabel lblMyCart = new JLabel("My Cart");
		lblMyCart.setHorizontalAlignment(SwingConstants.CENTER);
		lblMyCart.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblMyCart.setBounds(263, 140, 335, 74);
		Reservation.add(lblMyCart);

		JLabel cartLabel = new JLabel("0 Reservations");
		cartLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cartLabel.setFont(new Font("Tahoma", Font.ITALIC, 18));
		cartLabel.setBounds(610, 140, 335, 74);
		Reservation.add(cartLabel);

		JButton deleteCartButton = new JButton("Delete Cart");
		deleteCartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				for(int k = 0; k < cart.size(); k++) {
					int tableno = cart.get(k)/31 + 1;
					int resDate = cart.get(k)%31 + 1;
					
					connectToServer();
					try {
						out.writeObject(9);
						out.flush();
						out.writeObject(resDate);
						out.flush();
						out.writeObject(tableno);
						out.flush();
					
					} catch (IOException ex) {
						System.out.println("Error while sending data to server");
					}
				}
				cart.clear();
				JOptionPane.showMessageDialog(null, "Deleted cart");
				cartLabel.setText(cart.size() + " Reservations");
				ReservationComboBox.setSelectedItem(1);
				TableComboBox.setSelectedItem(1);
			}
		});
		deleteCartButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		deleteCartButton.setBounds(1019, 197, 216, 44);
		Reservation.add(deleteCartButton);
		
		
		

		tm = new Timer(900, new ActionListener() {
		int i=30;
		@Override
		public void actionPerformed(ActionEvent e) {

			TimerTxt.setText(Integer.toString(i));
		i--;
		if(i<0) {	
			i = 30;
			tm.stop();
			int messagecount = 0;
			for(int k = 0; k < cart.size(); k++) {
				
				int tableno = cart.get(k)/31 + 1;
				int resDate = cart.get(k)%31 + 1;
				
				connectToServer();
				try {
					out.writeObject(9);
					out.flush();
					out.writeObject(resDate);
					out.flush();
					out.writeObject(tableno);
					out.flush();
				
				} catch (IOException ex) {
					System.out.println("Error while sending data to server");
				}
				if(messagecount == 0) {
					JOptionPane.showMessageDialog(null, "Deleted cart as session timed out");
					messagecount++;
				}
				
				
			}
			cart.clear();
			cartLabel.setText(cart.size() + " Reservations");
		
			ReservationComboBox.setSelectedItem(1);
			TableComboBox.setSelectedItem(1);
			
		}
		}
		});
		
		JButton btnAddToCart = new JButton("Add To Cart");
		btnAddToCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				tm.start();
				
				
//				if(TimerTxt.getText().equals("30")) {
//					TimerTxt.setText("30");
//				}
				

				int resDate = (int) ReservationComboBox.getSelectedItem();
				int tableno = (int) TableComboBox.getSelectedItem();
				int row = (31 * (tableno - 1)) + (resDate - 1);
				int available = 0;
				int count = 3;
				
				connectToServer();
				try {
					out.writeObject(6);
					out.flush();
					out.writeObject(resDate);
					out.flush();
					out.writeObject(tableno);
					out.flush();
					available = (int) in.readObject();
				} catch (IOException | ClassNotFoundException ex) {
					System.out.println("Error while sending data to server");
				}
				
				
				connectToServer();
				try {
					out.writeObject(5);
					out.flush();
					out.writeObject(uname);
					out.flush();
					count = (int) in.readObject();
				} catch (IOException | ClassNotFoundException ex) {
					System.out.println("Error while sending data to server");
				}
				
				
				if(available == 0) {
					JOptionPane.showMessageDialog(null, "The table is not displayed as available for the selected date. Please try a different combination");
				}
				else {
					if(count == 3) {
						JOptionPane.showMessageDialog(null, "You can only reserve 3 transaction for the month");
					}
					else {
						
						connectToServer();
						try {
							out.writeObject(7);
							out.flush();
							out.writeObject(resDate);
							out.flush();
							out.writeObject(tableno);
							out.flush();
							out.writeObject(uname);
							out.flush();
						} catch (IOException ex) {
							System.out.println("Error while sending data to server");
						}
						cart.add(row);
						cartLabel.setText(cart.size() + " Reservations");
						ReservationComboBox.setSelectedItem(1);
						TableComboBox.setSelectedItem(1);
						JOptionPane.showMessageDialog(null,"Added to cart. You have 30 seconds to confirm reservations");
					}
					
				}
			}
		});
		btnAddToCart.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnAddToCart.setBounds(1019, 518, 216, 44);
		Reservation.add(btnAddToCart);

		JButton ConfirmCartButton = new JButton("Confirm Cart");
		ConfirmCartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cart.clear();
				JOptionPane.showMessageDialog(null, "Reserved");
				cartLabel.setText(cart.size() + " Reservations");
				ReservationComboBox.setSelectedItem(1);
				TableComboBox.setSelectedItem(1);
			}
		});
		ConfirmCartButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		ConfirmCartButton.setBounds(1019, 140, 216, 44);
		Reservation.add(ConfirmCartButton);

		JButton CancelBtn = new JButton("Cancel");
		CancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Homepage.setVisible(true);
				ReservationComboBox.setSelectedItem(1);
				TableComboBox.setSelectedItem(1);
				cartLabel.setText("0 Reservations");
				
				for(int k = 0; k < cart.size(); k++) {
					int tableno = k/31 + 1;
					int resDate = k%31 + 1;
					
					connectToServer();
					try {
						out.writeObject(9);
						out.flush();
						out.writeObject(resDate);
						out.flush();
						out.writeObject(tableno);
						out.flush();
					
					} catch (IOException ex) {
						System.out.println("Error while sending data to server");
					}
				}
				cart.clear();
				Reservation.setVisible(false);
			}
		});

		CancelBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
		CancelBtn.setBounds(263, 518, 216, 44);
		Reservation.add(CancelBtn);
		
		JButton ReservationLogOffButton = new JButton("Log Off");
		ReservationLogOffButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserLoginPage.setVisible(true);
				ReservationComboBox.setSelectedItem(1);
				TableComboBox.setSelectedItem(1);
				cartLabel.setText("0 Reservations");
				
				for(int k = 0; k < cart.size(); k++) {
					int tableno = k/31 + 1;
					int resDate = k%31 + 1;
					
					connectToServer();
					try {
						out.writeObject(9);
						out.flush();
						out.writeObject(resDate);
						out.flush();
						out.writeObject(tableno);
						out.flush();
					
					} catch (IOException ex) {
						System.out.println("Error while sending data to server");
					}
				}
				cart.clear();
				
				connectToServer();
				try {
					out.writeObject(11);
					out.flush();
					out.writeObject(uname);
					out.flush();
				
				} catch (IOException ex) {
					System.out.println("Error while sending data to server");
				}
				
				Reservation.setVisible(false);
			}

		});
		ReservationLogOffButton.setForeground(new Color(102, 0, 255));
		ReservationLogOffButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		ReservationLogOffButton.setBounds(1479, 62, 97, 25);
		Reservation.add(ReservationLogOffButton);

		JLabel lblSelectTheDate = new JLabel("Select the date to check availability");
		lblSelectTheDate.setBounds(827, 390, 387, 46);
		Homepage.add(lblSelectTheDate);
		
		JLabel lblMyReservations = new JLabel("My Reservations");
		lblMyReservations.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMyReservations.setBounds(263, 178, 168, 51);
		Homepage.add(lblMyReservations);
		
		JLabel lblNewLabel_2 = new JLabel("Date");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(489, 156, 97, 52);
		Homepage.add(lblNewLabel_2);
		
		JLabel lblTable = new JLabel("Table");
		lblTable.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTable.setBounds(489, 219, 97, 52);
		Homepage.add(lblTable);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				connectToServer();
				try {
					out.writeObject(12);
					out.flush();
					out.writeObject(uname);
					out.flush();
					Reservations = (int[]) in.readObject();
					for(int i = 0; i < Reservations.length; i++) {
						System.out.println(Reservations[i]);
					}
			
					
				} catch (IOException | ClassNotFoundException ex) {
					System.out.println("Error while sending data to server");
				}
				
				Table1Txt.setText(Integer.toString(Reservations[0]));
				Table2Txt.setText(Integer.toString(Reservations[2]));
				Table3Txt.setText(Integer.toString(Reservations[4]));
				Date1Txt.setText(Integer.toString(Reservations[1]));
				Date2Txt.setText(Integer.toString(Reservations[3]));
				Date3Txt.setText(Integer.toString(Reservations[5]));
				
			}
		});
		btnRefresh.setBounds(1354, 179, 107, 52);
		Homepage.add(btnRefresh);
		
		

		JButton ReservationBtnReserve = new JButton("Reserve!");
		ReservationBtnReserve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					
				int resDate = (int) ReservationComboBox.getSelectedItem();
				int tableno = (int) TableComboBox.getSelectedItem();
				int row = (31 * (tableno - 1)) + (resDate - 1);
				int available = 0;
				int count = 3;
				
				connectToServer();
				try {
					out.writeObject(6);
					out.flush();
					out.writeObject(resDate);
					out.flush();
					out.writeObject(tableno);
					out.flush();
					available = (int) in.readObject();
				} catch (IOException | ClassNotFoundException ex) {
					System.out.println("Error while sending data to server");
				}
				
				
				connectToServer();
				try {
					out.writeObject(5);
					out.flush();
					out.writeObject(uname);
					out.flush();
					count = (int) in.readObject();
				} catch (IOException | ClassNotFoundException ex) {
					System.out.println("Error while sending data to server");
				}
				
				
				if(available == 0) {
					JOptionPane.showMessageDialog(null, "Table not available for the selected date. Please try a different combination");
				}
				else {
					if(count == 3) {
						JOptionPane.showMessageDialog(null, "You can only reserve 3 transaction for the month");
					}
					else {
						
						connectToServer();
						try {
							out.writeObject(7);
							out.flush();
							out.writeObject(resDate);
							out.flush();
							out.writeObject(tableno);
							out.flush();
							out.writeObject(uname);
							out.flush();
						} catch (IOException ex) {
							System.out.println("Error while sending data to server");
						}
						
						JOptionPane.showMessageDialog(null, "Table no "+ tableno+ " is booked for "+resDate+" October, 2017");
						ReservationComboBox.setSelectedItem(1);
						TableComboBox.setSelectedItem(1);
						cartLabel.setText("0 Reservations");
					}
				}
			
			}
		});
		ReservationBtnReserve.setFont(new Font("Tahoma", Font.BOLD, 18));
		ReservationBtnReserve.setBounds(649, 518, 216, 44);
		Reservation.add(ReservationBtnReserve);
		
		TimerTxt = new JTextField();
		TimerTxt.setHorizontalAlignment(SwingConstants.CENTER);
		TimerTxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		TimerTxt.setEditable(false);
		TimerTxt.setBounds(474, 89, 136, 38);
		Reservation.add(TimerTxt);
		TimerTxt.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Session Times out in:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(453, 37, 175, 40);
		Reservation.add(lblNewLabel_3);
		
		

		// *******************Cancellation**********************

		

		JLabel CancellationScreenLbl = new JLabel("Cancellation Screen");
		CancellationScreenLbl.setHorizontalAlignment(SwingConstants.CENTER);
		CancellationScreenLbl.setFont(new Font("Tahoma", Font.ITALIC, 18));
		CancellationScreenLbl.setBounds(732, 44, 449, 58);
		Cancellation.add(CancellationScreenLbl);

		JSeparator CancellationSeparator = new JSeparator();
		CancellationSeparator.setBounds(291, 254, 996, 2);
		Cancellation.add(CancellationSeparator);

		JComboBox CanTableComboBox = new JComboBox();

		CanTableComboBox.setBounds(1099, 381, 36, 26);
		Cancellation.add(CanTableComboBox);
		for (int i = 1; i <= 12; i++) {
			CanTableComboBox.addItem(i);
		}
		JComboBox CanDateComboBox = new JComboBox();
		for (int i = 1; i <= 31; i++) {
			CanDateComboBox.addItem(i);
		}
		CanDateComboBox.setBounds(532, 371, 36, 26);
		Cancellation.add(CanDateComboBox);

//		JLabel ReservationsLabel = new JLabel("");
//		ReservationsLabel.setHorizontalAlignment(SwingConstants.CENTER);
//		ReservationsLabel.setFont(new Font("Tahoma", Font.ITALIC, 18));
//		ReservationsLabel.setBounds(1089, 360, 335, 44);
//		Cancellation.add(ReservationsLabel);

		

		JLabel lblSelectOctoberDate = new JLabel("Select October date to cancel reservation");
		lblSelectOctoberDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectOctoberDate.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblSelectOctoberDate.setBounds(373, 291, 335, 44);
		Cancellation.add(lblSelectOctoberDate);

		JButton CancelReservationBtn = new JButton("Cancel a Reservation");
		CancelReservationBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int date = (int) CanDateComboBox.getSelectedItem();
				int tableno = (int) CanTableComboBox.getSelectedItem();
				int row = (31 * (tableno - 1)) + (date - 1);
				int booking = 0;
				
				connectToServer();
				try {
					out.writeObject(8);
					out.flush();
					out.writeObject(date);
					out.flush();
					out.writeObject(tableno);
					out.flush();
					out.writeObject(uname);
					out.flush();
					booking = (int) in.readObject();
				} catch (IOException | ClassNotFoundException ex) {
					System.out.println("Error while sending data to server");
				}
				
				
				if(booking == 0) {
					JOptionPane.showMessageDialog(null,"There is no reservation in your name. Please cancel only your reservations.");
				}
				else {
					
					connectToServer();
					try {
						out.writeObject(9);
						out.flush();
						out.writeObject(date);
						out.flush();
						out.writeObject(tableno);
						out.flush();
					} catch (IOException ex) {
						System.out.println("Error while sending data to server");
					}
					
					JOptionPane.showMessageDialog(null,"Reservation cancelled for table " + tableno + " on " + date + " October, 2017");
				}			
			}
		});
		CancelReservationBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
		CancelReservationBtn.setBounds(1002, 462, 249, 44);
		Cancellation.add(CancelReservationBtn);

		JLabel lblSelectTableNumber = new JLabel("Select table number to cancel");
		lblSelectTableNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectTableNumber.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblSelectTableNumber.setBounds(952, 291, 335, 44);
		Cancellation.add(lblSelectTableNumber);

		JLabel label_1 = new JLabel("Welcome " + uname);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(281, 60, 195, 27);
		Cancellation.add(label_1);
		
		JButton CancellationLogOffButton = new JButton("Log Off");
		CancellationLogOffButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserLoginPage.setVisible(true);
				CanTableComboBox.setSelectedItem(1);
				CanDateComboBox.setSelectedItem(1);
				
				for(int k = 0; k < cart.size(); k++) {
					int tableno = k/31 + 1;
					int resDate = k%31 + 1;
					
					connectToServer();
					try {
						out.writeObject(9);
						out.flush();
						out.writeObject(resDate);
						out.flush();
						out.writeObject(tableno);
						out.flush();
					
					} catch (IOException ex) {
						System.out.println("Error while sending data to server");
					}
				}
				cart.clear();
				
				connectToServer();
				try {
					out.writeObject(11);
					out.flush();
					out.writeObject(uname);
					out.flush();
				
				} catch (IOException ex) {
					System.out.println("Error while sending data to server");
				}
				
				
				Cancellation.setVisible(false);
			}
		});
		CancellationLogOffButton.setForeground(new Color(102, 0, 255));
		CancellationLogOffButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		CancellationLogOffButton.setBounds(1479, 62, 97, 25);
		Cancellation.add(CancellationLogOffButton);
		
		JButton CancellationCancelBtn = new JButton("Cancel");
		CancellationCancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Homepage.setVisible(true);
				CanTableComboBox.setSelectedItem(1);
				CanDateComboBox.setSelectedItem(1);
				Cancellation.setVisible(false);
			}
		});

		CancellationCancelBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
		CancellationCancelBtn.setBounds(430, 462, 216, 44);
		Cancellation.add(CancellationCancelBtn);

		
	}

	private void connectToServer() {
		System.out.println("Connecting to server");
		try {
			socket = new Socket(InetAddress.getByName("localhost"), 1360);
			out = new ObjectOutputStream(socket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(socket.getInputStream());
			
				} catch (Exception ex) {
			System.out.println("Error connecting to server. " + ex);
		}
		
		System.out.println("\nI/O Stream is ready.");
	}
}

class RemindTask extends TimerTask {
    public void run() {
//      System.out.println("Time's up!");
//      toolkit.beep();
      //timer.cancel(); //Not necessary because we call System.exit
//      System.exit(0); //Stops the AWT thread (and everything else)
    }
  }
