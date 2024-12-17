package application;

import javafx.animation.PauseTransition;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;  // For winner message
import cards.PracticeGameManager;

public class PracticeMode {
    private Pane layout;
    private Main mainApp;
    private PracticeGameManager gameManager;
    private HBox playerHandContainer;
    private HBox dealerHandContainer;

    private Button dealButton;
    private Button hitButton;
    private Button stayButton;
    private Button mainMenuButton;  
    private Label winnerLabel; 
    private Label handValueLabel;

    public PracticeMode(Main mainApp) {
        this.mainApp = mainApp;
        layout = new Pane();  

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

        // Create the hand containers 
        playerHandContainer = new HBox(10);  
        dealerHandContainer = new HBox(10);  

        // Position hands on the screen
        playerHandContainer.layoutXProperty().bind(layout.widthProperty().subtract(playerHandContainer.widthProperty()).divide(2));  
        playerHandContainer.layoutYProperty().bind(layout.heightProperty().multiply(0.7));  
        
        dealerHandContainer.layoutXProperty().bind(layout.widthProperty().subtract(dealerHandContainer.widthProperty()).divide(2));
        dealerHandContainer.layoutYProperty().bind(layout.heightProperty().multiply(0.2));  

        layout.getChildren().addAll(playerHandContainer, dealerHandContainer);

        // Initialize PracticeGameManager
        gameManager = new PracticeGameManager(10, playerHandContainer, dealerHandContainer, mainApp);

        // Create the "Deal" button 
        dealButton = new Button();
        String dealButtonImagePath = "file:assets/Buttons/Simple Buttons v1.2/Deal_Button.png"; 
        Image dealButtonImage = new Image(dealButtonImagePath);
        ImageView dealButtonImageView = new ImageView(dealButtonImage);
        dealButton.setGraphic(dealButtonImageView);
        dealButton.setStyle("-fx-background-color: transparent; -fx-border: none;");

        // Position the Deal button 
        dealButton.layoutXProperty().bind(layout.widthProperty().subtract(dealButton.widthProperty()).divide(2));  
        dealButton.layoutYProperty().bind(layout.heightProperty().subtract(dealButton.heightProperty()).divide(2));  

        dealButton.setOnAction(e -> {
            System.out.print(mainApp.getActiveProfileId() + "PRACTICE MODE");
            gameManager.resetGame();  
            gameManager.startGame();  
            dealButton.setVisible(false);  
            mainMenuButton.setVisible(false);  
            winnerLabel.setText("Start the game");  
            winnerLabel.setVisible(false); 
            displayPlayerButtons();
        });
        
        // Create the main Menu button 
        mainMenuButton = new Button();
        String mainMenuButtonImagePath = "file:assets/Buttons/Simple Buttons v1.2/Menu_Button.png";
        Image mainMenuImage = new Image(mainMenuButtonImagePath);
        ImageView mainMenuImageView = new ImageView(mainMenuImage);
        mainMenuButton.setGraphic(mainMenuImageView);
        mainMenuButton.setStyle("-fx-background-color: transparent; -fx-border: none;");

        mainMenuButton.layoutXProperty().bind(layout.widthProperty().multiply(0.05));  
        mainMenuButton.layoutYProperty().bind(layout.heightProperty().multiply(0.05));  

        mainMenuButton.setOnAction(e -> {
            mainApp.showMainMenu();
        });

        // Create HBox for Hit and Stay
        HBox buttonContainer = new HBox(30);  
        buttonContainer.setAlignment(Pos.CENTER);  

        // Create the Stay button 
        stayButton = new Button();
        String stayButtonImagePath = "file:assets/Buttons/Simple Buttons v1.2/Stay_Button.png"; 
        Image stayButtonImage = new Image(stayButtonImagePath);
        ImageView stayButtonImageView = new ImageView(stayButtonImage);
        stayButton.setGraphic(stayButtonImageView);
        stayButton.setStyle("-fx-background-color: transparent; -fx-border: none;");
        stayButton.setVisible(false);  

        // Create the Hit button
        hitButton = new Button();
        String hitButtonImagePath = "file:assets/Buttons/Simple Buttons v1.2/Hit_Button.png"; 
        Image hitButtonImage = new Image(hitButtonImagePath);
        ImageView hitButtonImageView = new ImageView(hitButtonImage);
        hitButton.setGraphic(hitButtonImageView);
        hitButton.setStyle("-fx-background-color: transparent; -fx-border: none;");
        hitButton.setVisible(false); 

        // Add the buttons to the HBox
        buttonContainer.getChildren().addAll(stayButton, hitButton);

        // Position button container
        buttonContainer.layoutXProperty().bind(layout.widthProperty().subtract(buttonContainer.widthProperty()).divide(2));  
        buttonContainer.layoutYProperty().bind(layout.heightProperty().multiply(0.85));  

        // Action for Stay
        stayButton.setOnAction(e -> {
            System.out.println("Stay button pressed");
            gameManager.dealerTurn();         
            swapButtons();
            updateWinnerLabel();  
        });

        // Action for Hit
        hitButton.setOnAction(e -> {
            System.out.println("Hit button pressed");
            gameManager.playerTurn();  
            handValueLabel.setText(""+gameManager.getPlayerHandValue());
            if(gameManager.getPlayerHandValue() > 21) {
                swapButtons();
                updateWinnerLabel();  
            }
        });

        // Winner Label
        winnerLabel = new Label("Start the game");
        winnerLabel.setTextFill(Color.WHITE);
        winnerLabel.setStyle("-fx-font-size: 24px;");
        winnerLabel.setVisible(true);  
        winnerLabel.layoutXProperty().bind(layout.widthProperty().subtract(winnerLabel.widthProperty()).divide(2));
        winnerLabel.layoutYProperty().bind(layout.heightProperty().multiply(0.4));
        
        
      //hand value label
        handValueLabel = new Label("You should not see this");
        handValueLabel.setTextFill(Color.WHITE);
        handValueLabel.setStyle("-fx-font-size: 24px;");
        handValueLabel.setVisible(false);  
              
        handValueLabel.setTranslateX(588);
        handValueLabel.setTranslateY(500);


        layout.getChildren().addAll(dealButton, buttonContainer, winnerLabel, mainMenuButton,handValueLabel);
    }
    //Changes visibility of buttons
    public void swapButtons() {
        hitButton.setVisible(false);  
        stayButton.setVisible(false);  
        handValueLabel.setVisible(false);
        dealButton.setVisible(true);
        winnerLabel.setVisible(true); 
        mainMenuButton.setVisible(true);
    }

    public Pane getLayout() {
        return layout;
    }

    public void updateWinnerLabel() {
        String winnerText = gameManager.getWinner();  
        winnerLabel.setText(winnerText);  
        winnerLabel.setVisible(true);  
    }
    //displays hit and stay
    public void displayPlayerButtons() {
        pause(0.6, () -> {  
            hitButton.setVisible(true);   
            stayButton.setVisible(true); 
            handValueLabel.setVisible(true);
            handValueLabel.setText(""+gameManager.getPlayerHandValue());
            
            if(gameManager.checkForBlackJack()) {
                gameManager.dealerTurn();         
                swapButtons();
                updateWinnerLabel(); 
            }
        });
    }
    
    public void pause(double seconds, Runnable action) {
        PauseTransition pause = new PauseTransition(javafx.util.Duration.seconds(seconds));
        pause.setOnFinished(e -> action.run());  
        pause.play();
    }
}
