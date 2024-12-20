package cards;

import java.util.ArrayList;

public class DealerHand {
    private ArrayList<Card> hand;

    public DealerHand() {
        hand = new ArrayList<>();
    }

    public boolean hit(Deck deck) {
    	boolean successfulDraw;
    	
        Card drawnCard = deck.draw();
        if (drawnCard != null) {
            hand.add(drawnCard);
            return successfulDraw = true;
            
        } else {
            System.out.println("The deck is empty.");
            
            return successfulDraw = false;         
        }
        
    }
    
    public int getDealerHandValue() {
        int totalValue = 0;
        int aceAmount = 0;
        int jokerAmount = 0;
        ArrayList<Card> hand = getHand();  
        
        
        
        for (int i = 0; i < hand.size(); i++) {
            Card card = hand.get(i); 
            totalValue += card.getValue();
            
        
            if(card.getRank().equals("Ace")) {
            ++aceAmount;	
            }
        }
        
        for (int i = 0; i < hand.size(); i++) {
            Card card = hand.get(i);           
        
            if(card.getRank().equals("Joker")) {
            ++jokerAmount;	
            }
        }
        
         //adjust hand value based on ace
        while (totalValue<=11 && aceAmount>0) {
        	totalValue = totalValue+10;
        	--aceAmount;
        }
        
        if(jokerAmount>0) {
        	
        	if(totalValue<=11) {
        		totalValue = totalValue+10;
        	}
        	else {
        		totalValue = totalValue +(21-totalValue);
        	}
        }

        return totalValue; 
    }
    
    
    public boolean checkBlackJack() {
        boolean blackjack = false;
        Card card1 = hand.get(0);
        Card card2 = hand.get(1);
        
        if ((card1.getValue() == 10 && card2.getValue() == 1) || (card1.getValue() == 1 && card2.getValue() == 10)) {
            blackjack = true;
        }
        
        return blackjack;
    }

    public void clearHand() {
        hand.clear();  
    }

    
    public ArrayList<Card> getHand() {
        return hand;
    }
}
