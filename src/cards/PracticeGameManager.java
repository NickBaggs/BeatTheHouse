package cards;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.animation.PauseTransition;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import application.Main;
import data.DBStatsHandler;

public class PracticeGameManager {
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
    public PracticeGameManager(int deckCount, HBox playerHandContainer, HBox dealerHandContainer, Main mainApp) {
        this.mainApp = mainApp;
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

        for (Card card : playerHand.getHand()) {
            Image cardImage = new Image("file:assets/Cards/" + card.getId() + ".png");
            ImageView cardImageView = new ImageView(cardImage);
            cardImageView.setFitWidth(100);
            cardImageView.setFitHeight(140);
            playerHandContainer.getChildren().add(cardImageView);
        }

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
        }
    }

    public void dealerTurn() {
        System.out.println("Dealer's turn.");
        isPlayerTurn = false;
        displayHands();

        while (getDealerHandValue() < 17 && !checkForBlackJack()) {
            System.out.println("Dealer hits.");
            dealDealerCard();
            displayHands();
        }

        determineWinner();
    }

    public int getPlayerHandValue() {
        return playerHand.getPlayerHandValue();
    }

    public int getDealerHandValue() {
        return dealerHand.getDealerHandValue();
    }

    public void determineWinner() {
        int playerValue = getPlayerHandValue();
        int dealerValue = getDealerHandValue();

        if (playerValue > 21) {
            gameResults = "Dealer wins, Player Busted";
           
        } else if (dealerValue > 21) {
            gameResults = "Player wins, Dealer Busted";
            
        } else if (playerValue > dealerValue) {
            gameResults = "Player wins";
            
        } else if (dealerValue > playerValue) {
            gameResults = "Dealer Wins";
            
        } else {
            gameResults = "Push";
        }
        setWinner(gameResults);
    }

    public void setWinner(String gameResults) {
        this.gameResults = gameResults;
    }

    public String getWinner() {
        return gameResults;
    }

    public boolean checkForBlackJack() {
        return playerHand.checkBlackJack() || dealerHand.checkBlackJack();
    }

    public void pause(double seconds, Runnable action) {
        PauseTransition pause = new PauseTransition(javafx.util.Duration.seconds(seconds));
        pause.setOnFinished(e -> action.run());
        pause.play();
    }

    public void setDoneDealing(boolean doneDealing) {
        this.doneDealing = doneDealing;
    }

    public boolean getDoneDealing() {
        return doneDealing;
    }
}
