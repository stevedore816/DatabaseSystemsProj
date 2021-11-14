package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class OrderScreenHandler extends SQLHandler {
	@FXML
	ListView cart = new ListView();
	@FXML
	ComboBox cardType = new ComboBox<Object>();
	
	
	@FXML
	public void pushBackButton(ActionEvent e) throws IOException {
		AnchorPane adminLogin = (AnchorPane) FXMLLoader.load(getClass().getResource("welcomeScreen.fxml"));
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
	public void initialize() {
		cardType.getItems().add("Visa");
		cardType.getItems().add("MC");
		cardType.getItems().add("Discover");
		for(Product p : UserData.getCart()) {
			cart.getItems().add(p.toStringCustomer() + "     x" + p.getDuplicates() );
		}
		cart.getItems().add("Total Price for your Order:   $" + UserData.orderTotal());
	}
	@FXML
	public void order(ActionEvent e) throws IOException{
		String enumer = cardType.getValue().toString(); 
		
		ArrayList<Product> cart = UserData.getCart(); 
		Iterator<Product> it = cart.iterator();
		int orderID = super.createOrder(UserData.getCID(), enumer);
		System.out.println(orderID); 
		//call get order#
		for(int i = 0; i < cart.size(); i++) {
			//System.out.println(p.getUPC());
			Product p = cart.get(i); 
			int amnt = 1; 
			super.placeOrder(orderID, p.getDuplicates(), p.getUPC());
		}
		//Back To Welcome Screen
		AnchorPane adminLogin = (AnchorPane) FXMLLoader.load(getClass().getResource("welcomeScreen.fxml"));
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
