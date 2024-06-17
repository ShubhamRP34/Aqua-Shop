package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import log_in.*;

public class main_log_in_controller implements Initializable {
	
	
    @FXML
    private Button create_account;

    @FXML
    private Button log_in;
    
    @FXML
    private VBox dis_vbox;
    
    @FXML
    private ScrollPane scroll;
    

    private Stage primaryStage_;

////     Method to set the primary stage
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage_ = primaryStage;
        
//        validate_login v = new validate_login();
//        v.prime = primaryStage;
//        while(primaryStage != null)
//        {
//        v.setprime(primaryStage);
//        break;
//        }
//        
        System.out.print(""+ primaryStage_ == null + "in main login controller");
    }


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		log_in(null);
		
		dis_vbox.setStyle("-fx-padding : 20px;");
		dis_vbox.setSpacing(7);
		
		
		while(scroll != null)
		{
//	      This is code to set auto dimension of vbox in scrollpane
			scroll.widthProperty().addListener((observable, oldValue, newValue) -> {
				 dis_vbox.setPrefWidth(newValue.doubleValue());
			});
			
			scroll.heightProperty().addListener((observable, oldValue, newValue) -> {
				dis_vbox.setPrefHeight(newValue.doubleValue());
			});
//	      code ends
			
			// Disable vertical and horizontal scroll bars
	        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
	        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
	        
			break;
		}
		
////      This is code to set auto dimension of vbox in scrollpane
//		scroll.widthProperty().addListener((observable, oldValue, newValue) -> {
//			 dis_vbox.setPrefWidth(newValue.doubleValue());
//		});
//		
//		scroll.heightProperty().addListener((observable, oldValue, newValue) -> {
//			dis_vbox.setPrefHeight(newValue.doubleValue());
//		});
////      code ends
		
  

	}

    @FXML
    void create_account(MouseEvent event) {
    	Create_ac a = new Create_ac();
    	a.create_AC(dis_vbox);
    }

    @FXML
    void log_in(MouseEvent event) {
    	Log_in log = new Log_in();
    	log.user_log_in_input(dis_vbox);
    }
    
//    Stage s;
//    
//    public void setStage(Stage stage)
//    {
//    	this.s = stage;
//    }
    
    public void loadMainandCloseLogIn() {
        try {
// 
        	Main m = new Main();
//        	m.closeStage();
        	
//        	s.close();
            // Load two.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/frame/Main-frame.fxml"));
            Parent root_2 = loader.load();

            // Create a new Scene with root node from two.fxml
            Scene scene = new Scene(root_2);
            
            Stage twoStage = new Stage();
            twoStage.setScene(scene);
            
            Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
            twoStage.setX(primaryScreenBounds.getMinX());
            twoStage.setY(primaryScreenBounds.getMinY());
            twoStage.setWidth(primaryScreenBounds.getWidth());
            twoStage.setHeight(primaryScreenBounds.getHeight());
            
            twoStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}