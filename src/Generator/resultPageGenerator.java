package Generator;

import java.sql.ResultSet;
import java.sql.SQLException;

import Database.database;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class resultPageGenerator{
	
	public FlowPane getresult(String table_name, String productQuery, VBox main_vbox) throws SQLException
	{
    // Create a FlowPane for the content
    FlowPane flowPane = new FlowPane();
    flowPane.setPrefSize(200, 200);
    flowPane.setStyle("-fx-padding: 5;");
    flowPane.setHgap(5);
    flowPane.setVgap(5);

//	this section establish template from database
	database data = new database();
	
//	String query = "SELECT * FROM fish ORDER BY RAND() LIMIT 4;";
	String query = productQuery;
	
	
	 // Executing query and getting result set
    ResultSet resultSet = null;
	try {
		resultSet = data.executeQuery(query);	
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	 try {
     	
     	if(table_name == "fish")
     	{
     		while (resultSet.next()) {
     			
//     		  int id = resultSet.getInt("id");
//     		  String vid_url = resultSet.getString("video_url");s
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
     			
//	        		  int id = resultSet.getInt("id");
//	        		  String vid_url = resultSet.getString("video_url");
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
     			
//	        		  int id = resultSet.getInt("id");
//	        		  String vid_url = resultSet.getString("video_url");
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
     			
//	        		  int id = resultSet.getInt("id");
//	        		  String vid_url = resultSet.getString("video_url");
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
	 
	 return flowPane;
	}
}