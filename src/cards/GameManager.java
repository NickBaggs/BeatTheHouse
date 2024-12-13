package cards;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.animation.PauseTransition;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.time.Duration;

import application.Main;
import data.DBStatsHandler;
import data.DBUserProfileHandler;

public class GameManager {
	private Main mainApp;
    private Deck deck;
    private PlayerHand playerHand;
    private DealerHand dealerHand;
    private int deckCount;
    private HBox playerHandContainer;
    private HBox dealerHandContainer;
    private boolean isPlayerTurn;  
    private String gameResults;    
    private DBStatsHandler statsHandler;
    private boolean doneDealing;

    // Constructor for player and dealer hands
    public GameManager(int deckCount, HBox playerHandContainer, HBox dealerHandContainer, Main mainApp) {
        this.mainApp = mainApp;  // Store the Main instance for later use
        this.deckCount = deckCount;
        this.deck = new Deck();
        this.deck.addDecks(deckCount);
        this.deck.shuffleDeck();

        this.playerHand = new PlayerHand();
        this.dealerHand = new DealerHand();

        this.playerHandContainer = playerHandContainer;
        this.dealerHandContainer = dealerHandContainer;
        this.isPlayerTurn = true;
        
        this.statsHandler = new DBStatsHandler(); // Initialize statsHandler here
    }

    
    // Start the game
    public void startGame() {
    	setDoneDealing(false);
    	
        dealPlayerCard();
                
        dealPlayerCard();
               
        dealDealerCard();
                
        dealDealerCard();
        
        displayHandsDelay();      
        
    }
    
    
    public void resetGame() {
        playerHand.clearHand();  
        dealerHand.clearHand();  
        isPlayerTurn = true;  
    }
    
    
    // Deal a card to the player
    private void dealPlayerCard() {
        if (!playerHand.hit(deck)) {
            reshuffleDeck();
            playerHand.hit(deck);
        }
    }

    
    
    // Deal a card to the dealer
    private void dealDealerCard() {
        if (!dealerHand.hit(deck)) {
            reshuffleDeck();
            dealerHand.hit(deck);
        }
    }

    
    // Method to reshuffle the deck
    private void reshuffleDeck() {
        deck.reShuffleDeck();
    }
    

    // Display the hands in the GUI using images
    private void displayHands() {
        playerHandContainer.getChildren().clear();
        dealerHandContainer.getChildren().clear();   
        
        // Add player's cards
        for (Card card : playerHand.getHand()) {
            Image cardImage = new Image("file:assets/Cards/" + card.getId() + ".png");
            ImageView cardImageView = new ImageView(cardImage);
            cardImageView.setFitWidth(100);  
            cardImageView.setFitHeight(140); 
            playerHandContainer.getChildren().add(cardImageView);
        }

        
        // Add dealer's cards 
        for (int i = 0; i < dealerHand.getHand().size(); i++) {
            Card card = dealerHand.getHand().get(i);
            String cardImagePath;

            if (i == 0 && isPlayerTurn) {
                cardImagePath = "file:assets/Cards/blue.png";  
            } else {
                
                cardImagePath = "file:assets/Cards/" + card.getId() + ".png"; 
            }

            Image cardImage = new Image(cardImagePath);
            ImageView cardImageView = new ImageView(cardImage);
            cardImageView.setFitWidth(100);  
            cardImageView.setFitHeight(140); 
            dealerHandContainer.getChildren().add(cardImageView);
        }
    }
    
