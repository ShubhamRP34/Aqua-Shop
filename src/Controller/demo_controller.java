package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollToEvent;
import javafx.scene.layout.VBox;

public class demo_controller {

	  @FXML
	    private ScrollPane scrollPane;

	    @FXML
	    private VBox scrollContent;
	    
	    public demo_controller(){
//	        scrollContent.prefWidthProperty().bind(scrollPane.widthProperty());
//	        scrollContent.prefHeightProperty().bind(scrollPane.heightProperty());
	    	
	    	scrollPane.widthProperty().addListener((observable, oldValue, newValue) -> {
	    	    scrollContent.setPrefWidth(newValue.doubleValue());
	    	});
	    }
	    
//	    @FXML
//	    public void initialize() {
//	    	
//	    	if (scrollPane != null) {
//
//	        // Bind the width and height of the VBox to the ScrollPane
//	        scrollContent.prefWidthProperty().bind(scrollPane.widthProperty());
//	        scrollContent.prefHeightProperty().bind(scrollPane.heightProperty());
//	    	}
//	    	else
//	    	{
//	    		System.out.println("Scorllpane not fount");
//	    	}
//	    }

	    // Other methods and event handlers...
   
}
