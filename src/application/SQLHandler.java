package application;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class SQLHandler {
	private static Connection con;
	private static Statement stmnt;
	// local port being listening in on
	private static int lprt = 4407;
	// port that is remote and getting the information from
	private static int rprt = 3306;
	// remote host server that is being referenced
	private static String rhost = "localhost";

	private void connectSSHTunnel() {
		// username and password for the ssh server
		String usr = "16097", pass = "bluesky1", host = "71.168.159.51";
		// ssh port
		int port = 22;
		// creates the ssh client
		JSch ssh = new JSch();
		try {
			// creates session starter with information needed to get connected to
			Session session = ssh.getSession(usr, host, port);
			session.setPassword(pass);
			// makes so no auth key is needed
			session.setConfig("StrictHostKeyChecking", "no");
			// establishes the session with the ssh
			session.connect();
			// establishes the ports lSteocally
			int assinged = session.setPortForwardingL(lprt, rhost, rprt);
		} catch (JSchException e) {
		}
	}

	private void connectSQL() {
		// tester for now
		try {
			// mysql Driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establishes the connection to the database link to connect to the database
			// (will be a different ip later),user,password
			con = DriverManager.getConnection("jdbc:mysql://" + rhost + ":" + lprt + "/warehouseproject",
					"javaConnector", "Onelongparty!");

			stmnt = con.createStatement();

		} catch (Exception e) {
		}
	}

	public void startSQLConnection() {
		connectSSHTunnel();
		connectSQL();
	}

	public int addCustomer(String first, String last, String address, String city, String state, String zip) {
		int customerID = 0; 
		try {
			CallableStatement stmnt = con.prepareCall("call addCustomer(?,?,?,?,?,?)");
			stmnt.setString(1, first);
			stmnt.setString(2, last);
			stmnt.setString(3, address);
			stmnt.setString(4, city);
			stmnt.setString(5, state);
			stmnt.setString(6, zip);
			ResultSet set = stmnt.executeQuery();
			if(set.next()) {
				customerID = set.getInt(1); 
				return customerID;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customerID;
	}

	public void removeCustomer(int id) {
		CallableStatement stmnt;
		try {
			stmnt = con.prepareCall("call removeCustomer(?)");
			stmnt.setInt(1, id);
			stmnt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ArrayList<Product> showAdminInventory() {
		ArrayList<Product> inventory = new ArrayList<Product>();
		try {
			CallableStatement stmnt = con.prepareCall("call showInventory()");
			ResultSet set = stmnt.executeQuery();
			while (set.next()) {
				int UPC = set.getInt(1);
				String PName = set.getString(2);
				int price = set.getInt(3);
				String Sname = set.getString(4);
				int amount = set.getInt(5);
				int reOrderLevel = set.getInt(6);
				inventory.add(new Product(UPC, PName, price, Sname, amount, reOrderLevel));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inventory;
	}

	public ArrayList<String> FrequentUsers(int month) {
		ArrayList<String> freq = new ArrayList<String>();
		try {
			CallableStatement stmnt = con.prepareCall("call FrequentUsers(?)");
			stmnt.setInt(1, month);
			ResultSet set = stmnt.executeQuery();
			while (set.next()) {
				int customerID = set.getInt(1);
				String FName = set.getString(2);
				String LName = set.getString(3);
				freq.add(customerID + "                             " + FName + " " + LName);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return freq;
	}

	public ArrayList<String> NonFrequentUsers(int month) {
		ArrayList<String> freq = new ArrayList<String>();
		try {
			CallableStatement stmnt = con.prepareCall("call nonFrequentUsers(?)");
			stmnt.setInt(1, month);
			ResultSet set = stmnt.executeQuery();
			while (set.next()) {
				int customerID = set.getInt(1);
				String FName = set.getString(2);
				String LName = set.getString(3);
				freq.add(customerID + "                             " + FName + " " + LName);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return freq;
	}

	public ArrayList<String> BoughtNotRated() {
		ArrayList<String> freq = new ArrayList<String>();
		try {
			CallableStatement stmnt = con.prepareCall("call BoughtNotRated()");
			ResultSet set = stmnt.executeQuery();
			while (set.next()) {
				String customerID = set.getString(1);
				int countOfItemsNotOrdered = set.getInt(2);

				freq.add(customerID + "                             " + countOfItemsNotOrdered);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return freq;
	}

	public ArrayList<String> HighlyRated(int month) {
		ArrayList<String> freq = new ArrayList<String>();
		try {
			CallableStatement stmnt = con.prepareCall("call HighlyRated(?)");
			stmnt.setInt(1, month);
			ResultSet set = stmnt.executeQuery();
			while (set.next()) {
				String PName = set.getString(1);
				int UPC = set.getInt(2);
				int val = 38 - PName.length();
				// System.out.print(val + ", ");
				String str = PName + getSpaces(val);
				// System.out.println(str.length());
				str = str + getSpaces(val) + UPC;
				// System.out.println(str.length());
				freq.add(str);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return freq;
	}

	public ArrayList<String> NotSellingWell(int month) {
		ArrayList<String> freq = new ArrayList<String>();
		try {
			CallableStatement stmnt = con.prepareCall("call notSellingWell(?)");
			stmnt.setInt(1, month);
			ResultSet set = stmnt.executeQuery();
			while (set.next()) {
				String PName = set.getString(1);
				int UPC = set.getInt(2);
				int amount = set.getInt(3);
				int val = 38 - PName.length();
				// System.out.print(val + ", ");
				String str = PName + getSpaces(val);
				// System.out.println(str.length());
				str = str + UPC + getSpaces(val * 2 + 7) + amount;
				// System.out.println(str.length());
				freq.add(str);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return freq;
	}

	public ArrayList<String> belowReorderLevel() {
		ArrayList<String> freq = new ArrayList<String>();
		try {
			CallableStatement stmnt = con.prepareCall("call belowReorderLevel()");
			ResultSet set = stmnt.executeQuery();
			while (set.next()) {
				int UPC = set.getInt(1);
				String PName = set.getString(2);
				int val = 38 - PName.length();
				// System.out.print(val + ", ");
				String str = PName + getSpaces(val);
				// System.out.println(str.length());
				str = str + getSpaces(val) + UPC;
				// System.out.println(str.length());
				freq.add(str);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return freq;
	}

	public String getSpaces(int val) {
		String str = "";
		for (int i = 0; i < val; i++) {
			str += " ";
		}
		return str;
	}

	public ArrayList<Product> viewWishList(int CID) {
		ArrayList<Product> wish = new ArrayList<Product>();
		try {
			CallableStatement stmnt = con.prepareCall("call viewWishList(?)");
			stmnt.setInt(1, CID);
			ResultSet set = stmnt.executeQuery();
			while (set.next()) {
				int UPC = set.getInt(1);
				String PName = set.getString(2);
				int price = set.getInt(3);
				String Sname = set.getString(4);
				int amount = set.getInt(5);
				int reOrderLevel = set.getInt(6);
				wish.add(new Product(UPC, PName, price, Sname, amount, reOrderLevel));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wish;
	}

	public ArrayList<String> BestSelling(int month) {
		ArrayList<String> freq = new ArrayList<String>();
		try {
			CallableStatement stmnt = con.prepareCall("call BestSelling(?)");
			stmnt.setInt(1, month);
			ResultSet set = stmnt.executeQuery();
			while (set.next()) {
				String PName = set.getString(1);
				int price = set.getInt(2);
				int num = set.getInt(3);
				int val = 38 - PName.length();
				// System.out.print(val + ", ");
				String str = PName + getSpaces(val);
				// System.out.println(str.length());
				str = str + "$" + price + getSpaces(val) + num;
				// System.out.println(str.length());
				freq.add(str);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return freq;
	}

	public ArrayList<String> Suggested(int CID) {
		ArrayList<String> freq = new ArrayList<String>();
		try {
			CallableStatement stmnt = con.prepareCall("call Suggested(?)");
			stmnt.setInt(1, CID);
			ResultSet set = stmnt.executeQuery();
			while (set.next()) {
				String PName = set.getString(1);
				int price = set.getInt(2);

				freq.add(PName + "			$" + price + "			");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return freq;
	}

	public ArrayList<Product> productCategory(String category) {
		ArrayList<Product> freq = new ArrayList<Product>();
		try {
			PreparedStatement stmnt = con
					.prepareStatement("select * from product join prod_category using (UPC) where category = (?)");
			stmnt.setString(1, category);
			ResultSet set = stmnt.executeQuery();
			while (set.next()) {
				int UPC = set.getInt(1);
				String PName = set.getString(2);
				int price = set.getInt(3);
				String Sname = set.getString(4);
				int amount = set.getInt(5);
				int reOrderLevel = set.getInt(6);
				freq.add(new Product(UPC, PName, price, Sname, amount, reOrderLevel));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return freq;
	}

	public boolean checkCustomer(String name, String id) {
		ArrayList<Product> freq = new ArrayList<Product>();
		try {
			PreparedStatement stmnt = con
					.prepareStatement("select * from customer where customerID = (?) and Fname = (?)");
			stmnt.setString(1, id);
			stmnt.setString(2, name);
			ResultSet set = stmnt.executeQuery();
			if (set.next()) {
				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public Product addToCart(String name) {
		ArrayList<Product> freq = new ArrayList<Product>(); 
		try {
			PreparedStatement stmnt = con.prepareStatement("select * from product where Pname = (?)");
			stmnt.setString(1, name);
			ResultSet set = stmnt.executeQuery();
			while(set.next()) {
				int UPC = set.getInt(1); 
				String PName = set.getString(2);
				int price = set.getInt(3);
				String Sname = set.getString(4); 
				int amount = set.getInt(5);
				int reOrderLevel = set.getInt(6); 
				return (new Product(UPC, PName, price, Sname, amount, reOrderLevel));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return new Product(0,"",0, "", 0, 0); 
	}
	
	public int createOrder(int customerID, String paymentType) {
		int orderID = 0; 
		try {
			CallableStatement stmnt = con.prepareCall("call placeOrder(?,?)");
			stmnt.setInt(1, customerID);
			stmnt.setString(2, paymentType);
			ResultSet set = stmnt.executeQuery();
			if(set.next()) {
				orderID = set.getInt(1); 
				return orderID;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderID; 
	}
	
	public void placeOrder(int orderID, int amnt, int UPC) {
		try {
			CallableStatement stmnt = con.prepareCall("call addToOrder(?,?,?)");
			stmnt.setInt(1, orderID);
			stmnt.setInt(2, amnt);
			stmnt.setInt(3, UPC);
			stmnt.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void removeFromWishList(int CID,int PID) {
		ArrayList<Product> wish = new ArrayList<Product>();
		try {
			CallableStatement stmnt = con.prepareCall("call removeFromWishList(?,?)");
			stmnt.setInt(1, CID);
			stmnt.setInt(2,PID); 
			ResultSet set = stmnt.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addToWishList(int CID,int PID) {
		ArrayList<Product> wish = new ArrayList<Product>();
		try {
			CallableStatement stmnt = con.prepareCall("call addToWishList(?,?)");
			stmnt.setInt(1, CID);
			stmnt.setInt(2,PID); 
			ResultSet set = stmnt.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<String[]> getOrders(int ID) {
		ArrayList<String[]> freq = new ArrayList<String[]>();
		try {
			PreparedStatement stmnt = con
					.prepareStatement("select * from orders where customerID = (?)");
			stmnt.setInt(1, ID);
			ResultSet set = stmnt.executeQuery();
			String[] str = new String [6];
			while (set.next()) {
				 str[0] = "" + set.getInt(1);
				str[1] = "" + set.getString(2);
				str[2] = set.getString(3);
				str[3] = set.getString(4);
				str[4] = "" + set.getInt(5);
				str[5]= "" + set.getInt(6);
				freq.add(str);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return freq;
	}
	
	public void cancelOrder(int PID) {
		ArrayList<Product> wish = new ArrayList<Product>();
		try {
			CallableStatement stmnt = con.prepareCall("call cancelOrder(?)");
			stmnt.setInt(1,PID); 
			ResultSet set = stmnt.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
