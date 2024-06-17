package Generator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.*;

public class showCart
{
	
	 private  final String JDBC_URL = "jdbc:mysql://localhost:3306/aqua";
	    private final String USERNAME = "root";
	    private final String PASSWORD = "";

	    public GridPane fetchCartData(String email) {
	        GridPane root = new GridPane();
	        root.setHgap(10);
	        root.setVgap(5);
	        
	        root.setStyle("-fx-background-color: #f0f0f0; -fx-padding: 10px;");

	        double totalPrice = 0;

	        try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
	            String query = "SELECT * FROM cart WHERE email = ?";
	            PreparedStatement statement = conn.prepareStatement(query);
	            statement.setString(1, email); // Pass the email as a parameter
	            ResultSet resultSet = statement.executeQuery();

	            int row = 0;
	            while (resultSet.next()) {
	                int cartId = resultSet.getInt("cart_id");
	                int itemId = resultSet.getInt("item_id");
	                int quantity = resultSet.getInt("quantity");
	                double price = resultSet.getDouble("price");

	                totalPrice += price;

	                Label cartIdLabel = new Label("Cart ID: " + cartId);
	                Label itemIdLabel = new Label("Item ID: " + itemId);
	                Label quantityLabel = new Label("Quantity: " + quantity);
	                Label priceLabel = new Label("Price: $" + price);
	                
	              

	                // Increase font size for labels
	                cartIdLabel.setStyle("-fx-font-size: 15pt");
	                itemIdLabel.setStyle("-fx-font-size: 15pt");
	                quantityLabel.setStyle("-fx-font-size: 15pt");
	                priceLabel.setStyle("-fx-font-size: 15pt");

	                root.addRow(row++, cartIdLabel, itemIdLabel, quantityLabel, priceLabel);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        int intValue = (int) totalPrice;
	        
	        Label totalLabel = new Label("Total: $" + intValue);
	        totalLabel.setStyle("-fx-font-size: 15pt");
	        
	        Button checkoutButton = new Button("Checkout");

	        checkoutButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 16px;");

	        
	        
	        Button clearCartButton = new Button("Clear Cart");
	        clearCartButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 16px;");

	        checkoutButton.setOnAction(event -> {
	      
	        });
	        
	        

	        clearCartButton.setOnAction(event -> {
	            try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
	                String deleteQuery = "DELETE FROM cart WHERE email = ?";
	                PreparedStatement deleteStatement = conn.prepareStatement(deleteQuery);
	                deleteStatement.setString(1, email);
	                deleteStatement.executeUpdate();

	                Label emptyCartLabel = new Label("Cart is empty.");
	                emptyCartLabel.setStyle("-fx-font-size: 15pt; -fx-text-fill: red;");
	                
	                // Clear the grid pane after deleting cart items
	                root.getChildren().clear();
	                root.addRow(0, emptyCartLabel);
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
	        });

	        root.addRow(root.getRowCount(), totalLabel);
	        root.addRow(root.getRowCount(), checkoutButton);
	        root.addRow(root.getRowCount(), clearCartButton);

	        return root;
	    }
}