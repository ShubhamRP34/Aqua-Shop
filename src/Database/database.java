package Database;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.protocol.Resultset;

public class database {
    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/aqua";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            // Establishing connection
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            System.out.println("Database connection established successfully!");
        } catch (SQLException e) {
            // Error handling
            System.err.println("Failed to connect to database: " + e.getMessage());
            throw e;
        }
        return connection;
    }
    
    

    public ResultSet executeQuery(String query) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Establishing connection
            connection = getConnection();
            // Creating statement
            statement = connection.createStatement();
            // Executing query
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            // Error handling
            System.err.println("Failed to execute query: " + e.getMessage());
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
            throw e;
        }

        return resultSet;
    }

//    public static void main(String[] args) {
////        try {
//            // Getting database connection
//            Connection connection = getConnection();
//            // Do something with the connection if needed
//            // Don't forget to close the connection when done
////            
////            String query = "SELECT * FROM fish ORDER BY RAND() LIMIT 6;";
////            
////            // Executing query and getting result set
////            ResultSet resultSet = null;
////    		try {
////    			resultSet = executeQuery(query);
////    		} catch (SQLException e) {
////    			// TODO Auto-generated catch block
////    			e.printStackTrace();
////    		}
////    		
////    		while (resultSet.next()) {
////				  String species = resultSet.getString("species");
////			      String name = resultSet.getString("name");
////			      String family = resultSet.getString("family");
////			      String size = resultSet.getString("size");
////			      
////			      System.out.println(species + " " + name + " " + family + " " + size);
////			}
//   		
//            connection.close();
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
////    }
}
