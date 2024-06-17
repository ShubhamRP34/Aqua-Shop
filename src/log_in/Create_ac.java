package log_in;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Create_ac
{
	   public void create_AC(VBox vbox) {
//	        VBox vbox = new VBox();
//	        vbox.setPrefSize(600, 751);
//	        vbox.setStyle("-fx-padding: 20px;");
		   // Add children
	        Text personalDetails = new Text("Personal Details *");
	        personalDetails.setFont(new Font("Microsoft JhengHei UI", 25));
	        
	        Text firstName = new Text("First Name");
	        firstName.setFont(new Font("Imprint MT Shadow", 23));
	        TextField firstNameField = new TextField();
	        
	        Text lastName = new Text("Last Name");
	        lastName.setFont(new Font("Imprint MT Shadow", 23));
	        TextField lastNameField = new TextField();
	        
	        Text mobileNumber1 = new Text("Mobile Number (1)");
	        mobileNumber1.setFont(new Font("Imprint MT Shadow", 23));
	        TextField mobileNumber1Field = new TextField();
	        
	        Text mobileNumber2 = new Text("Mobile Number (2)");
	        mobileNumber2.setFont(new Font("Imprint MT Shadow", 23));
	        TextField mobileNumber2Field = new TextField();
	        
	        Text deliveryDetails = new Text("Delivery Details *");
	        deliveryDetails.setFont(new Font("Microsoft JhengHei UI", 25));
	        
	        Text address = new Text("Address");
	        address.setFont(new Font("Imprint MT Shadow", 23));
	        TextField addressField = new TextField();
	        
	        Text apartment = new Text("Nearby Appartment, landmark etc");
	        apartment.setFont(new Font("Imprint MT Shadow", 23));
	        TextField apartmentField = new TextField();
	        
	        Text city = new Text("City");
	        city.setFont(new Font("Imprint MT Shadow", 23));
	        TextField cityField = new TextField();
	        
	        Text state = new Text("State");
	        state.setFont(new Font("Imprint MT Shadow", 23));
	        TextField stateField = new TextField();
	        
	        Text pinCode = new Text("PIN Code");
	        pinCode.setFont(new Font("Imprint MT Shadow", 23));
	        TextField pinCodeField = new TextField();
	        
	        Text loginDetails = new Text("Log In Details *");
	        loginDetails.setFont(new Font("Microsoft JhengHei UI", 25));
	        
	        Text emailID = new Text("E-mail ID");
	        emailID.setFont(new Font("Imprint MT Shadow", 23));
	        TextField emailField = new TextField();
	        
	        Text password = new Text("Password");
	        password.setFont(new Font("Imprint MT Shadow", 23));
	        TextField passwordField = new TextField();
	        
	        Button createAccountBtn = new Button("Create Account");
	        createAccountBtn.setPrefSize(185, 38);
	        createAccountBtn.setStyle("-fx-background-radius: 5px; -fx-border-radius: 5px; -fx-background-color: #778ac9;");
	        createAccountBtn.setTextFill(javafx.scene.paint.Color.WHITE);
	        createAccountBtn.setFont(new Font("Imprint MT Shadow", 20));
	        
	        save_data s = new save_data();
	        
	        
	        // Set the event handler for the button
	        createAccountBtn.setOnAction(e -> {
//	            System.out.println("Button clicked!");
	            // Add your code here to handle the button click event
//	        	
		        String firstName_ = firstNameField.getText();
		        String lastName_ = lastNameField.getText();
		        String mobileNumber1_ = mobileNumber1Field.getText();
		        String mobileNumber2_ = mobileNumber2Field.getText();
		        String address_ = addressField.getText();
		        String additionalAddress_ = apartmentField .getText();
		        String city_ = cityField.getText();
		        String state_ = stateField.getText();
		        String pinCode_ = pinCodeField.getText();
		        String email_ = emailField.getText();
		        String pass_ = passwordField.getText();
		        
		        
		        s.createAccount(firstName_, lastName_, mobileNumber1_, mobileNumber2_, address_, additionalAddress_, city_,
		        		state_, pinCode_, email_, pass_);
		        
	        });
	        
	        vbox.getChildren().clear();
	        
	        // Add all children to VBox
	        vbox.getChildren().addAll(
	                personalDetails, 
	                firstName, firstNameField, 
	                lastName, lastNameField, 
	                mobileNumber1, mobileNumber1Field, 
	                mobileNumber2, mobileNumber2Field, 
	                deliveryDetails, 
	                address, addressField, 
	                apartment, apartmentField, 
	                city, cityField, 
	                state, stateField, 
	                pinCode, pinCodeField, 
	                loginDetails, 
	                emailID, emailField, 
	                password, passwordField, 
	                createAccountBtn
	        );

//	        return vbox;
	    }
}