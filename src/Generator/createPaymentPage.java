package Generator;

import java.util.concurrent.atomic.AtomicReference;
import java.util.prefs.Preferences;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import log_in.save_data;

public class createPaymentPage
{
    Preferences preferences = Preferences.userRoot().node(save_data.class.getName());

	
	public void createFrame(String name, String pricePerPiece, int quantity, double totalAmount) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL); // Restrict user access to this frame
        stage.setTitle("Purchase Details");
		
		 AnchorPane root = new AnchorPane();
	        root.setPrefSize(600, 400);
	        root.setStyle("-fx-background-color: #ecf7a3;");

	        VBox vBox = new VBox();
	        vBox.setLayoutY(46);
	        vBox.setPrefHeight(289);
	        vBox.setPrefWidth(600);
	        vBox.setStyle("-fx-background-color: #eef2d5; -fx-padding: 10px;");
	        root.getChildren().add(vBox);
	        AnchorPane.setBottomAnchor(vBox, 65.0);
	        AnchorPane.setLeftAnchor(vBox, 0.0);
	        AnchorPane.setRightAnchor(vBox, 0.0);
	        AnchorPane.setTopAnchor(vBox, 46.0);
	        
//	        vBox.getChildren().addAll(nameLabel, priceLabel, quantityLabel, totalLabel);


	        // Add Text elements, CheckBoxes, and other UI elements as described in the FXML
	        Text heading = new Text("AquaShop");
	        heading.setFont(new Font(29));
//	        root.getChildren().add(heading);
	        AnchorPane.setTopAnchor(heading, 0.0);
	        AnchorPane.setLeftAnchor(heading, 233.0);
	        AnchorPane.setRightAnchor(heading, 233.328125);
	        

	        Text nameLabel = new Text("Name: " + name);
	        nameLabel.setFont(new Font(24));
	        vBox.getChildren().add(nameLabel);

	        Text priceLabel = new Text("Price Per Piece: " + pricePerPiece);
	        priceLabel.setFont(new Font( 24));
	        vBox.getChildren().add(priceLabel);

	        Text quantityLabel = new Text("Quantity: " +  String.valueOf(quantity));
	        quantityLabel.setFont(new Font( 24));
	        vBox.getChildren().add(quantityLabel);

	        Text totalLabel = new Text("Total Amount: " +  String.valueOf(totalAmount));
	        totalLabel.setFont(new Font(24));
	        vBox.getChildren().add(totalLabel);

	        Text paymentOptionLabel = new Text("Select Payment Option");
	        paymentOptionLabel.setFont(new Font("Imprint MT Shadow", 24));
	        vBox.getChildren().add(paymentOptionLabel);
	        
	        
	        
	        CheckBox upiCheckBox = new CheckBox("UPI");
	        upiCheckBox.setFont(new Font("System Bold", 17));
	        vBox.getChildren().add(upiCheckBox);

	        CheckBox cardCheckBox = new CheckBox("Credit / Debit Card");
	        cardCheckBox.setFont(new Font("System Bold", 17));
	        vBox.getChildren().add(cardCheckBox);

	        CheckBox netBankingCheckBox = new CheckBox("Net Banking");
	        netBankingCheckBox.setFont(new Font("System Bold", 17));
	        vBox.getChildren().add(netBankingCheckBox);
	        

	        Button payButton = new Button("Pay");
	        payButton.setLayoutX(210);
	        payButton.setLayoutY(347);
	        payButton.setPrefWidth(181);
	        payButton.setPrefHeight(45);
	        payButton.setStyle("-fx-background-color: #2d3cbd; -fx-background-radius: 10px; -fx-border-radius: 10px;");
	        payButton.setTextFill(javafx.scene.paint.Color.web("#fcf9f9"));
	        
	        AtomicReference<String> selectedPaymentOption = new AtomicReference<>(null); // Initialize with null

	        payButton.setOnAction(event -> {
	            boolean isSelected = false;
	            for (Node node : vBox.getChildren()) {
	                if (node instanceof CheckBox) {
	                    CheckBox checkBox = (CheckBox) node;
	                    if (checkBox.isSelected()) {
	                        if (isSelected) {
	                            // More than one checkbox is selected, show error message
	                            showError("Please select only one payment option.");
	                            return;
	                        }
//	                        selectedPaymentOption = checkBox.getText();
	                        isSelected = true;
	                        
	                        selectedPaymentOption.set(checkBox.getText()); // Set the text of the selected checkbox
	                    }
	                }
	            }
	            if (!isSelected) {
	                // No checkbox selected, show error message
	                showError("Please select at least one payment option.");
	                return;
	            }
	            else
	            {
	            	TransactionManager m = new TransactionManager();
	            	m.insertTransaction(preferences.get("email", ""), totalAmount, "Completed", selectedPaymentOption.get());
	            	stage.close();
	            }

	            // Payment logic goes here
	            System.out.println("Payment logic executed.");
	        });

	        root.getChildren().addAll(heading, payButton);
	        
	        Scene scene = new Scene(root);
	        stage.setScene(scene);
	        stage.showAndWait(); // Show frame and wait for it to be closed before returning
			

//	        return root;
    }
	
	 private static void showError(String message) {
	        // Display error message to the user
//	        System.out.println("Error: " + message);
	        Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("Error");
	        alert.setHeaderText(null);
	        alert.setContentText(message);
	        alert.showAndWait();
	    }

}