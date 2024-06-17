package Generator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionManager
{
	 // JDBC URL, username, and password of MySQL server
    private static final String URL = "jdbc:mysql://localhost:3306/aqua";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    // SQL query to insert a new transaction into the transactions table
    private static final String INSERT_TRANSACTION_SQL = "INSERT INTO transactions (email, amount, status, payment_method) VALUES (?, ?, ?, ?)";

    public void insertTransaction(String email, double amount, String status, String paymentMethod) {
        try (
            // Establish a connection to the database
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            
            // Create a PreparedStatement object to execute the SQL query
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TRANSACTION_SQL)) {
            
            // Set the parameters of the PreparedStatement
            preparedStatement.setString(1, email);
            preparedStatement.setDouble(2, amount);
            preparedStatement.setString(3, status);
            preparedStatement.setString(4, paymentMethod);
            
            // Execute the query to insert the transaction
            preparedStatement.executeUpdate();
            
            System.out.println("Transaction inserted successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}