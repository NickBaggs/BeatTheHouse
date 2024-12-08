package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;  // For horizontal alignment
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MainMenu {
    private StackPane layout;
    private Main mainApp;

    public MainMenu(Main mainApp) {
        this.mainApp = mainApp;
        layout = new StackPane();

        // Set the background image
        String backgroundImagePath = "file:assets/Backgrounds/Table_Background.png";
        Image backgroundImage = new Image(backgroundImagePath);

        BackgroundImage background = new BackgroundImage(
            backgroundImage, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundPosition.CENTER, 
            new BackgroundSize(100, 100, true, true, true, true)
        );

        layout.setBackground(new Background(background));

        // Title Text for MainMenu
        Text titleLabel = new Text("Select A Mode");
        titleLabel.setFont(new Font("Arial", 36));  
        titleLabel.setFill(javafx.scene.paint.Color.WHITE);  
        
        // Position the title in the center
        titleLabel.setTranslateY(-250);  

        // Create HBox for button positioning
        HBox buttonContainer = new HBox(30);  
        buttonContainer.setAlignment(javafx.geometry.Pos.CENTER);  

        // Create a green button (Special Powers Mode)
        Button greenButton = new Button();
        Image greenImage = new Image("file:assets/Buttons/Simple Buttons v1.2/Special_Button.png");
        ImageView greenImageView = new ImageView(greenImage);
        greenButton.setGraphic(greenImageView);
        greenButton.setStyle("-fx-background-color: transparent; -fx-border: none;");

        // Create a blue button (Practice Mode)
        Button blueButton = new Button();
        Image blueImage = new Image("file:assets/Buttons/Simple Buttons v1.2/Practice_Button.png");
        ImageView blueImageView = new ImageView(blueImage);
        blueButton.setGraphic(blueImageView);
        blueButton.setStyle("-fx-background-color: transparent; -fx-border: none;");

        // Create a red button (Standard Mode)
        Button redButton = new Button();
        Image redImage = new Image("file:assets/Buttons/Simple Buttons v1.2/Standard_Button.png");
        ImageView redImageView = new ImageView(redImage);
        redButton.setGraphic(redImageView);
        redButton.setStyle("-fx-background-color: transparent; -fx-border: none;");

        // Add buttons to the HBox (green first, then blue, then red)
        buttonContainer.getChildren().addAll(greenButton, blueButton, redButton);

        // Position the button container (in the middle of the screen)
        buttonContainer.setTranslateY(50);  // Adjust the Y position for desired space from the title

        // Button actions (for now, just console output)
        greenButton.setOnAction(e -> System.out.println("Special Powers Mode clicked"));

        // Action for Practice Mode button
        blueButton.setOnAction(e -> {
            System.out.println("Practice Mode clicked");
            mainApp.showPracticeModeScreen(); // Calls the method in Main to show the Practice Mode screen
        });
        
        // Navigate to StandardMode screen when red button (Standard Mode) is clicked
        redButton.setOnAction(e -> mainApp.showStandardModeScreen());

        // Add the title and button container to the layout
        layout.getChildren().addAll(titleLabel, buttonContainer);
    }

    public StackPane getLayout() {
        return layout;
    }
}
