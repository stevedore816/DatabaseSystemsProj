package application;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class StartController extends SQLHandler {
	
	@FXML
	public void pushAdminUser(ActionEvent e) throws IOException {
		super.startSQLConnection();
		AnchorPane adminLogin = (AnchorPane) FXMLLoader.load(getClass().getResource("aLoginScreen.fxml"));
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
	public void pushCustomerUser(ActionEvent e) throws IOException {
		super.startSQLConnection();
		AnchorPane customerScreen = (AnchorPane) FXMLLoader.load(getClass().getResource("customerLogin.fxml"));
		Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
		Scene customerScene = new Scene(customerScreen, 600, 400);
		window.setScene(customerScene);
		window.show();
		window.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                Platform.exit();
            }
        });
	}
	
	@FXML
	public void pushSupplierUser(ActionEvent e) throws IOException {
		
	}
}
