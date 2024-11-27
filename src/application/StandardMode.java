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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;  // For winner message
import cards.GameManager;
import cards.PlayerHand;
import cards.DealerHand;

public class StandardMode {
    private Pane layout;
    private Main mainApp;
    private GameManager gameManager;
    private HBox playerHandContainer;
    private HBox dealerHandContainer;

    private Button dealButton;
    private Button hitButton;
    private Button stayButton;
    private Label winnerLabel; 

    public StandardMode(Main mainApp) {
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

        // Create the hand containers (HBox for cards)
        playerHandContainer = new HBox(10);  
        dealerHandContainer = new HBox(10);  

        // Position hands on the screen
        playerHandContainer.layoutXProperty().bind(layout.widthProperty().subtract(playerHandContainer.widthProperty()).divide(2));  
        playerHandContainer.layoutYProperty().bind(layout.heightProperty().multiply(0.7));  // Positioned below center
        
        dealerHandContainer.layoutXProperty().bind(layout.widthProperty().subtract(dealerHandContainer.widthProperty()).divide(2));
        dealerHandContainer.layoutYProperty().bind(layout.heightProperty().multiply(0.2));  // Positioned above center

        layout.getChildren().addAll(playerHandContainer, dealerHandContainer);

        // Initialize GameManager
        gameManager = new GameManager(10, playerHandContainer, dealerHandContainer);

        // Create the "Deal" button 
        dealButton = new Button();
        String dealButtonImagePath = "file:assets/Buttons/Simple Buttons v1.2/simple_ui_button_round_128_down_1.png"; 
        Image dealButtonImage = new Image(dealButtonImagePath);
        ImageView dealButtonImageView = new ImageView(dealButtonImage);
        dealButton.setGraphic(dealButtonImageView);
        dealButton.setStyle("-fx-background-color: transparent; -fx-border: none;");

        dealButton.setOnAction(e -> {
            gameManager.resetGame();  
            gameManager.startGame();  
            dealButton.setVisible(false);  
            hitButton.setVisible(true);   
            stayButton.setVisible(true); 
            winnerLabel.setText("Start the game");  
            winnerLabel.setVisible(false);  
            
            if(gameManager.checkForBlackJack()) {
            	gameManager.dealerTurn();         
                swapButtons();
                updateWinnerLabel(); 
            }
        });

        // Create HBox for Hit and Stay
        HBox buttonContainer = new HBox(30);  
        buttonContainer.setAlignment(Pos.CENTER);  

        
        // Create the "Stay" button 
        stayButton = new Button();
        String stayButtonImagePath = "file:assets/Buttons/Simple Buttons v1.2/simple_ui_button_round_128_down_2.png"; 
        Image stayButtonImage = new Image(stayButtonImagePath);
        ImageView stayButtonImageView = new ImageView(stayButtonImage);
        stayButton.setGraphic(stayButtonImageView);
        stayButton.setStyle("-fx-background-color: transparent; -fx-border: none;");
        stayButton.setVisible(false);  

        
        // Create the "Hit" button (right side)
        hitButton = new Button();
        String hitButtonImagePath = "file:assets/Buttons/Simple Buttons v1.2/simple_ui_button_round_128_down_0.png"; 
        Image hitButtonImage = new Image(hitButtonImagePath);
        ImageView hitButtonImageView = new ImageView(hitButtonImage);
        hitButton.setGraphic(hitButtonImageView);
        hitButton.setStyle("-fx-background-color: transparent; -fx-border: none;");
        hitButton.setVisible(false); 

        
        // Add the buttons to the HBox
        buttonContainer.getChildren().addAll(stayButton, hitButton);

        
        // Bind the position of the button container
        buttonContainer.layoutXProperty().bind(layout.widthProperty().subtract(buttonContainer.widthProperty()).divide(2));  
        buttonContainer.layoutYProperty().bind(layout.heightProperty().multiply(0.85));  

        
        //action for stay button
        stayButton.setOnAction(e -> {
            System.out.println("Stay button pressed");
            gameManager.dealerTurn();         
            swapButtons();
            updateWinnerLabel();  
        });
        
        //action for hit button
        hitButton.setOnAction(e -> {
            System.out.println("Hit button pressed");
            gameManager.playerTurn();  
            
            if(gameManager.getPlayerHandValue() > 21) {
                swapButtons();
                updateWinnerLabel();  
            }
        });

        
        // Initialize the winner label
        winnerLabel = new Label("Start the game");
        winnerLabel.setTextFill(Color.WHITE);
        winnerLabel.setStyle("-fx-font-size: 24px;");
        winnerLabel.setVisible(true);  
        winnerLabel.layoutXProperty().bind(layout.widthProperty().subtract(winnerLabel.widthProperty()).divide(2));
        winnerLabel.layoutYProperty().bind(layout.heightProperty().multiply(0.4));

        layout.getChildren().addAll(dealButton, buttonContainer, winnerLabel);
    }
    
    
    // Change visibility of the buttons when game starts
    public void swapButtons() {
        hitButton.setVisible(false);  
        stayButton.setVisible(false);  
        dealButton.setVisible(true);
        winnerLabel.setVisible(true);  
    }

    public Pane getLayout() {
        return layout;
    }

    //sets the winner text
    public void updateWinnerLabel() {
        String winnerText = gameManager.getWinner();  
        winnerLabel.setText(winnerText);  
        winnerLabel.setVisible(true);  
    }
}