    private void displayHandsDelay() {
        playerHandContainer.getChildren().clear();
        dealerHandContainer.getChildren().clear();

        // Add player's cards with delay
        for (int i = 0; i < playerHand.getHand().size(); i++) {
            final int index = i;
            pause(0.4 * i, () -> {
                Card card = playerHand.getHand().get(index);
                Image cardImage = new Image("file:assets/Cards/" + card.getId() + ".png");
                ImageView cardImageView = new ImageView(cardImage);
                cardImageView.setFitWidth(100);
                cardImageView.setFitHeight(140);
                playerHandContainer.getChildren().add(cardImageView);
            });
        }

        // Add dealer's cards with delay
        for (int i = 0; i < dealerHand.getHand().size(); i++) {
            final int index = i;
            
            pause(0.2 * i, () -> {
                Card card = dealerHand.getHand().get(index);
                String cardImagePath;

                if (index == 0 && isPlayerTurn) {
                    
                    cardImagePath = "file:assets/Cards/blue.png";
                } else {
                    cardImagePath = "file:assets/Cards/" + card.getId() + ".png";
                }

                Image cardImage = new Image(cardImagePath);
                ImageView cardImageView = new ImageView(cardImage);
                cardImageView.setFitWidth(100);
                cardImageView.setFitHeight(140);
                dealerHandContainer.getChildren().add(cardImageView);
            });
        }
        
        
    }
    
    

    public void playerTurn() {
        
        dealPlayerCard();  
        displayHands();  

        if (getPlayerHandValue() > 21) {
            System.out.println("Player busts!");
            dealerTurn();  
            return;
        }
    }

   
 // Dealer's turn
    public void dealerTurn() {
        System.out.println("Dealer's turn.");
        isPlayerTurn = false;  
        displayHands();  

        while (getDealerHandValue() < 17 && checkForBlackJack() == false) {
            System.out.println("Dealer hits.");
            dealDealerCard();
            displayHands();  
        }      
        
            System.out.println("Dealer stands with hand value: " + getDealerHandValue());
            determineWinner();  
        
    }


    
    
    // Method to calculate and return the player's hand value
    public int getPlayerHandValue() {
        return playerHand.getPlayerHandValue();
    }

    
    
    
    // Method to calculate and return the dealer's hand value
    public int getDealerHandValue() {
        return dealerHand.getDealerHandValue();
    }

    
    
    // Determine the winner of the game
    public void determineWinner() {
        int playerValue = getPlayerHandValue();
        int dealerValue = getDealerHandValue();

            
        if (playerValue > 21) {
            gameResults = "Dealer wins, Player Busted";  // Player busts, dealer wins
            statsHandler.incrementLosses(mainApp.getActiveProfileId());
            System.out.println(mainApp.getActiveProfileId() + " PLAYER BUST");
            
        } else if (dealerValue > 21) {
            gameResults = "Player wins, Dealer Busted";  // Dealer busts, player wins
            statsHandler.incrementWins(mainApp.getActiveProfileId());
            System.out.println(mainApp.getActiveProfileId() + " DEALER BUST");
            
        } else if (playerValue > dealerValue) {
            gameResults = "Player wins";  // Player wins
            statsHandler.incrementWins(mainApp.getActiveProfileId());
            System.out.println(mainApp.getActiveProfileId() + " PLAYER WINS HIGH CARD");
            
        } else if (dealerValue > playerValue) {
            gameResults = "Dealer Wins";  // Dealer wins
            statsHandler.incrementLosses(mainApp.getActiveProfileId());
            System.out.println(mainApp.getActiveProfileId() + " DEALER WINS HIGH CARD");
            
        } else {
            gameResults = "Push";  
            System.out.println(mainApp.getActiveProfileId() + " PUSH");
        }

        setWinner(gameResults);
    }

    // Set the winner result
    public void setWinner(String gameResults) {
        this.gameResults = gameResults;
    }

    
    
    // Get the winner result
    public String getWinner() {
        return gameResults;
    }


 	public boolean checkForBlackJack() {
 		boolean blackjack = false;
 		
 		if (playerHand.checkBlackJack() || dealerHand.checkBlackJack()) {
 			blackjack = true;
 		}
 		return blackjack;
 		
 	}
 	
 	

 	public void pause(double seconds, Runnable action) {
 	    // Convert seconds to milliseconds for the pause duration
 	    PauseTransition pause = new PauseTransition(javafx.util.Duration.seconds(seconds));
 	    pause.setOnFinished(e -> action.run());  // Run the action after the pause
 	    pause.play();
 	}

 	public void setDoneDealing(boolean doneDealing) {
        this.doneDealing = doneDealing;
    }
 	
 	public boolean getDoneDealing() {
 		return doneDealing;
 	}
}
