package Controller;

//import application.MainScrollPanel;




import Generator.*;


import java.io.IOException;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import javax.swing.JScrollPane;

import Database.database;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.util.Duration;
import log_in.save_data;

public class Main_frame_Controller implements Initializable {
	sliderGenerator m = new sliderGenerator();
	templateStructures t = new templateStructures();
	resultPageGenerator r = new resultPageGenerator();

	FlowPane fish = new FlowPane();
	FlowPane fish_food = new FlowPane();

	
	private Button lastClickedButton;

    @FXML
    private Button about_us_button;
    
    @FXML
    private VBox Main_frame_content_vbox;
    
    @FXML
    private AnchorPane main_frame_parent;
    
    @FXML
    private ScrollPane scrolllpane;

    @FXML
    private Button acc_button;

    @FXML
    private Button cart_button;

    @FXML
    private Button con_button;

    @FXML
    private HBox con_pane;

    @FXML
    private Button f_food_button;

    @FXML
    private Button facebook;

    @FXML
    private Button fish_button;

    @FXML
    private Button home_button;

    @FXML
    private Button instagram;

    @FXML
    private ImageView main_logo;

    @FXML
    private Button med_button;

    @FXML
    private Button search_button;

    @FXML
    private Button telegram;

    @FXML
    private Button user_button;

    @FXML
    private Button whatsapp;
    
    @FXML
    private TextField textfield;

