package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import data.DBStatsHandler;
import data.DBUserProfileHandler;

public class StatsScreen {
    private Pane layout;
    private Main mainApp;

    private Label winsLabel;
    private Label lossesLabel;
    private Label chipsLabel;
    private Label totalChipsWonLabel;
    private Label totalChipsLostLabel;
    private Label timesBankruptLabel;
    private Label highestChipCountLabel;
    private Label chipsCountLabel;  

    public StatsScreen(Main mainApp) {
        this.mainApp = mainApp;
        layout = new Pane();

        // Set the background image
        String backgroundImagePath = "file:assets/Backgrounds/Table_Background.png";
        BackgroundImage background = new BackgroundImage(
                new javafx.scene.image.Image(backgroundImagePath),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, true)
        );
        layout.setBackground(new Background(background));

        // Title text
        Text titleLabel = new Text("Player Stats");
        titleLabel.setFont(new Font("Arial", 36));
        titleLabel.setFill(javafx.scene.paint.Color.WHITE);

        // Position the title 
        titleLabel.layoutXProperty().bind(layout.widthProperty().subtract(titleLabel.prefWidth(-1)).divide(2));
        titleLabel.setTranslateY(60); 

        // get the active user ID
        int activeUserId = mainApp.getActiveProfileId();
        DBStatsHandler dbStatsHandler = new DBStatsHandler();
        DBUserProfileHandler dbUserProfileHandler = new DBUserProfileHandler();

        // get stats for the active user
        int wins = dbStatsHandler.getWins(activeUserId);
        int losses = dbStatsHandler.getLosses(activeUserId);
        int chipCount = dbStatsHandler.getChipCount(activeUserId);
        int chipsWon = dbStatsHandler.getChipsWon(activeUserId);
        int chipsLost = dbStatsHandler.getChipsLost(activeUserId);
        int timesBankrupt = dbStatsHandler.getTimesBankrupt(activeUserId);
        int highestChipCount = dbStatsHandler.getHighestChipCount(activeUserId);

        // Initialize the stats labels
        winsLabel = new Label("Wins: " + wins);
        lossesLabel = new Label("Losses: " + losses);
        chipsLabel = new Label("Chips: " + chipCount);
        totalChipsWonLabel = new Label("Total Chips Won: " + chipsWon);
        totalChipsLostLabel = new Label("Total Chips Lost: " + chipsLost);
        timesBankruptLabel = new Label("Times Bankrupt: " + timesBankrupt);
        highestChipCountLabel = new Label("Highest Chip Count: " + highestChipCount);

        // Set the style for the labels
        setLabelStyle(winsLabel);
        setLabelStyle(lossesLabel);
        setLabelStyle(chipsLabel);
        setLabelStyle(totalChipsWonLabel);
        setLabelStyle(totalChipsLostLabel);
        setLabelStyle(timesBankruptLabel);
        setLabelStyle(highestChipCountLabel);

        // Create a VBox to arrange the labels 
        VBox statsVBox = new VBox(15);
        statsVBox.setAlignment(Pos.CENTER);
        statsVBox.getChildren().addAll(
                winsLabel, lossesLabel, chipsLabel,
                totalChipsWonLabel, totalChipsLostLabel,
                timesBankruptLabel, highestChipCountLabel
        );

        // Position the VBox
        statsVBox.setTranslateY(250); 
        statsVBox.setTranslateX(-125);
        
        // Center the VBox 
        statsVBox.layoutXProperty().bind(layout.widthProperty().subtract(statsVBox.prefWidth(-1)).divide(2));

        // Create main menu button
        Button showMenuButton = new Button();
        Image buttonImage = new Image("file:assets/Buttons/Simple Buttons v1.2/Menu_Button.png");
        ImageView buttonImageView = new ImageView(buttonImage);
        showMenuButton.setGraphic(buttonImageView);
        showMenuButton.setStyle("-fx-background-color: transparent; -fx-border: none;");

        // Position the menu button 
        showMenuButton.setLayoutX(20);
        showMenuButton.setLayoutY(20);

        // Menu Button action
        showMenuButton.setOnAction(e -> {
            System.out.println("Show Menu Button Clicked!");
            mainApp.showMainMenu(); 
        });

        // Create Delete Button 
        Button deleteButton = new Button();
        String deleteButtonImagePath = "file:assets/Buttons/Simple Buttons v1.2/Delete_Button.png"; 
        Image deleteButtonImage = new Image(deleteButtonImagePath);
        ImageView deleteButtonImageView = new ImageView(deleteButtonImage);
        deleteButton.setGraphic(deleteButtonImageView);
        deleteButton.setStyle("-fx-background-color: transparent; -fx-border: none;");

        // Position the Delete Button
        deleteButton.setTranslateX(1040);  
        deleteButton.setTranslateY(20);  

        // Action for Delete Button
        deleteButton.setOnAction(e -> {
            System.out.println("Delete Button clicked");
            dbStatsHandler.updateStats(activeUserId,5000,0,0,0,0,0,0,5000);
            dbUserProfileHandler.deleteProfile(activeUserId);
            mainApp.showProfileScreen();
            
        });

        // Add all components to layout
        layout.getChildren().addAll(titleLabel, statsVBox, showMenuButton, deleteButton);
    }

    // method to set the style of labels
    private void setLabelStyle(Label label) {
        label.setFont(new Font("Arial", 24));
        label.setTextFill(javafx.scene.paint.Color.WHITE);
        label.setStyle("-fx-background-color: transparent;");
    }

    public Pane getLayout() {
        return layout;
    }
}
