package log_in;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Log_in{
	   public void user_log_in_input(VBox v) {
//	        VBox vbox = new VBox();
//	        vbox.setPrefSize(600.0, 400.0);
//	        vbox.setStyle("-fx-padding: 20px;");

	        Text emailText = new Text("E - Mail");
	        emailText.setFont(new Font("Imprint MT Shadow", 23.0));
//	        vbox.getChildren().add(emailText);

	        TextField emailField = new TextField();
//	        vbox.getChildren().add(emailField);

	        Text passwordText = new Text("Password");
	        passwordText.setFont(new Font("Imprint MT Shadow", 23.0));
//	        vbox.getChildren().add(passwordText);

	        TextField passwordField = new TextField();
//	        vbox.getChildren().add(passwordField);
	        
	        Button button = new Button("Enter");
	        button.setMnemonicParsing(false);
	        button.setPrefSize(133.0, 36.0);
	        button.setStyle("-fx-background-radius: 5px; -fx-border-radius: 5px; -fx-background-color: #778ac9;");
	        button.setTextFill(Color.WHITE);
	        button.setFont(new Font("Imprint MT Shadow", 20.0));
	        
	        validate_login l = new validate_login();
	        
	        button.setOnAction(e -> {
	          String email = emailField.getText();
	          String password = passwordField.getText();
	        
	          l.check(email, password);
	        });
	        
	        
	        v.getChildren().clear();
	        v.getChildren().addAll(emailText, emailField, passwordText, passwordField, button);
//	        return vbox;
	    }
}