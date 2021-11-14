package application;  

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader; 
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;  

public class Main extends Application {                

    public static void main(String[] args) {
        Application.launch(args);       
    }          

    public void start(Stage stage) throws Exception {
    	
    	
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("memberScreen.fxml"));
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                Platform.exit();
            }
        });  
    } 
}