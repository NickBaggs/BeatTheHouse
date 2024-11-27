package cards;

// Constructor class to create the cards

public class Card {
    private String suit;
    private String rank;
    private int value;
    private int id;

    public Card(String suit, String rank, int value, int id) {
        this.suit = suit;
        this.rank = rank;
        this.value = value;
        this.id = id;
    }

    public String getSuit() { return suit; }
    public String getRank() { return rank; }
    public int getValue() { return value; }
    public int getId() { return id; }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
