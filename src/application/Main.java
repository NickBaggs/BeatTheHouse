package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import data.DatabaseConnector;
import data.DBUserProfileHandler;
import data.DatabaseInitialization;

public class Main extends Application {
    private Stage primaryStage;
    private DBUserProfileHandler dbHandler;
    private int activeProfileId = 0;
    
    
    @Override
    public void start(Stage primaryStage) {
      
    	
        DatabaseConnector.getConnection();
        DatabaseInitialization dbInit = new DatabaseInitialization();
        dbInit.initializeProfiles();
        this.dbHandler = new DBUserProfileHandler();
        this.primaryStage = primaryStage;

        
        showTitleScreen();
    }
    
    
    
    //Sets selected profile ID
    public void setActiveProfileId(int profileId) {
        this.activeProfileId = profileId;
        System.out.println("Active profile ID set to: " + profileId);
    }
    
 
    public int getActiveProfileId() {
        return this.activeProfileId;
    }

    
    
    
    
    
    
    // Show the title screen
    public void showTitleScreen() {
        TitleScreen titleScreen = new TitleScreen(this);
        Scene scene = new Scene(titleScreen.getLayout(), 1200, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Beat The House");
        primaryStage.show();
    }

    // Show the profile screen
    public void showProfileScreen() {
        ProfileScreen profileScreen = new ProfileScreen(this);
        Scene scene = new Scene(profileScreen.getLayout(), 1200, 800);
        primaryStage.setScene(scene);
    }

    // Show the CreateProfileScreen for a selected profile
    public void showCreateProfileScreen(int profileId) {
        CreateProfileScreen createProfileScreen = new CreateProfileScreen(this, profileId);  
        Scene scene = new Scene(createProfileScreen.getLayout(), 1200, 800);  
        primaryStage.setScene(scene);
    }

    // Show the main menu
    public void showMainMenu() {
        MainMenu mainMenu = new MainMenu(this);
        Scene scene = new Scene(mainMenu.getLayout(), 1200, 800);
        primaryStage.setScene(scene);
    }

    // Show Standard Mode screen
    public void showStandardModeScreen() {
        StandardMode standardModeScreen = new StandardMode(this);
        Scene scene = new Scene(standardModeScreen.getLayout(), 1200, 800);  // Adjust size as needed
        primaryStage.setScene(scene);
    }
    
    public void showPracticeModeScreen() {
        PracticeMode practiceModeScreen = new PracticeMode(this);
        Scene scene = new Scene(practiceModeScreen.getLayout(), 1200, 800);  // Adjust size as needed
        primaryStage.setScene(scene);
    }
    
    public void showStatsScreen() {
        StatsScreen statsScreen = new StatsScreen(this);
        Scene scene = new Scene(statsScreen.getLayout(), 1200, 800);  // Adjust size as needed
        primaryStage.setScene(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
