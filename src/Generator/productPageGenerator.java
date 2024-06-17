package Generator;

//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;
//import javafx.scene.media.MediaView;
//import javafx.scene.web.WebEngine;
//import javafx.scene.web.WebView;

import java.lang.ModuleLayer.Controller;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.prefs.Preferences;

import Controller.*;
import Database.database;
import Database.saveCartData;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import log_in.save_data;

public class productPageGenerator
{
	
	ResultSet resultSet = null;
	String rword = null;
	
	public void loadProductPage(int id, String table_name, VBox main_vbox) throws SQLException
	{
	
		if(table_name == "accessories")
		{
			String query = "select * from "+table_name+" where 	accessory_id= "+id+";";
			getResultSet(query, main_vbox, table_name);
		}
		else if(table_name == "fish_medicines")
		{
			String query = "select * from "+table_name+" where medicine_id = "+id+";";
			getResultSet(query, main_vbox, table_name);
		}
		else if(table_name == "fish" || table_name == "fish_food")
		{
	    	String query = "select * from "+table_name+" where id = "+id+";";
	    	getResultSet(query, main_vbox, table_name);

		}

		 // Create a Region node for spacing
        Region spacingRegion = new Region();
        spacingRegion.setMinHeight(350);
        templateStructures t = new templateStructures();
    	main_vbox.getChildren().clear();
 
    	if(table_name == "fish")
    	{
    		main_vbox.getChildren().addAll(spacingRegion, createProductProfile(id , table_name), generateTilePane(table_name),
    	    		
        			t.make_template_Structure("fishes", "SELECT * FROM fish LIMIT 8;", "select * from fish WHERE relationship = '"+rword+"'  OR relationship = 'all';" , "fish", main_vbox),
        			t.make_template_Structure("Medicines", "SELECT * FROM fish LIMIT 8;", "select * from fish_medicines WHERE relationship = '"+rword+"'  OR relationship = 'all';" , "fish_medicines", main_vbox),
        			t.make_template_Structure("Accessories", "SELECT * FROM fish LIMIT 8;", "select * from accessories WHERE relationship = '"+rword+"'  OR relationship = 'all';" , "accessories", main_vbox),
        			t.make_template_Structure("fish food", "SELECT * FROM fish LIMIT 8;", "select * from fish_food WHERE relationship = '"+rword+"'  OR relationship = 'all';" , "fish_food", main_vbox)

        			);
    	}
    	else if(table_name == "fish_medicines" || table_name == "fish_food" || table_name == "accessories")
    	{
        	main_vbox.getChildren().addAll(spacingRegion, createProductProfile(id , table_name), generateTilePane(table_name),
            		
//        			t.make_template_Structure("fishes", "SELECT * FROM fish LIMIT 8;", "select * from fish WHERE relationship = '"+rword+"'  OR relationship = 'all';" , "fish", main_vbox),
        			t.make_template_Structure("Medicines", "SELECT * FROM fish LIMIT 8;", "select * from fish_medicines WHERE relationship = '"+rword+"'  OR relationship = 'all';" , "fish_medicines", main_vbox),
        			t.make_template_Structure("Accessories", "SELECT * FROM fish LIMIT 8;", "select * from accessories WHERE relationship = '"+rword+"'  OR relationship = 'all';" , "accessories", main_vbox),
        			t.make_template_Structure("fish food", "SELECT * FROM fish LIMIT 8;", "select * from fish_food WHERE relationship = '"+rword+"'  OR relationship = 'all';" , "fish_food", main_vbox)

        			);
    	}
//    	getBottomResults(main_vbox);
		System.out.println("laod the product page");
	}
	
	
	
