package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import data.DBUserProfileHandler;

public class CreateProfileScreen {

    private Pane layout;
    private Main mainApp;
    private int profileId;
    private DBUserProfileHandler dbHandler;

    public CreateProfileScreen(Main mainApp, int profileId) {
        this.mainApp = mainApp;
        this.profileId = profileId;
        this.dbHandler = new DBUserProfileHandler();
        layout = new Pane();  

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

        
        // Create title text
        Text title = new Text("Name Your Profile");
        title.setFont(new Font("Arial", 24)); 
        title.setFill(Color.WHITE);  
        
        
        // Set title position
        title.layoutXProperty().bind(layout.widthProperty().subtract(title.getBoundsInLocal().getWidth()).divide(2)); 
        title.layoutYProperty().bind(layout.heightProperty().multiply(0.2)); 

        
        // Create TextField for profile name input
        TextField profileNameField = new TextField();
        profileNameField.setPromptText("Enter Profile Name");
        profileNameField.setStyle("-fx-background-color: white; -fx-text-fill: black;");
        profileNameField.layoutXProperty().bind(layout.widthProperty().subtract(profileNameField.widthProperty()).divide(2)); 
        profileNameField.layoutYProperty().bind(layout.heightProperty().multiply(0.4)); 

        
        // Create a button to save the profile name
        Button saveButton = new Button("Save Profile");
        saveButton.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        saveButton.layoutXProperty().bind(layout.widthProperty().subtract(saveButton.widthProperty()).divide(2)); 
        saveButton.layoutYProperty().bind(layout.heightProperty().multiply(0.6)); 

        
        // Set action for save button
        saveButton.setOnAction(e -> {
            String profileName = profileNameField.getText().trim();

            if (profileName.isEmpty()) {
                
                System.out.println("Profile name cannot be empty!");
            } else {
                
                dbHandler.updateProfileName(profileId, profileName);
                
                mainApp.showProfileScreen();
            }
        });

        
        layout.getChildren().addAll(title, profileNameField, saveButton);
    }

    public Pane getLayout() {
        return layout;
    }
}
