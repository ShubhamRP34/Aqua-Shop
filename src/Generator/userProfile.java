package Generator;

import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class userProfile
{
	
    private  final String url = "jdbc:mysql://localhost:3306/aqua";
    private  final String username = "root";
    private  final String password = "";

    public VBox getUserProfile(String userEmail) {
    	   VBox vbox = new VBox();
           vbox.setStyle("-fx-padding: 20px; -fx-spacing: 10px; -fx-background-color: #f4f5e1; -fx-border-radius: 10px; -fx-border-color: #cccccc;");

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM user WHERE email = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, userEmail);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");
                String mobileNumber1 = resultSet.getString("mnum1");
                String mobileNumber2 = resultSet.getString("mnum2");
                String address = resultSet.getString("address");
                String additionalAddress = resultSet.getString("n_add");
                String city = resultSet.getString("city");
                String state = resultSet.getString("state");
                String pinCode = resultSet.getString("pin_code");

                // Display user data in Text nodes
                // Display user data in Text nodes with custom styling
                Text nameText = new Text("Name: " + firstName + " " + lastName);
                nameText.setFont(Font.font("Arial", 18));
                nameText.setFill(Color.web("#333333"));
                Text mobile1Text = new Text("Mobile 1: " + mobileNumber1);
                mobile1Text.setFont(Font.font("Arial", 16));
                mobile1Text.setFill(Color.web("#333333"));
                Text mobile2Text = new Text("Mobile 2: " + mobileNumber2);
                mobile2Text.setFont(Font.font("Arial", 16));
                mobile2Text.setFill(Color.web("#333333"));
                Text addressText = new Text("Address: " + address);
                addressText.setFont(Font.font("Arial", 16));
                addressText.setFill(Color.web("#333333"));
                Text additionalAddressText = new Text("Additional Address: " + additionalAddress);
                additionalAddressText.setFont(Font.font("Arial", 16));
                additionalAddressText.setFill(Color.web("#333333"));
                Text cityText = new Text("City: " + city);
                cityText.setFont(Font.font("Arial", 16));
                cityText.setFill(Color.web("#333333"));
                Text stateText = new Text("State: " + state);
                stateText.setFont(Font.font("Arial", 16));
                stateText.setFill(Color.web("#333333"));
                Text pinCodeText = new Text("Pin Code: " + pinCode);
                pinCodeText.setFont(Font.font("Arial", 16));
                pinCodeText.setFill(Color.web("#333333"));

                // Add text nodes to the VBox
                vbox.getChildren().addAll(nameText, mobile1Text, mobile2Text, addressText, 
                                            additionalAddressText, cityText, stateText, pinCodeText);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQL exception
        }
        
        return vbox;
    }
}