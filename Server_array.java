
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;


public class Server_array {

	public static ArrayList<String> usernames = new ArrayList<String>();
	public static ArrayList<String> passwords = new ArrayList<String>();
	public static String table[] = new String[372];
	public static ArrayList<ArrayList<String>> user = new ArrayList<ArrayList<String>>();
	public static ArrayList<String> loggedInUsers = new ArrayList<String>();
	
	
	private int connectionCount = 0;
	private ServerSocket serverSocket;
	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;

	private JFrame ServerFrame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Server_array window = new Server_array();
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
//New functions
	//*********************User Data Write*******************//
	public void UserDetailsWrite(String email, String username, String fname, String lname, String password) {
		//Addition of the user details in the arraylist
		
		ArrayList<String> details = new ArrayList<String>();
		
		details.add(fname);
		details.add(lname);
		details.add(email);
		details.add(username);
		details.add(password);
		
		user.add(details);

		 //Write Logic
		
		 for(int i = user.size() - 1; i < user.size(); i++) {
			 ArrayList<String> row = new ArrayList<String>();
			 
			 row = user.get(i);
			 File file = new File("C:\\Users\\s_U_m_O\\eclipse-workspace\\Server_520\\src\\User.txt");
			 try (FileOutputStream fop = new FileOutputStream(file,true)) {
				 for(int j = 0; j < row.size(); j++) {
					 String rec = row.get(j);
					 
					 if (!file.exists()) {
							file.createNewFile();
						}

						// get the content in bytes
					
					byte[] contentInBytes = rec.getBytes();
					
					fop.write(contentInBytes);
					fop.write(System.getProperty("line.separator").getBytes());
				 }
				 
				fop.flush();
				fop.close();
			 }
			 catch (IOException ex) {
					ex.printStackTrace();
			}
		}
	}
	
	
	//*********************LoginCheck*******************//
	
	public boolean LoginMatch(String username, String password) {
		UserDetailsRead();
		boolean match = false;
		if (!loggedInUsers.contains(username)) {
			for (int i = 0 ; i < usernames.size(); i++) {
				if (username.equals(usernames.get(i))) {
					if(password.equals(passwords.get(i))) {
						match = true;
						loggedInUsers.add(username);
						break;
					}
					else {
						match = false;
					}
				}
			}
		}
		System.out.println("Loggedd in users: ");
		for(int i = 0; i< loggedInUsers.size();i++) {
			System.out.println(loggedInUsers.get(i));
		}
		
		return match;
	}
	
	
	
	//*********************TableAvailabilityCheck*******************//
	
	public int [] TableAvailabilityCheck(int date) {
		
		tableDetailsRead();
		
		int availability[] = new int[12];
		
		for(int i = 0; i < 12 ; i++) {
			if(table[(31 * i)+(date - 1)].equals("0")) {
				availability[i] = 1;
			}
			else {
				availability[i] = 0;
			}
			
		}
		
		return availability;
		
		
	}
	
	//*********************UserReservationCount*******************//
	
		public int UserReservationCount(String username) {
			tableDetailsRead();
			int count = 0;
			for(int i = 0; i < table.length; i++) {
				if (username.equals(table[i])) {
					count++;
				}
			}
			return count;
		}
	
	//*********************ReservationCheck*******************//
	
	public int ReservationCheck(int date, int tableno) {
		tableDetailsRead();
		int row = (31*(tableno-1)) + (date - 1);
		if(table[row].equals("0")) {
			return 1;
		}
		else {
			return 0;
		}
	}
	
	//*********************ReservationWrite*******************//
	
		public void ReservationWrite(int date, int tableno, String username) {
			int row = (31*(tableno-1)) + (date - 1);
			
			String tableRow;
			String tabletemp[] = new String[372];
			try {
				Scanner tableDetails = new Scanner(new File("C:\\\\Users\\\\s_U_m_O\\\\eclipse-workspace\\\\Server_520\\\\src\\\\Table.txt"));
				while (tableDetails.hasNextLine()){

					for(int i = 0 ; i < 372; i++) {
						tableRow = tableDetails.nextLine();
						tabletemp[i] = tableRow;
   
					}
				}
				tableDetails.close();

			} catch (FileNotFoundException ea) {
				System.out.println("Table File not found");
			}

			File file = new File("C:\\Users\\s_U_m_O\\eclipse-workspace\\Server_520\\src\\Table.txt");
			try (FileOutputStream fop = new FileOutputStream(file)) {
				for(int j = 0; j < tabletemp.length; j++) {
					String rec = new String();
					if(j == row){
						rec = username;
					}
					else {
						rec = tabletemp[j];
					}
						// get the content in bytes

						byte[] contentInBytes = rec.getBytes();

						fop.write(contentInBytes);
						fop.write(System.getProperty("line.separator").getBytes());
					}
				 
					fop.flush();
					fop.close();
				}catch (IOException ex) {
					ex.printStackTrace();
				}
		}
		
