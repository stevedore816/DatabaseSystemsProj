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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class orderProductHandler extends SQLHandler {
	@FXML
	ListView<String> productList = new ListView<String>(); 
	@FXML
	public void reorderFromSupplier(ActionEvent e) {
		
	}
	
	@FXML
	public void pushBack(ActionEvent e) throws IOException {
		AnchorPane adminLogin = (AnchorPane) FXMLLoader.load(getClass().getResource("adminScreen.fxml"));
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
		productList.getItems().add("Product Name:			ProductID: "); 
		ArrayList<String> products = super.belowReorderLevel();
		for (String str : products) {
			productList.getItems().add(str); 
		}
	}
}
