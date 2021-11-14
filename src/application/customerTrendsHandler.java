package application;

import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.collections.ObservableList;
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

public class customerTrendsHandler extends SQLHandler{
	@FXML
	ListView<String> viewTrends = new ListView<String>(); 
	
	@FXML
	TextField trendData = new TextField(); 
	
	@FXML
	public void viewFrequent(ActionEvent e)  {
		int month = Integer.parseInt(trendData.getText());
		viewTrends.getItems().clear();
		viewTrends.getItems().add("CustomerID:       Name:    ");
		ArrayList<String> freq = super.FrequentUsers(month); 
		for (String str : freq) {
			viewTrends.getItems().add(str);
		}
	}
	@FXML
	public void viewNonFrequent(ActionEvent e)  {
		int month = Integer.parseInt(trendData.getText());
		viewTrends.getItems().clear();
		viewTrends.getItems().add("CustomerID:       Name:    ");
		ArrayList<String> freq = super.NonFrequentUsers(month); 
		for (String p : freq) {
			viewTrends.getItems().add(p);
		}
	}
	@FXML	
	public void viewRates(ActionEvent e)  {
		viewTrends.getItems().clear();
		viewTrends.getItems().add("CustomerID:          Number of Products UnRated: ");
		ArrayList<String> inventory = super.BoughtNotRated(); 
		for (String p : inventory) {
			viewTrends.getItems().add(p);
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