    private boolean isVBoxInitialized = false;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		try {
			home(null);
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		  textfield.textProperty().addListener((observable, oldValue, newValue) -> {
	            // Clear previous results
			  Main_frame_content_vbox.getChildren() .clear();
			  fish.getChildren().clear();
			  fish_food.getChildren().clear();


	            // Execute query and display results
	            String searchText = newValue.trim();
	            if (!searchText.isEmpty()) {
	            	
	            	try {
	            	
	            		
////	            		fish.getChildren().addAll(
//	            				r.getresult("fish", "SELECT * FROM fish WHERE name LIKE '%" + searchText + "%';", Main_frame_content_vbox),
//								r.getresult("fish_food", "SELECT * FROM fish_food WHERE name LIKE '%" + searchText + "%';", Main_frame_content_vbox)
////								r.getresult("accessories", "SELECT * FROM accessories WHERE name LIKE '%" + searchText + "%';", Main_frame_content_vbox),
//								r.getresult("fish_medicines", "SELECT * FROM fish_medicines WHERE name LIKE '%" + searchText + "%';", Main_frame_content_vbox)

//	            				);
	            		
	            		if (r.getresult("fish", "SELECT * FROM fish WHERE name LIKE '%" + searchText + "%';", Main_frame_content_vbox).getChildren().isEmpty()) 
	            		{
	            		    System.out.println("FlowPane is empty");
	            		} else {
	            			Main_frame_content_vbox.getChildren().add(r.getresult("fish", "SELECT * FROM fish WHERE name LIKE '%" + searchText + "%';", Main_frame_content_vbox));
	            		}
	            		
	            		if (r.getresult("fish_food", "SELECT * FROM fish_food WHERE name LIKE '%" + searchText + "%';", Main_frame_content_vbox).getChildren().isEmpty()) 
	            		{
	            		    System.out.println("FlowPane is empty");
	            		} else {
	            			Main_frame_content_vbox.getChildren().add(r.getresult("fish_food", "SELECT * FROM fish_food WHERE name LIKE '%" + searchText + "%';", Main_frame_content_vbox));
	            		}
	            		
	            		if (r.getresult("fish_medicines", "SELECT * FROM fish_medicines WHERE name LIKE '%" + searchText + "%';", Main_frame_content_vbox).getChildren().isEmpty()) 
	            		{
	            		    System.out.println("FlowPane is empty");
	            		} else {
	            			Main_frame_content_vbox.getChildren().add(r.getresult("fish_medicines", "SELECT * FROM fish_medicines WHERE name LIKE '%" + searchText + "%';", Main_frame_content_vbox));
	            		}
	            		
	            		if (r.getresult("accessories", "SELECT * FROM accessories WHERE name LIKE '%" + searchText + "%';", Main_frame_content_vbox).getChildren().isEmpty()) 
	            		{
	            		    System.out.println("FlowPane is empty");
	            		} else {
	            			Main_frame_content_vbox.getChildren().add(r.getresult("accessories", "SELECT * FROM accessories WHERE name LIKE '%" + searchText + "%';", Main_frame_content_vbox));
	            		}
  	
//						Main_frame_content_vbox.getChildren().addAll(
//								r.getresult("fish", "SELECT * FROM fish WHERE name LIKE '%" + searchText + "%';", Main_frame_content_vbox),
//								r.getresult("fish_food", "SELECT * FROM fish_food WHERE name LIKE '%" + searchText + "%';", Main_frame_content_vbox),
//								r.getresult("accessories", "SELECT * FROM accessories WHERE name LIKE '%" + searchText + "%';", Main_frame_content_vbox),
//								r.getresult("fish_medicines", "SELECT * FROM fish_medicines WHERE name LIKE '%" + searchText + "%';", Main_frame_content_vbox)
//
//								);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            	
//	    	    	this section establish template from database
//	    	    	database data = new database();
//	
//	    	    	 // Executing query and getting result set
//	    	        ResultSet resultSet = null;
//	    			try {
//	    				resultSet = data.executeQuery("SELECT * FROM fish WHERE name LIKE '%" + searchText + "%';");	
//	    			} catch (SQLException e) {
//	    				// TODO Auto-generated catch block
//	    				e.printStackTrace();
//	    			}
//	    			
//	    			try {
//						while (resultSet.next())
//						{
//							System.out.println(resultSet.getString("name"));
//						}
//					} catch (SQLException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
	            }
	        });
		
		
		// TODO Auto-generated method stub
		con_pane.setVisible(false);
		
		// Add event handlers for mouse enter and exit events
        con_button.setOnMouseEntered(event -> con_pane.setVisible(true));
        con_button.setOnMouseExited(event -> con_pane.setVisible(false));
        
        con_pane.setOnMouseEntered(event -> {
        	con_pane.setVisible(true); 
        });
        con_pane.setOnMouseExited(event -> con_pane.setVisible(false));
	
//        This is code to set auto dimension of vbox in scrollpane
    	scrolllpane.widthProperty().addListener((observable, oldValue, newValue) -> {
			 Main_frame_content_vbox.setPrefWidth(newValue.doubleValue());
    	});
		
		scrolllpane.heightProperty().addListener((observable, oldValue, newValue) -> {
			 Main_frame_content_vbox.setPrefHeight(newValue.doubleValue());
		});
//        code ends
		

		
//		 set_vbox();
	}
	
	    @FXML
	    void about_us(MouseEvent event) {

	    }

	    @FXML
	    void accessories(MouseEvent event) {

	    	Main_frame_content_vbox.getChildren().clear();
	    	Main_frame_content_vbox.setStyle(" ");
	    	changeButtonColor(acc_button);
//	    	 buttonhover(acc_button);
	    }

	    @FXML
	    void cart(MouseEvent event) {

	    	Main_frame_content_vbox.getChildren().clear();
	    	Main_frame_content_vbox.setStyle(" ");
	    	changeButtonColor(cart_button);
	    	
	    	
	    	Preferences preferences = Preferences.userRoot().node(save_data.class.getName());
	    	String email = preferences.get("email", "");
	    	showCart show = new showCart();
//	    	u.getUserProfile(email);
	    			
	    	Main_frame_content_vbox.getChildren().add(show.fetchCartData(email));
	    	
	    	
//	    	 buttonhover(cart_button);
	    }

	    @FXML
	    void contact(MouseEvent event) {

	    }

	    @FXML
	    void fish(MouseEvent event) throws SQLException {
	    	changeButtonColor(fish_button);
	    	
	    	
	    	Main_frame_content_vbox.getChildren().clear();
	    	Main_frame_content_vbox.setStyle(" ");

	    	
	    	 Main_frame_content_vbox.getChildren().addAll(
	    			 m.setPagination("Fish_Banner_Img"), 
					 t.make_template_Structure("Populor Sales", "SELECT * FROM fish ORDER BY RAND() LIMIT 4;", "SELECT * FROM fish ORDER BY RAND() LIMIT 4;", "fish", Main_frame_content_vbox),
					 t.make_template_Structure("Most pick", "SELECT * FROM fish ORDER BY RAND() LIMIT 8;", "SELECT * FROM fish ORDER BY RAND() LIMIT 8;", "fish", Main_frame_content_vbox)
					 
					 );
	    	 
//	    	 changeButtonColor(med_button);
//	    	 buttonhover(fish_button);
	    }

	    @FXML
	    void fish_food(MouseEvent event) {
	    	changeButtonColor(f_food_button);
//	    	 buttonhover(f_food_button);

	    	Main_frame_content_vbox.getChildren().clear();
	    	Main_frame_content_vbox.setStyle(" ");
	    }

	    @FXML
	    void hide_con_opt(MouseEvent event) {

	    }

	    @FXML
	    void home(MouseEvent event) throws IOException, SQLException {

	    	
	    	Main_frame_content_vbox.getChildren().clear();
	    	Main_frame_content_vbox.setStyle(" ");
			
			 Main_frame_content_vbox.getChildren().addAll(
					 m.setPagination("Home_Banner_Img"), 
					 
					 t.make_template_Structure("Populor Sales", "SELECT * FROM fish ORDER BY RAND() LIMIT 4;", "SELECT * FROM fish ORDER BY RAND() LIMIT 4;", "fish", Main_frame_content_vbox),
					 t.make_template_Structure("Most pick", "SELECT * FROM fish ORDER BY RAND() LIMIT 8;", "SELECT * FROM fish ORDER BY RAND() LIMIT 8;", "fish", Main_frame_content_vbox),
					 t.make_template_Structure("Fish Medicines", "SELECT * FROM fish ORDER BY RAND() LIMIT 8;", "SELECT * FROM fish_medicines ORDER BY RAND() LIMIT 8;", "fish_medicines", Main_frame_content_vbox),
					 t.make_template_Structure("Popular Accessories", "SELECT * FROM fish ORDER BY RAND() LIMIT 8;", "SELECT * FROM accessories ORDER BY RAND() LIMIT 8;", "accessories", Main_frame_content_vbox),
					 t.make_template_Structure("Fish Food", "SELECT * FROM fish ORDER BY RAND() LIMIT 8;", "SELECT * FROM fish_food ORDER BY RAND() LIMIT 8;", "fish_food", Main_frame_content_vbox)

					 );
			 changeButtonColor(home_button);
//			 buttonhover(home_button);
	    }


	    @FXML
	    void medicine(MouseEvent event) throws SQLException {
	    	
	    
	    	Main_frame_content_vbox.getChildren().clear();
	    	Main_frame_content_vbox.setStyle(" ");
	    	
//	    	buttonhover(med_button);

	    	
	    	 Main_frame_content_vbox.getChildren().addAll(
	    			 m.setPagination("Home_Banner_Img"), 
					 t.make_template_Structure("Populor Sales", "SELECT * FROM fish ORDER BY RAND() LIMIT 4;", "SELECT * FROM fish ORDER BY RAND() LIMIT 4;", "fish", Main_frame_content_vbox),
					 t.make_template_Structure("Most pick", "SELECT * FROM fish ORDER BY RAND() LIMIT 8;", "SELECT * FROM fish ORDER BY RAND() LIMIT 8;", "fish", Main_frame_content_vbox)
					 
					 );
	    	 
	    	 changeButtonColor(med_button);
	    }

	    @FXML
	    void search(MouseEvent event) {

	    }

	    @FXML
	    void show_con_opt(MouseEvent event) {

	    }

	    @FXML
	    void user(MouseEvent event) {
	    	changeButtonColor(user_button);
//	     	buttonhover(user_button);
	    	

	    	Main_frame_content_vbox.getChildren().clear();
	    	Main_frame_content_vbox.setStyle(" ");
	    	
	    	Preferences preferences = Preferences.userRoot().node(save_data.class.getName());
	    	String email = preferences.get("email", "");
	    	userProfile u = new userProfile();
//	    	u.getUserProfile(email);
	    			
	    	Main_frame_content_vbox.getChildren().add(u.getUserProfile(email));
	    	
	    }

	    
	    @FXML
	    void insta_in(MouseEvent event) {
	 
	    }
	    
	    @FXML
	    void insta_out(MouseEvent event) {
	    
	    }
	    
	    @FXML
	    void whats_in(MouseEvent event) {

	    }
	    
	    @FXML
	    void whats_out(MouseEvent event) {
	   
	    }


	    @FXML
	    void tele_in(MouseEvent event) {
	    
	    }
	    
	    @FXML
	    void tele_out(MouseEvent event) {
	   
	    }
	    
	    @FXML
	    void face_in(MouseEvent event) {
	    
	    }
	    
	    @FXML
	    void face_out(MouseEvent event) {
	    	
	    }

	    VBox v;
	    
	    public VBox returnVbox()
	    {
	    	System.out.println("this is the vbox of returnvbox " + v);
	    	return v;
	    }
	    
	    
	    private void changeButtonColor(Button button) {
	        // If a button was previously clicked, reset its color
	        if (lastClickedButton != null) {
	            lastClickedButton.setStyle("-fx-background-color: #e3e3e3"); // Restore default style
	        }

	        // Set the background color of the clicked button to blue
	        button.setStyle("-fx-background-color: #8aa2eb");

	        // Update the last clicked button
	        lastClickedButton = button;
	    }
	    
//	    private void buttonhover(Button button)
//	    {
//
//	        // Add hover effect
//	        button.setOnMouseEntered(event -> button.setStyle("-fx-background-color: white;"));
//	        button.setOnMouseExited(event -> button.setStyle("-fx-background-color: #d1d1cf;"));
//
//	    }
	  
	    
	    
}