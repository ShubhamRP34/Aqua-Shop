package examples;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;

public class mediademo extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a Media object with the path to your video file
        String videoFile = "path/to/your/video.mp4";
        Media media = new Media(new File(videoFile).toURI().toString());

        // Create a MediaPlayer to play the media
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        // Create a MediaView to display the media player
        MediaView mediaView = new MediaView(mediaPlayer);

        // Create a layout to hold the MediaView
        StackPane root = new StackPane();
        root.getChildren().add(mediaView);

        // Create a Scene with the layout
        Scene scene = new Scene(root, 800, 600);

        // Set the title of the stage
        primaryStage.setTitle("MediaView Example");

        // Set the scene to the stage
        primaryStage.setScene(scene);

        // Show the stage
        primaryStage.show();

        // Play the media
        mediaPlayer.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
