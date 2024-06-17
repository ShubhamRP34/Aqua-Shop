package Generator;

import java.sql.ResultSet;
import java.sql.SQLException;

import Database.database;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class templateStructures
{
	
//	This function return fish template
	  public VBox make_template_Structure(String Heading, String bottomLink, String productQuery, String table_name, VBox main_vbox) throws SQLException
	    {
	    	
	    	 // Create a VBox
	        VBox vbox = new VBox();
	        vbox.setPrefSize(600, 400);
	        vbox.setStyle("-fx-padding: 5;");

	        // Create a Text node for the header
	        Text headerText = new Text(Heading);
//	        headerText.setFont(Font.font("System Bold Italic", 16));
	        
	        headerText.setStyle("-fx-font-family: 'System'; -fx-font-size: 23px; -fx-font-weight: bold; -fx-font-style: italic;");

	        // Create a FlowPane for the content
	        FlowPane flowPane = new FlowPane();
	        flowPane.setPrefSize(200, 200);
	        flowPane.setStyle("-fx-padding: 5;");
	        flowPane.setHgap(5);
	        flowPane.setVgap(5);

//	    	this section establish template from database
	    	database data = new database();
	    	
//	    	String query = "SELECT * FROM fish ORDER BY RAND() LIMIT 4;";
	    	String query = productQuery;
	    	
	    	
	    	 // Executing query and getting result set
	        ResultSet resultSet = null;
			try {
				resultSet = data.executeQuery(query);	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        // Do something with the result set
	        try {
	        	
	        	if(table_name == "fish")
	        	{
	        		while (resultSet.next()) {
	        			
//	        		  int id = resultSet.getInt("id");
//	        		  String vid_url = resultSet.getString("video_url");s
	        		  int prod_id = resultSet.getInt("id");
	        			
					  String species = "Species : " + resultSet.getString("species");
				      String name = "Name : " + resultSet.getString("name");
				      String family = "Family : "+resultSet.getString("family");
				      String size = "Size : " + resultSet.getString("size");
				      String price = "Price : " + resultSet.getString("price_per_piece") + " per piece";
				      
				      String img = resultSet.getString("image");
					
				     templateGenerator temp = new templateGenerator();
				      
					flowPane.getChildren().add(temp.getProductTemplate(name, species, family, size, price, img, table_name, prod_id, main_vbox));
					}
	        	}
	        	else if(table_name == "fish_medicines")
	        	{
	        		while (resultSet.next()) {
	        			
//		        		  int id = resultSet.getInt("id");
//		        		  String vid_url = resultSet.getString("video_url");
		        		  int prod_id = resultSet.getInt("medicine_id");
		        			
		        		  String name = "Name : " + resultSet.getString("name");
						  String species = "Type : " + resultSet.getString("category");
					      String family = "Manufacturer : "+resultSet.getString("manufacturer");
					      String size = "Exp Date : " + resultSet.getString("expiration_date");
					      String price = "Price : " + resultSet.getString("price") + " per piece";
					      
					      String img = resultSet.getString("img_url");
						
					     templateGenerator temp = new templateGenerator();
					      
						flowPane.getChildren().add(temp.getProductTemplate(name, species, family, size, price, img, table_name, prod_id, main_vbox));
						}
	        	}
	        	else if(table_name == "accessories")
	        	{
	        		while (resultSet.next()) {
	        			
//		        		  int id = resultSet.getInt("id");
//		        		  String vid_url = resultSet.getString("video_url");
		        		  int prod_id = resultSet.getInt("accessory_id");
		        			
		        		  String name = "Name : " + resultSet.getString("name");
						  String species = "Brand : " + resultSet.getString("brand");
					      String family = "Material : "+resultSet.getString("material");
					      String size = "Size : " + resultSet.getString("size");
					      String price = "Price : " + resultSet.getString("price") + " per piece";
					      
					      String img = resultSet.getString("image_url");
						
					     templateGenerator temp = new templateGenerator();
					      
						flowPane.getChildren().add(temp.getProductTemplate(name, species, family, size, price, img, table_name, prod_id, main_vbox));
						}
	        	}
	        	else if(table_name == "fish_food")
	        	{
	        		while (resultSet.next()) {
	        			
//		        		  int id = resultSet.getInt("id");
//		        		  String vid_url = resultSet.getString("video_url");
		        		  int prod_id = resultSet.getInt("id");
		        			
		        		  String name = "Name : " + resultSet.getString("name");
						  String species = "Brand : " + resultSet.getString("brand");
					      String family = "Type : "+resultSet.getString("type");
					      String size = "Pack_Size : " + resultSet.getString("package_size");
					      String price = "Price : " + resultSet.getString("price") + " per piece";
					      
					      String img = resultSet.getString("image");
						
					     templateGenerator temp = new templateGenerator();
					      
						flowPane.getChildren().add(temp.getProductTemplate(name, species, family, size, price, img, table_name, prod_id, main_vbox));
						}
	        	}
	        	else
	        	{
	        		System.out.println("Template Type not Match");
	        	}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        finally
	        {
	        	  // Close the result set, statement, and connection when done
	            resultSet.close();
	        }
//	      template establish in flowpane
	    	
	        // Create a Text node for the footer
	        Text footerText = new Text("Click Here");
	        footerText.setFont(Font.font("System Italic", 14));
	        
	        // Mouse hover event handler
	        footerText.setOnMouseEntered(event -> {
//	            footerText.setStyle("-fx-text-fill: red !important;");
	        	footerText.setFill(Color.RED);
	        });
	        
	        // Mouse exit event handler
	        footerText.setOnMouseExited(event -> {
	        	footerText.setFill(Color.BLACK);
	        });
	        
	        // Mouse click event handler
	        footerText.setOnMouseClicked(event -> {
	            System.out.println("Text clicked!");
	            resultPageGenerator rp = new resultPageGenerator();
	            try {
//					rp.getresult(table_name, productQuery, main_vbox);
//					
					main_vbox.getChildren().clear();
					main_vbox.getChildren().add(rp.getresult(table_name, productQuery, main_vbox));
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        });
	        
	        
	        // Add the nodes to the VBox
	        vbox.getChildren().addAll(headerText, flowPane, footerText);
	    	
	    	
	    	return vbox;
	    }
//	  fish template generator end
}