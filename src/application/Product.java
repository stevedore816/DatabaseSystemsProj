package application;

public class Product {
	int UPC;
	String PName;
	int price;
	String Sname;
	int amount;
	int reorderlevel;
    int duplicates; 
	
	public Product(int uPC, String pName, int price, String sname, int amount, int reorderlevel) {
		super();
		UPC = uPC;
		PName = pName;
		this.price = price;
		Sname = sname;
		this.amount = amount;
		this.reorderlevel = reorderlevel;
		duplicates = 1;
	}
	public void incrementDupes() {
		duplicates++; 
	}
	public int getDuplicates() {
		return duplicates;
	}
	public int getUPC() {
		return UPC;
	}
	public void setUPC(int uPC) {
		UPC = uPC;
	}
	public String getPName() {
		return PName;
	}
	public void setPName(String pName) {
		PName = pName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getSname() {
		return Sname;
	}
	public void setSname(String sname) {
		Sname = sname;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getReorderlevel() {
		return reorderlevel;
	}
	public void setReorderlevel(int reorderlevel) {
		this.reorderlevel = reorderlevel;
	}
	public String toStringAdmin() {
		String str = "ProductID: " + UPC + " Product Name: " + PName + " InStock: " + amount + " ReorderLevel: " + reorderlevel; 
		return str; 
	}
	public String toStringCustomer() {
		String str =  PName + " " + "	$"+ price; 
		return str; 
	}
}
