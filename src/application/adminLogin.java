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

public class adminLogin {
	@FXML
	private TextField adminUser;

	@FXML
	private TextField adminPassword;

	@FXML
	public void pushLogin(ActionEvent e) throws IOException {
		if (adminUser.getText().equals("root") && adminPassword.getText().equals("1234")) {
			AnchorPane adminLogin = (AnchorPane) FXMLLoader.load(getClass().getResource("adminScreen.fxml"));
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
	public void pushBack(ActionEvent e) throws IOException {
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
