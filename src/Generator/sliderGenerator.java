package Generator;

import Database.database;
import Generator.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;


public class sliderGenerator {
	 Pagination pagination = new Pagination();
	 FlowPane tempflow = new FlowPane();
	 
////   Create the Text fields for product information
//  Text text1 = new Text();
//  Text text2 = new Text();
//  Text text3 = new Text();
//  Text text4 = new Text();
	 
	public sliderGenerator()
	{
//		this code slides the page
		Timeline timeline;
		 // Create a Timeline that changes the page every 2 seconds
        timeline = new Timeline(new KeyFrame(Duration.seconds(4), event -> nextPage()));
        timeline.setCycleCount(Timeline.INDEFINITE); // Repeat indefinitely
        timeline.play(); // Start the timeline when the controller is initialized
	}
	
    
//    first bunner image
    public  AnchorPane setPagination(String Folder_name) {
        // Create AnchorPane
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(600, 871);

        // Create Pagination
//        Pagination pagination = new Pagination();
        pagination.setFocusTraversable(false);
        pagination.setMaxPageIndicatorCount(6);
        pagination.setPageCount(6);
        pagination.setPrefSize(586, 510);
        AnchorPane.setTopAnchor(pagination, 5.0);
        AnchorPane.setLeftAnchor(pagination, 7.0);
        AnchorPane.setRightAnchor(pagination, 7.0);
        
        // Create AnchorPane with red background
        AnchorPane display = new AnchorPane();
        display.setPrefHeight(300);
        display.setPrefWidth(200);
        display.setStyle("-fx-background-color: red;"); // Set anchorpane background color to red
        
        
//       this code get images from folder in array
        pagination.setPageFactory(pageIndex -> 
        {
//        	if else goes here
        	Image[] bannerBackgrounds = new Image[6];
        	for (int i = 0; i < bannerBackgrounds.length; i++) {
        		String imagePath = "file:src/resources/"+Folder_name+"/image_"+i+".jpg";
        		bannerBackgrounds[i] = new Image(imagePath);
        	}
//        	if else ends here
        
        	String imageUrl = bannerBackgrounds[pageIndex].getUrl();
        	// Set the background image
        	display.setStyle("-fx-background-image: url('" + imageUrl + "'); " +
        		 "-fx-background-size: cover; " + // Adjusted to cover the full width
        		"-fx-background-size: contain; " +
                "-fx-background-repeat: no-repeat; "+
        		"-fx-background-size : 100% 100%");
        
        	return display;
        });
//    	code end
        

        // Create HBox
        HBox hBox = new HBox();
        hBox.setLayoutY(300);
        hBox.setPrefSize(538, 68);
        AnchorPane.setTopAnchor(hBox, 450.0);
        AnchorPane.setLeftAnchor(hBox, 31.0);
        AnchorPane.setRightAnchor(hBox, 31.0);
        
        Button b1, b2, b3, b4, b5, b6;
        
        b1 = new Button();
        b2 = new Button();
        b3 = new Button();
        b4 = new Button();
        b5 = new Button();
        b6 = new Button();
        
        b1.setPrefSize(120, 67);
        b2.setPrefSize(120, 67);
        b3.setPrefSize(120, 67);
        b4.setPrefSize(120, 67);
        b5.setPrefSize(120, 67);
        b6.setPrefSize(120, 67);
        
        hBox.getChildren().addAll(b1, b2, b3, b4, b5, b6);
        
        b1.setOnAction(event -> pagination.setCurrentPageIndex(0));
        b2.setOnAction(event -> pagination.setCurrentPageIndex(1));
        b3.setOnAction(event -> pagination.setCurrentPageIndex(2));
        b4.setOnAction(event -> pagination.setCurrentPageIndex(3));
        b5.setOnAction(event -> pagination.setCurrentPageIndex(4));
        b6.setOnAction(event -> pagination.setCurrentPageIndex(5));
        
        setButtonImg(Folder_name, 0, b1);
        setButtonImg(Folder_name, 1, b2);
        setButtonImg(Folder_name, 2, b3);
        setButtonImg(Folder_name, 3, b4);
        setButtonImg(Folder_name, 4, b5);
        setButtonImg(Folder_name, 5, b6);
    
        

        // Add children to AnchorPane
        anchorPane.getChildren().addAll(pagination, hBox);
        
        return anchorPane;
    }
    
    
    private void nextPage()
    {
    	 // Get the current page index and calculate the next page index
        int currentPageIndex = pagination.getCurrentPageIndex();
        int nextPageIndex = (currentPageIndex + 1) % pagination.getPageCount();

     // If nextPageIndex is zero, set it to 0 (return to page one)
        if (nextPageIndex == 0) {
            nextPageIndex = 0;
        }
        
        // Change the page to the next page
        pagination.setCurrentPageIndex(nextPageIndex);
    }
    
    public void setButtonImg(String Folder, int But_no, Button but)
    {
    	   but.setStyle("-fx-background-image: url(file:src/resources/"+Folder+"/image_"+But_no+".jpg); " +
    	       		 "-fx-background-size: cover; " + // Adjusted to cover the full width
    	       		"-fx-background-size: contain; " +
    	               "-fx-background-repeat: no-repeat; "+
    	       		"-fx-background-size : 100% 100%");
    }

}