	//*********************UserCancellationCheck*******************//
		
		public int UserCancellationCheck(int date, int tableno, String username) {
			tableDetailsRead();
			int row = (31*(tableno-1)) + (date - 1);
			if(table[row].equals(username)){
				return 1;
			}
			else {
				return 0;
			}
		}
	
	//*********************CancellationWrite*******************//
	
	public void CancellationWrite(int date, int tableno) {
		tableDetailsRead();
		int row = (31*(tableno-1)) + (date - 1);
		
		String tableRow;
		String tabletemp[] = new String[372];
		try {
			Scanner tableDetails = new Scanner(new File("C:\\\\Users\\\\s_U_m_O\\\\eclipse-workspace\\\\Server_520\\\\src\\\\Table.txt"));
			while (tableDetails.hasNextLine()){

				for(int i = 0 ; i < 372; i++) {
					tableRow = tableDetails.nextLine();
					tabletemp[i] = tableRow;

				}
			}
			tableDetails.close();

		} catch (FileNotFoundException ea) {
			System.out.println("Table File not found");
		}

		File file = new File("C:\\Users\\s_U_m_O\\eclipse-workspace\\Server_520\\src\\Table.txt");
		try (FileOutputStream fop = new FileOutputStream(file)) {
			for(int j = 0; j < tabletemp.length; j++) {
				String rec = new String();
				if(j == row){
					rec = "0";
				}
				else {
					rec = tabletemp[j];
				}
					// get the content in bytes

					byte[] contentInBytes = rec.getBytes();

					fop.write(contentInBytes);
					fop.write(System.getProperty("line.separator").getBytes());
				}
			 
				fop.flush();
				fop.close();
			}catch (IOException ex) {
				ex.printStackTrace();
			}
	}
	
	//*********************UsernameAvailable*******************//
	
	public boolean UsernameAvailable(String username)
	{
		UserDetailsRead();
		boolean available = true;
		for(int i = 0; i < usernames.size(); i++) {
			if (username.equals(usernames.get(i))) {
				available = false;
				break;
			}
		}
		return available;
	}

	//*********************UserReservations*******************//
	
	public int [] UserReservations(String username) {
		tableDetailsRead();
		int Reservations[] = new int [6];
		int index = 0;
		for(int i = 0; i < table.length; i++) {
			if(table[i].equals(username)) {
				int tableno = i/31 + 1;
				int resDate = i%31 + 1;
				
				Reservations[index] = tableno;
				Reservations[index + 1] = resDate;
				index = index + 2;
			}
		}

		
		return Reservations;
	}
	
	public Server_array() {
		initialize();
	}

	//*********************Server Frame Design*******************//
	

