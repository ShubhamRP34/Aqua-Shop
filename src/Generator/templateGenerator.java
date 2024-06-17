package Generator;

import java.sql.SQLException;

import Controller.Main_frame_Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class templateGenerator{
//   NOTE : This class respansible for generating diffrent templates for different products
	
	
//  templatea maker 
    public VBox  getProductTemplate(String text_1,String text_2,String text_3,String text_4, String text_5, String Image, String table_name, int id, VBox main_vbox) {
        // Create the VBox to hold the components
        VBox template = new VBox();
//        VBox view = new VBox();
        
        // Create the ImageView for the product image
        ImageView imageView = new ImageView(new Image(Image));
        imageView.setFitHeight(160);
        imageView.setFitWidth(170);
        
        Text text1 = new Text();
        Text text2 = new Text();
        Text text3 = new Text();
        Text text4 = new Text();
        Text text5 = new Text();
        
   //   Create the Text fields for product information
        
    	      text1.setText(text_1);
    	      text2.setText(text_2);
    	      text3.setText(text_3);
    	      text4.setText(text_4);
    	      text5.setText(text_5);
        
        
        // Create the Button
        Button button = new Button("Check it");
        
     // Mouse click event handler
        button.setOnMouseClicked(event -> {
           productPageGenerator page = new productPageGenerator();
           try {
			page.loadProductPage(id, table_name, main_vbox);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//        	Main_frame_Controller c = new Main_frame_Controller();
//    		c.set_vbox();
        	
//        	main_vbox.getChildren().clear();
//	    	Text textNode = new Text("Hello, JavaFX!");
//	    	main_vbox.getChildren().add(textNode);
//           System.out.println("check it buttton click");
        });
        
        // Add components to the VBox
        template.getChildren().addAll(imageView, text1, text2, text3, text4,text5, button);
        
     // Set the preferred height and width of the VBox
        template.setPrefHeight(100); // Set the height to 200 pixels (adjust as needed)
        template.setPrefWidth(170);  // Set the width to 300 pixels (adjust as needed)

        
        template.setStyle("-fx-border-color: black; "+"-fx-border-width: 1px;" + "-fx-border-radius : 5px;");
       
        template.setPadding(new Insets(5));
        template.setSpacing(4);
        
        return template;
    }
//    template maker close
}