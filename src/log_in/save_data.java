package log_in;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.prefs.Preferences;

import javafx.scene.control.Alert;

public class save_data
{
	  // Database connection details
    private final String url = "jdbc:mysql://localhost:3306/aqua";
    private final String username = "root";
    private final String password = "";
    
    private final Preferences preferences = Preferences.userRoot().node(getClass().getName());
    
	
	
	  public void createAccount(String firstName, String lastName, String mobileNumber1, String mobileNumber2, String address,
			  String additionalAddress, String city, String state, String pinCode, String email, String pass) {
//	        String firstName = firstNameField.getText();
//	        String lastName = lastNameField.getText();
//	        String mobileNumber1 = mobileNumber1Field.getText();
//	        String mobileNumber2 = mobileNumber2Field.getText();
//	        String address = addressField.getText();
//	        String additionalAddress = additionalAddressField.getText();
//	        String city = cityField.getText();
//	        String state = stateField.getText();
//	        String pinCode = pinCodeField.getText();
//	        String email = emailField.getText();
//	        String pass = passwordField.getText();

	        if (firstName.isEmpty() || lastName.isEmpty() || mobileNumber1.isEmpty() || mobileNumber2.isEmpty() || address.isEmpty() ||
	                city.isEmpty() || state.isEmpty() || pinCode.isEmpty() || email.isEmpty() || pass.isEmpty() || additionalAddress.isEmpty()) {
	            showError("Please fill in all fields.");
	            return;
	        }

	        if (!isValidEmail(email)) {
	            showError("Invalid email address.");
	            return;
	        }

	        // Check if the email already exists in the database
	        if (emailExists(email)) {
	            showError("Email already exists.");
	            return;
	        }

	        // Insert user data into the database
	        try (Connection conn = DriverManager.getConnection(url, username, password)) {
	            String query = "INSERT INTO user (firstname, lastname, mnum1, mnum2, address, n_add, city, state, pin_code, email, password) " +
	                           "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	            PreparedStatement statement = conn.prepareStatement(query);
	            statement.setString(1, firstName);
	            statement.setString(2, lastName);
	            statement.setString(3, mobileNumber1);
	            statement.setString(4, mobileNumber2);
	            statement.setString(5, address);
	            statement.setString(6, additionalAddress);
	            statement.setString(7, city);
	            statement.setString(8, state);
	            statement.setString(9, pinCode);
	            statement.setString(10, email);
	            statement.setString(11, pass);
	            statement.executeUpdate();

	            showAlert("Account created successfully!");
	        } catch (SQLException e) {
	            e.printStackTrace();
	            showError("Error occurred while creating account.");
	        }
	        
	        preferences.put("email", email);
	        preferences.put("password", pass);
	    }

	    private boolean isValidEmail(String email) {
	        // Basic email validation
	        return email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
	    }

	    private boolean emailExists(String email) {
	        try (Connection conn = DriverManager.getConnection(url, username, password)) {
	            String query = "SELECT * FROM user WHERE email = ?";
	            PreparedStatement statement = conn.prepareStatement(query);
	            statement.setString(1, email);
	            ResultSet resultSet = statement.executeQuery();
	            return resultSet.next();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return true; // Assume email exists to avoid potential registration issues
	        }
	    }

	    private void showAlert(String message) {
	        Alert alert = new Alert(Alert.AlertType.INFORMATION);
	        alert.setTitle("Information");
	        alert.setHeaderText(null);
	        alert.setContentText(message);
	        alert.showAndWait();
	    }

	    private void showError(String message) {
	        Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("Error");
	        alert.setHeaderText(null);
	        alert.setContentText(message);
	        alert.showAndWait();
	    }
}