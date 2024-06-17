package log_in;

import java.sql.Connection;
import Controller.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Controller.main_log_in_controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class validate_login
{
	 private Connection connection;
	   private final String url = "jdbc:mysql://localhost:3306/aqua";
	    private final String username = "root";
	    private final String d_password = "";
//		public Stage prime;
//	    
//		public void setprime(Stage prim)
//		{
//			prime = prim;
//			
//			 System.out.print("prime "+prime == null+"");
//		}
	    
//	     Method to set the primary stage
//	    public void setPrimaryStage(Stage primaryStage) {
//	        this.primaryStage_ = primaryStage;
//	        
//	        System.out.print(primaryStage_ == null);
//	    }
	
	public boolean check(String email, String password)
	{
	    try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Connect to the database (replace database_url, username, and password)
            connection = DriverManager.getConnection(url, username, d_password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	    
	    // Retrieve email and password entered by the user
//        String email = emailField.getText();
//        String password = passwordField.getText();

        // Query the user table to check if the email and password combination exists
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE email = ? AND password = ?");
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // User authentication successful
//                showAlert("Login Successful Welcome "+ email);
//                System.out.print(""+ s == null + "in vlidate log in");

//                loadMainScreen();
                main_log_in_controller m = new main_log_in_controller();
                
                m.loadMainandCloseLogIn(); 
                return true;
                
                // Add code to navigate to the next screen or perform other actions
            } else {
                // User authentication failed
                showAlert("Login Failed Invalid email or password");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        	showAlert("auto log in fail");
        	return false;
        }
//        return true;
	
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
    
    
    public void loadMainScreen() {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/frame/Main-frame.fxml"));
//            Parent root = loader.load();
//
//            // Create a new scene with the loaded FXML file
//            Scene scene = new Scene(root);
//
//            // Set the scene on the primary stage
//            s.setScene(scene);
//            s.show();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}