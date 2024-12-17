package cards;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> cards;
    private int deckCount;

    public Deck() {
        cards = new ArrayList<>();
    }

    // Runs the addSingleDeck method the amount of times received by deckCount
    public void addDecks(int deckCount) {
        this.deckCount = deckCount;  
        for (int i = 0; i < deckCount; i++) {
            addSingleDeck();  
        }
        shuffleDeck();  
    }

    // Adds one full deck of cards to the cards list
    private void addSingleDeck() {
        
        addCardToDeck("Spades", "Ace", 1, 1);
        addCardToDeck("Spades", "2", 2, 2);
        addCardToDeck("Spades", "3", 3, 3);
        addCardToDeck("Spades", "4", 4, 4);
        addCardToDeck("Spades", "5", 5, 5);
        addCardToDeck("Spades", "6", 6, 6);
        addCardToDeck("Spades", "7", 7, 7);
        addCardToDeck("Spades", "8", 8, 8);
        addCardToDeck("Spades", "9", 9, 9);
        addCardToDeck("Spades", "10", 10, 10);
        addCardToDeck("Spades", "Jack", 10, 11);
        addCardToDeck("Spades", "Queen", 10, 12);
        addCardToDeck("Spades", "King", 10, 13);

        addCardToDeck("Hearts", "Ace", 1, 14);
        addCardToDeck("Hearts", "2", 2, 15);
        addCardToDeck("Hearts", "3", 3, 16);
        addCardToDeck("Hearts", "4", 4, 17);
        addCardToDeck("Hearts", "5", 5, 18);
        addCardToDeck("Hearts", "6", 6, 19);
        addCardToDeck("Hearts", "7", 7, 20);
        addCardToDeck("Hearts", "8", 8, 21);
        addCardToDeck("Hearts", "9", 9, 22);
        addCardToDeck("Hearts", "10", 10, 23);
        addCardToDeck("Hearts", "Jack", 10, 24);
        addCardToDeck("Hearts", "Queen", 10, 25);
        addCardToDeck("Hearts", "King", 10, 26);

        addCardToDeck("Diamonds", "Ace", 1, 27);
        addCardToDeck("Diamonds", "2", 2, 28);
        addCardToDeck("Diamonds", "3", 3, 29);
        addCardToDeck("Diamonds", "4", 4, 30);
        addCardToDeck("Diamonds", "5", 5, 31);
        addCardToDeck("Diamonds", "6", 6, 32);
        addCardToDeck("Diamonds", "7", 7, 33);
        addCardToDeck("Diamonds", "8", 8, 34);
        addCardToDeck("Diamonds", "9", 9, 35);
        addCardToDeck("Diamonds", "10", 10, 36);
        addCardToDeck("Diamonds", "Jack", 10, 37);
        addCardToDeck("Diamonds", "Queen", 10, 38);
        addCardToDeck("Diamonds", "King", 10, 39);

        addCardToDeck("Clubs", "Ace", 1, 40);
        addCardToDeck("Clubs", "2", 2, 41);
        addCardToDeck("Clubs", "3", 3, 42);
        addCardToDeck("Clubs", "4", 4, 43);
        addCardToDeck("Clubs", "5", 5, 44);
        addCardToDeck("Clubs", "6", 6, 45);
        addCardToDeck("Clubs", "7", 7, 46);
        addCardToDeck("Clubs", "8", 8, 47);
        addCardToDeck("Clubs", "9", 9, 48);
        addCardToDeck("Clubs", "10", 10, 49);
        addCardToDeck("Clubs", "Jack", 10, 50);
        addCardToDeck("Clubs", "Queen", 10, 51);
        addCardToDeck("Clubs", "King", 10, 52);
        
    }
    
    public void addSpecialDecks(int deckCount) {
    	this.deckCount = deckCount;  
        for (int i = 0; i < deckCount; i++) {
            addSingleDeck();  
            addCardToDeck("Black", "Joker", 0, 53);
            addCardToDeck("Red", "Joker", 0, 54);           
        }
        shuffleDeck();  
    }

    
    private void addCardToDeck(String suit, String rank, int value, int id) {
        cards.add(new Card(suit, rank, value, id));
    }

    // Method to get all cards
    public ArrayList<Card> getCards() {
        return cards;
    }

    // Draw a card from the deck
    public Card draw() {
        if (cards.isEmpty()) {
            reShuffleDeck();
            return cards.remove(0);  
        }
        return cards.remove(0);  
    }

    
    // Clear the Deck.
    public void clearDeck() {
        cards.clear();
    }

    
    // Shuffle the deck
    public void shuffleDeck() {
        Collections.shuffle(cards);
    }

    
    // Reshuffle the deck when it's empty
    public void reShuffleDeck() {
        if (cards.isEmpty()) {
            addDecks(deckCount);  // Re-add decks
            shuffleDeck();  // Shuffle the newly added deck
        }
    }
}
