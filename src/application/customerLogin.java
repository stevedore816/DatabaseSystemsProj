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

public class customerLogin extends SQLHandler {
	@FXML
	private TextField customerID;

	@FXML
	private TextField customerName;

	@FXML
	public void pushLogin(ActionEvent e) throws IOException {
		String ID = customerID.getText();
		String Name = customerName.getText();
		if (super.checkCustomer(Name, ID)) {
			UserData.setCID(ID);
			UserData.setFName(Name); 
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
	}

	@FXML
	public void pushRegister(ActionEvent e) {
		try {
			AnchorPane adminLogin = (AnchorPane) FXMLLoader.load(getClass().getResource("RegisterScreen.fxml"));
			Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
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
	public void pushBackButton(ActionEvent e) throws IOException {
		AnchorPane adminLogin = (AnchorPane) FXMLLoader.load(getClass().getResource("memberScreen.fxml"));
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
}