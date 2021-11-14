package application;

import java.util.ArrayList;

public class UserData {
	private static String FName; 
	private static int CID;
	private static ArrayList<Product> cart = new ArrayList<Product> ();
	
	public static String getFName() {
		return FName;
	}
	public static void setFName(String fName) {
		FName = fName;
	}
	public static int getCID() {
		return CID;
	}
	
	public static void setCID(String cID) {
		CID = Integer.parseInt(cID);
	}
	public static ArrayList<Product> getCart() {
		return cart;
	}
	public static void setCart(ArrayList<Product> cart) {
		UserData.cart = cart;
	} 
	public static void addToCart(Product e) {
		cart.add(e);
	}
	public static void clearCart() {
		cart.clear();
	}
	public static int orderTotal() {
		int tot = 0;
		for(Product p : cart) {
			tot += p.getPrice() * p.getDuplicates(); 
		}
		return tot;
	}
}