	public void getResultSet(String query, VBox v, String table_name) throws SQLException
	{
		 
//    	this section establish template from database
    	database data = new database();	
    	    	 // Executing query and getting result set
//        ResultSet resultSet = null;
		try {
			resultSet = data.executeQuery(query);
			
//			 if (resultSet.next()) { // Move cursor to the first row
//			        System.out.println(resultSet.getString("name")); // Now it's safe to access column values
//			    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		here point setts on resultset frst row so no next menthod use for any other function in this class
		if(resultSet.next())
		{
			rword = resultSet.getString("relationship");
			
			if(table_name == "fish_medicines")
			{
	    	v.setStyle( "-fx-background-image: url(" + resultSet.getString("img_url") + "); -fx-background-size: cover; -fx-background-size: contain;-fx-background-repeat: no-repeat;-fx-background-size : 100% 400px;");
			}
			else if(table_name == "accessories")
			{
		    	v.setStyle( "-fx-background-image: url(" + resultSet.getString("image_url") + "); -fx-background-size: cover; -fx-background-size: contain;-fx-background-repeat: no-repeat;-fx-background-size : 100% 400px;");

			}
			else if(table_name == "fish" || table_name == "fish_food")
			{
		    	v.setStyle( "-fx-background-image: url(" + resultSet.getString("image") + "); -fx-background-size: cover; -fx-background-size: contain;-fx-background-repeat: no-repeat;-fx-background-size : 100% 400px;");

			}
		}
	
	}

	 public  AnchorPane createProductProfile(int id , String table_name) throws SQLException {
		 
		 Spinner<Integer> quantitySpinner = new Spinner<>(1, 10, 1);
		 
	        // Create the AnchorPane
	        AnchorPane anchorPane = new AnchorPane();
	        anchorPane.setPrefSize(600, 700);
	        anchorPane.setStyle("-fx-padding: 6px;");
	        

	        // Create the HBox
	        HBox hBox = new HBox();
	        hBox.setLayoutX(3);
	        hBox.setLayoutY(3);
	        hBox.setPrefSize(594, 394);
	        hBox.setStyle("-fx-background-color: #faf6e3; -fx-padding: 5px; -fx-background-radius: 5px;");
	        AnchorPane.setRightAnchor(hBox, 0.0);
	        AnchorPane.setLeftAnchor(hBox, 0.0);

	        // Create the left VBox
	        VBox leftVBox = new VBox();
	        leftVBox.setPrefSize(241, 394);
	        leftVBox.setStyle("-fx-padding: 10px;");
	        
	        leftVBox.setSpacing(10);

	        // Add elements to the left VBox
	        AnchorPane grayPane = new AnchorPane();
	        grayPane.setPrefSize(212, 224);
	        
//	        this code set image of product

	        	if(table_name == "fish")
	        	{
	        		grayPane.setStyle( "-fx-background-image: url(" + resultSet.getString("image") + "); -fx-background-size: cover; -fx-background-size: contain;-fx-background-repeat: no-repeat;-fx-background-size : 100% 100%;");
	        	}
	        	else if(table_name == "accessories")
	        	{
	        		grayPane.setStyle( "-fx-background-image: url(" + resultSet.getString("image_url") + "); -fx-background-size: cover; -fx-background-size: contain;-fx-background-repeat: no-repeat;-fx-background-size : 100% 100%;");
	        	}
	        	else if(table_name == "fish_food")
	        	{
	        		grayPane.setStyle( "-fx-background-image: url(" + resultSet.getString("image") + "); -fx-background-size: cover; -fx-background-size: contain;-fx-background-repeat: no-repeat;-fx-background-size : 100% 100%;");
	        	}
	        	else if(table_name == "fish_medicines")
	        	{
	        		grayPane.setStyle( "-fx-background-image: url(" + resultSet.getString("img_url") + "); -fx-background-size: cover; -fx-background-size: contain;-fx-background-repeat: no-repeat;-fx-background-size : 100% 100%;");
	        	}
//	        	else if(table_name == "fish_medicines")
//	        	{
//	        		grayPane.setStyle( "-fx-background-image: url(" + resultSet.getString("img_url") + "); -fx-background-size: cover; -fx-background-size: contain;-fx-background-repeat: no-repeat;-fx-background-size : 100% 100%;");
//	        	}


//	        image set complete
	        
	        
	        AnchorPane buttonsPane = new AnchorPane();
	        buttonsPane.setPrefSize(212, 137);

	        // Create and style the buttons
	        Button buyButton = new Button("Buy");
	        buyButton.setLayoutY(35);
	        buyButton.setPrefSize(232, 38);
	        buyButton.setStyle("-fx-background-color: #535fff;");
	        buyButton.setTextFill(javafx.scene.paint.Color.WHITE);
	        buyButton.setFont(new Font(18));
	        
	        
	        buyButton.setOnAction(event -> {
	        	createPaymentPage pp = new createPaymentPage();
	        	try {
	        		if(table_name == "fish")
	        		{
						pp.createFrame(resultSet.getString("name"), resultSet.getString("price_per_piece"), quantitySpinner.getValue(), calculate(quantitySpinner.getValue(), resultSet.getString("price_per_piece")));

	        		}
	        		else if(table_name == "fish_food")
	        		{
						pp.createFrame(resultSet.getString("name"), resultSet.getString("price"), quantitySpinner.getValue(), calculate(quantitySpinner.getValue(), resultSet.getString("price")));

	        		}
	        		else if(table_name == "accessories")
	        		{
						pp.createFrame(resultSet.getString("name"), resultSet.getString("price"), quantitySpinner.getValue(), calculate(quantitySpinner.getValue(), resultSet.getString("price")));

	        		}
	        		else if(table_name == "fish_medicines")
	        		{
						pp.createFrame(resultSet.getString("name"), resultSet.getString("price"), quantitySpinner.getValue(), calculate(quantitySpinner.getValue(), resultSet.getString("price")));

	        		}
//					pp.createFrame(resultSet.getString("name"), resultSet.getString("price"), quantitySpinner.getValue(), calculate(quantitySpinner.getValue(), resultSet.getString("price")));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        });

	        Button addToCartButton = new Button("Add to Cart");
	        addToCartButton.setLayoutY(90);
	        addToCartButton.setPrefSize(232, 38);
	        addToCartButton.setStyle("-fx-background-color: #00a200;");
	        addToCartButton.setTextFill(javafx.scene.paint.Color.WHITE);
	        addToCartButton.setFont(new Font(15));
	        
	        addToCartButton.setOnAction(event -> {
	        	Preferences preferences = Preferences.userRoot().node(save_data.class.getName());
		    	String email = preferences.get("email", "");
	        	
	        	saveCartData s = new saveCartData();
	        	cartAlert c = new cartAlert();
	        	try {
	        	if(table_name == "fish")
        		{
		        	
				 s.addToCart(email, resultSet.getInt("id"), quantitySpinner.getValue(), resultSet.getDouble("price_per_piece"));
				 c.showSuccessAlert(resultSet.getString("image")); 
        		}
        		else if(table_name == "fish_food")
        		{
   				 s.addToCart(email, resultSet.getInt("id"), quantitySpinner.getValue(), resultSet.getDouble("price"));
				 c.showSuccessAlert(resultSet.getString("image")); 

        		}
        		else if(table_name == "accessories")
        		{
   				 s.addToCart(email, resultSet.getInt("accessory_id"), quantitySpinner.getValue(), resultSet.getDouble("price"));
				 c.showSuccessAlert(resultSet.getString("image_url")); 

        		}
        		else if(table_name == "fish_medicines")
        		{
      				 s.addToCart(email, resultSet.getInt("medicine_id"), quantitySpinner.getValue(), resultSet.getDouble("price"));
    				 c.showSuccessAlert(resultSet.getString("img_url")); 

        		}
	        	} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

	        	
	        });

	        // Add buttons to the buttonsPane
	        buttonsPane.getChildren().addAll(buyButton, addToCartButton);

	        // Add elements to the left VBox
	        leftVBox.getChildren().addAll(grayPane, quantitySpinner, buttonsPane);

	        // Create the right VBox
	        VBox rightVBox = new VBox();
	        rightVBox.setPrefSize(160, 394);
	        rightVBox.setStyle("-fx-padding: 5px;");
	        rightVBox.setSpacing(5);

	        Text titleText = new Text();
	        // Add elements to the right VBox

	        titleText.setText(resultSet.getString("name"));
      
	        titleText.setFont(Font.font("System Bold Italic", 40));

	        // Create a Region node for spacing
	        Region spacingRegion = new Region();
	        spacingRegion.setPrefHeight(20);
	        
	        rightVBox.getChildren().addAll(titleText, spacingRegion);
	        
//	        Text contentText = new Text();
//	        contentText.setFont(new Font(15));
	        
	   
	        
	        if(table_name == "fish")
	        {
	        	String[] array = {"name", "species", "size", "diet", "price_per_piece"};
	        	
	        	ResultSetMetaData metaData = resultSet.getMetaData();
	        	int columnCount = metaData.getColumnCount();

	        	
				for (String columnName : array) {
	        	    try {
	        	        // Find the column index by its name
	        	        int columnIndex = resultSet.findColumn(columnName);

	        	        // Get the column value using the index
	        	        String columnValue = resultSet.getString(columnIndex);

	        	        // Create a text node to display column name and value
	        	        Text contentText = new Text(metaData.getColumnName(columnIndex) + " : " + columnValue);
	        	        contentText.setFont(new Font(20));

	        	        // Add the text node to the VBox
	        	        rightVBox.getChildren().addAll(contentText);
	        	    } catch (SQLException e) {
	        	        // Handle any SQL exceptions
	        	        e.printStackTrace();
	        	    }
	        	}
	        }
	        else if(table_name == "fish_food")
	        {
	        	String[] array = {"name", "brand", "type", "package_size", "price"};
	        	ResultSetMetaData metaData = resultSet.getMetaData();
	        	int columnCount = metaData.getColumnCount();

	        	
				for (String columnName : array) {
	        	    try {
	        	        // Find the column index by its name
	        	        int columnIndex = resultSet.findColumn(columnName);

	        	        // Get the column value using the index
	        	        String columnValue = resultSet.getString(columnIndex);

	        	        // Create a text node to display column name and value
	        	        Text contentText = new Text(metaData.getColumnName(columnIndex) + " : " + columnValue);
	        	        contentText.setFont(new Font(20));

	        	        // Add the text node to the VBox
	        	        rightVBox.getChildren().addAll(contentText);
	        	    } catch (SQLException e) {
	        	        // Handle any SQL exceptions
	        	        e.printStackTrace();
	        	    }
	        	}
	        }
	        else if(table_name == "accessories")
	        {
	        	String[] array = {"name", "brand", "type", "size", "price"};
	        	ResultSetMetaData metaData = resultSet.getMetaData();
	        	int columnCount = metaData.getColumnCount();

	        	
				for (String columnName : array) {
	        	    try {
	        	        // Find the column index by its name
	        	        int columnIndex = resultSet.findColumn(columnName);

	        	        // Get the column value using the index
	        	        String columnValue = resultSet.getString(columnIndex);

	        	        // Create a text node to display column name and value
	        	        Text contentText = new Text(metaData.getColumnName(columnIndex) + " : " + columnValue);
	        	        contentText.setFont(new Font(20));

	        	        // Add the text node to the VBox
	        	        rightVBox.getChildren().addAll(contentText);
	        	    } catch (SQLException e) {
	        	        // Handle any SQL exceptions
	        	        e.printStackTrace();
	        	    }
	        	}
	        }
	        else if(table_name == "fish_medicines")
	        {
	        	String[] array = {"name", "category", "manufacturer", "expiration_date", "price"};
	        	ResultSetMetaData metaData = resultSet.getMetaData();
	        	int columnCount = metaData.getColumnCount();

	        	
				for (String columnName : array) {
	        	    try {
	        	        // Find the column index by its name
	        	        int columnIndex = resultSet.findColumn(columnName);

	        	        // Get the column value using the index
	        	        String columnValue = resultSet.getString(columnIndex);

	        	        // Create a text node to display column name and value
	        	        Text contentText = new Text(metaData.getColumnName(columnIndex) + " : " + columnValue);
	        	        contentText.setFont(new Font(20));

	        	        // Add the text node to the VBox
	        	        rightVBox.getChildren().addAll(contentText);
	        	    } catch (SQLException e) {
	        	        // Handle any SQL exceptions
	        	        e.printStackTrace();
	        	    }
	        	}
	        }
	        
	       
	     
	        // Add HBox children
	        hBox.getChildren().addAll(leftVBox, rightVBox);

	        // Add HBox to AnchorPane
	        anchorPane.getChildren().add(hBox);

	        return anchorPane;
	    }
	    
	    public TilePane generateTilePane(String table_name) throws SQLException {
	        // Create a TilePane
	        TilePane tilePane = new TilePane();
	        
	        // Set horizontal and vertical gaps between nodes
	        tilePane.setHgap(50); // Set the horizontal gap to 50 pixels
	        tilePane.setVgap(10); // Set the vertical gap to 50 pixels
	        tilePane.setStyle("-fx-padding : 10px;");
	        
	        if (table_name.equals("fish")) {
	            String[] array = {"name", "species", "family", "size", "behavior", "water_param",
	                    "habitat", "diet", "breeding", "origin"};
	            ResultSetMetaData metaData = resultSet.getMetaData();
	            int columnCount = metaData.getColumnCount();

	            for (String columnName : array) {
	                try {
	                    VBox vBox = new VBox();
	                    vBox.setStyle("-fx-padding: 5px;");

	                    int columnIndex = resultSet.findColumn(columnName);
	                    String columnValue = resultSet.getString(columnIndex);

	                    Text text1 = new Text(metaData.getColumnName(columnIndex));
	                    text1.setFont(new Font("Cambria Math", 26));

	                    Text text2 = new Text(columnValue);
	                    text2.setFont(new Font("Calibri Light", 20));

	                    vBox.getChildren().addAll(text1, text2);
	                    tilePane.getChildren().add(vBox);

	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }}
	            
	            
	            else if (table_name.equals("fish_food")) {
		            String[] array = {"name", "brand", "type", "ingredients", "protein", "fat", "fiber","moisture",	"package_size",	"price","category",	"variant","storage_instructions",	"shelf_life","supplier","supplier_contact",	"supplier_email"};
	           
		            ResultSetMetaData metaData = resultSet.getMetaData();
		            int columnCount = metaData.getColumnCount();

		            for (String columnName : array) {
		                try {
		                    VBox vBox = new VBox();
		                    vBox.setStyle("-fx-padding: 5px;");

		                    int columnIndex = resultSet.findColumn(columnName);
		                    String columnValue = resultSet.getString(columnIndex);

		                    Text text1 = new Text(metaData.getColumnName(columnIndex));
		                    text1.setFont(new Font("Cambria Math", 26));

		                    Text text2 = new Text(columnValue);
		                    text2.setFont(new Font("Calibri Light", 20));

		                    vBox.getChildren().addAll(text1, text2);
		                    tilePane.getChildren().add(vBox);

		                } catch (SQLException e) {
		                    e.printStackTrace();
		                }
		        
		            } 
	            }
	        
	            else if (table_name.equals("accessories")) {
		            String[] array = {

"name",	
"category",	
"brand",	
"type",	
"material",	
"size",	
"price",	
"additional_info",	
"weight",	
"color"
		            };
	           
		            ResultSetMetaData metaData = resultSet.getMetaData();
		            int columnCount = metaData.getColumnCount();

		            for (String columnName : array) {
		                try {
		                    VBox vBox = new VBox();
		                    vBox.setStyle("-fx-padding: 5px;");

		                    int columnIndex = resultSet.findColumn(columnName);
		                    String columnValue = resultSet.getString(columnIndex);

		                    Text text1 = new Text(metaData.getColumnName(columnIndex));
		                    text1.setFont(new Font("Cambria Math", 26));

		                    Text text2 = new Text(columnValue);
		                    text2.setFont(new Font("Calibri Light", 20));

		                    vBox.getChildren().addAll(text1, text2);
		                    tilePane.getChildren().add(vBox);

		                } catch (SQLException e) {
		                    e.printStackTrace();
		                }
		        
		            } 
	            }
	        
	            else if (table_name.equals("fish_medicines")) {
		            String[] array = {

"name",	
"category",	
"active_ingredients",	
"description",	
"dosage_instructions",	
"precautions",	
"manufacturer",	
"expiration_date"
		            };
	           
		            ResultSetMetaData metaData = resultSet.getMetaData();
		            int columnCount = metaData.getColumnCount();

		            for (String columnName : array) {
		                try {
		                    VBox vBox = new VBox();
		                    vBox.setStyle("-fx-padding: 5px;");

		                    int columnIndex = resultSet.findColumn(columnName);
		                    String columnValue = resultSet.getString(columnIndex);

		                    Text text1 = new Text(metaData.getColumnName(columnIndex));
		                    text1.setFont(new Font("Cambria Math", 26));

		                    Text text2 = new Text(columnValue);
		                    text2.setFont(new Font("Calibri Light", 20));

		                    vBox.getChildren().addAll(text1, text2);
		                    tilePane.getChildren().add(vBox);

		                } catch (SQLException e) {
		                    e.printStackTrace();
		                }
		        
		            } 
	            }
	        
	            else
	            {
	            	System.out.println("kay kartoy");
		        }
	          

	        return tilePane;
	    }
	    
//	    public VBox getBottomResults(VBox Main_frame, String query, String table_name) throws SQLException
//	    {
////	    	String q = "SELECT * FROM fish WHERE fish.relationship LIKE "+resultSet.getString("relationship")+"\r\n"
////	    			+ "UNION\r\n"
////	    			+ "SELECT * FROM fish_food WHERE fish_food.relationship LIKE "+resultSet.getString("relationship")+"\r\n"
////	    			+ "UNION\r\n"
////	    			+ "SELECT * FROM fish_medicines WHERE fish_medicines.relationship LIKE "+"SELECT * FROM fish ORDER BY RAND() LIMIT 8;+"\r\n"
////	    			+ "UNION\r\n"
////	    			+ "SELECT * FROM accessories WHERE accessories.relationship LIKE "+resultSet.getString("relationship")+";\r\n";
//	    			
//	    	
//	    	System.out.println(rword);
////	    	templateStructures t = new templateStructures();
//			VBox v = new VBox(t.make_template_Structure("related fishes", "SELECT * FROM fish LIMIT 8;", query , table_name, Main_frame));
//			return v;
//	    }
	    
	    
	    public double calculate(double count, String amount)
	    {
	    	double total;
	    	
	    	double amountValue = Double.parseDouble(amount);
	    	total = count *  amountValue;
	    	
	    	return total;
	    }
}