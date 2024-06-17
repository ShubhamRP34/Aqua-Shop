package examples;

import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class demo extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a VBox to hold Swing content
        VBox root = new VBox();

        // Create a SwingNode to embed the Swing video player
        SwingNode swingNode = new SwingNode();

        // Create and set up the Swing video player
        createAndSetUpVideoPlayer(swingNode);

        // Add the SwingNode to the VBox
        root.getChildren().add(swingNode);

        // Create a Scene and set it on the Stage
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX Video Player");
        primaryStage.show();
    }

    private void createAndSetUpVideoPlayer(SwingNode swingNode) {
        // Create a JPanel to hold the video player
        JPanel panel = new JPanel(new BorderLayout());

        // Create a JLabel to display the video
        JLabel videoLabel = new JLabel();

        // Set the preferred size of the video player
        panel.setPreferredSize(new Dimension(640, 480));

        // Set up the video player (replace this with your video player logic)
        String videoFilePath = "path_to_your_video_file.mp4"; // Change this to your video file path
        File videoFile = new File(videoFilePath);
        if (!videoFile.exists()) {
            System.err.println("Video file not found: " + videoFilePath);
            return;
        }
        ImageIcon videoIcon = new ImageIcon(videoFilePath);
        videoLabel.setIcon(videoIcon);
        panel.add(videoLabel, BorderLayout.CENTER);

        // Set the panel on the SwingNode
        swingNode.setContent(panel);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
