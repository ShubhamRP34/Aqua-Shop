package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class saveCartData
{
	 
    
    private static final String url = "jdbc:mysql://localhost:3306/aqua";
    private static final String username = "root";
    private static final String password = "";

    public void addToCart(String email, int itemId, int quantity, double price) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
//            String query = "INSERT INTO cart (email, item_id, quantity) VALUES (?, ?, ?)";
            String query = "INSERT INTO cart (email, item_id, quantity, price) VALUES (?, ?, ?, ?)";

            PreparedStatement statement = conn.prepareStatement(query);
//            statement.setInt(1, cartId);
            statement.setString(1, email);
            statement.setInt(2, itemId);
            statement.setInt(3, quantity);
            statement.setDouble(4, price); // Assuming price is a double value

//            statement.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            statement.executeUpdate();
            System.out.println("Data inserted into cart table successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error occurred while inserting data into cart table.");
        }
    }
}