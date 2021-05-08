package cards;

import java.util.ArrayList;
import java.util.Arrays;

public class Card {
    private static ArrayList<String> suit = new ArrayList<>(Arrays.asList("Heart" , "Diamond", "Club", "Spade"));;
    private static ArrayList<String> number = new ArrayList<>(Arrays.asList("Six" , "Seven", "Eight", "Nine", "Ten",
            "Jack", "Queen", "King", "Ace"));;
    private String currentSuit;
    private String currentNum;
    private int cardPower;

    public Card(int sSuit, int nNum){
        currentSuit = suit.get(sSuit);
        currentNum = number.get(nNum);
        cardPower = nNum + 1;
    }

    public String getSuit() {
        return currentSuit;
    }

    public String getNumber() {
        return currentNum;
    }

    public int getCardPower() {
        return cardPower;
    }

    public void setCardPower(int cardPower) {
        this.cardPower = cardPower;
    }
}