	private void initialize() {

		startServer();
		
		JFrame ServerFrame = new JFrame();
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

				DateComboBox.setBounds(1169, 276, 36, 26);
				ServerPage.add(DateComboBox);

				JButton btnGenerateDateReport = new JButton("Generate Date Report");
				btnGenerateDateReport.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						tableDetailsRead();
						System.out.println("");
					}
				});
				btnGenerateDateReport.setFont(new Font("Tahoma", Font.BOLD, 16));
				btnGenerateDateReport.setBounds(1067, 350, 242, 64);
				ServerPage.add(btnGenerateDateReport);

				JButton btnGenerateUserReport = new JButton("Generate User Report");
				btnGenerateUserReport.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// User report logic
					}
				});
				btnGenerateUserReport.setFont(new Font("Tahoma", Font.BOLD, 16));
				btnGenerateUserReport.setBounds(1074, 729, 242, 64);
				ServerPage.add(btnGenerateUserReport);

				JLabel lblSelectDate = new JLabel("Select Date");
				lblSelectDate.setHorizontalAlignment(SwingConstants.CENTER);
				lblSelectDate.setFont(new Font("Tahoma", Font.BOLD, 17));
				lblSelectDate.setBounds(1067, 201, 235, 51);
				ServerPage.add(lblSelectDate);

				JTextField textField_1 = new JTextField();
				textField_1.setBounds(301, 113, 688, 314);
				ServerPage.add(textField_1);
				textField_1.setColumns(10);

				JTextField textField_2 = new JTextField();
				textField_2.setColumns(10);
				textField_2.setBounds(301, 518, 688, 314);
				ServerPage.add(textField_2);

				JTextField UserTxt = new JTextField();
				UserTxt.setBounds(1096, 653, 198, 51);
				ServerPage.add(UserTxt);
				UserTxt.setColumns(10);
		
		
	}

	//*********************Server start logic*******************//
	private void startServer() {
		
		
		System.out.println("Starting server");
		try {
			serverSocket = new ServerSocket(1360, 100);
			while (true) {
				waitForCall();
			}
		} catch (IOException ex) {
			System.out.println("Error starting server. " + ex);
		} catch (ClassNotFoundException ex) {
			System.out.println("Error starting server. " + ex);
			try {
				out.close();
				in.close();
				socket.close();
			} catch (IOException ea) {
			}
		}
	}
	
	//******************User logged out*********************//
	
	public void UserLoggedOut (String username) {
		int index = 0;
		for(int i = 0; i < loggedInUsers.size(); i++) {
			if(username.equals((loggedInUsers).get(i))) {
				index = i;
				loggedInUsers.remove(index);
				
				System.out.println("User loggedd out...Logged in users: ");
				for(int j = 0; j< loggedInUsers.size();j++) {
					System.out.println(loggedInUsers.get(j));
				}
				
				
			}
		}
		
		
	}

	//*********************Server Waiting for connection from Clients*******************//
	private void waitForCall() throws IOException, ClassNotFoundException {
		System.out.println("Waiting for client to connect...");
		socket = serverSocket.accept();
		System.out.println("Client Connection accepted from " + socket.getInetAddress().getHostName());
		out = new ObjectOutputStream(socket.getOutputStream());
		out.flush();
		in = new ObjectInputStream(socket.getInputStream());
	
		int communicationCode = (int) in.readObject();
		connectionCount ++;
		System.out.println("Hits: "+connectionCount);
		switch(communicationCode) {
		
		case 2: {
			String email = (String) in.readObject();
			String username = (String) in.readObject();
			String fname = (String) in.readObject();
			String lname = (String) in.readObject();
			String password = (String) in.readObject();
			UserDetailsWrite(email, username, fname, lname, password);
			break;
		}
		case 3:{
			String username = (String) in.readObject();
			String password = (String) in.readObject();
			boolean match = LoginMatch(username, password);
			out.writeObject(match);
			out.flush();
			break;
		}
		case 4:{
			int date = (int) in.readObject();
			int Availability [] = new int[12];
			Availability = TableAvailabilityCheck(date);
			out.writeObject(Availability);
			out.flush();
			break;
		}
		case 5:{
			String username = (String) in.readObject();
			int count = UserReservationCount(username);
			out.writeObject(count);
			out.flush();
			break;
		}
		case 6:{
			int date = (int) in.readObject();
			int tableno = (int) in.readObject();
			int available = ReservationCheck(date, tableno);
			out.writeObject(available);
			out.flush();
			break;
		}
		case 7:{
			int date = (int) in.readObject();
			int tableno = (int) in.readObject();
			String username = (String) in.readObject();
			ReservationWrite(date, tableno, username);
			break;
		}
		case 8:{
			int date = (int) in.readObject();
			int tableno = (int) in.readObject();
			String username = (String) in.readObject();
			int cancelAllowed = UserCancellationCheck(date, tableno, username);
			out.writeObject(cancelAllowed);
			out.flush();
			break;
		}
		case 9:{
			int date = (int) in.readObject();
			int tableno = (int) in.readObject();
			CancellationWrite(date, tableno);
			break;
		}
		case 10:{
			String username = (String) in.readObject();
			boolean usernameAvailable = UsernameAvailable(username);
			out.writeObject(usernameAvailable);
			out.flush();
			break;
		}
		case 11:{
			String username = (String) in.readObject();
			UserLoggedOut(username);
			break;
		}
		case 12:{
			String username = (String) in.readObject();
			int Res [] = new int[6];
			Res = UserReservations(username);
			out.writeObject(Res);
			out.flush();
			break;	
		}
		default:
			break;
		}
		

		// }
	}
}
