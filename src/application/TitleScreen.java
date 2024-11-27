package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TitleScreen {
    private StackPane layout;
    private Main mainApp;

    public TitleScreen(Main mainApp) {
        this.mainApp = mainApp;
        layout = new StackPane();

        // Set the background image
        String backgroundImagePath = "file:assets/Backgrounds/Table_Background.png";
        Image backgroundImage = new Image(backgroundImagePath);

        // Set the BackgroundImage to scale to the size of the window
        BackgroundImage background = new BackgroundImage(
            backgroundImage, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundPosition.CENTER, 
            new BackgroundSize(100, 100, true, true, true, true)
        );

        layout.setBackground(new Background(background));

        // Title Text
        Text titleLabel = new Text("Beat The House");
        titleLabel.setFont(new Font("Arial", 36));  
        titleLabel.setFill(Color.WHITE);  

        // Load button image (Start Game)
        String buttonImagePath = "file:assets/Buttons/Simple Buttons v1.2/simple_ui_button_96_0.png"; 
        Image buttonImage = new Image(buttonImagePath);
        ImageView buttonImageView = new ImageView(buttonImage);
        Button startButton = new Button("Start Game");
        startButton.setGraphic(buttonImageView);  

        // Button action: transition to ProfileScreen
        startButton.setOnAction(e -> mainApp.showProfileScreen());

       
        VBox vbox = new VBox(20); 
        vbox.setAlignment(Pos.CENTER);  
        vbox.getChildren().addAll(titleLabel, startButton);  

        layout.getChildren().add(vbox);  
    }

    public StackPane getLayout() {
        return layout;
    }
}
