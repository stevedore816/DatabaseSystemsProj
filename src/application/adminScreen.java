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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class adminScreen extends SQLHandler{
	@FXML
	public void showProductDetail(ActionEvent e) throws IOException {
		AnchorPane adminLogin = (AnchorPane) FXMLLoader.load(getClass().getResource("productTrends.fxml"));
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
	public void showCustomerDetail(ActionEvent e) throws IOException {
		AnchorPane adminLogin = (AnchorPane) FXMLLoader.load(getClass().getResource("customerTrends.fxml"));
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
	public void showSupplierDetail(ActionEvent e) throws IOException {
		
	}
	
	@FXML
	public void pushOrderProduct(ActionEvent e) throws IOException {
		AnchorPane adminLogin = (AnchorPane) FXMLLoader.load(getClass().getResource("productOrdering.fxml"));
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
	public void pushBack(ActionEvent e) throws IOException {
		AnchorPane adminLogin = (AnchorPane) FXMLLoader.load(getClass().getResource("memberScreen.fxml"));
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

