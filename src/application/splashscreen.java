package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import log_in.Auto_log_in;

import java.io.IOException;
import java.net.InetAddress;

public class splashscreen extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        ImageView imageView = new ImageView(new Image("src/resources/Menu icons/Screenshot 2024-03-21 213744.jpg")); // Replace "splash_image.jpg" with your image file path
        ProgressBar progressBar = new ProgressBar();
        progressBar.setPrefWidth(200);

        StackPane root = new StackPane();
        root.getChildren().addAll(imageView, progressBar);
        StackPane.setAlignment(imageView, Pos.CENTER);
        StackPane.setAlignment(progressBar, Pos.BOTTOM_CENTER);

        Scene scene = new Scene(root, 600, 400);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Splash Screen");
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
                        Platform.runLater(() -> {
                            showErrorAlert("No internet connection!");
                            primaryStage.close();
                        });
                    } else {
                    	Auto_log_in aut = new Auto_log_in();
                    	aut.auto();
                        Platform.runLater(primaryStage::close);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
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

    public static void main(String[] args) {
        launch(args);
    }
}
