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
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class productTrendsHandler extends SQLHandler{
	@FXML
	ListView<String> trends = new ListView<String>(); 
	
	@FXML
	TextField trendData = new TextField(); 
	
	@FXML
	public void pushHighlyRated(ActionEvent e) {
		int month = Integer.parseInt(trendData.getText());
		trends.getItems().clear();
		trends.getItems().add("Product Name					ProductID"); 
		ArrayList<String> highlyRated = super.HighlyRated(month);
		for (String product : highlyRated) {
			trends.getItems().add(product); 
		}
	}
	//Work on this l8r
	@FXML
	public void pushNotSelling(ActionEvent e) {
		int month = Integer.parseInt(trendData.getText());
		trends.getItems().clear();
		trends.getItems().add("Product Name				ProductID				amountSold"); 
		ArrayList<String> noSell = super.NotSellingWell(month);
		for (String product : noSell) {
			trends.getItems().add(product); 
		}
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
}
