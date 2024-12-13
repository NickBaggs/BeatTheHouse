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

public class StatsScreen {
    private Pane layout;
    private Main mainApp;

    private Label winsLabel;
    private Label lossesLabel;
    private Label chipsLabel;
    private Label totalChipsWonLabel;
    private Label totalChipsLostLabel;
    private Label lastLoginLabel;
    private Label timesBankruptLabel;
    private Label highestChipCountLabel;
    
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

        // Position the title near the top and center it horizontally
        titleLabel.layoutXProperty().bind(layout.widthProperty().subtract(titleLabel.prefWidth(-1)).divide(2));
        titleLabel.setTranslateY(60); // Adjust to original vertical position

        // Fetch the active user ID
        int activeUserId = mainApp.getActiveProfileId();
        DBStatsHandler dbStatsHandler = new DBStatsHandler();

        // Fetch the stats for the active user
        int wins = dbStatsHandler.getWins(activeUserId);
        int losses = dbStatsHandler.getLosses(activeUserId);
        int chipCount = dbStatsHandler.getChipCount(activeUserId);
        int chipsWon = dbStatsHandler.getChipsWon(activeUserId);
        int chipsLost = dbStatsHandler.getChipsLost(activeUserId);
        int totalWinnings = dbStatsHandler.getTotalWinnings(activeUserId);
        int timesBankrupt = dbStatsHandler.getTimesBankrupt(activeUserId);
        int highestChipCount = dbStatsHandler.getHighestChipCount(activeUserId);

        // Initialize the stats labels
        winsLabel = new Label("Wins: " + wins);
        lossesLabel = new Label("Losses: " + losses);
        chipsLabel = new Label("Chips: " + chipCount);
        totalChipsWonLabel = new Label("Total Chips Won: " + chipsWon);
        totalChipsLostLabel = new Label("Total Chips Lost: " + chipsLost);
        lastLoginLabel = new Label("Total Winnings: " + totalWinnings);
        timesBankruptLabel = new Label("Times Bankrupt: " + timesBankrupt);
        highestChipCountLabel = new Label("Highest Chip Count: " + highestChipCount);

        // Set the style for the labels
        setLabelStyle(winsLabel);
        setLabelStyle(lossesLabel);
        setLabelStyle(chipsLabel);
        setLabelStyle(totalChipsWonLabel);
        setLabelStyle(totalChipsLostLabel);
        setLabelStyle(lastLoginLabel);
        setLabelStyle(timesBankruptLabel);
        setLabelStyle(highestChipCountLabel);

        // Create a VBox to arrange the labels vertically
        VBox statsVBox = new VBox(15);
        statsVBox.setAlignment(Pos.CENTER);
        statsVBox.getChildren().addAll(
                winsLabel, lossesLabel, chipsLabel,
                totalChipsWonLabel, totalChipsLostLabel, lastLoginLabel,
                timesBankruptLabel, highestChipCountLabel
        );

        // Position the VBox and set labels' vertical positions manually
        statsVBox.setTranslateY(250); 
        statsVBox.setTranslateX(-125);
        
        // Center the VBox 
        statsVBox.layoutXProperty().bind(layout.widthProperty().subtract(statsVBox.prefWidth(-1)).divide(2));

        // Create a button to return to the main menu
        Button showMenuButton = new Button();
        Image buttonImage = new Image("file:assets/Buttons/Simple Buttons v1.2/Menu_Button.png");
        ImageView buttonImageView = new ImageView(buttonImage);
        showMenuButton.setGraphic(buttonImageView);
        showMenuButton.setStyle("-fx-background-color: transparent; -fx-border: none;");

        // Position the menu button at the top left
        showMenuButton.setLayoutX(20);
        showMenuButton.setLayoutY(20);

        // Menu Button action
        showMenuButton.setOnAction(e -> {
            System.out.println("Show Menu Button Clicked!");
            mainApp.showMainMenu(); // Navigate back to the main menu
        });

        // Add all components to the layout
        layout.getChildren().addAll(titleLabel, statsVBox, showMenuButton);
    }

    // Helper method to set the style of labels
    private void setLabelStyle(Label label) {
        label.setFont(new Font("Arial", 24));
        label.setTextFill(javafx.scene.paint.Color.WHITE);
        label.setStyle("-fx-background-color: transparent;");
    }

    public Pane getLayout() {
        return layout;
    }
}
