package application;

import data.DBStatsHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MainMenu {
    private StackPane layout;
    private Main mainApp;
    private DBStatsHandler statsHandler;
    private Label chipsCountLabel;  // Declare chipsCountLabel

    public MainMenu(Main mainApp) {
        this.statsHandler = new DBStatsHandler();
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

        // Create special powers mode button
        Button greenButton = new Button();
        Image greenImage = new Image("file:assets/Buttons/Simple Buttons v1.2/Special_Button.png");
        ImageView greenImageView = new ImageView(greenImage);
        greenButton.setGraphic(greenImageView);
        greenButton.setStyle("-fx-background-color: transparent; -fx-border: none;");

        // Create practice mode button
        Button blueButton = new Button();
        Image blueImage = new Image("file:assets/Buttons/Simple Buttons v1.2/Practice_Button.png");
        ImageView blueImageView = new ImageView(blueImage);
        blueButton.setGraphic(blueImageView);
        blueButton.setStyle("-fx-background-color: transparent; -fx-border: none;");

        // Create Standard Mode button
        Button redButton = new Button();
        Image redImage = new Image("file:assets/Buttons/Simple Buttons v1.2/Standard_Button.png");
        ImageView redImageView = new ImageView(redImage);
        redButton.setGraphic(redImageView);
        redButton.setStyle("-fx-background-color: transparent; -fx-border: none;");

        // Add buttons to the HBox
        buttonContainer.getChildren().addAll(greenButton, blueButton, redButton);

        // Position the button container
        buttonContainer.setTranslateY(50);

        // Button actions
        greenButton.setOnAction(e -> System.out.println("Special Powers Mode clicked"));

        // Action for Practice Mode button
        blueButton.setOnAction(e -> {
            System.out.println("Practice Mode clicked");
            mainApp.showPracticeModeScreen();
        });

        // Standard Mode
        redButton.setOnAction(e -> mainApp.showStandardModeScreen());

        // //profile screen button
        Button showProfileButton = new Button();
        Image profileImage = new Image("file:assets/Buttons/Simple Buttons v1.2/Profile_Button.png");
        ImageView profileImageView = new ImageView(profileImage);
        showProfileButton.setGraphic(profileImageView);
        showProfileButton.setStyle("-fx-background-color: transparent; -fx-border: none;");

        // Position to the top-left corner
        showProfileButton.setTranslateX(-500); 
        showProfileButton.setTranslateY(-300);

        showProfileButton.setOnAction(e -> {
            System.out.println("Show Profile Screen clicked");
            mainApp.showProfileScreen();
        });

        Button showStatsButton = new Button();
        Image statsImage = new Image("file:assets/Buttons/Simple Buttons v1.2/Stats_Button.png");
        ImageView statsImageView = new ImageView(statsImage);
        showStatsButton.setGraphic(statsImageView);
        showStatsButton.setStyle("-fx-background-color: transparent; -fx-border: none;");

        showStatsButton.setTranslateX(500); 
        showStatsButton.setTranslateY(-300);

        showStatsButton.setOnAction(e -> {
            System.out.println("Show Stats Screen clicked");  
            mainApp.showStatsScreen();
        });

        // Create Bankrupt Button 
        Button bankruptButton = new Button();
        Image bankruptImage = new Image("file:assets/Buttons/Simple Buttons v1.2/Bankrupt_Button.png");  
        ImageView bankruptImageView = new ImageView(bankruptImage);
        bankruptButton.setGraphic(bankruptImageView);
        bankruptButton.setStyle("-fx-background-color: transparent; -fx-border: none;");

        
        bankruptButton.setTranslateX(00); 
        bankruptButton.setTranslateY(250);  
        bankruptButton.setVisible(checkBankrupt());
        
        // Action for Bankrupt Button
        bankruptButton.setOnAction(e -> {
        	if(statsHandler.getChipCount(mainApp.getActiveProfileId())<5000) {
        		statsHandler.updateChipCount(mainApp.getActiveProfileId(), 5000);
        		statsHandler.incrementTimesBankrupt(mainApp.getActiveProfileId());    
        		bankruptButton.setVisible(false);
        		chipsCountLabel.setText("Chips: " + statsHandler.getChipCount(mainApp.getActiveProfileId()));
        	}
        	System.out.println("Bankrupt button clicked");
        });

        // Create Chips Count Label
        int chipCount = statsHandler.getChipCount(mainApp.getActiveProfileId());
        chipsCountLabel = new Label("Chips: " + chipCount);
        chipsCountLabel.setFont(new Font("Arial", 24));
        chipsCountLabel.setTextFill(javafx.scene.paint.Color.WHITE);
        chipsCountLabel.setStyle("-fx-background-color: transparent;");

        
        chipsCountLabel.setTranslateX(00);  
        chipsCountLabel.setTranslateY(200);  

        // Add components to layout
        layout.getChildren().addAll(titleLabel, buttonContainer, showProfileButton, showStatsButton, bankruptButton, chipsCountLabel);
    }
    
    public boolean checkBankrupt() {
    	boolean bankrupt;
    	if(statsHandler.getChipCount(mainApp.getActiveProfileId())<5000) {
    		bankrupt = true;
    	}
    	else {
    		bankrupt = false;
    	}
    	return bankrupt;
    }

    public StackPane getLayout() {
        return layout;
    }
}
