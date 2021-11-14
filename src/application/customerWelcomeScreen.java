package application;

import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class customerWelcomeScreen extends SQLHandler{
	@FXML
	ListView<String> products = new ListView<String>(); 
	@FXML
	TextField searchByCategory;
	
	@FXML
	Text WelcomeTxt = new Text();
	
	@FXML
	public void showWishList(ActionEvent e) {
		
		AnchorPane adminLogin;
		try {
			adminLogin = (AnchorPane) FXMLLoader.load(getClass().getResource("MoreScreen.fxml"));
			Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
			Scene adminScene = new Scene(adminLogin, 600, 400);
			window.setScene(adminScene);
			window.show();
			window.setOnCloseRequest(new EventHandler<WindowEvent>() {
	            public void handle(WindowEvent we) {
	                Platform.exit();
	            }
	        });
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		
		
	}
	@FXML
	public void showProductList(ActionEvent e) {
		products.getItems().clear();
		ArrayList<Product> product =  super.showAdminInventory(); 
		products.getItems().add("Product Name: " + "	" + " Price: " + "		");
		for(Product p : product) {
			String s = p.toStringCustomer();
			products.getItems().add(s);
		}
	}
	
	@FXML
	public void addToWishList(ActionEvent e) {
		String str = products.getSelectionModel().getSelectedItem();
		str = str.substring(0,str.indexOf("$")).trim();
		Product product = super.addToCart(str);
		super.addToWishList(UserData.getCID(), product.getUPC());
	}
	@FXML
	public void showBestSelling(ActionEvent e) {
		products.getItems().clear();
		products.getItems().add("Product Name:		Price: 		#ofOrders:"); 
		ArrayList<String> product = super.BestSelling(6);
		for (String str : product) {
			products.getItems().add(str); 
		}
	}
	@FXML
	public void showRecommended(ActionEvent e) {
		products.getItems().clear();
		products.getItems().add("Product Name:		Price: 	"); 
		ArrayList<String> product = super.Suggested(UserData.getCID());
		for (String str : product) {
			products.getItems().add(str); 
		}
	}
	@FXML
	public void searchButton(ActionEvent e) {
		products.getItems().clear();
		String category = searchByCategory.getText().toLowerCase(); 
		ArrayList<Product> product =  super.productCategory(category); 
		products.getItems().add("Product Name: " + "	" + " Price: " + "		");
		for(Product p : product) {
			String s = p.toStringCustomer();
			products.getItems().add(s);	
		}
	}
	
	@FXML
	public void orderProduct(ActionEvent e) {
		boolean dupes = false; 
		String str = products.getSelectionModel().getSelectedItem();
		str = str.substring(0,str.indexOf("$")).trim();
		Product product = super.addToCart(str);
		//System.out.println(product.toStringCustomer());
		for (Product p : UserData.getCart()) {
			if (p.getUPC() == product.getUPC()) {
				p.incrementDupes(); 
				dupes = true; 
			} 
		}
		if (!dupes) {UserData.addToCart(product);}
	}
	
	@FXML
	public void initialize() {
		WelcomeTxt.setText("Welcome " + UserData.getFName() + "!");
		ArrayList<Product> product =  super.showAdminInventory(); 
		products.getItems().add("Product Name: " + "	" + " Price: " + "		");
		for(Product p : product) {
			String e = p.toStringCustomer();
			products.getItems().add(e);
		}
	}
	
	@FXML
	public void backButton(ActionEvent e) throws IOException{
		AnchorPane adminLogin = (AnchorPane) FXMLLoader.load(getClass().getResource("customerLogin.fxml"));
		Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
		Scene adminScene = new Scene(adminLogin, 600, 400);
		window.setScene(adminScene);
		window.show();
		window.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                Platform.exit();
            }
        });
	}
	
	@FXML
	public void pushCheckout(ActionEvent e) throws IOException{
		AnchorPane adminLogin = (AnchorPane) FXMLLoader.load(getClass().getResource("OrderScreen.fxml"));
		Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
		Scene adminScene = new Scene(adminLogin, 600, 400);
		window.setScene(adminScene);
		window.show();
		window.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                Platform.exit();
            }
        });
	}
	
}
