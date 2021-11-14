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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MoreScreenHandler extends SQLHandler {

	@FXML
	ListView<String> list = new ListView<String>();

	@FXML
	ComboBox menu = new ComboBox();

	ArrayList<String[]> info;

	@FXML
	public void pushBackButton(ActionEvent e) throws IOException {
		AnchorPane adminLogin = (AnchorPane) FXMLLoader.load(getClass().getResource("welcomeScreen.fxml"));
		Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
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
		menu.getItems().add("WishList");
		menu.getItems().add("Orders");
	}

	@FXML
	public void pushCancel(ActionEvent e) throws IOException {
		String type = menu.getValue().toString();
		if (type.equals("WishList")) {
		String str = list.getSelectionModel().getSelectedItem();
		str = str.substring(0,str.indexOf("$")).trim();
		Product product = super.addToCart(str);
		super.removeFromWishList(UserData.getCID(), product.getUPC());
		loadWishList(); 
		} else {
			String str = list.getSelectionModel().getSelectedItem();
			int orderID = Integer.parseInt(str.substring(0,str.indexOf(" ")).trim());
			super.cancelOrder(orderID);
			loadOrders(); 
		}
	}

	@FXML
	public void pushGo(ActionEvent e) throws IOException {
		String type = menu.getValue().toString();
		if (type.equals("WishList")) {
			loadWishList(); 
		} else {
			loadOrders(); 
		}
	}
	
	public void loadOrders() {
		list.getItems().clear();
		info = super.getOrders(UserData.getCID());
		list.getItems().add("orderID" + "  " + "orderDate" + "   " + "shipDate" + "   " + "paymentType" + "   " + "CCN" + "   " + "customerID");
		for (String[] data : info) {
			if(data[3].equals("null")) {
				System.out.print("ran");
				data[3] = "not shipped yet";
			}
			list.getItems().add(data[0] + "       " + data[1] + "        " + data[2] + "         " + data[3] + "         " + data[4] + "         " + data[5]);
		}
	}
	
	public void loadWishList() {
		list.getItems().clear();
		ArrayList<Product> product = super.viewWishList(UserData.getCID());
		list.getItems().add("Product Name: " + "	" + " Price: " + "		");
		for (Product p : product) {
			String s = p.toStringCustomer();
			list.getItems().add(s);
		}
	}
}
