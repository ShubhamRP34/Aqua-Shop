package Generator;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class cartAlert
{
	public  void showSuccessAlert(String img) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL); // Restrict user access to this frame
        stage.setTitle("Product Added Successfully"); 
        
        // Create AnchorPane
        AnchorPane root = new AnchorPane();
        root.setPrefSize(283, 400);

        // Create ImageView
        ImageView imageView = new ImageView(new Image(img));
        imageView.setLayoutX(7);
        imageView.setLayoutY(14);
        imageView.setFitWidth(270);
        imageView.setFitHeight(232);
        root.getChildren().add(imageView);
        AnchorPane.setBottomAnchor(imageView, 154.0);
        AnchorPane.setLeftAnchor(imageView, 7.0);
        AnchorPane.setRightAnchor(imageView, 6.0);
        AnchorPane.setTopAnchor(imageView, 14.0);

        // Create Text
        Text text = new Text("Added to cart");
        text.setLayoutX(51);
        text.setLayoutY(298);
        text.setFont(new Font(30));
        root.getChildren().add(text);
        AnchorPane.setBottomAnchor(text, 94.09765625);
        AnchorPane.setLeftAnchor(text, 51.0);
        AnchorPane.setRightAnchor(text, 50.06640625);
        AnchorPane.setTopAnchor(text, 265.626953125);

        // Create Button
        Button button = new Button("OK");
        button.setLayoutX(78);
        button.setLayoutY(325);
        button.setPrefSize(127, 40);
        button.setStyle("-fx-background-color: #4fd663;");
        button.setFont(new Font(18));
        root.getChildren().add(button);
        AnchorPane.setBottomAnchor(button, 35.0);
        AnchorPane.setLeftAnchor(button, 78.0);
        AnchorPane.setRightAnchor(button, 78.0);
        AnchorPane.setTopAnchor(button, 325.0);
        

        // Set onClick event for the button
        button.setOnAction(event -> {
//            System.out.println("Button clicked!");
            // Add your action here
            
            stage.close();
        });
        
        
        // Create scene and set it to the stage
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        
        // Set alert type
//        Alert alert = new Alert(AlertType.INFORMATION);
//        alert.initModality(Modality.APPLICATION_MODAL);
//        alert.initOwner(stage);
//        alert.setTitle("Success");
//        alert.setHeaderText("Product Added Successfully");
//        alert.setContentText("Your product has been added to the cart.");

//        alert.showAndWait();
    }
}