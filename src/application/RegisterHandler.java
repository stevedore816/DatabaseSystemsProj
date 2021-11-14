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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class RegisterHandler extends SQLHandler{
	@FXML
	private Text cidDisplay = new Text(); 
	@FXML
	private TextField FirstName; 
	@FXML
	private TextField LastName; 
	@FXML
	private TextField Address; 
	@FXML
	private TextField City; 
	@FXML
	private TextField State; 
	@FXML
	private TextField Zip; 
	
	@FXML
	public void pushRegister(ActionEvent e) {
		String zip = Zip.getText().substring(0,5);
		String state = State.getText().substring(0,2);
		int customerID = super.addCustomer(FirstName.getText(), LastName.getText(), Address.getText(), City.getText(), state, zip);
		cidDisplay.setText("Your CID: " + customerID + "!");
		
		/*try {
			AnchorPane adminLogin = (AnchorPane) FXMLLoader.load(getClass().getResource("customerLogin.fxml"));
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
		*/
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
