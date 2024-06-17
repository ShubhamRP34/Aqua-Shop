//package application;
//	
////import javafx.application.Application;
////import javafx.stage.Stage;
////import javafx.scene.Scene;
////import javafx.scene.layout.BorderPane;
////
////
////public class Main extends Application {
////	@Override
////	public void start(Stage primaryStage) {
////		try {
////			BorderPane root = new BorderPane();
////			Scene scene = new Scene(root,400,400);
////			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
////			primaryStage.setScene(scene);
////			primaryStage.show();
////		} catch(Exception e) {
////			e.printStackTrace();
////		}
////	}
////	
////	public static void main(String[] args) {
////		launch(args);
////	}
////}
//
//
//import javafx.application.Application;
//
//
//import log_in.*;
//import javafx.stage.Stage;
//import javafx.scene.Scene;
//import javafx.scene.layout.BorderPane;
//import javafx.fxml.FXMLLoader;
//import Controller.main_log_in_controller;
//import javafx.*;
//import javafx.scene.*;
//import javafx.stage.*;
//
//
//public class Main extends Application {
//	Scene scene;
//	
//	 public Stage setroot(Stage root)
//	    {
//	    	
//	    	 return root;
//	    }
//	
//	@Override
//	public void start(Stage primaryStage) {
//		try {
////			Parent root = FXMLLoader.load(getClass().getResource("/frame/Main-frame.fxml"));
////			Parent root = FXMLLoader.load(getClass().getResource("/frame/demo.fxml"));
////			Mainframegenerator frame =  new Mainframegenerator();
////			example e = new example();
////			Parent root = e.main();
//			
////			MainScrollPanel d = new MainScrollPanel();
//			
////			Parent root = d.createAnchorPaneWithScrollPane();
////			Parent root = FXMLLoader.load(getClass().getResource("/frame/Main_Log_in_page.fxml"));
//			FXMLLoader loader = new FXMLLoader(getClass().getResource("/frame/Main_Log_in_page.fxml"));
//			 Parent root = loader.load();
//			 
//
//			   // Get the controller instance
//	       main_log_in_controller controller = loader.getController();
//	       
//	       
//
//	        // Pass the primary stage to the controller
//	        controller.setPrimaryStage(primaryStage);
//
//			
//			 scene = new Scene(root);
////			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(scene);
//			primaryStage.show();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//	}     
//	
//	public static void main(String[] args) {
//		launch(args);
//	}
//}
//















package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import log_in.Auto_log_in;

import java.io.IOException;
import java.net.InetAddress;

import Controller.main_log_in_controller;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        ImageView imageView = new ImageView(new Image("resources/Menu icons/Screenshot 2024-03-21 213744.jpg")); // Replace "splash_image.jpg" with your image file path

        ProgressBar progressBar = new ProgressBar();
        progressBar.setPrefWidth(400);
        progressBar.setStyle("-fx-accent: green;-fx-background-color: green;-fx-background-radius: 30; -fx-border-radius: 30;");

//        Label loadingLabel = new Label("Loading...");
//        loadingLabel.setTextFill(Color.BLACK);

        StackPane root = new StackPane();
        root.getChildren().addAll(imageView, progressBar);
        StackPane.setAlignment(imageView, Pos.CENTER);
        StackPane.setAlignment(progressBar, Pos.BOTTOM_CENTER);
//        StackPane.setAlignment(loadingLabel, Pos.BOTTOM_CENTER);

        Scene scene = new Scene(root, 600, 400);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Splash Screen");
        
        // Set stage to maximum size
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX(primaryScreenBounds.getMinX());
        primaryStage.setY(primaryScreenBounds.getMinY());
        primaryStage.setWidth(primaryScreenBounds.getWidth());
        primaryStage.setHeight(primaryScreenBounds.getHeight());
        
        primaryStage.show();

        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                // Simulating loading process
                for (int i = 0; i <= 100; i++) {
                    Thread.sleep(50); // Simulate loading time
                    final double progress = (double) i / 100;
                    Platform.runLater(() -> progressBar.setProgress(progress));
                }

                // Check internet connectivity
                try {
                    InetAddress inetAddress = InetAddress.getByName("www.google.com");
                    if (!inetAddress.isReachable(5000)) {
//                    	this is not call logically ( catch -> calls the error box )
                        Platform.runLater(() -> {
                            showErrorAlert("No internet connection!");
                            primaryStage.close();
                        });
                    } else {
//                        Platform.runLater(primaryStage::close);
                        
                    	 Platform.runLater(() -> {
                             primaryStage.close();
                             try {
                            	 Auto_log_in aut = new Auto_log_in();
                            	 
                            	 if(aut.auto() == false)
                            	 {
                            		 openMainScreen();
                            	 }
//                             	aut.auto();
//                            	 
//                                 openMainScreen();
                            	 
                            	 	
                             } catch (IOException e) {
                                 e.printStackTrace();
                             }
                         });

                        
                    }
                } catch (IOException e) {
//                    e.printStackTrace();
                  Platform.runLater(() -> {
                    showErrorAlert("No internet connection!");
                    primaryStage.close();
                });
                }

                return null;
            }
        };

        new Thread(task).start();
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    Stage stage;
    
    private void openMainScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/frame/Main_Log_in_page.fxml")); // Replace "main_screen.fxml" with your FXML file path
        Parent root = fxmlLoader.load();
        stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Main Screen");
        
        main_log_in_controller m = new main_log_in_controller();
        
//        m.setStage(stage);
        
        // Set stage to maximum size
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX(primaryScreenBounds.getMinX());
        stage.setY(primaryScreenBounds.getMinY());
        stage.setWidth(primaryScreenBounds.getWidth());
        stage.setHeight(primaryScreenBounds.getHeight());
        
        stage.show();
  
    }
    
//    public void closeStage()
//    {
//    	stage.close();
//    }

    public static void main(String[] args) {
        launch(args);
    }
}




